package com.vishal.ink.ui.AddOwn;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.Gravity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.vishal.ink.DataBase.QuotesFavDataBase;
import com.vishal.ink.DataBase.WriteQuotes;
import com.vishal.ink.R;
import com.vishal.ink.databinding.FragmentAddBinding;
import com.vishal.ink.ui.AddOwn.OwnQuotes.WriteOwnQuotesFragment;
import com.vishal.ink.ui.PressedAnimation;

import java.util.concurrent.Executors;

public class AddOwnFragment extends Fragment implements AddFragmentAdapter.AddFragmentListener {

    private FragmentAddBinding _binding;
    private QuotesFavDataBase database;
    private AddFragmentAdapter adapter;
    private RecyclerView addFragmentRecyclerView;

    private FragmentAddBinding getBinding() {
        return _binding;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        _binding = FragmentAddBinding.inflate(inflater, container, false);
        View root = _binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView buttonAdd = getBinding().addOwnQuotes; // Updated type to ImageView if needed

        buttonAdd.setOnClickListener(v -> {
            PressedAnimation.addAnimation(v);
            WriteOwnQuotesFragment dialog = new WriteOwnQuotesFragment();
            dialog.setStyle(DialogFragment.STYLE_NORMAL, com.google.android.material.R.style.Theme_Design_Light_NoActionBar);
            dialog.show(getChildFragmentManager(), "Fragment Dialog");
        });

        addFragmentRecyclerView = getBinding().rvAddFragment;

        addFragmentRecyclerView.setLayoutManager(
                new LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        );

        database = Room.databaseBuilder(
                requireContext().getApplicationContext(),
                QuotesFavDataBase.class, "quotesFavDB"
        ).build();

        getData(view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        _binding = null;
    }

    private void getData(View view) {
        database.quotesFavDAO().getAllWriteQuotes().observe(requireActivity(), quotes -> {
            if (!quotes.isEmpty()) {
                getBinding().addCircular.setVisibility(View.GONE);
                adapter = new AddFragmentAdapter(quotes, AddOwnFragment.this);
                addFragmentRecyclerView.setAdapter(adapter);
            } else {
                getBinding().addCircular.setVisibility(View.VISIBLE);
            }
        });
    }

    public void showDialogAdd(View view, String quotes, String quotesAuthor) {
        Dialog dialog = new Dialog(view.getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fav_bottom_popup);
        ImageView backArrow = dialog.findViewById(R.id.fav_backArrowPopup);
        TextView quotesD = dialog.findViewById(R.id.tv_favQuotesPopup);
        TextView authorD = dialog.findViewById(R.id.tv_favQuotesAuthorPopup);
        ImageView copy = dialog.findViewById(R.id.iv_dialogCopyFav);
        ImageView share = dialog.findViewById(R.id.iv_dialogShareFav);

        quotesD.setText(quotes);
        authorD.setText(quotesAuthor);
        String textToCopy = quotesD.getText() + "\n:-" + authorD.getText() + "\n\nDownload Quotmotiv App On PlayStore For More Exciting Quotes";

        share.setOnClickListener(v -> shareQuotes(v.getContext(), textToCopy));
        copy.setOnClickListener(v -> {
            setClipboard(v.getContext(), textToCopy);
            dialog.dismiss();
        });
        backArrow.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
        dialog.getWindow().setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }

    public void deleteQuoteAdd(WriteQuotes writeQuotes) {
        deleteWarningPopup(writeQuotes);
    }

    private void setClipboard(Context context, String text) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager clipboard =
                    (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setText(text);
        } else {
            ClipboardManager clipboard =
                    (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("Copied Text", text);
            clipboard.setPrimaryClip(clip);
        }
        Toast.makeText(context, "Quotes Copied", Toast.LENGTH_SHORT).show();
    }

    private void shareQuotes(Context context, String text) {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, text);
        Intent intent = Intent.createChooser(shareIntent, text);
        ContextCompat.startActivity(context, intent, null);
    }

    private void deleteWarningPopup(WriteQuotes deleteQuote) {
        Dialog customDialog = new Dialog(requireContext());
        customDialog.setCancelable(false);
        customDialog.setContentView(R.layout.delete_warning_layout);
        Button yes = customDialog.findViewById(R.id.tvYes);
        Button no = customDialog.findViewById(R.id.tvNo);
        yes.setOnClickListener(v -> {
            deleteFavQuotes(deleteQuote);
            customDialog.dismiss();
        });
        no.setOnClickListener(v -> customDialog.dismiss());

        customDialog.show();
    }

    private void deleteFavQuotes(WriteQuotes deleteQuote) {
        Executors.newSingleThreadExecutor().execute(() -> database.quotesFavDAO().deleteWriteQuotes(deleteQuote));
    }
}

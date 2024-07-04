package com.vishal.ink.ui.AddOwn.OwnQuotes;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.room.Room;

import com.vishal.ink.DataBase.QuotesFavDataBase;
import com.vishal.ink.DataBase.WriteQuotes;
import com.vishal.ink.R;
import com.vishal.ink.ui.PressedAnimation;

import java.util.concurrent.Executors;

public class WriteOwnQuotesFragment extends DialogFragment {

    private QuotesFavDataBase dataBase;

    public WriteOwnQuotesFragment() {
        super(R.layout.fragment_write_own_quotes);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initializing database
        dataBase = Room.databaseBuilder(
                requireContext().getApplicationContext(),
                QuotesFavDataBase.class, "quotesFavDB"
        ).build();

        ImageView backArrow = view.findViewById(R.id.backArrowWrite);
        Button quotesSubmit = view.findViewById(R.id.submitBtnWrite);

        // Adding Data to database
        quotesSubmit.setOnClickListener(v -> addingQuotes(view));

        backArrow.setOnClickListener(v -> {
            PressedAnimation.addAnimation(v);
            // Navigate from one fragment to another
            NavHostFragment.findNavController(WriteOwnQuotesFragment.this)
                    .navigate(R.id.navigation_add);
            // Dismiss the fragment dialog
            dismiss();
        });
    }

    private void addingQuotes(View view) {
        TextView quotes = view.findViewById(R.id.writeQuotes);
        TextView quotesAuthor = view.findViewById(R.id.writeQuotesAuthor);

        // Adding Data to database
        if (!quotes.getText().toString().isEmpty() && !quotesAuthor.getText().toString().isEmpty()) {
            Executors.newSingleThreadExecutor().execute(() -> {
                dataBase.quotesFavDAO().insertWriteQuotes(
                        new WriteQuotes(
                                0, quotes.getText().toString(), quotesAuthor.getText().toString()
                        )
                );
                requireActivity().runOnUiThread(() -> {
                    Toast.makeText(view.getContext(), "Successfully Added", Toast.LENGTH_SHORT).show();
                    quotes.setText("");
                    quotesAuthor.setText("");
                    // Moving from one fragment to another
                    NavHostFragment.findNavController(WriteOwnQuotesFragment.this)
                            .navigate(R.id.navigation_add);
                    dismiss();
                });
            });
        } else if (quotes.getText().toString().isEmpty()) {
            Toast.makeText(view.getContext(), "Please Write Some Quote To Add", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(view.getContext(), "Please Provide Author", Toast.LENGTH_SHORT).show();
        }
    }
}

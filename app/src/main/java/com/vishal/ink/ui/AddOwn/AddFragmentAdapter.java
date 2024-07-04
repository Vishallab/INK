package com.vishal.ink.ui.AddOwn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.vishal.ink.DataBase.WriteQuotes;
import com.vishal.ink.R;
import com.vishal.ink.ui.PressedAnimation;

import java.util.List;

public class AddFragmentAdapter extends RecyclerView.Adapter<AddFragmentAdapter.ViewHolder> {

    private List<WriteQuotes> addedQuotesList;
    private AddFragmentListener addListener;
    private Context context;

    public AddFragmentAdapter(List<WriteQuotes> addedQuotesList, AddFragmentListener addListener) {
        this.addedQuotesList = addedQuotesList;
        this.addListener = addListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_add_quotes_layout, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        WriteQuotes addQuotesItemsViewModel = addedQuotesList.get(position);

        if (position % 2 == 0) {
            holder.addQuotesLinearLayout.setBackground(
                    ContextCompat.getDrawable(context, R.drawable.ic_cv_shape_border));
            holder.addQuotesHLinearLayout.setBackground(
                    ContextCompat.getDrawable(context, R.drawable.ic_cv_shape_border));
        } else {
            holder.addQuotesLinearLayout.setBackground(
                    ContextCompat.getDrawable(context, R.drawable.ic_cv_shape_border_with_solid));
            holder.addQuotesHLinearLayout.setBackground(
                    ContextCompat.getDrawable(context, R.drawable.ic_cv_shape_border_with_solid));
        }

        holder.addQuotesTextView.setText(addQuotesItemsViewModel.getQuotesWrite());
        holder.addQuotesTextViewAuthor.setText(addQuotesItemsViewModel.getAuthorWrite());

        holder.addQuotesLinearLayout.setOnClickListener(v -> {
            PressedAnimation.addAnimation(v);
            addListener.showDialogAdd(v, addQuotesItemsViewModel.getQuotesWrite(), addQuotesItemsViewModel.getAuthorWrite());
        });
        holder.addQuotesTextViewDelete.setOnClickListener(v -> {
            PressedAnimation.addAnimation(v);
            addListener.deleteQuoteAdd(addQuotesItemsViewModel);
        });
    }

    @Override
    public int getItemCount() {
        return addedQuotesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView addQuotesTextView;
        public LinearLayout addQuotesLinearLayout;
        public LinearLayout addQuotesHLinearLayout;
        public TextView addQuotesTextViewAuthor;
        public ImageView addQuotesTextViewDelete;

        public ViewHolder(View addQuotesItemView) {
            super(addQuotesItemView);
            addQuotesTextView = addQuotesItemView.findViewById(R.id.tv_AddQuotes);
            addQuotesLinearLayout = addQuotesItemView.findViewById(R.id.ll_H_AddQuotes);
            addQuotesHLinearLayout = addQuotesItemView.findViewById(R.id.ll_AddQuotes);
            addQuotesTextViewAuthor = addQuotesItemView.findViewById(R.id.tv_AddQuotesAuthor);
            addQuotesTextViewDelete = addQuotesItemView.findViewById(R.id.delete_AddQuotes);
        }
    }

    public interface AddFragmentListener {
        void showDialogAdd(View view, String quotes, String quotesAuthor);
        void deleteQuoteAdd(WriteQuotes writeQuotes);
    }
}

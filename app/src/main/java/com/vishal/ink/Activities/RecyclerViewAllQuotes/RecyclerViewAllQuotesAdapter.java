package com.vishal.ink.Activities.RecyclerViewAllQuotes;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.vishal.ink.R;
import com.vishal.ink.ui.PressedAnimation;

import java.util.List;

public class RecyclerViewAllQuotesAdapter extends RecyclerView.Adapter<RecyclerViewAllQuotesAdapter.ViewHolder> {

    private final List<RecyclerViewAllQuotesData> rvAllQuotesList;
    private final dialogWork show;
    private Context context;

    public RecyclerViewAllQuotesAdapter(List<RecyclerViewAllQuotesData> rvAllQuotesList, dialogWork show) {
        this.rvAllQuotesList = rvAllQuotesList;
        this.show = show;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_all_quotes_layout, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RecyclerViewAllQuotesData allQuotesItemsViewModel = rvAllQuotesList.get(position);

        if (position % 2 == 0) {
            holder.allQuotesLinearLayout.setBackground(ContextCompat.getDrawable(context, R.drawable.ic_cv_shape_border));
            holder.allQuotesHLinearLayout.setBackground(ContextCompat.getDrawable(context, R.drawable.ic_cv_shape_border));
            holder.allQuotesTextViewNumber.setBackground(ContextCompat.getDrawable(context, R.drawable.ic_cv_shape_border_with_solid));
        } else {
            holder.allQuotesLinearLayout.setBackground(ContextCompat.getDrawable(context, R.drawable.ic_cv_shape_border_with_solid));
            holder.allQuotesHLinearLayout.setBackground(ContextCompat.getDrawable(context, R.drawable.ic_cv_shape_border_with_solid));
            holder.allQuotesTextViewNumber.setBackground(ContextCompat.getDrawable(context, R.drawable.ic_cv_shape_border));
        }

        holder.allQuotesTextViewNumber.setText(allQuotesItemsViewModel.getCount());
        holder.allQuotesTextView.setText(allQuotesItemsViewModel.getQuotes());
        holder.allQuotesTextViewAuthor.setText(allQuotesItemsViewModel.getQuotesAuthor());

        holder.allQuotesLinearLayout.setOnClickListener(v -> {
            PressedAnimation.addAnimation(v);
            show.showingDialog(context, allQuotesItemsViewModel.getQuotes(), allQuotesItemsViewModel.getQuotesAuthor());
        });
    }

    @Override
    public int getItemCount() {
        return rvAllQuotesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView allQuotesTextView;
        LinearLayout allQuotesLinearLayout;
        LinearLayout allQuotesHLinearLayout;
        TextView allQuotesTextViewAuthor;
        TextView allQuotesTextViewNumber;

        public ViewHolder(View allQuotesItemView) {
            super(allQuotesItemView);
            allQuotesTextView = allQuotesItemView.findViewById(R.id.tv_allQuotes);
            allQuotesLinearLayout = allQuotesItemView.findViewById(R.id.ll_H_allQuotes);
            allQuotesHLinearLayout = allQuotesItemView.findViewById(R.id.ll_allQuotes);
            allQuotesTextViewAuthor = allQuotesItemView.findViewById(R.id.tv_allQuotesAuthor);
            allQuotesTextViewNumber = allQuotesItemView.findViewById(R.id.tv_number_allQuotes);
        }
    }

    public interface dialogWork {
        void showingDialog(Context context, String quotes, String author);
    }
}

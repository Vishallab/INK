package com.vishal.ink.ui.Favorite.RecyclerViewFavorite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;


import com.vishal.ink.DataBase.QuotesFav;
import com.vishal.ink.R;
import com.vishal.ink.ui.PressedAnimation;

import java.util.List;

public class RecyclerViewFavoriteAdapter extends RecyclerView.Adapter<RecyclerViewFavoriteAdapter.ViewHolder> {

    private List<QuotesFav> rvFavQuotesList;
    private Listener click;
    private Context context;

    public RecyclerViewFavoriteAdapter(List<QuotesFav> rvFavQuotesList, Listener click) {
        this.rvFavQuotesList = rvFavQuotesList;
        this.click = click;
    }

    // Create new views
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_favorite_layout, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    // Binds the list items to a view
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        QuotesFav allFavItemsViewModel = rvFavQuotesList.get(position);

        // Sets the text to the TextView from our itemHolder class
        if (position % 2 == 0) {
            holder.favQuotesLinearLayout.setBackground(
                    ContextCompat.getDrawable(context, R.drawable.ic_cv_shape_border)
            );
            holder.favQuotesHLinearLayout.setBackground(
                    ContextCompat.getDrawable(context, R.drawable.ic_cv_shape_border)
            );
            holder.favQuotesTextViewNumber.setBackground(
                    ContextCompat.getDrawable(context, R.drawable.ic_cv_shape_border_with_solid)
            );
        } else {
            holder.favQuotesLinearLayout.setBackground(
                    ContextCompat.getDrawable(context, R.drawable.ic_cv_shape_border_with_solid)
            );
            holder.favQuotesHLinearLayout.setBackground(
                    ContextCompat.getDrawable(context, R.drawable.ic_cv_shape_border_with_solid)
            );
            holder.favQuotesTextViewNumber.setBackground(
                    ContextCompat.getDrawable(context, R.drawable.ic_cv_shape_border)
            );
        }

        holder.favQuotesTextViewNumber.setText(String.valueOf(allFavItemsViewModel.getId()));
        holder.favQuotesTextView.setText(allFavItemsViewModel.getQuotes());
        holder.favQuotesTextViewAuthor.setText(allFavItemsViewModel.getAuthor());

        holder.favQuotesLinearLayout.setOnClickListener(v -> {
            PressedAnimation.addAnimation(v);
            click.showBottomDialog(v, allFavItemsViewModel.getQuotes(), allFavItemsViewModel.getAuthor());
        });
        holder.favQuotesTextViewDelete.setOnClickListener(v -> {
            PressedAnimation.addAnimation(v);
            click.deleteClicked(allFavItemsViewModel);
        });
    }

    // Return the number of items in the list
    @Override
    public int getItemCount() {
        return rvFavQuotesList.size();
    }

    // Holds the views for adding it to image and text
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView favQuotesTextView;
        LinearLayout favQuotesLinearLayout;
        LinearLayout favQuotesHLinearLayout;
        TextView favQuotesTextViewAuthor;
        TextView favQuotesTextViewNumber;
        ImageView favQuotesTextViewDelete;

        public ViewHolder(View allFavItemView) {
            super(allFavItemView);
            favQuotesTextView = allFavItemView.findViewById(R.id.tv_FavQuotes);
            favQuotesLinearLayout = allFavItemView.findViewById(R.id.ll_H_Fav);
            favQuotesHLinearLayout = allFavItemView.findViewById(R.id.ll_Fav);
            favQuotesTextViewAuthor = allFavItemView.findViewById(R.id.tv_FavQuotesAuthor);
            favQuotesTextViewNumber = allFavItemView.findViewById(R.id.tv_number_Fav);
            favQuotesTextViewDelete = allFavItemView.findViewById(R.id.delete_Fav);
        }
    }

    public interface Listener {
        void showBottomDialog(View view, String quotes, String quotesAuthor);
        void deleteClicked(QuotesFav deleteQuote);
    }
}

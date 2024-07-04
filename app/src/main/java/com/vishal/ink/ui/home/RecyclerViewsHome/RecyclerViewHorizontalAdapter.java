package com.vishal.ink.ui.home.RecyclerViewsHome;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.vishal.ink.Activities.AllQuotesActivity;
import com.vishal.ink.R;

import java.util.List;

import soup.neumorphism.NeumorphCardView;

public class RecyclerViewHorizontalAdapter extends RecyclerView.Adapter<RecyclerViewHorizontalAdapter.ViewHolder> {

    private final List<RecyclerViewHomeData> rvHList;

    public RecyclerViewHorizontalAdapter(List<RecyclerViewHomeData> rvHList) {
        this.rvHList = rvHList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.rv_horizontal_home_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RecyclerViewHomeData horizontalItemsViewModel = rvHList.get(position);

        // Sets the image to the ImageView from the ViewHolder class
        holder.horizontalImageView.setImageResource(horizontalItemsViewModel.getImage());

        // Sets the text to the TextView from the ViewHolder class
        holder.horizontalTextView.setText(horizontalItemsViewModel.getText());
        holder.horizontalCardView.setOnClickListener(v -> startActivityOther(v, horizontalItemsViewModel.getText()));
    }

    @Override
    public int getItemCount() {
        return rvHList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView horizontalImageView;
        final TextView horizontalTextView;
        final NeumorphCardView horizontalCardView;

        public ViewHolder(View itemView) {
            super(itemView);
            horizontalImageView = itemView.findViewById(R.id.iv_horizontalHome);
            horizontalTextView = itemView.findViewById(R.id.tv_horizontalHome);
            horizontalCardView = itemView.findViewById(R.id.cv_horizontalHome);
        }
    }

    private void startActivityOther(View view, String quotesType) {
        Context context = view.getContext();
        Intent intent = new Intent(context, AllQuotesActivity.class);
        intent.putExtra("QuotesName", quotesType);
        ContextCompat.startActivity(context, intent, null);
    }
}

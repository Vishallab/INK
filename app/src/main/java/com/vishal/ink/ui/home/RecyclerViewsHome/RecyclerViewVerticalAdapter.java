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

public class RecyclerViewVerticalAdapter extends RecyclerView.Adapter<RecyclerViewVerticalAdapter.ViewHolder> {

    private final List<RecyclerViewHomeData> rvLList;

    public RecyclerViewVerticalAdapter(List<RecyclerViewHomeData> rvLList) {
        this.rvLList = rvLList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_vertical_home_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RecyclerViewHomeData verticalItemsViewModel = rvLList.get(position);

        // Sets the image to the imageview from the ViewHolder class
        holder.verticalImageView.setImageResource(verticalItemsViewModel.getImage());

        // Sets the text to the textview from the ViewHolder class
        holder.verticalTextView.setText(verticalItemsViewModel.getText());
        holder.verticalCardView.setOnClickListener(v -> startActivityOther(v, verticalItemsViewModel.getText()));
    }

    @Override
    public int getItemCount() {
        return rvLList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView verticalImageView;
        final TextView verticalTextView;
        final NeumorphCardView verticalCardView;

        public ViewHolder(View itemView) {
            super(itemView);
            verticalImageView = itemView.findViewById(R.id.iv_VerticalHome);
            verticalTextView = itemView.findViewById(R.id.tv_VerticalHome);
            verticalCardView = itemView.findViewById(R.id.cv_VerticalHome);
        }
    }

    private void startActivityOther(View view, String quotesTypeV) {
        Context context = view.getContext();
        Intent intent = new Intent(context, AllQuotesActivity.class);
        intent.putExtra("VerticalQuotesName", quotesTypeV);
        ContextCompat.startActivity(context, intent, null);
    }
}

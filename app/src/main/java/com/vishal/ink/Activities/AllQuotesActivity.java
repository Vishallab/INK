package com.vishal.ink.Activities;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.vishal.ink.Activities.RecyclerViewAllQuotes.AllTypeOfQuotes;
import com.vishal.ink.Activities.RecyclerViewAllQuotes.RecyclerViewAllQuotesAdapter;
import com.vishal.ink.Activities.RecyclerViewAllQuotes.RecyclerViewAllQuotesData;
import com.vishal.ink.DataBase.QuotesFav;
import com.vishal.ink.DataBase.QuotesFavDataBase;
import com.vishal.ink.R;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AllQuotesActivity extends AppCompatActivity implements RecyclerViewAllQuotesAdapter.dialogWork {
    private Dialog dialog;
    private String titleQuotesType;
    private QuotesFavDataBase database;
    private ExecutorService executorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_quotes);

        androidx.appcompat.widget.Toolbar toolbarallQuotesactivity = findViewById(R.id.toolbar_allQuotes_activity);
        setSupportActionBar(toolbarallQuotesactivity);

        database = Room.databaseBuilder(
                getApplicationContext(),
                QuotesFavDataBase.class, "quotesFavDB"
        ).build();

        executorService = Executors.newSingleThreadExecutor(); // Initialize ExecutorService

        String actionTitle = getIntent().getStringExtra("QuotesName");
        String verticalActionTitle = getIntent().getStringExtra("VerticalQuotesName");
        android.app.ActionBar actionbar = getActionBar(); // ActionBar

        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true); // Set back button
            if (actionTitle != null) {
                actionbar.setTitle(actionTitle);
                titleQuotesType = actionTitle;
            } else if (verticalActionTitle != null) {
                actionbar.setTitle(verticalActionTitle);
                titleQuotesType = verticalActionTitle;
            }
        }

        toolbarallQuotesactivity.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        if (actionTitle != null) {
            showDialog();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    getQuotes(actionTitle);
                }
            }, 1000);
        } else if (verticalActionTitle != null) {
            showDialog();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    getQuotes(verticalActionTitle);
                }
            }, 1000);
        }
    }

    private void getQuotes(String quotesType) {
        switch (quotesType) {
            case "Time Quotes":
                writeDataInRecyclerView(AllTypeOfQuotes.getTimeQuotes());
                break;
            case "Positive Quotes":
                writeDataInRecyclerView(AllTypeOfQuotes.getPositiveQuotes());
                break;
            case "Inspiration Quotes":
                writeDataInRecyclerView(AllTypeOfQuotes.getInspirationQuotes());
                break;
            case "Best Life Quotes":
                writeDataInRecyclerView(AllTypeOfQuotes.getBestLifeQuotes());
                break;
            case "Success Quotes":
                writeDataInRecyclerView(AllTypeOfQuotes.getSuccessQuotes());
                break;
            case "Wisdom Quotes":
                writeDataInRecyclerView(AllTypeOfQuotes.getWisdomQuotes());
                break;
            case "Relationship Quotes":
                writeDataInRecyclerView(AllTypeOfQuotes.getRelationshipQuotes());
                break;
            case "Nature Quotes":
                writeDataInRecyclerView(AllTypeOfQuotes.getNatureQuotes());
                break;
            case "Love Quotes":
                writeDataInRecyclerView(AllTypeOfQuotes.getLoveQuotes());
                break;
            case "Angry Quotes":
                writeDataInRecyclerView(AllTypeOfQuotes.getAngryQuotes());
                break;
            case "Motivational Quotes":
                writeDataInRecyclerView(AllTypeOfQuotes.getMotivationalQuotes());
                break;
            case "Sad Quotes":
                writeDataInRecyclerView(AllTypeOfQuotes.getSadQuotes());
                break;
            case "Alone Quotes":
                writeDataInRecyclerView(AllTypeOfQuotes.getAloneQuotes());
                break;
            case "Life Quotes":
                writeDataInRecyclerView(AllTypeOfQuotes.getLifeQuotes());
                break;
            case "Attitude Quotes":
                writeDataInRecyclerView(AllTypeOfQuotes.getAttitudeQuotes());
                break;
            case "Trust Quotes":
                writeDataInRecyclerView(AllTypeOfQuotes.getTrustQuotes());
                break;
            case "Friendship Quotes":
                writeDataInRecyclerView(AllTypeOfQuotes.getFriendshipQuotes());
                break;
            case "Leadership Quotes":
                writeDataInRecyclerView(AllTypeOfQuotes.getLeadershipQuotes());
                break;
            case "Happiness Quotes":
                writeDataInRecyclerView(AllTypeOfQuotes.getHappinessQuotes());
                break;
            case "Smiling Quotes":
                writeDataInRecyclerView(AllTypeOfQuotes.getSmilingQuotes());
                break;
        }
    }

    private void showDialog() {
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_wait);
        dialog.setCancelable(false);
        dialog.show();
    }

    private void hideDialog() {
        dialog.dismiss();
    }

    private void writeDataInRecyclerView(ArrayList<RecyclerViewAllQuotesData> objectFunction) {
        RecyclerView allQuotesRecyclerView = findViewById(R.id.rv_allQuotes);
        allQuotesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewAllQuotesAdapter allQuotesAdapter = new RecyclerViewAllQuotesAdapter(objectFunction, this);
        allQuotesRecyclerView.setAdapter(allQuotesAdapter);
        hideDialog();
    }

    @Override
    public void showingDialog(Context context, String quotes, String author) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottom_popup);
        ImageView backArrow = dialog.findViewById(R.id.backArrowPopup);
        TextView quotesD = dialog.findViewById(R.id.tv_allQuotesPopup);
        TextView authorD = dialog.findViewById(R.id.tv_allQuotesAuthorPopup);
        ImageView copy = dialog.findViewById(R.id.iv_dialogCopy);
        ImageView share = dialog.findViewById(R.id.iv_dialogShare);
        ImageView favorite = dialog.findViewById(R.id.iv_dialogFavorite);

        quotesD.setText(quotes);
        authorD.setText(author);
        final String textToCopy = quotesD.getText().toString() + "\n:-" + authorD.getText().toString() + "\n\nDownload Quotmotiv App On PlayStore For More Exciting Quotes";

        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Adding Data to database
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        database.quotesFavDAO().insertQuotes(new QuotesFav(0, quotes, author));
                    }
                });
                Toast.makeText(v.getContext(), "Added to Favorite", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareQuotes(v.getContext(), textToCopy);
            }
        });

        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setClipboard(v.getContext(), textToCopy);
                dialog.dismiss();
            }
        });

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }

    private void setClipboard(Context context, String text) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setText(text);
        } else {
            ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
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
}

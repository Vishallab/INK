package com.vishal.ink.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import com.vishal.ink.R;

public class PressedAnimation extends AppCompatActivity {

    public static void addAnimation(View view) {
        int[] attrs = new int[]{androidx.appcompat.R.attr.selectableItemBackgroundBorderless};
        Context context = view.getContext();
        TypedArray typedArray = context.obtainStyledAttributes(attrs);
        int backgroundResource = typedArray.getResourceId(0, 0);
        view.setBackgroundResource(backgroundResource);
        typedArray.recycle();  // Don't forget to recycle TypedArray
    }
}

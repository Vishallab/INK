package com.vishal.ink.ui.home;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vishal.ink.R;
import com.vishal.ink.databinding.FragmentHomeBinding;
import com.vishal.ink.ui.PressedAnimation;
import com.vishal.ink.ui.home.RecyclerViewsHome.CommonData;
import com.vishal.ink.ui.home.RecyclerViewsHome.RecyclerViewHomeData;
import com.vishal.ink.ui.home.RecyclerViewsHome.RecyclerViewHorizontalAdapter;
import com.vishal.ink.ui.home.RecyclerViewsHome.RecyclerViewVerticalAdapter;

import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.naniagtionBarCopyright.setOnClickListener(view -> {
            PressedAnimation.addAnimation(view);
            showBottomDialog(root);
        });

        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Horizontal RecyclerView
        RecyclerView horizontalRecyclerView = binding.rvHorizontalHome;
        horizontalRecyclerView.setLayoutManager(new LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false));
        List<RecyclerViewHomeData> horizontalRecyclerViewData = CommonData.getPopularData(); // Corrected type
        RecyclerViewHorizontalAdapter horizontalAdapter = new RecyclerViewHorizontalAdapter(horizontalRecyclerViewData);
        horizontalRecyclerView.setAdapter(horizontalAdapter);

        // Vertical RecyclerView
        RecyclerView verticalRecyclerView = binding.rvVerticalHome;
        verticalRecyclerView.setLayoutManager(new GridLayoutManager(requireActivity(), 2));
        List<RecyclerViewHomeData> verticalRecyclerViewData = CommonData.getCategoryData(); // Corrected type
        RecyclerViewVerticalAdapter verticalAdapter = new RecyclerViewVerticalAdapter(verticalRecyclerViewData);
        verticalRecyclerView.setAdapter(verticalAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void showBottomDialog(View view) {
        Dialog dialog = new Dialog(view.getContext(), R.style.DialogAnimation);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.show();
        // Setting dialog width and height
        Window window = dialog.getWindow();
        if (window != null) {
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            window.getAttributes().windowAnimations = R.style.DialogAnimation;
            window.setGravity(Gravity.BOTTOM);
        }
    }
}

package com.example.aplikasitokoonline.Activity.ui.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.aplikasitokoonline.BackGroundTasks.BackgroundTaskProduct;
import com.example.aplikasitokoonline.R;
import com.example.aplikasitokoonline.databinding.FragmentMenuBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;


public class MenuFragment extends Fragment {



    private com.example.aplikasitokoonline.databinding.FragmentMenuBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentMenuBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ListView lv = root.findViewById(R.id.listviewbarang);

        new BackgroundTaskProduct(getContext(), lv).execute("http://103.67.187.184/api/products");
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
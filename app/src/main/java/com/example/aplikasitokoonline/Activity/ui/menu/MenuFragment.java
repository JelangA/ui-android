package com.example.aplikasitokoonline.Activity.ui.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.aplikasitokoonline.R;
import com.example.aplikasitokoonline.databinding.FragmentMenuBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;


public class MenuFragment extends Fragment {

    private ListView lv;
    private FloatingActionButton btn;

    String id, menuName, desc, price;

    ArrayList<HashMap<String,String>> menuList;

    private com.example.aplikasitokoonline.databinding.FragmentMenuBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentMenuBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final ListView lv = binding.listviewbarang;

//        ListAdapter adapter = new SimpleAdapter(
//                MenuFragment.this,
//                menuList,
//                R.layout.card_layout,
//                new String[]{"id", "menuName", "desc", "price"},
//                new int[]{R.id.txtnama3, R.id.txtharga3, R.id.txtStars3, R.id.txtid3});
//
//        lv.setAdapter(adapter);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
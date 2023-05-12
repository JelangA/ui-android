package com.example.aplikasitokoonline.Activity.ui.account;

import com.example.aplikasitokoonline.Activity.LoginActivity;
import com.example.aplikasitokoonline.databinding.FragmentAccountBinding;
import com.example.aplikasitokoonline.models.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;



public class AccountFragment extends Fragment {

    TextView nama, nomor, alamat;

    private FragmentAccountBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentAccountBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        User user = new User();

        TextView nama = binding.namauser4;
        nama.setText(user.getNama());
        TextView alamat = binding.alamat4;
        alamat.setText(user.getAddress());
        TextView nohp = binding.nomoruser4;
        nohp.setText(user.getId());


        LinearLayout linearLayout = binding.logot4;
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tambahkan kode aksi yang diinginkan di sini

            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
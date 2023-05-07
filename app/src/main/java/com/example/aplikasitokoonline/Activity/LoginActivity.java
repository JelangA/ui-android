package com.example.aplikasitokoonline.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.aplikasitokoonline.BackGroundTasks.BackgroundTaskProduct;
import com.example.aplikasitokoonline.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        BackgroundTaskProduct backgroundTaskProduct = new BackgroundTaskProduct(getApplicationContext());
        backgroundTaskProduct.execute("data");
    }
}
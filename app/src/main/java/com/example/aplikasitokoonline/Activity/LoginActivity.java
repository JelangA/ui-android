package com.example.aplikasitokoonline.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aplikasitokoonline.BackGroundTasks.BackgroundTaskAuth;
import com.example.aplikasitokoonline.R;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnLogin = (Button) findViewById(R.id.btnLogin1);
        Button btnRegister = (Button) findViewById(R.id.btnRegister1);
        EditText txtUsername = (EditText) findViewById(R.id.txtUsername1);
        EditText txtPassword = (EditText) findViewById(R.id.txtPassword1);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = txtUsername.getText().toString();
                String password = txtPassword.getText().toString();

                try{
                    JSONObject json = new JSONObject();
                    json.put("username", username);
                    json.put("password", password);

                    BackgroundTaskAuth taskAuth = new BackgroundTaskAuth(getApplicationContext(), json);
                    taskAuth.execute("http://103.67.187.184/api/login");

                }catch (Exception e){
                    e.printStackTrace();
                }


            }
        });


    }
}
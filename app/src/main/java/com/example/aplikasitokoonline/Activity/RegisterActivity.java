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

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button btnRegister = (Button) findViewById(R.id.btnRegister2);
        Button btnPunyaAkun = (Button) findViewById(R.id.btnBack2);
        EditText txtalamat = (EditText) findViewById(R.id.txtAlamat2);
        EditText txtusername = (EditText) findViewById(R.id.txtUsername2);
        EditText txtpassword = (EditText) findViewById(R.id.txtPassword2);
        EditText txtnama = (EditText) findViewById(R.id.txtNamaLengkap2);
        EditText txtConfirm = (EditText) findViewById(R.id.txtFKonfirmasi2);

        btnPunyaAkun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String alamat = txtalamat.getText().toString();
                String username = txtusername.getText().toString();
                String password = txtpassword.getText().toString();
                String nama = txtnama.getText().toString();
                String confirm = txtConfirm.getText().toString();

                try{
                    JSONObject json = new JSONObject();
                    json.put( "address", alamat);
                    json.put("username", username);
                    json.put("password", password);
                    json.put("name", nama);
                    json.put("password_confirmation", confirm);

                    BackgroundTaskAuth taskAuth = new BackgroundTaskAuth(getApplicationContext(), json);
                    taskAuth.execute("http://103.67.187.184/api/register");

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });



    }
}
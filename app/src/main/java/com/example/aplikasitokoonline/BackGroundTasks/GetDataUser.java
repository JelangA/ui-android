package com.example.aplikasitokoonline.BackGroundTasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.aplikasitokoonline.Activity.MenuActivity;
import com.example.aplikasitokoonline.models.Product;
import com.example.aplikasitokoonline.models.User;
import com.example.aplikasitokoonline.utils.Token;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetDataUser extends AsyncTask<String, String ,String> {

    private Context context;
    private ProgressDialog progressDialog;

    public GetDataUser(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
        String urlString = params[0];

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // tambahkan header dengan key "Authorization" dan value "Bearer {token}"
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + Token.getToken());

            int responseCode = connection.getResponseCode();
            String result = "";
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                result = stringBuilder.toString();

                JSONObject obj = new JSONObject(result);
                User user = new User();
                user.setId(obj.getInt("id"));
                user.setName(obj.getString("name"));
                user.setAddress(obj.getString("address"));
                user.setImage(obj.getString("image"));


            } else {
                InputStream inputStream = connection.getErrorStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                result = stringBuilder.toString();
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return "Gagal mendapat data user";
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (Token.getToken() != ""){
            Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, MenuActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
        }else{
            Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
        }
    }
}

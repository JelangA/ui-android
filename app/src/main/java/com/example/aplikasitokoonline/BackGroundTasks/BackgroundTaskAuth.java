package com.example.aplikasitokoonline.BackGroundTasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.aplikasitokoonline.Activity.MenuActivity;
import com.example.aplikasitokoonline.utils.Token;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class BackgroundTaskAuth extends AsyncTask<String, String, String> {

    private Context mContext;
    private JSONObject requestData;

    public BackgroundTaskAuth(Context mContext, JSONObject requestData) {
        this.mContext = mContext;
        this.requestData = requestData;
    }

    @Override
    protected String doInBackground(String... params) {
        String urlString = params[0];
        String result = "";

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + Token.getToken());
            connection.setDoOutput(true);

            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(requestData.toString().getBytes());
            outputStream.close();

            int responseCode = connection.getResponseCode();

            if (responseCode >= 200 && responseCode < 300) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                JSONObject response = new JSONObject(stringBuilder.toString());
                String data = response.getString("data");
                result = response.getString("message");
                String[] parts = data.split("\\|");
                String token = parts[1];
                Token.setToken(token);

            }else {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                JSONObject response = new JSONObject(stringBuilder.toString());
                String error = response.getString("message");

                result = error;
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Task Failed URL Not Found";
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (Token.getToken() != ""){
            Toast.makeText(mContext, s, Toast.LENGTH_SHORT).show();
            if (s.contains("Berhasil Login") || s.contains("Berhasil Register")){
                Intent intent = new Intent(mContext, MenuActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        }else{
            Toast.makeText(mContext, s, Toast.LENGTH_SHORT).show();
        }

        GetDataUser getDataUser = new GetDataUser(mContext.getApplicationContext());
        getDataUser.execute("http://103.67.187.184/api/user");

    }
}

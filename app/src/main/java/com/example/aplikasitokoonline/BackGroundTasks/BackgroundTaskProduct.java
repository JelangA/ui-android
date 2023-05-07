package com.example.aplikasitokoonline.BackGroundTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.aplikasitokoonline.utils.AppConst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class BackgroundTaskProduct extends AsyncTask<String, String, String> {

    Context ctx;

    String result;

    public BackgroundTaskProduct(Context ctx) {
        this.ctx = ctx;
    }


    @Override
    protected String doInBackground(String... strings) {
        String type = strings[0];

        if (type.equals("data")){
            String urls = "http://10.0.2.2:3000/api/barang";
            try{
                URL url = new URL(urls);

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setDoOutput(true);
                connection.setDoInput(true);

                InputStream is = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, "ISO-8859-1"));
                StringBuilder sb = new StringBuilder();

                String line = "";
                while ((line = bufferedReader.readLine()) != null){
                    sb.append(line).append("\n");
                }

                is.close();
                bufferedReader.close();
                connection.disconnect();
                result = sb.toString();
                return sb.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "gagal task";
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Toast.makeText(ctx, s, Toast.LENGTH_LONG).show();
    }
}

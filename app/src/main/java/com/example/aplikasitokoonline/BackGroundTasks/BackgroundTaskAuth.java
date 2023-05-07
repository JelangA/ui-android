package com.example.aplikasitokoonline.BackGroundTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.aplikasitokoonline.utils.AppConst;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class BackgroundTaskAuth extends AsyncTask<String, String, String> {

    Context context;

    public BackgroundTaskAuth(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];

        if (type.equals("login")){
            String username = params[1];
            String password = params[2];

            try {
                URL url = new URL(AppConst.URL_LOGIN);

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.addRequestProperty("Content-Type", "application/json");
                httpURLConnection.setDoInput(true);

                JSONObject json = new JSONObject();
                json.put("username", username);
                json.put("password", password);

                OutputStream os = httpURLConnection.getOutputStream();
                os.write(json.toString().getBytes(StandardCharsets.UTF_8));
                os.close();

                InputStream is = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, "ISO-8859-1"));
                StringBuilder sb = new StringBuilder();

                String response = "";
                while ((response = bufferedReader.readLine()) != null) {
                    sb.append(response).append("\n");
                }

                response = sb.toString();

                is.close();
                bufferedReader.close();
                httpURLConnection.disconnect();

                return response;

            } catch (ProtocolException e) {
                e.printStackTrace();
                return "error 1";
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "error 2";
            } catch (JSONException e) {
                e.printStackTrace();
                return "Json not found";
            }  catch (IOException e) {
                e.printStackTrace();
                return "Data not found";
            }

        } else if (type.equals("register")){
            if (type.equals("login")){
                String nama_lengkap = params[1];
                String username = params[2];
                String alamat = params[3];
                String password = params[4];

                try {
                    URL url = new URL(AppConst.URL_REGISTER);

                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.addRequestProperty("Content-Type", "application/json");
                    httpURLConnection.setDoInput(true);

                    JSONObject json = new JSONObject();
                    json.put("nama_lengkap", nama_lengkap);
                    json.put("username", username);
                    json.put("alamat", alamat);
                    json.put("password", password);

                    OutputStream os = httpURLConnection.getOutputStream();
                    os.write(json.toString().getBytes(StandardCharsets.UTF_8));
                    os.close();

                    InputStream is = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, "ISO-8859-1"));
                    StringBuilder sb = new StringBuilder();

                    String response = "";
                    while ((response = bufferedReader.readLine()) != null) {
                        sb.append(response).append("\n");
                    }

                    response = sb.toString();

                    is.close();
                    bufferedReader.close();
                    httpURLConnection.disconnect();

                    return response;

                } catch (ProtocolException e) {
                    e.printStackTrace();
                    return "protocol error";
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    return "url error";
                } catch (JSONException e) {
                    e.printStackTrace();
                    return "Json not found";
                }  catch (IOException e) {
                    e.printStackTrace();
                    return "Data not found";
                }
            }
        }
        return "Task Gagal";
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }
}

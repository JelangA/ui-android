package com.example.aplikasitokoonline.BackGroundTasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import com.example.aplikasitokoonline.R;
import com.example.aplikasitokoonline.models.Product;
import com.example.aplikasitokoonline.utils.ListViewAdapters;
import com.example.aplikasitokoonline.utils.Token;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BackgroundTaskProduct extends AsyncTask<String, String, List<Product>> {

    private Context context;
    private ListView listView;
    private ProgressDialog progressDialog;

    public BackgroundTaskProduct(Context context, ListView listView) {
        this.context = context;
        this.listView = listView;
    }

    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected List<Product> doInBackground(String... strings) {
        String _url = strings[0];

        try{

            URL url = new URL(_url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + Token.getToken());

            int statuscode = connection.getResponseCode();
            if (statuscode == HttpURLConnection.HTTP_OK){

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line = "";

                while ((line = reader.readLine()) != null){
                    response.append(line);
                }

                reader.close();

                JSONObject json = new JSONObject(response.toString());
                JSONArray parentArray = json.getJSONArray("data");

                List<Product> productList = new ArrayList<>();

                for (int i=0; i< parentArray.length(); i++){

                    JSONObject obj = parentArray.getJSONObject(i);
                    Product product = new Product();

                    product.setId(obj.getInt("id"));
                    product.setName(obj.getString("name"));
                    product.setPrice(obj.getInt("price"));
                    product.setImage(obj.getString("image"));
                    product.setRating(obj.getDouble("rating"));

                    productList.add(product);

                }
                return productList;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<Product> result) {
        super.onPostExecute(result);

        if(result != null && !result.isEmpty()) {
            progressDialog.dismiss();
            ListViewAdapters adapters = new ListViewAdapters(context.getApplicationContext(), R.layout.activity_list_card, result);
            listView.setAdapter(adapters);
        } else {
            // handle empty result
            Toast.makeText(context, "gagal mendapatkan data", Toast.LENGTH_SHORT).show();
        }
    }
}

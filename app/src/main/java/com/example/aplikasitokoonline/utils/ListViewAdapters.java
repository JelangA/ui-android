package com.example.aplikasitokoonline.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.aplikasitokoonline.R;
import com.example.aplikasitokoonline.models.Product;

import java.util.List;

public class ListViewAdapters extends ArrayAdapter<Product> implements AdapterView.OnItemClickListener {

    private List<Product> productList;
    private int resource;
    private LayoutInflater inflater;
    private Context context;
    private OnItemClickListener mListener;

    public ListViewAdapters(@NonNull Context context, int resource, @NonNull List<Product> objects) {
        super(context, resource, objects);
        productList = objects;
        this.resource = resource;
        inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null){
            convertView = inflater.inflate(resource, null);
        }

        ImageView imgProduct;
        TextView txtProductName;
        TextView txtprice;

        imgProduct = convertView.findViewById(R.id.image3);
        txtProductName = convertView.findViewById(R.id.txtnama3);
        txtprice = convertView.findViewById(R.id.txtharga3);

        Glide.with(context)
                .load(productList.get(position).getImage())
                .into(imgProduct);
        txtProductName.setText(productList.get(position).getName());
        txtprice.setText(String.valueOf(productList.get(position).getPrice()));

        return convertView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mListener != null) {
            mListener.onItemClick(productList.get(position));
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Product item);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }
}

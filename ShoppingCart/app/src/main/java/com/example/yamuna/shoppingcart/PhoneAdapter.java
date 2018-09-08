package com.example.yamuna.shoppingcart;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class PhoneAdapter extends RecyclerView.Adapter<PhoneAdapter.PhoneViewHolder>{
    private Context context;
    private List<Phone> phoneList;

    public PhoneAdapter(Context context, List<Phone> phoneList){
        this.context = context;
        this.phoneList = phoneList;
    }

    @Override
    public PhoneAdapter.PhoneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.content_main, parent, false);

        return new PhoneViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneViewHolder holder, int position) {
        Phone phone = phoneList.get(position);

        Glide.with(context).load(Uri.parse(phone.getImage())).into(holder.image);
        holder.model.setText(phone.getModel());
        holder.manufacturer.setText(phone.getManufacturer());
        String cost = "Rs: "+phone.getPrice().toString();
        holder.price.setText(cost);


    }

    @Override
    public int getItemCount() {
        return phoneList.size();
    }

    public class PhoneViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView model;
        TextView manufacturer;
        TextView price;
        public PhoneViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
            model = (TextView) itemView.findViewById(R.id.model);
            manufacturer = (TextView) itemView.findViewById(R.id.manufacturer);
            price= (TextView)itemView.findViewById(R.id.price);

        }
    }
}

package com.example.yamuna.shoppingcart;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.AlertDialogLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PhoneAdapter extends RecyclerView.Adapter<PhoneAdapter.PhoneViewHolder>{
    private Context context;
    private List<Phone> phoneList;

    public class PhoneViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView model;
        TextView manufacturer;
        TextView price;
        LinearLayout layout;
        public PhoneViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
            model = (TextView) itemView.findViewById(R.id.model);
            manufacturer = (TextView) itemView.findViewById(R.id.manufacturer);
            price= (TextView)itemView.findViewById(R.id.price);
            layout = (LinearLayout)itemView.findViewById(R.id.card_layout);

        }
    }

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
    public void onBindViewHolder(@NonNull final PhoneViewHolder holder, final int position) {
        final Phone phone = phoneList.get(position);

        final String phoneModel = phone.getModel();
        holder.model.setText(phoneModel);
        holder.manufacturer.setText(phone.getManufacturer());
        String cost = "Rs: " + phone.getPrice().toString();
        holder.price.setText(cost);
        Glide.with(context).load(Uri.parse(phone.getImage())).into(holder.image);

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder=new AlertDialog.Builder(context);

                View view= LayoutInflater.from(context).inflate(R.layout.dialog_main, null);
                builder.setView(view);
                builder.setTitle("Buy Phones:");
                builder.setMessage("Enter the details");

                final EditText username=view.findViewById(R.id.username);
                final EditText quantity=view.findViewById(R.id.quantity);

                builder.setPositiveButton("BUY", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String user_name = username.getText().toString();
                        String qty = quantity.getText().toString();
                        if(!user_name.equals("") &&!qty.equals("")) {
                            Api api = ApiClient.getRetrofit().create(Api.class);
                            Call<Buy> call = api.getBuy(phoneModel, user_name, qty);
                            call.enqueue(new Callback<Buy>() {
                                @Override
                                public void onResponse(Call<Buy> call, Response<Buy> response) {
                                    Toast.makeText(context, "Successfully Completed", Toast.LENGTH_LONG).show();
                                }

                                @Override
                                public void onFailure(Call<Buy> call, Throwable t) {
                                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            });
                        }else{
                            Toast.makeText(context, "No Details Entered", Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        }

                    }
                });
                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "Canceled", Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                });
                AlertDialog alert=builder.create();
                alert.show();
            }
        });

    }


    @Override
    public int getItemCount() {
        return phoneList.size();
    }

}

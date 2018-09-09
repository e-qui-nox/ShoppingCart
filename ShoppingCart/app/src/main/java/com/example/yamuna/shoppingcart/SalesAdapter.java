package com.example.yamuna.shoppingcart;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class SalesAdapter extends RecyclerView.Adapter<SalesAdapter.SalesViewHolder>{
    private Context context;
    private List<Buy> details;

    public SalesAdapter(Context context,List<Buy> details){
        this.context = context;
        this.details = details;
    }

    @NonNull
    @Override
    public SalesAdapter.SalesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.sales_main, parent, false);
        return new SalesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SalesViewHolder holder,int position) {
        holder.dinvoice_number.setText("Invoice Number: " + details.get(position).getInvoiceNumber());
        holder.dmodel.setText("Model: " + details.get(position).getModel());
        holder.dquantity.setText("Quantity: " + details.get(position).getQuantity());
        holder.dusername.setText("Username: " + details.get(position).getUsername());
    }


    public class SalesViewHolder extends RecyclerView.ViewHolder{

        TextView dusername;
        TextView dquantity;
        TextView dmodel;
        TextView dinvoice_number;
        public SalesViewHolder(View itemView) {
            super(itemView);
            dinvoice_number = (TextView)itemView.findViewById(R.id.d_invoice_number);
            dmodel = (TextView)itemView.findViewById(R.id.d_model);
            dquantity = (TextView)itemView.findViewById(R.id.d_quantity);
            dusername = (TextView)itemView.findViewById(R.id.d_username);
        }
    }

    @Override
    public int getItemCount() {
        return details.size();
    }
}

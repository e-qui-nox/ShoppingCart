package com.example.yamuna.shoppingcart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SalesActivity extends AppCompatActivity{
    RecyclerView recyclerView;
    List<Buy> details = new ArrayList<>();
    RecyclerView.Adapter<SalesAdapter.SalesViewHolder> adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sales_main);

        recyclerView = (RecyclerView) findViewById(R.id.sales_recycler_View);
        RecyclerView.LayoutManager  layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        Api api = ApiClient.getRetrofit().create(Api.class);

        Call<List<Buy>> call = api.getSales();
        call.enqueue(new Callback<List<Buy>>() {
            @Override
            public void onResponse(Call<List<Buy>> call, Response<List<Buy>> response) {
                details=response.body();
                renderPhones();
            }

            @Override
            public void onFailure(Call<List<Buy>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    private void renderPhones() {
        adapter=new SalesAdapter(this,details);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }
}

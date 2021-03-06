package com.example.yamuna.shoppingcart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

     RecyclerView recyclerView;
     List<Phone> phoneList = new ArrayList<>();
     RecyclerView.Adapter<PhoneAdapter.PhoneViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundle = getIntent().getExtras();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_View);
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        final ProgressBar simpleProgressBar = (ProgressBar) findViewById(R.id.pbLoading);

        Api api = ApiClient.getRetrofit().create(Api.class);

        Call<List<Phone>> call = api.getPhones();

        if(bundle!=null){
            String manufacturer = bundle.getString("Manufacturer",null);
            String model = bundle.getString("Model",null);
            String minprice = bundle.getString("MinimumPrice",null);
            String maxprice = bundle.getString("MaximumPrice",null);


            if(manufacturer==null && model==null && maxprice==null && minprice==null) {
                call = api.getPhones();
            }else {
                call = api.getPhones(manufacturer, model, minprice, maxprice);
            }
        }

        call.enqueue(new Callback<List<Phone>>() {
            @Override
            public void onResponse(Call<List<Phone>> call, Response<List<Phone>> response) {
                Log.i("url",call.request().url().toString());
                simpleProgressBar.setVisibility(View.GONE);
                phoneList=response.body();
                renderPhones();
            }

            @Override
            public void onFailure(Call<List<Phone>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void renderPhones(){
        adapter=new PhoneAdapter(this,phoneList);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.searchbar) {
            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);
        } else if (id == R.id.details) {
            Intent intent = new Intent(this, SalesActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}

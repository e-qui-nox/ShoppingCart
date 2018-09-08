package com.example.yamuna.shoppingcart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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

        Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_View);
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        Api api = ApiClient.getRetrofit().create(Api.class);

        Call<List<Phone>> call = api.getPhones();

        if(bundle!=null){
            String manufacturer = bundle.getString("Manufacturer");
            String model = bundle.getString("Model");
            String max_price = bundle.getString("Minimum price");
            String min_price = bundle.getString("Maximum price");


            if(manufacturer==null && model==null && max_price==null && min_price==null)
                call = api.getPhones();
            else
                call = api.getPhones(manufacturer,model,max_price,min_price);
        }

        call.enqueue(new Callback<List<Phone>>() {
            @Override
            public void onResponse(Call<List<Phone>> call, Response<List<Phone>> response) {
                List<Phone> list=response.body();
                Log.i("url",call.request().url().toString());
                for(int i=0;i<list.size();i++){
                    String model=(list.get(i).getModel());
                    String manufacturer = (list.get(i).getManufacturer());
                    int price=(list.get(i).getPrice());
                    String image=(list.get(i).getImage());
                    phoneList.add( new Phone(model,manufacturer,price,image));
                }
                recyclerView.setAdapter(new PhoneAdapter(getApplicationContext(),phoneList));
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.search_bar) {
            Intent intent = new Intent(MainActivity.this, SearchActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}

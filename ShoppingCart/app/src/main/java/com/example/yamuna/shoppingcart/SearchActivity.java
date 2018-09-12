package com.example.yamuna.shoppingcart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SearchActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_main);

        final EditText manufacturer = (EditText)findViewById(R.id.et_manufacturer);
        final EditText model = (EditText)findViewById(R.id.et_model);
        final EditText min_price = (EditText)findViewById(R.id.et_min_price);
        final EditText max_price = (EditText)findViewById(R.id.et_max_price);

        final Button search = (Button) findViewById(R.id.bt_search);

        search.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SearchActivity.this,MainActivity.class);
                Bundle bundle = new Bundle();
                Log.i(manufacturer.getText().toString(),"Model");
                Log.i(model.getText().toString(),"Manufacturer");
                Log.i(min_price.getText().toString(),"Min Price");
                Log.i(max_price.getText().toString(),"Max Price");

                String text1 = manufacturer.getText().toString();
                bundle.putString("Manufacturer",text1);

                String text2 = model.getText().toString();
                bundle.putString("Model",text2);

                String text3 = min_price.getText().toString();
                bundle.putString("MinimumPrice",text3);

                String text4 = max_price.getText().toString();
                bundle.putString("MaximumPrice",text4);

                intent.putExtras(bundle);
                startActivity(intent);
            }

        });
    }
}

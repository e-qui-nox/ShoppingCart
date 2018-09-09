package com.example.yamuna.shoppingcart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SearchActivity extends AppCompatActivity{

    EditText manufacturer,model,max_price,min_price;
    Button search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_main);

        manufacturer = (EditText)findViewById(R.id.et_manufacturer);
        model = (EditText)findViewById(R.id.et_model);
        min_price = (EditText)findViewById(R.id.et_min_price);
        max_price = (EditText)findViewById(R.id.et_max_price);

        search = (Button) findViewById(R.id.bt_search);

        search.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();

                String text = manufacturer.getText().toString();
                if(text=="")    text = null;
                bundle.putString("Manufacturer",text);

                text = model.getText().toString();
                if(text=="")    text = null;
                bundle.putString("Model",text);

                text = min_price.getText().toString();
                if(text=="")    text = null;
                bundle.putString("Minimum Price",text);

                text = max_price.getText().toString();
                if(text=="")    text = null;
                bundle.putString("Maximum Price",text);

                Intent intent = new Intent(SearchActivity.this, MainActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }

        });
    }
}

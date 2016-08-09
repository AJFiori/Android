package com.example.anthonyjfiori.lab3;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Results extends AppCompatActivity {

    //static ArrayList<Order> CandyOrders = new ArrayList<Order>();
    static ArrayList<String> CandyOrders = new ArrayList<String>();
    ListView lsCandyOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

//Initializes ListView
        lsCandyOrder = (ListView)findViewById(R.id.lvCandyOrder);
//pulls from first window
        Bundle extras = getIntent().getExtras();
        //Order candy = getIntent().getParcelableExtra("results");
        //CandyOrders.add(candy);
        String inputString = extras.getString("mykey");
        CandyOrders.add(inputString);

        // ArrayAdapter<Order> adapter = new ArrayAdapter<Order>(this,android.R.layout.simple_list_item_1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        for(int i = 0; i < CandyOrders.size(); i++){
            //adapter.add(candy);

            adapter.add(CandyOrders.get(i));
        }
        lsCandyOrder.setAdapter(adapter);
    }
    //Passes BackToMain
    public void BackToMain(View view){
        finish();
    }

    public void finish() {
        Intent i = new Intent();
        i.putExtra("results",CandyOrders.size());
        setResult(RESULT_OK,i);
        super.finish();
    }
}



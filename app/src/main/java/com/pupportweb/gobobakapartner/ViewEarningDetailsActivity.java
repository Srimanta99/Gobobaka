package com.pupportweb.gobobakapartner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.pupportweb.gobobakapartner.Adapter.EarningDetailsAdapter;
import com.pupportweb.gobobakapartner.retrofitModel.StoreEarningsItem;

import java.util.ArrayList;

public class ViewEarningDetailsActivity extends AppCompatActivity {

    ArrayList<StoreEarningsItem> list = new ArrayList<>();
    EarningDetailsAdapter adapter ;
    LinearLayoutManager layoutManager;
    RecyclerView rvList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_earning_details2);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.earning_detail));

        rvList = findViewById(R.id.main_list);

        if(getIntent()!=null){
            list = (ArrayList<StoreEarningsItem>)getIntent().getSerializableExtra("list");
        }

        adapter = new EarningDetailsAdapter(ViewEarningDetailsActivity.this,list);
        layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);


        rvList.setLayoutManager(layoutManager);
        rvList.setAdapter(adapter);


    }
}
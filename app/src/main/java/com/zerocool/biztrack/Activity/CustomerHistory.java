package com.zerocool.biztrack.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import com.zerocool.biztrack.Adapters.CustomerHistoryAdapter;
import com.zerocool.biztrack.DatabaseOperation.Retrieve;
import com.zerocool.biztrack.Models.ModelRetrieve;
import com.zerocool.biztrack.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CrashOverride on 1/5/2018.
 */

public class CustomerHistory extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    TableLayout HistoryLayout;
    TextView Recievable, CashPaid, RemainingDue, Date;
    String PhoneNumber;
    String UID;
    //String UID2;
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        UID =  intent.getStringExtra("UID");

        setContentView(R.layout.customer_history_layout);
        recyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setMinimumWidth(300);

        List<ModelRetrieve> input = new ArrayList<>();
        input.clear();
        input = Retrieve.getCustomerHistory(UID);
        CustomerHistoryAdapter adapter = new CustomerHistoryAdapter(this, input);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

    }


    private void Initialize(View view) {
//        listViewData = (ListView) view.findViewById(R.id.listViewNames);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.RecyclerView);

    }


}

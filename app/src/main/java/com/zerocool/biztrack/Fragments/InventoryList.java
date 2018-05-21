package com.zerocool.biztrack.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zerocool.biztrack.DatabaseOperation.Retrieve;
import com.zerocool.biztrack.Models.ModelRetrieve;
import com.zerocool.biztrack.R;
import com.zerocool.biztrack.Adapters.InventoryAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CrashOverride on 1/5/2018.
 */

public class InventoryList extends Fragment {


    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
//    private ProductsAdapter mAdapter = null;


 //   private ListView listViewData;

    ModelRetrieve modelRetrieve = new ModelRetrieve();
    public InventoryList(){}




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.inventory_list_layout, container, false);
        return rootView;
    }


    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.RecyclerView);
        // use this setting to
        // improve performance if you know that changes
        // in content do not change the layout size
        // of the RecyclerView
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        List<ModelRetrieve> input = new ArrayList<>();
        input = Retrieve.getInventoryList();
        InventoryAdapter adapter = new InventoryAdapter(getActivity(),input);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);





    }







    private void Initialize(View view) {
//        listViewData = (ListView) view.findViewById(R.id.listViewNames);
        RecyclerView recyclerView =(RecyclerView) view.findViewById(R.id.RecyclerView);

    }


}

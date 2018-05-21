package com.zerocool.biztrack.Fragments;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.zerocool.biztrack.Adapters.AllProductAdapter;
import com.zerocool.biztrack.Adapters.InventoryAdapter;
import com.zerocool.biztrack.Adapters.ToPurchaseAdapter;
import com.zerocool.biztrack.DataModel.ProductTable;
import com.zerocool.biztrack.DatabaseOperation.Retrieve;
import com.zerocool.biztrack.DatabaseOperation.Save;
import com.zerocool.biztrack.Models.ModelRetrieve;
import com.zerocool.biztrack.Models.ModelSave;
import com.zerocool.biztrack.R;
import com.zerocool.biztrack.Utils.UtillMets;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CrashOverride on 1/5/2018.
 */

public class AllProductList extends Fragment {


    private RecyclerView ProductView;
    Toolbar ProductToolbar;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    ModelSave modelSave = new ModelSave();
    Save save = new Save();


    ModelRetrieve modelRetrieve = new ModelRetrieve();

    public AllProductList() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.all_product_list_layout, container, false);
        setHasOptionsMenu(true);
        return rootView;
    }


    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Initialize(view);

//        ProductView = (RecyclerView) view.findViewById(R.id.RecyclerView);
//        FloatingActionButton AddNewProduct = (FloatingActionButton) view.findViewById(R.id.AddNewProduct);
//        AddNewProduct.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showDialog();
//            }
//        });
        ProductView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        ProductView.setLayoutManager(layoutManager);

        List<ModelRetrieve> input = new ArrayList<>();
        input.clear();
//        input = Retrieve.getAllProductList();
        input = Retrieve.getInventoryList();
        //refreshList();
        adapter = new AllProductAdapter(getActivity(), input);
        adapter.notifyDataSetChanged();
        ProductView.setAdapter(adapter);


    }



    @Override
    public void onCreateOptionsMenu(Menu menu,MenuInflater inflater) {

        inflater.inflate(R.menu.add_new_product_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
        // Inflate the menu; this adds items to the action bar if it is present.
//        getActivity().getMenuInflater().inflate(R.menu.all_products_options, menu);
//        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id == R.id.action_add_new){

            showDialog(getContext());
        }

        if(id == R.id.action_refresh_products){

            ProgressDialog progressDialog = new ProgressDialog(getActivity());
            progressDialog.show();
            progressDialog.setMessage("Refreshing...");
            adapter.notifyDataSetChanged();
            progressDialog.dismiss();

        }


        if(id == R.id.action_all_products){
            ProductView.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(getContext());
            ProductView.setLayoutManager(layoutManager);

            List<ModelRetrieve> input = new ArrayList<>();
            input.clear();
            input = Retrieve.getAllProductList();
            //refreshList();
            adapter = new AllProductAdapter(getActivity(),input);
            adapter.notifyDataSetChanged();
            ProductView.setAdapter(adapter);

        }



        if(id == R.id.action_carted){
            ProductView.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(getContext());
            ProductView.setLayoutManager(layoutManager);

            List<ModelRetrieve> input = new ArrayList<>();
            input.clear();
            input = Retrieve.getToPurchaseList();
            //refreshList();
            adapter = new ToPurchaseAdapter(getActivity(),input);
            adapter.notifyDataSetChanged();
            ProductView.setAdapter(adapter);

        }

        if(id == R.id.action_instock){
            ProductView.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(getContext());
            ProductView.setLayoutManager(layoutManager);

            List<ModelRetrieve> input = new ArrayList<>();
            input.clear();
            input = Retrieve.getInventoryList();
            //refreshList();
//            adapter = new InventoryAdapter(getActivity(),input);
            adapter = new AllProductAdapter(getActivity(),input);
            adapter.notifyDataSetChanged();
            ProductView.setAdapter(adapter);

        }


        return super.onOptionsItemSelected(item);
    }







    public void showDialog(Context context) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.entry_page_layout);
        dialog.setTitle("Add New Products");

        final EditText ProductName, UnitPrice,CompanyName;
        Button AddMoreButton, ButtonCancel;

        ProductName = (EditText) dialog.findViewById(R.id.ProductName);
        CompanyName = (EditText) dialog.findViewById(R.id.CompanyName);
        UnitPrice = (EditText) dialog.findViewById(R.id.UnitPrice);
        AddMoreButton = (Button) dialog.findViewById(R.id.AddMoreButton);
        ButtonCancel = (Button) dialog.findViewById(R.id.ButtonCancel);


        dialog.show();

        AddMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                modelSave.setProductName(ProductName.getText().toString());
                modelSave.setUnitPrice(UtillMets.toInteger(UnitPrice));
                modelSave.setCompany(CompanyName.getText().toString());
                modelSave.setIfCarted(ProductTable.DisCarted);
                modelSave.setQuantity(0);

                save.saveProducts(modelSave);
                //modelSave.setProductID(save.getID());

                UnitPrice.setText("");
                CompanyName.setText("");
                ProductName.setText("");

//                adapter.notifyDataSetChanged();


            }
        });


        ButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


    }


    public void refreshList() {
        adapter.notifyDataSetChanged();
    }


    private void Initialize(View view) {
//        listViewData = (ListView) view.findViewById(R.id.listViewNames);
        ProductView = (RecyclerView) view.findViewById(R.id.RecyclerView);
        //ProductToolbar = view.findViewById(R.id.ProductToolbar);

    }


}

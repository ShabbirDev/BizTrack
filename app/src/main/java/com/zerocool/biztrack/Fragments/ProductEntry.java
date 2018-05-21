package com.zerocool.biztrack.Fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.zerocool.biztrack.DataModel.ProductTable;
import com.zerocool.biztrack.DatabaseOperation.Save;
import com.zerocool.biztrack.Models.ModelSave;
import com.zerocool.biztrack.R;
import com.zerocool.biztrack.Utils.UtillMets;
//import com.zerocool.biztrack.ListView.SurveyDataList;


/**
 * Created by CrashOverride on 1/5/2018.
 */

public class ProductEntry extends Fragment {

    private AlertDialog dialog;
    private ProgressBar Progress;

    EditText UnitPrice, ProductName;
    Button AddMoreButton, CancelButton;
    ModelSave modelSave = new ModelSave();
    Save save = new Save();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.entry_page_layout, container, false);
        return rootView;
    }

    public void onViewCreated(final View rootView, Bundle savedInstanceState) {
        super.onViewCreated(rootView, savedInstanceState);
        Initialize(rootView);

        AddMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try
                {
                    modelSave.setProductName(ProductName.getText().toString());
                    modelSave.setProductName(ProductName.getText().toString());
                    modelSave.setUnitPrice(UtillMets.toInteger(UnitPrice));
                    modelSave.setIfCarted(ProductTable.DisCarted);
                    modelSave.setQuantity(0);

                    save.saveProducts(modelSave);
                    //modelSave.setProductID(save.getID());

                    ProductName.setText("");
                    UnitPrice.setText("");


                    System.out.println("============================FROM DB================"+ save.getID());
                    System.out.println("================"+modelSave.getQuantity()+"============"+modelSave.getIfCarted()+" MODEL================"+modelSave.getProductID());

//                    save.insertDataInInventoryList(modelSave);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });




    }


    private void SaveData(View rootView) {
        Initialize(rootView);

        try {

            modelSave.setProductName(ProductName.getText().toString());
            modelSave.setUnitPrice(Integer.parseInt(UnitPrice.getText().toString()));
            save.saveProducts(modelSave);



//            Intent intent = new Intent(getContext(), SurveyDataList.class);
//            startActivity(intent);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    void Initialize(View rootView) {

        ProductName = (EditText) rootView.findViewById(R.id.ProductName);
        UnitPrice = (EditText) rootView.findViewById(R.id.UnitPrice);
        ProductName = (EditText) rootView.findViewById(R.id.ProductName);
        AddMoreButton = (Button) rootView.findViewById(R.id.AddMoreButton);
    }


}

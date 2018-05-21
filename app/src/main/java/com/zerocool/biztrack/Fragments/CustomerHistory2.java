package com.zerocool.biztrack.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.zerocool.biztrack.DatabaseOperation.Delete;
import com.zerocool.biztrack.DatabaseOperation.Retrieve;
import com.zerocool.biztrack.Models.ModelRetrieve;
import com.zerocool.biztrack.R;
import com.zerocool.biztrack.Utils.UtillMets;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.zerocool.biztrack.Utils.UtillMets.createLine;
import static com.zerocool.biztrack.Utils.UtillMets.createTableRow;
import static com.zerocool.biztrack.Utils.UtillMets.createTextView;
import static com.zerocool.biztrack.Utils.UtillMets.createVerticLine;

/**
 * Created by CrashOverride on 1/5/2018.
 */

public class CustomerHistory2 extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    com.zerocool.biztrack.DatabaseOperation.Delete delete = new Delete();
    TableLayout HistoryLayout;
    TextView Shopped, Recievable, CashPaid,CashBack, RemainingDue, Datee;
    View Line, VertLine;
    CheckBox ActionCheck;
    Button Delete;
    String UID;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        UID = getArguments().getString("UID");

        View rootView = inflater.inflate(R.layout.history2, container, false);
        return rootView;
    }


    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        HistoryLayout = (TableLayout) view.findViewById(R.id.HistoryLayout);
        TableRow tableRow;



        List<ModelRetrieve> input = new ArrayList<>();
        input = Retrieve.getCustomerHistory(UID);

        for (int i = 0; i < input.size(); i++) {

            ModelRetrieve modelRetrieve = input.get(i);
            tableRow = createTableRow(getContext());
            tableRow.setClickable(true);
            tableRow.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    //Delete.setVisibility(View.VISIBLE);
                    return false;
                }
            });
            Shopped = createTextView(getContext());
            Recievable = createTextView(getContext());
            CashPaid = createTextView(getContext());
            CashBack = createTextView(getContext());
            RemainingDue = createTextView(getContext());
            Datee = createTextView(getContext());
            Line = createLine(getActivity());
            VertLine = createVerticLine(getContext());



            Shopped.setText(""+modelRetrieve.getTotalShopped()+" BDT");
            Recievable.setText(""+modelRetrieve.getTotalRecievable()+" BDT");
            CashPaid.setText(""+modelRetrieve.getCashPaid()+" BDT");
            if(modelRetrieve.getCashBack()!=0){
                CashBack.setText(""+modelRetrieve.getCashBack()+" BDT");
            }else{
                CashBack.setText(""+0+" BDT");
            }
            RemainingDue.setText(""+modelRetrieve.getCustomerDue()+" BDT");




            String dateStr = modelRetrieve.getDate();
            SimpleDateFormat readFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm");
            SimpleDateFormat writeFormat = new SimpleDateFormat( "MMM dd, yyyy hh:mm aa");
            Date date = null;
            try {
                date = readFormat.parse(dateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (date != null) {
                String formattedDate = writeFormat.format(date);
                Datee.setText(formattedDate);
            }














            VertLine = createVerticLine(getContext());
            tableRow.addView(VertLine);
            tableRow.addView(Shopped);
            VertLine = createVerticLine(getContext());
            tableRow.addView(VertLine);
            tableRow.addView(Recievable);
            VertLine = createVerticLine(getContext());
            tableRow.addView(VertLine);
            tableRow.addView(CashPaid);
            VertLine = createVerticLine(getContext());
            tableRow.addView(VertLine);
            tableRow.addView(CashBack);
            VertLine = createVerticLine(getContext());
            tableRow.addView(VertLine);
            tableRow.addView(RemainingDue);
            VertLine = createVerticLine(getContext());
            tableRow.addView(VertLine);
            tableRow.addView(Datee);
            VertLine = createVerticLine(getContext());
            tableRow.addView(VertLine);
//            tableRow.addView(Line);

            HistoryLayout.addView(tableRow);
            HistoryLayout.addView(Line);

        }


    }


    private void Initialize(View view) {

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.RecyclerView);
    }


}

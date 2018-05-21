package com.zerocool.biztrack.Fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.zerocool.biztrack.Activity.CustomerRegistration;
import com.zerocool.biztrack.DatabaseOperation.Retrieve;
import com.zerocool.biztrack.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.zerocool.biztrack.Utils.CalUtils.endOfToDay;
import static com.zerocool.biztrack.Utils.CalUtils.getCustomDate;
import static com.zerocool.biztrack.Utils.CalUtils.getEndOfLastMonth;
import static com.zerocool.biztrack.Utils.CalUtils.getEndOfLastWeek;
import static com.zerocool.biztrack.Utils.CalUtils.getEndOfThisMonth;
import static com.zerocool.biztrack.Utils.CalUtils.getEndOfThisWeek;
import static com.zerocool.biztrack.Utils.CalUtils.getEndOfYesterday;
import static com.zerocool.biztrack.Utils.CalUtils.getStartOfLastMonth;
import static com.zerocool.biztrack.Utils.CalUtils.getStartOfLastWeek;
import static com.zerocool.biztrack.Utils.CalUtils.getStartOfThisMonth;
import static com.zerocool.biztrack.Utils.CalUtils.getStartOfThisWeek;
import static com.zerocool.biztrack.Utils.CalUtils.getStartOfYesterday;
import static com.zerocool.biztrack.Utils.CalUtils.startOfToday;
import static com.zerocool.biztrack.Utils.UtillMets.getDate;

/**
 * Created by CrashOverride on 1/23/2018.
 */

public class Home extends Fragment {

//    TextView TodayDate, SummaryForDate, TotalSale, TotalCash, TotalReciavable;
    TextView TotalSale, TotalCash, TotalReciavable;
    CardView AddNewCustomer,AddProduct;

    Retrieve retrieve = new Retrieve();
    DatePickerDialog startDate;
    DatePickerDialog endDate;
    String Currency = " BDT";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.home2, container, false);
        setHasOptionsMenu(true);
        return rootView;
    }

    public void onViewCreated(final View rootView, Bundle savedInstanceState) {
        super.onViewCreated(rootView, savedInstanceState);
        Initialize(rootView);

//        TodayDate.setText(getString(R.string.today_is) +"    "+ getDate());
//        TodayDate.setText(getString(R.string.today_is) + getDate());

        //SummaryForDate
        TotalSale.setText("" + retrieve.totalSales(startOfToday(getDate()), endOfToDay(getDate()))+Currency);
        TotalCash.setText("" + retrieve.totalCash(startOfToday(getDate()), endOfToDay(getDate()))+Currency);
        TotalReciavable.setText("" + retrieve.totalRecievables(startOfToday(getDate()), endOfToDay(getDate()))+Currency);

        AddNewCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CustomerRegistration.class);
                startActivity(intent);
            }
        });

        AddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AllProductList allProductList = new AllProductList();
                allProductList.showDialog(getContext());

            }
        });




    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.home_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_summary_for_today) {
            //SummaryForDate.setText("Showing Summary For Today");
            TotalSale.setText("" + retrieve.totalSales(startOfToday(getDate()), endOfToDay(getDate()))+Currency);
            TotalCash.setText("" + retrieve.totalCash(startOfToday(getDate()), endOfToDay(getDate()))+Currency);
            TotalReciavable.setText("" + retrieve.totalRecievables(startOfToday(getDate()), endOfToDay(getDate()))+Currency);
        }

        if (id == R.id.action_summary_for_yesterday) {
            //SummaryForDate.setText("Showing Summary For Yesterday");
            TotalSale.setText("" + retrieve.totalSales(getStartOfYesterday(getDate()), getEndOfYesterday(getDate()))+Currency);
            TotalCash.setText("" + retrieve.totalCash(getStartOfYesterday(getDate()), getEndOfYesterday(getDate()))+Currency);
            TotalReciavable.setText("" + retrieve.totalRecievables(getStartOfYesterday(getDate()), getEndOfYesterday(getDate()))+Currency);
        }

        if (id == R.id.action_summary_for_thisweek) {
            //SummaryForDate.setText("Showing Summary For This Week");
            TotalSale.setText("" + retrieve.totalSales(getStartOfThisWeek(getDate()), getEndOfThisWeek(getDate()))+Currency);
            TotalCash.setText("" + retrieve.totalCash(getStartOfThisWeek(getDate()), getEndOfThisWeek(getDate()))+Currency);
            TotalReciavable.setText("" + retrieve.totalRecievables(getStartOfThisWeek(getDate()), getEndOfThisWeek(getDate()))+Currency);
        }


        if (id == R.id.action_summary_for_lastweek) {
            //SummaryForDate.setText("Showing Summary For Last Week");
            TotalSale.setText("" + retrieve.totalSales(getStartOfLastWeek(getDate()), getEndOfLastWeek(getDate()))+Currency);
            TotalCash.setText("" + retrieve.totalCash(getStartOfLastWeek(getDate()), getEndOfLastWeek(getDate()))+Currency);
            TotalReciavable.setText("" + retrieve.totalRecievables(getStartOfLastWeek(getDate()), getEndOfLastWeek(getDate()))+Currency);
        }

        if (id == R.id.action_summary_for_thismonth) {
            //SummaryForDate.setText("Showing Summary For This Month");
            TotalSale.setText("" + retrieve.totalSales(getStartOfThisMonth(getDate()), getEndOfThisMonth(getDate()))+Currency);
            TotalCash.setText("" + retrieve.totalCash(getStartOfThisMonth(getDate()), getEndOfThisMonth(getDate()))+Currency);
            TotalReciavable.setText("" + retrieve.totalRecievables(getStartOfThisMonth(getDate()), getEndOfThisMonth(getDate()))+Currency);

        }


        if (id == R.id.action_summary_for_lastmonth) {
            //SummaryForDate.setText("Showing Summary For Last Month");
            TotalSale.setText("" + retrieve.totalSales(getStartOfLastMonth(getDate()), getEndOfLastMonth(getDate()))+Currency);
            TotalCash.setText("" + retrieve.totalCash(getStartOfLastMonth(getDate()), getEndOfLastMonth(getDate()))+Currency);
            TotalReciavable.setText("" + retrieve.totalRecievables(getStartOfLastMonth(getDate()), getEndOfLastMonth(getDate()))+Currency);
        }

        if (id == R.id.action_summary_for_custom) {
            datePickerDialog();
//            SummaryForDate.setText("Showing Summary For Last Month");
//            TotalSale.setText("" + retrieve.totalSales(getStartOfLastMonth(getDate()), getEndOfLastMonth(getDate())));
//            TotalCash.setText("" + retrieve.totalCash(getStartOfLastMonth(getDate()), getEndOfLastMonth(getDate())));
//            TotalReciavable.setText("" + retrieve.totalRecievables(getStartOfLastMonth(getDate()), getEndOfLastMonth(getDate())));
        }



        return super.onOptionsItemSelected(item);
    }


    private void Initialize(View view) {
//        TodayDate = view.findViewById(R.id.TodayDate);
//        SummaryForDate = view.findViewById(R.id.SummaryForDate);
        TotalSale = (TextView) view.findViewById(R.id.TotalSale);
        TotalCash = (TextView) view.findViewById(R.id.TotalCash);
        TotalReciavable = (TextView) view.findViewById(R.id.TotalReciavable);
        AddNewCustomer = (CardView) view.findViewById(R.id.AddNewCustomer);
        AddProduct = (CardView) view.findViewById(R.id.AddProduct);
    }


    private void datePickerDialog() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.pick_date_layout);
        dialog.setTitle("Pick Dates");
        Calendar calendar =  Calendar.getInstance();

        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        final EditText FromDate, ToDate;
        final String Start,End;
        Button Ok;

        FromDate = (EditText) dialog.findViewById(R.id.FromDate);
        ToDate = (EditText) dialog.findViewById(R.id.ToDate);
        Ok = (Button) dialog.findViewById(R.id.Ok);
        dialog.show();

        FromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startDate.show();
            }
        });
        ToDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                endDate.show();
            }
        });

        startDate = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                FromDate.setText(dateFormat.format(newDate.getTime()));

            }

        },calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        endDate = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                ToDate.setText(dateFormat.format(newDate.getTime()));
            }

        },calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));


        Start = FromDate.getText().toString();
        End = ToDate.getText().toString();

        Ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //SummaryForDate.setText("Summary: \n From "+FromDate.getText().toString()+ "\n To "+ToDate.getText().toString());
                TotalSale.setText("" + retrieve.totalSales(getCustomDate(FromDate.getText().toString()), getCustomDate(ToDate.getText().toString()))+Currency);
                TotalCash.setText("" + retrieve.totalCash(getCustomDate(FromDate.getText().toString()), getCustomDate(ToDate.getText().toString()))+Currency);
                TotalReciavable.setText("" + retrieve.totalRecievables(getCustomDate(FromDate.getText().toString()), getCustomDate(ToDate.getText().toString()))+Currency);
                dialog.dismiss();

            }
        });



    }




}

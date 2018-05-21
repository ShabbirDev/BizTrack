package com.zerocool.biztrack.Fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
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
import com.zerocool.biztrack.Adapters.CustomerListAdapter;
import com.zerocool.biztrack.DatabaseOperation.Retrieve;
import com.zerocool.biztrack.Utils.CalUtils;
import com.zerocool.biztrack.DatabaseOperation.Save;
import com.zerocool.biztrack.Models.ModelRetrieve;
import com.zerocool.biztrack.Models.ModelSave;
import com.zerocool.biztrack.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.zerocool.biztrack.Utils.CalUtils.getCustomDate;

/**
 * Created by CrashOverride on 1/5/2018.
 */

public class CustomerList extends Fragment {


    private RecyclerView CustomerListView;
    TextView ErrorMessage;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    DatePickerDialog startDate;
    DatePickerDialog endDate;
    List<ModelRetrieve> input = new ArrayList<>();
    ModelRetrieve modelRetrieve = new ModelRetrieve();
    ModelSave modelSave = new ModelSave();
    Save save = new Save();

    public CustomerList() {
    }

    private List<ModelRetrieve> dictionaryWords;
    private List<ModelRetrieve> filteredList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.customer_list_layout, container, false);
        setHasOptionsMenu(true);
        return rootView;
    }


    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Initialize(view);

//        CustomerListView = (RecyclerView) view.findViewById(R.id.RecyclerView);
        CustomerListView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        CustomerListView.setLayoutManager(layoutManager);

        input = Retrieve.getCustomerProfileList();
        adapter = new CustomerListAdapter(getActivity(), input);
        adapter.notifyDataSetChanged();
        CustomerListView.setAdapter(adapter);

    }


    @Override
    public void onCreateOptionsMenu(final Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.all_customer_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
//        final MenuItem menuItem = menu.findItem(R.id.action_search);


//        SearchView searchView = (SearchView)
//                MenuItemCompat.getActionView(menuItem);
//        searchView.setOnQueryTextListener(this);
//        final MenuItem item = menu.findItem(R.id.action_search);
//        final SearchView searchView = (SearchView) item.getActionView();
//
//        searchView.setOnQueryTextListener(this);


//        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                callSearch(query);
//                return true;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
////              if (searchView.isExpanded() && TextUtils.isEmpty(newText)) {
//                callSearch(newText);
////              }
//                return true;
//            }
//
//            public void callSearch(String query) {
//                //Do searching
//                input = Retrieve.getCustomerProfileList();
//                CustomFilter customFilter = new CustomFilter(input);
//
//                adapter.notifyDataSetChanged();
//                CustomerListView.setAdapter(adapter);
//
//            }
//
//        });
//        final MenuItem menuItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
//        searchView = menu.findItem(R.id.search);

//        mSearchView = (SearchView) mSearchBox.getActionView();
        searchView.setQueryHint(getString(R.string.mark_editor_hint));


        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //View.setItemsVisibility(menu, mSearchBox, false);
                searchView.requestFocus();
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                //setItemsVisibility(menu, mSearchBox, true);
                return true;    // true = prevent collapse mSearchView
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //adapter.getFilter().filter(newText);
                //adapter.get

                List<ModelRetrieve> input = new ArrayList<>();
                input = Retrieve.getCustomerProfileList();
                final List<ModelRetrieve> filteredModelList = filter(input, newText);
                adapter = new  CustomerListAdapter(getActivity(), filteredModelList);
                adapter.notifyDataSetChanged();
                CustomerListView.setAdapter(adapter);
                return true;
            }
        });




    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_add_new_customers) {

            Intent intent = new Intent(getActivity(), CustomerRegistration.class);
            startActivity(intent);
        }

        if (id == R.id.action_refresh_customers) {

            ProgressDialog progressDialog = new ProgressDialog(getActivity());
            progressDialog.show();
            progressDialog.setMessage("Refreshing...");

            CustomerListView.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(getContext());
            CustomerListView.setLayoutManager(layoutManager);
            List<ModelRetrieve> input = new ArrayList<>();
            input = Retrieve.getCustomerProfileList();
            adapter = new CustomerListAdapter(getActivity(), input);
            adapter.notifyDataSetChanged();
            CustomerListView.setAdapter(adapter);

            progressDialog.dismiss();

            System.out.println("=================================== CLICKED ON REFRESH");

        }

        if (id == R.id.action_all_customers) {

            CustomerListView.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(getContext());
            CustomerListView.setLayoutManager(layoutManager);
            List<ModelRetrieve> input = new ArrayList<>();
            input = Retrieve.getCustomerProfileList();
            adapter = new CustomerListAdapter(getActivity(), input);
            adapter.notifyDataSetChanged();
            CustomerListView.setAdapter(adapter);

        }

//        if (id == R.id.action_search) {
//
//            CustomerListView.setHasFixedSize(true);
//            layoutManager = new LinearLayoutManager(getContext());
//            CustomerListView.setLayoutManager(layoutManager);
//            List<ModelRetrieve> input = new ArrayList<>();
//            //input = Retrieve.getValuedCustomers();
//            adapter = new CustomerListAdapter(getActivity(), filteredList);
//            adapter.notifyDataSetChanged();
//            CustomerListView.setAdapter(adapter);
//
//        }

        if (id == R.id.action_dued_customers) {

            CustomerListView.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(getContext());
            CustomerListView.setLayoutManager(layoutManager);
            List<ModelRetrieve> input = new ArrayList<>();
            input = Retrieve.getDuedCustomers();
            adapter = new CustomerListAdapter(getActivity(), input);
            adapter.notifyDataSetChanged();
            CustomerListView.setAdapter(adapter);


        }


        if (id == R.id.action_valued_customers_ofday) {

            CustomerListView.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(getContext());
            CustomerListView.setLayoutManager(layoutManager);
            List<ModelRetrieve> input = new ArrayList<>();
            input = Retrieve.getMostValuedCustomers(CalUtils.getStringDate(CalUtils.startOfToday(CalUtils.getDate())),
                    CalUtils.getStringDate(CalUtils.endOfToDay(CalUtils.getDate())));
//            if (input.isEmpty()){
//                CustomerListView.setVisibility(View.GONE);
//                ErrorMessage.setText("No Records Found !");
//                //ErrorMessage.setVisibility(View.VISIBLE);
//            }
//            else {
            adapter = new CustomerListAdapter(getActivity(), input);
            adapter.notifyDataSetChanged();
            CustomerListView.setAdapter(adapter);
//            }

        }

        if (id == R.id.action_valued_customers_ofweek) {

            CustomerListView.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(getContext());
            CustomerListView.setLayoutManager(layoutManager);
            List<ModelRetrieve> input = new ArrayList<>();
            input = Retrieve.getMostValuedCustomers(CalUtils.getStringDate(CalUtils.getStartOfThisWeek(CalUtils.getDate())),
                    CalUtils.getStringDate(CalUtils.getEndOfThisWeek(CalUtils.getDate())));
            if (input.isEmpty()) {
                CustomerListView.setVisibility(View.GONE);
                ErrorMessage.setVisibility(View.VISIBLE);
            } else {

                adapter = new CustomerListAdapter(getActivity(), input);
                adapter.notifyDataSetChanged();
                CustomerListView.setAdapter(adapter);
            }

        }


        if (id == R.id.action_valued_customers_ofmonth) {

            CustomerListView.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(getContext());
            CustomerListView.setLayoutManager(layoutManager);
            List<ModelRetrieve> input = new ArrayList<>();
            //input = Retrieve.getCustomerOfMonth(CalUtils.getStartOfThisMonth(CalUtils.getDate()),CalUtils.getEndOfThisMonth(CalUtils.getDate()));
            input = Retrieve.getMostValuedCustomers(CalUtils.getStringDate(CalUtils.getStartOfThisMonth(CalUtils.getDate())),
                    CalUtils.getStringDate(CalUtils.getEndOfThisMonth(CalUtils.getDate())));

            if (input.isEmpty()) {
                ErrorMessage.setText("No Records Found !");
                ErrorMessage.setVisibility(View.VISIBLE);
            } else {

                adapter = new CustomerListAdapter(getActivity(), input);
                adapter.notifyDataSetChanged();
                CustomerListView.setAdapter(adapter);
            }

//            adapter = new CustomerListAdapter(getActivity(),input);
//            adapter.notifyDataSetChanged();
//            CustomerListView.setAdapter(adapter);


        }
        if (id == R.id.action_valued_customers_custom) {

            datePickerDialog();

        }


        return super.onOptionsItemSelected(item);
    }


    private void Initialize(View view) {
        CustomerListView = (RecyclerView) view.findViewById(R.id.RecyclerView);
        ErrorMessage = (TextView) view.findViewById(R.id.ErrorMessage);
    }


    private void datePickerDialog() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.pick_date_layout);
        dialog.setTitle("Pick Dates");
        Calendar calendar = Calendar.getInstance();

        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        final EditText FromDate, ToDate;
        final String Start, End;
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

        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        endDate = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                ToDate.setText(dateFormat.format(newDate.getTime()));
            }

        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));


        Start = FromDate.getText().toString();
        End = ToDate.getText().toString();

        Ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CustomerListView.setHasFixedSize(true);
                layoutManager = new LinearLayoutManager(getContext());
                CustomerListView.setLayoutManager(layoutManager);
                List<ModelRetrieve> input = new ArrayList<>();
                input = Retrieve.getMostValuedCustomers(CalUtils.getStringDate(getCustomDate(FromDate.getText().toString())),
                        CalUtils.getStringDate(getCustomDate(ToDate.getText().toString())));

                if (input.isEmpty()) {
                    ErrorMessage.setText("No Records Found !");
                    ErrorMessage.setVisibility(View.VISIBLE);
                } else {

                    adapter = new CustomerListAdapter(getActivity(), input);
                    adapter.notifyDataSetChanged();
                    CustomerListView.setAdapter(adapter);
                }

                dialog.dismiss();

            }
        });


    }


//    @Override
//    public boolean onQueryTextSubmit(String query) {
//        return false;
//    }
//
//    @Override
//    public boolean onQueryTextChange(String newText) {
//        adapter.getFilter().filter(newText.toString());
//        return false;
//    }


    private List<ModelRetrieve> filter(List<ModelRetrieve> models, String query) {
        query = query.toLowerCase();

        final List<ModelRetrieve> filteredModelList = new ArrayList<>();
        for (ModelRetrieve model : models) {
            final String CustomerName = model.getCustomerName().toLowerCase();
            final String Number = model.getPhoneNumber().toLowerCase();
//            if (text.contains(query)) {
//                filteredModelList.add(model);
//            }
            if (CustomerName.contains(query) || Number.contains(query) ) {
                filteredModelList.add(model);
            }


        }
        return filteredModelList;
    }


//
//    @Override
//    public boolean onQueryTextSubmit(String s) {
//        return false;
//    }
//
//    @Override
//    public boolean onQueryTextChange(String s) {
//        final List<ModelRetrieve> filteredModelList = filter(input, s);
//
//        animateTo(filteredModelList);
//        CustomerListView.scrollToPosition(0);
//        return true;
//    }
//
//
//
//    public void animateTo(List<ModelRetrieve> models) {
//        applyAndAnimateRemovals(models);
//        applyAndAnimateAdditions(models);
//        applyAndAnimateMovedItems(models);
//
//    }
//
//    private void applyAndAnimateRemovals(List<ModelRetrieve> newModels) {
//
//        for (int i = input.size() - 1; i >= 0; i--) {
//            final ModelRetrieve model = input.get(i);
//            if (!newModels.contains(model)) {
//                removeItem(i);
//            }
//        }
//    }
//
//    private void applyAndAnimateAdditions(List<ModelRetrieve> newModels) {
//
//        for (int i = 0, count = newModels.size(); i < count; i++) {
//            final ModelRetrieve model = newModels.get(i);
//            if (!input.contains(model)) {
//                addItem(i, model);
//            }
//        }
//    }
//
//    private void applyAndAnimateMovedItems(List<ModelRetrieve> newModels) {
//
//        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
//            final ModelRetrieve model = newModels.get(toPosition);
//            final int fromPosition = input.indexOf(model);
//            if (fromPosition >= 0 && fromPosition != toPosition) {
//                moveItem(fromPosition, toPosition);
//            }
//        }
//    }
//    public ModelRetrieve removeItem(int position) {
//        final ModelRetrieve model = input.remove(position);
//        adapter.notifyItemRemoved(position);
//        return model;
//    }
//    public void addItem(int position, ModelRetrieve model) {
//        input.add(position, model);
//        adapter.notifyItemInserted(position);
//    }
//
//    public void moveItem(int fromPosition, int toPosition) {
//        final ModelRetrieve model = input.remove(fromPosition);
//        input.add(toPosition, model);
//        adapter.notifyItemMoved(fromPosition, toPosition);
//    }


//    @Override
//    public boolean onQueryTextSubmit(String s) {
//        return false;
//    }
//
//    @Override
//    public boolean onQueryTextChange(String s) {
//        List<ModelRetrieve> input = new ArrayList<>();
//        input = Retrieve.getCustomerProfileList();
////        final List<ModelRetrieve> filteredModelList = filter(mCountryModel, newText);
////
////        adapter.setFilter(filteredModelList);
//        return true;
//    }


}
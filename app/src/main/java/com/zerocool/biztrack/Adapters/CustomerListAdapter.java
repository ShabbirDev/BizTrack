package com.zerocool.biztrack.Adapters;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.zerocool.biztrack.Activity.CustomFilter;
import com.zerocool.biztrack.Activity.CustomerProfile;
import com.zerocool.biztrack.Activity.EditCustomerProfile;
import com.zerocool.biztrack.DatabaseOperation.Delete;
import com.zerocool.biztrack.DatabaseOperation.Update;
import com.zerocool.biztrack.Models.ModelDelete;
import com.zerocool.biztrack.Models.ModelRetrieve;
import com.zerocool.biztrack.Models.ModelUpdate;
import com.zerocool.biztrack.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.zerocool.biztrack.Utils.UtillMets.decodeBase64;
import static com.zerocool.biztrack.Utils.UtillMets.toInteger;

/**
 * Created by CrashOverride on 1/5/2018.
 */

public class CustomerListAdapter extends RecyclerView.Adapter<CustomerListAdapter.PersonViewHolder>{

    private List<ModelRetrieve> customerLists;
    Context context;
    CustomFilter customFilter;
    private ModelUpdate modelUpdate = new ModelUpdate();
    private Update update = new Update();
    ModelDelete modelDelete = new ModelDelete();
    Delete delete = new Delete();
    private int i = 0;

    public CustomerListAdapter(Context context, List<ModelRetrieve> customerLists){
        this.customerLists = customerLists;
        this.context = context;
        //customFilter = new CustomFilter(CustomerListAdapter.this);
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_list_adapter3, parent, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(final PersonViewHolder holder, final int position) {

        final ModelRetrieve modelRetrieve = customerLists.get(position);

                holder.Serial.setText(""+ (position+1) +". ");
                holder.CustomerDue.setText("Due: "+modelRetrieve.getCustomerDue());
                holder.CustomerAddress.setText(modelRetrieve.getAddress());
                holder.CustomerName.setText(modelRetrieve.getCustomerName());
                holder.CustomerImage.setImageBitmap(decodeBase64(modelRetrieve.getCustomerImage()));
                //holder.Total.setText(""+modelRetrieve.getTotal());



                holder.ButtonAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        PopupMenu popup = new PopupMenu(context, holder.ButtonAction);
                        popup.inflate(R.menu.all_customers_options);
                        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                switch (item.getItemId()) {
                                    case R.id.option_edit:
                                        Intent intent = new Intent(context, EditCustomerProfile.class);
//                                        intent.putExtra("CustomerID",modelRetrieve.getCustomerID());
//                                        intent.putExtra("Phone",modelRetrieve.getPhoneNumber());
                                        intent.putExtra("UID",modelRetrieve.getCustomerUID());
                                        context.startActivity(intent);
                                        //customerEditDialog(modelRetrieve.getProductID(),modelRetrieve.getProductName(),modelRetrieve.getUnitPrice());
                                        break;
                                    case R.id.option_delete:
//                                        customerDeleteDialog(position,modelRetrieve.getCustomerID());
//                                        customerDeleteDialog(position,modelRetrieve.getPhoneNumber());
                                        customerDeleteDialog(position,modelRetrieve.getCustomerUID());
                                        break;
                                }
                                return false;
                            }
                        });
                        //displaying the popup
                        popup.show();
                    }
                });


                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, CustomerProfile.class);
                        //intent.putExtra("CustomerID",modelRetrieve.getCustomerID());
//                        intent.putExtra("Phone",modelRetrieve.getPhoneNumber());
                        intent.putExtra("UID",modelRetrieve.getCustomerUID());
                        System.out.printf("=Shopped:================================="+modelRetrieve.getTotalShopped()+"==================="+modelRetrieve.getCashPaid()+"====================="+modelRetrieve.getCustomerDue());

                        System.out.println("=================================================ListAdapter Phonenumber "+modelRetrieve.getCustomerUID());
                        System.out.printf("=Shopped:================================="+modelRetrieve.getTotalShopped()+"==================="+modelRetrieve.getCashPaid()+"====================="+modelRetrieve.getCustomerDue());


                        context.startActivity(intent);
                    }
                });





    }

    @Override
    public int getItemCount() {
        return customerLists.size();
    }

    public void removeAt(int position) {
        customerLists.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, customerLists.size());
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        CardView ProductCardView;
        TextView CustomerDue,CustomerAddress,CustomerName,Serial,Total;
        ImageButton ButtonAction;
        CircleImageView CustomerImage;


        PersonViewHolder(View itemView) {
            super(itemView);

            ProductCardView = (CardView)itemView.findViewById(R.id.ProductCardView);
            Serial = (TextView)itemView.findViewById(R.id.Serial);
            CustomerDue = (TextView)itemView.findViewById(R.id.CustomerDue);
            CustomerAddress = (TextView)itemView.findViewById(R.id.CustomerAddress);
            CustomerName = (TextView) itemView.findViewById(R.id.CustomerName);
            CustomerImage = (CircleImageView) itemView.findViewById(R.id.CustomerImage);
            //Total = (TextView) itemView.findViewById(R.id.Total);
            ButtonAction = (ImageButton) itemView.findViewById(R.id.ButtonAction);

        }
    }


    private void customerEditDialog(final int id, String name, int price){

        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Saving...");
        progressDialog.show();

        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.edit_products_layout);
        dialog.setTitle("Edit Products");

        final EditText ProductName,UnitPrice;
        Button ButtonSave,ButtonCancel;

        ProductName = (EditText)dialog.findViewById(R.id.ProductName);
        UnitPrice = (EditText)dialog.findViewById(R.id.UnitPrice);
        ButtonSave = (Button)dialog.findViewById(R.id.ButtonSave);
        ButtonCancel = (Button)dialog.findViewById(R.id.ButtonCancel);

        ProductName.setText(name);
        UnitPrice.setText(""+price);

        dialog.show();

        ButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                modelUpdate.setProductID(id);
                modelUpdate.setProductName(ProductName.getText().toString());
                modelUpdate.setUnitPrice(toInteger(UnitPrice));
                update.Product(modelUpdate);

                //System.out.println("======"+modelUpdate.getProductID()+"=========="+modelUpdate.getProductName()+"========"+modelUpdate.getUnitPrice()+"========");
                //refreshEvents(customers);
                //notifyDataSetChanged();

                notifyDataSetChanged();
                progressDialog.dismiss();
                dialog.dismiss();


            }
        });


        ButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });









    }

    private void customerDeleteDialog(final int position, final String UUID){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.delete_warning_layout);
        dialog.setTitle("Delete ?");

        Button ConfirmYes,ConfirmNo;
        ConfirmYes = (Button)dialog.findViewById(R.id.ConfirmYes);
        ConfirmNo = (Button)dialog.findViewById(R.id.ConfirmNo);
        dialog.show();

        ConfirmYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                removeAt(position);
                delete.deleteOneCustomer(UUID);
                delete.deleteAllHistory(UUID);

                dialog.dismiss();


            }
        });


        ConfirmNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });









    }





    public void animateTo(List<ModelRetrieve> models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);

    }

    private void applyAndAnimateRemovals(List<ModelRetrieve> newModels) {

        for (int i = customerLists.size() - 1; i >= 0; i--) {
            final ModelRetrieve model = customerLists.get(i);
            if (!newModels.contains(model)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(List<ModelRetrieve> newModels) {

        for (int i = 0, count = newModels.size(); i < count; i++) {
            final ModelRetrieve model = newModels.get(i);
            if (!customerLists.contains(model)) {
                addItem(i, model);
            }
        }
    }

    private void applyAndAnimateMovedItems(List<ModelRetrieve> newModels) {

        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
            final ModelRetrieve model = newModels.get(toPosition);
            final int fromPosition = customerLists.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }



    public ModelRetrieve removeItem(int position) {
        final ModelRetrieve model = customerLists.remove(position);
        notifyItemRemoved(position);
        return model;
    }

    public void addItem(int position, ModelRetrieve model) {
        customerLists.add(position, model);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        final ModelRetrieve model = customerLists.remove(fromPosition);
        customerLists.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }




}
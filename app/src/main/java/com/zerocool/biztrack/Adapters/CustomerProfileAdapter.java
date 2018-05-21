package com.zerocool.biztrack.Adapters;//package com.crashoverride.keeptrack.Adapters;
//
//import android.Manifest;
//import android.app.Dialog;
//import android.content.Context;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.net.Uri;
//import android.os.Bundle;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentActivity;
//import android.support.v7.widget.RecyclerView;
//import android.telephony.PhoneNumberUtils;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.CheckBox;
//import android.widget.EditText;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.crashoverride.keeptrack3.DataModel.ProductTable;
//import com.crashoverride.keeptrack3.DatabaseOperation.Delete;
//import com.crashoverride.keeptrack3.DatabaseOperation.Save;
//import com.crashoverride.keeptrack3.DatabaseOperation.Update;
//import com.crashoverride.keeptrack3.Activity.CustomerHistory;
//import com.crashoverride.keeptrack3.Models.ModelDelete;
//import com.crashoverride.keeptrack3.Models.ModelRetrieve;
//import com.crashoverride.keeptrack3.Models.ModelSave;
//import com.crashoverride.keeptrack3.Models.ModelUpdate;
//import com.crashoverride.keeptrack3.R;
//
//import java.util.List;
//
//import static com.crashoverride.keeptrack3.UtilityMethods.decodeBase64;
//import static com.crashoverride.keeptrack3.UtilityMethods.getDate;
//import static com.crashoverride.keeptrack3.UtilityMethods.toInteger;
//
///**
// * Created by CrashOverride on 1/5/2018.
// */
//
//public class CustomerProfileAdapter extends RecyclerView.Adapter<CustomerProfileAdapter.CustomerViewHolder> {
//
//
//    List<ModelRetrieve> products;
//    ModelUpdate modelUpdate = new ModelUpdate();
//    Update update = new Update();
//    ModelDelete modelDelete = new ModelDelete();
//    Delete delete = new Delete();
//    ModelSave modelSave = new ModelSave();
//    Save saveData = new Save();
//    private int i = 0;
//
//    Context mcontext;
//
//    public CustomerProfileAdapter(Context context, List<ModelRetrieve> products){
//        this.products = products;
//        this.mcontext = context;
//    }
//
//    @Override
//    public CustomerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_profile_items, parent, false);
//        CustomerViewHolder pvh = new CustomerViewHolder(v);
//        return pvh;
//
//
//    }
//
//    @Override
//    public void onBindViewHolder(final CustomerViewHolder holder, final int position) {
//
//        final ModelRetrieve modelRetrieve = products.get(position);
//
//        holder.CustomerName.setText(modelRetrieve.getCustomerName());
//        holder.CustomerImage.setImageBitmap(decodeBase64(modelRetrieve.getCustomerImage()));
////        holder.PhoneNumber.setText(""+modelRetrieve.getPhoneNumber());
//        holder.PhoneNumber.setText(PhoneNumberUtils.formatNumber(modelRetrieve.getPhoneNumber()));
//
//        holder.Address.setText("Address: "+modelRetrieve.getAddress());
//        holder.PreviousDue.setText("Previous Due: "+modelRetrieve.getCustomerDue());
//
//
//
//
//
//
//
//        holder.NewEntryPayment.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(holder.NewEntryPayment.isChecked()){
//                    holder.TotalShopped.setVisibility(View.VISIBLE);
//                    holder.CashPaid.setVisibility(View.VISIBLE);
//                    holder.SaveCash.setVisibility(View.VISIBLE);
//                    holder.DuePayment.setChecked(false);
//                }
//                else{
//                    holder.TotalShopped.setVisibility(View.GONE);
//                    holder.CashPaid.setVisibility(View.GONE);
//                    holder.SaveCash.setVisibility(View.GONE);
//                }
//
//
//                holder.DuePaid.setVisibility(View.GONE);
//                holder.SaveDue.setVisibility(View.GONE);
//
//            }
//        });
//
//        holder.DuePayment.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if(holder.DuePayment.isChecked()){
//                    holder.DuePaid.setVisibility(View.VISIBLE);
//                    holder.SaveDue.setVisibility(View.VISIBLE);
//                    holder.NewEntryPayment.setChecked(false);
//                }
//                else{
//                    holder.DuePaid.setVisibility(View.GONE);
//                    holder.SaveDue.setVisibility(View.GONE);
//                }
//         holder.TotalShopped.setVisibility(View.GONE);
//         holder.CashPaid.setVisibility(View.GONE);
//         holder.SaveCash.setVisibility(View.GONE);
//
//
//            }
//        });
//
//        holder.DialNumber.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent call = new Intent(Intent.ACTION_CALL);
//
//                call.setData(Uri.parse("tel:"+modelRetrieve.getPhoneNumber()));
//
//                if (ActivityCompat.checkSelfPermission(mcontext,
//                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                    return;
//                }
//                mcontext.startActivity(call);
//
//            }
//        });
//
//        holder.SaveCash.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                int PreviousDue = modelRetrieve.getCustomerDue();
//                int NowRecievable = (modelRetrieve.getCustomerDue() + toInteger(holder.TotalShopped));
//                int NowDue = (NowRecievable - toInteger(holder.CashPaid));
//
//                modelUpdate.setCustomerDue(NowDue);
//                update.CustomerDue(modelUpdate);
//
//                modelSave.setPhoneNumber(modelRetrieve.getPhoneNumber());
//                modelSave.setTotalRecievable(NowRecievable);
//                modelSave.setCashPaid(toInteger(holder.CashPaid));
//                modelSave.setCustomerDue(NowDue);
//                modelSave.setDate(getDate());
//
//                saveData.CustomerHistory(modelSave);
//
//            }
//        });
//
//
//        holder.SaveDue.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int RemainingDue = modelRetrieve.getCustomerDue() - toInteger(holder.DuePaid);
//                modelUpdate.setCustomerDue(RemainingDue);
//
//                modelSave.setPhoneNumber(modelRetrieve.getPhoneNumber());
//                modelSave.setTotalRecievable(modelRetrieve.getCustomerDue());
//                modelSave.setCashPaid(toInteger(holder.CashPaid));
//                modelSave.setCustomerDue(RemainingDue);
//                modelSave.setDate(getDate());
//
//                saveData.CustomerHistory(modelSave);
//
//            }
//        });
//
//
//
//
//
//
//        holder.CustomerHistory.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Fragment fragment = new CustomerHistory();
//                Bundle args = new Bundle();
//                args.putString("PhoneNumber",modelRetrieve.getPhoneNumber());
//                fragment.setArguments(args);
//                ((FragmentActivity)mcontext).getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.main_content, fragment).addToBackStack(null)
//                        .commit();
//                System.out.println("");
//
//            }
//        });
//
//
//
//
//
//
//    }
//
//
//    @Override
//    public int getItemCount() {
//        return products.size();
//    }
//
//    public void removeAt(int position) {
//        products.remove(position);
//        notifyItemRemoved(position);
//        notifyItemRangeChanged(position, products.size());
//    }
//
//
//    @Override
//    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
//        super.onAttachedToRecyclerView(recyclerView);
//    }
//
////    @Override
////    public void onClick(View view) {
////        switch (view.getId()){
////            case R.id.DialNumber:
////                break;
////            case R.id.NewEntryPayment:
////                view.TotalShopped.
////                break;
////            case R.id.CashBack:
////                break;
////            case R.id.Submit:
////                break;
////            case R.id.CustomerHistory:
////                break;
////
////        }
////    }
//
//
//    public static class CustomerViewHolder extends RecyclerView.ViewHolder {
//
//        TextView CustomerName, PhoneNumber, Address, PreviousDue;
//        ImageView CustomerImage;
//        ImageButton DialNumber;
//        CheckBox NewEntryPayment, DuePayment;
//        EditText TotalShopped, CashPaid, DuePaid;
//        Button SaveCash,SaveDue, CustomerHistory;
//
//        CustomerViewHolder(View itemView) {
//            super(itemView);
//            CustomerName = itemView.findViewById(R.id.CustomerName);
//            CustomerImage = itemView.findViewById(R.id.CustomerImage);
//            PhoneNumber = itemView.findViewById(R.id.PhoneNumber);
//            DialNumber = itemView.findViewById(R.id.DialNumber);
//            Address = itemView.findViewById(R.id.Address);
//            PreviousDue = itemView.findViewById(R.id.PreviousDue);
//            NewEntryPayment = itemView.findViewById(R.id.CashEntry);
//            DuePayment = itemView.findViewById(R.id.CashBackEntry);
//            TotalShopped = itemView.findViewById(R.id.TotalShopped);
//            CashPaid = itemView.findViewById(R.id.CashPaid);
//            DuePaid = itemView.findViewById(R.id.CashBacked);
//            SaveCash = itemView.findViewById(R.id.SaveCash);
//            SaveDue = itemView.findViewById(R.id.SaveCashBack);
//            CustomerHistory = itemView.findViewById(R.id.CustomerHistory);
//
//
//            //personPhoto = (ImageView)itemView.findViewById(R.id.person_photo);
//        }
//    }
//
//
//
//
//    public void refreshEvents(List<ModelRetrieve> events) {
//        //this.customers.clear();
//        this.products.addAll(events);
//        notifyDataSetChanged();
//    }
//
//
//
//
//
//    private void showEditDialog(final int id, String name, int price){
//        final Dialog dialog = new Dialog(mcontext);
//        dialog.setContentView(R.layout.edit_products_layout);
//        dialog.setTitle("Edit Products");
//
//        final EditText ProductName,UnitPrice;
//        Button ButtonSave,ButtonCancel;
//
//        ProductName = (EditText)dialog.findViewById(R.id.ProductName);
//        UnitPrice = (EditText)dialog.findViewById(R.id.UnitPrice);
//        ButtonSave = (Button)dialog.findViewById(R.id.ButtonSave);
//        ButtonCancel = (Button)dialog.findViewById(R.id.ButtonCancel);
//
//        ProductName.setText(name);
//        UnitPrice.setText(""+price);
//
//        dialog.show();
//
//        ButtonSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                modelUpdate.setProductID(id);
//                modelUpdate.setProductName(ProductName.getText().toString());
//                modelUpdate.setUnitPrice(toInteger(UnitPrice));
//                update.Product(modelUpdate);
//
//                //System.out.println("======"+modelUpdate.getProductID()+"=========="+modelUpdate.getProductName()+"========"+modelUpdate.getUnitPrice()+"========");
//                //refreshEvents(customers);
//                //notifyDataSetChanged();
//
//                dialog.dismiss();
//
//
//            }
//        });
//
//
//        ButtonCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//            }
//        });
//
//
//
//
//
//
//
//
//
//    }
//
//    private void showDeleteDialog(final int position, final int id){
//        final Dialog dialog = new Dialog(mcontext);
//        dialog.setContentView(R.layout.delete_warning_layout);
//        dialog.setTitle("Are you sure ?");
//
//        Button ConfirmYes,ConfirmNo;
//        ConfirmYes = (Button)dialog.findViewById(R.id.ConfirmYes);
//        ConfirmNo = (Button)dialog.findViewById(R.id.ConfirmNo);
//        dialog.show();
//
//        ConfirmYes.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                removeAt(position);
//                delete.singleProduct(id);
//
//                dialog.dismiss();
//
//
//            }
//        });
//
//
//        ConfirmNo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//            }
//        });
//
//
//
//
//
//
//
//
//
//    }
//
//    private void showCartDialog(final int position, final int id, final int UnitPrice){
//        final Dialog dialog = new Dialog(mcontext);
//        dialog.setContentView(R.layout.quantity_layout);
//        dialog.setTitle("Quantity ?");
//
//        ImageButton AddQuantity,RemoveQuantity;
//        Button ButtonConfirm,ButtonCancel;
//        final TextView Quantity,EstimatedCost;
//
//        AddQuantity = (ImageButton)dialog.findViewById(R.id.AddQuantity);
//        RemoveQuantity = (ImageButton)dialog.findViewById(R.id.RemoveQuantity);
//        ButtonConfirm = (Button)dialog.findViewById(R.id.ButtonConfirm);
//        ButtonCancel = (Button)dialog.findViewById(R.id.ButtonCancel);
//        Quantity = (TextView)dialog.findViewById(R.id.Quantity);
//        EstimatedCost = (TextView)dialog.findViewById(R.id.EstimatedCost);
//
//        dialog.show();
//
//
//        AddQuantity.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                i++;
//                Quantity.setText(""+i);
//                EstimatedCost.setText("Estimated Cost: "+i*UnitPrice);
//
//            }
//        });
//
//        RemoveQuantity.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                --i;
//                Quantity.setText(""+i);
//                EstimatedCost.setText("Estimated Cost: "+i*UnitPrice);
//
//            }
//        });
//
//
//
//        ButtonConfirm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                removeAt(position);
//                modelUpdate.setProductID(id);
//                modelUpdate.setQuantity(i);
//                modelUpdate.setIfCarted(ProductTable.Carted);
//                update.Carted(modelUpdate);
//                i=0;
//                dialog.dismiss();
//
//
//            }
//        });
//
//
//        ButtonCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//            }
//        });
//
//
//
//
//
//
//
//
//
//    }
//
//
//
//}
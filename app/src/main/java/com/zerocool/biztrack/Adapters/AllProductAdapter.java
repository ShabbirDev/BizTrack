package com.zerocool.biztrack.Adapters;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
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

import com.zerocool.biztrack.DataModel.ProductTable;
import com.zerocool.biztrack.DatabaseOperation.Delete;
import com.zerocool.biztrack.DatabaseOperation.Update;
import com.zerocool.biztrack.Models.ModelDelete;
import com.zerocool.biztrack.Models.ModelRetrieve;
import com.zerocool.biztrack.Models.ModelUpdate;
import com.zerocool.biztrack.R;

import java.util.List;

import static com.zerocool.biztrack.Utils.UtillMets.buttonEffect;
import static com.zerocool.biztrack.Utils.UtillMets.toInteger;

/**
 * Created by CrashOverride on 1/5/2018.
 */

public class AllProductAdapter extends RecyclerView.Adapter<AllProductAdapter.ProductsViewHolder> {


    List<ModelRetrieve> products;
    ModelUpdate modelUpdate = new ModelUpdate();
    Update update = new Update();
    ModelDelete modelDelete = new ModelDelete();
    Delete delete = new Delete();
    private int i = 0;

    Context mcontext;

    public AllProductAdapter(Context context, List<ModelRetrieve> products) {
        this.products = products;
        this.mcontext = context;
    }

    @Override
    public ProductsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_product_items2, parent, false);
        ProductsViewHolder pvh = new ProductsViewHolder(v);
        return pvh;


    }

    @Override
    public void onBindViewHolder(final ProductsViewHolder holder, final int position) {

        final ModelRetrieve modelRetrieve = products.get(position);


        holder.Serial.setText("" + (position + 1) + ". ");
        holder.ProductsName.setText(modelRetrieve.getProductName());
        holder.UnitPrice.setText(modelRetrieve.getUnitPrice() + " BDT");
        holder.Company.setText(modelRetrieve.getCompany());


//        holder.ButtonAction.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                showEditDialog(modelRetrieve.getProductID(),modelRetrieve.getProductName(),modelRetrieve.getUnitPrice());
//
//            }
//        });


        holder.ButtonAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PopupMenu popup = new PopupMenu(mcontext, holder.ButtonAction);
                //inflating menu from xml resource
                popup.inflate(R.menu.all_products_options);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.option_edit:
                                showEditDialog(modelRetrieve.getProductID(), modelRetrieve.getProductName(), modelRetrieve.getCompany(), modelRetrieve.getUnitPrice());
                                break;
                            case R.id.option_delete:
                                showDeleteDialog(position, modelRetrieve.getProductID());
                                break;
                            case R.id.option_add_to_cart:
                                showCartDialog(position, modelRetrieve.getProductID(), modelRetrieve.getUnitPrice());
                                break;
                        }
                        return false;
                    }
                });
                //displaying the popup
                popup.show();


            }
        });


//        holder.ButtonDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //modelDelete.setProductID(modelRetrieve.getProductID());
//                showDeleteDialog(position,modelRetrieve.getProductID());
////                removeAt(position);
////                delete.singleProduct(modelRetrieve.getProductID());
//            }
//        });


//        holder.Cart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showCartDialog(position, modelRetrieve.getProductID(), modelRetrieve.getUnitPrice());
//            }
//        });


    }


    @Override
    public int getItemCount() {
        return products.size();
    }

    public void removeAt(int position) {
        products.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, products.size());
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    public static class ProductsViewHolder extends RecyclerView.ViewHolder {
        CardView ProductCardView;
        TextView ProductsName, UnitPrice, Serial, Company;
        ImageButton ButtonAction, ButtonDelete, Cart;
        //Toolbar ProductToolbar;
        //ImageView personPhoto;


        ProductsViewHolder(View itemView) {
            super(itemView);
            ProductCardView = (CardView) itemView.findViewById(R.id.ProductCardView);
            ProductsName = (TextView) itemView.findViewById(R.id.ProductName);
            UnitPrice = (TextView) itemView.findViewById(R.id.UnitPrice);
            Company = (TextView) itemView.findViewById(R.id.Company);
            Serial = (TextView) itemView.findViewById(R.id.Serial);
            //Cart = (ImageButton) itemView.findViewById(R.id.Cart);
            ButtonAction = (ImageButton) itemView.findViewById(R.id.ButtonAction);

            //ProductToolbar = itemView.findViewById(R.id.ProductToolbar);

            //ButtonDelete = (ImageButton)itemView.findViewById(R.id.ButtonDelete);


            //personPhoto = (ImageView)itemView.findViewById(R.id.person_photo);
        }
    }


    public void refreshEvents(List<ModelRetrieve> events) {
        //this.customers.clear();
        this.products.addAll(events);
        notifyDataSetChanged();
    }


    private void showEditDialog(final int id, String name, String company, int price) {

        final ProgressDialog progressDialog = new ProgressDialog(mcontext);
        progressDialog.setMessage("Saving...");


        final Dialog dialog = new Dialog(mcontext);
        dialog.setContentView(R.layout.edit_products_layout);
        dialog.setTitle("Edit Products");

        final EditText ProductName, Company, UnitPrice;
        Button ButtonSave, ButtonCancel;

        ProductName = (EditText) dialog.findViewById(R.id.ProductName);
        Company = (EditText) dialog.findViewById(R.id.CompanyName);
        UnitPrice = (EditText) dialog.findViewById(R.id.UnitPrice);
        ButtonSave = (Button) dialog.findViewById(R.id.ButtonSave);
        ButtonCancel = (Button) dialog.findViewById(R.id.ButtonCancel);

        ProductName.setText(name);
        UnitPrice.setText("" + price);
        Company.setText(company);

        dialog.show();

        ButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                progressDialog.show();
                modelUpdate.setProductID(id);
                modelUpdate.setProductName(ProductName.getText().toString());
                modelUpdate.setCompany(Company.getText().toString());
                modelUpdate.setUnitPrice(toInteger(UnitPrice));
                update.Product(modelUpdate);

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

    private void showDeleteDialog(final int position, final int id) {
        final Dialog dialog = new Dialog(mcontext);
        dialog.setContentView(R.layout.delete_warning_layout);
        dialog.setTitle("Are you sure ?");

        Button ConfirmYes, ConfirmNo;
        ConfirmYes = (Button) dialog.findViewById(R.id.ConfirmYes);
        ConfirmNo = (Button) dialog.findViewById(R.id.ConfirmNo);
        dialog.show();

        ConfirmYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeAt(position);
                Delete.singleProduct(id);
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

    private void showCartDialog(final int position, final int id, final int UnitPrice) {
        final Dialog dialog = new Dialog(mcontext);
        dialog.setContentView(R.layout.quantity_layout);
        dialog.setTitle("Quantity ?");

        ImageButton AddQuantity, RemoveQuantity;
        final Button ButtonConfirm, ButtonCancel;
        //final EditText Quantity;
        final TextView Quantity;
        final TextView EstimatedCost;

        AddQuantity = (ImageButton) dialog.findViewById(R.id.AddQuantity);
        RemoveQuantity = (ImageButton) dialog.findViewById(R.id.RemoveQuantity);
        ButtonConfirm = (Button) dialog.findViewById(R.id.ButtonConfirm);
        ButtonCancel = (Button) dialog.findViewById(R.id.ButtonCancel);
        Quantity = (TextView) dialog.findViewById(R.id.Quantity);
        EstimatedCost = (TextView) dialog.findViewById(R.id.EstimatedCost);
        //buttonEffect(ButtonConfirm);
        //buttonEffect(ButtonCancel);

        dialog.show();


        AddQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                i++;
                Quantity.setText("" + i);
                EstimatedCost.setText("Estimated Cost: " + i * UnitPrice);

            }
        });

        RemoveQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                --i;
                Quantity.setText("" + i);
                EstimatedCost.setText("Estimated Cost: " + i * UnitPrice);

            }
        });


        ButtonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeAt(position);
                modelUpdate.setProductID(id);
                modelUpdate.setQuantity(i);
                modelUpdate.setIfCarted(ProductTable.Carted);
                update.Carted(modelUpdate);
                i = 0;
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


}
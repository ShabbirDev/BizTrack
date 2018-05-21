package com.zerocool.biztrack.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.zerocool.biztrack.DataModel.ProductTable;
import com.zerocool.biztrack.DatabaseOperation.Update;
import com.zerocool.biztrack.Models.ModelRetrieve;
import com.zerocool.biztrack.Models.ModelUpdate;
import com.zerocool.biztrack.R;

import java.util.List;

import static com.zerocool.biztrack.Utils.UtillMets.toInteger;

/**
 * Created by CrashOverride on 1/5/2018.
 */

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.PersonViewHolder>{


    private List<ModelRetrieve> products;
    Context context;
    private ModelUpdate modelUpdate = new ModelUpdate();
    private Update update = new Update();
    private int i = 0;

    public InventoryAdapter(Context context, List<ModelRetrieve> products){
        this.products = products;
        this.context = context;
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.inventory_items, parent, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(final PersonViewHolder holder, final int position) {

        final ModelRetrieve modelRetrieve = products.get(position);



        holder.Serial.setText(" "+String.valueOf(modelRetrieve.getProductID())+".");
        holder.ProductsName.setText(modelRetrieve.getProductName());
        holder.UnitPrice.setText(" "+String.valueOf(modelRetrieve.getUnitPrice())+" BDT");


        holder.AddQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                holder.Quantity.setText(""+i);
            }
        });

        holder.RemoveQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                --i;
                holder.Quantity.setText(""+i);
            }
        });

        holder.Cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeAt(position);

//                if(!holder.Quantity.getText().toString().equals(""))
//                    modelUpdate.setQuantity(toInteger(holder.Quantity));
//                else
//                    modelUpdate.setQuantity(1);
                modelUpdate.setQuantity(i);

                modelUpdate.setProductID(modelRetrieve.getProductID());
                modelUpdate.setIfCarted(ProductTable.Carted);
                update.Carted(modelUpdate);
                System.out.println("============from inventoryada==========================QUANTITY:"+modelUpdate.getQuantity());
                i = 0;

            }
        });




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

    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        CardView ProductCardView;
        TextView ProductsName,UnitPrice,Serial;
        ImageButton Cart,ButtonEdit,RemoveQuantity,AddQuantity;
        TextView Quantity;
        //ImageView personPhoto;


        PersonViewHolder(View itemView) {
            super(itemView);
            ProductCardView = (CardView)itemView.findViewById(R.id.ProductCardView);
            Serial = (TextView)itemView.findViewById(R.id.Serial);
            ProductsName = (TextView)itemView.findViewById(R.id.ProductName);
            UnitPrice = (TextView)itemView.findViewById(R.id.UnitPrice);
            Cart = (ImageButton)itemView.findViewById(R.id.Cart);
            RemoveQuantity = (ImageButton)itemView.findViewById(R.id.RemoveQuantity);
            AddQuantity = (ImageButton)itemView.findViewById(R.id.AddQuantity);
            Quantity = (TextView) itemView.findViewById(R.id.Quantity);
            //ButtonEdit = (ImageButton)itemView.findViewById(R.id.ButtonEdit);

            //personPhoto = (ImageView)itemView.findViewById(R.id.person_photo);
        }
    }

    private void showDialog(final int id,String name, int price){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.edit_products_layout);

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
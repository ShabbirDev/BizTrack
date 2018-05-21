package com.zerocool.biztrack.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.zerocool.biztrack.DataModel.ProductTable;
import com.zerocool.biztrack.DatabaseOperation.Update;
import com.zerocool.biztrack.Models.ModelRetrieve;
import com.zerocool.biztrack.Models.ModelUpdate;
import com.zerocool.biztrack.R;

import java.util.List;

/**
 * Created by CrashOverride on 1/5/2018.
 */

public class ToPurchaseAdapter extends RecyclerView.Adapter<ToPurchaseAdapter.PersonViewHolder>{


    Context context;
    List<ModelRetrieve> products;
    Update update = new Update();
    ModelUpdate modelUpdate = new ModelUpdate();
    private int i = 0;

    public ToPurchaseAdapter(Context context, List<ModelRetrieve> products){
        this.context = context;
        this.products = products;
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.topurchase_items, parent, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(final PersonViewHolder holder, final int position) {
        final ModelRetrieve modelRetrieve = products.get(position);

        holder.Serial.setText(""+(position+1)+". ");
        holder.ProductsName.setText(modelRetrieve.getProductName());
        int res = modelRetrieve.getUnitPrice()*modelRetrieve.getQuantity();
        holder.UnitPrice.setText("Cost:"+ String.valueOf(res)+" BDT");
        holder.Quantity.setText(""+modelRetrieve.getQuantity());
        holder.Company.setText(""+modelRetrieve.getCompany());
        i = modelRetrieve.getQuantity();

//        System.out.println("======================================UNIT PRICE:"+modelRetrieve.getUnitPrice());
//        System.out.println("======================================QUANTITY:"+modelRetrieve.getQuantity());
//        System.out.println("======================================RESULT:"+res);


//        holder.ProductsName.setText(customers.get(position).getProductName());
//        holder.UnitPrice.setText(""+customers.get(position).getUnitPrice());

        holder.AddQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //i = 1;
                //i = modelRetrieve.getQuantity();
                i = i + 1;
                modelUpdate.setQuantity(i);
                holder.Quantity.setText(""+i);
                holder.UnitPrice.setText("Cost:"+modelRetrieve.getUnitPrice()*i+" BDT");
                //i=1;
            }
        });

        holder.RemoveQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //i = modelRetrieve.getQuantity();
                --i;
                modelUpdate.setQuantity(i);
                holder.Quantity.setText(""+i);
                holder.UnitPrice.setText("Cost:"+modelRetrieve.getUnitPrice()*i+" BDT");
                //i=1;
            }
        });

        holder.ButtonPurchased.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                removeAt(position);
//                modelUpdate.setProductID(modelRetrieve.getProductID());
//                modelUpdate.setQuantity(i);
//                modelUpdate.setIfCarted(ProductTable.DisCarted);
//                update.DisCarted(modelUpdate);

                showDiscartedDialog(position,modelRetrieve.getProductID(),i);
            }
        });

//        holder.personPhoto.setImageResource(persons.get(i).photoId);

    }

    public void removeAt(int position) {
        products.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, products.size());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        CardView ProductCardView;
        TextView ProductsName,UnitPrice,Serial,Quantity,Company;
        ImageButton ButtonPurchased,RemoveQuantity,AddQuantity;
        //ImageView personPhoto;


        PersonViewHolder(View itemView) {
            super(itemView);
            ProductCardView = (CardView)itemView.findViewById(R.id.ProductCardView);
            Serial = (TextView)itemView.findViewById(R.id.Serial);
            ProductsName = (TextView)itemView.findViewById(R.id.ProductName);
            UnitPrice = (TextView)itemView.findViewById(R.id.UnitPrice);
            ButtonPurchased = (ImageButton) itemView.findViewById(R.id.ButtonPurchased);

            RemoveQuantity = (ImageButton)itemView.findViewById(R.id.RemoveQuantity);
            AddQuantity = (ImageButton)itemView.findViewById(R.id.AddQuantity);
            Quantity = (TextView) itemView.findViewById(R.id.Quantity);
            Company = (TextView) itemView.findViewById(R.id.Company);
            //personPhoto = (ImageView)itemView.findViewById(R.id.person_photo);
        }
    }




    private void showDiscartedDialog(final int position, final int id, final int quantity){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.discart_warning_layout);
        dialog.setTitle("Are you sure ?");

        Button ConfirmYes,ConfirmNo;
        ConfirmYes = (Button)dialog.findViewById(R.id.ConfirmYes);
        ConfirmNo = (Button)dialog.findViewById(R.id.ConfirmNo);
        dialog.show();

        ConfirmYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                removeAt(position);
                modelUpdate.setProductID(id);
                modelUpdate.setQuantity(quantity);
                modelUpdate.setIfCarted(ProductTable.DisCarted);
                update.DisCarted(modelUpdate);

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





}
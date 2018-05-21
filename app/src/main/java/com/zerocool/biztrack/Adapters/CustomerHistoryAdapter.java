package com.zerocool.biztrack.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.zerocool.biztrack.DataModel.ProductTable;
import com.zerocool.biztrack.DatabaseOperation.Delete;
import com.zerocool.biztrack.DatabaseOperation.Update;
import com.zerocool.biztrack.Models.ModelRetrieve;
import com.zerocool.biztrack.Models.ModelUpdate;
import com.zerocool.biztrack.R;

import java.util.List;

/**
 * Created by CrashOverride on 1/5/2018.
 */

public class CustomerHistoryAdapter extends RecyclerView.Adapter<CustomerHistoryAdapter.HistoryViewHolder> {

    Context context;
    List<ModelRetrieve> customers;
    Update update = new Update();
    ModelUpdate modelUpdate = new ModelUpdate();
    Delete delete = new Delete();
    private int i = 0;

    public CustomerHistoryAdapter(Context context, List<ModelRetrieve> customers) {
        this.context = context;
        this.customers = customers;
    }

    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_history_items, parent, false);
        HistoryViewHolder pvh = new HistoryViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(final HistoryViewHolder holder, final int position) {
        final ModelRetrieve modelRetrieve = customers.get(position);

        int RemainingDue = (modelRetrieve.getTotalRecievable() - modelRetrieve.getCashPaid());
        if (modelRetrieve.getCashBack() != 0) {
            //holder.CashBack.setText("Cash Back: "+modelRetrieve.getCashBack());
            holder.CashBack.setText("" + modelRetrieve.getCashBack());
            //holder.CashBack.setVisibility(View.VISIBLE);
        }

        System.out.println("====================================================" + modelRetrieve.getTotalRecievable());
        System.out.println("====================================================" + modelRetrieve.getCashBack());
        System.out.println("====================================================" + modelRetrieve.getCashPaid());
        System.out.println("====================================================" + modelRetrieve.getCustomerDue());
        holder.TotalShopped.setText("" + modelRetrieve.getTotalShopped());
        holder.Recievable.setText("" + modelRetrieve.getTotalRecievable());
        holder.CashPaid.setText("" + modelRetrieve.getCashPaid());
        holder.RemainingDue.setText("" + modelRetrieve.getCustomerDue());
        holder.Date.setText(modelRetrieve.getDate());

        holder.DeleteHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete.deleteSingleHistory(modelRetrieve.getCustomerHistoryID());
                removeAt(position);
            }
        });


    }

    public void removeAt(int position) {
        customers.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, customers.size());
    }

    @Override
    public int getItemCount() {
        return customers.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class HistoryViewHolder extends RecyclerView.ViewHolder {
        TextView Date, RemainingDue, CashPaid, Recievable, CashBack, DeleteHistory, TotalShopped;


        HistoryViewHolder(View itemView) {
            super(itemView);

            TotalShopped = (TextView) itemView.findViewById(R.id.TotalShopped);
            Recievable = (TextView) itemView.findViewById(R.id.Recievable);
            CashPaid = (TextView) itemView.findViewById(R.id.CashPaid);
            RemainingDue = (TextView) itemView.findViewById(R.id.RemainingDue);
            CashBack = (TextView) itemView.findViewById(R.id.CashBack);
            Date = (TextView) itemView.findViewById(R.id.Date);
            DeleteHistory = (TextView) itemView.findViewById(R.id.DeleteHistory);

        }
    }


    private void showDiscartedDialog(final int position, final int id, final int quantity) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.discart_warning_layout);
        dialog.setTitle("Are you sure ?");

        Button ConfirmYes, ConfirmNo;
        ConfirmYes = (Button) dialog.findViewById(R.id.ConfirmYes);
        ConfirmNo = (Button) dialog.findViewById(R.id.ConfirmNo);
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
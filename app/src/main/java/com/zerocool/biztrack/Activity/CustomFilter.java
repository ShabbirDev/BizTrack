package com.zerocool.biztrack.Activity;

import android.widget.Filter;

import com.zerocool.biztrack.Adapters.CustomerListAdapter;
import com.zerocool.biztrack.Models.ModelRetrieve;

import java.util.List;

/**
 * Created by Technocrats on 12-Feb-18.
 */

public class CustomFilter extends Filter {


    private List<ModelRetrieve> dictionaryWords;
    private List<ModelRetrieve> filteredList;



    private CustomerListAdapter mAdapter;
    public CustomFilter(List<ModelRetrieve> dictionaryWords) {
        super();
        this.dictionaryWords = dictionaryWords;
    }
    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        filteredList.clear();
        final FilterResults results = new FilterResults();
        if (constraint.length() == 0) {
            filteredList.addAll(dictionaryWords);
        } else {
            final String filterPattern = constraint.toString().toLowerCase().trim();
            for (final ModelRetrieve mWords : dictionaryWords) {
                if (mWords.getCustomerName().toLowerCase().startsWith(filterPattern)) {
                    filteredList.add(mWords);
                }
            }
        }
        System.out.println("Count Number " + filteredList.size());
        results.values = filteredList;
        results.count = filteredList.size();
        return results;
    }
    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        System.out.println("Count Number 2 " + ((List<ModelRetrieve>) results.values).size());
        this.mAdapter.notifyDataSetChanged();
    }
}

package com.zerocool.biztrack.Adapters;//package com.crashoverride.keeptrack.Adapters;
//
//import android.content.Context;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.crashoverride.keeptrack3.Models.ModelRetrieve;
//import com.crashoverride.keeptrack3.R;
//
//import java.util.List;
//
//
//public class HistoryAdapter extends ArrayAdapter<ModelRetrieve> {
//
//
//    private List<ModelRetrieve> surveyDataLists;
//    private Context context;
//
//    public HistoryAdapter(Context context, int resource, List<ModelRetrieve> surveyDataFromList) {
//        super(context, resource, surveyDataFromList);
//        this.context = context;
//        this.surveyDataLists = surveyDataFromList;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//        //getting the layoutinflater
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//        //getting listview itmes
//        View listViewItem = inflater.inflate(R.layout.data_listview_items, null, true);
//        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
//        ImageView imageViewStatus = (ImageView) listViewItem.findViewById(R.id.imageViewStatus);
//
//
//        textViewName.setText(String.valueOf(surveyData.getData_ID()));
//
//
//        return listViewItem;
//    }
//
//
//
//
//}
//
//

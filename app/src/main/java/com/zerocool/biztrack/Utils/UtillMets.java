package com.zerocool.biztrack.Utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;

import com.zerocool.biztrack.R;
import com.zerocool.biztrack.ViewPagerHandler.ViewPagerContainer;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;


public class UtillMets extends AppCompatActivity {


    public static String UniqueID() {
        String ts = String.valueOf(System.currentTimeMillis());
        String rand = UUID.randomUUID().toString();
        //return DigestUtils.sha1Hex(ts + rand);
        return rand;
    }


    public static int getPowerOfTwoForSampleRatio(double ratio) {
        int k = Integer.highestOneBit((int) Math.floor(ratio));
        if (k == 0)
            return 1;
        else
            return k;
    }

    public static Bitmap getThumbnail(Uri uri, Context context)
            throws FileNotFoundException, IOException {
        InputStream input = context.getContentResolver().openInputStream(uri);

        BitmapFactory.Options onlyBoundsOptions = new BitmapFactory.Options();
        onlyBoundsOptions.inJustDecodeBounds = true;
        onlyBoundsOptions.inDither = true;// optional
        onlyBoundsOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;// optional
        BitmapFactory.decodeStream(input, null, onlyBoundsOptions);
        input.close();
        if ((onlyBoundsOptions.outWidth == -1)
                || (onlyBoundsOptions.outHeight == -1))
            return null;

        int originalSize = (onlyBoundsOptions.outHeight > onlyBoundsOptions.outWidth) ? onlyBoundsOptions.outHeight
                : onlyBoundsOptions.outWidth;

        double ratio = (originalSize > 400) ? (originalSize / 350) : 1.0;

        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inSampleSize = getPowerOfTwoForSampleRatio(ratio);
        bitmapOptions.inDither = true;// optional
        bitmapOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;// optional
        input = context.getContentResolver().openInputStream(uri);
        Bitmap bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);
        input.close();
        return bitmap;
    }

    public void showFullImage(View view) {
        String path = (String) view.getTag();

        if (path != null) {

            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            Uri imgUri = Uri.parse("file://" + path);
            intent.setDataAndType(imgUri, "image/*");
            startActivity(intent);

        }

    }


    //method to convert image into String
    public static String encodeTobase64(ImageView image) {
        Bitmap bitmap1 = ((BitmapDrawable) image.getDrawable()).getBitmap();
        Bitmap bitmap2 = Bitmap.createScaledBitmap(bitmap1, 512, 512, false);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap2.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] byteArray = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
        Log.d("Image Log:", imageEncoded);

        return imageEncoded;
    }


    public static Bitmap decodeBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory
                .decodeByteArray(decodedByte, 0, decodedByte.length);
    }

    public static String getSpinnerValue(Spinner spinner) {
        String SelectedItem = spinner.getSelectedItem().toString().trim();
        return SelectedItem;
    }

    public static String getCheckboxValue(CheckBox checkBox, String checkText) {
        boolean value = checkBox.isChecked();
        if (value) {
            //checkBox.setChecked(false);
            //return checkText+" Yes";
            return checkBox.getText().toString().trim();
        } else {
            //checkBox.setChecked(false);
            return " ";
            //return checkBox.getText().toString();
        }
    }

    public static int toInteger(EditText editText) {
        String s = editText.getText().toString();
        return Integer.parseInt(s);
    }


    public static long toLong(EditText editText) {
        String s = editText.getText().toString();
        return Long.parseLong(s);
    }


    public static String getRadioButtonText(RadioGroup radioGroup, View view) {
        int selectedId = radioGroup.getCheckedRadioButtonId();
        String radioButtonText = "";

        if (selectedId != -1) {
            RadioButton selectedRadioButton = (RadioButton) view.findViewById(selectedId);
            radioButtonText = selectedRadioButton.getText().toString().trim();
            selectedRadioButton.setChecked(false);
            //selectedRadioButton.setChecked(true);
            return radioButtonText;
        } else {
            return radioButtonText;
        }


    }


    public static String getDate() {
        Calendar c = Calendar.getInstance();
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String formattedDate = df.format(c.getTime());
        return formattedDate;

    }


    public static Calendar getOneHourBack() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = null;
        try {
            date = sdf.parse(getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, -1);
//        cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + 10);
        return cal;

    }


    public static Calendar getOneDayBack() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = null;
        try {
            date = sdf.parse(getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, -1);
        return cal;

    }

    public static Calendar getLastWeek() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int i = calendar.get(Calendar.DAY_OF_WEEK) - calendar.getFirstDayOfWeek();
        calendar.add(Calendar.DATE, -i - 7);
        Date start = calendar.getTime();
        calendar.add(Calendar.DATE, 6);
        Date end = calendar.getTime();
        System.out.println(start + " - " + end);
        return calendar;

    }

//    public static Calendar getLastWeek2(){
//        final ZonedDateTime input = ZonedDateTime.now();
//        System.out.println(input);
//        final ZonedDateTime startOfLastWeek = input.minusWeeks(1).with(DayOfWeek.MONDAY);
//        System.out.println(startOfLastWeek);
//        final ZonedDateTime endOfLastWeek = startOfLastWeek.plusDays(6);
//        System.out.println(endOfLastWeek);
//        Calendar calendar = Calendar.getInstance();
////        calendar.setTime(endOfLastWeek);
//        return calendar;
//    }

    public static Calendar getLastWeekOnThisDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = null;
        try {
            date = sdf.parse(getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, -6);
        return cal;

    }


    public static Calendar getLastMonth() {
        Calendar calendar = Calendar.getInstance();
// add -1 month to current month
        calendar.add(Calendar.MONTH, -1);
// set DATE to 1, so first date of previous month
        calendar.set(Calendar.DATE, 1);

        Date firstDateOfPreviousMonth = calendar.getTime();

// set actual maximum date of previous month
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        //read it
        Date lastDateOfPreviousMonth = calendar.getTime();
        return calendar;
    }


    public static Calendar getLastMonthOnThisDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = null;
        try {
            date = sdf.parse(getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        //cal.add(Calendar.DATE, - 6);
        cal.add(Calendar.DAY_OF_MONTH, -30);
        return cal;

    }


    public Calendar getEndOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        //return calendar.getTime();
        return calendar;
    }

    public Calendar getStartOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        //return calendar.getTime();
        return calendar;
    }


    public static Calendar getQueryTime(String sdate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = null;
        try {
            date = sdf.parse(sdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
//        cal.add(Calendar.MINUTE, - 2);
//        cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + 10);
        return cal;

    }


    public static Calendar getDate2() {
        Calendar c = Calendar.getInstance();
        return c;

    }


    public static TextView createTextView(Context context) {
        TextView textView = new TextView(context);
//        textView.setLayoutParams(new TableRow.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,TableLayout.LayoutParams.WRAP_CONTENT));
        textView.setGravity(Gravity.LEFT);
        textView.setTextSize(14);
        textView.setPadding(4,4,4,4);
        textView.setTypeface(Typeface.create("serif", Typeface.NORMAL));
        //textView.setTypeface(textView.getTypeface(), Typeface.NORMAL);
        TableRow.LayoutParams params = new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        params.setMargins(5,2,2,2);
        textView.setLayoutParams(params);
        return textView;
    }


    public static TableRow createTableRow(Context context) {
//        TextView textView = new TextView(context);
//        textView.setLayoutParams(new TableRow.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,TableLayout.LayoutParams.WRAP_CONTENT));
        TableRow tableRow = new TableRow(context);
        tableRow.setGravity(Gravity.CENTER_HORIZONTAL);
        tableRow.setPadding(0,0,0,0);
        //textView.setTypeface(textView.getTypeface(), Typeface.NORMAL);
        TableRow.LayoutParams params = new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        params.setMargins(0,0,0,0);
        tableRow.setLayoutParams(params);
        //textView.setWe

        return tableRow;
    }




    public static CheckBox createCheckBox(Context context) {
        CheckBox checkBox = new CheckBox(context);
        checkBox.setGravity(Gravity.RIGHT);
//        checkBox.setTextSize(10);
//        checkBox.setTypeface(checkBox.getTypeface(), Typeface.ITALIC);
        TableRow.LayoutParams params = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        checkBox.setLayoutParams(params);

        return checkBox;
    }


    public static View createLine(Context context){
        View view = new View(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,1);
        layoutParams.setMargins(0,0,0,0);
        view.setLayoutParams(layoutParams);
        //view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 3));
        view.setBackgroundColor(Color.parseColor("#d4d9de"));
        return view;
    }


public static View createVerticLine(Context context){
//    View v = new View(context);
//    v.setLayoutParams(new TableRow.LayoutParams(1, TableRow.LayoutParams.MATCH_PARENT));
//    v.setBackgroundColor(Color.parseColor("#d4d9de"));
//    return v;

    View view = new View(context);
    TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(1, TableRow.LayoutParams.MATCH_PARENT);
    layoutParams.setMargins(0,0,0,0);
    view.setLayoutParams(layoutParams);
    //view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 3));
    view.setBackgroundColor(Color.parseColor("#d4d9de"));
    return view;


}



//    public static String getRadioButtonText(RadioGroup radioGroup, View view){
//        int selectedId = radioGroup.getCheckedRadioButtonId();
//        String radioButtonText = null;
//
//        if(selectedId != -1) {
//            // find the radiobutton by returned id
//            RadioButton selectedRadioButton = (RadioButton) view.findViewById(selectedId);
//            // do what you want with radioButtonText (save it to database in your case)
//            radioButtonText = selectedRadioButton.getText().toString().trim();
////            selectedRadioButton.setChecked(false);
//            selectedRadioButton.setChecked(true);
//
//        }
//        return radioButtonText;
//
////        String value = ((RadioButton) radioGroup.findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();
////        return value;
//
//    }

    public static String A(RadioGroup radioGroup, View view) {
        RadioButton radioButton = (RadioButton) view.findViewById(radioGroup.getCheckedRadioButtonId());
        return radioButton.getText().toString();

        //String buisnesstype = ((RadioButton) rdtranscompany.findViewById(rdtranscompany.getCheckedRadioButtonId())).getText().toString();

    }

    public static void clickOnCheckbox(final CheckBox checkBox, final EditText editText) {

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkBox.isChecked()) {
                    editText.setVisibility(View.VISIBLE);
                } else {
                    editText.setVisibility(View.GONE);
                }
            }

        });
    }

    //method to convert image into byte array aka BLOB
    public byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        return byteArray;
    }

    private void replaceFragment(View view) {
        Fragment fragment = new ViewPagerContainer();
        FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_content, fragment);
        ft.commit();

    }


    public static int stringToInt(String var) {
        //String s = editText.getText().toString();
        return Integer.parseInt(var);
    }

    public static String integerToString(int var) {

        return String.valueOf(var);
    }


    public static void buttonEffect(View button){
        button.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.getBackground().setColorFilter(0xe0f47521, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                        break;
                    }
                }
                return false;
            }
        });
    }

}

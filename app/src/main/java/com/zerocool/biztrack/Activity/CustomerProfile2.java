package com.zerocool.biztrack.Activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.PhoneNumberUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.zerocool.biztrack.DatabaseOperation.Retrieve;
import com.zerocool.biztrack.DatabaseOperation.Save;
import com.zerocool.biztrack.DatabaseOperation.Update;
import com.zerocool.biztrack.Fragments.CustomerHistory2;
import com.zerocool.biztrack.Models.ModelDelete;
import com.zerocool.biztrack.Models.ModelRetrieve;
import com.zerocool.biztrack.Models.ModelSave;
import com.zerocool.biztrack.Models.ModelUpdate;
import com.zerocool.biztrack.R;
import com.zerocool.biztrack.Utils.UtillMets;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.zerocool.biztrack.Utils.UtillMets.decodeBase64;
import static com.zerocool.biztrack.Utils.UtillMets.getDate;
import static com.zerocool.biztrack.Utils.UtillMets.toInteger;

/**
 * Created by CrashOverride on 1/5/2018.
 */

public class CustomerProfile2 extends AppCompatActivity {


    ModelUpdate modelUpdate = new ModelUpdate();
    Update update = new Update();
    ModelDelete modelDelete = new ModelDelete();
    ModelSave modelSave = new ModelSave();
    UtillMets utillMets = new UtillMets();
    Save save = new Save();

    TextView CustomerName, PhoneNumber, Address,TotalShopped1,CashPaid1, PreviousDue;
    CircleImageView CustomerImage;
    ImageButton DialNumber;
    Toolbar ProfileToolbar;
    //    CheckBox CashEntry, CashBackEntry;
    RadioGroup PaymentType;

    Button History;
    String UID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.customer_profile_items);
        setContentView(R.layout.profile2);
        Initialize();

        setSupportActionBar(ProfileToolbar);
        ProfileToolbar.setTitle("Customer Profile");
////        ProfileToolbar.setNavigationIcon(R.drawable.ic_go_back);
////        ProfileToolbar.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                System.out.println("=========================================clicked on profile navigation");
////                Intent intent = new Intent(CustomerProfile2.this, MainActivity.class);
////                startActivity(intent);
////            }
////        });
//
//        //ProfileToolbar.setLogoDescription(getResources().getString(R.string.logo_desc));


        Intent intent = getIntent();
        //CustomerID = intent.getIntExtra("CustomerID", 0);
//        Phone =  intent.getStringExtra("Phone");
       UID =  intent.getStringExtra("UID");


//        final ModelRetrieve modelRetrieve = Retrieve.ProfileDetails(CustomerID);
        final ModelRetrieve modelRetrieve = Retrieve.ProfileDetails(UID);
        if (modelRetrieve.getCustomerDue() > 0) {
            findViewById(R.id.DuePayment).setVisibility(View.VISIBLE);
        }

        CustomerName.setText(""+modelRetrieve.getCustomerName());
        TotalShopped1.setText(""+modelRetrieve.getTotalShopped());
        CashPaid1.setText(""+modelRetrieve.getCashPaid());
        CustomerImage.setImageBitmap(decodeBase64(modelRetrieve.getCustomerImage()));
        PhoneNumber.setText(PhoneNumberUtils.formatNumber(modelRetrieve.getPhoneNumber()));
        Address.setText("" +modelRetrieve.getAddress());
        PreviousDue.setText("" +modelRetrieve.getCustomerDue());
        //System.out.println("====================CUSTOMER PROFILE=============Previous Due: "+modelRetrieve.getCustomerDue());

        PaymentType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.CashPayment:
//                        saveCash(Phone);
                        saveCash(UID);
                        break;
                    case R.id.CashBack:
                        saveCashback(UID);
                        break;
                    case R.id.DuePayment:
                        saveDue(UID);
                        break;
                }
            }
        });

        CustomerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                utillMets.showFullImage(CustomerImage);
            }
        });



        PhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent call = new Intent(Intent.ACTION_CALL);
                call.setData(Uri.parse("tel:"+modelRetrieve.getPhoneNumber()));
                if (ActivityCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(call);

            }
        });




        History.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Intent intent1 = new Intent(CustomerProfile2.this, CustomerHistory.class);
//
//                intent1.putExtra("UID",UID);
//                startActivity(intent1);

                Fragment fragment = new CustomerHistory2();
//                Fragment fragment = new AllProductList();
                Bundle args = new Bundle();
                args.putString("UID", UID);
                fragment.setArguments(args);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_content, fragment).addToBackStack(null)
                        .commit();

            }
        });


    }

    private void saveCash(final String UIID) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.new_payment_layout);
        dialog.setTitle("Add Payments");
        final EditText TotalShopped, CashPaid;
        Button SaveCash;
        //final ModelRetrieve modelRetrieve = Retrieve.getCustomerPaymentHistory(phone);
        final ModelRetrieve modelRetrieve = Retrieve.ProfileDetails(UIID);

        //System.out.println("=================================profile 1: phone number:  "+modelRetrieve.getPhoneNumber());
        //System.out.println("=================================profile 1: due:  "+modelRetrieve.getCustomerDue());

        TotalShopped = (EditText) dialog.findViewById(R.id.TotalShopped);
        CashPaid = (EditText) dialog.findViewById(R.id.CashPaid);
        SaveCash = (Button) dialog.findViewById(R.id.SaveCash);


        dialog.show();

        SaveCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int NewRecievable = modelRetrieve.getCustomerDue() + toInteger(TotalShopped);
                int NewDue = (NewRecievable - toInteger(CashPaid));
                modelUpdate.setCustomerUID(UIID);
                modelUpdate.setTotalShopped(modelRetrieve.getTotalShopped() + toInteger(TotalShopped));
                modelUpdate.setCashPaid(modelRetrieve.getCashPaid() + toInteger(CashPaid));
                modelUpdate.setCustomerDue(NewDue);
                modelUpdate.setDate(getDate());


                PreviousDue.setText("" + NewDue);
                TotalShopped1.setText(""+(modelRetrieve.getTotalShopped() + toInteger(TotalShopped)));
                CashPaid1.setText(""+(modelRetrieve.getCashPaid() + toInteger(CashPaid)));

                update.CustomerDue(modelUpdate);


                modelSave.setCustomerUID(UIID);
                modelSave.setPhoneNumber(modelRetrieve.getPhoneNumber());
                modelSave.setTotalShopped(toInteger(TotalShopped));
                modelSave.setTotalRecievable(NewRecievable);
                modelSave.setCashPaid(toInteger(CashPaid));
                modelSave.setCashBack(0);
                modelSave.setCustomerDue(NewDue);
                modelSave.setDate(getDate());

                Save.CustomerHistory(modelSave);

                dialog.dismiss();


            }
        });


    }

    private void saveCashback(final String UIID) {
        //final ModelRetrieve modelRetrieve = Retrieve.ProfileDetails(CustomerID);
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.due_payment_layout);
        dialog.setTitle("Cash Back ");
        final EditText CashBacked;
        Button SaveCashBack;

        CashBacked = (EditText) dialog.findViewById(R.id.CashBacked);
        CashBacked.setHint("Enter Amount");
        SaveCashBack = (Button) dialog.findViewById(R.id.SaveCashBack);


        dialog.show();

        SaveCashBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                ModelRetrieve modelRetrieve = Retrieve.getCustomerPaymentHistory(phone);
                ModelRetrieve modelRetrieve = Retrieve.ProfileDetails(UIID);

                if (modelRetrieve.getCustomerDue() > 0) {//this customer has previous due
                    int NewRecievable = modelRetrieve.getCustomerDue() - toInteger(CashBacked);
                    int NewTotalShopped = modelRetrieve.getTotalShopped() - toInteger(CashBacked);

                    modelUpdate.setCustomerUID(UIID);
                    modelUpdate.setTotalShopped(NewTotalShopped);
                    modelUpdate.setCashPaid(modelRetrieve.getCashPaid() + 0);
                    modelUpdate.setCustomerDue(NewRecievable);
                    modelUpdate.setPhoneNumber(modelRetrieve.getPhoneNumber());
                    modelUpdate.setDate(getDate());

                    update.CustomerDue(modelUpdate);
                    PreviousDue.setText(""+NewRecievable);
                    TotalShopped1.setText(""+NewTotalShopped);
                    CashPaid1.setText(""+(modelRetrieve.getCashPaid() + 0));


                    modelSave.setCustomerUID(UIID);
                    modelSave.setPhoneNumber(modelRetrieve.getPhoneNumber());
                    modelSave.setTotalShopped(0);
                    //modelSave.setTotalRecievable(NewRecievable);
                    modelSave.setTotalRecievable(modelRetrieve.getCustomerDue());
                    modelSave.setCashPaid(0);
                    modelSave.setCashBack(toInteger(CashBacked));
                    modelSave.setCustomerDue(NewRecievable);
                    modelSave.setDate(getDate());

                    Save.CustomerHistory(modelSave);


                } else {//customer has no due remaining

                    modelUpdate.setCustomerUID(UIID);
                    modelUpdate.setTotalShopped(modelRetrieve.getTotalShopped() - toInteger(CashBacked));
                    modelUpdate.setCashPaid(modelRetrieve.getCashPaid() - toInteger(CashBacked));
                    modelUpdate.setCustomerDue(modelRetrieve.getCustomerDue());
                    modelUpdate.setPhoneNumber(modelRetrieve.getPhoneNumber());
                    modelUpdate.setDate(getDate());

                    update.CustomerDue(modelUpdate);

                    TotalShopped1.setText(""+(modelRetrieve.getTotalShopped() - toInteger(CashBacked)));
                    CashPaid1.setText(""+(modelRetrieve.getCashPaid() - toInteger(CashBacked)));
                    PreviousDue.setText(""+modelRetrieve.getCustomerDue());


                    modelSave.setCustomerUID(UIID);
                    modelSave.setPhoneNumber(modelRetrieve.getPhoneNumber());
//                    modelSave.setTotalRecievable(NewRecievable);
//                    modelSave.setCashPaid(modelRetrieve.getCashPaid() - toInteger(CashBacked));
                    modelSave.setTotalShopped(0);
                    modelSave.setTotalRecievable(0);
                    modelSave.setCashPaid(0);
                    modelSave.setCashBack(toInteger(CashBacked));
                    modelSave.setCustomerDue(0);
                    modelSave.setDate(getDate());

                    Save.CustomerHistory(modelSave);

                }



                dialog.dismiss();


            }
        });


    }

    private void saveDue(final String UIID) {

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.due_payment_layout);
        dialog.setTitle("Due Payment");
        final EditText DuePaid;
        Button SaveDue;

        DuePaid = (EditText) dialog.findViewById(R.id.CashBacked);
        DuePaid.setHint("Enter Amount");
        SaveDue = (Button) dialog.findViewById(R.id.SaveCashBack);


        dialog.show();

        SaveDue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ModelRetrieve customerProfile = Retrieve.ProfileDetails(UIID);
                ModelRetrieve customerHistory = Retrieve.getCustomerPaymentHistory(UIID);

                int NewDue = customerProfile.getCustomerDue() - toInteger(DuePaid);

                modelUpdate.setCustomerUID(UIID);
                modelUpdate.setTotalShopped(customerProfile.getTotalShopped() + 0);
                modelUpdate.setCashPaid(customerProfile.getCashPaid() + toInteger(DuePaid));
                modelUpdate.setCustomerDue(NewDue);
                modelUpdate.setPhoneNumber(customerProfile.getPhoneNumber());
                modelUpdate.setDate(getDate());

                TotalShopped1.setText(""+(customerProfile.getTotalShopped() + 0));
                CashPaid1.setText(""+(customerProfile.getCashPaid() + toInteger(DuePaid)));
                PreviousDue.setText(""+NewDue);

                update.CustomerDue(modelUpdate);

//                System.out.println("============================================ PHONE NUMBER  "+Phone);
                modelSave.setCustomerUID(UIID);
                modelSave.setPhoneNumber(customerHistory.getPhoneNumber());
                modelSave.setTotalRecievable(customerHistory.getCustomerDue());
                modelSave.setCashPaid(toInteger(DuePaid));
                modelSave.setCashBack(0);
                modelSave.setCustomerDue(customerHistory.getCustomerDue() - toInteger(DuePaid));
                modelSave.setDate(getDate());

                Save.CustomerHistory(modelSave);


                dialog.dismiss();


            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.customer_profile_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_edit){

            Intent intent = new Intent(this, EditCustomerProfile.class);
            //intent.putExtra("CustomerID",CustomerID);
            intent.putExtra("UID",UID);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    private void Initialize() {

        CustomerName = (TextView) findViewById(R.id.CustomerName);
        CustomerImage = (CircleImageView) findViewById(R.id.CustomerImage);
        PhoneNumber = (TextView) findViewById(R.id.PhoneNumber);
        Address = (TextView) findViewById(R.id.Address);
        TotalShopped1 = (TextView) findViewById(R.id.TotalShopped);
        CashPaid1 = (TextView) findViewById(R.id.CashPaid);
        PreviousDue = (TextView) findViewById(R.id.PreviousDue);
        PaymentType = (RadioGroup) findViewById(R.id.PaymentType);

        History = (Button) findViewById(R.id.CustomerHistory);
        ProfileToolbar = (Toolbar) findViewById(R.id.ProfileToolbar);

    }

}
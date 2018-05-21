package com.zerocool.biztrack.Activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zerocool.biztrack.DatabaseOperation.Delete;
import com.zerocool.biztrack.DatabaseOperation.Retrieve;
import com.zerocool.biztrack.DatabaseOperation.Save;
import com.zerocool.biztrack.DatabaseOperation.Update;
import com.zerocool.biztrack.Models.ModelDelete;
import com.zerocool.biztrack.Models.ModelRetrieve;
import com.zerocool.biztrack.Models.ModelSave;
import com.zerocool.biztrack.Models.ModelUpdate;
import com.zerocool.biztrack.R;
import com.zerocool.biztrack.Utils.CustomEditText;

import java.io.FileNotFoundException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.zerocool.biztrack.Utils.UtillMets.decodeBase64;
import static com.zerocool.biztrack.Utils.UtillMets.encodeTobase64;

/**
 * Created by CrashOverride on 1/5/2018.
 */

public class EditCustomerProfile extends AppCompatActivity {


    ModelUpdate modelUpdate = new ModelUpdate();
    Update update = new Update();
    ModelDelete modelDelete = new ModelDelete();
    Delete delete = new Delete();
    ModelSave modelSave = new ModelSave();
    Save save = new Save();
    ModelRetrieve modelRetrieve = new ModelRetrieve();

//    EditText CustomerName, PhoneNumber, Address, PreviousDue,TotalShopped, CashPaid;

    CustomEditText CustomerName, PhoneNumber, Address, PreviousDue, TotalShopped, CashPaid;
//    AppCompatImageView CustomerImage;
    Button CustomerHistory, Submit;
    String UID;
    CircleImageView CustomerImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_customer_profile_);
        Initialize();
        Intent intent = getIntent();
        UID = intent.getStringExtra("UID");


        final ModelRetrieve modelRetrieve = Retrieve.ProfileDetails(UID);

        CustomerName.setText(modelRetrieve.getCustomerName());
        CustomerImage.setImageBitmap(decodeBase64(modelRetrieve.getCustomerImage()));
        PhoneNumber.setText(PhoneNumberUtils.formatNumber("" + modelRetrieve.getPhoneNumber()));
        Address.setText("" + modelRetrieve.getAddress());
//        PreviousDue.setText("Previous Due: "+modelRetrieve.getCustomerDue());
//        TotalShopped.setText("Total Recievable: "+modelRetrieve.getTotalRecievable());
//        CashPaid.setText("Cash Paid: "+modelRetrieve.getCashPaid());

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                confirmProfileEdit(update);

                modelUpdate.setCustomerUID(UID);
                modelUpdate.setCustomerName(CustomerName.getText().toString());
                modelUpdate.setCustomerImage(encodeTobase64(CustomerImage));
                modelUpdate.setPhoneNumber(PhoneNumber.getText().toString());
                modelUpdate.setAddress(Address.getText().toString());

                confirmProfileEdit(modelUpdate);
                //update.updateCustomerProfile(modelUpdate);

            }
        });


        CustomerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePhotoDialog();
            }
        });


    }

//    void setDrawable(EditText editText, int resource){
//        Drawable img = getApplicationContext().getResources().getDrawable(resource);
//        img.setBounds( 0, 0, 60, 0 );
//        editText.setCompoundDrawables( img, null, null, null );
//    }


    private void takePhotoDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.take_photo_layout);
        dialog.setTitle("Add Photo");

        Button PhotoFromGallery, PhotoFromCamera;


        PhotoFromGallery = (Button) dialog.findViewById(R.id.PhotoFromGallery);
        PhotoFromCamera = (Button) dialog.findViewById(R.id.PhotoFromCamera);


        dialog.show();

        PhotoFromGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                permitMe(1);
                dialog.dismiss();

            }
        });


        PhotoFromCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                permitMe(2);
                dialog.dismiss();
            }
        });


    }


    private void permitMe(int requestcode) {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, requestcode);
    }


    private void openImageChooser(Intent intent, int code) {

        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, code);

    }

    private void openCamera(int code) {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, code);


//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
//        CustomerRegistration.this.startActivityForResult(intent, code);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK && data != null) {
            try {

                switch (requestCode) {
                    case (1): {
                        Uri uri = data.getData();
                        InputStream inputStream = this.getContentResolver().openInputStream(uri);
                        final BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inSampleSize = 8;
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, options);
                        CustomerImage.setImageBitmap(bitmap);
                        break;
                    }
                    case (2):
                        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                        CustomerImage.setImageBitmap(bitmap);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // final Context context;
        //context = getContext();
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent();
            switch (requestCode) {
                case (1):
                    openImageChooser(intent, requestCode);
                    break;
                case (2):
                    openCamera(requestCode);
                    break;

            }
        } else {
            Toast.makeText(this, "You don't have permission to access file ", Toast.LENGTH_SHORT).show();
        }


        //return;

    }


    private void confirmProfileEdit(final ModelUpdate modelUpdate) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.confirm_edit_customer_profile_);
        dialog.setTitle("Are you sure ?");

        final TextView CustomerName, PhoneNumber, Address;
        Button ConfirmYes, ConfirmNo;
//        final ImageView CustomerImage;
        CircleImageView CustomerImage;

        CustomerName = (TextView) dialog.findViewById(R.id.CustomerName);
        CustomerImage = (CircleImageView) dialog.findViewById(R.id.CustomerImage);
        PhoneNumber = (TextView) dialog.findViewById(R.id.PhoneNumber);
        Address = (TextView) dialog.findViewById(R.id.Address);

        ConfirmYes = (Button) dialog.findViewById(R.id.ConfirmYes);
        ConfirmNo = (Button) dialog.findViewById(R.id.ConfirmNo);


        CustomerName.setText("" + modelUpdate.getCustomerName());
        CustomerImage.setImageBitmap(decodeBase64(modelUpdate.getCustomerImage()));
        PhoneNumber.setText("" + modelUpdate.getPhoneNumber());
        Address.setText("" + modelUpdate.getAddress());

        dialog.show();

        ConfirmYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                modelUpdate.setCustomerUID(modelUpdate.getCustomerID());
                modelUpdate.setCustomerUID(UID);
                modelUpdate.setCustomerName(modelUpdate.getCustomerName());
                modelUpdate.setCustomerImage(modelUpdate.getCustomerImage());
                modelUpdate.setPhoneNumber(modelUpdate.getPhoneNumber());
                modelUpdate.setAddress(modelUpdate.getAddress());


                update.updateCustomerProfile(modelUpdate);
                //update.ProfileHistory(modelUpdate);

                Intent intent = new Intent(EditCustomerProfile.this, CustomerProfile.class);
//                intent.putExtra("Phone", modelUpdate.getPhoneNumber());
                intent.putExtra("UID", UID);
                startActivity(intent);
                System.out.println("=========================================PhoneNumber 1:=========" + modelUpdate.getPhoneNumber());
                System.out.println("=========================================PhoneNumber 2:=========" + modelUpdate.getCustomerID());
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

//    private void saveDue(){
//        final Dialog dialog = new Dialog(this);
//        dialog.setContentView(R.layout.due_payment_layout);
//        dialog.setTitle("Add Payments");
//
//
//        CashBacked = dialog.findViewById(R.id.CashBacked);
//        SaveDue = dialog.findViewById(R.id.SaveDue);
//
//
//
//        dialog.show();
//
//        SaveDue.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int RemainingDue = modelRetrieve.getCustomerDue() - toInteger(CashBacked);
//                modelUpdate.setCustomerDue(RemainingDue);
//
//                modelSave.setPhoneNumber(modelRetrieve.getPhoneNumber());
//                modelSave.setTotalRecievable(modelRetrieve.getCustomerDue());
//                modelSave.setCashPaid(toInteger(CashPaid));
//                modelSave.setCustomerDue(RemainingDue);
//                modelSave.setDate(getDate());
//
//                save.History(modelSave);
//
//                dialog.dismiss();
//
//
//            }
//        });
//
//
//
//
//
//    }


    private void Initialize() {

        CustomerName = (CustomEditText) findViewById(R.id.CustomerName);
        CustomerImage = (CircleImageView) findViewById(R.id.CustomerImage);
        PhoneNumber = (CustomEditText) findViewById(R.id.PhoneNumber);
        Address = (CustomEditText) findViewById(R.id.Address);
        Submit = (Button) findViewById(R.id.Submit);

    }

}
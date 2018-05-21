package com.zerocool.biztrack.Activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zerocool.biztrack.DatabaseOperation.Retrieve;
import com.zerocool.biztrack.DatabaseOperation.Save;
import com.zerocool.biztrack.Models.ModelSave;
import com.zerocool.biztrack.R;
import com.zerocool.biztrack.Utils.CustomEditText;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;
import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO;
import static com.zerocool.biztrack.DatabaseOperation.Retrieve.isCustomerExists;
import static com.zerocool.biztrack.Utils.UtillMets.UniqueID;
import static com.zerocool.biztrack.Utils.UtillMets.encodeTobase64;
import static com.zerocool.biztrack.Utils.UtillMets.getDate;

//import com.zerocool.biztrack.Activity.CustomerProfile;

/**
 * Created by CrashOverride on 1/12/2018.
 */

public class CustomerRegistration extends AppCompatActivity {

    private static final String IMAGE_DIRECTORY_NAME = "KeepTrack";
    //    EditText CustomerName, PhoneNumber, Address;
    CustomEditText CustomerName, PhoneNumber, Address;
    CircleImageView CustomerImage;
    Button CreateCustomerPrifile;


    ModelSave modelSave = new ModelSave();
    Save save = new Save();
    Retrieve retrieve = new Retrieve();
    Uri fileUri;
    String NumberPrefix = "+880";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_registration_layout);
        Initialize();

//        CustomerImage.setImageResource(R.drawable.ic_orion_user);
////        CustomerImage.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_orion_user));
////        CustomerImage.setImageDrawable(R.drawable.ic_orion_user);
////        modelSave.setCustomerImage(encodeTobase64(R.drawable.ic_orion_user));
//
//
////        PhoneNumber.setText(NumberPrefix);
////        Selection.setSelection(PhoneNumber.getText(), PhoneNumber.getText().length());
////
////        PhoneNumber.addTextChangedListener(new TextWatcher() {
////            @Override
////            public void onTextChanged(CharSequence s, int start, int before, int count) {
////                // TODO Auto-generated method stub
////
////            }
////
////            @Override
////            public void beforeTextChanged(CharSequence s, int start, int count,
////                                          int after) {
////                // TODO Auto-generated method stub
////
////            }
////            @Override
////            public void afterTextChanged(Editable s) {
////                if(!s.toString().startsWith(NumberPrefix)){
////                    PhoneNumber.setText(NumberPrefix);
////                    Selection.setSelection(PhoneNumber.getText(), PhoneNumber.getText().length());
////
////                }
////
////            }
////        });
//
//        Bitmap icon = BitmapFactory.decodeResource(this.getResources(),
//                R.drawable.ic_orion_user);
//        CustomerImage.setImageBitmap(icon);

        CreateCustomerPrifile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {


                    if (!checkIfEmpty(CustomerName) && !checkIfEmpty(PhoneNumber) && !checkIfEmpty(Address)) {
                        if (!isCustomerExists(PhoneNumber.getText().toString())) {
//                            modelSave.setPhoneNumber(PhoneNumber.getText().toString());
                            modelSave.setCustomerUID(UniqueID());
                            modelSave.setCustomerName(CustomerName.getText().toString());
                            modelSave.setCustomerImage(encodeTobase64(CustomerImage));
                            modelSave.setPhoneNumber(PhoneNumber.getText().toString());
                            modelSave.setAddress(Address.getText().toString());
                            modelSave.setTotalShopped(0);
                            modelSave.setCashPaid(0);
                            modelSave.setCustomerDue(0);
                            modelSave.setDate(getDate());

//                            Save.CustomerProfile(modelSave);
//                            Save.CustomerHistory(modelSave);

//                            String Phone = PhoneNumber.getText().toString();
                            String UID = save.CustomerProfile(modelSave);

                            Intent intent = new Intent(CustomerRegistration.this, CustomerProfile.class);
//                            intent.putExtra("Phone", Phone);
                            intent.putExtra("UID", UID);
                            startActivity(intent);

                            CustomerName.setText("");
                            //CustomerImage.setImageDrawable(getDrawable(R.drawable.customer_avatar));
                            //CustomerImage.setBackgroundResource(R.drawable.customer_avatar);
                            PhoneNumber.setText("");
                            Address.setText("");
                            System.out.println("==============================Regis UID   " + UID);

                        } else {
                            PhoneNumber.setError("A customer with this phone number already exists");
                            //SnackMessage("Already Exists");
                        }

                    } else {
                        //showToast("fields are empty");
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });


        CustomerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });


    }


    private boolean checkIfEmpty(EditText editText) {
        String s = editText.getText().toString();
        if (s.isEmpty()) {
            editText.setError("This field can not be empty");
            //showToast("Field");
            return true;
        } else
            return false;
    }


    private void customerReg() {

        modelSave.setCustomerName(CustomerName.getText().toString());
        modelSave.setCustomerImage(encodeTobase64(CustomerImage));
        modelSave.setPhoneNumber(PhoneNumber.getText().toString());
        modelSave.setAddress(Address.getText().toString());
        modelSave.setTotalShopped(0);
        modelSave.setCashPaid(0);
        modelSave.setCustomerDue(0);
        modelSave.setDate(getDate());

        save.CustomerProfile(modelSave);

        CustomerName.setText("");
        CustomerImage.setImageResource(R.drawable.customer_avatar);
        PhoneNumber.setText("");
        Address.setText("");



    }


    private void showToast(String message) {
        Toast.makeText(this, message + " Can not be empty", Toast.LENGTH_SHORT).show();
    }


    private void SnackMessage(String message) {
        View parentLayout = findViewById(android.R.id.content);
        Snackbar.make(parentLayout, message, Snackbar.LENGTH_LONG).show();
    }

    void Initialize() {

        CustomerName = (CustomEditText) findViewById(R.id.CustomerName);
        PhoneNumber = (CustomEditText) findViewById(R.id.PhoneNumber);
        Address = (CustomEditText) findViewById(R.id.Address);
        //TotalShopped = rootView.findViewById(R.id.TotalShopped);
        //CashPaid = rootView.findViewById(R.id.CashPaid);
        CustomerImage = (CircleImageView) findViewById(R.id.CustomerImage);
        CreateCustomerPrifile = (Button) findViewById(R.id.CreateCustomerPrifile);

    }

    void setDrawable(EditText editText, int resource) {
        Drawable img = getApplicationContext().getResources().getDrawable(resource);
        img.setBounds(0, 0, 60, 0);
        editText.setCompoundDrawables(img, null, null, null);
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
                        InputStream inputStream = getContentResolver().openInputStream(uri);
                        final BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inSampleSize = 24;
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


    private void showDialog() {
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


    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }


    @TargetApi(Build.VERSION_CODES.N)
    private static File getOutputMediaFile(int type) {

        // External sdcard location
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                IMAGE_DIRECTORY_NAME);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create "
                        + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + timeStamp + ".jpg");
        } else if (type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "VID_" + timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }


    private void previewCapturedImage() {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 8;
            final Bitmap bitmap = BitmapFactory.decodeFile(fileUri.getPath(),
                    options);

            CustomerImage.setImageBitmap(bitmap);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }


}
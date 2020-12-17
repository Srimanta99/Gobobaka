package com.pupportweb.gobobakapartner;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.bumptech.glide.Glide;
import com.nguyenhoanglam.imagepicker.model.Config;
import com.nguyenhoanglam.imagepicker.ui.imagepicker.ImagePicker;
import com.pupportweb.gobobakapartner.Config.BaseURL;
import com.pupportweb.gobobakapartner.Dashboard.LoginActivity;
import com.pupportweb.gobobakapartner.retrofit.RetrofitBuilder;
import com.pupportweb.gobobakapartner.retrofitModel.StoreRegistrationResponse;
import com.pupportweb.gobobakapartner.util.AppPermission;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.picasso.Picasso;

import okhttp3.MediaType;




import retrofit2.http.Multipart;

public class Register_screen4 extends AppCompatActivity {
    TextView imagename;
    String ImageName;
    int img_selected;
    Uri selectedImage;
    Button bUploadImage, bRegister;
    private  Uri filePathVendor;
    String token="";
    String image="";
    Bitmap bitmap;
    String TAG="";
    ImageView aadhar,gst,bank;
    TextView txt_aadhar,txt_imag_gst,txt_imag_bank;
    Button btnaadhar,btngst,btnbank,send_register_req;
    String click;

    String sellername,selleremail,sellermobile,sellerpass,sellercity,selleraddress,sellerStorename;

    ProgressDialog progressDialog;


    int GALLERY_REQUEST_CODE = 101;
    com.nguyenhoanglam.imagepicker.model.Image imageAdhar,imageGst,imageBank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen4);
        aadhar=findViewById(R.id.img_register_aadhar);
        gst=findViewById(R.id.img_register_gst);
        bank=findViewById(R.id.img_register_bank);

        btnaadhar=findViewById(R.id.btn_aadhar_upload);
        btngst=findViewById(R.id.btn_gst);
        btnbank=findViewById(R.id.btn_bank_detail);
        send_register_req=findViewById(R.id.send_register_req);

        txt_aadhar=findViewById(R.id.text_img_addhar);
        txt_imag_gst=findViewById(R.id.txt_img_gst);
        txt_imag_bank=findViewById(R.id.txt_img_bank);

        Intent intent = getIntent();
        if (intent.hasExtra("name") &&
                intent.hasExtra("email") &&
                intent.hasExtra("mobile") )
        {

                sellername = intent.getStringExtra("name");
                selleremail = intent.getStringExtra("email");
                sellermobile = intent.getStringExtra("mobile");
                sellerpass = intent.getStringExtra("password");
                selleraddress = intent.getStringExtra("address");
                sellercity = intent.getStringExtra("city");
                sellerStorename = intent.getStringExtra("storeName");

        }

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading");
        progressDialog.setCancelable(false);



        onclick();

    }
    public void onclick()
    {
        btnaadhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AppPermission.isCameraWithStoragePermissionGranted(Register_screen4.this)){
                    Log.e("Permission", "Permission is granted");
                  click="aadhar";
                    //imageaadharUploadDailog();
                    pickFromGallery();

                }
                else
                {
                    Toast.makeText(Register_screen4.this, "Please Provide Permission to Continue", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btngst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AppPermission.isCameraWithStoragePermissionGranted(Register_screen4.this)){
                    Log.e("Permission", "Permission is granted");
                    click="gst";

                    //imagegstUploadDailog();
                    pickFromGallery();


                }
                else
                {
                    Toast.makeText(Register_screen4.this, "Please Provide Permission to Continue", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnbank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AppPermission.isCameraWithStoragePermissionGranted(Register_screen4.this)){
                    Log.e("Permission", "Permission is granted");
                    click="bank";

                    //imagebankUploadDailog();
                    pickFromGallery();

                }
                else
                {
                    Toast.makeText(Register_screen4.this, "Please Provide Permission to Continue", Toast.LENGTH_SHORT).show();
                }
            }
        });

//        send_register_req.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String tag_json_obj = "json_login_req";
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("employee_name", sellername);
//                params.put("email", selleremail);
//                params.put("phone_number", sellermobile);
//                params.put("password", sellerpass);
//                params.put("device_id", getUniqueIMEIId(getApplicationContext()));
//                params.put("address", selleraddress);
//                params.put("address_proof", " ");
//                params.put("business_doc", " ");
//                params.put("bank_detail", " ");
//                params.put("city", sellercity);
//                params.put("lat", " ");
//                params.put("long", " ");
//
//               CustomVolleyJsonRequest jsonObjectRequest = new CustomVolleyJsonRequest(Request.Method.POST, BaseURL.Store_Resgister, params, new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        Log.d("Tag", response.toString());
//
//                        try {
//                            progressDialog.dismiss();
//                            String status = response.getString("status");
//                            String message = response.getString("message");
//
////                            if (status.contains("1")) {
////
////                                JSONArray jsonArray = response.getJSONArray("data");
////                                for (int i = 0; i < jsonArray.length(); i++) {
////
////                                    JSONObject obj = jsonArray.getJSONObject(i);
////
////                                    String user_id = obj.getString("store_id");
////                                    SharedPreferences.Editor editor = getSharedPreferences("logindata", MODE_PRIVATE).edit();
////                                    editor.putString("id", user_id);
////                                    editor.apply();
////                                    String user_fullname = obj.getString("employee_name");
////                                    String user_email = obj.getString("email");
////                                    String user_phone = obj.getString("phone_number");
////                                    String password = obj.getString("password");
////                                    Session_management sessionManagement = new Session_management(Register_screen4.this);
////                                    sessionManagement.createLoginSession(user_id, user_email, user_fullname, user_phone, "", "", "", "", password);
////                                    send_register_req.setEnabled(false);
////                                    Intent i1 = new Intent(Register_screen4.this, MainActivity.class);
////                                    i1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
////                                    startActivity(i1);
////                                    finish();
//////                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//////                                    finishAndRemoveTask();
//////                                }else {
//////                                    finish();
//////                                }
////
////                                }
////                            }
////                            else {
//                                progressDialog.dismiss();
////                        String error = response.getString("error");
//                                Toast.makeText(Register_screen4.this, "" + message, Toast.LENGTH_SHORT).show();
//                                send_register_req.setEnabled(true);
//
////                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        VolleyLog.d(TAG, "Error: " + error.getMessage());
//                        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
//                            progressDialog.dismiss();
//                            Toast.makeText(Register_screen4.this, getResources().getString(R.string.connection_time_out), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//
//                // Adding request to request queue
//                AppController.getInstance().addToRequestQueue(jsonObjectRequest, tag_json_obj);
//
//
//                   // Toast.makeText(Register_screen4.this, "Server Not Responding Please try Agter Some time", Toast.LENGTH_SHORT).show();
//
//            }
//        });


       /* send_register_req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (imageAdhar != null && imageBank != null && imageGst != null) {
                    progressDialog.show();
                    File fileAdhar = new File(imageAdhar.getPath());
                    RequestBody reqAdharFile = RequestBody.create(MediaType.parse("multipart/form-data"), fileAdhar);
                    MultipartBody.Part bodyAdhar = MultipartBody.Part.createFormData("address_proof", fileAdhar.getName(), reqAdharFile);

                    File fileGst = new File(imageGst.getPath());
                    RequestBody reqGstFile = RequestBody.create(MediaType.parse("multipart/form-data"), fileGst);
                    MultipartBody.Part bodyGst = MultipartBody.Part.createFormData("business_doc", fileGst.getName(), reqGstFile);

                    File fileBank = new File(imageBank.getPath());
                    RequestBody reqBankFile = RequestBody.create(MediaType.parse("multipart/form-data"), fileBank);
                    MultipartBody.Part bodyBank = MultipartBody.Part.createFormData("bank_detail", fileBank.getName(), reqBankFile);

                    RequestBody employee_name = RequestBody.create(MediaType.parse("multipart/form-data"), sellername);
                    RequestBody city = RequestBody.create(MediaType.parse("multipart/form-data"), sellercity);
                    RequestBody lat = RequestBody.create(MediaType.parse("multipart/form-data"), "");
                    RequestBody longitude = RequestBody.create(MediaType.parse("multipart/form-data"), "");
                    RequestBody device_id = RequestBody.create(MediaType.parse("multipart/form-data"), getUniqueIMEIId(getApplicationContext()));
                    RequestBody password = RequestBody.create(MediaType.parse("multipart/form-data"), sellerpass);
                    RequestBody phone_number = RequestBody.create(MediaType.parse("multipart/form-data"), sellermobile);
                    RequestBody email = RequestBody.create(MediaType.parse("multipart/form-data"), selleremail);
                    RequestBody address = RequestBody.create(MediaType.parse("multipart/form-data"), selleraddress);
                    RequestBody store_name = RequestBody.create(MediaType.parse("multipart/form-data"), sellerStorename);


                    Log.d("Image Req", bodyAdhar.headers().toString());

                Call call = RetrofitBuilder.Companion.getInstance().getRetrofit().register(bodyBank,bodyGst,bodyAdhar,longitude,lat,city,device_id,password,phone_number,email,employee_name,address,store_name);

                call.enqueue(new Callback<StoreRegistrationResponse>() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        progressDialog.dismiss();
                        Toast.makeText(Register_screen4.this,"Success",Toast.LENGTH_LONG).show();
                         startActivity(new Intent(Register_screen4.this, LoginActivity.class));
                         finish();

                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        progressDialog.dismiss();
                        Log.d("Error",t.getLocalizedMessage());
                        Toast.makeText(Register_screen4.this,"Failure",Toast.LENGTH_LONG).show();
                    }
                });

                }
                else {
                    Toast.makeText(Register_screen4.this,"Select All Images..!!",Toast.LENGTH_LONG).show();
                }
            }
        });*/
        send_register_req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             //  MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                final OkHttpClient client = new OkHttpClient();
                if (imageAdhar != null && imageBank != null && imageGst != null) {
                    progressDialog.show();
                   File fileAdhar = new File(imageAdhar.getPath());
                    //builder.addFormDataPart("address_proof", "address_proof.png", RequestBody.create(com.squareup.okhttp.MediaType.parse("image/jpg"),fileAdhar));
                 //   RequestBody reqAdharFile = RequestBody.create(MediaType.parse("multipart/form-data"), fileAdhar);
                 //   MultipartBody.Part bodyAdhar = MultipartBody.Part.createFormData("address_proof", fileAdhar.getName(), reqAdharFile);

                    File fileGst = new File(imageGst.getPath());
                   // builder.addFormDataPart("business_doc", "business_doc.png",okhttp3.RequestBody.create(MediaType.parse("image/*"),fileGst));
                    // RequestBody reqGstFile = RequestBody.create(MediaType.parse("multipart/form-data"), fileGst);
                   // MultipartBody.Part bodyGst = MultipartBody.Part.createFormData("business_doc", fileGst.getName(), reqGstFile);

                    File fileBank = new File(imageBank.getPath());
                  //  builder.addFormDataPart("bank_detail", "bank_detail.png",okhttp3.RequestBody.create(MediaType.parse("image/*"),fileBank));



                    final RequestBody requestBody = new MultipartBuilder()
                            .type(MultipartBuilder.FORM)
                            .addFormDataPart("address_proof",fileAdhar.getName(), RequestBody.create(com.squareup.okhttp.MediaType.parse("image/jpeg"), fileAdhar))
                            .addFormDataPart("business_doc", fileGst.getName(),RequestBody.create(com.squareup.okhttp.MediaType.parse("image/jpeg"),fileGst))
                            .addFormDataPart("bank_detail", fileBank.getName(),RequestBody.create(com.squareup.okhttp.MediaType.parse("image/jpeg"),fileBank))
                    .addFormDataPart("long","")
                    .addFormDataPart("lat","")
                    .addFormDataPart("city",sellercity)
                    .addFormDataPart("device_id",getUniqueIMEIId(getApplicationContext()))
                    .addFormDataPart("password",sellerpass)
                    .addFormDataPart("phone_number",sellermobile)
                    .addFormDataPart("email",selleremail)
                    .addFormDataPart("employee_name",sellername)
                    .addFormDataPart("address",selleraddress)
                    .addFormDataPart("store_name",sellerStorename)
                            .build();


                    com.squareup.okhttp.Request request = new com.squareup.okhttp.Request.Builder()
                            .url(BaseURL.BASE_URL+"store_register")
                            .post(requestBody)
                            .build();


                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Request request, IOException e) {
                            progressDialog.dismiss();
                            Log.d("Error",e.toString());
                            Register_screen4.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    Toast.makeText(Register_screen4.this,"Failure",Toast.LENGTH_LONG).show();

                                }
                            });

                        }

                        @Override
                        public void onResponse(Response response) throws IOException {
                            progressDialog.dismiss();
                            try{
                                String resStr =response.body().string();
                                JSONObject response_obj= new JSONObject(resStr);
                                Log.d("sucess",response_obj.toString());
                               // Toast.makeText(Register_screen4.this,response_obj.optString("message"),Toast.LENGTH_LONG).show();
                                if (response_obj.optString("status").equals("2")) {
                                    Register_screen4.this.runOnUiThread(new Runnable() {
                                        public void run() {
                                            startActivity(new Intent(Register_screen4.this, LoginActivity.class));
                                            finish();
                                        }
                                    });

                                }else{
                                    Register_screen4.this.runOnUiThread(new Runnable() {
                                        public void run() {
                                            Toast.makeText(Register_screen4.this,response_obj.optString("message"),Toast.LENGTH_LONG).show();

                                        }
                                    });
                                }
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    });
/*

                    // RequestBody reqBankFile = RequestBody.create(MediaType.parse("multipart/form-data"), fileBank);
                   // MultipartBody.Part bodyBank = MultipartBody.Part.createFormData("bank_detail", fileBank.getName(), reqBankFile);
                    builder.addFormDataPart("long","");
                    builder.addFormDataPart("lat","");
                    builder.addFormDataPart("city",sellercity);
                    builder.addFormDataPart("device_id",getUniqueIMEIId(getApplicationContext()));
                    builder.addFormDataPart("password",sellerpass);
                    builder.addFormDataPart("phone_number",sellermobile);
                    builder.addFormDataPart("email",selleremail);
                    builder.addFormDataPart("employee_name",sellername);
                    builder.addFormDataPart("address",selleraddress);
                    builder.addFormDataPart("store_name",sellerStorename);
*/

                   /* MultipartBody requestBody = builder.build();
                    Request request= new Request.Builder()
                            .url(BaseURL.BASE_URL+"store_register")
                            .post(requestBody)
                            .build();

                    OkHttpClient client = new OkHttpClient.Builder()
                            .connectTimeout(10, TimeUnit.SECONDS)
                            .writeTimeout(100, TimeUnit.SECONDS)
                            .readTimeout(100, TimeUnit.SECONDS)
                            .build();

                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            progressDialog.dismiss();
                            Log.d("Error",e.toString());
                            Toast.makeText(Register_screen4.this,"Failure",Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            progressDialog.dismiss();
                            try{
                                String resStr =response.body().string();
                                JSONObject response_obj= new JSONObject(resStr);
                                Toast.makeText(Register_screen4.this,"Success",Toast.LENGTH_LONG).show();
                                startActivity(new Intent(Register_screen4.this, LoginActivity.class));
                                finish();
                            }catch (Exception e){
                                e.printStackTrace();
                            }


                        }
                    });*/



                }
                else {
                    Toast.makeText(Register_screen4.this,"Select All Images..!!",Toast.LENGTH_LONG).show();
                }
            }
        });

        }

   /* public Boolean Photo_upload_process() {

        progressDialog.show();
        final OkHttpClient client = new OkHttpClient();
        try {
            // parent_id=2406&name=Demo Test&gender=Male&relationship=Father&day=23&month=08&year=1888&photo=

            // builder.addFormDataPart("sync_file[]",imgFile_profile.getName(), okhttp3.RequestBody.create(imgFile_profile.getAbsoluteFile(), MediaType.parse("image/jpeg")));
            File imgFile_profile = new File(imagePath_user_profile);
            final RequestBody requestBody = new MultipartBuilder()
                    .type(MultipartBuilder.FORM)
                    .addFormDataPart("user_id", user_id)
                    .addFormDataPart("image", imgFile_profile.getName(), RequestBody.create(com.squareup.okhttp.MediaType.parse("image/jpeg"), imgFile_profile))
                    .build();

            com.squareup.okhttp.Request request = new com.squareup.okhttp.Request.Builder()
                    .url(Url_list.PROFILE_PHOTO_UPLOAD.getURL())
                    .post(requestBody)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(com.squareup.okhttp.Request request, IOException e) {
                    if (My_account.this != null) {
                        if (!My_account.this.isFinishing()) {
                            My_account.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    progressDialog.dismiss();

                                    Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.offline), Toast.LENGTH_LONG);
                                    toast.setGravity(Gravity.CENTER, 0, 0);
                                    toast.show();
                                }
                            });
                        }
                    }
                }

                @Override
                public void onResponse(com.squareup.okhttp.Response response) throws IOException {

                    String jsonData = response.body().string();
                    Log.e("response", jsonData);
                    if (response.isSuccessful()) {

                        try {
                            final JSONObject response_obj = new JSONObject(jsonData);
                            if (My_account.this != null) {
                                if (!My_account.this.isFinishing()) {
                                    My_account.this.runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            try {
                                                progressDialog.dismiss();
                                                boolean error = response_obj.getBoolean("success");
                                                if (error) {

                                                    Toast toast = Toast.makeText(getApplicationContext(), "Profile photo update successfully", Toast.LENGTH_LONG);
                                                    toast.setGravity(Gravity.CENTER, 0, 0);
                                                    toast.show();

                                                } else {

                                                    Toast toast = Toast.makeText(getApplicationContext(), "" + response_obj.getString("msg") + "", Toast.LENGTH_LONG);
                                                    toast.setGravity(Gravity.CENTER, 0, 0);
                                                    toast.show();
                                                }

                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        if (My_account.this != null) {
                            if (!My_account.this.isFinishing()) {
                                My_account.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        progressDialog.dismiss();
                                        Toast toast = Toast.makeText(My_account.this, getString(R.string.Something), Toast.LENGTH_LONG);
                                        toast.setGravity(Gravity.CENTER, 0, 0);
                                        toast.show();
                                    }
                                });
                            }
                        }
                    }
                }
            });

            return true;

        } catch (Exception ex) {
            // Handle the error
        }
        return false;
    }*/

    private void imageaadharUploadDailog() {
        final CharSequence[] items = {"Camera", "From Gallery", "Cancel"};
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Choose Image Upload Option");
        dialogBuilder.setItems(items , new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int item) {
                // Do anything you want here
                if(item==0)
                {
//                    profileImgLayout.setVisibility(View.VISIBLE);
                    Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);
                }

                if(item==1)
                {
//                    profileImgLayout.setVisibility(View.VISIBLE);
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto , 1);             }

                if(item==2)
                {
                    dialog.dismiss();
                }
            }

        });
        dialogBuilder.create().show();
    }

    private void imagegstUploadDailog() {
        final CharSequence[] items = {"Camera", "From Gallery", "Cancel"};
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Choose Image Upload Option");
        dialogBuilder.setItems(items , new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int item) {
                // Do anything you want here
                if(item==0)
                {
//                    profileImgLayout.setVisibility(View.VISIBLE);
                    Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);
                }

                if(item==1)
                {
//                    profileImgLayout.setVisibility(View.VISIBLE);
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto , 1);             }

                if(item==2)
                {
                    dialog.dismiss();
                }
            }

        });
        dialogBuilder.create().show();
    }

    private void imagebankUploadDailog() {
        final CharSequence[] items = {"Camera", "From Gallery", "Cancel"};
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Choose Image Upload Option");
        dialogBuilder.setItems(items , new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int item) {
                // Do anything you want here
                if(item==0)
                {
//                    profileImgLayout.setVisibility(View.VISIBLE);
                    Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);
                }

                if(item==1)
                {
//                    profileImgLayout.setVisibility(View.VISIBLE);
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto , 1);             }

                if(item==2)
                {
                    dialog.dismiss();
                }
            }

        });
        dialogBuilder.create().show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case 0:
                if(resultCode == RESULT_OK){
                    Bitmap thumbnail = (Bitmap) data.getExtras().get( "data" );
//                        // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
                    Uri tempUri = getImageUri( thumbnail );
                    // CALL THIS METHOD TO GET THE ACTUAL PATH
                    File finalFile = new File( getRealPathFromURI( tempUri ) );
                    filePathVendor = Uri.fromFile( finalFile );
                    image=filePathVendor.toString();
//                     selectedImage = data.getData();


                    try {

                        bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), filePathVendor);
                        Log.d("bitmap_",""+bitmap);
//                        Log.d("BtnStatus", String.valueOf(btnclickaadhar.booleanValue()+"  "+btnclickgstr.booleanValue()+"  "+btnclickbank.booleanValue()));



                        if(click.equals("aadhar")) {
                            aadhar.setImageBitmap(thumbnail);
                        }else if(click.equals("gst"))
                        {
                            gst.setImageBitmap(thumbnail);

                        }else if(click.equals("bank"))
                        {
                            bank.setImageBitmap(thumbnail);

                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(Register_screen4.this, "Failed!", Toast.LENGTH_SHORT).show();
                    }

//                    profile_image_vender.setImageURI(selectedImage);
                    ImageName=  filePathVendor.getLastPathSegment();
                    Log.d(TAG, "onActivityResult: one "+ImageName);
                    String name = ImageName.substring(ImageName.lastIndexOf("/") + 1);



                    // instead of "/" you can also use File.sepearator
                    System.out.println("......"+ name);
//                    imagename.setText(name+".jpg");
                    img_selected=1;

                    if (filePathVendor == null) {
                        //Display an error
                        return;
                    }
                    try {
                        InputStream inputStream = Register_screen4.this.getContentResolver().openInputStream(filePathVendor);
                        Toast.makeText(this, inputStream+"", Toast.LENGTH_SHORT).show();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    //Now you can do whatever you want with your inpustream, save it as file, upload to a server, decode a bitmap...
                }

                break;
            case 1:
                if(resultCode == RESULT_OK){
                    selectedImage = data.getData();
                    image=selectedImage.toString();



                    Log.d(TAG, "onActivityResult: slectedImage"+selectedImage.toString());
                    try {

                        bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                        Log.d(TAG, "onActivityResult:2"+bitmap.toString());

                        if(click.equals("aadhar")) {
                            aadhar.setImageBitmap(bitmap);
                        }else if(click.equals("gst"))
                        {
                            gst.setImageBitmap(bitmap);

                        }else if(click.equals("bank"))
                        {
                            bank.setImageBitmap(bitmap);

                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(Register_screen4.this, "Failed!", Toast.LENGTH_SHORT).show();
                    }


//                    profile_image_vender.setImageURI(selectedImage);
                    ImageName=  selectedImage.getLastPathSegment();
                    Log.d(TAG, "onActivityResult: one "+ImageName);
                    String name = ImageName.substring(ImageName.lastIndexOf("/") + 1);
                    // instead of "/" you can also use File.sepearator
                    System.out.println("......"+ name);
//                    imagename.setText(name+".jpg");
                    img_selected=1;
                    if (data == null) {
                        //Display an error
                        return;
                    }
                    try {
                        InputStream inputStream = Register_screen4.this.getContentResolver().openInputStream(data.getData());
//                        Toast.makeText(this, inputStream+"", Toast.LENGTH_SHORT).show();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;

            case 101 :

                ArrayList<Parcelable> images = data.getParcelableArrayListExtra(Config.EXTRA_IMAGES);

                if(click.equalsIgnoreCase("aadhar")){
                    imageAdhar = (com.nguyenhoanglam.imagepicker.model.Image) images.get(0);
                    Glide.with(this).load(imageAdhar.getPath())
                            .into(aadhar);
                }else if(click.equalsIgnoreCase("bank")){
                    imageBank = (com.nguyenhoanglam.imagepicker.model.Image) images.get(0);
                    Glide.with(this).load(imageBank.getPath())
                            .into(bank);
                }else {
                    imageGst = (com.nguyenhoanglam.imagepicker.model.Image) images.get(0);
                    Glide.with(this).load(imageGst.getPath())
                            .into(gst);
                }

                break;
        }
    }
    public Uri getImageUri(Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress( Bitmap.CompressFormat.JPEG, 100, bytes );
        String path = MediaStore.Images.Media.insertImage( getContentResolver(), inImage, null, null );
        return Uri.parse( path );
    }

    public String getRealPathFromURI(Uri uri) {
        String path = "";
        if (getContentResolver() != null) {
            Cursor cursor = getContentResolver().query( uri, null, null, null, null );
            if (cursor != null) {
                cursor.moveToFirst();
                int idx = cursor.getColumnIndex( MediaStore.Images.ImageColumns.DATA );
                path = cursor.getString( idx );
                cursor.close();
            }
        }
        return path;
    }

    public static String getUniqueIMEIId(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return "";
            }
            String imei = telephonyManager.getDeviceId();
            Log.e("imei", "=" + imei);
            if (imei != null && !imei.isEmpty()) {
                return imei;
            } else {
                return android.os.Build.SERIAL;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "not_found";
    }

    private void pickFromGallery() {
        ImagePicker.with(this)
                .setRequestCode(GALLERY_REQUEST_CODE)
                .setMultipleMode(true)
                .setFolderMode(true)
                .setShowCamera(true)
                .setMaxSize(1)
                .start();
    }



}
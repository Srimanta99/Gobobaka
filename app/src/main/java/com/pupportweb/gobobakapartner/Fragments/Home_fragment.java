package com.pupportweb.gobobakapartner.Fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager2.widget.ViewPager2;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.pupportweb.gobobakapartner.Adapter.DeliveryBoyAdapter;
import com.pupportweb.gobobakapartner.Adapter.My_Nextday_Order_Adapter;
import com.pupportweb.gobobakapartner.Adapter.My_Today_Order_Adapter;
import com.pupportweb.gobobakapartner.AppController;
import com.pupportweb.gobobakapartner.Config.BaseURL;
import com.pupportweb.gobobakapartner.Dashboard.MyOrderDeatil;
import com.pupportweb.gobobakapartner.Dashboard.OrderDetails_today;
import com.pupportweb.gobobakapartner.MainActivity;
import com.pupportweb.gobobakapartner.Model.ListAssignAndUnassigned;
import com.pupportweb.gobobakapartner.Model.NewDeliveryBoyModel;
import com.pupportweb.gobobakapartner.Model.NextdayOrderModel;
import com.pupportweb.gobobakapartner.Model.TodayOrderModel;
import com.pupportweb.gobobakapartner.R;
import com.pupportweb.gobobakapartner.ViewEarningDetailsActivity;
import com.pupportweb.gobobakapartner.retrofit.RetrofitBuilder;
import com.pupportweb.gobobakapartner.retrofitModel.EarnResponse;
import com.pupportweb.gobobakapartner.retrofitModel.StoreEarningsItem;
import com.pupportweb.gobobakapartner.util.DeliveryBoyListClick;
import com.pupportweb.gobobakapartner.util.TodayOrderClickListner;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TimeZone;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

import static android.content.Context.MODE_PRIVATE;

public class Home_fragment extends Fragment {

    private static String TAG = Home_fragment.class.getSimpleName();
    ProgressDialog pd;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String store_id;
    List<NewDeliveryBoyModel> boyModels = new ArrayList<>();
    //    private RecyclerView rv_today_orders,rv_next_day_orders;
    private My_Today_Order_Adapter my_today_order_adapter;
    private My_Nextday_Order_Adapter my_nextday_order_adapter;
    private List<TodayOrderModel> movieList = new ArrayList<>();
    private List<NextdayOrderModel> nextdayOrderModels = new ArrayList<>();
    private List<ListAssignAndUnassigned> listAssignAndUnassigneds = new ArrayList<>();
    private LinearLayout linearLayout;
    private AssignFragment adapter;
    private ProgressDialog dialog;
    private Context context;
    private int todayPosition = -1;
    private int unassPosition = -1;
    private String boy_id = "";
    private SwipeRefreshLayout swipe_to;

    private TextView txtEraning;

    public Home_fragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_home, container, false);
        setHasOptionsMenu(true);
        ((MainActivity) getActivity()).setTitle(getResources().getString(R.string.app_name));
        context = container.getContext();
        dialog = new ProgressDialog(container.getContext());
        dialog.setMessage("Loading....");
        dialog.setCancelable(false);
        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        swipe_to = view.findViewById(R.id.swipe_to);

        txtEraning = view.findViewById(R.id.txtEraning);
//        tabLayout.addTab(tabLayout.newTab().setText("Today's Order"));
        tabLayout.addTab(tabLayout.newTab().setText("Assigned Orders"));
//        tabLayout.addTab(tabLayout.newTab().setText("Nextday's Order"));
        tabLayout.addTab(tabLayout.newTab().setText("UnAssigned Orders"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        listAssignAndUnassigneds.add(new ListAssignAndUnassigned("assigned", movieList, nextdayOrderModels));
        listAssignAndUnassigneds.add(new ListAssignAndUnassigned("unassigned", movieList, nextdayOrderModels));

        final ViewPager2 viewPager = view.findViewById(R.id.pager);
        adapter = new AssignFragment(container.getContext(), listAssignAndUnassigneds, new TodayOrderClickListner() {
            @Override
            public void ClickGetBoys(int position, String viewType) {
                getBoysList(viewType, position);
            }

            @Override
            public void assignClickBoys(int position, String viewType) {
            }

            @Override
            public void forOrderTodayClick(int position) {

                String saleid = movieList.get(position).getCart_id();
                String userfullname = movieList.get(position).getUser_name();
                String customerphone = movieList.get(position).getUser_phone();
                String date = movieList.get(position).getDelivery_date();
                String time = movieList.get(position).getTime_slot();
                String ammount = movieList.get(position).getOrder_price();
                String status = movieList.get(position).getOrder_status();
                Intent intent = new Intent(context, OrderDetails_today.class);
                intent.putExtra("sale_id", saleid);
                intent.putExtra("user_fullname", userfullname);
                intent.putExtra("customer_phone", customerphone);
                intent.putExtra("date", date);
                intent.putExtra("time", time);
                intent.putExtra("cart_id", movieList.get(position).getCart_id());
                intent.putExtra("ammount", ammount);
                intent.putExtra("address", movieList.get(position).getUser_address());
                intent.putExtra("status", status);
                intent.putExtra("data", movieList.get(position).getOrder_details());
                startActivityForResult(intent, 7);

            }

            @Override
            public void forOrderNextClick(int position) {
                String saleid = nextdayOrderModels.get(position).getCart_id();
                String userfullname = nextdayOrderModels.get(position).getUser_name();
                String cart_id = nextdayOrderModels.get(position).getCart_id();
                String customerphone = nextdayOrderModels.get(position).getUser_phone();
                String date = nextdayOrderModels.get(position).getDelivery_date();
                String ammount = nextdayOrderModels.get(position).getOrder_price();
                String status = nextdayOrderModels.get(position).getOrder_status();
                Intent intent = new Intent(context, MyOrderDeatil.class);
                intent.putExtra("sale_id", saleid);
                intent.putExtra("user_fullname", userfullname);
                intent.putExtra("cart_id", cart_id);
                intent.putExtra("customer_phone", customerphone);
                intent.putExtra("address", nextdayOrderModels.get(position).getUser_address());
                intent.putExtra("date", date);
                intent.putExtra("time", "");
                intent.putExtra("ammount", ammount);
                intent.putExtra("status", status);
                intent.putExtra("data", nextdayOrderModels.get(position).getOrder_details());
                startActivityForResult(intent, 7);
            }
        });
        viewPager.setAdapter(adapter);
//        rv_today_orders = (RecyclerView) view.findViewById(R.id.rv_today_order);
//        rv_next_day_orders=(RecyclerView)view.findViewById(R.id.rv_next_order);
//        rv_today_orders.setLayoutManager(new LinearLayoutManager(getActivity()));
//        rv_next_day_orders.setLayoutManager(new LinearLayoutManager(getActivity()));


        sharedPreferences = container.getContext().getSharedPreferences("logindata", MODE_PRIVATE);
//        editor = sharedPreferences.edit();
        store_id = sharedPreferences.getString("id", "");
        Log.d("dd", store_id);

        getEarning(txtEraning,"","");

        txtEraning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Dialog dialog ;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    dialog = new Dialog(
                            getActivity(),
                    android.R.style.Theme_Material_Light_Dialog_Alert
            );
                } else {
                    dialog = new Dialog(getActivity());
                }
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.layout_select_start_end_date);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                TextView txtStartDateTitle =  dialog.findViewById(R.id.txtStartDateTitle);
                TextView txtStartDate = dialog.findViewById(R.id.txtStartDate);
                TextView txtEndDateTitle = dialog.findViewById(R.id.txtEndDateTitle);
                TextView txtEndDate = dialog.findViewById(R.id.txtEndDate);

                Button btnSet = dialog.findViewById(R.id.btnSet);
                Button btnCancel = dialog.findViewById(R.id.btnCancel);

                txtStartDateTitle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

                        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                                  int dayOfMonth) {
                                txtStartDate.setText(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
                            }

                        };

                        DatePickerDialog dialog = new DatePickerDialog(context, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                                calendar.get(Calendar.DAY_OF_MONTH));
                        dialog.show();


                    }


                });

                txtEndDateTitle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

                        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                                  int dayOfMonth) {
                                txtEndDate.setText(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
                            }

                        };

                        DatePickerDialog dialog = new DatePickerDialog(context, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                                calendar.get(Calendar.DAY_OF_MONTH));
                        dialog.show();
                    }
                });

                btnSet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(TextUtils.isEmpty(txtStartDate.getText().toString())){
                            Toast.makeText(context,"Select Start Date",Toast.LENGTH_LONG).show();
                        }else if(TextUtils.isEmpty(txtEndDate.getText().toString())){
                            Toast.makeText(context,"Select End Date",Toast.LENGTH_LONG).show();
                        }else {
                            getSpecificEarning(txtStartDate.getText().toString(),txtEndDate.getText().toString());
                            dialog.dismiss();
                        }
                    }
                });

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

//        adapter = new PagerOrderAdapter(getFragmentManager(), tabLayout.getTabCount());
//        viewPager.setAdapter(adapter);
        wrapTabIndicatorToTitle(tabLayout, 80, 80);
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                viewPager.setCurrentItem(tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if (position == 0) {
//                    tab.setText("Today's Order");
                    tab.setText("Assigned Order");
                } else if (position == 1) {
                    tab.setText("UnAssigned Order");
                }

            }
        });
        tabLayoutMediator.attach();

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
//                super.onPageSelected(position);
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
//        viewPager.addon(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        swipe_to.setOnRefreshListener(this::getTodayOrders);

        getTodayOrders();
        return view;


    }


    public void wrapTabIndicatorToTitle(TabLayout tabLayout, int externalMargin, int internalMargin) {
        View tabStrip = tabLayout.getChildAt(0);
        if (tabStrip instanceof ViewGroup) {
            ViewGroup tabStripGroup = (ViewGroup) tabStrip;
            int childCount = ((ViewGroup) tabStrip).getChildCount();
            for (int i = 0; i < childCount; i++) {
                View tabView = tabStripGroup.getChildAt(i);
                tabView.setMinimumWidth(0);
                tabView.setPadding(0, tabView.getPaddingTop(), 0, tabView.getPaddingBottom());
                if (tabView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
                    ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) tabView.getLayoutParams();
                    if (i == 0) {
                        setMargin(layoutParams, externalMargin, internalMargin);
                    } else if (i == childCount - 1) {
                        setMargin(layoutParams, internalMargin, externalMargin);
                    } else {
                        setMargin(layoutParams, internalMargin, internalMargin);
                    }
                }
            }

            tabLayout.requestLayout();
        }
    }

    private void setMargin(ViewGroup.MarginLayoutParams layoutParams, int start, int end) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            layoutParams.setMarginStart(start);
            layoutParams.setMarginEnd(end);
        } else {
            layoutParams.leftMargin = start;
            layoutParams.rightMargin = end;
        }
    }


    private void getTodayOrders() {
        dialog.show();
        movieList.clear();
        nextdayOrderModels.clear();
        String tag_json_obj = "json_login_req";
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("store_id", store_id);

        StringRequest request = new StringRequest(Request.Method.POST, BaseURL.storeassigned_url, response -> {
            Log.i(TAG, response);
            try {
                if (response.contains("no orders found")) {
                    Toast.makeText(context, "No Assigned Orders found", Toast.LENGTH_SHORT).show();
                } else {
                    JSONArray jsonArray = new JSONArray(response);
                    Gson gson = new Gson();
                    Type listType = new TypeToken<List<TodayOrderModel>>() {
                    }.getType();
                    List<TodayOrderModel> orderModels = gson.fromJson(jsonArray.toString(), listType);
                    movieList.addAll(orderModels);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                getNextDayOrders();
            }


        }, error -> {
            getNextDayOrders();
            System.out.println("Error [" + error + "]");
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("store_id", store_id);
                return params;
            }
        };


//        CustomVolleyJsonRequest jsonObjReq = new CustomVolleyJsonRequest(Request.Method.POST,
//                BaseURL.storeassigned_url, params, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });

        AppController.getInstance().addToRequestQueue(request);
    }

    private void getNextDayOrders() {
        String tag_json_obj = "json_login_req";


        StringRequest request = new StringRequest(Request.Method.POST, BaseURL.storeunassigned_url, response -> {
            Log.i(TAG, response);
            try {
                if (response.contains("no orders found")) {
                    Toast.makeText(context, "No Orders found", Toast.LENGTH_SHORT).show();
                    if (movieList.size() > 0) {
                        adapter.notifyDataSetChanged();
                    }
                } else {
                    JSONArray jsonArray = new JSONArray(response);
                    Gson gson = new Gson();
                    Type listType = new TypeToken<List<NextdayOrderModel>>() {
                    }.getType();
                    List<NextdayOrderModel> nextdayOrderModels1 = gson.fromJson(jsonArray.toString(), listType);
                    nextdayOrderModels.addAll(nextdayOrderModels1);
                    adapter.notifyDataSetChanged();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                swipe_to.setRefreshing(false);
            }

        }, error -> {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
            if (movieList.size() > 0) {
                adapter.notifyDataSetChanged();
            }
            swipe_to.setRefreshing(false);
            System.out.println("Error [" + error + "]");
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("store_id", store_id);
                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(request);
    }

    private void getBoysList(String viewType, int positiond) {
        dialog.show();
        boyModels.clear();
        StringRequest request = new StringRequest(Request.Method.POST, BaseURL.getBoyList, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getString("status").equalsIgnoreCase("1")) {
                        Gson gson = new Gson();
                        Type listType = new TypeToken<List<NewDeliveryBoyModel>>() {
                        }.getType();
                        List<NewDeliveryBoyModel> nextdayOrderModels1 = gson.fromJson(jsonObject.getString("data"), listType);
                        boyModels.addAll(nextdayOrderModels1);
                        selectBoyDialog(viewType, positiond);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    if (dialog != null && dialog.isShowing()) {
                        dialog.dismiss();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                System.out.println("Error [" + error + "]");
            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("store_id", store_id);
                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(request);
    }


    private void selectBoyDialog(String viewType, int positiondd) {
        final Dialog dialog;
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.delivery_boy_list);
//        dialog.getWindow().getDecorView().setTop(100);
//        dialog.getWindow().getDecorView().setLeft(100);

        RecyclerView listRecy = dialog.findViewById(R.id.boy_list);
        DeliveryBoyAdapter deliveryBoyAdapter = new DeliveryBoyAdapter(context, boyModels, new DeliveryBoyListClick() {
            @Override
            public void onClick(int position) {
                dialog.dismiss();
                boy_id = boyModels.get(position).getDboy_id();
                if (viewType.equalsIgnoreCase("today")) {
                    todayPosition = positiondd;
                    unassPosition = -1;
                } else {
                    unassPosition = positiondd;
                    todayPosition = -1;
                }
                if (boyModels.size() > 0 && !boy_id.equalsIgnoreCase("")) {
                    assignBoyToOrder(boyModels.get(position).getDboy_id());
                }

            }
        });
        listRecy.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        listRecy.setAdapter(deliveryBoyAdapter);
        dialog.show();

    }

    private void assignBoyToOrder(final String dboy_id) {
        dialog.show();
//        boyModels.clear();
        StringRequest request = new StringRequest(Request.Method.POST, BaseURL.assignBoyToOrder, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getString("status").equalsIgnoreCase("1")) {
                        if (dialog != null && dialog.isShowing()) {
                            dialog.dismiss();
                        }
                        getTodayOrders();
//                        Gson gson = new Gson();
//                        Type listType = new TypeToken<List<NewDeliveryBoyModel>>() {
//                        }.getType();
//                        List<NewDeliveryBoyModel> nextdayOrderModels1 = gson.fromJson(jsonObject.getString("data"), listType);
//                        boyModels.addAll(nextdayOrderModels1);
                    } else {
                        if (dialog != null && dialog.isShowing()) {
                            dialog.dismiss();
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                System.out.println("Error [" + error + "]");
            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("store_id", store_id);
                if (todayPosition != -1) {
                    params.put("cart_id", movieList.get(todayPosition).getCart_id());
                } else if (unassPosition != -1) {
                    params.put("cart_id", nextdayOrderModels.get(unassPosition).getCart_id());
                }
                params.put("dboy_id", dboy_id);
//                params.put("store_id", store_id);
                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(request);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 7) {
            if (data != null && Objects.requireNonNull(data.getStringExtra("runapi")).equalsIgnoreCase("true")) {
                getTodayOrders();
            }
        }
    }

    private void getEarning(TextView txtEraning,String startDate,String endDate){

        String storeid = sharedPreferences.getString("id", "");


        RequestBody date_from = RequestBody.create(MediaType.parse("date_from"),startDate );
        RequestBody date_to = RequestBody.create(MediaType.parse("date_to"),endDate );
        RequestBody store_id = RequestBody.create(MediaType.parse("store_id"),storeid );

        Call call= RetrofitBuilder.Companion.getInstance().getRetrofit().earn(store_id,date_from,date_to);

        call.enqueue(new Callback<EarnResponse>() {
            @Override
            public void onResponse(Call call, retrofit2.Response response) {

                if (response.isSuccessful()) {
                    try {

                        EarnResponse res = (EarnResponse) response.body();

                        if(res.getEarnData().getSumprice()!=null){
                            txtEraning.setText("Total Earning : "+res.getEarnData().getSumprice().toString());
                        }

                    } catch (Exception e) {
                        txtEraning.setText("Total Earning : 0");
                    }

                }else {
                    txtEraning.setText("Total Earning : 0");
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                txtEraning.setText("Total Earning : 0");
            }
        });
    }

    private void getSpecificEarning(String startDate,String endDate){

        String storeid = sharedPreferences.getString("id", "");


        RequestBody date_from = RequestBody.create(MediaType.parse("date_from"),startDate );
        RequestBody date_to = RequestBody.create(MediaType.parse("date_to"),endDate );
        RequestBody store_id = RequestBody.create(MediaType.parse("store_id"),storeid );

        Call call= RetrofitBuilder.Companion.getInstance().getRetrofit().earn(store_id,date_from,date_to);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call call, retrofit2.Response response) {

                if(response.isSuccessful()) {
                    try {
                        EarnResponse res = (EarnResponse) response.body();

                        ArrayList<StoreEarningsItem> list = (ArrayList<StoreEarningsItem>) res.getStoreEarnings();

                        if (!list.isEmpty()) {
                            Intent intent = new Intent(context, ViewEarningDetailsActivity.class);
                            intent.putExtra("list", list);
                        } else {
                            Toast.makeText(context, "Data not available..!!", Toast.LENGTH_LONG).show();
                        }

                    } catch (Exception e) {
                        Toast.makeText(context, "Data not available..!!", Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(context,"Data not available..!!",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(context,"Data not available..!!",Toast.LENGTH_LONG).show();
            }
        });
    }
}


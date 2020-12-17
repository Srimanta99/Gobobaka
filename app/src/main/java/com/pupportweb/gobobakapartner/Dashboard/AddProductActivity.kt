package com.pupportweb.gobobakapartner.Dashboard

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pupportweb.gobobakapartner.Adapter.SelectProductAdapter
import com.pupportweb.gobobakapartner.R
import com.pupportweb.gobobakapartner.retrofit.RetrofitBuilder
import com.pupportweb.gobobakapartner.retrofitModel.ProductSelectDataItem
import com.pupportweb.gobobakapartner.retrofitModel.ProductSelectResponse
import com.pupportweb.gobobakapartner.retrofitModel.StoreAddProductResponse
import com.pupportweb.gobobakapartner.util.AllProductClickListner
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddProductActivity : AppCompatActivity(),AllProductClickListner {

    lateinit var progressDialog: ProgressDialog

    lateinit var sharedPreferences: SharedPreferences
    lateinit var rvList : RecyclerView
    var list = arrayListOf<ProductSelectDataItem>()

     var storeid="0";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)

        rvList = findViewById(R.id.rvList)

        progressDialog = ProgressDialog(this@AddProductActivity)
        progressDialog.setMessage("Loading..Please Wait..!!")
        progressDialog.setCancelable(false)

        sharedPreferences = getSharedPreferences("logindata", Context.MODE_PRIVATE)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.title = resources.getString(R.string.select_product)

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        getAllProduct()

    }

    fun getAllProduct(){
        progressDialog.show()

        storeid = sharedPreferences.getString("id", "").toString()

        val store_id: RequestBody = RequestBody.create("store_id".toMediaTypeOrNull(), storeid)
        val call = RetrofitBuilder.instance.retrofit.productselect(store_id)

        call.enqueue(object : Callback<ProductSelectResponse>{
            override fun onFailure(call: Call<ProductSelectResponse>, t: Throwable) {
                progressDialog.dismiss()
                Toast.makeText(this@AddProductActivity,"Error while getting data..!!"+t,Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<ProductSelectResponse>, response: Response<ProductSelectResponse>) {

                progressDialog.dismiss()
                if(response.isSuccessful){

                     list = response.body()!!.data as ArrayList<ProductSelectDataItem>

                    if(!list.isEmpty()) {
                        var adapter = SelectProductAdapter(this@AddProductActivity, list, list, this@AddProductActivity)
                        val layoutManager = LinearLayoutManager(this@AddProductActivity)
                        rvList.layoutManager = layoutManager
                        rvList.adapter = adapter
                    }else{
                        Toast.makeText(this@AddProductActivity,"Product list not found.!!",Toast.LENGTH_LONG).show()
                    }

                }else{
                    Toast.makeText(this@AddProductActivity,"Error while getting data..!!",Toast.LENGTH_LONG).show()
                }
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return if (id == R.id.action_search) {
            true
        } else super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAndRemoveTask()
        } else {
            finish()
        }
    }

    override fun onClick(position: Int) {
        displayDialogForStock(position);
    }

    private fun displayDialogForStock(position : Int) {

        var dialog: Dialog
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialog = Dialog(
                    this@AddProductActivity,
                    android.R.style.Theme_Material_Light_Dialog_Alert
            )
        } else {
            dialog = Dialog(this@AddProductActivity)
        }
        var title: TextView = dialog.findViewById(android.R.id.title)

        if (title != null) {
            title.visibility = View.GONE
        }

        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.layout_enter_stock)


        var etNoOfStock = dialog.findViewById(R.id.etNoOfStocks) as EditText
        var btnOk = dialog.findViewById(R.id.btnOk) as Button

        btnOk.setOnClickListener {

            if (TextUtils.isEmpty(etNoOfStock.text.toString())) {

                Toast.makeText(this@AddProductActivity, "Enter Stock", Toast.LENGTH_LONG)
                        .show()
            } else {

                addProduct(etNoOfStock.text.toString(),list.get(position).varientId.toString())
                dialog.dismiss()
            }
        }


        dialog.show()

    }

    fun addProduct(Stock: String, varientId: String){
        progressDialog.show()

        storeid = sharedPreferences.getString("id", "").toString()

        val store_id: RequestBody = RequestBody.create("store_id".toMediaTypeOrNull(), storeid)
        val varient_id: RequestBody = RequestBody.create("varient_id".toMediaTypeOrNull(), varientId)
        val stock: RequestBody = RequestBody.create("stock".toMediaTypeOrNull(),Stock)

        val call = RetrofitBuilder.instance.retrofit.store_add_products(store_id,varient_id,stock)
        call.enqueue(object : Callback<StoreAddProductResponse>{
            override fun onFailure(call: Call<StoreAddProductResponse>, t: Throwable) {
                progressDialog.dismiss()
                Toast.makeText(this@AddProductActivity,"Error while Addind product..!!",Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<StoreAddProductResponse>, response: Response<StoreAddProductResponse>) {

                progressDialog.dismiss()
                if(response.isSuccessful){

                    Toast.makeText(this@AddProductActivity,response.body()!!.message,Toast.LENGTH_LONG).show()

                }else{
                    Toast.makeText(this@AddProductActivity,"Error while Addind product..!!",Toast.LENGTH_LONG).show()
                }
            }

        })
    }


}
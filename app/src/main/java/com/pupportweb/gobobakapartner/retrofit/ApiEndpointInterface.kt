package com.omelette.stockjar.Retrofit

import androidx.annotation.Keep
import com.pupportweb.gobobakapartner.retrofitModel.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*


@Keep
interface ApiEndpointInterface {


    @Multipart
    @POST("store_register")
    fun register(
            @Part file1: MultipartBody.Part?,
            @Part file2: MultipartBody.Part?,
            @Part file3: MultipartBody.Part?,
            @Part("long") long: RequestBody?,
            @Part("lat") lat: RequestBody?,
            @Part("city") city: RequestBody?,
            @Part("device_id") device_id: RequestBody?,
            @Part("password") password: RequestBody?,
            @Part("phone_number") phone_number: RequestBody?,
            @Part("email") email: RequestBody?,
            @Part("employee_name") employee_name: RequestBody?,
            @Part("address") address: RequestBody?,
            @Part("store_name") store_name: RequestBody?
    ): Call<StoreRegistrationResponse>


    @Multipart
    @POST("earn")
    fun earn(
            @Part("store_id") store_id: RequestBody?,
            @Part("date_from") date_from: RequestBody?,
            @Part("date_to") date_to: RequestBody?
    ): Call<EarnResponse>

    @Multipart
    @POST("store_add_products")
    fun store_add_products(
            @Part("store_id") store_id: RequestBody?,
            @Part("varient_id") varient_id: RequestBody?,
            @Part("stock") stock: RequestBody?
    ): Call<StoreAddProductResponse>

    @Multipart
    @POST("productselect")
    fun productselect(
            @Part("store_id") store_id: RequestBody?
    ): Call<ProductSelectResponse>


    @Multipart
    @POST("store_stock_update")
    fun store_stock_update(
            @Part("p_id") p_id: RequestBody?,
            @Part("stock") stock: RequestBody?
    ): Call<StoreStockUpdateResponse>


}
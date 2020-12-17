package com.pupportweb.gobobakapartner.retrofitModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;
import java.io.Serializable;

@Generated("com.robohorse.robopojogenerator")
public class StoreEarningsItem implements Serializable  {

	@JsonProperty("order_status")
	private String orderStatus;

	@JsonProperty("delivery_date")
	private String deliveryDate;

	@JsonProperty("orderTotalPrice")
	private int orderTotalPrice;

	@JsonProperty("orderPriceWithoutDeliveryFees")
	private int orderPriceWithoutDeliveryFees;

	@JsonProperty("payment_method")
	private String paymentMethod;

	@JsonProperty("product_name")
	private Object productName;

	public void setOrderStatus(String orderStatus){
		this.orderStatus = orderStatus;
	}

	public String getOrderStatus(){
		return orderStatus;
	}

	public void setDeliveryDate(String deliveryDate){
		this.deliveryDate = deliveryDate;
	}

	public String getDeliveryDate(){
		return deliveryDate;
	}

	public void setOrderTotalPrice(int orderTotalPrice){
		this.orderTotalPrice = orderTotalPrice;
	}

	public int getOrderTotalPrice(){
		return orderTotalPrice;
	}

	public void setOrderPriceWithoutDeliveryFees(int orderPriceWithoutDeliveryFees){
		this.orderPriceWithoutDeliveryFees = orderPriceWithoutDeliveryFees;
	}

	public int getOrderPriceWithoutDeliveryFees(){
		return orderPriceWithoutDeliveryFees;
	}

	public void setPaymentMethod(String paymentMethod){
		this.paymentMethod = paymentMethod;
	}

	public String getPaymentMethod(){
		return paymentMethod;
	}

	public void setProductName(Object productName){
		this.productName = productName;
	}

	public Object getProductName(){
		return productName;
	}

	@Override
 	public String toString(){
		return 
			"StoreEarningsItem{" + 
			"order_status = '" + orderStatus + '\'' + 
			",delivery_date = '" + deliveryDate + '\'' + 
			",orderTotalPrice = '" + orderTotalPrice + '\'' + 
			",orderPriceWithoutDeliveryFees = '" + orderPriceWithoutDeliveryFees + '\'' + 
			",payment_method = '" + paymentMethod + '\'' + 
			",product_name = '" + productName + '\'' + 
			"}";
		}
}
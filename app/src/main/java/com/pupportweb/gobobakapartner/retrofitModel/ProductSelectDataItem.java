package com.pupportweb.gobobakapartner.retrofitModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;
import java.io.Serializable;

@Generated("com.robohorse.robopojogenerator")
public class ProductSelectDataItem implements Serializable {

	@JsonProperty("varient_id")
	private int varientId;

	@JsonProperty("product_id")
	private int productId;

	@JsonProperty("quantity")
	private int quantity;

	@JsonProperty("unit")
	private String unit;

	@JsonProperty("mrp")
	private int mrp;

	@JsonProperty("price")
	private int price;

	@JsonProperty("description")
	private String description;

	@JsonProperty("varient_image")
	private String varientImage;

	@JsonProperty("cat_id")
	private int catId;

	@JsonProperty("product_name")
	private String productName;

	@JsonProperty("product_image")
	private String productImage;

	@JsonProperty("product_image1")
	private String productImage1;

	@JsonProperty("product_image2")
	private String productImage2;

	@JsonProperty("product_image3")
	private String productImage3;

	@JsonProperty("product_image4")
	private String productImage4;

	public void setVarientId(int varientId){
		this.varientId = varientId;
	}

	public int getVarientId(){
		return varientId;
	}

	public void setProductId(int productId){
		this.productId = productId;
	}

	public int getProductId(){
		return productId;
	}

	public void setQuantity(int quantity){
		this.quantity = quantity;
	}

	public int getQuantity(){
		return quantity;
	}

	public void setUnit(String unit){
		this.unit = unit;
	}

	public String getUnit(){
		return unit;
	}

	public void setMrp(int mrp){
		this.mrp = mrp;
	}

	public int getMrp(){
		return mrp;
	}

	public void setPrice(int price){
		this.price = price;
	}

	public int getPrice(){
		return price;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setVarientImage(String varientImage){
		this.varientImage = varientImage;
	}

	public String getVarientImage(){
		return varientImage;
	}

	public void setCatId(int catId){
		this.catId = catId;
	}

	public int getCatId(){
		return catId;
	}

	public void setProductName(String productName){
		this.productName = productName;
	}

	public String getProductName(){
		return productName;
	}

	public void setProductImage(String productImage){
		this.productImage = productImage;
	}

	public String getProductImage(){
		return productImage;
	}

	public void setProductImage1(String productImage1){
		this.productImage1 = productImage1;
	}

	public String getProductImage1(){
		return productImage1;
	}

	public void setProductImage2(String productImage2){
		this.productImage2 = productImage2;
	}

	public String getProductImage2(){
		return productImage2;
	}

	public void setProductImage3(String productImage3){
		this.productImage3 = productImage3;
	}

	public String getProductImage3(){
		return productImage3;
	}

	public void setProductImage4(String productImage4){
		this.productImage4 = productImage4;
	}

	public String getProductImage4(){
		return productImage4;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"varient_id = '" + varientId + '\'' + 
			",product_id = '" + productId + '\'' + 
			",quantity = '" + quantity + '\'' + 
			",unit = '" + unit + '\'' + 
			",mrp = '" + mrp + '\'' + 
			",price = '" + price + '\'' + 
			",description = '" + description + '\'' + 
			",varient_image = '" + varientImage + '\'' + 
			",cat_id = '" + catId + '\'' + 
			",product_name = '" + productName + '\'' + 
			",product_image = '" + productImage + '\'' + 
			",product_image1 = '" + productImage1 + '\'' + 
			",product_image2 = '" + productImage2 + '\'' + 
			",product_image3 = '" + productImage3 + '\'' + 
			",product_image4 = '" + productImage4 + '\'' + 
			"}";
		}
}
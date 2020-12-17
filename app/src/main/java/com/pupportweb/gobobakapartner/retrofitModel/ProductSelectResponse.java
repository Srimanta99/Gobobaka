package com.pupportweb.gobobakapartner.retrofitModel;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;
import java.io.Serializable;

@Generated("com.robohorse.robopojogenerator")
public class ProductSelectResponse implements Serializable {

	@JsonProperty("status")
	private String status;

	@JsonProperty("message")
	private String message;

	@JsonProperty("data")
	private List<ProductSelectDataItem> data;

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setData(List<ProductSelectDataItem> data){
		this.data = data;
	}

	public List<ProductSelectDataItem> getData(){
		return data;
	}

	@Override
 	public String toString(){
		return 
			"ProductSelectResponse{" + 
			"status = '" + status + '\'' + 
			",message = '" + message + '\'' + 
			",data = '" + data + '\'' + 
			"}";
		}
}
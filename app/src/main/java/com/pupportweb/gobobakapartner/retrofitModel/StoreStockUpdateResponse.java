package com.pupportweb.gobobakapartner.retrofitModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;
import java.io.Serializable;

@Generated("com.robohorse.robopojogenerator")
public class StoreStockUpdateResponse implements Serializable {

	@JsonProperty("status")
	private String status;

	@JsonProperty("message")
	private String message;

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

	@Override
 	public String toString(){
		return 
			"StoreStockUpdateResponse{" + 
			"status = '" + status + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}
package com.pupportweb.gobobakapartner.retrofitModel;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;
import java.io.Serializable;

@Generated("com.robohorse.robopojogenerator")
public class EarnResponse implements Serializable {

	@JsonProperty("status")
	private String status;

	@JsonProperty("message")
	private String message;

	@JsonProperty("data")
	private EarnData earnData;

	@JsonProperty("store_earnings")
	private List<StoreEarningsItem> storeEarnings;

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

	public void setEarnData(EarnData earnData){
		this.earnData = earnData;
	}

	public EarnData getEarnData(){
		return earnData;
	}

	public void setStoreEarnings(List<StoreEarningsItem> storeEarnings){
		this.storeEarnings = storeEarnings;
	}

	public List<StoreEarningsItem> getStoreEarnings(){
		return storeEarnings;
	}

	@Override
 	public String toString(){
		return 
			"EarnResponse{" + 
			"status = '" + status + '\'' + 
			",message = '" + message + '\'' + 
			",data = '" + earnData + '\'' +
			",store_earnings = '" + storeEarnings + '\'' + 
			"}";
		}
}
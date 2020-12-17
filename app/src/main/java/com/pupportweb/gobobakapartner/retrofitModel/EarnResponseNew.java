package com.pupportweb.gobobakapartner.retrofitModel;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class EarnResponseNew implements Serializable {

	@JsonProperty("status")
	private String status;

	@JsonProperty("message")
	private String message;

	@JsonProperty("data")
	private EarnData earnData;



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





	@Override
 	public String toString(){
		return 
			"EarnResponse{" + 
			"status = '" + status + '\'' + 
			",message = '" + message + '\'' + 
			",data = '" + earnData + '\'' +
			"}";
		}
}
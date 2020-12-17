package com.pupportweb.gobobakapartner.retrofitModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;
import java.io.Serializable;

@Generated("com.robohorse.robopojogenerator")
public class StoreRegistrationResponse implements Serializable {

	@JsonProperty("status")
	private String status;

	@JsonProperty("message")
	private String message;

	@JsonProperty("data")
	private RegistrationData registrationData;

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

	public void setRegistrationData(RegistrationData registrationData){
		this.registrationData = registrationData;
	}

	public RegistrationData getRegistrationData(){
		return registrationData;
	}

	@Override
 	public String toString(){
		return 
			"StoreRegistrationResponse{" + 
			"status = '" + status + '\'' + 
			",message = '" + message + '\'' + 
			",data = '" + registrationData + '\'' +
			"}";
		}
}
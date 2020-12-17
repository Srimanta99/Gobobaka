package com.pupportweb.gobobakapartner.retrofitModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;
import java.io.Serializable;

@Generated("com.robohorse.robopojogenerator")
public class EarnData implements Serializable {

	@JsonProperty("paid")
	private Object paid;

	@JsonProperty("sumprice")
	private Object sumprice;

	@JsonProperty("securityScore")
	private Object securityScore;

	public void setPaid(Object paid){
		this.paid = paid;
	}

	public Object getPaid(){
		return paid;
	}

	public void setSumprice(Object sumprice){
		this.sumprice = sumprice;
	}

	public Object getSumprice(){
		return sumprice;
	}

	public void setSecurityScore(Object securityScore){
		this.securityScore = securityScore;
	}

	public Object getSecurityScore(){
		return securityScore;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"paid = '" + paid + '\'' + 
			",sumprice = '" + sumprice + '\'' + 
			",securityScore = '" + securityScore + '\'' + 
			"}";
		}
}
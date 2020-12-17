package com.pupportweb.gobobakapartner.retrofitModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;
import java.io.Serializable;

@Generated("com.robohorse.robopojogenerator")
public class RegistrationData implements Serializable {

	@JsonProperty("store_id")
	private int storeId;

	@JsonProperty("store_name")
	private Object storeName;

	@JsonProperty("employee_name")
	private String employeeName;

	@JsonProperty("phone_number")
	private String phoneNumber;

	@JsonProperty("city")
	private Object city;

	@JsonProperty("admin_share")
	private int adminShare;

	@JsonProperty("device_id")
	private String deviceId;

	@JsonProperty("email")
	private String email;

	@JsonProperty("is_verified")
	private int isVerified;

	@JsonProperty("password")
	private String password;

	@JsonProperty("del_range")
	private Object delRange;

	@JsonProperty("lat")
	private Object lat;

	@JsonProperty("lng")
	private Object lng;

	@JsonProperty("reg_date")
	private String regDate;

	@JsonProperty("address")
	private String address;

	@JsonProperty("address_proof")
	private String addressProof;

	@JsonProperty("business_doc")
	private String businessDoc;

	@JsonProperty("bank_detail")
	private String bankDetail;

	@JsonProperty("otp_value")
	private Object otpValue;

	@JsonProperty("refer_code")
	private String referCode;

	public void setStoreId(int storeId){
		this.storeId = storeId;
	}

	public int getStoreId(){
		return storeId;
	}

	public void setStoreName(Object storeName){
		this.storeName = storeName;
	}

	public Object getStoreName(){
		return storeName;
	}

	public void setEmployeeName(String employeeName){
		this.employeeName = employeeName;
	}

	public String getEmployeeName(){
		return employeeName;
	}

	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneNumber(){
		return phoneNumber;
	}

	public void setCity(Object city){
		this.city = city;
	}

	public Object getCity(){
		return city;
	}

	public void setAdminShare(int adminShare){
		this.adminShare = adminShare;
	}

	public int getAdminShare(){
		return adminShare;
	}

	public void setDeviceId(String deviceId){
		this.deviceId = deviceId;
	}

	public String getDeviceId(){
		return deviceId;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setIsVerified(int isVerified){
		this.isVerified = isVerified;
	}

	public int getIsVerified(){
		return isVerified;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setDelRange(Object delRange){
		this.delRange = delRange;
	}

	public Object getDelRange(){
		return delRange;
	}

	public void setLat(Object lat){
		this.lat = lat;
	}

	public Object getLat(){
		return lat;
	}

	public void setLng(Object lng){
		this.lng = lng;
	}

	public Object getLng(){
		return lng;
	}

	public void setRegDate(String regDate){
		this.regDate = regDate;
	}

	public String getRegDate(){
		return regDate;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	public void setAddressProof(String addressProof){
		this.addressProof = addressProof;
	}

	public String getAddressProof(){
		return addressProof;
	}

	public void setBusinessDoc(String businessDoc){
		this.businessDoc = businessDoc;
	}

	public String getBusinessDoc(){
		return businessDoc;
	}

	public void setBankDetail(String bankDetail){
		this.bankDetail = bankDetail;
	}

	public String getBankDetail(){
		return bankDetail;
	}

	public void setOtpValue(Object otpValue){
		this.otpValue = otpValue;
	}

	public Object getOtpValue(){
		return otpValue;
	}

	public void setReferCode(String referCode){
		this.referCode = referCode;
	}

	public String getReferCode(){
		return referCode;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"store_id = '" + storeId + '\'' + 
			",store_name = '" + storeName + '\'' + 
			",employee_name = '" + employeeName + '\'' + 
			",phone_number = '" + phoneNumber + '\'' + 
			",city = '" + city + '\'' + 
			",admin_share = '" + adminShare + '\'' + 
			",device_id = '" + deviceId + '\'' + 
			",email = '" + email + '\'' + 
			",is_verified = '" + isVerified + '\'' + 
			",password = '" + password + '\'' + 
			",del_range = '" + delRange + '\'' + 
			",lat = '" + lat + '\'' + 
			",lng = '" + lng + '\'' + 
			",reg_date = '" + regDate + '\'' + 
			",address = '" + address + '\'' + 
			",address_proof = '" + addressProof + '\'' + 
			",business_doc = '" + businessDoc + '\'' + 
			",bank_detail = '" + bankDetail + '\'' + 
			",otp_value = '" + otpValue + '\'' + 
			",refer_code = '" + referCode + '\'' + 
			"}";
		}
}
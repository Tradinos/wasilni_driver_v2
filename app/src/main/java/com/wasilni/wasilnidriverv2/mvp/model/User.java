package com.wasilni.wasilnidriverv2.mvp.model;

import android.location.Location;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by bpn on 11/30/17.
 */

public class User{

    @SerializedName("location")
    @Expose
    private Location location ;
    @SerializedName("isLogingIn")
    @Expose
    private boolean isLogingIn ;
    @SerializedName("isChecked")
    @Expose
    private boolean isChecked ;
    @SerializedName("isConfirmed")
    @Expose
    private boolean isConfirmed ;
    @SerializedName("last_check")
    @Expose
    private Integer last_check ;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("email")
    @Expose
    private String  email;
    @SerializedName("accessToken")
    @Expose
    private String  accessToken ;
    @SerializedName("first_name")
    @Expose
    private String  first_name;
    @SerializedName("last_name")
    @Expose
    private String last_name ;
    @SerializedName("phone_number")
    @Expose
    private String  phone_number ;
    @SerializedName("birthday")
    @Expose
    private String birthday;
    @SerializedName("profile_image")
    @Expose
    private String profile_image;
    @SerializedName("whatsapp_number")
    @Expose
    private String whatsapp_number;
    @SerializedName("id_image")
    @Expose
    private String  id_image;
    @SerializedName("password")
    @Expose
    private String  password;
    @SerializedName("password_confirmation")
    @Expose
    private String  password_confirmation ;
    @SerializedName("activation_code")
    @Expose
    private String  activation_code;
    @SerializedName("address")
    @Expose
    private String address  ;
    @SerializedName("driving_certificate_start_date")
    @Expose
    private String driving_certificate_start_date;
    @SerializedName("driving_certificate_end_date")
    @Expose
    private String driving_certificate_end_date;
    @SerializedName("verified")
    @Expose
    private boolean verified ;
    @SerializedName("gender")
    @Expose
    private boolean  gender;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("location_id")
    @Expose
    private Integer  location_id;
    @SerializedName("nationality_id")
    @Expose
    private Integer  nationality_id;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Boolean getLogingIn() {
        return isLogingIn;
    }

    public void setLogingIn(Boolean logingIn) {
        isLogingIn = logingIn;
    }

    public boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }

    public Boolean getConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        isConfirmed = confirmed;
    }

    public Integer getLast_check() {
        return last_check;
    }

    public void setLast_check(Integer last_check) {
        this.last_check = last_check;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getWhatsapp_number() {
        return whatsapp_number;
    }

    public void setWhatsapp_number(String whatsapp_number) {
        this.whatsapp_number = whatsapp_number;
    }

    public String getId_image() {
        return id_image;
    }

    public void setId_image(String id_image) {
        this.id_image = id_image;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword_confirmation() {
        return password_confirmation;
    }

    public void setPassword_confirmation(String password_confirmation) {
        this.password_confirmation = password_confirmation;
    }

    public String getActivation_code() {
        return activation_code;
    }

    public void setActivation_code(String activation_code) {
        this.activation_code = activation_code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDriving_certificate_start_date() {
        return driving_certificate_start_date;
    }

    public void setDriving_certificate_start_date(String driving_certificate_start_date) {
        this.driving_certificate_start_date = driving_certificate_start_date;
    }

    public String getDriving_certificate_end_date() {
        return driving_certificate_end_date;
    }

    public void setDriving_certificate_end_date(String driving_certificate_end_date) {
        this.driving_certificate_end_date = driving_certificate_end_date;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLocation_id() {
        return location_id;
    }

    public void setLocation_id(Integer location_id) {
        this.location_id = location_id;
    }

    public Integer getNationality_id() {
        return nationality_id;
    }

    public void setNationality_id(Integer nationality_id) {
        this.nationality_id = nationality_id;
    }
}

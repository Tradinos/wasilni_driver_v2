package com.wasilni.wasilnidriverv2.mvp.model;

import android.location.Location;

/**
 * Created by bpn on 11/30/17.
 */

public class User{

    private Location location ;
    private boolean isLogingIn ;
    private boolean isChecked ;
    private boolean isConfirmed ;
    private String username = "", email = "", accessToken="" , first_name="",
            last_name="" , phone_number="" ,birthday="" ,profile_image="",
            whatsapp_number="" , id_image=""
            ;
    private boolean verified , gender;
    private int id ;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getId_image() {
        return id_image;
    }

    public void setId_image(String id_image) {
        this.id_image = id_image;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_imag) {
        this.profile_image = profile_imag;
    }

    public String getWhatsapp_number() {
        return whatsapp_number;
    }

    public void setWhatsapp_number(String whatsapp_number) {
        this.whatsapp_number = whatsapp_number;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return  "verified:"+verified+"\nid:"+id+"\nEmail : " + email + "\nFullName : " + username + "\nfirst name :" +
                first_name + "\nlast name :" +last_name + "\ngender :"+gender +
                "\nphone number :" + phone_number + "\ntoken:"+accessToken;
    }




    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }

    public boolean isLogingIn() {
        return isLogingIn;
    }

    public void setLogingIn(boolean logingIn) {
        isLogingIn = logingIn;
    }

}

package com.codeguru.g_ultimate.models;

public class Mdl_profile {

    private int profile_id;
    private String name;
    private String surname;
    private String date_birth;
    private String gender;
    private String tel;
    private String email;
    private String residence;
    private int image;

    public Mdl_profile() {
        super();
    }

    public Mdl_profile(int profile_id) {
         super();
         this.profile_id = profile_id;
    }
    
    public void setProfile_id(int profile_id) {
       
        this.profile_id = profile_id;
    }

    public int getProfile_id() {
        return profile_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    public void setDate_birth(String date_birth) {
        this.date_birth = date_birth;
    }

    public String getDate_birth() {
        return date_birth;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTel() {
        return tel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public String getResidence() {
        return residence;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getImage() {
        return image;
    }

}

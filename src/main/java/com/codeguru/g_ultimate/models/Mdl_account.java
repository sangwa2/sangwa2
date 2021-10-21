package com.codeguru.g_ultimate.models;

public class Mdl_account {

    private int account_id;
    private String name;
    private int account_category;
    private String username;
    private String password;
    private int profile;

    public Mdl_account() {
        super();
    }

    public Mdl_account(int account_id) {
        super();
        this.account_id = account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAccount_category(int account_category) {
        this.account_category = account_category;
    }

    public int getAccount_category() {
        return account_category;
    }

    public void setProfile(int profile) {
        this.profile = profile;
    }

    public int getProfile() {
        return profile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
    
}

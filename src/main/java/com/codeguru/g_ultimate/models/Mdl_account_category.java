package com.codeguru.g_ultimate.models;

 public class  Mdl_account_category {
private int account_category_id;
private String name;
 public Mdl_account_category() {
        super();
    }

    public Mdl_account_category(int account_category_id) {
        super();
        this.account_category_id = account_category_id;
    }  
 public void setAccount_category_id(int account_category_id) {
        this.account_category_id = account_category_id;
    }
public int getAccount_category_id() {
        return account_category_id;
    }

 public void setName(String name) {
        this.name = name;
    }
public String getName() {
        return name;
    }




}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.models;

/**
 *
 * @author SANGWA
 */
public class Mdl_structure {
    
    private int structure_id;
    private String db_name;
    private String db_user;
    private String db_password;
    private int cash_total;
    private String start_time;
    private String delivery_date;
    private String pgm_language;
    private String platform;
    private String entry_date;
    private String User;

    public Mdl_structure() {
        super();
    }
 
    public Mdl_structure(int structure_id) {
        super();
        this.structure_id = structure_id;
    }
    
    public int getStructure_id() {
        return structure_id;
    }

    public void setStructure_id(int structure_id) {
        this.structure_id = structure_id;
    }

    public String getDb_name() {
        return db_name;
    }

    public void setDb_name(String db_name) {
        this.db_name = db_name;
    }

    public String getDb_user() {
        return db_user;
    }

    public void setDb_user(String db_user) {
        this.db_user = db_user;
    }

    public String getDb_password() {
        return db_password;
    }

    public void setDb_password(String db_password) {
        this.db_password = db_password;
    }

    public int getCash_total() {
        return cash_total;
    }

    public void setCash_total(int cash_total) {
        this.cash_total = cash_total;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(String delivery_date) {
        this.delivery_date = delivery_date;
    }

    public String getPgm_language() {
        return pgm_language;
    }

    public void setPgm_language(String pgm_language) {
        this.pgm_language = pgm_language;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getEntry_date() {
        return entry_date;
    }

    public void setEntry_date(String entry_date) {
        this.entry_date = entry_date;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String User) {
        this.User = User;
    }

}

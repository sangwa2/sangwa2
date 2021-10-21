/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.files.db;

/**
 *
 * @author SANGWA
 */
public class Model {
    private int structure;
    
    private int unit_id;
    private String unit_name;
    private String tuple_name;
    private String db_name;
    private String db_user;
    private String password;
    private int cash_total;
    private String start_time;
    private String delivery_date;
    private String pgm_language;
    private String platform;
    private String data_type;
    private String category;
    private String display_caption;
    private String today_date;
    
    public Model(int structure, String unit_name, String db_name, String db_user, String password, int cash_total, String start_time, String delivery_date, String pgm_language, String platform) {
        this.structure = structure;
        this.unit_name = unit_name;
        this.db_name = db_name;
        this.db_user = db_user;
        this.password = db_user;
        this.cash_total = cash_total;
        this.start_time = start_time;
        this.delivery_date = delivery_date;
        this.pgm_language = pgm_language;
        this.platform = platform;
    }

    public Model() {
        super();
    }

    public Model(int structure, String unit_name, String tuple_name, String db_name, String db_user, String password, int cash_total, String start_time, String delivery_date, String pgm_language, String platform) {
        this.structure = structure;
        this.unit_name = unit_name;
        this.tuple_name = tuple_name;
        this.db_name = db_name;
        this.db_user = db_user;
        this.password = password;
        this.cash_total = cash_total;
        this.start_time = start_time;
        this.delivery_date = delivery_date;
        this.pgm_language = pgm_language;
        this.platform = platform;
    }

    public int getStructure() {
        return structure;
    }

    public void setStructure(int structure) {
        this.structure = structure;
    }

    public String getUnit_name() {
        return unit_name;
    }

    public void setUnit_name(String unit_name) {
        this.unit_name = unit_name;
    }

    public String getTuple_name() {
        return tuple_name;
    }

    public void setTuple_name(String tuple_name) {
        this.tuple_name = tuple_name;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public int getUnit_id() {
        return unit_id;
    }

    public void setUnit_id(int unit_id) {
        this.unit_id = unit_id;
    }

    public String getData_type() {
        return data_type;
    }

    public void setData_type(String data_type) {
        this.data_type = data_type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDisplay_caption() {
        return display_caption;
    }

    public void setDisplay_caption(String display_caption) {
        this.display_caption = display_caption;
    }

    public String getToday_date() {
        return today_date;
    }

    public void setToday_date(String today_date) {
        this.today_date = today_date;
    }
 
    
}

package com.codeguru.g_ultimate.models;

 public class  Mdl_tuple_settings {
private int tuple_settings_id;
private int tuple;
private String enable_update;
private String input_length;
private String number_only;
private String number_andthousand_separator;
private String other_format;
private String auto_date_time;
private String auto_date;
private String auto_time;
private String auto_logged_in_user;
private String auto_filled_from_other_field;
private String view_on_insert;
private String view_on_datalist;
 public Mdl_tuple_settings() {
        super();
    }

    public Mdl_tuple_settings(int tuple_settings_id) {
        super();
        this.tuple_settings_id = tuple_settings_id;
    }  
 public void setTuple_settings_id(int tuple_settings_id) {
        this.tuple_settings_id = tuple_settings_id;
    }
public int getTuple_settings_id() {
        return tuple_settings_id;
    }

 public void setTuple(int tuple) {
        this.tuple = tuple;
    }
public int getTuple() {
        return tuple;
    }

 public void setEnable_update(String enable_update) {
        this.enable_update = enable_update;
    }
public String getEnable_update() {
        return enable_update;
    }

 public void setInput_length(String input_length) {
        this.input_length = input_length;
    }
public String getInput_length() {
        return input_length;
    }

 public void setNumber_only(String number_only) {
        this.number_only = number_only;
    }
public String getNumber_only() {
        return number_only;
    }

 public void setNumber_andthousand_separator(String number_andthousand_separator) {
        this.number_andthousand_separator = number_andthousand_separator;
    }
public String getNumber_andthousand_separator() {
        return number_andthousand_separator;
    }

 public void setOther_format(String other_format) {
        this.other_format = other_format;
    }
public String getOther_format() {
        return other_format;
    }

 public void setAuto_date_time(String auto_date_time) {
        this.auto_date_time = auto_date_time;
    }
public String getAuto_date_time() {
        return auto_date_time;
    }

 public void setAuto_date(String auto_date) {
        this.auto_date = auto_date;
    }
public String getAuto_date() {
        return auto_date;
    }

 public void setAuto_time(String auto_time) {
        this.auto_time = auto_time;
    }
public String getAuto_time() {
        return auto_time;
    }

 public void setAuto_logged_in_user(String auto_logged_in_user) {
        this.auto_logged_in_user = auto_logged_in_user;
    }
public String getAuto_logged_in_user() {
        return auto_logged_in_user;
    }

 public void setAuto_filled_from_other_field(String auto_filled_from_other_field) {
        this.auto_filled_from_other_field = auto_filled_from_other_field;
    }
public String getAuto_filled_from_other_field() {
        return auto_filled_from_other_field;
    }

 public void setView_on_insert(String view_on_insert) {
        this.view_on_insert = view_on_insert;
    }
public String getView_on_insert() {
        return view_on_insert;
    }

 public void setView_on_datalist(String view_on_datalist) {
        this.view_on_datalist = view_on_datalist;
    }
public String getView_on_datalist() {
        return view_on_datalist;
    }




}


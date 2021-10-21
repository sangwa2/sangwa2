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
public class Mdl_tuple {

    private int tuple_id;
    private int unit;
    private String name;
    private String data_type;
    private String category;
    private String display_caption;
    private String today_date;
    private String curr_date;

    
    private String fkdisp_type;
    public Mdl_tuple(String name, String data_type) {
        this.name = name;
        this.data_type = data_type;
    }
    

    public Mdl_tuple(int tuple_id) {
        super();
        this.tuple_id = tuple_id;
    }

    public Mdl_tuple() {
        super();
    }

    public Mdl_tuple(int tuple_id,  int unit) {
        this.tuple_id = tuple_id;
        
        this.unit=unit;
    }

    
    
    
    public int getTuple_id() {
        return tuple_id;
    }

    public void setTuple_id(int tuple_id) {
        this.tuple_id = tuple_id;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
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

    public String getCurr_date() {
        return curr_date;
    }

    public void setCurr_date(String curr_date) {
        this.curr_date = curr_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFkdisp_type() {
        return fkdisp_type;
    }

    public void setFkdisp_type(String fkdisp_type) {
        this.fkdisp_type = fkdisp_type;
    }

    
    
}

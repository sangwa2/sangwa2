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

public class Mdl_fk_order {
    private int fk_order_id;//fk_order_id, layout_id, unit, tuple, disp_type
    private int layout_id;
    private int unit;
    private int tuple;
    private String disp_type;
    
     
    public Mdl_fk_order() {
        super();
    }

    public Mdl_fk_order(int fk_order_id) {
        super();
        this.fk_order_id = fk_order_id;
    }

    public int getFk_order_id() {
        return fk_order_id;
    }

    public void setFk_order_id(int fk_order_id) {
        this.fk_order_id = fk_order_id;
    }

    public int getLayout_id() {
        return layout_id;
    }

    public void setLayout_id(int layout_id) {
        this.layout_id = layout_id;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public int getTuple() {
        return tuple;
    }

    public void setTuple(int tuple) {
        this.tuple = tuple;
    }

    public String getDisp_type() {
        return disp_type;
    }

    public void setDisp_type(String disp_type) {
        this.disp_type = disp_type;
    }

   
    
    
    
    
    
    
}

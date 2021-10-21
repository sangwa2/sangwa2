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
public class Mdl_unit {
    private int unit_id;
    private int structure;
    private String name;
    private int number_children;
    private int number_parent;

    
    //addon
    private int layoutid;
    public Mdl_unit() {
        super();
    }

    public Mdl_unit(int unit_id) {
        super();
        this.unit_id = unit_id;
    }
        
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    public int getUnit_id() {
        return unit_id;
    }

    public void setUnit_id(int unit_id) {
        this.unit_id = unit_id;
    }

    public int getStructure() {
        return structure;
    }

    public void setStructure(int structure) {
        this.structure = structure;
    }

    public int getNumber_children() {
        return number_children;
    }

    public void setNumber_children(int number_children) {
        this.number_children = number_children;
    }

    public int getNumber_parent() {
        return number_parent;
    }

    public void setNumber_parent(int number_parent) {
        this.number_parent = number_parent;
    }

    public int getLayoutid() {
        return layoutid;
    }

    public void setLayoutid(int layoutid) {
        this.layoutid = layoutid;
    }
    
    
}

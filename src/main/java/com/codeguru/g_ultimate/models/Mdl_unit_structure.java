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
public class Mdl_unit_structure {
    private int unit_id;
    private String name;
    private int structure;
    
    private String tuple_name;
    private int tuple_id;

    public int getTuple_id() {
        return tuple_id;
    }

    public void setTuple_id(int tuple_id) {
        this.tuple_id = tuple_id;
    }

    public int getUnit_id() {
        return unit_id;
    }

    public void setUnit_id(int unit_id) {
        this.unit_id = unit_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStructure() {
        return structure;
    }

    public void setStructure(int structure) {
        this.structure = structure;
    }

    public String getTuple_name() {
        return tuple_name;
    }

    public void setTuple_name(String tuple_name) {
        this.tuple_name = tuple_name;
    }

      
}

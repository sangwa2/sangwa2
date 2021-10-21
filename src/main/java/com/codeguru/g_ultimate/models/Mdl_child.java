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
public class Mdl_child {
    private int child_id;
    private int unit_id;
    private int parent_id;
    private String relation_type;

    public Mdl_child() {
        super();
    }

    public Mdl_child(int child_id) {
        super();
        this.child_id = child_id;
    }
    
    public int getChild_id() {
        return child_id;
    }

    public void setChild_id(int child_id) {
        this.child_id = child_id;
    }

    public int getUnit_id() {
        return unit_id;
    }

    public void setUnit_id(int unit_id) {
        this.unit_id = unit_id;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public String getRelation_type() {
        return relation_type;
    }

    public void setRelation_type(String relation_type) {
        this.relation_type = relation_type;
    }
    
    
}

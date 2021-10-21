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
public class Mdl_relations {

    private int relations_id;
    private int child_unit_id;
    private int parent_unit_id;
    private String disp_type;

    public Mdl_relations() {
        super();
    }
    public Mdl_relations(int relations_id) {
        super();
        this.relations_id = relations_id;
    }

    public int getRelations_id() {
        return relations_id;
    }

    public void setRelations_id(int relations_id) {
        this.relations_id = relations_id;
    }

    public int getChild_unit_id() {
        return child_unit_id;
    }

    public void setChild_unit_id(int child_unit_id) {
        this.child_unit_id = child_unit_id;
    }

    public int getParent_unit_id() {
        return parent_unit_id;
    }

    public void setParent_unit_id(int parent_unit_id) {
        this.parent_unit_id = parent_unit_id;
    }

    public String getDisp_type() {
        return disp_type;
    }

    public void setDisp_type(String disp_type) {
        this.disp_type = disp_type;
    }

}

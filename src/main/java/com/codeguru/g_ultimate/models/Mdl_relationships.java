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
public class Mdl_relationships {

    private int relationships_id;
    private int parent_id;
    private int child_id;
    private int parent_count;
    private int child_count;

    public Mdl_relationships() {
        super();
    }

    public Mdl_relationships(int relationships_id) {
        super();
        this.relationships_id = relationships_id;
    }

    public int getRelationships_id() {
        return relationships_id;
    }

    public void setRelationships_id(int relationships_id) {
        this.relationships_id = relationships_id;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public int getChild_id() {
        return child_id;
    }

    public void setChild_id(int child_id) {
        this.child_id = child_id;
    }

    public int getParent_count() {
        return parent_count;
    }

    public void setParent_count(int parent_count) {
        this.parent_count = parent_count;
    }

    public int getChild_count() {
        return child_count;
    }

    public void setChild_count(int child_count) {
        this.child_count = child_count;
    }

}

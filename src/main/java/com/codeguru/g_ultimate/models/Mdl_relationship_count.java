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
public class Mdl_relationship_count {

    private int relationship_count_id;
    private int tuple_id;
    private int number_children;
    private int number_parent;

    public Mdl_relationship_count() {
        super();
    }

    public Mdl_relationship_count(int tuple_id) {
        super();
        this.tuple_id = tuple_id;
    }

    public int getRelationship_count_id() {
        return relationship_count_id;
    }

    public void setRelationship_count_id(int relationship_count_id) {
        this.relationship_count_id = relationship_count_id;
    }

    public int getTuple_id() {
        return tuple_id;
    }

    public void setTuple_id(int tuple_id) {
        this.tuple_id = tuple_id;
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

}

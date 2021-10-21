package com.codeguru.g_ultimate.models;

public class Mdl_rel_query {

    private int rel_query_id;
    private int unit;
    private int tuple;
    private int rel_query_category;
    private String name;
    private int structure;

    private String parent;
    private String child;
    private String join;

    public Mdl_rel_query() {
        super();
    }

    public Mdl_rel_query(int rel_query_id) {
        super();
        this.rel_query_id = rel_query_id;
    }

    public void setRel_query_id(int rel_query_id) {
        this.rel_query_id = rel_query_id;
    }

    public int getRel_query_id() {
        return rel_query_id;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public int getUnit() {
        return unit;
    }

    public void setTuple(int tuple) {
        this.tuple = tuple;
    }

    public int getTuple() {
        return tuple;
    }

    public void setRel_query_category(int rel_query_category) {
        this.rel_query_category = rel_query_category;
    }

    public int getRel_query_category() {
        return rel_query_category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Mdl_rel_query(int unit, int tuple) {
        this.unit = unit;
        this.tuple = tuple;

    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getChild() {
        return child;
    }

    public void setChild(String child) {
        this.child = child;
    }

    public Mdl_rel_query(String parent, String child) {
        this.parent = parent;
        this.child = child;
    }

    
    public int getStructure() {
        return structure;
    }

    public void setStructure(int structure) {
        this.structure = structure;
    }

    public String getJoin() {
        return join;
    }

    public void setJoin(String join) {
        this.join = join;
    }

    
    /**
     * This is the final array tht will bring the finishec joins together
     * @param parent
     * @param child
     * @param join 
     */
    public Mdl_rel_query(String parent, String child, String join) {
        this.parent = parent;
        this.child = child;
        this.join = join;
    }

    
}

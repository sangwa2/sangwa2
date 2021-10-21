package com.codeguru.g_ultimate.models;

public class Mdl_rel_query_category {

    private int rel_query_category_id;
    private String name;

    public Mdl_rel_query_category() {
        super();
    }

    public Mdl_rel_query_category(int rel_query_category_id) {
        super();
        this.rel_query_category_id = rel_query_category_id;
    }

    public void setRel_query_category_id(int rel_query_category_id) {
        this.rel_query_category_id = rel_query_category_id;
    }

    public int getRel_query_category_id() {
        return rel_query_category_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}

package com.codeguru.g_ultimate.models;

public class Mdl_f_end_layout {

    private int f_end_layout_id;
    private String name;
    private int layout_type;
    private int unit;
    private int order;
    private int supplied_index;

    public Mdl_f_end_layout() {
        super();
    }

    public Mdl_f_end_layout(int f_end_layout_id) {
        super();
        this.f_end_layout_id = f_end_layout_id;
    }

    public void setF_end_layout_id(int f_end_layout_id) {
        this.f_end_layout_id = f_end_layout_id;
    }

    public int getF_end_layout_id() {
        return f_end_layout_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLayout_type(int layout_type) {
        this.layout_type = layout_type;
    }

    public int getLayout_type() {
        return layout_type;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public int getUnit() {
        return unit;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getOrder() {
        return order;
    }

    public void setSupplied_index(int supplied_index) {
        this.supplied_index = supplied_index;
    }

    public int getSupplied_index() {
        return supplied_index;
    }

}

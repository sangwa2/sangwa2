package com.codeguru.g_ultimate.models;

public class Mdl_g_properties {

    private int g_properties_id;
    private String name;
    private String default_value;

    public Mdl_g_properties() {
        super();
    }

    public Mdl_g_properties(int g_properties_id) {
        super();
        this.g_properties_id = g_properties_id;
    }

    public void setG_properties_id(int g_properties_id) {
        this.g_properties_id = g_properties_id;
    }

    public int getG_properties_id() {
        return g_properties_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDefault_value(String default_value) {
        this.default_value = default_value;
    }

    public String getDefault_value() {
        return default_value;
    }

}

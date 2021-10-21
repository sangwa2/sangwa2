package com.codeguru.g_ultimate.models;

public class Mdl_f_end_data_list_template {

    private int f_end_data_list_template_id;
    private String name;
    private String description;

    public Mdl_f_end_data_list_template() {
        super();
    }

    public Mdl_f_end_data_list_template(int f_end_data_list_template_id) {
        super();
        this.f_end_data_list_template_id = f_end_data_list_template_id;
    }

    public void setF_end_data_list_template_id(int f_end_data_list_template_id) {
        this.f_end_data_list_template_id = f_end_data_list_template_id;
    }

    public int getF_end_data_list_template_id() {
        return f_end_data_list_template_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}

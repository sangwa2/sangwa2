package com.codeguru.g_ultimate.models;

public class Mdl_f_end_form_template {

    private int f_end_form_template_id;
    private String name;
    private int html_code;

    public Mdl_f_end_form_template() {
        super();
    }

    public Mdl_f_end_form_template(int f_end_form_template_id) {
        super();
        this.f_end_form_template_id = f_end_form_template_id;
    }

    public void setF_end_form_template_id(int f_end_form_template_id) {
        this.f_end_form_template_id = f_end_form_template_id;
    }

    public int getF_end_form_template_id() {
        return f_end_form_template_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setHtml_code(int html_code) {
        this.html_code = html_code;
    }

    public int getHtml_code() {
        return html_code;
    }

}

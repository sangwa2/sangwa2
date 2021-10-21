package com.codeguru.g_ultimate.models;

 public class  Mdl_f_end_form {
private int f_end_form_id;
private int layout;
private int unit;
private int form_template;
 public Mdl_f_end_form() {
        super();
    }

    public Mdl_f_end_form(int f_end_form_id) {
        super();
        this.f_end_form_id = f_end_form_id;
    }  
 public void setF_end_form_id(int f_end_form_id) {
        this.f_end_form_id = f_end_form_id;
    }
public int getF_end_form_id() {
        return f_end_form_id;
    }

 public void setLayout(int layout) {
        this.layout = layout;
    }
public int getLayout() {
        return layout;
    }

 public void setUnit(int unit) {
        this.unit = unit;
    }
public int getUnit() {
        return unit;
    }

 public void setForm_template(int form_template) {
        this.form_template = form_template;
    }
public int getForm_template() {
        return form_template;
    }




}


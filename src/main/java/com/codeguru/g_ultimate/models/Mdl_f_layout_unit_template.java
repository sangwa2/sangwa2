package com.codeguru.g_ultimate.models;

public class Mdl_f_layout_unit_template {

    private int f_layout_unit_template_id;
    private int layout;
    private int unit;
    private int form_template;
    private String combo_form;

    public String getCombo_form() {
        return combo_form;
    }

    public void setCombo_form(String combo_form) {
        this.combo_form = combo_form;
    }
    
    public Mdl_f_layout_unit_template() {
        super();
    }

    public Mdl_f_layout_unit_template(int f_layout_unit_template_id) {
        super();
        this.f_layout_unit_template_id = f_layout_unit_template_id;
    }

    public void setF_layout_unit_template_id(int f_layout_unit_template_id) {
        this.f_layout_unit_template_id = f_layout_unit_template_id;
    }

    public int getF_layout_unit_template_id() {
        return f_layout_unit_template_id;
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

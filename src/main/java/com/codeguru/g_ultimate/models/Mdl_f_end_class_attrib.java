package com.codeguru.g_ultimate.models;

 public class  Mdl_f_end_class_attrib {
private int f_end_class_attrib_id;
private String attribute;
private String value;
 public Mdl_f_end_class_attrib() {
        super();
    }

    public Mdl_f_end_class_attrib(int f_end_class_attrib_id) {
        super();
        this.f_end_class_attrib_id = f_end_class_attrib_id;
    }  
 public void setF_end_class_attrib_id(int f_end_class_attrib_id) {
        this.f_end_class_attrib_id = f_end_class_attrib_id;
    }
public int getF_end_class_attrib_id() {
        return f_end_class_attrib_id;
    }

 public void setAttribute(String attribute) {
        this.attribute = attribute;
    }
public String getAttribute() {
        return attribute;
    }

 public void setValue(String value) {
        this.value = value;
    }
public String getValue() {
        return value;
    }




}


package com.brck.blog_musema_java.models;

 public class  Mdl_g_propreties {
private int g_propreties_id;
private String name;
private String default_value;
 public Mdl_g_propreties() {
        super();
    }

    public Mdl_g_propreties(int g_propreties_id) {
        super();
        this.g_propreties_id = g_propreties_id;
    }  
 public void setG_propreties_id(int g_propreties_id) {
        this.g_propreties_id = g_propreties_id;
    }
public int getG_propreties_id() {
        return g_propreties_id;
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


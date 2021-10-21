package com.codeguru.g_ultimate.models;

 public class  Mdl_f_end_datalist {
private int f_end_datalist_id;
private int query;
private int data_list_template;
private int layout;
 public Mdl_f_end_datalist() {
        super();
    }

    public Mdl_f_end_datalist(int f_end_datalist_id) {
        super();
        this.f_end_datalist_id = f_end_datalist_id;
    }  
 public void setF_end_datalist_id(int f_end_datalist_id) {
        this.f_end_datalist_id = f_end_datalist_id;
    }
public int getF_end_datalist_id() {
        return f_end_datalist_id;
    }

 public void setQuery(int query) {
        this.query = query;
    }
public int getQuery() {
        return query;
    }

 public void setData_list_template(int data_list_template) {
        this.data_list_template = data_list_template;
    }
public int getData_list_template() {
        return data_list_template;
    }

 public void setLayout(int layout) {
        this.layout = layout;
    }
public int getLayout() {
        return layout;
    }




}


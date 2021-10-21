package com.codeguru.g_ultimate.models;

 public class  Mdl_code_place {
private int code_place_id;
private String code_number;
private int html_code_line;
private int query;
 public Mdl_code_place() {
        super();
    }

    public Mdl_code_place(int code_place_id) {
        super();
        this.code_place_id = code_place_id;
    }  
 public void setCode_place_id(int code_place_id) {
        this.code_place_id = code_place_id;
    }
public int getCode_place_id() {
        return code_place_id;
    }

 public void setCode_number(String code_number) {
        this.code_number = code_number;
    }
public String getCode_number() {
        return code_number;
    }

 public void setHtml_code_line(int html_code_line) {
        this.html_code_line = html_code_line;
    }
public int getHtml_code_line() {
        return html_code_line;
    }

 public void setQuery(int query) {
        this.query = query;
    }
public int getQuery() {
        return query;
    }




}


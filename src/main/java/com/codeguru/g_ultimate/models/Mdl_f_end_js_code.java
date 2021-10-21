package com.codeguru.g_ultimate.models;

 public class  Mdl_f_end_js_code {
private int f_end_js_code_id;
private String code_line;
private int css;
private int js_code_category;
 public Mdl_f_end_js_code() {
        super();
    }

    public Mdl_f_end_js_code(int f_end_js_code_id) {
        super();
        this.f_end_js_code_id = f_end_js_code_id;
    }  
 public void setF_end_js_code_id(int f_end_js_code_id) {
        this.f_end_js_code_id = f_end_js_code_id;
    }
public int getF_end_js_code_id() {
        return f_end_js_code_id;
    }

 public void setCode_line(String code_line) {
        this.code_line = code_line;
    }
public String getCode_line() {
        return code_line;
    }

 public void setCss(int css) {
        this.css = css;
    }
public int getCss() {
        return css;
    }

 public void setJs_code_category(int js_code_category) {
        this.js_code_category = js_code_category;
    }
public int getJs_code_category() {
        return js_code_category;
    }




}


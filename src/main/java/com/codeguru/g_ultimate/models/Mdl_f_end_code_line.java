package com.codeguru.g_ultimate.models;

 public class  Mdl_f_end_code_line {
private int f_end_code_line_id;
private String name;
private String content;
private String content_type;
private int html_code;
 public Mdl_f_end_code_line() {
        super();
    }

    public Mdl_f_end_code_line(int f_end_code_line_id) {
        super();
        this.f_end_code_line_id = f_end_code_line_id;
    }  
 public void setF_end_code_line_id(int f_end_code_line_id) {
        this.f_end_code_line_id = f_end_code_line_id;
    }
public int getF_end_code_line_id() {
        return f_end_code_line_id;
    }

 public void setName(String name) {
        this.name = name;
    }
public String getName() {
        return name;
    }

 public void setContent(String content) {
        this.content = content;
    }
public String getContent() {
        return content;
    }

 public void setContent_type(String content_type) {
        this.content_type = content_type;
    }
public String getContent_type() {
        return content_type;
    }

 public void setHtml_code(int html_code) {
        this.html_code = html_code;
    }
public int getHtml_code() {
        return html_code;
    }




}


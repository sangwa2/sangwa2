package com.codeguru.g_ultimate.models;

public class Mdl_f_end_css {

    private int f_end_class_id;
    private String name;
    private int html_code_line;
    private String css_category;

    public Mdl_f_end_css() {
        super();
    }

    public Mdl_f_end_css(int f_end_css_id) {
        super();
        this.f_end_class_id = f_end_css_id;
    }

    public void setF_end_class_id(int f_end_class_id) {
        this.f_end_class_id = f_end_class_id;
    }

    public int getF_end_class_id() {
        return f_end_class_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setHtml_code_line(int html_code_line) {
        this.html_code_line = html_code_line;
    }

    public int getHtml_code_line() {
        return html_code_line;
    }

    public void setCss_category(String css_category) {
        this.css_category = css_category;
    }

    public String getCss_category() {
        return css_category;
    }

}

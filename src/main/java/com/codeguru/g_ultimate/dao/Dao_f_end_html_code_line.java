package com.codeguru.g_ultimate.dao;

import com.codeguru.g_ultimate.models.Mdl_f_end_html_code_line;
import java.util.List;

/**
 *
 * @author SANGWA
 */
public interface Dao_f_end_html_code_line {

    public List<Mdl_f_end_html_code_line> all_f_end_html_code_lines();

    
    public void add_f_end_html_code_line(Mdl_f_end_html_code_line f_end_html_code_line);

    public void delete_f_end_html_code_line(int f_end_html_code_line);

    public void update_f_end_html_code_line(Mdl_f_end_html_code_line f_end_html_code_line);

    
    public Mdl_f_end_html_code_line find_f_end_html_code_lineBy_id(int f_end_html_code_line);
    
    public int get_last_f_end_html_code_line();
}


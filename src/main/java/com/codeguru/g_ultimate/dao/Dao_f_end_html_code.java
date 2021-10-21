package com.codeguru.g_ultimate.dao;

import com.codeguru.g_ultimate.models.Mdl_f_end_html_code;
import java.util.List;

/**
 *
 * @author SANGWA
 */
public interface Dao_f_end_html_code {

    public List<Mdl_f_end_html_code> all_f_end_html_codes();

    
    public void add_f_end_html_code(Mdl_f_end_html_code f_end_html_code);

    public void delete_f_end_html_code(int f_end_html_code);

    public void update_f_end_html_code(Mdl_f_end_html_code f_end_html_code);

    
    public Mdl_f_end_html_code find_f_end_html_codeBy_id(int f_end_html_code);
    
    public int get_last_f_end_html_code();
}


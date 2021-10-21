package com.codeguru.g_ultimate.service;

import com.codeguru.g_ultimate.models.Mdl_f_end_css;
import java.util.List;

/**
 *
 * For author SANGWA  
 sangwa22@gmail.com 
 */
public interface Srv_f_end_css {

    public List<Mdl_f_end_css> all_f_end_csss();
    
    public void add_f_end_css(Mdl_f_end_css f_end_css);

    public void delete_f_end_css(int f_end_css);

    public void update_f_end_css(Mdl_f_end_css f_end_css);

    
    public Mdl_f_end_css find_f_end_cssBy_id(int f_end_css);
    
    public int get_last_f_end_css();
}


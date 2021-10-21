package com.codeguru.g_ultimate.service;

import com.codeguru.g_ultimate.models.Mdl_f_end_layout_type;
import java.util.List;

/**
 *
 * For author SANGWA  
 sangwa22@gmail.com 
 */
public interface Srv_f_end_layout_type {

    public List<Mdl_f_end_layout_type> f_end_lay_type_by_structure(int structure);
    
    public List<Mdl_f_end_layout_type> all_f_end_layout_types();
    
    public void add_f_end_layout_type(Mdl_f_end_layout_type f_end_layout_type);

    public void delete_f_end_layout_type(int f_end_layout_type);

    public void update_f_end_layout_type(Mdl_f_end_layout_type f_end_layout_type);

    
    public Mdl_f_end_layout_type find_f_end_layout_typeBy_id(int f_end_layout_type);
    public  Mdl_f_end_layout_type find_f_end_layout_typeBy_name(String type_name);
    public int get_last_f_end_layout_type();
}


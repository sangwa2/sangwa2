package com.codeguru.g_ultimate.service;

import com.codeguru.g_ultimate.models.Mdl_f_end_code_line;
import java.util.List;

/**
 *
 * For author SANGWA  
 sangwa22@gmail.com 
 */
public interface Srv_f_end_code_line {

    public List<Mdl_f_end_code_line> all_f_end_code_lines();
    
    public void add_f_end_code_line(Mdl_f_end_code_line f_end_code_line);

    public void delete_f_end_code_line(int f_end_code_line);

    public void update_f_end_code_line(Mdl_f_end_code_line f_end_code_line);

    
    public Mdl_f_end_code_line find_f_end_code_lineBy_id(int f_end_code_line);
    
    public int get_last_f_end_code_line();
}


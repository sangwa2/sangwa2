package com.codeguru.g_ultimate.dao;

import com.codeguru.g_ultimate.models.Mdl_f_end_form;
import java.util.List;

/**
 *
 * @author SANGWA
 */
public interface Dao_f_end_form {

    public List<Mdl_f_end_form> all_f_end_forms();

    
    public void add_f_end_form(Mdl_f_end_form f_end_form);

    public void delete_f_end_form(int f_end_form);

    public void update_f_end_form(Mdl_f_end_form f_end_form);

    
    public Mdl_f_end_form find_f_end_formBy_id(int f_end_form);
    
    public int get_last_f_end_form();
}


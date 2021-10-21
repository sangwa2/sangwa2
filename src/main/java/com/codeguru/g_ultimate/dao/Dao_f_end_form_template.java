package com.codeguru.g_ultimate.dao;

import com.codeguru.g_ultimate.models.Mdl_f_end_form_template;
import java.util.List;

/**
 *
 * @author SANGWA
 */
public interface Dao_f_end_form_template {

    public List<Mdl_f_end_form_template> all_f_end_form_templates();

    
    public void add_f_end_form_template(Mdl_f_end_form_template f_end_form_template);

    public void delete_f_end_form_template(int f_end_form_template);

    public void update_f_end_form_template(Mdl_f_end_form_template f_end_form_template);

    
    public Mdl_f_end_form_template find_f_end_form_templateBy_id(int f_end_form_template);
    
    public int get_last_f_end_form_template();
}


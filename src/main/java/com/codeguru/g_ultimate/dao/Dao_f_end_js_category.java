package com.codeguru.g_ultimate.dao;

import com.codeguru.g_ultimate.models.Mdl_f_end_js_category;
import java.util.List;

/**
 *
 * @author SANGWA
 */
public interface Dao_f_end_js_category {

    public List<Mdl_f_end_js_category> all_f_end_js_categorys();

    
    public void add_f_end_js_category(Mdl_f_end_js_category f_end_js_category);

    public void delete_f_end_js_category(int f_end_js_category);

    public void update_f_end_js_category(Mdl_f_end_js_category f_end_js_category);

    
    public Mdl_f_end_js_category find_f_end_js_categoryBy_id(int f_end_js_category);
    
    public int get_last_f_end_js_category();
}


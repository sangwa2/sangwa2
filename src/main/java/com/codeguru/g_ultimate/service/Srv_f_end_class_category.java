package com.codeguru.g_ultimate.service;

import com.codeguru.g_ultimate.models.Mdl_f_end_class_category;
import java.util.List;

/**
 *
 * For author SANGWA  
 sangwa22@gmail.com 
 */
public interface Srv_f_end_class_category {

    public List<Mdl_f_end_class_category> all_f_end_class_categorys();
    
    public void add_f_end_class_category(Mdl_f_end_class_category f_end_class_category);

    public void delete_f_end_class_category(int f_end_class_category);

    public void update_f_end_class_category(Mdl_f_end_class_category f_end_class_category);

    public Mdl_f_end_class_category find_f_end_class_categoryBy_id(int f_end_class_category);
    
    public int get_last_f_end_class_category();
}


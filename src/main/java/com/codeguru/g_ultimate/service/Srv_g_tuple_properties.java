package com.codeguru.g_ultimate.service;

import com.codeguru.g_ultimate.models.Mdl_g_tuple_properties;
import java.util.List;

/**
 *
 * For author SANGWA  
 sangwa22@gmail.com 
 */
public interface Srv_g_tuple_properties {

    public List<Mdl_g_tuple_properties> all_g_tuple_propertiess();
    
    public void add_g_tuple_properties(Mdl_g_tuple_properties g_tuple_properties);

    public void delete_g_tuple_properties(int g_tuple_properties);

    public void update_g_tuple_properties(Mdl_g_tuple_properties g_tuple_properties);

    
    public Mdl_g_tuple_properties find_g_tuple_propertiesBy_id(int g_tuple_properties);
    
    public int get_last_g_tuple_properties();
}


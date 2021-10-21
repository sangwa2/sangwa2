package com.codeguru.g_ultimate.service;

import com.codeguru.g_ultimate.models.Mdl_g_properties;
import java.util.List;

/**
 *
 * For author SANGWA  
 sangwa22@gmail.com 
 */
public interface Srv_g_properties {

    public List<Mdl_g_properties> all_g_propertiess();
    
    public void add_g_properties(Mdl_g_properties g_properties);

    public void delete_g_properties(int g_properties);

    public void update_g_properties(Mdl_g_properties g_properties);

    
    public Mdl_g_properties find_g_propertiesBy_id(int g_properties);
    
    public int get_last_g_properties();
}


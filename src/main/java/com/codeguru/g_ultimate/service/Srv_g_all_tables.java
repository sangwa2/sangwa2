package com.codeguru.g_ultimate.service;

import com.codeguru.g_ultimate.models.Mdl_g_all_tables;
import java.util.List;

/**
 *
 * For author SANGWA  
 sangwa22@gmail.com 
 */
public interface Srv_g_all_tables {

    public List<Mdl_g_all_tables> all_g_all_tabless();
    
    public void add_g_all_tables(Mdl_g_all_tables g_all_tables);

    public void delete_g_all_tables(int g_all_tables);

    public void update_g_all_tables(Mdl_g_all_tables g_all_tables);

    
    public Mdl_g_all_tables find_g_all_tablesBy_id(int g_all_tables);
    
    public int get_last_g_all_tables();
}


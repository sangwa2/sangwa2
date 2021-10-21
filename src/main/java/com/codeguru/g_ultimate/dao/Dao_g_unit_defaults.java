package com.codeguru.g_ultimate.dao;

import com.codeguru.g_ultimate.models.Mdl_g_unit_defaults;
import java.util.List;

/**
 *
 * @author SANGWA
 */
public interface Dao_g_unit_defaults {

    public List<Mdl_g_unit_defaults> all_g_unit_defaultss();

    
    public void add_g_unit_defaults(Mdl_g_unit_defaults g_unit_defaults);

    public void delete_g_unit_defaults(int g_unit_defaults);

    public void update_g_unit_defaults(Mdl_g_unit_defaults g_unit_defaults);

    
    public Mdl_g_unit_defaults find_g_unit_defaultsBy_id(int g_unit_defaults);
    
    public int get_last_g_unit_defaults();
}


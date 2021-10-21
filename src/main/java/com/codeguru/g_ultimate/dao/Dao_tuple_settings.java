package com.codeguru.g_ultimate.dao;

import com.codeguru.g_ultimate.models.Mdl_tuple_settings;
import java.util.List;

/**
 *
 * @author SANGWA
 */
public interface Dao_tuple_settings {

    public List<Mdl_tuple_settings> all_tuple_settingss();

    
    public void add_tuple_settings(Mdl_tuple_settings tuple_settings);

    public void delete_tuple_settings(int tuple_settings);

    public void update_tuple_settings(Mdl_tuple_settings tuple_settings);

    
    public Mdl_tuple_settings find_tuple_settingsBy_id(int tuple_settings);
    
    public int get_last_tuple_settings();
}


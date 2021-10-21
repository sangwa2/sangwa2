/*
 * Implementing the service of tuple_settings.
 *  .
 */
package com.codeguru.g_ultimate.service;

import com.codeguru.g_ultimate.dao.Dao_tuple_settings;
import com.codeguru.g_ultimate.models.Mdl_tuple_settings;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SANGWA
 */
@Service
public class Srv_tuple_settings_impl implements Srv_tuple_settings {

    Dao_tuple_settings dao_tuple_settings;

    @Autowired
    public void setDao_tuple_settings(Dao_tuple_settings dao_tuple_settings) {
        this.dao_tuple_settings = dao_tuple_settings;
    }

    @Override
    public List<Mdl_tuple_settings> all_tuple_settingss() {
       return dao_tuple_settings.all_tuple_settingss();
    }

    @Override
    public void add_tuple_settings(Mdl_tuple_settings tuple_settings) {
      dao_tuple_settings.add_tuple_settings(tuple_settings);
    }

    @Override
    public void delete_tuple_settings(int tuple_settings) {
        dao_tuple_settings.delete_tuple_settings(tuple_settings);
    }

    @Override
    public void update_tuple_settings(Mdl_tuple_settings tuple_settings) {
       dao_tuple_settings.update_tuple_settings(tuple_settings);
    }

    @Override
    public Mdl_tuple_settings find_tuple_settingsBy_id(int tuple_settings) {
        return dao_tuple_settings.find_tuple_settingsBy_id(tuple_settings);
    }

    @Override
    public int get_last_tuple_settings() {
        return dao_tuple_settings.get_last_tuple_settings();
    }
 
}


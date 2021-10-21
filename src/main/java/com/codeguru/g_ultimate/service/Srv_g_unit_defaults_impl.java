/*
 * Implementing the service of g_unit_defaults.
 *  .
 */
package com.codeguru.g_ultimate.service;

import com.codeguru.g_ultimate.dao.Dao_g_unit_defaults;
import com.codeguru.g_ultimate.models.Mdl_g_unit_defaults;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SANGWA
 */
@Service
public class Srv_g_unit_defaults_impl implements Srv_g_unit_defaults {

    Dao_g_unit_defaults dao_g_unit_defaults;

    @Autowired
    public void setDao_g_unit_defaults(Dao_g_unit_defaults dao_g_unit_defaults) {
        this.dao_g_unit_defaults = dao_g_unit_defaults;
    }

    @Override
    public List<Mdl_g_unit_defaults> all_g_unit_defaultss() {
       return dao_g_unit_defaults.all_g_unit_defaultss();
    }

    @Override
    public void add_g_unit_defaults(Mdl_g_unit_defaults g_unit_defaults) {
      dao_g_unit_defaults.add_g_unit_defaults(g_unit_defaults);
    }

    @Override
    public void delete_g_unit_defaults(int g_unit_defaults) {
        dao_g_unit_defaults.delete_g_unit_defaults(g_unit_defaults);
    }

    @Override
    public void update_g_unit_defaults(Mdl_g_unit_defaults g_unit_defaults) {
       dao_g_unit_defaults.update_g_unit_defaults(g_unit_defaults);
    }

    @Override
    public Mdl_g_unit_defaults find_g_unit_defaultsBy_id(int g_unit_defaults) {
        return dao_g_unit_defaults.find_g_unit_defaultsBy_id(g_unit_defaults);
    }

    @Override
    public int get_last_g_unit_defaults() {
        return dao_g_unit_defaults.get_last_g_unit_defaults();
    }
 
}


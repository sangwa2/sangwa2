/*
 * Implementing the service of g_properties.
 *  .
 */
package com.codeguru.g_ultimate.service;

import com.codeguru.g_ultimate.dao.Dao_g_properties;
import com.codeguru.g_ultimate.models.Mdl_g_properties;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SANGWA
 */
@Service
public class Srv_g_properties_impl implements Srv_g_properties {

    Dao_g_properties dao_g_properties;

    @Autowired
    public void setDao_g_properties(Dao_g_properties dao_g_properties) {
        this.dao_g_properties = dao_g_properties;
    }

    @Override
    public List<Mdl_g_properties> all_g_propertiess() {
       return dao_g_properties.all_g_propertiess();
    }

    @Override
    public void add_g_properties(Mdl_g_properties g_properties) {
      dao_g_properties.add_g_properties(g_properties);
    }

    @Override
    public void delete_g_properties(int g_properties) {
        dao_g_properties.delete_g_properties(g_properties);
    }

    @Override
    public void update_g_properties(Mdl_g_properties g_properties) {
       dao_g_properties.update_g_properties(g_properties);
    }

    @Override
    public Mdl_g_properties find_g_propertiesBy_id(int g_properties) {
        return dao_g_properties.find_g_propertiesBy_id(g_properties);
    }

    @Override
    public int get_last_g_properties() {
        return dao_g_properties.get_last_g_properties();
    }
 
}


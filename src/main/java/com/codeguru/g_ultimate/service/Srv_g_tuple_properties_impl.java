/*
 * Implementing the service of g_tuple_properties.
 *  .
 */
package com.codeguru.g_ultimate.service;

import com.codeguru.g_ultimate.dao.Dao_g_tuple_properties;
import com.codeguru.g_ultimate.models.Mdl_g_tuple_properties;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SANGWA
 */
@Service
public class Srv_g_tuple_properties_impl implements Srv_g_tuple_properties {

    Dao_g_tuple_properties dao_g_tuple_properties;

    @Autowired
    public void setDao_g_tuple_properties(Dao_g_tuple_properties dao_g_tuple_properties) {
        this.dao_g_tuple_properties = dao_g_tuple_properties;
    }

    @Override
    public List<Mdl_g_tuple_properties> all_g_tuple_propertiess() {
       return dao_g_tuple_properties.all_g_tuple_propertiess();
    }

    @Override
    public void add_g_tuple_properties(Mdl_g_tuple_properties g_tuple_properties) {
      dao_g_tuple_properties.add_g_tuple_properties(g_tuple_properties);
    }

    @Override
    public void delete_g_tuple_properties(int g_tuple_properties) {
        dao_g_tuple_properties.delete_g_tuple_properties(g_tuple_properties);
    }

    @Override
    public void update_g_tuple_properties(Mdl_g_tuple_properties g_tuple_properties) {
       dao_g_tuple_properties.update_g_tuple_properties(g_tuple_properties);
    }

    @Override
    public Mdl_g_tuple_properties find_g_tuple_propertiesBy_id(int g_tuple_properties) {
        return dao_g_tuple_properties.find_g_tuple_propertiesBy_id(g_tuple_properties);
    }

    @Override
    public int get_last_g_tuple_properties() {
        return dao_g_tuple_properties.get_last_g_tuple_properties();
    }
 
}


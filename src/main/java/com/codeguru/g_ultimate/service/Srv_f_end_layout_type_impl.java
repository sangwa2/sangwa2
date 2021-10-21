/*
 * Implementing the service of f_end_layout_type.
 *  .
 */
package com.codeguru.g_ultimate.service;

import com.codeguru.g_ultimate.dao.Dao_f_end_layout_type;
import com.codeguru.g_ultimate.models.Mdl_f_end_layout_type;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SANGWA
 */
@Service
public class Srv_f_end_layout_type_impl implements Srv_f_end_layout_type {

    Dao_f_end_layout_type dao_f_end_layout_type;

    @Autowired
    public void setDao_f_end_layout_type(Dao_f_end_layout_type dao_f_end_layout_type) {
        this.dao_f_end_layout_type = dao_f_end_layout_type;
    }

    @Override
    public List<Mdl_f_end_layout_type> all_f_end_layout_types() {
       return dao_f_end_layout_type.all_f_end_layout_types();
    }

    @Override
    public void add_f_end_layout_type(Mdl_f_end_layout_type f_end_layout_type) {
      dao_f_end_layout_type.add_f_end_layout_type(f_end_layout_type);
    }

    @Override
    public void delete_f_end_layout_type(int f_end_layout_type) {
        dao_f_end_layout_type.delete_f_end_layout_type(f_end_layout_type);
    }

    @Override
    public void update_f_end_layout_type(Mdl_f_end_layout_type f_end_layout_type) {
       dao_f_end_layout_type.update_f_end_layout_type(f_end_layout_type);
    }

    @Override
    public Mdl_f_end_layout_type find_f_end_layout_typeBy_id(int f_end_layout_type) {
        return dao_f_end_layout_type.find_f_end_layout_typeBy_id(f_end_layout_type);
    }

    @Override
    public int get_last_f_end_layout_type() {
        return dao_f_end_layout_type.get_last_f_end_layout_type();
    }

    @Override
    public List<Mdl_f_end_layout_type> f_end_lay_type_by_structure(int structure) {
       return dao_f_end_layout_type.f_end_lay_type_by_structure(structure);
    }

    @Override
    public Mdl_f_end_layout_type find_f_end_layout_typeBy_name(String type_name) {
        return dao_f_end_layout_type.find_f_end_layout_typeBy_name(type_name);
    }
 
}


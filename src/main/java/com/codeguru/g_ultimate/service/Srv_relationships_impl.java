/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.service;

import com.codeguru.g_ultimate.dao.Dao_relationships;
import com.codeguru.g_ultimate.dao.Dao_unit_structure;
import com.codeguru.g_ultimate.models.Mdl_relationships;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SANGWA
 */
@Service
public class Srv_relationships_impl implements Srv_relationships {

    Dao_relationships dao_relationships;

    @Autowired
    public void setDao_relationships(Dao_relationships dao_relationships) {
        this.dao_relationships = dao_relationships;
    }

    @Override
    public List<Mdl_relationships> list_all_relationships() {
        return dao_relationships.list_all_relationships();
    }

    @Override
    public void add_relationships(Mdl_relationships relationships) {
        dao_relationships.add_relationships(relationships);
    }

    @Override
    public void delete_relationships(int relationships) {
        dao_relationships.delete_relationships(relationships);
    }

    @Override
    public void update_relationships(Mdl_relationships relationships) {
        dao_relationships.update_relationships(relationships);
    }

    @Override
    public Mdl_relationships find_structurBy_id(int id) {
        return dao_relationships.find_structurBy_id(id);
    }

    @Override
    public int get_tuples_by_if_parent(int unit) {
        return dao_relationships.get_tuples_by_if_parent(unit);
    }

    @Override
    public int get_tuples_by_if_child(int unit) {
        return dao_relationships.get_tuples_by_if_child(unit);
    }

    @Override
    public int get_tuples_by_if_parentchild(int tuple) {
       return dao_relationships.get_tuples_by_if_parentchild(tuple);
    }

    @Override
    public void delete_relationships_by_unit(int unit) {
      dao_relationships.delete_relationships_by_unit(unit);
    }

    @Override
    public void delete_relationships_by_structure(int structure) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

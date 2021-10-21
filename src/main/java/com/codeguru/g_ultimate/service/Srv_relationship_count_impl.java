/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.service;

import com.codeguru.g_ultimate.dao.Dao_relationship_count;
import com.codeguru.g_ultimate.models.Mdl_relationship_count;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SANGWA
 */
@Service
public class Srv_relationship_count_impl implements Srv_relationship_count {

    
    Dao_relationship_count dao_relationship_count;

    @Autowired
    public void setDao_relationship_count(Dao_relationship_count dao_relationship_count) {
        this.dao_relationship_count = dao_relationship_count;
    }
    
    @Override
    public List<Mdl_relationship_count> list_all_relationship_count() {
        return dao_relationship_count.list_all_relationship_count();
    }

    @Override
    public void add_relationship_count(Mdl_relationship_count relationship_count) {
        dao_relationship_count.add_relationship_count(relationship_count);
    }

    @Override
    public void delete_relationship_count(int relationship_count) {
        dao_relationship_count.delete_relationship_count(relationship_count);
    }

    @Override
    public void update_relationship_count(Mdl_relationship_count relationship_count) {
        dao_relationship_count.update_relationship_count(relationship_count);
    }

    @Override
    public int n_children_by_tuple(int tuple) {
        return dao_relationship_count.n_children_by_tuple(tuple);
    }

    @Override
    public int n_parent_by_tuple(int tuple) {
       return dao_relationship_count.n_parent_by_tuple(tuple);
    }

    @Override
    public int find_tuple_countBy_tuple_id(int id) {
       return dao_relationship_count.find_tuple_countBy_tuple_id(id);
    }

    @Override
    public Mdl_relationship_count rel_count_by_tuple_id(int id) {
       return dao_relationship_count.rel_count_by_tuple_id(id);
    }

}

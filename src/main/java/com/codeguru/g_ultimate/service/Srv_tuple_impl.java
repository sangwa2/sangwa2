/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.service;

import com.codeguru.g_ultimate.dao.Dao_tuple;
import com.codeguru.g_ultimate.files.db.Model;
import com.codeguru.g_ultimate.models.Mdl_query;
import com.codeguru.g_ultimate.models.Mdl_tuple;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SANGWA
 */
@Service
public class Srv_tuple_impl implements Srv_tuple{

    Dao_tuple dao_tuple;

    @Autowired
    public void setDao_tuple(Dao_tuple dao_tuple) {
        this.dao_tuple = dao_tuple;
    }

    @Override
    public List<Mdl_tuple> all_tuples() {
        return dao_tuple.all_tuples();
    }

    @Override
    public List<Mdl_tuple> tuples_by_unit(int id) {
        return dao_tuple.tuples_by_unit(id);
    }

    @Override
    public void add_tuple(Mdl_tuple tuple) {
        dao_tuple.add_tuple(tuple);
    }

    @Override
    public void delete_tuple(int tuple) {
        dao_tuple.delete_tuple(tuple);
    }

    @Override
    public void update_tuple(Mdl_tuple tuple) {
        dao_tuple.update_tuple(tuple);
    }

    @Override
    public Mdl_tuple find_tupleBy_id(int tuple) {
        return dao_tuple.find_tupleBy_id(tuple);
    }

    @Override
    public int first_tuple_by_unit(int unit) {
        return dao_tuple.first_tuple_by_unit(unit);
    }

    @Override
    public List<Mdl_tuple> get_only_tuples_by_unit(int unit ) {
        return dao_tuple.get_only_tuples_by_unit(unit );
    }
    
    
 

}

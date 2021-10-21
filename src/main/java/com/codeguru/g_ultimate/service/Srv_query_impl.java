/*
 * Implementing the service of query.
 *  .
 */
package com.codeguru.g_ultimate.service;

import com.codeguru.g_ultimate.dao.Dao_query;
import com.codeguru.g_ultimate.models.Mdl_query;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SANGWA
 */
@Service
public class Srv_query_impl implements Srv_query {

    Dao_query dao_query;

    @Autowired
    public void setDao_query(Dao_query dao_query) {
        this.dao_query = dao_query;
    }

    @Override
    public List<Mdl_query> all_querys() {
       return dao_query.all_querys();
    }

    @Override
    public void add_query(Mdl_query query) {
      dao_query.add_query(query);
    }

    @Override
    public void delete_query(int query) {
        dao_query.delete_query(query);
    }

    @Override
    public void update_query(Mdl_query query) {
       dao_query.update_query(query);
    }

    @Override
    public Mdl_query find_queryBy_id(int query) {
        return dao_query.find_queryBy_id(query);
    }

    @Override
    public int get_last_query() {
        return dao_query.get_last_query();
    }



     
 
}


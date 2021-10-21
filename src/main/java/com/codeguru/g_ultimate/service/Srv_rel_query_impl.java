/*
 * Implementing the service of rel_query.
 *  .
 */
package com.codeguru.g_ultimate.service;

import com.codeguru.g_ultimate.dao.Dao_rel_query;
import com.codeguru.g_ultimate.models.Mdl_rel_query;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SANGWA
 */
@Service
public class Srv_rel_query_impl implements Srv_rel_query {

    Dao_rel_query dao_rel_query;

    @Autowired
    public void setDao_rel_query(Dao_rel_query dao_rel_query) {
        this.dao_rel_query = dao_rel_query;
    }

    @Override
    public List<Mdl_rel_query> all_rel_querys() {
        return dao_rel_query.all_rel_querys();
    }

    @Override
    public String add_rel_query(Mdl_rel_query rel_query, String filter) {
        return dao_rel_query.add_rel_query(rel_query, filter);
    }

    @Override
    public void delete_rel_query(int rel_query) {
        dao_rel_query.delete_rel_query(rel_query);
    }

    @Override
    public void update_rel_query(Mdl_rel_query rel_query) {
        dao_rel_query.update_rel_query(rel_query);
    }

    @Override
    public Mdl_rel_query find_rel_queryBy_id(int rel_query) {
        return dao_rel_query.find_rel_queryBy_id(rel_query);
    }

    @Override
    public int get_last_rel_query() {
        return dao_rel_query.get_last_rel_query();
    }

    @Override
    public void delete_rel_query_by_unit_tuple(int unit, int tuple) {
        dao_rel_query.delete_rel_query_by_unit_tuple(unit, tuple);
    }

    @Override
    public boolean get_tuple_by_unitTuple(int unit_id, int tuple_id) {
        return dao_rel_query.get_tuple_by_unitTuple(unit_id, tuple_id);
    }

    @Override
    public List<Mdl_rel_query> get_parents() {
        return dao_rel_query.get_parents();
    }

    @Override
    public List<Object> children_for_each_parent() {
       return dao_rel_query.children_for_each_parent();
    }

    @Override
    public Mdl_rel_query get_rel_query_by_tupleName(String tuple_name) {
       return dao_rel_query.get_rel_query_by_tupleName(tuple_name);
    }

}

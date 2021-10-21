/*
 * Implementing the service of query_details.
 *  .
 */
package com.codeguru.g_ultimate.service;

import com.codeguru.g_ultimate.dao.Dao_query_details;
import com.codeguru.g_ultimate.models.Mdl_query_details;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SANGWA
 */
@Service
public class Srv_query_details_impl implements Srv_query_details {

    Dao_query_details dao_query_details;

    @Autowired
    public void setDao_query_details(Dao_query_details dao_query_details) {
        this.dao_query_details = dao_query_details;
    }

    @Override
    public List<Mdl_query_details> all_query_detailss() {
       return dao_query_details.all_query_detailss();
    }

    @Override
    public void add_query_details(Mdl_query_details query_details) {
      dao_query_details.add_query_details(query_details);
    }

    @Override
    public void delete_query_details(int query_details) {
        dao_query_details.delete_query_details(query_details);
    }

    @Override
    public void update_query_details(Mdl_query_details query_details) {
       dao_query_details.update_query_details(query_details);
    }

    @Override
    public Mdl_query_details find_query_detailsBy_id(int query_details) {
        return dao_query_details.find_query_detailsBy_id(query_details);
    }

    @Override
    public int get_last_query_details() {
        return dao_query_details.get_last_query_details();
    }
 
}


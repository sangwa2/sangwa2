package com.codeguru.g_ultimate.dao;

import com.codeguru.g_ultimate.models.Mdl_query_details;
import java.util.List;

/**
 *
 * @author SANGWA
 */
public interface Dao_query_details {

    public List<Mdl_query_details> all_query_detailss();

    
    public void add_query_details(Mdl_query_details query_details);

    public void delete_query_details(int query_details);

    public void update_query_details(Mdl_query_details query_details);

    
    public Mdl_query_details find_query_detailsBy_id(int query_details);
    
    public int get_last_query_details();
}


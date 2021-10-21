package com.codeguru.g_ultimate.service;

import com.codeguru.g_ultimate.models.Mdl_query;
import java.util.List;

/**
 *
 * For author SANGWA sangwa22@gmail.com
 */
public interface Srv_query {

    public List<Mdl_query> all_querys();

    public void add_query(Mdl_query query);

    public void delete_query(int query);

    public void update_query(Mdl_query query);

    public Mdl_query find_queryBy_id(int query);

    public int get_last_query();


}

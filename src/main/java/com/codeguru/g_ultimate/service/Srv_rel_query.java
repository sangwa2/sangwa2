package com.codeguru.g_ultimate.service;

import com.codeguru.g_ultimate.models.Mdl_rel_query;
import java.util.List;

/**
 *
 * For author SANGWA sangwa22@gmail.com
 */
public interface Srv_rel_query {

    public List<Mdl_rel_query> all_rel_querys();

   public String add_rel_query(Mdl_rel_query rel_query,String filter);

    public void delete_rel_query(int rel_query);

    public void delete_rel_query_by_unit_tuple(int unit, int tuple);

    public void update_rel_query(Mdl_rel_query rel_query);

    public Mdl_rel_query find_rel_queryBy_id(int rel_query);

    public int get_last_rel_query();

    public boolean get_tuple_by_unitTuple(int unit_id, int tuple_id);
 public Mdl_rel_query get_rel_query_by_tupleName(String tuple_name);
    public List<Mdl_rel_query> get_parents();
    public List<Object> children_for_each_parent();
}

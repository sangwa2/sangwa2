/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.dao;

import com.codeguru.g_ultimate.models.Mdl_relationship_count;
import com.codeguru.g_ultimate.models.Mdl_relationships;
import java.util.List;

/**
 *
 * @author SANGWA
 */
public interface Dao_relationship_count {

    public List<Mdl_relationship_count> list_all_relationship_count();

    public void add_relationship_count(Mdl_relationship_count relationship_count);

    public void delete_relationship_count(int relationship_count);
    
    
    public void update_relationship_count(Mdl_relationship_count relationship_count);

    public int find_tuple_countBy_tuple_id(int id);

    public Mdl_relationship_count rel_count_by_tuple_id(int id);

    public int n_children_by_tuple(int tuple);

    public int n_parent_by_tuple(int tuple);
}

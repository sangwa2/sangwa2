/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.dao;

import com.codeguru.g_ultimate.models.Mdl_relationships;
import java.util.List;

/**
 *
 * @author SANGWA
 */
public interface Dao_relationships {

    public List<Mdl_relationships> list_all_relationships();

    public void add_relationships(Mdl_relationships relationships);

    public void delete_relationships(int relationships);

    public void delete_relationships_by_unit(int unit);

    public void delete_relationships_by_structure(int structure);

    public void update_relationships(Mdl_relationships relationships);

    public Mdl_relationships find_structurBy_id(int id);

    public int get_tuples_by_if_parent(int tuple);

    public int get_tuples_by_if_child(int tuple);

    public int get_tuples_by_if_parentchild(int tuple);
}

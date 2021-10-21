/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.dao;

import com.codeguru.g_ultimate.files.db.Model;
import com.codeguru.g_ultimate.models.Mdl_tuple;
import java.util.List;

/**
 *
 * @author SANGWA
 */
public interface Dao_tuple {

    public List<Mdl_tuple> all_tuples();
    public List<Mdl_tuple> get_only_tuples_by_unit(int unit);

    public List<Mdl_tuple> tuples_by_unit(int id);

    public void add_tuple(Mdl_tuple tuple);

    public void delete_tuple(int tuple);

    public void update_tuple(Mdl_tuple tuple);

    public Mdl_tuple find_tupleBy_id(int tuple);

    public int first_tuple_by_unit(int unit);

  
}

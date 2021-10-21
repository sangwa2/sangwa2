/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.dao;

import com.codeguru.g_ultimate.models.Mdl_child;
import com.codeguru.g_ultimate.models.Mdl_tuple;
import java.util.List;

/**
 *
 * @author SANGWA
 */
public interface Dao_child {

    public List<Mdl_child> all_child();

    public List<Mdl_child> child_by_unit(int id);

    public void add_child(Mdl_child child);

    public void delete_child(int child);

    public void update_child(Mdl_child child);

    public int last_child();

    public Mdl_child find_childBy_id(int child);
}

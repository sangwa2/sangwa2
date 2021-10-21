/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.service;

import com.codeguru.g_ultimate.models.Mdl_child;
import com.codeguru.g_ultimate.models.Mdl_parent;
import java.util.List;

/**
 *
 * @author SANGWA
 */
public interface Srv_parent {

    public List<Mdl_parent> all_parent();

    public void add_parent(Mdl_parent parent);

    public void delete_parent(int parent);

    public void update_parent(Mdl_parent parent);

    public Mdl_parent find_parentBy_id(int parent);

    public int last_parent();

}

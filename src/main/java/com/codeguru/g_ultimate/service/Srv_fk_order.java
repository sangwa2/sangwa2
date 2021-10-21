/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.service;

import com.codeguru.g_ultimate.models.Mdl_fk_order;
import java.util.List;

/**
 *
 * @author SANGWA
 */
public interface Srv_fk_order {

    public List<Mdl_fk_order> all_fk_orders();

    public void add_fk_order(Mdl_fk_order fk_order);

    public void delete_fk_order(int fk_order);

    public void update_fk_order(Mdl_fk_order fk_order);

    public Mdl_fk_order find_fk_orderBy_id(int fk_order);

    public Mdl_fk_order find_fk_orderBy_layout(int layout);

    public Mdl_fk_order find_fk_orderBy_tuple(int tuple);

    public int get_last_fk_order();

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.service;

import com.codeguru.g_ultimate.dao.Dao_fk_order;
import com.codeguru.g_ultimate.models.Mdl_fk_order;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SANGWA
 */
@Service
public class Srv_fk_order_impl implements Srv_fk_order{

    Dao_fk_order dao_fk_order;

    @Autowired
    public void setDao_fk_order(Dao_fk_order dao_fk_order) {
        this.dao_fk_order = dao_fk_order;
    }
    
    @Override
    public List<Mdl_fk_order> all_fk_orders() {
        return dao_fk_order.all_fk_orders();
    }

    @Override
    public void add_fk_order(Mdl_fk_order fk_order) {
         dao_fk_order.add_fk_order(fk_order);
    }

    @Override
    public void delete_fk_order(int fk_order) {
        dao_fk_order.delete_fk_order(fk_order);
    }

    @Override
    public void update_fk_order(Mdl_fk_order fk_order) {
      dao_fk_order.update_fk_order(fk_order);
    }

    @Override
    public Mdl_fk_order find_fk_orderBy_id(int fk_order) {
       return dao_fk_order.find_fk_orderBy_id(fk_order);
    }

    @Override
    public int get_last_fk_order() {
      return dao_fk_order.get_last_fk_order();
    }

    @Override
    public Mdl_fk_order find_fk_orderBy_layout(int layout) {
       return dao_fk_order.find_fk_orderBy_layout(layout);
    }

    @Override
    public Mdl_fk_order find_fk_orderBy_tuple(int tuple) {
        return dao_fk_order.find_fk_orderBy_tuple(tuple);
    }
    
}

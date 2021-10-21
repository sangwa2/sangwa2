/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.service;

import com.codeguru.g_ultimate.dao.Dao_parent;
import com.codeguru.g_ultimate.models.Mdl_parent;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SANGWA
 */
@Service
public class Srv_parent_impl implements Srv_parent{

    
    Dao_parent dao_parent;

    @Autowired
    public void setDao_parent(Dao_parent dao_parent) {
        this.dao_parent = dao_parent;
    }
    
    @Override
    public List<Mdl_parent> all_parent() {
   return  dao_parent.all_parent();
    }

    @Override
    public void add_parent(Mdl_parent parent) {
       dao_parent.add_parent(parent);
    }

    @Override
    public void delete_parent(int parent) {
       dao_parent.delete_parent(parent);
    }

    @Override
    public void update_parent(Mdl_parent parent) {
       dao_parent.update_parent(parent);
    }

    @Override
    public Mdl_parent find_parentBy_id(int parent) {
       return dao_parent.find_parentBy_id(parent);
    }

    @Override
    public int last_parent() {
       return dao_parent.last_parent();
    }

    
    
    
}

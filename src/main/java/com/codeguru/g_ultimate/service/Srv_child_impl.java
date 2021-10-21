/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.service;

import com.codeguru.g_ultimate.dao.Dao_child;
import com.codeguru.g_ultimate.models.Mdl_child;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SANGWA
 */
@Service
public class Srv_child_impl implements Srv_child {

    Dao_child dao_child;

    @Autowired
    public void setDao_child(Dao_child dao_child) {
        this.dao_child = dao_child;
    }

    @Override
    public List<Mdl_child> all_child() {
       return dao_child.all_child();
    }

    @Override
    public List<Mdl_child> child_by_unit(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add_child(Mdl_child child) {
      dao_child.add_child(child);
    }

    @Override
    public void delete_child(int child) {
        dao_child.delete_child(child);
    }

    @Override
    public void update_child(Mdl_child child) {
       dao_child.update_child(child);
    }

    @Override
    public Mdl_child find_childBy_id(int child) {
        return dao_child.find_childBy_id(child);
    }

    @Override
    public int last_child() {
        return dao_child.last_child();
    }
 
}

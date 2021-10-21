/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.service;

import com.codeguru.g_ultimate.dao.Dao_relations;
import com.codeguru.g_ultimate.models.Mdl_relations;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SANGWA
 */
@Service
public class Srv_relations_impl implements Srv_relations{

    Dao_relations dao_relations;
    @Autowired
    public void setDao_relations(Dao_relations dao_relations) {
        this.dao_relations = dao_relations;
    }
    
    @Override
    public List<Mdl_relations> all_relationss() {
       return dao_relations.all_relationss();
    }

    @Override
    public void add_relations(Mdl_relations relations) {
       dao_relations.add_relations(relations);
    }

    @Override
    public void delete_relations(int relations) {
      dao_relations.delete_relations(relations);
    }

    @Override
    public void update_relations(Mdl_relations relations) {
       dao_relations.update_relations(relations);
    }

    @Override
    public Mdl_relations find_relationsBy_id(int relations) {
        return dao_relations.find_relationsBy_id(relations);
    }

    @Override
    public int get_last_relations() {
        return dao_relations.get_last_relations();
    }

    @Override
    public void delete_relations_by_unit(int unit) {
        dao_relations.delete_relations_by_unit(unit);
    }
    
}

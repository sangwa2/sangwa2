/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.service;

import com.codeguru.g_ultimate.dao.Dao_structure;
import com.codeguru.g_ultimate.models.Mdl_structure;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Service;

/**
 *
 * @author SANGWA
 */
@Service
public class Srv_structure_impl implements Srvc_structure{
    
    Dao_structure dao_structure;
     
    @Autowired
    public void setDao_structure(Dao_structure dao_structure) {
        this.dao_structure = dao_structure;
    }
 
    @Override
    public List<Mdl_structure> list_all_structure() {
       return dao_structure.list_all_structure();
    }

    @Override
    public void add_structure(Mdl_structure structure) {
        dao_structure.add_structure(structure);
    }

    @Override
    public void delete_structure(int structure) {
        dao_structure.delete_structure(structure);
    }

    @Override
    public void update_structure(Mdl_structure structure) {
        dao_structure.update_structure(structure);
    }

    @Override
    public Mdl_structure find_structurBy_id(int id) {
        return dao_structure.find_structurBy_id(id);
    }

    @Override
    public int get_last_structure() {
         return dao_structure.get_last_structure();
    }

    @Override
    public String db_exists(String name) {
        return dao_structure.db_exists(name);
    }

    @Override
    public ArrayList<String> existing_str() {
        return dao_structure.existing_str();
    }
       
    
}

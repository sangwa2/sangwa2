/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.service;

import com.codeguru.g_ultimate.dao.Dao_unit_structure;
import com.codeguru.g_ultimate.models.Mdl_unit_structure;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SANGWA
 */
@Service
public class Srv_unit_structure_impl implements Srv_unit_structure{

    Dao_unit_structure dao_unit_structure;

    @Autowired
    public void setDao_unit_structure(Dao_unit_structure dao_unit_structure) {
        this.dao_unit_structure = dao_unit_structure;
    }
     
    @Override
    public List<Mdl_unit_structure> units_by_structure(int id) {
         return dao_unit_structure.units_by_structure(id);
    }

    @Override
    public List<Mdl_unit_structure> units_by_structure_no_tuples(int id) {
          return dao_unit_structure.units_by_structure_no_tuples(id);
    
    
    }
     
}

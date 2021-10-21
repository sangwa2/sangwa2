/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.files.db;

import java.lang.annotation.Annotation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 *
 * @author SANGWA
 */
@Service
public class Srv_db_maker_impl implements Srv_db_maker{

    Dao dao;
    @Autowired
    public void setDao(Dao dao) {
        this.dao = dao;
    }
    
    @Override
    public List<Model>  structure_details(int structure) {
      return dao.structure_details(structure);
    }

    @Override
    public String struture_tuple(int structure ) {
      return dao.struture_tuple(structure );
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.files.app;

import com.codeguru.g_ultimate.files.db.Model;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SANGWA
 */
@Service
public class Gf_srvc_impl implements Gf_srvc {

    com.codeguru.g_ultimate.files.db.Dao dao;
    @Autowired
    public void setDao(com.codeguru.g_ultimate.files.db.Dao dao) {
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

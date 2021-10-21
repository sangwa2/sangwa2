/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.diagrams;

import com.codeguru.g_ultimate.files.db.Dao;
import com.codeguru.g_ultimate.files.db.Model;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SANGWA
 */
@Service
public class Srv_dictionary_impl  implements Srv_dictionary {

    Dao_dict dao_dict;

    @Autowired
     public void setDao_dict(Dao_dict dao_dict) {
        this.dao_dict = dao_dict;
    }

    @Override
    public String dictionary(int structure) {
        return dao_dict.dictionary(structure);
    }

    @Override
    public List<Model> structure_details(int structure) {
        return dao_dict.structure_details(structure);
    }

}

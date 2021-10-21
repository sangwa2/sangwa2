/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.files.db;

import com.codeguru.g_ultimate.models.Mdl_unit;
import java.util.List;

/**
 *
 * @author SANGWA
 */
public interface Dao {
    public List<Model> structure_details(int structure);
    public String struture_tuple(int structure);
    
 
}

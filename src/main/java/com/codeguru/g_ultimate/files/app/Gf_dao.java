/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.files.app;

import com.codeguru.g_ultimate.files.db.Model;
import java.util.List;

/**
 *
 * @author SANGWA
 */
public interface Gf_dao {
     public List<Model> structure_details(int structure);
    public String struture_tuple(int structure);
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.dao.query;

import com.codeguru.g_ultimate.models.Mdl_rel_query;
import java.util.List;

/**
 *
 * @author SANGWA
 */
public class Cross_join extends Search_if_found {
   
    public void add_cross_join(Mdl_rel_query mdl_rel_query){
        cross_join.add( mdl_rel_query);
    }
    
    
}

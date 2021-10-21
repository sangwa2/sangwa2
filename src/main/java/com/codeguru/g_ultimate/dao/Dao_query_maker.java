/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.dao;

import com.codeguru.g_ultimate.models.Mdl_relationship_count;
import com.codeguru.g_ultimate.models.Mdl_relationships;
import com.codeguru.g_ultimate.models.Mdl_unit_structure;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 *
 * @author SANGWA
 */
public class Dao_query_maker   {

    ArrayList<String> unit_tuples= new ArrayList<>();
    
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    
    void get_parse_tuples(String tuple){
        unit_tuples.add(tuple);
        String item="";
        for (String unit_tuple : unit_tuples) {
           // item=unit_tuple.subs
                    
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Dao_query_maker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
 
}

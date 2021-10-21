/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.files.app;

import com.codeguru.g_ultimate.files.db.Model;
import com.codeguru.g_ultimate.files.db.Query;
import static com.codeguru.g_ultimate.files.db.Query.structure_q;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SANGWA
 */
@Repository
public class Gf_dao_impl extends Query implements Gf_dao {
    
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Model> structure_details(int structure) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("structure", structure);
        List<Model> units = namedParameterJdbcTemplate.query(structure_q(), params, new Query.db_mapper_str());
        return units;
    }

    @Override
    public String struture_tuple(int structure) {
        //GEt the structure details and get the tuples
        try {
            sturectures = structure_details(structure);

            if (true) {//it is String

            }//else make Servlet
            new Spring_App().run(); //get the App maker
        } catch (Exception e) {
            System.out.println("Error making db:  " + e.toString());
        }
        return code_db;
    }

    class Spring_App extends App_files {

        void run() {

            get_app(namedParameterJdbcTemplate);
        }
    }
}

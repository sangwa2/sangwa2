/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.files.db;

import com.codeguru.g_ultimate.dao.Dao_units_impl;
import com.codeguru.g_ultimate.models.Mdl_unit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SANGWA
 */
@Repository
public class Dao_impl extends Query implements Dao {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Model> structure_details(int structure) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("structure", structure);
        List<Model> units = namedParameterJdbcTemplate.query(structure_q(), params, new db_mapper_str());
        return units;
    }

    @Override
    public String struture_tuple(int structure) {
        //GEt the structure details and get the tuples
        try {
            primary_key = "";
            fields = "";
            field_val = "";
            field_val1 = "";
            code_db="";
            sturectures = structure_details(structure);
            System.out.println("Size: " + sturectures.size());
            get_the_tuples(namedParameterJdbcTemplate);
        } catch (Exception e) {
            System.out.println("Error making db:  " + e.toString());
        }
        return code_db;//this will be done from the method above "get_the_tuples(np)"
    }

    private SqlParameterSource getParameterByModel(Model model) {
        MapSqlParameterSource paramsource = new MapSqlParameterSource();
        if (model != null) {
            paramsource.addValue("structure", model.getStructure());
            paramsource.addValue("unit_name", model.getUnit_name());
            paramsource.addValue("db_name", model.getDb_name());
            paramsource.addValue("db_user", model.getDb_name());
            paramsource.addValue("cash_total", model.getCash_total());
            paramsource.addValue("start_time", model.getStart_time());
            paramsource.addValue("delivery_date", model.getDelivery_date());
            paramsource.addValue("pgm_language", model.getPgm_language());
            paramsource.addValue("platform", model.getPlatform());
        }
        return paramsource;
    }

}

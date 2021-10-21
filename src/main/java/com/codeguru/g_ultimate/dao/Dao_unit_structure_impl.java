/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.dao;

import com.codeguru.g_ultimate.models.Mdl_structure;
import com.codeguru.g_ultimate.models.Mdl_unit_structure;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
public class Dao_unit_structure_impl implements Dao_unit_structure {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    List<Mdl_unit_structure> common(String sql, int id) {
        MapSqlParameterSource paramsource = new MapSqlParameterSource();
        paramsource.addValue("structure", id);
        List<Mdl_unit_structure> list = namedParameterJdbcTemplate.query(sql, paramsource, new unit_structure_mapper());
        
        
        return list;
    }

    @Override
    public List<Mdl_unit_structure> units_by_structure(int id) {
        String sql = "SELECT unit.unit_id, unit.name, unit.structure, tuple.tuple_id, tuple.name as tuple_name FROM unit join structure on structure.structure_id=unit.structure  join tuple on unit.unit_id=tuple.unit where unit.structure=:structure";
        MapSqlParameterSource paramsource = new MapSqlParameterSource();
        paramsource.addValue("structure", id);
        List<Mdl_unit_structure> list = namedParameterJdbcTemplate.query(sql, paramsource, new unit_structure_mapper());
               
        return list;
    }

    private SqlParameterSource getParameterByModel(Mdl_unit_structure mdl_unit_structure) {
        MapSqlParameterSource paramsource = new MapSqlParameterSource();
        if (mdl_unit_structure != null) {
            paramsource.addValue("unit_id", mdl_unit_structure.getUnit_id());
            paramsource.addValue("name", mdl_unit_structure.getName());
            paramsource.addValue("structure", mdl_unit_structure.getStructure());
        }
        return paramsource;
    }

    private static final class unit_structure_mapper implements RowMapper<Mdl_unit_structure> {

        @Override
        public Mdl_unit_structure mapRow(ResultSet rs, int i) throws SQLException {
            Mdl_unit_structure mdl_unit_structure = new Mdl_unit_structure();
            mdl_unit_structure.setUnit_id(rs.getInt("unit_id"));
            mdl_unit_structure.setName(rs.getString("name"));
            mdl_unit_structure.setStructure(rs.getInt("structure"));
            mdl_unit_structure.setTuple_name(rs.getString("tuple_name"));
            mdl_unit_structure.setTuple_id(rs.getInt("tuple_id"));
            return mdl_unit_structure;
        }

    }
    
    private static final class unit_structure_no_tuple_mapper implements RowMapper<Mdl_unit_structure> {

        @Override
        public Mdl_unit_structure mapRow(ResultSet rs, int i) throws SQLException {
            Mdl_unit_structure mdl_unit_structure = new Mdl_unit_structure();
            mdl_unit_structure.setUnit_id(rs.getInt("unit_id"));
            mdl_unit_structure.setName(rs.getString("name"));
            mdl_unit_structure.setStructure(rs.getInt("structure"));
            return mdl_unit_structure;  
        }

    }
    @Override
    public List<Mdl_unit_structure> units_by_structure_no_tuples(int id) {
        String sql = "SELECT unit.unit_id, unit.name,unit.structure  from unit where unit.structure=:structure";
        MapSqlParameterSource paramsource = new MapSqlParameterSource();
        paramsource.addValue("structure", id);
        List<Mdl_unit_structure> list = namedParameterJdbcTemplate.query(sql, paramsource, new unit_structure_no_tuple_mapper());
        return list;
    }

}

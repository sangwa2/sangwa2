/*
 * Interface implementation of G_tuple_properties.
 * @For Author SANGWA 
 sangwa22@gmail.com.
 */
package com.codeguru.g_ultimate.dao;

import com.codeguru.g_ultimate.models.Mdl_g_tuple_properties;
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
public class Dao_g_tuple_properties_impl implements Dao_g_tuple_properties {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Mdl_g_tuple_properties> all_g_tuple_propertiess() {
        String sql = "SELECT  g_tuple_properties.g_tiple_properties_id    FROM g_tuple_properties";
        List<Mdl_g_tuple_properties> list = namedParameterJdbcTemplate.query(sql, getParameterByModel(null), new g_tuple_properties_mapper());
        return list;
    }

    @Override
    public void add_g_tuple_properties(Mdl_g_tuple_properties g_tuple_properties) {
        String sql =  " INSERT INTO g_tuple_properties (g_tiple_properties_id) VALUES  (:g_tiple_properties_id)";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(g_tuple_properties));
    }

    @Override
    public void delete_g_tuple_properties(int g_tuple_properties) {
        String sql = "DELETE from g_tuple_properties where g_tuple_properties_id=:g_tuple_properties_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(new Mdl_g_tuple_properties(g_tuple_properties)));
    }

    @Override
    public void update_g_tuple_properties(Mdl_g_tuple_properties g_tuple_properties) {
        String sql = "UPDATE child SET   g_tiple_properties_id_id= : g_tiple_properties_id_id  WHERE g_tuple_properties_id =:g_tuple_properties_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(g_tuple_properties));
    }

    @Override
    public Mdl_g_tuple_properties find_g_tuple_propertiesBy_id(int g_tuple_properties) {
        String sql = "SELECT * FROM g_tuple_properties where g_tuple_properties_id=:g_tuple_properties_id";
        return namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(new Mdl_g_tuple_properties(g_tuple_properties)), new g_tuple_properties_mapper());
    }

    @Override
    public int get_last_g_tuple_properties() {
       String sql="select g_tuple_properties_id from g_tuple_properties order by g_tuple_properties_id desc limit 1";
        int n= namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(null), Integer.class);
        return n;
    }
    
    
    
    
    

    private SqlParameterSource getParameterByModel(Mdl_g_tuple_properties mdl_g_tuple_properties) {
        MapSqlParameterSource paramsource = new MapSqlParameterSource();
        if (mdl_g_tuple_properties != null) {
            paramsource.addValue("g_tiple_properties_id", mdl_g_tuple_properties.getG_tiple_properties_id());
        }
        return paramsource;
    }

    private static final class g_tuple_properties_mapper implements RowMapper<Mdl_g_tuple_properties> {

        @Override
        public Mdl_g_tuple_properties mapRow(ResultSet rs, int i) throws SQLException {
            Mdl_g_tuple_properties mdl_g_tuple_properties = new Mdl_g_tuple_properties();
            mdl_g_tuple_properties.setG_tiple_properties_id(rs.getInt("g_tiple_properties_id"));

            return mdl_g_tuple_properties;
        }

    }
}


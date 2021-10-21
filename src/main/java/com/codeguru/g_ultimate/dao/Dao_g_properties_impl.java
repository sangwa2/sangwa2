/*
 * Interface implementation of G_properties.
 * @For Author SANGWA 
 sangwa22@gmail.com.
 */
package com.codeguru.g_ultimate.dao;

import com.codeguru.g_ultimate.models.Mdl_g_properties;
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
public class Dao_g_properties_impl implements Dao_g_properties {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Mdl_g_properties> all_g_propertiess() {
        String sql = "SELECT  g_properties.g_properties_idg_properties.name ,g_properties.default_value    FROM g_properties";
        List<Mdl_g_properties> list = namedParameterJdbcTemplate.query(sql, getParameterByModel(null), new g_properties_mapper());
        return list;
    }

    @Override
    public void add_g_properties(Mdl_g_properties g_properties) {
        String sql =  " INSERT INTO g_properties (g_properties_idname,default_value) VALUES  (:g_properties_id:name ,:default_value)";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(g_properties));
    }

    @Override
    public void delete_g_properties(int g_properties) {
        String sql = "DELETE from g_properties where g_properties_id=:g_properties_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(new Mdl_g_properties(g_properties)));
    }

    @Override
    public void update_g_properties(Mdl_g_properties g_properties) {
        String sql = "UPDATE child SET   g_properties_id_id= : g_properties_id_idname_id= : name_id ,:default_value_id= : default_value_id  WHERE g_properties_id =:g_properties_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(g_properties));
    }

    @Override
    public Mdl_g_properties find_g_propertiesBy_id(int g_properties) {
        String sql = "SELECT * FROM g_properties where g_properties_id=:g_properties_id";
        return namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(new Mdl_g_properties(g_properties)), new g_properties_mapper());
    }

    @Override
    public int get_last_g_properties() {
       String sql="select g_properties_id from g_properties order by g_properties_id desc limit 1";
        int n= namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(null), Integer.class);
        return n;
    }
    
    
    
    
    

    private SqlParameterSource getParameterByModel(Mdl_g_properties mdl_g_properties) {
        MapSqlParameterSource paramsource = new MapSqlParameterSource();
        if (mdl_g_properties != null) {
            paramsource.addValue("g_properties_id", mdl_g_properties.getG_properties_id());
            paramsource.addValue("name", mdl_g_properties.getName());
            paramsource.addValue("default_value", mdl_g_properties.getDefault_value());
        }
        return paramsource;
    }

    private static final class g_properties_mapper implements RowMapper<Mdl_g_properties> {

        @Override
        public Mdl_g_properties mapRow(ResultSet rs, int i) throws SQLException {
            Mdl_g_properties mdl_g_properties = new Mdl_g_properties();
            mdl_g_properties.setG_properties_id(rs.getInt("g_properties_id"));
            mdl_g_properties.setName(rs.getString("name"));
            mdl_g_properties.setDefault_value(rs.getString("default_value"));

            return mdl_g_properties;
        }

    }
}


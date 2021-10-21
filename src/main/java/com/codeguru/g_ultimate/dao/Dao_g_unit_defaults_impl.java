/*
 * Interface implementation of G_unit_defaults.
 * @For Author SANGWA 
 sangwa22@gmail.com.
 */
package com.codeguru.g_ultimate.dao;

import com.codeguru.g_ultimate.models.Mdl_g_unit_defaults;
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
public class Dao_g_unit_defaults_impl implements Dao_g_unit_defaults {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Mdl_g_unit_defaults> all_g_unit_defaultss() {
        String sql = "SELECT  g_unit_defaults.g_unit_defaults_id    FROM g_unit_defaults";
        List<Mdl_g_unit_defaults> list = namedParameterJdbcTemplate.query(sql, getParameterByModel(null), new g_unit_defaults_mapper());
        return list;
    }

    @Override
    public void add_g_unit_defaults(Mdl_g_unit_defaults g_unit_defaults) {
        String sql =  " INSERT INTO g_unit_defaults (g_unit_defaults_id) VALUES  (:g_unit_defaults_id)";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(g_unit_defaults));
    }

    @Override
    public void delete_g_unit_defaults(int g_unit_defaults) {
        String sql = "DELETE from g_unit_defaults where g_unit_defaults_id=:g_unit_defaults_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(new Mdl_g_unit_defaults(g_unit_defaults)));
    }

    @Override
    public void update_g_unit_defaults(Mdl_g_unit_defaults g_unit_defaults) {
        String sql = "UPDATE child SET   g_unit_defaults_id_id= : g_unit_defaults_id_id  WHERE g_unit_defaults_id =:g_unit_defaults_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(g_unit_defaults));
    }

    @Override
    public Mdl_g_unit_defaults find_g_unit_defaultsBy_id(int g_unit_defaults) {
        String sql = "SELECT * FROM g_unit_defaults where g_unit_defaults_id=:g_unit_defaults_id";
        return namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(new Mdl_g_unit_defaults(g_unit_defaults)), new g_unit_defaults_mapper());
    }

    @Override
    public int get_last_g_unit_defaults() {
       String sql="select g_unit_defaults_id from g_unit_defaults order by g_unit_defaults_id desc limit 1";
        int n= namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(null), Integer.class);
        return n;
    }
    
    
    
    
    

    private SqlParameterSource getParameterByModel(Mdl_g_unit_defaults mdl_g_unit_defaults) {
        MapSqlParameterSource paramsource = new MapSqlParameterSource();
        if (mdl_g_unit_defaults != null) {
            paramsource.addValue("g_unit_defaults_id", mdl_g_unit_defaults.getG_unit_defaults_id());
        }
        return paramsource;
    }

    private static final class g_unit_defaults_mapper implements RowMapper<Mdl_g_unit_defaults> {

        @Override
        public Mdl_g_unit_defaults mapRow(ResultSet rs, int i) throws SQLException {
            Mdl_g_unit_defaults mdl_g_unit_defaults = new Mdl_g_unit_defaults();
            mdl_g_unit_defaults.setG_unit_defaults_id(rs.getInt("g_unit_defaults_id"));

            return mdl_g_unit_defaults;
        }

    }
}


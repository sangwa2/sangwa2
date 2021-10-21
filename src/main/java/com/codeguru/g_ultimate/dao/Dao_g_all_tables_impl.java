/*
 * Interface implementation of G_all_tables.
 * @For Author SANGWA 
 sangwa22@gmail.com.
 */
package com.codeguru.g_ultimate.dao;

import com.codeguru.g_ultimate.models.Mdl_g_all_tables;
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
public class Dao_g_all_tables_impl implements Dao_g_all_tables {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Mdl_g_all_tables> all_g_all_tabless() {
        String sql = "SELECT  g_all_tables.g_all_tables_id    FROM g_all_tables";
        List<Mdl_g_all_tables> list = namedParameterJdbcTemplate.query(sql, getParameterByModel(null), new g_all_tables_mapper());
        return list;
    }

    @Override
    public void add_g_all_tables(Mdl_g_all_tables g_all_tables) {
        String sql =  " INSERT INTO g_all_tables (g_all_tables_id) VALUES  (:g_all_tables_id)";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(g_all_tables));
    }

    @Override
    public void delete_g_all_tables(int g_all_tables) {
        String sql = "DELETE from g_all_tables where g_all_tables_id=:g_all_tables_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(new Mdl_g_all_tables(g_all_tables)));
    }

    @Override
    public void update_g_all_tables(Mdl_g_all_tables g_all_tables) {
        String sql = "UPDATE child SET   g_all_tables_id_id= : g_all_tables_id_id  WHERE g_all_tables_id =:g_all_tables_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(g_all_tables));
    }

    @Override
    public Mdl_g_all_tables find_g_all_tablesBy_id(int g_all_tables) {
        String sql = "SELECT * FROM g_all_tables where g_all_tables_id=:g_all_tables_id";
        return namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(new Mdl_g_all_tables(g_all_tables)), new g_all_tables_mapper());
    }

    @Override
    public int get_last_g_all_tables() {
       String sql="select g_all_tables_id from g_all_tables order by g_all_tables_id desc limit 1";
        int n= namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(null), Integer.class);
        return n;
    }
    
    
    
    
    

    private SqlParameterSource getParameterByModel(Mdl_g_all_tables mdl_g_all_tables) {
        MapSqlParameterSource paramsource = new MapSqlParameterSource();
        if (mdl_g_all_tables != null) {
            paramsource.addValue("g_all_tables_id", mdl_g_all_tables.getG_all_tables_id());
        }
        return paramsource;
    }

    private static final class g_all_tables_mapper implements RowMapper<Mdl_g_all_tables> {

        @Override
        public Mdl_g_all_tables mapRow(ResultSet rs, int i) throws SQLException {
            Mdl_g_all_tables mdl_g_all_tables = new Mdl_g_all_tables();
            mdl_g_all_tables.setG_all_tables_id(rs.getInt("g_all_tables_id"));

            return mdl_g_all_tables;
        }

    }
}


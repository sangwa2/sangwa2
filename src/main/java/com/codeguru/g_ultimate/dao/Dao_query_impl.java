/*
 * Interface implementation of Query.
 * @For Author SANGWA 
 sangwa22@gmail.com.
 */
package com.codeguru.g_ultimate.dao;

import com.codeguru.g_ultimate.models.Mdl_query;
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
public class Dao_query_impl implements Dao_query {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Mdl_query> all_querys() {
        String sql = "SELECT  query.query_id    FROM query";
        List<Mdl_query> list = namedParameterJdbcTemplate.query(sql, getParameterByModel(null), new query_mapper());
        return list;
    }
    
    @Override
    public void add_query(Mdl_query query) {
        String sql =  " INSERT INTO query (query_id) VALUES  (:query_id)";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(query));
    }

    @Override
    public void delete_query(int query) {
        String sql = "DELETE from query where query_id=:query_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(new Mdl_query(query)));
    }

    @Override
    public void update_query(Mdl_query query) {
        String sql = "UPDATE child SET   query_id_id= : query_id_id  WHERE query_id =:query_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(query));
    }

    @Override
    public Mdl_query find_queryBy_id(int query) {
        String sql = "SELECT * FROM query where query_id=:query_id";
        return namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(new Mdl_query(query)), new query_mapper());
    }

    @Override
    public int get_last_query() {
       String sql="select query_id from query order by query_id desc limit 1";
        int n= namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(null), Integer.class);
        return n;
    }
    
    
    
    
    

    private SqlParameterSource getParameterByModel(Mdl_query mdl_query) {
        MapSqlParameterSource paramsource = new MapSqlParameterSource();
        if (mdl_query != null) {
            paramsource.addValue("query_id", mdl_query.getQuery_id());
        }
        return paramsource;
    }

    private static final class query_mapper implements RowMapper<Mdl_query> {

        @Override
        public Mdl_query mapRow(ResultSet rs, int i) throws SQLException {
            Mdl_query mdl_query = new Mdl_query();
            mdl_query.setQuery_id(rs.getInt("query_id"));

            return mdl_query;
        }

    }
}


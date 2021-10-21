/*
 * Interface implementation of Query_details.
 * @For Author SANGWA 
 sangwa22@gmail.com.
 */
package com.codeguru.g_ultimate.dao;

import com.codeguru.g_ultimate.models.Mdl_query_details;
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
public class Dao_query_details_impl implements Dao_query_details {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Mdl_query_details> all_query_detailss() {
        String sql = "SELECT  query_details.query_details_id    FROM query_details";
        List<Mdl_query_details> list = namedParameterJdbcTemplate.query(sql, getParameterByModel(null), new query_details_mapper());
        return list;
    }

    @Override
    public void add_query_details(Mdl_query_details query_details) {
        String sql =  " INSERT INTO query_details (query_details_id) VALUES  (:query_details_id)";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(query_details));
    }

    @Override
    public void delete_query_details(int query_details) {
        String sql = "DELETE from query_details where query_details_id=:query_details_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(new Mdl_query_details(query_details)));
    }

    @Override
    public void update_query_details(Mdl_query_details query_details) {
        String sql = "UPDATE child SET   query_details_id_id= : query_details_id_id  WHERE query_details_id =:query_details_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(query_details));
    }

    @Override
    public Mdl_query_details find_query_detailsBy_id(int query_details) {
        String sql = "SELECT * FROM query_details where query_details_id=:query_details_id";
        return namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(new Mdl_query_details(query_details)), new query_details_mapper());
    }

    @Override
    public int get_last_query_details() {
       String sql="select query_details_id from query_details order by query_details_id desc limit 1";
        int n= namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(null), Integer.class);
        return n;
    }
    
    
    
    
    

    private SqlParameterSource getParameterByModel(Mdl_query_details mdl_query_details) {
        MapSqlParameterSource paramsource = new MapSqlParameterSource();
        if (mdl_query_details != null) {
            paramsource.addValue("query_details_id", mdl_query_details.getQuery_details_id());
        }
        return paramsource;
    }

    private static final class query_details_mapper implements RowMapper<Mdl_query_details> {

        @Override
        public Mdl_query_details mapRow(ResultSet rs, int i) throws SQLException {
            Mdl_query_details mdl_query_details = new Mdl_query_details();
            mdl_query_details.setQuery_details_id(rs.getInt("query_details_id"));

            return mdl_query_details;
        }

    }
}


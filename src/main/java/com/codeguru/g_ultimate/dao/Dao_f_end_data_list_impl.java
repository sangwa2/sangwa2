/*
 * Interface implementation of F_end_data_list.
 * @For Author SANGWA 
 sangwa22@gmail.com.
 */
package com.codeguru.g_ultimate.dao;

import com.codeguru.g_ultimate.models.Mdl_f_end_data_list;
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
public class Dao_f_end_data_list_impl implements Dao_f_end_data_list {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Mdl_f_end_data_list> all_f_end_data_lists() {
        String sql = "SELECT  f_end_data_list.f_end_data_list_idf_end_data_list.query    FROM f_end_data_list";
        List<Mdl_f_end_data_list> list = namedParameterJdbcTemplate.query(sql, getParameterByModel(null), new f_end_data_list_mapper());
        return list;
    }

    @Override
    public void add_f_end_data_list(Mdl_f_end_data_list f_end_data_list) {
        String sql =  " INSERT INTO f_end_data_list (f_end_data_list_idquery) VALUES  (:f_end_data_list_id:query)";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(f_end_data_list));
    }

    @Override
    public void delete_f_end_data_list(int f_end_data_list) {
        String sql = "DELETE from f_end_data_list where f_end_data_list_id=:f_end_data_list_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(new Mdl_f_end_data_list(f_end_data_list)));
    }

    @Override
    public void update_f_end_data_list(Mdl_f_end_data_list f_end_data_list) {
        String sql = "UPDATE child SET   f_end_data_list_id_id= : f_end_data_list_id_idquery_id= : query_id  WHERE f_end_data_list_id =:f_end_data_list_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(f_end_data_list));
    }

    @Override
    public Mdl_f_end_data_list find_f_end_data_listBy_id(int f_end_data_list) {
        String sql = "SELECT * FROM f_end_data_list where f_end_data_list_id=:f_end_data_list_id";
        return namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(new Mdl_f_end_data_list(f_end_data_list)), new f_end_data_list_mapper());
    }

    @Override
    public int get_last_f_end_data_list() {
       String sql="select f_end_data_list_id from f_end_data_list order by f_end_data_list_id desc limit 1";
        int n= namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(null), Integer.class);
        return n;
    }
    
    
    
    
    

    private SqlParameterSource getParameterByModel(Mdl_f_end_data_list mdl_f_end_data_list) {
        MapSqlParameterSource paramsource = new MapSqlParameterSource();
        if (mdl_f_end_data_list != null) {
            paramsource.addValue("f_end_data_list_id", mdl_f_end_data_list.getF_end_data_list_id());
            paramsource.addValue("query", mdl_f_end_data_list.getQuery());
        }
        return paramsource;
    }

    private static final class f_end_data_list_mapper implements RowMapper<Mdl_f_end_data_list> {

        @Override
        public Mdl_f_end_data_list mapRow(ResultSet rs, int i) throws SQLException {
            Mdl_f_end_data_list mdl_f_end_data_list = new Mdl_f_end_data_list();
            mdl_f_end_data_list.setF_end_data_list_id(rs.getInt("f_end_data_list_id"));
            mdl_f_end_data_list.setQuery(rs.getString("query"));

            return mdl_f_end_data_list;
        }

    }
}


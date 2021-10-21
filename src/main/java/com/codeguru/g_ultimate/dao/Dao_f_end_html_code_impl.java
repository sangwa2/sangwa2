/*
 * Interface implementation of F_end_html_code.
 * @For Author SANGWA 
 sangwa22@gmail.com.
 */
package com.codeguru.g_ultimate.dao;

import com.codeguru.g_ultimate.models.Mdl_f_end_html_code;
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
public class Dao_f_end_html_code_impl implements Dao_f_end_html_code {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Mdl_f_end_html_code> all_f_end_html_codes() {
        String sql = "SELECT  f_end_html_code.f_end_html_code_idf_end_html_code.name    FROM f_end_html_code";
        List<Mdl_f_end_html_code> list = namedParameterJdbcTemplate.query(sql, getParameterByModel(null), new f_end_html_code_mapper());
        return list;
    }

    @Override
    public void add_f_end_html_code(Mdl_f_end_html_code f_end_html_code) {
        String sql =  " INSERT INTO f_end_html_code (f_end_html_code_idname) VALUES  (:f_end_html_code_id:name)";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(f_end_html_code));
    }

    @Override
    public void delete_f_end_html_code(int f_end_html_code) {
        String sql = "DELETE from f_end_html_code where f_end_html_code_id=:f_end_html_code_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(new Mdl_f_end_html_code(f_end_html_code)));
    }

    @Override
    public void update_f_end_html_code(Mdl_f_end_html_code f_end_html_code) {
        String sql = "UPDATE child SET   f_end_html_code_id_id= : f_end_html_code_id_idname_id= : name_id  WHERE f_end_html_code_id =:f_end_html_code_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(f_end_html_code));
    }

    @Override
    public Mdl_f_end_html_code find_f_end_html_codeBy_id(int f_end_html_code) {
        String sql = "SELECT * FROM f_end_html_code where f_end_html_code_id=:f_end_html_code_id";
        return namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(new Mdl_f_end_html_code(f_end_html_code)), new f_end_html_code_mapper());
    }

    @Override
    public int get_last_f_end_html_code() {
       String sql="select f_end_html_code_id from f_end_html_code order by f_end_html_code_id desc limit 1";
        int n= namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(null), Integer.class);
        return n;
    }
    
    
    
    
    

    private SqlParameterSource getParameterByModel(Mdl_f_end_html_code mdl_f_end_html_code) {
        MapSqlParameterSource paramsource = new MapSqlParameterSource();
        if (mdl_f_end_html_code != null) {
            paramsource.addValue("f_end_html_code_id", mdl_f_end_html_code.getF_end_html_code_id());
            paramsource.addValue("name", mdl_f_end_html_code.getName());
        }
        return paramsource;
    }

    private static final class f_end_html_code_mapper implements RowMapper<Mdl_f_end_html_code> {

        @Override
        public Mdl_f_end_html_code mapRow(ResultSet rs, int i) throws SQLException {
            Mdl_f_end_html_code mdl_f_end_html_code = new Mdl_f_end_html_code();
            mdl_f_end_html_code.setF_end_html_code_id(rs.getInt("f_end_html_code_id"));
            mdl_f_end_html_code.setName(rs.getString("name"));

            return mdl_f_end_html_code;
        }

    }
}


/*
 * Interface implementation of F_end_js_code.
 * @For Author SANGWA 
 sangwa22@gmail.com.
 */
package com.codeguru.g_ultimate.dao;

import com.codeguru.g_ultimate.models.Mdl_f_end_js_code;
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
public class Dao_f_end_js_code_impl implements Dao_f_end_js_code {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Mdl_f_end_js_code> all_f_end_js_codes() {
        String sql = "SELECT  f_end_js_code.f_end_js_code_idf_end_js_code.code_line ,f_end_js_code.css ,f_end_js_code.js_code_category    FROM f_end_js_code";
        List<Mdl_f_end_js_code> list = namedParameterJdbcTemplate.query(sql, getParameterByModel(null), new f_end_js_code_mapper());
        return list;
    }

    @Override
    public void add_f_end_js_code(Mdl_f_end_js_code f_end_js_code) {
        String sql =  " INSERT INTO f_end_js_code (f_end_js_code_idcode_line,css,js_code_category) VALUES  (:f_end_js_code_id:code_line ,:css ,:js_code_category)";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(f_end_js_code));
    }

    @Override
    public void delete_f_end_js_code(int f_end_js_code) {
        String sql = "DELETE from f_end_js_code where f_end_js_code_id=:f_end_js_code_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(new Mdl_f_end_js_code(f_end_js_code)));
    }

    @Override
    public void update_f_end_js_code(Mdl_f_end_js_code f_end_js_code) {
        String sql = "UPDATE child SET   f_end_js_code_id_id= : f_end_js_code_id_idcode_line_id= : code_line_id ,:css_id= : css_id ,:js_code_category_id= : js_code_category_id  WHERE f_end_js_code_id =:f_end_js_code_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(f_end_js_code));
    }

    @Override
    public Mdl_f_end_js_code find_f_end_js_codeBy_id(int f_end_js_code) {
        String sql = "SELECT * FROM f_end_js_code where f_end_js_code_id=:f_end_js_code_id";
        return namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(new Mdl_f_end_js_code(f_end_js_code)), new f_end_js_code_mapper());
    }

    @Override
    public int get_last_f_end_js_code() {
       String sql="select f_end_js_code_id from f_end_js_code order by f_end_js_code_id desc limit 1";
        int n= namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(null), Integer.class);
        return n;
    }
    
    
    
    
    

    private SqlParameterSource getParameterByModel(Mdl_f_end_js_code mdl_f_end_js_code) {
        MapSqlParameterSource paramsource = new MapSqlParameterSource();
        if (mdl_f_end_js_code != null) {
            paramsource.addValue("f_end_js_code_id", mdl_f_end_js_code.getF_end_js_code_id());
            paramsource.addValue("code_line", mdl_f_end_js_code.getCode_line());
            paramsource.addValue("css", mdl_f_end_js_code.getCss());
            paramsource.addValue("js_code_category", mdl_f_end_js_code.getJs_code_category());
        }
        return paramsource;
    }

    private static final class f_end_js_code_mapper implements RowMapper<Mdl_f_end_js_code> {

        @Override
        public Mdl_f_end_js_code mapRow(ResultSet rs, int i) throws SQLException {
            Mdl_f_end_js_code mdl_f_end_js_code = new Mdl_f_end_js_code();
            mdl_f_end_js_code.setF_end_js_code_id(rs.getInt("f_end_js_code_id"));
            mdl_f_end_js_code.setCode_line(rs.getString("code_line"));
            mdl_f_end_js_code.setCss(rs.getInt("css"));
            mdl_f_end_js_code.setJs_code_category(rs.getInt("js_code_category"));

            return mdl_f_end_js_code;
        }

    }
}


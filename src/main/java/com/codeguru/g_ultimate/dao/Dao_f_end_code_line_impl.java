/*
 * Interface implementation of F_end_code_line.
 * @For Author SANGWA 
 sangwa22@gmail.com.
 */
package com.codeguru.g_ultimate.dao;

import com.codeguru.g_ultimate.models.Mdl_f_end_code_line;
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
public class Dao_f_end_code_line_impl implements Dao_f_end_code_line {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Mdl_f_end_code_line> all_f_end_code_lines() {
        String sql = "SELECT  f_end_code_line.f_end_code_line_idf_end_code_line.name ,f_end_code_line.content ,f_end_code_line.content_type ,f_end_code_line.html_code    FROM f_end_code_line";
        List<Mdl_f_end_code_line> list = namedParameterJdbcTemplate.query(sql, getParameterByModel(null), new f_end_code_line_mapper());
        return list;
    }

    @Override
    public void add_f_end_code_line(Mdl_f_end_code_line f_end_code_line) {
        String sql =  " INSERT INTO f_end_code_line (f_end_code_line_idname,content,content_type,html_code) VALUES  (:f_end_code_line_id:name ,:content ,:content_type ,:html_code)";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(f_end_code_line));
    }

    @Override
    public void delete_f_end_code_line(int f_end_code_line) {
        String sql = "DELETE from f_end_code_line where f_end_code_line_id=:f_end_code_line_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(new Mdl_f_end_code_line(f_end_code_line)));
    }

    @Override
    public void update_f_end_code_line(Mdl_f_end_code_line f_end_code_line) {
        String sql = "UPDATE child SET   f_end_code_line_id_id= : f_end_code_line_id_idname_id= : name_id ,:content_id= : content_id ,:content_type_id= : content_type_id ,:html_code_id= : html_code_id  WHERE f_end_code_line_id =:f_end_code_line_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(f_end_code_line));
    }

    @Override
    public Mdl_f_end_code_line find_f_end_code_lineBy_id(int f_end_code_line) {
        String sql = "SELECT * FROM f_end_code_line where f_end_code_line_id=:f_end_code_line_id";
        return namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(new Mdl_f_end_code_line(f_end_code_line)), new f_end_code_line_mapper());
    }

    @Override
    public int get_last_f_end_code_line() {
       String sql="select f_end_code_line_id from f_end_code_line order by f_end_code_line_id desc limit 1";
        int n= namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(null), Integer.class);
        return n;
    }
    
    
    
    
    

    private SqlParameterSource getParameterByModel(Mdl_f_end_code_line mdl_f_end_code_line) {
        MapSqlParameterSource paramsource = new MapSqlParameterSource();
        if (mdl_f_end_code_line != null) {
            paramsource.addValue("f_end_code_line_id", mdl_f_end_code_line.getF_end_code_line_id());
            paramsource.addValue("name", mdl_f_end_code_line.getName());
            paramsource.addValue("content", mdl_f_end_code_line.getContent());
            paramsource.addValue("content_type", mdl_f_end_code_line.getContent_type());
            paramsource.addValue("html_code", mdl_f_end_code_line.getHtml_code());
        }
        return paramsource;
    }

    private static final class f_end_code_line_mapper implements RowMapper<Mdl_f_end_code_line> {

        @Override
        public Mdl_f_end_code_line mapRow(ResultSet rs, int i) throws SQLException {
            Mdl_f_end_code_line mdl_f_end_code_line = new Mdl_f_end_code_line();
            mdl_f_end_code_line.setF_end_code_line_id(rs.getInt("f_end_code_line_id"));
            mdl_f_end_code_line.setName(rs.getString("name"));
            mdl_f_end_code_line.setContent(rs.getString("content"));
            mdl_f_end_code_line.setContent_type(rs.getString("content_type"));
            mdl_f_end_code_line.setHtml_code(rs.getInt("html_code"));

            return mdl_f_end_code_line;
        }

    }
}


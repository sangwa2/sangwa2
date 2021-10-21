/*
 * Interface implementation of F_end_css.
 * @For Author SANGWA 
 sangwa22@gmail.com.
 */
package com.codeguru.g_ultimate.dao;

import com.codeguru.g_ultimate.models.Mdl_f_end_css;
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
public class Dao_f_end_css_impl implements Dao_f_end_css {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Mdl_f_end_css> all_f_end_csss() {
        String sql = "SELECT  f_end_css.f_end_class_idf_end_css.name ,f_end_css.html_code_line ,f_end_css.css_category    FROM f_end_css";
        List<Mdl_f_end_css> list = namedParameterJdbcTemplate.query(sql, getParameterByModel(null), new f_end_css_mapper());
        return list;
    }

    @Override
    public void add_f_end_css(Mdl_f_end_css f_end_css) {
        String sql =  " INSERT INTO f_end_css (f_end_class_idname,html_code_line,css_category) VALUES  (:f_end_class_id:name ,:html_code_line ,:css_category)";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(f_end_css));
    }

    @Override
    public void delete_f_end_css(int f_end_css) {
        String sql = "DELETE from f_end_css where f_end_css_id=:f_end_css_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(new Mdl_f_end_css(f_end_css)));
    }

    @Override
    public void update_f_end_css(Mdl_f_end_css f_end_css) {
        String sql = "UPDATE child SET   f_end_class_id_id= : f_end_class_id_idname_id= : name_id ,:html_code_line_id= : html_code_line_id ,:css_category_id= : css_category_id  WHERE f_end_css_id =:f_end_css_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(f_end_css));
    }

    @Override
    public Mdl_f_end_css find_f_end_cssBy_id(int f_end_css) {
        String sql = "SELECT * FROM f_end_css where f_end_css_id=:f_end_css_id";
        return namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(new Mdl_f_end_css(f_end_css)), new f_end_css_mapper());
    }

    @Override
    public int get_last_f_end_css() {
       String sql="select f_end_css_id from f_end_css order by f_end_css_id desc limit 1";
        int n= namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(null), Integer.class);
        return n;
    }
    
    
    
    
    

    private SqlParameterSource getParameterByModel(Mdl_f_end_css mdl_f_end_css) {
        MapSqlParameterSource paramsource = new MapSqlParameterSource();
        if (mdl_f_end_css != null) {
            paramsource.addValue("f_end_class_id", mdl_f_end_css.getF_end_class_id());
            paramsource.addValue("name", mdl_f_end_css.getName());
            paramsource.addValue("html_code_line", mdl_f_end_css.getHtml_code_line());
            paramsource.addValue("css_category", mdl_f_end_css.getCss_category());
        }
        return paramsource;
    }

    private static final class f_end_css_mapper implements RowMapper<Mdl_f_end_css> {

        @Override
        public Mdl_f_end_css mapRow(ResultSet rs, int i) throws SQLException {
            Mdl_f_end_css mdl_f_end_css = new Mdl_f_end_css();
            mdl_f_end_css.setF_end_class_id(rs.getInt("f_end_class_id"));
            mdl_f_end_css.setName(rs.getString("name"));
            mdl_f_end_css.setHtml_code_line(rs.getInt("html_code_line"));
            mdl_f_end_css.setCss_category(rs.getString("css_category"));

            return mdl_f_end_css;
        }

    }
}


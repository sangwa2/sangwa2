/*
 * Interface implementation of F_end_js_category.
 * @For Author SANGWA 
 sangwa22@gmail.com.
 */
package com.codeguru.g_ultimate.dao;

import com.codeguru.g_ultimate.models.Mdl_f_end_js_category;
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
public class Dao_f_end_js_category_impl implements Dao_f_end_js_category {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Mdl_f_end_js_category> all_f_end_js_categorys() {
        String sql = "SELECT  f_end_js_category.f_end_js_category_idf_end_js_category.name    FROM f_end_js_category";
        List<Mdl_f_end_js_category> list = namedParameterJdbcTemplate.query(sql, getParameterByModel(null), new f_end_js_category_mapper());
        return list;
    }

    @Override
    public void add_f_end_js_category(Mdl_f_end_js_category f_end_js_category) {
        String sql =  " INSERT INTO f_end_js_category (f_end_js_category_idname) VALUES  (:f_end_js_category_id:name)";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(f_end_js_category));
    }

    @Override
    public void delete_f_end_js_category(int f_end_js_category) {
        String sql = "DELETE from f_end_js_category where f_end_js_category_id=:f_end_js_category_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(new Mdl_f_end_js_category(f_end_js_category)));
    }

    @Override
    public void update_f_end_js_category(Mdl_f_end_js_category f_end_js_category) {
        String sql = "UPDATE child SET   f_end_js_category_id_id= : f_end_js_category_id_idname_id= : name_id  WHERE f_end_js_category_id =:f_end_js_category_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(f_end_js_category));
    }

    @Override
    public Mdl_f_end_js_category find_f_end_js_categoryBy_id(int f_end_js_category) {
        String sql = "SELECT * FROM f_end_js_category where f_end_js_category_id=:f_end_js_category_id";
        return namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(new Mdl_f_end_js_category(f_end_js_category)), new f_end_js_category_mapper());
    }

    @Override
    public int get_last_f_end_js_category() {
       String sql="select f_end_js_category_id from f_end_js_category order by f_end_js_category_id desc limit 1";
        int n= namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(null), Integer.class);
        return n;
    }
    
    
    
    
    

    private SqlParameterSource getParameterByModel(Mdl_f_end_js_category mdl_f_end_js_category) {
        MapSqlParameterSource paramsource = new MapSqlParameterSource();
        if (mdl_f_end_js_category != null) {
            paramsource.addValue("f_end_js_category_id", mdl_f_end_js_category.getF_end_js_category_id());
            paramsource.addValue("name", mdl_f_end_js_category.getName());
        }
        return paramsource;
    }

    private static final class f_end_js_category_mapper implements RowMapper<Mdl_f_end_js_category> {

        @Override
        public Mdl_f_end_js_category mapRow(ResultSet rs, int i) throws SQLException {
            Mdl_f_end_js_category mdl_f_end_js_category = new Mdl_f_end_js_category();
            mdl_f_end_js_category.setF_end_js_category_id(rs.getInt("f_end_js_category_id"));
            mdl_f_end_js_category.setName(rs.getString("name"));

            return mdl_f_end_js_category;
        }

    }
}


/*
 * Interface implementation of F_end_class_attrib.
 * @For Author SANGWA 
 sangwa22@gmail.com.
 */
package com.codeguru.g_ultimate.dao;

import com.codeguru.g_ultimate.models.Mdl_f_end_class_attrib;
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
public class Dao_f_end_class_attrib_impl implements Dao_f_end_class_attrib {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Mdl_f_end_class_attrib> all_f_end_class_attribs() {
        String sql = "SELECT  f_end_class_attrib.f_end_class_attrib_idf_end_class_attrib.attribute ,f_end_class_attrib.value    FROM f_end_class_attrib";
        List<Mdl_f_end_class_attrib> list = namedParameterJdbcTemplate.query(sql, getParameterByModel(null), new f_end_class_attrib_mapper());
        return list;
    }

    @Override
    public void add_f_end_class_attrib(Mdl_f_end_class_attrib f_end_class_attrib) {
        String sql =  " INSERT INTO f_end_class_attrib (f_end_class_attrib_idattribute,value) VALUES  (:f_end_class_attrib_id:attribute ,:value)";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(f_end_class_attrib));
    }

    @Override
    public void delete_f_end_class_attrib(int f_end_class_attrib) {
        String sql = "DELETE from f_end_class_attrib where f_end_class_attrib_id=:f_end_class_attrib_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(new Mdl_f_end_class_attrib(f_end_class_attrib)));
    }

    @Override
    public void update_f_end_class_attrib(Mdl_f_end_class_attrib f_end_class_attrib) {
        String sql = "UPDATE child SET   f_end_class_attrib_id_id= : f_end_class_attrib_id_idattribute_id= : attribute_id ,:value_id= : value_id  WHERE f_end_class_attrib_id =:f_end_class_attrib_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(f_end_class_attrib));
    }

    @Override
    public Mdl_f_end_class_attrib find_f_end_class_attribBy_id(int f_end_class_attrib) {
        String sql = "SELECT * FROM f_end_class_attrib where f_end_class_attrib_id=:f_end_class_attrib_id";
        return namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(new Mdl_f_end_class_attrib(f_end_class_attrib)), new f_end_class_attrib_mapper());
    }

    @Override
    public int get_last_f_end_class_attrib() {
       String sql="select f_end_class_attrib_id from f_end_class_attrib order by f_end_class_attrib_id desc limit 1";
        int n= namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(null), Integer.class);
        return n;
    }
    
    
    
    
    

    private SqlParameterSource getParameterByModel(Mdl_f_end_class_attrib mdl_f_end_class_attrib) {
        MapSqlParameterSource paramsource = new MapSqlParameterSource();
        if (mdl_f_end_class_attrib != null) {
            paramsource.addValue("f_end_class_attrib_id", mdl_f_end_class_attrib.getF_end_class_attrib_id());
            paramsource.addValue("attribute", mdl_f_end_class_attrib.getAttribute());
            paramsource.addValue("value", mdl_f_end_class_attrib.getValue());
        }
        return paramsource;
    }

    private static final class f_end_class_attrib_mapper implements RowMapper<Mdl_f_end_class_attrib> {

        @Override
        public Mdl_f_end_class_attrib mapRow(ResultSet rs, int i) throws SQLException {
            Mdl_f_end_class_attrib mdl_f_end_class_attrib = new Mdl_f_end_class_attrib();
            mdl_f_end_class_attrib.setF_end_class_attrib_id(rs.getInt("f_end_class_attrib_id"));
            mdl_f_end_class_attrib.setAttribute(rs.getString("attribute"));
            mdl_f_end_class_attrib.setValue(rs.getString("value"));

            return mdl_f_end_class_attrib;
        }

    }
}


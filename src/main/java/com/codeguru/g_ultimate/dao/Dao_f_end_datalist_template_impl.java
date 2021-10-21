/*
 * Interface implementation of F_end_datalist_template.
 * @For Author SANGWA 
 sangwa22@gmail.com.
 */
package com.codeguru.g_ultimate.dao;

import com.codeguru.g_ultimate.models.Mdl_f_end_datalist_template;
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
public class Dao_f_end_datalist_template_impl implements Dao_f_end_datalist_template {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Mdl_f_end_datalist_template> all_f_end_datalist_templates() {
        String sql = "SELECT  f_end_datalist_template.F_end_datalist_template_id ,f_end_datalist_template.name ,f_end_datalist_template.description    FROM f_end_datalist_template";
        List<Mdl_f_end_datalist_template> list = namedParameterJdbcTemplate.query(sql, getParameterByModel(null), new f_end_datalist_template_mapper());
        return list;
    }

    @Override
    public void add_f_end_datalist_template(Mdl_f_end_datalist_template f_end_datalist_template) {
        String sql =  " INSERT INTO f_end_datalist_template (F_end_datalist_template_id,name,description) VALUES  (:F_end_datalist_template_id ,:name ,:description)";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(f_end_datalist_template));
    }

    @Override
    public void delete_f_end_datalist_template(int f_end_datalist_template) {
        String sql = "DELETE from f_end_datalist_template where f_end_datalist_template_id=:f_end_datalist_template_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(new Mdl_f_end_datalist_template(f_end_datalist_template)));
    }

    @Override
    public void update_f_end_datalist_template(Mdl_f_end_datalist_template f_end_datalist_template) {
        String sql = "UPDATE child SET   F_end_datalist_template_id_id= : F_end_datalist_template_id_idname_id= : name_id ,:description_id= : description_id  WHERE f_end_datalist_template_id =:f_end_datalist_template_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(f_end_datalist_template));
    }

    @Override
    public Mdl_f_end_datalist_template find_f_end_datalist_templateBy_id(int f_end_datalist_template) {
        String sql = "SELECT * FROM f_end_datalist_template where f_end_datalist_template_id=:f_end_datalist_template_id";
        return namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(new Mdl_f_end_datalist_template(f_end_datalist_template)), new f_end_datalist_template_mapper());
    }

    @Override
    public int get_last_f_end_datalist_template() {
       String sql="select f_end_datalist_template_id from f_end_datalist_template order by f_end_datalist_template_id desc limit 1";
        int n= namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(null), Integer.class);
        return n;
    }
    
    
    
    
    

    private SqlParameterSource getParameterByModel(Mdl_f_end_datalist_template mdl_f_end_datalist_template) {
        MapSqlParameterSource paramsource = new MapSqlParameterSource();
        if (mdl_f_end_datalist_template != null) {
            paramsource.addValue("F_end_datalist_template_id", mdl_f_end_datalist_template.getF_end_datalist_template_id());
            paramsource.addValue("name", mdl_f_end_datalist_template.getName());
            paramsource.addValue("description", mdl_f_end_datalist_template.getDescription());
        }
        return paramsource;
    }

    private static final class f_end_datalist_template_mapper implements RowMapper<Mdl_f_end_datalist_template> {

        @Override
        public Mdl_f_end_datalist_template mapRow(ResultSet rs, int i) throws SQLException {
            Mdl_f_end_datalist_template mdl_f_end_datalist_template = new Mdl_f_end_datalist_template();
            mdl_f_end_datalist_template.setF_end_datalist_template_id(rs.getInt("F_end_datalist_template_id"));
            mdl_f_end_datalist_template.setName(rs.getString("name"));
            mdl_f_end_datalist_template.setDescription(rs.getString("description"));

            return mdl_f_end_datalist_template;
        }

    }
}


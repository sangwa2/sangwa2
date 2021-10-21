/*
 * Interface implementation of F_end_form.
 * @For Author SANGWA 
 sangwa22@gmail.com.
 */
package com.codeguru.g_ultimate.dao;

import com.codeguru.g_ultimate.models.Mdl_f_end_form;
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
public class Dao_f_end_form_impl implements Dao_f_end_form {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Mdl_f_end_form> all_f_end_forms() {
        String sql = "SELECT  f_end_form.f_end_form_idf_end_form.layout ,f_end_form.unit ,f_end_form.form_template    FROM f_end_form";
        List<Mdl_f_end_form> list = namedParameterJdbcTemplate.query(sql, getParameterByModel(null), new f_end_form_mapper());
        return list;
    }

    @Override
    public void add_f_end_form(Mdl_f_end_form f_end_form) {
        String sql =  " INSERT INTO f_end_form (f_end_form_idlayout,unit,form_template) VALUES  (:f_end_form_id:layout ,:unit ,:form_template)";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(f_end_form));
    }

    @Override
    public void delete_f_end_form(int f_end_form) {
        String sql = "DELETE from f_end_form where f_end_form_id=:f_end_form_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(new Mdl_f_end_form(f_end_form)));
    }

    @Override
    public void update_f_end_form(Mdl_f_end_form f_end_form) {
        String sql = "UPDATE child SET   f_end_form_id_id= : f_end_form_id_idlayout_id= : layout_id ,:unit_id= : unit_id ,:form_template_id= : form_template_id  WHERE f_end_form_id =:f_end_form_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(f_end_form));
    }

    @Override
    public Mdl_f_end_form find_f_end_formBy_id(int f_end_form) {
        String sql = "SELECT * FROM f_end_form where f_end_form_id=:f_end_form_id";
        return namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(new Mdl_f_end_form(f_end_form)), new f_end_form_mapper());
    }

    @Override
    public int get_last_f_end_form() {
       String sql="select f_end_form_id from f_end_form order by f_end_form_id desc limit 1";
        int n= namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(null), Integer.class);
        return n;
    }
    
    
    
    
    

    private SqlParameterSource getParameterByModel(Mdl_f_end_form mdl_f_end_form) {
        MapSqlParameterSource paramsource = new MapSqlParameterSource();
        if (mdl_f_end_form != null) {
            paramsource.addValue("f_end_form_id", mdl_f_end_form.getF_end_form_id());
            paramsource.addValue("layout", mdl_f_end_form.getLayout());
            paramsource.addValue("unit", mdl_f_end_form.getUnit());
            paramsource.addValue("form_template", mdl_f_end_form.getForm_template());
        }
        return paramsource;
    }

    private static final class f_end_form_mapper implements RowMapper<Mdl_f_end_form> {

        @Override
        public Mdl_f_end_form mapRow(ResultSet rs, int i) throws SQLException {
            Mdl_f_end_form mdl_f_end_form = new Mdl_f_end_form();
            mdl_f_end_form.setF_end_form_id(rs.getInt("f_end_form_id"));
            mdl_f_end_form.setLayout(rs.getInt("layout"));
            mdl_f_end_form.setUnit(rs.getInt("unit"));
            mdl_f_end_form.setForm_template(rs.getInt("form_template"));

            return mdl_f_end_form;
        }

    }
}


/*
 * Interface implementation of F_layout_unit_template.
 * @For Author SANGWA 
 sangwa22@gmail.com.
 */
package com.codeguru.g_ultimate.dao;

import com.codeguru.g_ultimate.models.Mdl_f_end_layout;
import com.codeguru.g_ultimate.models.Mdl_f_layout_unit_template;
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
public class Dao_f_layout_unit_template_impl implements Dao_f_layout_unit_template {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Mdl_f_layout_unit_template> all_f_layout_unit_templates() {
        String sql = "SELECT  f_layout_unit_template.f_layout_unit_template_id, f_layout_unit_template.layout ,f_layout_unit_template.unit ,f_layout_unit_template.form_template    FROM f_layout_unit_template";
        List<Mdl_f_layout_unit_template> list = namedParameterJdbcTemplate.query(sql, getParameterByModel(null), new f_layout_unit_template_mapper());
        return list;
    }

    @Override
    public void add_f_layout_unit_template(Mdl_f_layout_unit_template f_layout_unit_template) {
        String sql =  " INSERT INTO f_layout_unit_template (layout,unit,form_template,combo_form) VALUES  (:layout,:unit,:form_template,:combo_form)";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(f_layout_unit_template));
    }

    @Override
    public void delete_f_layout_unit_template(int f_layout_unit_template) {
        String sql = "DELETE from f_layout_unit_template where f_layout_unit_template_id=:f_layout_unit_template_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(new Mdl_f_layout_unit_template(f_layout_unit_template)));
    }

    @Override
    public void update_f_layout_unit_template(Mdl_f_layout_unit_template f_layout_unit_template) {
        String sql = "UPDATE child SET   f_layout_unit_template_id_id= : f_layout_unit_template_id_idlayout_id= : layout_id ,:unit_id= : unit_id ,:form_template_id= : form_template_id  WHERE f_layout_unit_template_id =:f_layout_unit_template_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(f_layout_unit_template));
    }

    @Override
    public Mdl_f_layout_unit_template find_f_layout_unit_templateBy_id(int f_layout_unit_template) {
        String sql = "SELECT * FROM f_layout_unit_template where f_layout_unit_template_id=:f_layout_unit_template_id";
        return namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(new Mdl_f_layout_unit_template(f_layout_unit_template)), new f_layout_unit_template_mapper());
    }

    @Override
    public int get_last_f_layout_unit_template() {
       String sql="select f_layout_unit_template_id from f_layout_unit_template order by f_layout_unit_template_id desc limit 1";
        int n= namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(null), Integer.class);
        return n;
    }

    @Override
    public int layout_by_structure(int structure) {
         String sql = " SELECT  f_layout_unit_template.f_layout_unit_template_id    FROM f_layout_unit_template"
                + " join unit on f_layout_unit_template.unit=unit.unit_id"
                + " join structure on unit.structure=structure.structure_id "
                + " where unit.structure=:structure limit 1";
        MapSqlParameterSource paramsource = new MapSqlParameterSource();
        paramsource.addValue("structure", structure);
        int mdl_f_end_layout = namedParameterJdbcTemplate.queryForObject(sql, paramsource, Integer.class);
        return mdl_f_end_layout;
    }

    private SqlParameterSource getParameterByModel(Mdl_f_layout_unit_template mdl_f_layout_unit_template) {
        MapSqlParameterSource paramsource = new MapSqlParameterSource();
        if (mdl_f_layout_unit_template != null) {
            paramsource.addValue("f_layout_unit_template_id", mdl_f_layout_unit_template.getF_layout_unit_template_id());
            paramsource.addValue("layout", mdl_f_layout_unit_template.getLayout());
            paramsource.addValue("unit", mdl_f_layout_unit_template.getUnit());
            paramsource.addValue("form_template", mdl_f_layout_unit_template.getForm_template());
            paramsource.addValue("combo_form", mdl_f_layout_unit_template.getCombo_form());
        }
        return paramsource;
    }
    
    private static final class f_layout_unit_template_mapper implements RowMapper<Mdl_f_layout_unit_template> {

        @Override
        public Mdl_f_layout_unit_template mapRow(ResultSet rs, int i) throws SQLException {
            Mdl_f_layout_unit_template mdl_f_layout_unit_template = new Mdl_f_layout_unit_template();
            mdl_f_layout_unit_template.setF_layout_unit_template_id(rs.getInt("f_layout_unit_template_id"));
            mdl_f_layout_unit_template.setLayout(rs.getInt("layout"));
            mdl_f_layout_unit_template.setUnit(rs.getInt("unit"));
            mdl_f_layout_unit_template.setForm_template(rs.getInt("form_template"));
            mdl_f_layout_unit_template.setCombo_form(rs.getString("combo_form"));

            return mdl_f_layout_unit_template;
        }

    }
}


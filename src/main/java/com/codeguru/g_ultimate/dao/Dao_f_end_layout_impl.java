/*
 * Interface implementation of F_end_layout.
 * @For Author SANGWA 
 sangwa22@gmail.com.
 */
package com.codeguru.g_ultimate.dao;

import com.codeguru.g_ultimate.models.Mdl_account;
import com.codeguru.g_ultimate.models.Mdl_f_end_layout;
import com.codeguru.g_ultimate.models.Mdl_unit_structure;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
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
public class Dao_f_end_layout_impl implements Dao_f_end_layout {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }
 

    @Override
    public void add_f_end_layout(Mdl_f_end_layout f_end_layout) {
        String sql = " INSERT INTO f_end_layout (name,layout_type_id,unit,supplied_index) VALUES  (:name ,:layout_type_id ,:unit  ,:supplied_index)";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(f_end_layout));
    }

    @Override
    public void delete_f_end_layout(int f_end_layout) {

        String sql = "DELETE from f_end_layout where F_end_layout_id=:F_end_layout_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(new Mdl_f_end_layout(f_end_layout)));
    }

    @Override
    public void update_f_end_layout(Mdl_f_end_layout f_end_layout) {
        try {
            String sql = "  UPDATE f_end_layout SET    name=:name   "
                    + "  WHERE F_end_layout_id=:F_end_layout_id  ";
            int res = namedParameterJdbcTemplate.update(sql, getParameterByModel(f_end_layout));
            System.out.println("Result on updating: " + res);
        } catch (DataAccessException e) {
            System.out.println("Error: " + e.toString() + " " + Dao_f_end_layout_impl.class.getName());

        }
    }

    @Override
    public List<Mdl_f_end_layout> find_f_end_layoutBy_id(int f_end_layout) {
        try {

            String sql = "SELECT * FROM f_end_layout where F_end_layout_id=:F_end_layout_id";

            List<String> str = namedParameterJdbcTemplate.query(sql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int i) throws SQLException {
                    return rs.getString(1);
                }
            });

            if (str.isEmpty()) {
                return null;
            } else {
                return namedParameterJdbcTemplate.query(sql, getParameterByModel(new Mdl_f_end_layout(f_end_layout)), new f_end_layout_mapper());
            }

        } catch (DataAccessException e) {
            System.out.println("Error not finding an item in " + Dao_f_end_layout_impl.class.getName() + " - " + e.toString());
            return null;
        }
    }

    @Override
    public int get_last_f_end_layout() {
        String sql = "select f_end_layout_id from f_end_layout order by f_end_layout_id desc limit 1";
        int n = namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(null), Integer.class);
        return n;
    }

    private SqlParameterSource getParameterByModel(Mdl_f_end_layout mdl_f_end_layout) {
        MapSqlParameterSource paramsource = new MapSqlParameterSource();
        if (mdl_f_end_layout != null) {
            paramsource.addValue("F_end_layout_id", mdl_f_end_layout.getF_end_layout_id());
            paramsource.addValue("name", mdl_f_end_layout.getName());
            paramsource.addValue("layout_type_id", mdl_f_end_layout.getLayout_type());
            paramsource.addValue("unit", mdl_f_end_layout.getUnit());
            paramsource.addValue("order", mdl_f_end_layout.getOrder());
            paramsource.addValue("supplied_index", mdl_f_end_layout.getSupplied_index());
        }
        return paramsource;
    }

    private SqlParameterSource getParameterByIdName(Mdl_f_end_layout mdl_f_end_layout) {
        MapSqlParameterSource paramsource = new MapSqlParameterSource();
        if (mdl_f_end_layout != null) {
            paramsource.addValue("f_end_layout_id", mdl_f_end_layout.getF_end_layout_id());
            paramsource.addValue("name", mdl_f_end_layout.getName());

        }
        return paramsource;
    }

    @Override
    public Mdl_f_end_layout find_single_f_end_layoutBy_name(String layout_name) {
        String sql = "SELECT * FROM f_end_layout where f_end_layout.name=:name";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", layout_name);

        List<String> str = namedParameterJdbcTemplate.query(sql, params, new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int i) throws SQLException {
                return rs.getString(1);
            }
        });
        if (str.isEmpty()) {
            return null;
        } else {

            return namedParameterJdbcTemplate.queryForObject(sql, params, new f_end_layout_mapper());
        }

    }

    private static final class f_end_layout_mapper implements RowMapper<Mdl_f_end_layout> {

        @Override
        public Mdl_f_end_layout mapRow(ResultSet rs, int i) throws SQLException {
            Mdl_f_end_layout mdl_f_end_layout = new Mdl_f_end_layout();
            mdl_f_end_layout.setF_end_layout_id(rs.getInt("F_end_layout_id"));
            mdl_f_end_layout.setName(rs.getString("name"));
            mdl_f_end_layout.setLayout_type(rs.getInt("layout_type_id"));
            mdl_f_end_layout.setUnit(rs.getInt("unit"));
            
            mdl_f_end_layout.setSupplied_index(rs.getInt("supplied_index"));

            return mdl_f_end_layout;
        }

    }
}

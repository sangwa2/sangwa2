/*
 * Interface implementation of F_end_layout_type.
 * @For Author SANGWA 
 sangwa22@gmail.com.
 */
package com.codeguru.g_ultimate.dao;

import com.codeguru.g_ultimate.models.Mdl_f_end_layout;
import com.codeguru.g_ultimate.models.Mdl_f_end_layout_type;
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
public class Dao_f_end_layout_type_impl implements Dao_f_end_layout_type {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Mdl_f_end_layout_type> all_f_end_layout_types() {
        String sql = "SELECT  f_end_layout_type.f_end_layout_type_idf_end_layout_type.name    FROM f_end_layout_type";
        List<Mdl_f_end_layout_type> list = namedParameterJdbcTemplate.query(sql, getParameterByModel(null), new f_end_layout_type_mapper());
        return list;
    }

    @Override
    public void add_f_end_layout_type(Mdl_f_end_layout_type f_end_layout_type) {
        String sql = " INSERT INTO f_end_layout_type (name, structure) VALUES  (:name, :structure)";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(f_end_layout_type));
    }

    @Override
    public void delete_f_end_layout_type(int f_end_layout_type) {
        String sql = "DELETE from f_end_layout_type where f_end_layout_type_id=:f_end_layout_type_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(new Mdl_f_end_layout_type(f_end_layout_type)));
    }

    @Override
    public void update_f_end_layout_type(Mdl_f_end_layout_type f_end_layout_type) {
        String sql = "UPDATE child SET   f_end_layout_type_id_id= : f_end_layout_type_id_idname_id= : name_id  WHERE f_end_layout_type_id =:f_end_layout_type_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(f_end_layout_type));
    }

    @Override
    public Mdl_f_end_layout_type find_f_end_layout_typeBy_id(int f_end_layout_type) {
        String sql = "SELECT * FROM f_end_layout_type where f_end_layout_type_id=:f_end_layout_type_id";
        return namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(new Mdl_f_end_layout_type(f_end_layout_type)), new f_end_layout_type_mapper());
    }

    @Override
    public int get_last_f_end_layout_type() {
        String sql = "select f_end_layout_type_id from f_end_layout_type order by f_end_layout_type_id desc limit 1";
        int n = namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(null), Integer.class);
        return n;
    }

    private SqlParameterSource getParameterByModel(Mdl_f_end_layout_type mdl_f_end_layout_type) {
        MapSqlParameterSource paramsource = new MapSqlParameterSource();
        if (mdl_f_end_layout_type != null) {
            paramsource.addValue("f_end_layout_type_id", mdl_f_end_layout_type.getF_end_layout_type_id());
            paramsource.addValue("name", mdl_f_end_layout_type.getName());
            paramsource.addValue("structure", mdl_f_end_layout_type.getStructure());
        }
        return paramsource;
    }

    @Override
    public List<Mdl_f_end_layout_type> f_end_lay_type_by_structure(int structure) {
        String sql = " select *    from f_end_layout_type "
                + " where f_end_layout_type.structure=:structure";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("structure", structure);
        List<Mdl_f_end_layout_type> list = namedParameterJdbcTemplate.query(sql, params, new f_end_layout_type_mapper());
        return list;
    }

    @Override
    public Mdl_f_end_layout_type find_f_end_layout_typeBy_name(String type_name) {
        String sql = " select * from f_end_layout_type \n"
                + " where f_end_layout_type.name=:name limit 1";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", type_name);

        List<String> str = namedParameterJdbcTemplate.query(sql, params, new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int i) throws SQLException {
                return rs.getString(1);
            }
        });
        
        if (str.isEmpty()) {
            return null;
        } else {
            return namedParameterJdbcTemplate.queryForObject(sql, params, new f_end_layout_type_mapper());
        }
    }

    private static final class f_end_layout_type_mapper implements RowMapper<Mdl_f_end_layout_type> {

        @Override
        public Mdl_f_end_layout_type mapRow(ResultSet rs, int i) throws SQLException {
            Mdl_f_end_layout_type mdl_f_end_layout_type = new Mdl_f_end_layout_type();
            mdl_f_end_layout_type.setF_end_layout_type_id(rs.getInt("f_end_layout_type_id"));
            mdl_f_end_layout_type.setName(rs.getString("name"));
            mdl_f_end_layout_type.setStructure(rs.getInt("structure"));

            return mdl_f_end_layout_type;
        }

    }
}

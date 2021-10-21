/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.dao;

import com.codeguru.g_ultimate.models.Mdl_account;
import com.codeguru.g_ultimate.models.Mdl_f_end_layout;
import com.codeguru.g_ultimate.models.Mdl_fk_order;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
public class Dao_fk_order_impl implements Dao_fk_order {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Mdl_fk_order> all_fk_orders() {
        String sql = "SELECT  fk_order_id, layout_id, unit, tuple, disp_type    FROM fk_order";
        List<Mdl_fk_order> list = namedParameterJdbcTemplate.query(sql, getParameterByModel(null), new fk_order_mapper());
        return list;
    }

    @Override
    public void add_fk_order(Mdl_fk_order fk_order) {
        String sql = " INSERT INTO fk_order ( layout_id, unit, tuple, disp_type) VALUES  (:layout_id, :unit, :tuple, :disp_type)";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(fk_order));
    }

    @Override
    public void delete_fk_order(int fk_order) {
        String sql = "DELETE from fk_order where fk_order_id=:fk_order_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(new Mdl_fk_order(fk_order)));
    }

    @Override
    public void update_fk_order(Mdl_fk_order fk_order) {
        String sql = "UPDATE fk_order SET  fk_order_id=:fk_order_id,  layout_id=:layout_id, unit=:unit, tuple=:tuple, disp_type=:disp_type  WHERE fk_order_id =:fk_order_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(fk_order));
    }

    @Override
    public Mdl_fk_order find_fk_orderBy_id(int fk_order) {
        String sql = "SELECT * FROM fk_order where fk_order_id=:fk_order_id";
        return namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(new Mdl_fk_order(fk_order)), new fk_order_mapper());
    }

    @Override
    public int get_last_fk_order() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private SqlParameterSource getParameterByModel(Mdl_fk_order mdl_fk_order) {
        MapSqlParameterSource paramsource = new MapSqlParameterSource();
        if (mdl_fk_order != null) {
            paramsource.addValue("fk_order_id", mdl_fk_order.getFk_order_id());
            paramsource.addValue("layout_id", mdl_fk_order.getLayout_id());
            paramsource.addValue("unit", mdl_fk_order.getUnit());
            paramsource.addValue("tuple", mdl_fk_order.getTuple());
            paramsource.addValue("disp_type", mdl_fk_order.getDisp_type());
        }
        return paramsource;
    }

    @Override
    public Mdl_fk_order find_fk_orderBy_layout(int layout) {
        String sql = "SELECT * FROM fk_order where fk_order.layout_id=:layout_id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("layout_id", layout);

        List<String> str = namedParameterJdbcTemplate.query(sql, params, new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int i) throws SQLException {
                return rs.getString(1);
            }
        });

        if (str.isEmpty()) {
            return null;
        } else {
            return namedParameterJdbcTemplate.queryForObject(sql, params, new fk_order_mapper());
        }

    }

    @Override
    public Mdl_fk_order find_fk_orderBy_tuple(int tuple) {
        String sql = "SELECT * FROM fk_order where fk_order.tuple=:tuple";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("tuple", tuple);

        List<String> str = namedParameterJdbcTemplate.query(sql, params, new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int i) throws SQLException {
                return rs.getString(1);
            }
        });
        if (str.isEmpty()) {
            return null;
        } else {
            return namedParameterJdbcTemplate.queryForObject(sql, params, new fk_order_mapper());
        }
    }

    private static final class fk_order_mapper implements RowMapper<Mdl_fk_order> {

        @Override
        public Mdl_fk_order mapRow(ResultSet rs, int i) throws SQLException {
            Mdl_fk_order mdl_fk_order = new Mdl_fk_order();
            mdl_fk_order.setFk_order_id(rs.getInt("fk_order_id"));
            mdl_fk_order.setLayout_id(rs.getInt("layout_id"));
            mdl_fk_order.setUnit(rs.getInt("unit"));
            mdl_fk_order.setTuple(rs.getInt("tuple"));
            mdl_fk_order.setDisp_type(rs.getString("disp_type"));
            return mdl_fk_order;
        }

    }
}

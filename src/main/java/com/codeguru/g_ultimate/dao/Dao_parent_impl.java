/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.dao;

import com.codeguru.g_ultimate.models.Mdl_child;
import com.codeguru.g_ultimate.models.Mdl_parent;
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
public class Dao_parent_impl implements Dao_parent {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Mdl_parent> all_parent() {
        String sql = "SELECT parent.parent_id,     parent.unit_id,     parent.relation_type FROM parent";
        List<Mdl_parent> list = namedParameterJdbcTemplate.query(sql, getParameterByModel(null), new parent_mapper());
        return list;
    }

    @Override
    public void add_parent(Mdl_parent parent) {
        String sql = " INSERT INTO parent (unit_id, relation_type) VALUES (:unit_id, :relation_type)";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(parent));
    }

    @Override
    public void delete_parent(int parent) {
        String sql = "DELETE from parent where parent_id=:parent_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(new Mdl_parent(parent)));
    }

    @Override
    public void update_parent(Mdl_parent parent) {
        String sql = "UPDATE parent SET  unit_id =:unit_id, relation_type =:relation_type WHERE parent_id =:parent_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(parent));
    }

    @Override
    public Mdl_parent find_parentBy_id(int parent) {
        String sql = "SELECT * FROM child where child_id=:child_id";
        return namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(new Mdl_parent(parent)), new parent_mapper());
    }

    @Override
    public int last_parent() {
        String sql = "select parent_id from parent order by parent_id desc limit 1";
        return  namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(null), Integer.class);
        
    }

    private SqlParameterSource getParameterByModel(Mdl_parent mdl_parent) {
        MapSqlParameterSource paramsource = new MapSqlParameterSource();
        if (mdl_parent != null) {
            paramsource.addValue("parent_id", mdl_parent.getParent_id());
            paramsource.addValue("unit_id", mdl_parent.getUnit_id());
            paramsource.addValue("relation_type", mdl_parent.getRelation_type());
        }
        return paramsource;
    }

    private static final class parent_mapper implements RowMapper<Mdl_parent> {

        @Override
        public Mdl_parent mapRow(ResultSet rs, int i) throws SQLException {
            Mdl_parent mdl_parent = new Mdl_parent();
            mdl_parent.setParent_id(rs.getInt("parent_id"));
            mdl_parent.setUnit_id(rs.getInt("unit_id"));
            mdl_parent.setRelation_type(rs.getString("relation_type"));
            return mdl_parent;
        }

    }
}

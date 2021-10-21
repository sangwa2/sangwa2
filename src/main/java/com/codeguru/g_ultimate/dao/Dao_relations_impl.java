/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.dao;

import com.codeguru.g_ultimate.models.Mdl_account;
import com.codeguru.g_ultimate.models.Mdl_relations;
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
public class Dao_relations_impl implements Dao_relations {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Mdl_relations> all_relationss() {
        String sql = "SELECT  relations.relations_id, relations.child_unit_id, relations.parent_unit_id, relations.disp_type   FROM relations";
        List<Mdl_relations> list = namedParameterJdbcTemplate.query(sql, getParameterByModel(null), new relations_mapper());
        return list;
    }

    @Override
    public void add_relations(Mdl_relations relations) {
        String sql = " INSERT INTO relations (child_unit_id, parent_unit_id, disp_type) VALUES  (:child_unit_id, :parent_unit_id, :disp_type)";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(relations));
    }

    @Override
    public void delete_relations(int relations) {
        String sql = "DELETE from relations where relations_id=:relations_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(new Mdl_relations(relations)));
    }

    @Override
    public void update_relations(Mdl_relations relations) {
        String sql = "UPDATE realtions SET  child_unit_id=:child_unit_id, parent_unit_id=:parent_unit_id WHERE relations_id=: relations_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(relations));
    }

    @Override
    public Mdl_relations find_relationsBy_id(int relations) {
        String sql = "SELECT * FROM relations where relations_id=:relations_id";
        return namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(new Mdl_relations(relations)), new relations_mapper());
    }

    @Override
    public int get_last_relations() {
        String sql = "select relations_id from relations order by relations.relations_id desc limit 1";
        return namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(null), Integer.class);

    }

    private SqlParameterSource getParameterByModel(Mdl_relations mdl_relations) {
        MapSqlParameterSource paramsource = new MapSqlParameterSource();
        if (mdl_relations != null) {
            paramsource.addValue("relations_id", mdl_relations.getRelations_id());
            paramsource.addValue("child_unit_id", mdl_relations.getChild_unit_id());
            paramsource.addValue("parent_unit_id", mdl_relations.getParent_unit_id());
            paramsource.addValue("disp_type", mdl_relations.getDisp_type());
        }
        return paramsource;
    }

    @Override
    public void delete_relations_by_unit(int unit_id) {
        String sql = "DELETE from relations where relations.child_unit_id=:child_unit_id or relations.parent_unit_id=:parent_unit_id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("child_unit_id", unit_id);
        params.addValue("parent_unit_id", unit_id);
        namedParameterJdbcTemplate.update(sql, params);

    }

    private static final class relations_mapper implements RowMapper<Mdl_relations> {

        @Override
        public Mdl_relations mapRow(ResultSet rs, int i) throws SQLException {
            Mdl_relations mdl_relations = new Mdl_relations();
            mdl_relations.setRelations_id(rs.getInt("relations_id"));
            mdl_relations.setChild_unit_id(rs.getInt("child_unit_id"));
            mdl_relations.setParent_unit_id(rs.getInt("parent_unit_id"));
            mdl_relations.setDisp_type(rs.getString("disp_type"));
            return mdl_relations;
        }

    }

}

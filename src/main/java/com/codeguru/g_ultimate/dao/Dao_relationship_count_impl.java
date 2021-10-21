/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.dao;

import com.codeguru.g_ultimate.models.Mdl_relationship_count;
import com.codeguru.g_ultimate.models.Mdl_relationships;
import com.codeguru.g_ultimate.models.Mdl_tuple;
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
public class Dao_relationship_count_impl implements Dao_relationship_count {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Mdl_relationship_count> list_all_relationship_count() {
        String sql = "SELECT relationship_count.relationship_count_id, relationship_count.tuple_id,    relationship_count.number_children,    relationship_count.number_parent FROM giga_java.relationship_count";
        List<Mdl_relationship_count> list = namedParameterJdbcTemplate.query(sql, getParameterByModel(null), new relationshipcount_mapper());
        return list;
    }

    @Override
    public void add_relationship_count(Mdl_relationship_count relationship_count) {
        String sql = "INSERT INTO relationship_count (tuple_id, number_children,number_parent)  VALUES (:tuple_id, :number_children, :number_parent);";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(relationship_count));
    }

    @Override
    public void delete_relationship_count(int relationship_count) {
        String sql = "DELETE from relationship_count where relationship_count_id=:relationship_count_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(new Mdl_relationship_count(relationship_count)));
    }

    @Override
    public void update_relationship_count(Mdl_relationship_count relationship_count) {
        String sql = " UPDATE relationship_count SET   tuple_id=:tuple_id, number_children=:number_children, number_parent=:number_parent WHERE relationship_count_id=:relationship_count_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(relationship_count));
    }

    @Override
    public int n_children_by_tuple(int tuple) {
        String sql = "select relationship_count.number_children from relationship_count where tuple_id=:tuple_id  ";
        MapSqlParameterSource paramsource = new MapSqlParameterSource();
        paramsource.addValue("tuple_id", tuple);
        return namedParameterJdbcTemplate.queryForObject(sql, paramsource, Integer.class);
    }

    @Override
    public int n_parent_by_tuple(int tuple) {
        String sql = "select relationship_count.number_parent from relationship_count where tuple_id=:tuple_id  ";
        MapSqlParameterSource paramsource = new MapSqlParameterSource();
        paramsource.addValue("tuple_id", tuple);
        return namedParameterJdbcTemplate.queryForObject(sql, paramsource, Integer.class);
    }

    @Override
    public int find_tuple_countBy_tuple_id(int id) {
        String sql = "select tuple_id from relationship_count where relationship_count.tuple_id=:tuple_id  ";
        MapSqlParameterSource paramsource = new MapSqlParameterSource();
        paramsource.addValue("tuple_id", id);
        
        List<String> str = namedParameterJdbcTemplate.query(sql, paramsource, new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int i) throws SQLException {
                return rs.getString(1);
            }
        });
        if (str.isEmpty()) {
            return 0;
        } else {
            MapSqlParameterSource paramsource2 = new MapSqlParameterSource();
            paramsource.addValue("tuple_id", id);
            return namedParameterJdbcTemplate.queryForObject(sql, paramsource2, Integer.class);

        }
    }

    @Override
    public Mdl_relationship_count rel_count_by_tuple_id(int id) {
        String sql = "SELECT *   from relationship_count where relationship_count.tuple_id=:tuple_id";
        return namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(new Mdl_relationship_count(id)), new relationshipcount_mapper());

    }

    private SqlParameterSource getParameterByModel(Mdl_relationship_count mdl_relationship_count) {
        MapSqlParameterSource paramsource = new MapSqlParameterSource();
        if (mdl_relationship_count != null) {
            paramsource.addValue("relationship_count_id", mdl_relationship_count.getRelationship_count_id());
            paramsource.addValue("tuple_id", mdl_relationship_count.getTuple_id());
            paramsource.addValue("number_children", mdl_relationship_count.getNumber_children());
            paramsource.addValue("number_parent", mdl_relationship_count.getNumber_parent());

        }
        return paramsource;
    }

    private static final class relationshipcount_mapper implements RowMapper<Mdl_relationship_count> {

        @Override
        public Mdl_relationship_count mapRow(ResultSet rs, int i) throws SQLException {
            Mdl_relationship_count mdl_relationship_count = new Mdl_relationship_count();
            mdl_relationship_count.setRelationship_count_id(rs.getInt("relationship_count_id"));
            mdl_relationship_count.setTuple_id(rs.getInt("tuple_id"));
            mdl_relationship_count.setNumber_children(rs.getInt("number_children"));
            mdl_relationship_count.setNumber_parent(rs.getInt("number_parent"));
            return mdl_relationship_count;
        }

    }

}

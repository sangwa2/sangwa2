/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.dao;

import com.codeguru.g_ultimate.models.Mdl_f_end_layout;
import com.codeguru.g_ultimate.models.Mdl_relationships;
import com.codeguru.g_ultimate.models.Mdl_tuple;
import com.codeguru.g_ultimate.models.Mdl_unit;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class Dao_relationships_impl implements Dao_relationships {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Mdl_relationships> list_all_relationships() {
        String sql = "SELECT relationships.relationships_id,   relationships.parent_id,  relationships.child_id,   relationships.parent_count, relationships.child_count FROM giga_java.relationships ";
        List<Mdl_relationships> list = namedParameterJdbcTemplate.query(sql, getParameterByModel(null), new relationships_mapper());
        return list;
    }

    @Override
    public void add_relationships(Mdl_relationships relationships) {
        String sql = "INSERT INTO relationships( parent_id, child_id, parent_count, child_count) VALUES (:parent_id, :child_id, :parent_count, :child_count)";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(relationships));
    }

    @Override
    public void delete_relationships(int relationships) {
        String sql = "DELETE from relationships where relationships_id=:relationships_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(new Mdl_relationships(relationships)));
    }

    @Override
    public void update_relationships(Mdl_relationships relationships) {
        String sql = "UPDATE relationships SET   parent_id =:parent_id, child_id =:child_id, parent_count =:parent_count, child_count =:child_count WHERE relationships_id =:relationships_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(relationships));
    }

    @Override
    public Mdl_relationships find_structurBy_id(int id) {
        String sql = "SELECT * FROM relationships where relationships_id=:relationships_id";
        return namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(new Mdl_relationships(id)), new relationships_mapper());
    }

    private SqlParameterSource getParameterByModel(Mdl_relationships mdl_relationships) {
        MapSqlParameterSource paramsource = new MapSqlParameterSource();
        if (mdl_relationships != null) {
            paramsource.addValue("relationships_id", mdl_relationships.getRelationships_id());
            paramsource.addValue("parent_id", mdl_relationships.getParent_id());
            paramsource.addValue("child_id", mdl_relationships.getChild_id());
            paramsource.addValue("parent_count", mdl_relationships.getParent_count());
            paramsource.addValue("child_count", mdl_relationships.getChild_count());
        }
        return paramsource;
    }

    @Override
    public int get_tuples_by_if_parent(int unit) {
        try {
            String sql = "select tuple.tuple_id from relationship_count\n"
                    + "  join tuple on tuple.tuple_id=relationship_count.tuple_id\n"
                    + "  join unit on unit.unit_id=tuple.unit\n"
                    + "  join structure on structure.structure_id=unit.structure\n"
                    + "  where relationship_count.number_parent=0\n"
                    + "  and tuple.tuple_id=:tuple_id group by tuple.tuple_id";
            MapSqlParameterSource paramsource1 = new MapSqlParameterSource();
            paramsource1.addValue("tuple_id", unit);
            List<String> str = namedParameterJdbcTemplate.query(sql, paramsource1, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int i) throws SQLException {
                    return rs.getString(1);
                }
            });
            if (str.isEmpty()) {
                return 0;
            } else {
                MapSqlParameterSource paramsource = new MapSqlParameterSource();
                paramsource.addValue("tuple_id", unit);
                return namedParameterJdbcTemplate.queryForObject(sql, paramsource, Integer.class);
            }
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }

    }

    @Override
    public int get_tuples_by_if_child(int unit) {
        try {
            String sql = "select tuple.tuple_id from relationship_count\n"
                    + "  join tuple on tuple.tuple_id=relationship_count.tuple_id\n"
                    + "  join unit on unit.unit_id=tuple.unit\n"
                    + "  join structure on structure.structure_id=unit.structure\n"
                    + "  where relationship_count.number_children=0\n"
                    + "  and tuple.tuple_id=:tuple_id group by tuple.tuple_id";
            MapSqlParameterSource paramsource1 = new MapSqlParameterSource();
            paramsource1.addValue("tuple_id", unit);
            List<String> str = namedParameterJdbcTemplate.query(sql, paramsource1, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int i) throws SQLException {
                    return rs.getString(1);
                }
            });
            if (str.isEmpty()) {
                return 0;
            } else {
                MapSqlParameterSource paramsource2 = new MapSqlParameterSource();
                paramsource2.addValue("tuple_id", unit);
                return namedParameterJdbcTemplate.queryForObject(sql, paramsource2, Integer.class);
            }

        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

    @Override
    public int get_tuples_by_if_parentchild(int tuple) {
        try {
            String sql = " select tuple.tuple_id from relationship_count \n"
                    + " join tuple on tuple.tuple_id=relationship_count.tuple_id\n"
                    + " where relationship_count.number_children > 0 "
                    + "  and relationship_count.number_parent>0 "
                    + "  and tuple.tuple_id=:tuple_id group by tuple.tuple_id";
            MapSqlParameterSource paramsource1 = new MapSqlParameterSource();
            paramsource1.addValue("tuple_id", tuple);
            List<String> str = namedParameterJdbcTemplate.query(sql, paramsource1, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int i) throws SQLException {
                    return rs.getString(1);
                }
            });
            if (str.isEmpty()) {
                return 0;
            } else {
                MapSqlParameterSource paramsource2 = new MapSqlParameterSource();
                paramsource2.addValue("tuple_id", tuple);
                return namedParameterJdbcTemplate.queryForObject(sql, paramsource2, Integer.class);
            }

        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

    @Override
    public void delete_relationships_by_unit(int unit  ) {
        String sql = "DELETE from relationships where parent_id=:parent or child_id=:child";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("parent", unit);
        params.addValue("child", unit);
        namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public void delete_relationships_by_structure(int structure  ) {
        String sql = "DELETE from relationships where parent_id "
                + "  in(select unit.unit_id from unit where unit.structure=:parent) or child_id "
                + "  in(select unit.unit_id from unit where unit.structure=:child)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("parent", structure);
        params.addValue("child", structure);
        namedParameterJdbcTemplate.update(sql, params);

    }

    private static final class tuple_mapper implements RowMapper<Mdl_tuple> {

        @Override
        public Mdl_tuple mapRow(ResultSet rs, int i) throws SQLException {
            Mdl_tuple mdl_tuple = new Mdl_tuple();
            mdl_tuple.setTuple_id(rs.getInt("tuple_id"));
            return mdl_tuple;
        }
    }

    private static final class relationships_mapper implements RowMapper<Mdl_relationships> {

        @Override
        public Mdl_relationships mapRow(ResultSet rs, int i) throws SQLException {
            Mdl_relationships mdl_relationships = new Mdl_relationships();
            mdl_relationships.setRelationships_id(rs.getInt("relationships_id"));
            mdl_relationships.setParent_id(rs.getInt("parent_id"));
            mdl_relationships.setChild_id(rs.getInt("child_id"));
            mdl_relationships.setParent_count(rs.getInt("parent_count"));
            mdl_relationships.setChild_count(rs.getInt("child_count"));
            return mdl_relationships;
        }

    }

}

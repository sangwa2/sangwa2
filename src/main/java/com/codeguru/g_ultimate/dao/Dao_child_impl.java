/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.dao;

import com.codeguru.g_ultimate.models.Mdl_child;
import com.codeguru.g_ultimate.models.Mdl_relationships;
import java.sql.DatabaseMetaData;
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
public class Dao_child_impl implements Dao_child {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Mdl_child> all_child() {
        String sql = "SELECT child.child_id,   child.unit_id,   child.parent_id,    child.relation_type    FROM child";
        List<Mdl_child> list = namedParameterJdbcTemplate.query(sql, getParameterByModel(null), new child_mapper());
        
        return list;
    }

    @Override
    public List<Mdl_child> child_by_unit(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add_child(Mdl_child child) {
        String sql =  " INSERT INTO child (unit_id, parent_id, relation_type) VALUES  (:unit_id, :parent_id, :relation_type)";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(child));
    }

    @Override
    public void delete_child(int child) {
        String sql = "DELETE from child where child_id=:child_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(new Mdl_child(child)));
    }

    @Override
    public void update_child(Mdl_child child) {
        String sql = "UPDATE child SET   unit_id=:unit_id, parent_id =:parent_id, relation_type=:relation_type  WHERE child_id =:child_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(child));
    }

    @Override
    public Mdl_child find_childBy_id(int child) {
        String sql = "SELECT * FROM child where child_id=:child_id";
        return namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(new Mdl_child(child)), new child_mapper());
    }

    @Override
    public int last_child() {
       String sql="select child_id from child order by child_id desc limit 1";
        int n= namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(null), Integer.class);
        return n;
    }
    
    private SqlParameterSource getParameterByModel(Mdl_child mdl_child) {
        MapSqlParameterSource paramsource = new MapSqlParameterSource();
        if (mdl_child != null) {
            paramsource.addValue("child_id", mdl_child.getChild_id());
            paramsource.addValue("unit_id", mdl_child.getUnit_id());
            paramsource.addValue("parent_id", mdl_child.getParent_id());
            paramsource.addValue("relation_type", mdl_child.getRelation_type());
        }
        return paramsource;
    }

    private static final class child_mapper implements RowMapper<Mdl_child> {

        @Override
        public Mdl_child mapRow(ResultSet rs, int i) throws SQLException {
            Mdl_child mdl_child = new Mdl_child();
            mdl_child.setChild_id(rs.getInt("child_id"));
            mdl_child.setUnit_id(rs.getInt("unit_id"));
            mdl_child.setParent_id(rs.getInt("parent_id"));
            mdl_child.setRelation_type(rs.getString("relation_type"));

            return mdl_child;
        }

    }
}

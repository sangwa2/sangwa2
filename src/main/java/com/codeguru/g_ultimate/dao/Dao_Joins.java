/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.dao;

import com.codeguru.g_ultimate.models.Mdl_rel_query;
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
public class Dao_Joins {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    List<Mdl_rel_query> set_parents = null;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<Mdl_rel_query> top_parents() throws DataAccessException {
        List<Mdl_rel_query> parents = null;
        try {
            //Get children of each parent
            String parents_sql = "select  u_p.name parent ,  u_c.name as child \n"
                    + "    from relationships \n"
                    + "    join rel_query on rel_query.unit= relationships.parent_id\n"
                    + "    join rel_query_category on rel_query_category.rel_query_category_id=rel_query.rel_query_category\n"
                    + "    join unit as u_p on u_p.unit_id=rel_query.unit\n"
                    + "    join child on child.child_id=relationships.child_id\n"
                    + "    join unit as u_c on u_c.unit_id=child.unit_id\n"
                    + "    join tuple on tuple.tuple_id=rel_query.tuple\n"
                    + "    where relationships.parent_id  not in (select relationships.child_id from relationships) \n"
                    + "	   and rel_query_category.name='parent'\n"
                    + "    and relationships.child_id in (select child.child_id from child)\n"
                    + "    group by rel_query.rel_query_id\n"
                    + "    order by rel_query.rel_query_id ";
            MapSqlParameterSource params = new MapSqlParameterSource();
            set_parents = namedParameterJdbcTemplate.query(parents_sql, getParameterByModel(null), new Dao_Joins.rel_query_mapper());
            //Get the related children
        } catch (EmptyResultDataAccessException e) {
            System.out.println("Error getting top parent: " + new Dao_Joins().getClass().getName()+"\n\n"+ e.toString());
        }
        return set_parents;
    }

    private static final class rel_query_mapper implements RowMapper<Mdl_rel_query> {

        @Override
        public Mdl_rel_query mapRow(ResultSet rs, int i) throws SQLException {
            Mdl_rel_query mdl_rel_query = new Mdl_rel_query();
            mdl_rel_query.setRel_query_id(rs.getInt("rel_query_id"));
            mdl_rel_query.setUnit(rs.getInt("unit"));
            mdl_rel_query.setTuple(rs.getInt("tuple"));
            mdl_rel_query.setName(rs.getString("name"));
            mdl_rel_query.setRel_query_category(rs.getInt("rel_query_category"));
            mdl_rel_query.setParent(rs.getString("parent"));
            mdl_rel_query.setChild(rs.getString("child"));

            return mdl_rel_query;
        }

    }

    private SqlParameterSource getParameterByModel(Mdl_rel_query mdl_rel_query) {
        MapSqlParameterSource paramsource = new MapSqlParameterSource();
        if (mdl_rel_query != null) {
            paramsource.addValue("rel_query_id", mdl_rel_query.getRel_query_id());
            paramsource.addValue("unit", mdl_rel_query.getUnit());
            paramsource.addValue("tuple", mdl_rel_query.getTuple());
            paramsource.addValue("rel_query_category", mdl_rel_query.getRel_query_category());
            paramsource.addValue("parent", mdl_rel_query.getParent());
            paramsource.addValue("child", mdl_rel_query.getChild());
        }
        return paramsource;
    }

}

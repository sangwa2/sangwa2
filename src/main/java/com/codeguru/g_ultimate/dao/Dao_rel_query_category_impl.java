/*
 * Interface implementation of Rel_query_category.
 * @For Author SANGWA 
 sangwa22@gmail.com.
 */
package com.codeguru.g_ultimate.dao;

import com.codeguru.g_ultimate.models.Mdl_rel_query_category;
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
public class Dao_rel_query_category_impl implements Dao_rel_query_category {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Mdl_rel_query_category> all_rel_query_categorys() {
        String sql = "SELECT  rel_query_category.rel_query_category_idrel_query_category.name    FROM rel_query_category";
        List<Mdl_rel_query_category> list = namedParameterJdbcTemplate.query(sql, getParameterByModel(null), new rel_query_category_mapper());
        return list;
    }

    @Override
    public void add_rel_query_category(Mdl_rel_query_category rel_query_category) {
        String sql = " INSERT INTO rel_query_category (rel_query_category_idname) VALUES  ( ,:name)";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(rel_query_category));
    }

    @Override
    public void delete_rel_query_category(int rel_query_category) {
        String sql = "DELETE from rel_query_category where rel_query_category_id=:rel_query_category_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(new Mdl_rel_query_category(rel_query_category)));
    }

    @Override
    public void update_rel_query_category(Mdl_rel_query_category rel_query_category) {
        String sql = "UPDATE child SET   rel_query_category_id_id= : rel_query_category_id_idname_id= : name_id  WHERE rel_query_category_id =:rel_query_category_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(rel_query_category));
    }

    @Override
    public Mdl_rel_query_category find_rel_query_categoryBy_id(int rel_query_category) {
        String sql = "SELECT * FROM rel_query_category where rel_query_category_id=:rel_query_category_id";
        return namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(new Mdl_rel_query_category(rel_query_category)), new rel_query_category_mapper());
    }

    @Override
    public int get_last_rel_query_category() {
        String sql = "select rel_query_category_id from rel_query_category order by rel_query_category_id desc limit 1";
        int n = namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(null), Integer.class);
        return n;
    }

    private SqlParameterSource getParameterByModel(Mdl_rel_query_category mdl_rel_query_category) {
        MapSqlParameterSource paramsource = new MapSqlParameterSource();
        if (mdl_rel_query_category != null) {
            paramsource.addValue("rel_query_category_id", mdl_rel_query_category.getRel_query_category_id());
            paramsource.addValue("name", mdl_rel_query_category.getName());
        }
        return paramsource;
    }

    @Override
    public int querycategoryid_by_categoryname(String name) {

        String sql = " select rel_query_category.rel_query_category_id  from rel_query_category where rel_query_category.name=:name group by rel_query_category.rel_query_category_id";
        MapSqlParameterSource paramsource1 = new MapSqlParameterSource();
        paramsource1.addValue("name", name);
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
            paramsource2.addValue("name", name);
            return namedParameterJdbcTemplate.queryForObject(sql, paramsource2, Integer.class);
        }

    }

    private static final class rel_query_category_mapper implements RowMapper<Mdl_rel_query_category> {

        @Override
        public Mdl_rel_query_category mapRow(ResultSet rs, int i) throws SQLException {
            Mdl_rel_query_category mdl_rel_query_category = new Mdl_rel_query_category();
            mdl_rel_query_category.setRel_query_category_id(rs.getInt("rel_query_category_id"));

            return mdl_rel_query_category;
        }

    }
}

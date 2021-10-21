/*
 * Interface implementation of Code_place.
 * @For Author SANGWA 
 sangwa22@gmail.com.
 */
package com.codeguru.g_ultimate.dao;

import com.codeguru.g_ultimate.models.Mdl_code_place;
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
public class Dao_code_place_impl implements Dao_code_place {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Mdl_code_place> all_code_places() {
        String sql = "SELECT  code_place.code_place_idcode_place.code_number ,code_place.html_code_line ,code_place.query    FROM code_place";
        List<Mdl_code_place> list = namedParameterJdbcTemplate.query(sql, getParameterByModel(null), new code_place_mapper());
        return list;
    }

    @Override
    public void add_code_place(Mdl_code_place code_place) {
        String sql =  " INSERT INTO code_place (code_place_idcode_number,html_code_line,query) VALUES  (:code_place_id:code_number ,:html_code_line ,:query)";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(code_place));
    }

    @Override
    public void delete_code_place(int code_place) {
        String sql = "DELETE from code_place where code_place_id=:code_place_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(new Mdl_code_place(code_place)));
    }

    @Override
    public void update_code_place(Mdl_code_place code_place) {
        String sql = "UPDATE child SET   code_place_id_id= : code_place_id_idcode_number_id= : code_number_id ,:html_code_line_id= : html_code_line_id ,:query_id= : query_id  WHERE code_place_id =:code_place_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(code_place));
    }

    @Override
    public Mdl_code_place find_code_placeBy_id(int code_place) {
        String sql = "SELECT * FROM code_place where code_place_id=:code_place_id";
        return namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(new Mdl_code_place(code_place)), new code_place_mapper());
    }

    @Override
    public int get_last_code_place() {
       String sql="select code_place_id from code_place order by code_place_id desc limit 1";
        int n= namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(null), Integer.class);
        return n;
    }
    
    
    
    
    

    private SqlParameterSource getParameterByModel(Mdl_code_place mdl_code_place) {
        MapSqlParameterSource paramsource = new MapSqlParameterSource();
        if (mdl_code_place != null) {
            paramsource.addValue("code_place_id", mdl_code_place.getCode_place_id());
            paramsource.addValue("code_number", mdl_code_place.getCode_number());
            paramsource.addValue("html_code_line", mdl_code_place.getHtml_code_line());
            paramsource.addValue("query", mdl_code_place.getQuery());
        }
        return paramsource;
    }

    private static final class code_place_mapper implements RowMapper<Mdl_code_place> {

        @Override
        public Mdl_code_place mapRow(ResultSet rs, int i) throws SQLException {
            Mdl_code_place mdl_code_place = new Mdl_code_place();
            mdl_code_place.setCode_place_id(rs.getInt("code_place_id"));
            mdl_code_place.setCode_number(rs.getString("code_number"));
            mdl_code_place.setHtml_code_line(rs.getInt("html_code_line"));
            mdl_code_place.setQuery(rs.getInt("query"));

            return mdl_code_place;
        }

    }
}


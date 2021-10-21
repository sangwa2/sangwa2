/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.dao;

import com.codeguru.g_ultimate.files.db.Model;
import com.codeguru.g_ultimate.files.db.Query;
import com.codeguru.g_ultimate.files.db.Dao;
import com.codeguru.g_ultimate.models.Mdl_tuple;
import com.codeguru.g_ultimate.models.Mdl_unit;
import com.codeguru.g_ultimate.models.Mdl_unit_structure;
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
public class Dao_tuple_impl extends Query implements Dao_tuple {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Mdl_tuple> all_tuples() {
        String sql = "SELECT tuple_id,   unit,   name,    data_type,   category, display_caption, today_date, current_date  FROM tuple ";
        List<Mdl_tuple> list = namedParameterJdbcTemplate.query(sql, getParameterByModel(null), new tuple_mapper());
        return list;
    }

    @Override
    public List<Mdl_tuple> tuples_by_unit(int id) {
        String sql = "SELECT tuple_id,   unit,   name,    data_type,   category, display_caption, today_date, curr_date  FROM tuple where tuple.unit=:unit ";
        MapSqlParameterSource paramsource = new MapSqlParameterSource();
        paramsource.addValue("unit", id);
        List<Mdl_tuple> list = namedParameterJdbcTemplate.query(sql, paramsource, new tuple_mapper());
        return list;
    }

    @Override
    public void add_tuple(Mdl_tuple tuple) {
        String sql = "INSERT INTO tuple (unit, name, data_type, category, display_caption, today_date, curr_date)   VALUES ( :unit, :name, :data_type, :category, :display_caption, :today_date, :curr_date)";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(tuple));

    }

    @Override
    public void delete_tuple(int tuple) {
        String sql = "DELETE from tuple where tuple_id=:tuple_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(new Mdl_tuple(tuple)));
    }

    @Override
    public void update_tuple(Mdl_tuple tuple) {
        String sql = "UPDATE   tuple set tuple_id=:tuple_id, unit=:unit, name=:name, data_type=:data_type, category=:category, display_caption=:display_caption, today_date=:today_date, curr_date=:curr_date where tuple_id=:tuple_id ";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(tuple));
    }

    @Override
    public Mdl_tuple find_tupleBy_id(int tuple) {
        String sql = "SELECT * FROM tuple where tuple_id=:tuple_id";
        return namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(new Mdl_tuple(tuple)), new tuple_mapper());
    }

    @Override
    public int first_tuple_by_unit(int unit) {

        String sql = "select  tuple.tuple_id  from unit  join tuple on unit.unit_id=tuple.unit  where unit.unit_id=:unit order by tuple.tuple_id asc limit 1  ";
        MapSqlParameterSource paramsource1 = new MapSqlParameterSource();
        paramsource1.addValue("unit", unit);
        System.out.println("unit: " + unit);
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
            paramsource2.addValue("unit", unit);
            return namedParameterJdbcTemplate.queryForObject(sql, paramsource2, Integer.class);

        }

    }

    private SqlParameterSource getParameterByModel(Mdl_tuple mdl_tuple) {
        MapSqlParameterSource paramsource = new MapSqlParameterSource();
        if (mdl_tuple != null) {
            paramsource.addValue("tuple_id", mdl_tuple.getTuple_id());
            paramsource.addValue("unit", mdl_tuple.getUnit());
            paramsource.addValue("name", mdl_tuple.getName());
            paramsource.addValue("data_type", mdl_tuple.getData_type());
            paramsource.addValue("category", mdl_tuple.getCategory());
            paramsource.addValue("display_caption", mdl_tuple.getDisplay_caption());
            paramsource.addValue("today_date", mdl_tuple.getToday_date());
            paramsource.addValue("curr_date", mdl_tuple.getCurr_date());
        }
        return paramsource;
    }

    @Override
    public List<Mdl_tuple> get_only_tuples_by_unit(int unit_id) {
        String sql = "select  tuple.tuple_id, tuple.unit, tuple.name, data_type, category, display_caption, today_date, curr_date "
                + ", fk_order.disp_type "
                + " from tuple"
                + " join unit on tuple.unit=unit.unit_id"
                + " left join fk_order on fk_order.tuple=tuple.tuple_id \n"
                + " where unit.unit_id=:unit  ";
        MapSqlParameterSource paramsource = new MapSqlParameterSource();
        paramsource.addValue("unit", unit_id);
        List<Mdl_tuple> list = namedParameterJdbcTemplate.query(sql, paramsource, new tupleFk_mapper());
        return list;
    }

    private static final class tuple_mapper implements RowMapper<Mdl_tuple> {

        @Override
        public Mdl_tuple mapRow(ResultSet rs, int i) throws SQLException {
            Mdl_tuple mdl_tuple = new Mdl_tuple();
            mdl_getTyuple(mdl_tuple, rs);
            return mdl_tuple;
        }

    }

    private static final class tupleFk_mapper implements RowMapper<Mdl_tuple> {

        @Override
        public Mdl_tuple mapRow(ResultSet rs, int i) throws SQLException {
            Mdl_tuple mdl_tuple = new Mdl_tuple();
            mdl_getTyuple(mdl_tuple, rs);
            mdl_tuple.setFkdisp_type(rs.getString("disp_type"));
            return mdl_tuple;
        }
    }

    static void mdl_getTyuple(Mdl_tuple mdl_tuple, ResultSet rs) throws SQLException {
        mdl_tuple.setTuple_id(rs.getInt("tuple_id"));
        mdl_tuple.setUnit(rs.getInt("unit"));
        mdl_tuple.setName(rs.getString("name"));
        mdl_tuple.setData_type(rs.getString("data_type"));
        mdl_tuple.setCategory(rs.getString("category"));
        mdl_tuple.setDisplay_caption(rs.getString("display_caption"));
        mdl_tuple.setToday_date(rs.getString("today_date"));
        mdl_tuple.setCurr_date(rs.getString("curr_date"));
    }
}

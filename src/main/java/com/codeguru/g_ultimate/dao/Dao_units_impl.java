/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.dao;

import com.codeguru.g_ultimate.controller.Existing_database_impl;
import com.codeguru.g_ultimate.models.Mdl_existing_db_tables;
import com.codeguru.g_ultimate.models.Mdl_structure;
import com.codeguru.g_ultimate.models.Mdl_tuple;
import com.codeguru.g_ultimate.models.Mdl_unit;
import com.codeguru.g_ultimate.service.Srv_unit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class Dao_units_impl extends Existing_database_impl implements Dao_units {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Mdl_unit> all_units() {
        String sql = "SELECT unit_id,   structure,   name,    number_children,   number_parent  FROM unit ";
        List<Mdl_unit> list = namedParameterJdbcTemplate.query(sql, getParameterByModel(null), new unit_mapper());
        return list;
    }

    @Override
    public void add_unit(Mdl_unit unit) {
        String sql = "INSERT INTO unit (structure,name,number_children, number_parent)   VALUES (:structure,:name,:number_children, :number_parent)";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(unit));
    }

    @Override
    public void delete_unit(int unit) {
        String sql = "DELETE from unit where unit_id=:unit_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(new Mdl_unit(unit)));
    }

    @Override
    public void update_unit(Mdl_unit unit) {
        String sql = "UPDATE   unit  set structure=:structure, name=:name,number_children=:number_children, number_parent=:number_parent  where unit_id=:unit_id  ";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(unit));
    }

    @Override
    public Mdl_unit find_unitBy_id(int id) {
        String sql = "SELECT * FROM unit where unit_id=:unit_id";
        return namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(new Mdl_unit(id)), new unit_mapper());
    }

    @Override
    public int get_all_units_by_structure(int structure) {
        String sql = "select count(*) from unit where unit.structure=:structure ";
        MapSqlParameterSource paramsource = new MapSqlParameterSource();
        paramsource.addValue("structure", structure);
        int n = namedParameterJdbcTemplate.queryForObject(sql, paramsource, Integer.class);
        return n;
    }

    private SqlParameterSource getParameterByModel(Mdl_unit mdl_unit) {
        MapSqlParameterSource paramsource = new MapSqlParameterSource();
        if (mdl_unit != null) {
            paramsource.addValue("unit_id", mdl_unit.getUnit_id());
            paramsource.addValue("structure", mdl_unit.getStructure());
            paramsource.addValue("name", mdl_unit.getName());
            paramsource.addValue("number_children", mdl_unit.getNumber_children());
            paramsource.addValue("number_parent", mdl_unit.getNumber_parent());
        }
        return paramsource;
    }

    @Override
    public int get_last_unit() {
        String sql = "select unit_id from unit order by unit_id desc limit 1";
        int n = namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(null), Integer.class);
        return n;
    }

    @Override
    public List<Mdl_unit> units_by_structure(int id, String name) {
        String sql = "SELECT unit_id,   structure,   name,    number_children,   number_parent  FROM unit where structure=:structure";
        MapSqlParameterSource paramsource = new MapSqlParameterSource();
        paramsource.addValue("structure", id);
        List<Mdl_unit> list = namedParameterJdbcTemplate.query(sql, paramsource, new unit_mapper());
        return list;
    }

    @Override
    public int get_if_unit_bystructure_exists(String unit, int structure) {
        try {

            String sql = "select unit_id from unit where    unit.name=:name and structure=:structure";
            MapSqlParameterSource paramsource = new MapSqlParameterSource();
            paramsource.addValue("name", unit);
            paramsource.addValue("structure", structure);
            return namedParameterJdbcTemplate.queryForObject(sql, paramsource, Integer.class);
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

    @Override
    public List<Mdl_existing_db_tables> existing_db_tabl(String db) {
        List<Mdl_existing_db_tables> lst = db_tables(db);
        return lst;
    }

    @Override
    public List<Mdl_unit> units_by_layout(int layout_type, int structure) {
        String sql = "select  unit.unit_id, unit.structure, unit.name, number_children,unit.number_parent, "
                + " f_end_layout_type.f_end_layout_type_id,"
                + " f_end_layout.f_end_layout_id as layoutid "
                + "  from unit"
                + "  join f_end_layout on unit.unit_id= f_end_layout.unit"
                + "  join f_end_layout_type on f_end_layout_type.f_end_layout_type_id=f_end_layout.layout_type_id "
                + " join structure on structure.structure_id=unit.structure \n"
                + " where f_end_layout_type.f_end_layout_type_id=:f_end_layout_type and structure.structure_id=:structure";
        MapSqlParameterSource paramsource = new MapSqlParameterSource();
        paramsource.addValue("f_end_layout_type", layout_type);
        paramsource.addValue("structure", structure);
        List<Mdl_unit> list = namedParameterJdbcTemplate.query(sql, paramsource, new unit_layout_mapper());
        return list;
    }


    private void setsModel(Mdl_unit mdl_unit, ResultSet rs) throws SQLException {
        mdl_unit.setUnit_id(rs.getInt("unit_id"));
        mdl_unit.setStructure(rs.getInt("structure"));
        mdl_unit.setName(rs.getString("name"));
        mdl_unit.setNumber_children(rs.getInt("number_children"));
        mdl_unit.setNumber_parent(rs.getInt("number_parent"));
    }
 
    @Override
    public ArrayList<String> tuples_of_existingdb(String db, String table) {
        return tuples_existing_db2(db, table);
    }

    @Override
    public ArrayList<String> add_existing_units(String db, int structure, List<String> table) {
        ArrayList<String> cols = new ArrayList<>();

        for (int i = 0; i < table.size(); i++) {
            if (get_if_unit_bystructure_exists(table.get(i), structure) < 1) {//if the unit exists in the database

                cols.clear();
                cols = tuples_existing_db2(db, table.get(i));
                System.out.println("\n-" + table.get(i) + "-\n");

                Mdl_unit mdl_unit = save_unit(table, i, structure);

                this.add_unit(mdl_unit);

                for (String col : cols) {
                    String datatype = ("VARCHAR").equals(col.split("-")[1]) ? "String" : "INT".equals(col.split("-")[1]) ? "int" : col.split("-")[1];
                    int last_unit = this.get_last_unit();
                    save_tuple(last_unit, col.split("-")[0], datatype);

                }
            } else {
                cols.add("Table exists in the current db (" + db + "-" + table.get(i) + ")");
                System.out.println("Table exists in the current db (" + db + "  -   " + table.get(i) + ")");
            }
        }
        return cols;
    }

    private Mdl_unit save_unit(List<String> table, int i, int structure) throws DataAccessException {
        //saving the unit
        Mdl_unit mdl_unit = new Mdl_unit();

        mdl_unit.setStructure(structure);
        mdl_unit.setName(table.get(i));
        mdl_unit.setNumber_children(0);
        mdl_unit.setNumber_parent(0);

        return mdl_unit;
    }

    private void save_tuple(int last_unit, String col, String datatype) throws DataAccessException {
        //Saving the each tuple
        Mdl_tuple mdl_tuple = new Mdl_tuple();
        mdl_tuple.setUnit(last_unit);
        mdl_tuple.setName(col);
        mdl_tuple.setData_type(datatype);
        mdl_tuple.setCategory("NORMAL");
        mdl_tuple.setDisplay_caption(col);
        mdl_tuple.setToday_date("no");
        mdl_tuple.setCurr_date(TodayDate());

        String sql_tuple = "INSERT INTO tuple (unit, name, data_type, category, display_caption, today_date, curr_date)   VALUES ( :unit, :name, :data_type, :category, :display_caption, :today_date, :curr_date)";
        namedParameterJdbcTemplate.update(sql_tuple, gettuple_ParameterByModel(mdl_tuple));
    }

    private SqlParameterSource gettuple_ParameterByModel(Mdl_tuple mdl_tuple) {
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

    private SqlParameterSource get_str_ParameterByModel(Mdl_structure mdl_structure) {
        MapSqlParameterSource paramsource = new MapSqlParameterSource();
        if (mdl_structure != null) {
            paramsource.addValue("structure_id", mdl_structure.getStructure_id());
            paramsource.addValue("db_name", mdl_structure.getDb_name());

        }
        return paramsource;
    }

    public String TodayDate() {
        Date date = new Date();
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        String d = formatter.format(date);
        return d;
    }
    
    
       private static final class unit_layout_mapper implements RowMapper<Mdl_unit> {
        
        @Override
        public Mdl_unit mapRow(ResultSet rs, int i) throws SQLException {
            Mdl_unit mdl_unit = new Mdl_unit();
            new Dao_units_impl().setsModel(mdl_unit, rs);
            mdl_unit.setLayoutid(rs.getInt("layoutid"));
            return mdl_unit;
        }

    }

    private static final class unit_mapper implements RowMapper<Mdl_unit> {

        @Override
        public Mdl_unit mapRow(ResultSet rs, int i) throws SQLException {
            Mdl_unit mdl_unit = new Mdl_unit();
            new Dao_units_impl().setsModel(mdl_unit, rs);
            return mdl_unit;
        }

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.dao;

import com.codeguru.g_ultimate.controller.Existing_database_impl;
import com.codeguru.g_ultimate.models.Mdl_structure;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SANGWA
 */
@Repository
public class Dao_structure_impl extends Existing_database_impl implements Dao_structure {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Mdl_structure> list_all_structure() {
        String sql = "SELECT structure.structure_id,db_name,  db_user,  db_password,  cash_total,  start_time,  delivery_date,  pgm_language,  platform,  entry_date,  User FROM structure";
        List<Mdl_structure> list = namedParameterJdbcTemplate.query(sql, getParameterByModel(null), new structure_mapper());
        return list;
    }

    private SqlParameterSource getParameterByModel(Mdl_structure mdl_structure) {
        MapSqlParameterSource paramsource = new MapSqlParameterSource();
        if (mdl_structure != null) {
            paramsource.addValue("structure_id", mdl_structure.getStructure_id());
            paramsource.addValue("db_name", mdl_structure.getDb_name());
            paramsource.addValue("db_user", mdl_structure.getDb_user());
            paramsource.addValue("db_password", mdl_structure.getDb_password());
            paramsource.addValue("cash_total", mdl_structure.getCash_total());
            paramsource.addValue("start_time", mdl_structure.getStart_time());
            paramsource.addValue("delivery_date", mdl_structure.getDelivery_date());
            paramsource.addValue("pgm_language", mdl_structure.getPgm_language());
            paramsource.addValue("platform", mdl_structure.getPlatform());
            paramsource.addValue("entry_date", mdl_structure.getEntry_date());
            paramsource.addValue("User", mdl_structure.getUser());
        }
        return paramsource;
    }

    @Override
    public int get_last_structure() {
        String sql = "select structure.structure_id from structure  order by structure_id desc limit 1";
        int n = namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(null), Integer.class);
        return n;
    }

    @Override
    public String db_exists(String name) {
        try {
            String sql = "SELECT  db_name  FROM structure where structure.db_name=:name";
            MapSqlParameterSource param = new MapSqlParameterSource();
            param.addValue("name", name);
            String list = namedParameterJdbcTemplate.queryForObject(sql, param, String.class);
            return list;
        } catch (EmptyResultDataAccessException e) {

            return null;
        }
    }

    @Override
    public ArrayList existing_str() {
       ArrayList<String> dbs =super.all_dbs();
         
        return dbs;
    }

    private static final class structure_mapper implements RowMapper<Mdl_structure> {

        @Override
        public Mdl_structure mapRow(ResultSet rs, int i) throws SQLException {
            Mdl_structure mdl_structure = new Mdl_structure();
            mdl_structure.setStructure_id(rs.getInt("structure_id"));
            mdl_structure.setDb_name(rs.getString("db_name"));
            mdl_structure.setDb_password(rs.getString("db_password"));
            mdl_structure.setCash_total(rs.getInt("cash_total"));
            mdl_structure.setStart_time(rs.getString("cash_total"));
            mdl_structure.setDelivery_date(rs.getString("cash_total"));
            mdl_structure.setPgm_language(rs.getString("pgm_language"));
            mdl_structure.setPlatform(rs.getString("platform"));
            mdl_structure.setEntry_date(rs.getString("entry_date"));
            mdl_structure.setUser(rs.getString("User"));

            return mdl_structure;
        }

    }

    @Override
    public void add_structure(Mdl_structure structure) {
        String sql = "INSERT INTO structure (db_name,db_user,db_password,cash_total,start_time,delivery_date,pgm_language,platform,entry_date,User)   VALUES (:db_name,:db_user,:db_password,:cash_total,:start_time,:delivery_date,:pgm_language,:platform,:entry_date,:User)";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(structure));
    }

    @Override
    public void update_structure(Mdl_structure structure) {
        String sql = "UPDATE   structure set db_name=:db_name,db_user=:db_user,db_password=:db_password,cash_total=:cash_total,start_time=:start_time,delivery_date=:delivery_date,pgm_language=:pgm_language,platform=:platform,entry_date=:entry_date,User=:User where structure_id=:structure_id ) ";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(structure));
    }

    @Override
    public void delete_structure(int structure) {
        String sql = "DELETE from structure where structure_id=:structure_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(new Mdl_structure(structure)));
    }

    @Override
    public Mdl_structure find_structurBy_id(int id) {
        String sql = "SELECT * FROM structure where structure_id=:structure_id";
        return namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(new Mdl_structure(id)), new structure_mapper());

    }

}

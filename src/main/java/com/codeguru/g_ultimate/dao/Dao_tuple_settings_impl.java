/*
 * Interface implementation of Tuple_settings.
 * @For Author SANGWA 
 sangwa22@gmail.com.
 */
package com.codeguru.g_ultimate.dao;

import com.codeguru.g_ultimate.models.Mdl_tuple_settings;
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
public class Dao_tuple_settings_impl implements Dao_tuple_settings {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Mdl_tuple_settings> all_tuple_settingss() {
        String sql = "SELECT  tuple_settings.tuple_settings_id ,tuple_settings.tuple ,tuple_settings.enable_update ,tuple_settings.input_length ,tuple_settings.number_only ,tuple_settings.number_andthousand_separator ,tuple_settings.other_format ,tuple_settings.auto_date_time ,tuple_settings.auto_date ,tuple_settings.auto_time ,tuple_settings.auto_logged_in_user ,tuple_settings.auto_filled_from_other_field ,tuple_settings.view_on_insert ,tuple_settings.view_on_datalist    FROM tuple_settings";
        List<Mdl_tuple_settings> list = namedParameterJdbcTemplate.query(sql, getParameterByModel(null), new tuple_settings_mapper());
        return list;
    }

    @Override
    public void add_tuple_settings(Mdl_tuple_settings tuple_settings) {
        String sql =  " INSERT INTO tuple_settings (tuple_settings_id,tuple,enable_update,input_length,number_only,number_andthousand_separator,other_format,auto_date_time,auto_date,auto_time,auto_logged_in_user,auto_filled_from_other_field,view_on_insert,view_on_datalist) VALUES  (:tuple_settings_id ,:tuple ,:enable_update ,:input_length ,:number_only ,:number_andthousand_separator ,:other_format ,:auto_date_time ,:auto_date ,:auto_time ,:auto_logged_in_user ,:auto_filled_from_other_field ,:view_on_insert ,:view_on_datalist)";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(tuple_settings));
    }

    @Override
    public void delete_tuple_settings(int tuple_settings) {
        String sql = "DELETE from tuple_settings where tuple_settings_id=:tuple_settings_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(new Mdl_tuple_settings(tuple_settings)));
    }

    @Override
    public void update_tuple_settings(Mdl_tuple_settings tuple_settings) {
        String sql = "UPDATE child SET   tuple_settings_id_id= : tuple_settings_id_idtuple_id= : tuple_id ,:enable_update_id= : enable_update_id ,:input_length_id= : input_length_id ,:number_only_id= : number_only_id ,:number_andthousand_separator_id= : number_andthousand_separator_id ,:other_format_id= : other_format_id ,:auto_date_time_id= : auto_date_time_id ,:auto_date_id= : auto_date_id ,:auto_time_id= : auto_time_id ,:auto_logged_in_user_id= : auto_logged_in_user_id ,:auto_filled_from_other_field_id= : auto_filled_from_other_field_id ,:view_on_insert_id= : view_on_insert_id ,:view_on_datalist_id= : view_on_datalist_id  WHERE tuple_settings_id =:tuple_settings_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(tuple_settings));
    }

    @Override
    public Mdl_tuple_settings find_tuple_settingsBy_id(int tuple_settings) {
        String sql = "SELECT * FROM tuple_settings where tuple_settings_id=:tuple_settings_id";
        return namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(new Mdl_tuple_settings(tuple_settings)), new tuple_settings_mapper());
    }

    @Override
    public int get_last_tuple_settings() {
       String sql="select tuple_settings_id from tuple_settings order by tuple_settings_id desc limit 1";
        int n= namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(null), Integer.class);
        return n;
    }
    
    
    
    
    

    private SqlParameterSource getParameterByModel(Mdl_tuple_settings mdl_tuple_settings) {
        MapSqlParameterSource paramsource = new MapSqlParameterSource();
        if (mdl_tuple_settings != null) {
            paramsource.addValue("tuple_settings_id", mdl_tuple_settings.getTuple_settings_id());
            paramsource.addValue("tuple", mdl_tuple_settings.getTuple());
            paramsource.addValue("enable_update", mdl_tuple_settings.getEnable_update());
            paramsource.addValue("input_length", mdl_tuple_settings.getInput_length());
            paramsource.addValue("number_only", mdl_tuple_settings.getNumber_only());
            paramsource.addValue("number_andthousand_separator", mdl_tuple_settings.getNumber_andthousand_separator());
            paramsource.addValue("other_format", mdl_tuple_settings.getOther_format());
            paramsource.addValue("auto_date_time", mdl_tuple_settings.getAuto_date_time());
            paramsource.addValue("auto_date", mdl_tuple_settings.getAuto_date());
            paramsource.addValue("auto_time", mdl_tuple_settings.getAuto_time());
            paramsource.addValue("auto_logged_in_user", mdl_tuple_settings.getAuto_logged_in_user());
            paramsource.addValue("auto_filled_from_other_field", mdl_tuple_settings.getAuto_filled_from_other_field());
            paramsource.addValue("view_on_insert", mdl_tuple_settings.getView_on_insert());
            paramsource.addValue("view_on_datalist", mdl_tuple_settings.getView_on_datalist());
        }
        return paramsource;
    }

    private static final class tuple_settings_mapper implements RowMapper<Mdl_tuple_settings> {

        @Override
        public Mdl_tuple_settings mapRow(ResultSet rs, int i) throws SQLException {
            Mdl_tuple_settings mdl_tuple_settings = new Mdl_tuple_settings();
            mdl_tuple_settings.setTuple_settings_id(rs.getInt("tuple_settings_id"));
            mdl_tuple_settings.setTuple(rs.getInt("tuple"));
            mdl_tuple_settings.setEnable_update(rs.getString("enable_update"));
            mdl_tuple_settings.setInput_length(rs.getString("input_length"));
            mdl_tuple_settings.setNumber_only(rs.getString("number_only"));
            mdl_tuple_settings.setNumber_andthousand_separator(rs.getString("number_andthousand_separator"));
            mdl_tuple_settings.setOther_format(rs.getString("other_format"));
            mdl_tuple_settings.setAuto_date_time(rs.getString("auto_date_time"));
            mdl_tuple_settings.setAuto_date(rs.getString("auto_date"));
            mdl_tuple_settings.setAuto_time(rs.getString("auto_time"));
            mdl_tuple_settings.setAuto_logged_in_user(rs.getString("auto_logged_in_user"));
            mdl_tuple_settings.setAuto_filled_from_other_field(rs.getString("auto_filled_from_other_field"));
            mdl_tuple_settings.setView_on_insert(rs.getString("view_on_insert"));
            mdl_tuple_settings.setView_on_datalist(rs.getString("view_on_datalist"));

            return mdl_tuple_settings;
        }

    }
}


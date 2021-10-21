/*
 * Interface implementation of Profile.
 * @For Author SANGWA 
 sangwa22@gmail.com.
 */
package com.codeguru.g_ultimate.dao;

import com.codeguru.g_ultimate.models.Mdl_profile;
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
public class Dao_profile_impl implements Dao_profile {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Mdl_profile> all_profiles() {
        String sql = "SELECT  profile.profile_idprofile.name ,profile.surname ,profile.date_birth ,profile.gender ,profile.tel ,profile.email ,profile.residence ,profile.image    FROM profile";
        List<Mdl_profile> list = namedParameterJdbcTemplate.query(sql, getParameterByModel(null), new profile_mapper());
        return list;
    }

    @Override
    public void add_profile(Mdl_profile profile) {
        String sql =  " INSERT INTO profile (profile_idname,surname,date_birth,gender,tel,email,residence,image) VALUES  (:profile_id:name ,:surname ,:date_birth ,:gender ,:tel ,:email ,:residence ,:image)";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(profile));
    }

    @Override
    public void delete_profile(int profile) {
        String sql = "DELETE from profile where profile_id=:profile_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(new Mdl_profile(profile)));
    }

    @Override
    public void update_profile(Mdl_profile profile) {
        String sql = "UPDATE child SET   profile_id_id= : profile_id_idname_id= : name_id ,:surname_id= : surname_id ,:date_birth_id= : date_birth_id ,:gender_id= : gender_id ,:tel_id= : tel_id ,:email_id= : email_id ,:residence_id= : residence_id ,:image_id= : image_id  WHERE profile_id =:profile_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(profile));
    }

    @Override
    public Mdl_profile find_profileBy_id(int profile) {
        String sql = "SELECT * FROM profile where profile_id=:profile_id";
        return namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(new Mdl_profile(profile)), new profile_mapper());
    }

    @Override
    public int get_last_profile() {
       String sql="select profile_id from profile order by profile_id desc limit 1";
        int n= namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(null), Integer.class);
        return n;
    }
    
    
    
    
    

    private SqlParameterSource getParameterByModel(Mdl_profile mdl_profile) {
        MapSqlParameterSource paramsource = new MapSqlParameterSource();
        if (mdl_profile != null) {
            paramsource.addValue("profile_id", mdl_profile.getProfile_id());
            paramsource.addValue("name", mdl_profile.getName());
            paramsource.addValue("surname", mdl_profile.getSurname());
            paramsource.addValue("date_birth", mdl_profile.getDate_birth());
            paramsource.addValue("gender", mdl_profile.getGender());
            paramsource.addValue("tel", mdl_profile.getTel());
            paramsource.addValue("email", mdl_profile.getEmail());
            paramsource.addValue("residence", mdl_profile.getResidence());
            paramsource.addValue("image", mdl_profile.getImage());
        }
        return paramsource;
    }

    private static final class profile_mapper implements RowMapper<Mdl_profile> {

        @Override
        public Mdl_profile mapRow(ResultSet rs, int i) throws SQLException {
            Mdl_profile mdl_profile = new Mdl_profile();
            mdl_profile.setProfile_id(rs.getInt("profile_id"));
            mdl_profile.setName(rs.getString("name"));
            mdl_profile.setSurname(rs.getString("surname"));
            mdl_profile.setDate_birth(rs.getString("date_birth"));
            mdl_profile.setGender(rs.getString("gender"));
            mdl_profile.setTel(rs.getString("tel"));
            mdl_profile.setEmail(rs.getString("email"));
            mdl_profile.setResidence(rs.getString("residence"));
            mdl_profile.setImage(rs.getInt("image"));

            return mdl_profile;
        }

    }
}


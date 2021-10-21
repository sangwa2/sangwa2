/*
 * Interface implementation of Account_category.
 * @For Author SANGWA 
 sangwa22@gmail.com.
 */
package com.codeguru.g_ultimate.dao;

import com.codeguru.g_ultimate.models.Mdl_account_category;
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
public class Dao_account_category_impl implements Dao_account_category {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Mdl_account_category> all_account_categorys() {
        String sql = "SELECT  account_category.account_category_idaccount_category.name    FROM account_category";
        List<Mdl_account_category> list = namedParameterJdbcTemplate.query(sql, getParameterByModel(null), new account_category_mapper());
        return list;
    }

    @Override
    public void add_account_category(Mdl_account_category account_category) {
        String sql =  " INSERT INTO account_category (name) VALUES  (:name)";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(account_category));
    }

    @Override
    public void delete_account_category(int account_category) {
        String sql = "DELETE from account_category where account_category_id=:account_category_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(new Mdl_account_category(account_category)));
    }

    @Override
    public void update_account_category(Mdl_account_category account_category) {
        String sql = "UPDATE child SET   account_category_id_id= : account_category_id_idname_id= : name_id  WHERE account_category_id =:account_category_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(account_category));
    }

    @Override
    public Mdl_account_category find_account_categoryBy_id(int account_category) {
        String sql = "SELECT * FROM account_category where account_category_id=:account_category_id";
        return namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(new Mdl_account_category(account_category)), new account_category_mapper());
    }

    @Override
    public int get_last_account_category() {
       String sql="select account_category_id from account_category order by account_category_id desc limit 1";
        int n= namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(null), Integer.class);
        return n;
    }
    
    
    
    
    

    private SqlParameterSource getParameterByModel(Mdl_account_category mdl_account_category) {
        MapSqlParameterSource paramsource = new MapSqlParameterSource();
        if (mdl_account_category != null) {
            paramsource.addValue("account_category_id", mdl_account_category.getAccount_category_id());
            paramsource.addValue("name", mdl_account_category.getName());
        }
        return paramsource;
    }

    private static final class account_category_mapper implements RowMapper<Mdl_account_category> {

        @Override
        public Mdl_account_category mapRow(ResultSet rs, int i) throws SQLException {
            Mdl_account_category mdl_account_category = new Mdl_account_category();
            mdl_account_category.setAccount_category_id(rs.getInt("account_category_id"));
            mdl_account_category.setName(rs.getString("name"));

            return mdl_account_category;
        }

    }
}


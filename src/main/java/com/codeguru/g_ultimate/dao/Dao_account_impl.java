/*
 * Interface implementation of Account.
 * @For Author SANGWA 
 sangwa22@gmail.com.
 */
package com.codeguru.g_ultimate.dao;

import com.codeguru.g_ultimate.models.Mdl_account;
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
public class Dao_account_impl implements Dao_account {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Mdl_account> all_accounts() {
        String sql = "SELECT  account.account_id,account.name ,account.account_category ,account.profile    FROM account";
        List<Mdl_account> list = namedParameterJdbcTemplate.query(sql, getParameterByModel(null), new account_mapper());
        return list;
    }

    @Override
    public void add_account(Mdl_account account) {
        String sql = " INSERT INTO account (account_id,name,account_category,profile) VALUES  (:account_id,:name ,:account_category ,:profile)";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(account));
    }

    @Override
    public void delete_account(int account) {
        String sql = "DELETE from account where account_id=:account_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(new Mdl_account(account)));
    }

    @Override
    public void update_account(Mdl_account account) {
        String sql = "UPDATE child SET   account_id_id= : account_id_idname_id= : name_id ,:account_category_id= : account_category_id ,:profile_id= : profile_id  WHERE account_id =:account_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(account));
    }

    @Override
    public Mdl_account find_accountBy_id(int account) {
        String sql = "SELECT * FROM account where account_id=:account_id";
        return namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(new Mdl_account(account)), new account_mapper());
    }

    @Override
    public int get_last_account() {
        String sql = "select account_id from account order by account_id desc limit 1";
        int n = namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(null), Integer.class);
        return n;
    }

    private SqlParameterSource getParameterByModel(Mdl_account mdl_account) {
        MapSqlParameterSource paramsource = new MapSqlParameterSource();
        if (mdl_account != null) {
            paramsource.addValue("account_id", mdl_account.getAccount_id());
            paramsource.addValue("name", mdl_account.getName());
            paramsource.addValue("account_category", mdl_account.getAccount_category());
            paramsource.addValue("profile", mdl_account.getProfile());
        }
        return paramsource;
    }

    @Override
    public Mdl_account find_accountBy_usename_pass(String username, String password) {
        String sql = "SELECT * FROM account where account.username=:username and account.password=:password";
        MapSqlParameterSource paramsource = new MapSqlParameterSource();
        paramsource.addValue("username", username);
        paramsource.addValue("password", password);

        List<String> str = namedParameterJdbcTemplate.query(sql, paramsource, new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int i) throws SQLException {
                return rs.getString(1);
            }
        });

        if (str.isEmpty()) {
            return null;
        } else {
            return namedParameterJdbcTemplate.queryForObject(sql, paramsource, new account_mapper());
        }

    }

    private static final class account_mapper implements RowMapper<Mdl_account> {

        @Override
        public Mdl_account mapRow(ResultSet rs, int i) throws SQLException {
            Mdl_account mdl_account = new Mdl_account();
            mdl_account.setUsername(rs.getString("username"));
            mdl_account.setPassword(rs.getString("password"));
            mdl_account.setAccount_category(rs.getInt("account_category"));
            return mdl_account;
        }

    }
}

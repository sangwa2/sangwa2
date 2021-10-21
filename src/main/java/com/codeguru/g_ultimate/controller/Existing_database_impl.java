/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.controller;

import com.codeguru.g_ultimate.Config;
import com.codeguru.g_ultimate.models.Mdl_child;
import com.codeguru.g_ultimate.models.Mdl_existing_db_tables;
import com.codeguru.g_ultimate.models.Mdl_tuple;
import com.codeguru.g_ultimate.models.Mdl_unit;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.jdbc.support.MetaDataAccessException;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SANGWA
 */
@Repository
public class Existing_database_impl extends Config {

    public DataSource getDataSource(String database) {

        DriverManagerDataSource datasource = new DriverManagerDataSource();
        datasource.setDriverClassName("com.mysql.jdbc.Driver");
        datasource.setUsername(super.uname);
        datasource.setPassword(super.psw);
        datasource.setUrl("jdbc:mysql://localhost:3306/" + database);
        return datasource;
    }

    public ArrayList all_dbs() {
        ArrayList<String> dbs = new ArrayList<>();
        try {
            DriverManagerDataSource datasource = new DriverManagerDataSource();
            datasource.setDriverClassName("com.mysql.jdbc.Driver");
            datasource.setUsername(Config.uname);
            datasource.setPassword(Config.psw);
            datasource.setUrl("jdbc:mysql://localhost:3306");
            Connection conn = DataSourceUtils.getConnection(datasource);
            DatabaseMetaData md = conn.getMetaData();
            ResultSet rdb = md.getCatalogs();
            while (rdb.next()) {
                dbs.add(rdb.getString(1));
            }

        } catch (SQLException ex) {
            Logger.getLogger(Existing_database_impl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dbs;
    }

    public List<Mdl_existing_db_tables> db_tables(String db) {
        List<Mdl_existing_db_tables> tables_with_columns = new ArrayList<>();

        try {
            Connection conn = DataSourceUtils.getConnection(this.getDataSource(db));
            DatabaseMetaData md = conn.getMetaData();
            ResultSet rs = md.getTables(null, null, "%", null);
            List< Mdl_tuple> columns_of_table = new ArrayList<>();
            ArrayList<String> my_tables = new ArrayList<>();
            while (rs.next()) {
                my_tables.add(rs.getString("TABLE_NAME"));
                System.out.println("Table: " + rs.getString("TABLE_NAME"));
            }
            for (int i = 0; i < my_tables.size(); i++) {
                try {
                    ResultSet columns = md.getColumns(null, null, my_tables.get(i), null);
                    while (columns.next()) {
                        columns_of_table.add(new Mdl_tuple(columns.getString("COLUMN_NAME"), columns.getString("COLUMN_NAME")));

                    }
                    tables_with_columns.add(new Mdl_existing_db_tables(my_tables.get(i), columns_of_table));

                } catch (SQLException ex) {
                    Logger.getLogger(Existing_database_impl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(Existing_database_impl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tables_with_columns;
    }

    public ArrayList<String> tuples_existing_db2(String db, String table) {

        ArrayList<String> columnslist = new ArrayList<>();
        try {
            Connection conn = DataSourceUtils.getConnection(this.getDataSource(db));
            DatabaseMetaData md = conn.getMetaData();

            ResultSet columns = md.getColumns(null, null, table, null);
            while (columns.next()) {
                //get the last unit_id
                columnslist.add(columns.getString("COLUMN_NAME") + "-" + columns.getString("TYPE_NAME") );

            }
        } catch (SQLException ex) {
            Logger.getLogger(Existing_database_impl.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return columnslist;
    }

 
}

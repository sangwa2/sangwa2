/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.controller;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.springframework.jdbc.support.DatabaseMetaDataCallback;
import org.springframework.stereotype.Controller;

/**
 *
 * @author SANGWA
 */
@Controller
public class Existing_db implements DatabaseMetaDataCallback {

      
    @Override
    public Object processMetaData(DatabaseMetaData dbmd) throws SQLException {
        ResultSet rs = dbmd.getTables(dbmd.getUserName(), null, null, new String[]{"TABLE"});
        ArrayList l = new ArrayList();
        while (rs.next()) {
            l.add(rs.getString(3));
            System.out.println("Table: "+ rs.getString(3));
        }
        return l;
    }

}

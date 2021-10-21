/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.models;

import java.util.List;

/**
 *
 * @author SANGWA
 */
public class Mdl_existing_db_tables {

    private String table;
    private List<Mdl_tuple> columns;

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public List<Mdl_tuple> getColumns() {
        return columns;
    }

    public void setColumns(List<Mdl_tuple> columns) {
        this.columns = columns;
    }

    public Mdl_existing_db_tables() {
    }

    
    public Mdl_existing_db_tables(String table, List<Mdl_tuple> columns) {
        this.table = table;
        this.columns = columns;
    }

}

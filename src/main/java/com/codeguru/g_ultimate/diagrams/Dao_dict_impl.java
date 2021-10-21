/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.diagrams;

import com.codeguru.g_ultimate.files.db.Dao_impl;
import com.codeguru.g_ultimate.files.db.Model;
import com.codeguru.g_ultimate.files.db.Query;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SANGWA
 */
@Repository
public class Dao_dict_impl extends Query implements Dao_dict {

    String tuple = "", data_type = "", constraint = "", all_code = "", tuples = "",
            open_tab_header = "", close_tab_header = "", tab_close = "", pack = "", header_tuples = "";
    int c = 0;

    NamedParameterJdbcTemplate np;

    @Autowired
    public void setNp(NamedParameterJdbcTemplate np) {
        this.np = np;
    }

    @Override
    public String dictionary(int structure) {
        //<editor-fold defaultstate="collapsed" desc="--Clear fields--">
        pack = "";
        open_tab_header = "";
        header_tuples = "";
        close_tab_header = "";
        all_code = "";
        tab_close = "";
        //</editor-fold>
        List<Model> structure_det = this.structure_details(structure);
        open_tab_header = dict_open_table_header();
        close_tab_header = dict_close_table_header();
        tab_close = dict_close_table();
        for (int i = 0; i < structure_det.size(); i++) {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("structure", structure_det.get(i).getStructure());
            params.addValue("unit", structure_det.get(i).getUnit_id());
            List<Model> list = np.query(unit_tuples_query(), params, new db_mapper());
            c = 0;
            header_tuples = "";
            tuples = "";
            header_tuples = dict_header_tuples();
            for (int j = 0; j < list.size(); j++) {
                tuple = list.get(j).getTuple_name();
                data_type = list.get(j).getData_type();
                constraint = list.get(j).getCategory();
                data_type_check();
                tuples += dict_tuples(c, list.get(j).getUnit_name(), tuple, data_type, constraint);
                c += 1;

            }
            all_code += tuples;
        }
        pack = open_tab_header + header_tuples + close_tab_header + all_code + tab_close;
        return pack;
    }

    private void data_type_check() {
        if (null == constraint) {
            constraint = "Normal";
        } else {
            switch (constraint) {
                case "PK":
                    constraint = "Primary key";
                    break;
                case "FK":
                    constraint = "Foreign key";
                    break;
                default:
                    constraint = "Normal";
                    break;
            }
        }
    }

    String dict_open_table_header() {
        return "<table class=\"dict\">\n<tr>";
    }

    String dict_header_tuples() {
        return "\t<td><b>TABLE</b></td><td><b>COLUMN</b></td><td><b>DATA TYPE</b></td><td><b>CONSTRAINT</b></td>\n";
    }

    String dict_close_table_header() {
        return "</tr>\n";
    }

    String dict_tuples(int c, String table, String tuple, String data_type, String constraint) {
        String tab = (c != 0) ? "" : table;
        return "<tr><td><b>" + tab.toUpperCase() + "</b></td><td>" + tuple + "</td><td>" + data_type + "</td><td>" + constraint + "</td></tr>\n";
    }

    String dict_close_table() {
        return "</table>";
    }

    @Override
    public List<Model> structure_details(int structure) {
        String sql = "select  unit.unit_id,  unit.structure,  unit.name as unit_name,  \n"
                + "                  structure.structure_id,  structure.db_name,  structure.db_user,  structure.db_password,  structure.cash_total,  structure.start_time, \n"
                + "                  structure.delivery_date, \n"
                + "                  structure.pgm_language,  structure.platform,  structure.entry_date,  structure.User \n"
                + "                  from unit \n"
                + "                  join structure on structure.structure_id=unit.structure \n"
                + "				where structure.structure_id=:structure";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("structure", structure);
        List<Model> units = np.query(sql, params, new Query.db_mapper_str());

        return units;
    }

}

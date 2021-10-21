/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.files.db;

import com.codeguru.g_ultimate.dao.Dao_units_impl;
import com.codeguru.g_ultimate.models.Mdl_unit;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 *
 * @author SANGWA
 */
public class Query extends Initials {

    String db_name = "";
    @Autowired
    private ServletContext servletContext;

    public static String structure_q() {
        return "            select      structure.structure_id as  structure, unit.unit_id,   structure.db_name,  structure.db_user,  structure.db_password,  structure.cash_total,  structure.start_time, \n"
                + "                  structure.delivery_date, \n"
                + "                  structure.pgm_language,  structure.platform,  structure.entry_date,  structure.User"
                + "                  ,unit.name as unit_name \n"
                + "                  from unit \n"
                + "                  join structure on structure.structure_id=unit.structure \n"
                + "where structure.structure_id=:structure";
    }

    public static String unit_join_agent_join_tuples2() {
        return "   select  unit.unit_id,  unit.structure,  unit.name,  \n"
                + " structure.structure_id,  structure.db_name,  structure.db_user,  structure.db_password,  structure.cash_total,  structure.start_time, \n"
                + " structure.delivery_date, \n"
                + " structure.pgm_language,  structure.platform,  structure.entry_date,  structure.User \n"
                + " from unit \n"
                + " join structure on structure.structure_id=unit.structure \n"
                + "  \n"
                + " where structure.structure_id=:structure";
    }

    public static String unit_tuples_query() {
        return " select  unit.unit_id,  unit.structure,  unit.name as unit_name,    structure.structure_id,  structure.db_name,  structure.db_user,  structure.db_password,\n"
                + "                   structure.cash_total,  structure.start_time,  structure.delivery_date,  structure.pgm_language,  structure.platform,  structure.entry_date,\n"
                + "                   structure.User, \n"
                + "                   tuple.tuple_id,  tuple.unit,  tuple.name,  tuple.data_type,  tuple.category,  tuple.display_caption,  tuple.today_date \n"
                + "                   from unit  \n"
                + "                   join structure on structure.structure_id=unit.structure\n"
                + "                   join tuple on tuple.unit=unit.unit_id \n"
                + "                   where   unit.unit_id=:unit    and structure.structure_id=:structure";

    }

    /**
     * this itself loops through the structures+units and passes down structure
     * attributes to another method to get tuples
     *
     * @param np this is the name parameter to get access to databse
     * @return this returns a list of Model objects
     */
    public String get_the_tuples(NamedParameterJdbcTemplate np) {
        try {

            List<Model> units = null;
            int c = 0;
            for (int i = 0; i < sturectures.size(); i++) {
                MapSqlParameterSource params = new MapSqlParameterSource();
                params.addValue("structure", sturectures.get(i).getStructure());
                params.addValue("unit", sturectures.get(i).getUnit_id());
                System.out.println("structure: " + sturectures.get(i).getStructure());
                System.out.println("unit: "+ sturectures.get(i).getUnit_name());
                units = np.query(unit_tuples_query(), params, new db_mapper());
                c = 0;
                open_tbl_def = open_table_definition(sturectures.get(i).getUnit_name());

                primary_key = "";
                fields = "";
                field_val = "";
                field_val1 = "";
                make_db(params, np, c);
                String fk_getter = (!"".equals(all_fks)) ? "," + all_fks : "";
                close_tbl_definition = get_close_table_definition();
                code_db += "\n" + open_tbl_def + fields + primary_key + fk_getter + close_tbl_definition;
                db_name = sturectures.get(i).getDb_name();

            }
            delete_db_ifexists();//first delete the db if not exists
            write_to_file();
            all_fks = "";
        } catch (DataAccessException e) {
            System.out.println("No data may be: " + e.toString());
        }
        return code_db;
    }

    private void delete_db_ifexists() {
        File f = new File(servletContext.getRealPath("/") + "/apps/" + db_name + ".sql");
        if (f.exists()) {
            f.delete();
        }
    }

    private void write_to_file() {
        File f = new File(servletContext.getRealPath("/") + "/apps");
        if (f.exists() && f.isDirectory()) {

            f.delete();//delete it and write a new one
            create_file(f);
        } else {
            create_file(f);
        }
    }

    private void create_file(File f) {
        try {
            new File(servletContext.getRealPath("/") + "/apps").mkdir();
            File sql = new File(servletContext.getRealPath("/") + "/apps/" + this.db_name + ".sql");
            sql.createNewFile();
            PrintWriter wr = new PrintWriter(new BufferedWriter(new FileWriter(f + "/" + this.db_name + ".sql")));
            wr.println(code_db);
            wr.close();
        } catch (IOException ex) {
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static class db_mapper implements RowMapper<Model> {

        @Override
        public Model mapRow(ResultSet rs, int i) throws SQLException {
            Model model = new Model();
            model.setStructure(rs.getInt("structure"));
            model.setUnit_id(rs.getInt("unit_id"));
            model.setUnit_name(rs.getString("name"));
            model.setTuple_name(rs.getString("name"));
            model.setDb_name(rs.getString("db_name"));
            model.setDb_user(rs.getString("db_user"));
            model.setPassword(rs.getString("db_password"));
            model.setCash_total(rs.getInt("cash_total"));
            model.setStart_time(rs.getString("start_time"));
            model.setDelivery_date(rs.getString("delivery_date"));
            model.setPgm_language(rs.getString("pgm_language"));
            model.setPlatform(rs.getString("platform"));
            model.setData_type(rs.getString("data_type"));
            model.setCategory(rs.getString("category"));
            model.setDisplay_caption(rs.getString("display_caption"));
            model.setToday_date(rs.getString("today_date"));

            return model;
        }

    }

    public static class db_mapper_str implements RowMapper<Model> {

        @Override
        public Model mapRow(ResultSet rs, int i) throws SQLException {
            Model model = new Model();
            model.setStructure(rs.getInt("structure"));
            model.setUnit_id(rs.getInt("unit_id"));
            model.setUnit_name(rs.getString("unit_name"));
//            model.setTuple_name(rs.getString("name"));
            model.setDb_name(rs.getString("db_name"));
            model.setDb_user(rs.getString("db_user"));
            model.setPassword(rs.getString("db_password"));
            model.setCash_total(rs.getInt("cash_total"));
            model.setStart_time(rs.getString("start_time"));
            model.setDelivery_date(rs.getString("delivery_date"));
            model.setPgm_language(rs.getString("pgm_language"));
            model.setPlatform(rs.getString("platform"));
            model.setPlatform(rs.getString("entry_date"));
            model.setPlatform(rs.getString("User"));

            return model;
        }

    }

    public void make_db(MapSqlParameterSource mp, NamedParameterJdbcTemplate np, int c) {

        List<Model> list = np.query(unit_tuples_query(), mp, new db_mapper());
        for (int j = 0; j < list.size(); j++) {
            if (c != 0) {
                data_type(list, j);
                fields += "," + get_table_schema(list.get(j).getTuple_name(), field_val1);
            } else if (c == 0) {//zero, the first tuple
                fields += get_table_schema(list.get(j).getTuple_name(), " int(11) AUTO_INCREMENT PRIMARY KEY");
            }

            c += 1;
        }

    }

    private void data_type(List<Model> list, int j) {
        switch (list.get(j).getData_type()) {
            case "String":
                field_val1 = "VARCHAR(60)";
                break;
            case "Date":
                field_val1 = "Date";
                break;
            case "Integer":
                field_val1 = "int(11)";
                break;
            default:
                break;
        }
    }

    public String open_table_definition(String unit) {
        return "--\n"
                + "-- Table structure for table `" + unit + "`\n"
                + "--\n"
                + "\n"
                + "CREATE TABLE IF NOT EXISTS `" + unit + "` (\n";
    }

    public String get_table_schema(String tuple, String data_typle) {
        return "`" + tuple + "`     " + data_typle + " \n";

    }

    public String get_close_table_definition() {
        return "  "
                + ") \nENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;\n";

    }
}

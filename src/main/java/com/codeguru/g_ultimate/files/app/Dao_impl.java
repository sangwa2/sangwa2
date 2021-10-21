/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.files.app;

import com.codeguru.g_ultimate.files.db.Model;
import com.codeguru.g_ultimate.files.db.Query;
import static com.codeguru.g_ultimate.files.db.Query.unit_tuples_query;
 
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 *
 * @author SANGWA
 */
public class Dao_impl extends Query {

    Pub_methods pm = new Pub_methods();

    public Dao_impl(NamedParameterJdbcTemplate np, String path, String app) {

        try {
            int c = 0;

            String code = "", field = "", field_name = "";

            String mdl_imports = "", type = "", row_mapper = "", param_model = "", insert_param = "", insert_values = "", update_values = "", select_fields = "";
            String all_code = "";

            for (int i = 0; i < sturectures.size(); i++) {

                unit_name = sturectures.get(i).getUnit_name();

                MapSqlParameterSource params = new MapSqlParameterSource();
                params.addValue("structure", sturectures.get(i).getStructure());
                params.addValue("unit", sturectures.get(i).getUnit_id());

                c = 0;
                row_mapper = "";
                param_model = "";
                insert_param = "";
                insert_values = "";
                update_values = "";
                select_fields = "";

                mdl_imports = mdl_imports(unit_name, app);
                List<Model> list = np.query(unit_tuples_query(), params, new db_mapper());
                for (int j = 0; j < list.size(); j++) {
                    field = list.get(i).getTuple_name();
                    field_name = field;
                    type = list.get(i).getData_type();

                    row_mapper += row_mapper(unit_name, field, type);
                    param_model += param_model(unit_name, field);
                    insert_param += insert_param(c, field);
                    insert_values += insert_values(c, field);
                    update_values += update_values(c, field);
                    select_fields += select_fields(c, unit_name, field);
                    c += 1;
                }
                all_code = "/*\n"
                        + " * Interface implementation of " + pm.get_Capitalized(unit_name) + ".\n"
                        + " * @For Author SANGWA \n sangwa22@gmail.com.\n"
                        + " */\n"
                        + "package com." + Pub_methods.app_folder + ".dao;\n"
                        + "\n"
                        + mdl_imports
                        + "import java.sql.ResultSet;\n"
                        + "import java.sql.SQLException;\n"
                        + "import java.util.List;\n"
                        + "import org.springframework.beans.factory.annotation.Autowired;\n"
                        + "import org.springframework.dao.DataAccessException;\n"
                        + "import org.springframework.jdbc.core.RowMapper;\n"
                        + "import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;\n"
                        + "import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;\n"
                        + "import org.springframework.jdbc.core.namedparam.SqlParameterSource;\n"
                        + "import org.springframework.stereotype.Repository;\n"
                        + "\n"
                        + "/**\n"
                        + " *\n"
                        + " * @author SANGWA\n"
                        + " */\n"
                        + "@Repository\n"
                        + "public class Dao_" + unit_name + "_impl implements Dao_" + unit_name + " {\n"
                        + "\n"
                        + "    NamedParameterJdbcTemplate namedParameterJdbcTemplate;\n"
                        + "\n"
                        + "    @Autowired\n"
                        + "    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {\n"
                        + "        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;\n"
                        + "    }\n"
                        + "\n"
                        + "    @Override\n"
                        + "    public List<Mdl_" + unit_name + "> all_" + unit_name + "s() {\n"
                        + "        String sql = \"SELECT  " + select_fields + "    FROM " + unit_name + "\";\n"
                        + "        List<Mdl_" + unit_name + "> list = namedParameterJdbcTemplate.query(sql, getParameterByModel(null), new " + unit_name + "_mapper());\n"
                        + "        return list;\n"
                        + "    }\n"
                        + "\n"
                        + "    @Override\n"
                        + "    public void add_" + unit_name + "(Mdl_" + unit_name + " " + unit_name + ") {\n"
                        + "        String sql =  \" INSERT INTO " + unit_name + " (" + insert_param + ") VALUES  (" + insert_values + ")\";\n"
                        + "        namedParameterJdbcTemplate.update(sql, getParameterByModel(" + unit_name + "));\n"
                        + "    }\n"
                        + "\n"
                        + "    @Override\n"
                        + "    public void delete_" + unit_name + "(int " + unit_name + ") {\n"
                        + "        String sql = \"DELETE from " + unit_name + " where " + unit_name + "_id=:" + unit_name + "_id\";\n"
                        + "        namedParameterJdbcTemplate.update(sql, getParameterByModel(new Mdl_" + unit_name + "(" + unit_name + ")));\n"
                        + "    }\n"
                        + "\n"
                        + "    @Override\n"
                        + "    public void update_" + unit_name + "(Mdl_" + unit_name + " " + unit_name + ") {\n"
                        + "        String sql = \"UPDATE child SET   " + update_values + "  WHERE " + unit_name + "_id =:" + unit_name + "_id\";\n"
                        + "        namedParameterJdbcTemplate.update(sql, getParameterByModel(" + unit_name + "));\n"
                        + "    }\n"
                        + "\n"
                        + "    @Override\n"
                        + "    public Mdl_" + unit_name + " find_" + unit_name + "By_id(int " + unit_name + ") {\n"
                        + "        String sql = \"SELECT * FROM " + unit_name + " where " + unit_name + "_id=:" + unit_name + "_id\";\n"
                        + "        return namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(new Mdl_" + unit_name + "(" + unit_name + ")), new " + unit_name + "_mapper());\n"
                        + "    }\n"
                        + "\n"
                        + "    @Override\n"
                        + "    public int get_last_" + unit_name + "() {\n"
                        + "       String sql=\"select " + unit_name + "_id from " + unit_name + " order by " + unit_name + "_id desc limit 1\";\n"
                        + "        int n= namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(null), Integer.class);\n"
                        + "        return n;\n"
                        + "    }\n"
                        + "    \n"
                        + "    \n"
                        + "    \n"
                        + "    \n"
                        + "    \n"
                        + "\n"
                        + "    private SqlParameterSource getParameterByModel(Mdl_" + unit_name + " mdl_" + unit_name + ") {\n"
                        + "        MapSqlParameterSource paramsource = new MapSqlParameterSource();\n"
                        + "        if (mdl_" + unit_name + " != null) {\n"
                        + param_model
                        + "        }\n"
                        + "        return paramsource;\n"
                        + "    }\n"
                        + "\n"
                        + "    private static final class " + unit_name + "_mapper implements RowMapper<Mdl_" + unit_name + "> {\n"
                        + "\n"
                        + "        @Override\n"
                        + "        public Mdl_" + unit_name + " mapRow(ResultSet rs, int i) throws SQLException {\n"
                        + "            Mdl_" + unit_name + " mdl_" + unit_name + " = new Mdl_" + unit_name + "();\n"
                        + row_mapper
                        + "\n"
                        + "            return mdl_" + unit_name + ";\n"
                        + "        }\n"
                        + "\n"
                        + "    }\n"
                        + "}\n"
                        + "";

                new File(path + "/Dao_" + unit_name + "_impl.java").createNewFile();
                pm.get_writer(path + "/Dao_" + unit_name + "_impl.java", all_code);
            }

        } catch (IOException e) {
            System.out.println("Error creating the Dao impl: " + e.toString());
        }

    }

    private String mdl_imports(String unit, String app) {
        return "import com." + Pub_methods.app_folder + ".models.Mdl_" + unit + ";\n";
    }

    private String row_mapper(String unit, String tuple, String type) {
        return "            mdl_" + unit + ".set" + pm.get_Capitalized(tuple) + "(rs.get" + pm.get_Capitalized(pm.get_short_int(type)) + "(\"" + tuple + "\"));\n";
    }

    private String param_model(String unit, String tuple) {
        return "            paramsource.addValue(\"" + tuple + "\", mdl_" + unit + ".get" + pm.get_Capitalized(tuple) + "());\n";
    }

    private String insert_param(int c, String tuple) {
        return (c > 0) ? "," + tuple : tuple;
    }

    private String insert_values(int c, String tuple) {
        return (c > 0) ? " ,:" + tuple : (c == 0) ? ":" + tuple : "";
    }

    private String update_values(int c, String tuple) {
        return (c > 1) ? " ,:" + tuple + "_id= : " + tuple + "_id" : tuple + "_id= : " + tuple + "_id";
    }

    private String select_fields(int c, String unit, String tuple) {
        return (c > 0) ? " ," + unit + "." + tuple : unit + "." + tuple;
    }
}

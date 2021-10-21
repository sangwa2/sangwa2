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
public class New_values extends Query {

    Pub_methods pm = new Pub_methods();

    public New_values(NamedParameterJdbcTemplate np, String path, String app) {

        String serv_declarations = "";
        try {
            int c = 0;
            String unit_name = "";
          
            

            String all_code = "";
            String mdl_imports = "", srv_imports = "", field = "", insert_method = "",
                    field_name = "", mdl_values = "", insert_params = "", path_variables = "";
            for (int i = 0; i < sturectures.size(); i++) {
                unit_name =sturectures.get(i).getUnit_name();

                serv_declarations += serv_declarations(unit_name);
                mdl_imports += mdl_imports(unit_name, app);
                srv_imports += srv_imports(unit_name, app);

                MapSqlParameterSource params = new MapSqlParameterSource();
                params.addValue("structure", sturectures.get(i).getStructure());
                params.addValue("unit", sturectures.get(i).getUnit_id());

                mdl_values = "";
                insert_params = "";
                path_variables = "";
                c = 0;
                List<Model> list = np.query(unit_tuples_query(), params, new Query.db_mapper());
                for (int j = 0; j < list.size(); j++) {
                    field = list.get(i).getUnit_name();
                    field_name = field;
                    mdl_values += mdl_values(c, unit_name, field);
                    insert_params += insert_params(c, field);
                    path_variables += path_variables(c, field, list.get(i).getData_type());
                    c += 1;

                }
                mdl_imports += mdl_imports(unit_name, app);
                srv_imports += srv_imports(unit_name, app);
                serv_declarations += serv_declarations(unit_name);

                insert_method += insert_method(path_variables, insert_params, unit_name, mdl_values);
            }

            all_code = "/*\n"
                    + " * New values\n"
                    + " */\n"
                    + "package com." + Pub_methods.app_folder + ".rest;\n"
                    + "\n"
                    + mdl_imports
                    + srv_imports
                    + "import java.text.Format;\n"
                    + "import java.text.SimpleDateFormat;\n"
                    + "import java.util.Date;\n"
                    + "import org.springframework.beans.factory.annotation.Autowired;\n"
                    + "import org.springframework.http.HttpStatus;\n"
                    + "import org.springframework.http.ResponseEntity;\n"
                    + "import org.springframework.util.MimeTypeUtils;\n"
                    + "import org.springframework.web.bind.annotation.PathVariable;\n"
                    + "\n"
                    + "import org.springframework.web.bind.annotation.RequestMapping;\n"
                    + "import org.springframework.web.bind.annotation.RequestMethod;\n"
                    + "import org.springframework.web.bind.annotation.RestController;\n"
                    + "\n"
                    + "/**\n"
                    + " *\n"
                    + " * By author SANGWA\n"
                    + " */\n"
                    + "@RestController\n"
                    + "@RequestMapping(\"api/new_data\")\n"
                    + "public class New_values {\n"
                    + "//<editor-fold defaultstate=\"collapsed\" desc=\"---- Srv declarations -------------\">\n"
                    + "\n"
                    + serv_declarations
                    + "//</editor-fold>\n"
                    + "\n"
                    + "    public String TodayDate() {\n"
                    + "        Date date = new Date();\n"
                    + "        Format formatter = new SimpleDateFormat(\"yyyy-MM-dd\");\n"
                    + "        String d = formatter.format(date);\n"
                    + "        return d;\n"
                    + "    }\n"
                    + "\n"
                    + "\n"
                    + insert_method
                    + ""
                    + "    //<editor-fold defaultstate=\"collapsed\" desc=\"---------------Check point (Verify first if the data are found in the database)-----------------------\">\n"
                    + "\n"
                    + "//</editor-fold>\n"
                    + "}\n"
                    + "";
            new File(path + "/New_values.java").createNewFile();
            pm.get_writer(path + "/New_values.java", all_code);
        } catch (Exception e) {
            System.out.println("Error creating the New values: " + e.toString());
        }
    }

    private String serv_declarations(String unit) {
        return "@Autowired\n"
                + "Srv_" + unit + " srv_" + unit + ";\n";
    }

    private String mdl_imports(String unit, String app) {
        return "import com.codeguru." + Pub_methods.app_folder + ".models.Mdl_" + unit + ";\n";
    }

    private String srv_imports(String unit, String app) {
        return "import com.codeguru." + Pub_methods.app_folder + ".service.Srv_" + unit + ";\n";
    }

    private String mdl_values(int c, String unit, String tuple) {
        return (c != 0) ? "               mdl_" + unit + ".set" + pm.get_Capitalized(tuple) + "(" + tuple + ");\n" : "";
    }

    private String insert_params(int c, String tuple) {
        return (c != 0) ? "/{" + tuple + "}" : "";
    }

    private String path_variables(int c, String tuple, String type) {
        return (c > 0 && c != 1) ? ",@PathVariable(\"" + tuple + "\") " + type + " " + tuple
                : (c == 1) ? "@PathVariable(\"" + tuple + "\") " + type + " " + tuple : "";
    }

    private String insert_method(String path_variables, String insert_params, String unit_name, String mdl_values) {
        return " @RequestMapping(value = \"new_" + unit_name + insert_params + "\", method = RequestMethod.POST, produces = {MimeTypeUtils.TEXT_PLAIN_VALUE})\n"
                + "    public ResponseEntity<Mdl_" + unit_name + "> new_" + unit_name + "(" + path_variables + ") {\n"
                + "        try {\n"
                + "            Mdl_" + unit_name + "  mdl_" + unit_name + " = new Mdl_" + unit_name + "();\n"
                + mdl_values
                + "             srv_" + unit_name + ".add_" + unit_name + "(mdl_" + unit_name + ");"
                + "\n"
                + "            \n"
                + "            ResponseEntity<Mdl_" + unit_name + "> responseEntity = new ResponseEntity<>(mdl_" + unit_name + ", HttpStatus.OK);\n"
                + "            return responseEntity;\n"
                + "        } catch (Exception e) {\n"
                + "            System.out.println(\"Error: \" + e.toString());\n"
                + "            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);\n"
                + "        }\n"
                + "    }\n";
    }
}

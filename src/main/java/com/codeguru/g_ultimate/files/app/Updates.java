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
public class Updates extends Query {

    Pub_methods pm = new Pub_methods();

    public Updates(NamedParameterJdbcTemplate np,  String path, String app) {

        String type = "";
        String field = "";
        String field_name = "";
        String unit = "";
        String unit_name = "";
        String pk_getter = "", pk_setter = "", pk_var;
        String declare = "", methods = "", mdl_values = "", path_variables = "";
        String mdl_imports = "", srv_imports = "", serv_declarations = "",
                insert_params = "", all_code = "", update_method = "";
        try {
            int c = 0;
             
            for (int i = 0; i < sturectures.size(); i++) {
                declare = "";
                methods = "";
                //copy default images
                unit = sturectures.get(i).getUnit_name();
                unit_name =unit;
                // System.out.println("this is the unit id: " + res1.getInt(1) + " data:    " + res1.getString(1));

                MapSqlParameterSource params = new MapSqlParameterSource();
                params.addValue("structure", sturectures.get(i).getStructure());
                params.addValue("unit", sturectures.get(i).getUnit_id());


                declare = "package com.model;\n\n public class  Mdl_" + unit + " {\n";
                c = 0;

                mdl_values = "";
                path_variables = "";
                insert_params = "";
                List<Model> list = np.query(unit_tuples_query(), params, new db_mapper());
                for (int j = 0; j < list.size(); j++) {
                    field = list.get(i).getTuple_name();
                    field_name = field;
                    
                    field_name =list.get(i).getTuple_name();
                    mdl_values += mdl_values(field);
                    path_variables += path_variables(c, field, list.get(i).getData_type());
                    insert_params += insert_params(c,field);
                    c += 1;
                }
                mdl_imports += mdl_imports(unit_name, app);
                srv_imports += srv_imports(unit_name, app);
                serv_declarations += serv_declarations(unit_name);
                update_method += update_method(unit, insert_params, unit_name, path_variables, mdl_values);
            }
            all_code = "/*\n"
                    + " * Updating data.\n"
                    + " */\n"
                    + "package com." + app + ".ajaxrest;\n"
                    + "\n"
                    + mdl_imports
                    + srv_imports
                    + "import org.springframework.beans.factory.annotation.Autowired;\n"
                    + "import org.springframework.http.HttpStatus;\n"
                    + "import org.springframework.http.ResponseEntity;\n"
                    + "import org.springframework.util.MimeTypeUtils;\n"
                    + "import org.springframework.web.bind.annotation.PathVariable;\n"
                    + "import org.springframework.web.bind.annotation.RequestMapping;\n"
                    + "import org.springframework.web.bind.annotation.RequestMethod;\n"
                    + "import org.springframework.web.bind.annotation.RestController;\n"
                    + "\n"
                    + "/**\n"
                    + " *\n"
                    + " * @author SANGWA\n"
                    + " */\n"
                    + "@RestController\n"
                    + "@RequestMapping(\"api/update_data\")\n"
                    + "public class Updates {\n"
                    + "\n"
                    + serv_declarations
                    + "\n"
                    + "    \n"
                    + update_method
                    + "\n"
                    + "}\n"
                    + "";
            new File(path + "/Updates.java").createNewFile();
            pm.get_writer(path + "/Updates.java", all_code);
        } catch (IOException e) {
            System.out.println("Error creating the Updates: " + e.toString());
        }
    }

    private String serv_declarations(String unit) {
        return "@Autowired\n"
                + "Srv_" + unit + " srv_" + unit + ";\n";
    }

    private String mdl_imports(String unit, String app) {
        return "import com.codeguru." + app + ".models.Mdl_" + unit + ";\n";
    }

    private String srv_imports(String unit, String app) {
        return "import com.codeguru." + app + ".service.Srv_" + unit + ";\n";
    }

    private String mdl_values(String tuple) {
        return "        current_mdl.set" + pm.get_Capitalized(tuple) + "(" + tuple + ");\n";
    }

    private String path_variables(int c, String tuple, String type) {
        return (c > 0) ? ", @PathVariable(\"" + tuple + "\") " + pm.get_short_int(type) + " " + tuple
                : "@PathVariable(\"" + tuple + "\") " + pm.get_short_int(type) + " " + tuple;
    }

    private String insert_params(int c, String tuple) {
        return (c > 0) ? "/{" + tuple + "}" : "";
    }

    private String update_method(String unit, String insert_params, String unit_name, String path_variables, String mdl_values) {
        return "\n     @RequestMapping(value = \"update_" + unit_name + insert_params + "/{id}\", method = RequestMethod.POST, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})\n"
                + "    public ResponseEntity<Mdl_" + unit + "> update_" + unit_name + "(" + path_variables + ",int id) {\n"
                + "        Mdl_" + unit + " current_mdl = srv_" + unit_name + ".find" + unit + "By_id(id);\n"
                + "        if (current_mdl == null) {\n"
                + "            return new ResponseEntity<>(HttpStatus.NOT_FOUND);\n"
                + "        }\n"
                + mdl_values
                + "          srv_" + unit + ".update_" + unit + "(current_mdl);"
                + "        try {\n"
                + "            ResponseEntity<Mdl_" + unit_name + "> responseEntity = new ResponseEntity(current_mdl, HttpStatus.OK);\n"
                + "            return responseEntity;\n"
                + "        } catch (Exception e) {\n"
                + "            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);\n"
                + "        }\n"
                + "    }\n\n";
    }
}

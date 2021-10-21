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

import java.util.List;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 *
 * @author SANGWA
 */
public class Models extends Query {

    public Models(NamedParameterJdbcTemplate np, String path) {

        String type = "";
        String field = "";
        String field_name = "";
        String unit = "";
        String unit_name = "";
        String pk_getter = "", pk_setter = "", pk_var;
        String declare = "", methods = "";
        String declared_fields = "";
        Pub_methods pm = new Pub_methods();
        try {
            for (int i = 0; i < sturectures.size(); i++) {

                declare = "";
                methods = "";
                declared_fields = "";
                unit = sturectures.get(i).getUnit_name();
                unit_name = unit;
                declare = "package com." + Pub_methods.app_folder + ".models;\n\n public class  Mdl_" + unit + " {\n";

                MapSqlParameterSource params = new MapSqlParameterSource();
                params.addValue("structure", sturectures.get(i).getStructure());
                params.addValue("unit", sturectures.get(i).getUnit_id());

                List<Model> list = np.query(unit_tuples_query(), params, new db_mapper());
                for (int j = 0; j < list.size(); j++) {
                    field = list.get(i).getTuple_name();
                    field_name = field;
                    type = list.get(i).getData_type();

                    declared_fields += "private " + pm.get_short_int(type) + " " + field + ";\n";

                    methods += " public void set" + pm.get_Capitalized(field) + "(" + pm.get_short_int(type) + " " + field + ") {\n"
                            + "        this." + pm.get_pk(field) + " = " + pm.get_pk(field) + ";\n"
                            + "    }\n"
                            + ""
                            + "public " + pm.get_short_int(type) + " get" + pm.get_Capitalized(pm.get_pk(field)) + "() {\n"
                            + "        return " + pm.get_pk(field) + ";\n"
                            + "    }\n\n";
                }
                declare += declared_fields;
                declare += " public Mdl_" + unit + "() {\n"
                        + "        super();\n"
                        + "    }\n"
                        + "\n"
                        + "    public Mdl_" + unit + "(int " + unit + "_id) {\n"
                        + "        super();\n"
                        + "        this." + unit + "_id = " + unit + "_id;\n"
                        + "    }";
                declare += "  \n";
                declare += methods + "\n\n\n}\n";
                new File(path + "/Mdl_" + unit + ".java").createNewFile();
                pm.get_writer(path + "/Mdl_" + unit + ".java", declare);

            }
        } catch (IOException e) {
            System.out.println("Error creating Models: " + e.toString());
        }

    }

}

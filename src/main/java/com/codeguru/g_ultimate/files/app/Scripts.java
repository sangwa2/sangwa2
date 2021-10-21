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
public class Scripts extends Query  {

    public Scripts( NamedParameterJdbcTemplate np, String path) {

        String type = "";
        String tuple = "";
        String field_name = "";
        String unit_name = "", code = "", fields_init = "", fields_validation = "",
                ajax_fields = "", clear_fields = "";

        Pub_methods pm = new Pub_methods();
        try {
            int c = 0;
            
            for (int i = 0; i < sturectures.size(); i++) {

                //copy default images
                unit_name = sturectures.get(i).getUnit_name();
                 

                fields_init = "";
                fields_validation = "";
                ajax_fields = "";
                clear_fields = "";
                c = 0;
                MapSqlParameterSource params = new MapSqlParameterSource();
                params.addValue("structure", sturectures.get(i).getStructure());
                params.addValue("unit", sturectures.get(i).getUnit_id());
                 List<Model> list = np.query(unit_tuples_query(), params, new db_mapper());
                for (int j = 0; j < list.size(); j++) { 
                    tuple = list.get(i).getTuple_name();
                    field_name = list.get(i).getTuple_name();
                    type = list.get(i).getData_type();

                    fields_init += fields_init(c, tuple);
                    fields_validation += fields_validation(c, tuple);

                    ajax_fields += ajax_fields(c, tuple);
                    clear_fields += clear_fields(tuple);
                    c += 1;

                }

                code += "function save_" + unit_name + "() {\n"
                        + "\n"
                        + fields_init
                        + "\n"
                        + "        if (" + fields_validation + ") {\n"
                        + "            $.ajax({\n"
                        + "                type: 'POST',\n"
                        + "                url: 'api/new_data/new_" + unit_name + "/'+" + ajax_fields + ",\n"
                        + "                success: function (result) {\n"
                        + "\n"
                        + "                }\n"
                        + "            });\n"
                        + clear_fields
                        + "            //Refresh data\n"
                        + "        } else {\n"
                        + "            alert('You have to enter all the fields!! ');\n"
                        + "        }\n"
                        + "}\n";

            }
            new File(path + "/db.js").createNewFile();
            pm.get_writer(path + "/db.js", code);
        } catch (IOException e) {
            System.out.println("Error creating Models: " + e.toString());
        }

    }

    String fields_init(int c, String tuple) {
        return (c != 0) ? "        var " + tuple + " = $('#" + tuple + "').val();\n" : "";
    }

    String fields_validation(int c, String tuple) {
        String op = (c != 1) ? "&&" : "";
        return (c != 0) ? op + " " + tuple + " !== '' " : "";
    }

    String ajax_fields(int c, String tuple) {
        String op = (c != 1) ? "+'/'+" : "";
        return (c != 0) ? op + " " + tuple : "";
    }

    String clear_fields(String tuple) {
        return "            $('#txt_" + tuple + "').val('');\n";
    }

}

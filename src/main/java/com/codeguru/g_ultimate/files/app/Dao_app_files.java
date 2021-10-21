/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.files.app;

import com.codeguru.g_ultimate.files.db.Model;
import com.codeguru.g_ultimate.files.db.Query;
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
public class Dao_app_files extends Query {

    public Dao_app_files(String path, String app) {

        try {
            int c = 0;
            String declare = "";
            Pub_methods pm = new Pub_methods();
            String code = "";

            for (int i = 0; i < sturectures.size(); i++) {//we get the structures loaded sinec this class extends the Query
                unit_name = sturectures.get(i).getUnit_name();
                code = "";
                declare = "";
                declare = "package com." + Pub_methods.app_folder + ".dao;\n"
                        + "\n"
                        + "import com." + Pub_methods.app_folder + ".models.Mdl_" + unit_name + ";\n"
                        + "import java.util.List;\n"
                        + "\n"
                        + "/**\n"
                        + " *\n"
                        + " * @author SANGWA\n"
                        + " */\n"
                        + "public interface Dao_" + unit_name + " {\n";
                code += declare;
                code += "\n"
                        + "    public List<Mdl_" + unit_name + "> all_" + unit_name + "s();\n"
                        + "\n"
                        + "    \n"
                        + "    public void add_" + unit_name + "(Mdl_" + unit_name + " " + unit_name + ");\n"
                        + "\n"
                        + "    public void delete_" + unit_name + "(int " + unit_name + ");\n"
                        + "\n"
                        + "    public void update_" + unit_name + "(Mdl_" + unit_name + " " + unit_name + ");\n"
                        + "\n"
                        + "    \n"
                        + "    public Mdl_" + unit_name + " find_" + unit_name + "By_id(int " + unit_name + ");\n"
                        + "    \n"
                        + "    public int get_last_" + unit_name + "();\n"
                        + "";
                code += "}\n";
                new File(path + "/Dao_" + unit_name + ".java").createNewFile();
                pm.get_writer(path + "/Dao_" + unit_name + ".java", code);
            }
        } catch (IOException e) {
            System.out.println("Error creating the Dao: " + e.toString());
        }
    }

}

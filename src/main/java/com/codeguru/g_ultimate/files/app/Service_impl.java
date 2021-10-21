/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.files.app;

import com.codeguru.g_ultimate.files.db.Query;
 
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author SANGWA
 */
public class Service_impl extends Query {

    Pub_methods pm = new Pub_methods();

    public Service_impl(String path, String app) {

        try {
            int c = 0;
            String unit_name = "";
            
           

            String code = "", field = "", field_name = "";

            String mdl_imports = "", row_mapper = "", param_model = "", insert_param = "", insert_values = "", update_values = "", select_fields = "";
            String all_code = "";
               for (int i = 0; i < sturectures.size(); i++) {//we get the structures loaded sinec this class extends the Query
                unit_name = sturectures.get(i).getUnit_name();

                mdl_imports = mdl_imports(unit_name, app);
                all_code = "/*\n"
                        + " * Implementing the service of " + unit_name + ".\n"
                        + " *  .\n"
                        + " */\n"
                        + "package com." + Pub_methods.app_folder + ".service;\n"
                        + "\n"
                        + "import com." + Pub_methods.app_folder + ".dao.Dao_" + unit_name + ";\n"
                        + "import com." + Pub_methods.app_folder + ".models.Mdl_" + unit_name + ";\n"
                        + "import java.util.List;\n"
                        + "import org.springframework.beans.factory.annotation.Autowired;\n"
                        + "import org.springframework.stereotype.Service;\n"
                        + "\n"
                        + "/**\n"
                        + " *\n"
                        + " * @author SANGWA\n"
                        + " */\n"
                        + "@Service\n"
                        + "public class Srv_" + unit_name + "_impl implements Srv_" + unit_name + " {\n"
                        + "\n"
                        + "    Dao_" + unit_name + " dao_" + unit_name + ";\n"
                        + "\n"
                        + "    @Autowired\n"
                        + "    public void setDao_" + unit_name + "(Dao_" + unit_name + " dao_" + unit_name + ") {\n"
                        + "        this.dao_" + unit_name + " = dao_" + unit_name + ";\n"
                        + "    }\n"
                        + "\n"
                        + "    @Override\n"
                        + "    public List<Mdl_" + unit_name + "> all_" + unit_name + "s() {\n"
                        + "       return dao_" + unit_name + ".all_" + unit_name + "s();\n"
                        + "    }\n"
                        + "\n"
                        + "    @Override\n"
                        + "    public void add_" + unit_name + "(Mdl_" + unit_name + " " + unit_name + ") {\n"
                        + "      dao_" + unit_name + ".add_" + unit_name + "(" + unit_name + ");\n"
                        + "    }\n"
                        + "\n"
                        + "    @Override\n"
                        + "    public void delete_" + unit_name + "(int " + unit_name + ") {\n"
                        + "        dao_" + unit_name + ".delete_" + unit_name + "("+unit_name+");\n"
                        + "    }\n"
                        + "\n"
                        + "    @Override\n"
                        + "    public void update_" + unit_name + "(Mdl_" + unit_name + " " + unit_name + ") {\n"
                        + "       dao_" + unit_name + ".update_"+unit_name+"(" + unit_name + ");\n"
                        + "    }\n"
                        + "\n"
                        + "    @Override\n"
                        + "    public Mdl_" + unit_name + " find_" + unit_name + "By_id(int " + unit_name + ") {\n"
                        + "        return dao_" + unit_name + ".find_" + unit_name + "By_id(" + unit_name + ");\n"
                        + "    }\n"
                        + "\n"
                        + "    @Override\n"
                        + "    public int get_last_" + unit_name + "() {\n"
                        + "        return dao_" + unit_name + ".get_last_" + unit_name + "();\n"
                        + "    }\n"
                        + " \n"
                        + "}\n"
                        + "";

                new File(path + "/Srv_" + unit_name + "_impl.java").createNewFile();
                pm.get_writer(path + "/Srv_" + unit_name + "_impl.java", all_code);
            }

        } catch (Exception e) {
            System.out.println("Error creating the Service impl: " + e.toString());
        }

    }

    private String mdl_imports(String unit, String app) {
        return "import com.codeguru." + app + ".models.Mdl_" + unit + ";\n";
    }

    private String row_mapper(String tuple) {
        return "            mdl_child." + tuple + "(rs.get" + pm.get_short_int(tuple) + "(\"" + tuple + "\"));\n";
    }

    private String param_model(String tuple) {
        return "            paramsource.addValue(\"" + tuple + "\", mdl_child.get" + pm.get_Capitalized(tuple) + "());\n";
    }

    private String insert_param(int c, String tuple) {
        return (c > 1) ? "," + tuple : tuple;
    }

    private String insert_values(int c, String tuple) {
        return (c > 1) ? " ,:" + tuple : ":" + tuple;
    }

    private String update_values(int c, String tuple) {
        return (c > 1) ? " ,:" + tuple + "_id= : " + tuple + "_id" : tuple + "_id= : " + tuple + "_id";
    }

    private String select_fields(int c, String unit, String tuple) {
        return (c > 1) ? " ," + unit + "." + tuple : unit + "." + tuple;
    }

}

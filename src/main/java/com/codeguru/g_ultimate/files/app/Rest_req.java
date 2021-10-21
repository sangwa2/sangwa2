/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.files.app;

 
import com.codeguru.g_ultimate.files.db.Query;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author SANGWA
 */
public class Rest_req extends Query {

    public Rest_req(String path, String app) {

        String declaration_1 = "/*\n"
                + " * This is the api to be exposed, .......\n"
                + " * @author SANGWA\n"
                + " */\n"
                + "package com." + Pub_methods.app_folder + ".ajaxrest;\n"
                + "\n";

        String decl2_open_class = "import java.io.PrintWriter;\n"
                + "import java.util.ArrayList;\n"
                + "import java.util.List;\n"
                + "import javax.servlet.RequestDispatcher;\n"
                + "import javax.servlet.http.HttpServletRequest;\n"
                + "import javax.servlet.http.HttpServletResponse;\n"
                + "import org.springframework.beans.factory.annotation.Autowired;\n"
                + "import org.springframework.http.HttpStatus;\n"
                + "import org.springframework.http.ResponseEntity;\n"
                + "import org.springframework.ui.ModelMap;\n"
                + "import org.springframework.util.MimeTypeUtils;\n"
                + "import org.springframework.web.bind.annotation.PathVariable;\n"
                + "import org.springframework.web.bind.annotation.RequestBody;\n"
                + "import org.springframework.web.bind.annotation.RequestMapping;\n"
                + "import org.springframework.web.bind.annotation.RequestMethod;\n"
                + "import org.springframework.web.bind.annotation.RestController;\n"
                + "\n"
                + "/**\n"
                + " *\n"
                + " * @author SANGWA\n"
                + " */\n"
                + "@RestController\n"
                + "@RequestMapping(\"api/restapi\")\n"
                + "public class Rest_api {\n";

        try {
            int c = 0;
            String unit_name = "";
            String mdl_imports = "", srv_import = "", srv_declare = "", req_all = "", req_by_id = "";
            Pub_methods pm = new Pub_methods();
            String code = "";

             for (int i = 0; i < sturectures.size(); i++) {
                unit_name = sturectures.get(i).getUnit_name();
                mdl_imports += mdl_imports(unit_name, app);
                srv_import += srv_imports(unit_name, app);
                srv_declare += serv_declarations(unit_name);

                req_all += req_all(unit_name);
                req_by_id += req_unit_by_id(unit_name);
            }

            String all_code = declaration_1 + mdl_imports + srv_import + decl2_open_class + srv_declare + req_all + req_by_id;
            all_code += "}\n";
            new File(path + "/Rest_api.java").createNewFile();
            pm.get_writer(path + "/Rest_api.java", all_code);
        } catch (IOException e) {
            System.out.println("Error creating Rest_req: " + e.toString());
        }

    }

    private String mdl_imports(String unit, String app) {
        return "import com.codeguru." + Pub_methods.app_folder + ".models.Mdl_" + unit + ";\n";
    }

    private String srv_imports(String unit, String app) {
        return "import com.codeguru." + Pub_methods.app_folder + ".service.Srv_" + unit + ";\n";
    }

    private String serv_declarations(String unit) {
        return "@Autowired\n"
                + "Srv_" + unit + " srv_" + unit + ";\n";
    }

    private String req_all(String unit) {
        return "\n     @RequestMapping(value = \"all_" + unit + "\", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})\n"
                + "    public ResponseEntity<List<Mdl_" + unit + ">> " + unit + "() {\n"
                + "        try {\n"
                + "            ResponseEntity<List<Mdl_" + unit + ">> responseEntity = new ResponseEntity<>(srv_" + unit + ".all_" + unit + "s(), HttpStatus.OK);\n"
                + "            return responseEntity;\n"
                + "\n"
                + "        } catch (Exception e) {\n"
                + "            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);\n"
                + "        }\n"
                + "    }\n\n";
    }

    private String req_unit_by_id(String unit) {
        return " \n    @RequestMapping(value = \"" + unit + "by_id/{" + unit + "_id}\", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})\n"
                + "    public ResponseEntity<List<Mdl_" + unit + ">> "+unit+"_list(@PathVariable(\"" + unit + "_id\") int " + unit + "_id) {\n"
                + "        try {\n"
                + "            ResponseEntity<List<Mdl_" + unit + ">> responseEntity = new ResponseEntity<>(srv_" + unit + ".find_" + unit + "By_id(" + unit + "_id), HttpStatus.OK);\n"
                + "            return responseEntity;\n"
                + "\n"
                + "        } catch (Exception e) {\n"
                + "            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);\n"
                + "        }\n"
                + "    }\n\n";
    }

}

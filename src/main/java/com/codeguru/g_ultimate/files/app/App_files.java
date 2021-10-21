/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.files.app;

import com.codeguru.g_ultimate.files.db.Model;
import com.codeguru.g_ultimate.files.db.Query;
import java.io.File;
import java.util.List;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 *
 * @author SANGWA
 */
public class App_files extends Query {

    @Autowired
    private ServletContext servletContext;

    public void get_app(NamedParameterJdbcTemplate np) {
        String path = servletContext.getRealPath("/") + "/apps/";
        String app_name = sturectures.get(0).getDb_name();

        if (!"".equals(path)) {

            new File(path + app_name).mkdir();
            new File(path + app_name + "/src").mkdir();
            new File(path + app_name + "/src/main").mkdir();
            new File(path + app_name + "/src/main/java").mkdir();
            new File(path + app_name + "/src/main/java/com").mkdir();
            new File(path + app_name + "/src/main/java/com/codeguru").mkdir();
            String whole_path = app_name + "/src/main/java/com/codeguru";

            //project itself
            new File(path + whole_path + "/controllers").mkdir();
            new File(path + whole_path + "/dao").mkdir();
            new File(path + whole_path + "/service").mkdir();
            new File(path + whole_path + "/rest").mkdir();
            new File(path + whole_path + "/model").mkdir();
            new File(path + whole_path + "/web_scripts").mkdir();
            new File(path + whole_path + "/web_styles").mkdir();
            new File(path + whole_path + "/web_images").mkdir();
            new File(path + whole_path + "/Util").mkdir();
            new File(path + whole_path + "/Front_layouts").mkdir();
            new File(path + whole_path + "/sub_view").mkdir();
            new File(path + whole_path + "/sub_view_core").mkdir();

            //The controller
//            new controllers().getClass(); this is omitted because all the controller are in rest folder after the app is made
            new Models(np, path + whole_path + "/model");
            new Dao_app_files(path + whole_path + "/dao", app_name);
            new Dao_impl(np, path + whole_path + "/dao", app_name);
            new Services(path + whole_path + "/service", app_name);
            new Service_impl(path + whole_path + "/service", app_name);
            new Rest_req(path + whole_path + "/rest", app_name);
            new New_values(np, path + whole_path + "/rest", app_name);
            new Updates(np, path + whole_path + "/rest", app_name);
            new Scripts(np, path + whole_path + "/web_scripts");

        }

    }
}

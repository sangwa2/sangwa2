/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.ajaxrest;

import com.codeguru.g_ultimate.models.*;

import com.codeguru.g_ultimate.service.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.apache.log4j.Logger;

/**
 *
 * @author SANGWA
 */
@RestController
@RequestMapping("api/ajaxrest")
public class Ajaxrest {

    private Logger logger = Logger.getLogger(Ajaxrest.class);
    @Autowired
    Srv_profile srv_profile;
    @Autowired
    Srv_account srv_account;
    @Autowired
    Srv_account_category srv_account_category;
    @Autowired
    Srv_child srv_child;
    @Autowired
    Srv_code_place srv_code_place;
    @Autowired
    Srv_f_end_class_attrib srv_f_end_class_attrib;
    @Autowired
    Srv_f_end_css srv_f_end_css;

    @Autowired
    Srv_f_end_datalist srv_f_end_datalist;

    @Autowired
    Srv_f_end_form srv_f_end_form;
    @Autowired
    Srv_f_end_form_template srv_f_end_form_template;
    @Autowired
    Srv_f_end_html_code srv_f_end_html_code;
    @Autowired
    Srv_f_end_html_code_line srv_f_end_html_code_line;
    @Autowired
    Srv_f_end_js_category srv_f_end_js_category;
    @Autowired
    Srv_f_end_js_code srv_f_end_js_code;
    @Autowired
    Srv_f_end_layout srv_f_end_layout;
    @Autowired
    Srv_f_end_layout_type srv_f_end_layout_type;
    @Autowired
    Srv_f_layout_unit_template srv_f_layout_unit_template;
    @Autowired
    Srv_g_all_tables srv_g_all_tables;
    @Autowired
    Srv_f_end_css_category srv_f_end_css_category;
    @Autowired
    Srv_g_tuple_properties srv_g_tuple_properties;
    @Autowired
    Srv_g_unit_defaults srv_g_unit_defaults;
    @Autowired
    Srv_parent srv_parent;
    @Autowired
    Srv_query srv_query;
    @Autowired
    Srv_query_details srv_query_details;
    @Autowired
    Srv_rel_query srv_rel_query;
    @Autowired
    Srv_rel_query_category srv_rel_query_category;
    @Autowired
    Srv_relationship_count srv_relationship_count;
    @Autowired
    Srv_relationships srv_relationships;
    @Autowired
    Srv_f_end_datalist_template srv_f_end_datalist_template;
    @Autowired
    Srvc_structure srvc_structure;
    @Autowired
    Srv_unit_structure srv_unit_structure;
    @Autowired
    Srv_tuple srv_tuple;
    @Autowired
    Srv_tuple_settings srv_tuple_settings;
    @Autowired
    Srv_unit srv_unit;
    @Autowired
    Srv_g_properties srv_g_properties;
    @Autowired
    Srv_fk_order srv_fk_order;

    @RequestMapping(value = "login/{username}/{password}", method = RequestMethod.POST, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<Mdl_account> get_login(@PathVariable("username") String username, @PathVariable("password") String password) {
        ResponseEntity<Mdl_account> responseEntity = null;
        try {

            System.out.println("username: " + username);
            System.out.println("username: " + password);
            if (srv_account.find_accountBy_usename_pass(username, password) != null) {
                Mdl_account mdl_account = srv_account.find_accountBy_usename_pass(username, password);
                System.out.println("username: " + mdl_account.getUsername());
                responseEntity = new ResponseEntity<>(mdl_account, HttpStatus.OK);
            } else {
                System.out.println("No record");
            }

        } catch (Exception e) {
            //logger.info("This is an info log entry");
            logger.error("Error: "+ e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return responseEntity;

    }

    @RequestMapping(value = "all_structures", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Mdl_structure>> demo4(HttpServletRequest hreq, HttpServletResponse hres) {
        try {
            ResponseEntity<List<Mdl_structure>> responseEntity = new ResponseEntity<>(srvc_structure.list_all_structure(), HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "units_by_structure/{structure}", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Mdl_unit_structure>> test_post(@PathVariable("structure") int structure) {
        try {
            ResponseEntity<List<Mdl_unit_structure>> responseEntity = new ResponseEntity<>(srv_unit_structure.units_by_structure(structure), HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error getting units_by_structure: " + e.toString() + "\n");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "db_exists/{db_exists}", method = RequestMethod.GET, produces = {MimeTypeUtils.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> db_exists(@PathVariable("db_exists") String db_exists) {
        try {
            ResponseEntity<String> responseEntity = new ResponseEntity<>(srvc_structure.db_exists(db_exists), HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error checking if the db exists " + e.toString() + "\n");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "units_by_structure_no_tuples/{structure}", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Mdl_unit_structure>> units_by_structure(@PathVariable("structure") int structure) {
        try {
            ResponseEntity<List<Mdl_unit_structure>> responseEntity = new ResponseEntity<>(srv_unit_structure.units_by_structure_no_tuples(structure), HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error getting units_by_structure: " + e.toString() + "\n");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "units_by_layout/{layout_type}/{structure}", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Mdl_unit>> units_by_layout(@PathVariable("layout_type") int layout_type, @PathVariable("structure") int structure) {
        try {

            ResponseEntity<List<Mdl_unit>> responseEntity = new ResponseEntity<>(srv_unit.units_by_layout(layout_type, structure), HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error getting units_by_structure: " + e.toString() + "\n");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "get_only_tuples_by_unit/{unit_id}", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Mdl_tuple>> get_tuple_by_unit_layout_str(@PathVariable("unit_id") int unit_id) {
        try {
            System.out.println("getting onlytuples by unit");
            ResponseEntity<List<Mdl_tuple>> responseEntity = new ResponseEntity<>(srv_tuple.get_only_tuples_by_unit(unit_id), HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error getting only by tuples: " + e.toString() + "\n");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "tuple_by_unit/{unit}/{structure}", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<ModelMap> tuple_by_unit(@PathVariable("unit") int unit, @PathVariable("structure") int structure) {
        try {
            ModelMap modelMap = new ModelMap();
            modelMap.put("tuples", srv_tuple.tuples_by_unit(unit));
            modelMap.put("units", srv_unit_structure.units_by_structure_no_tuples(structure));
            ResponseEntity<ModelMap> responseEntity = new ResponseEntity<>(modelMap, HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error getting units_by_structure: " + e.toString() + "\n");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "total_units/{structureid}", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<Integer> all_unit_by_structure(@PathVariable("structureid") int structureid) {
        try {
            ResponseEntity<Integer> responseEntity = new ResponseEntity<>(srv_unit.get_all_units_by_structure(structureid), HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error getting units_by_structure: " + e.toString() + "\n");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "unit_first_tuple/{ref_unit}", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<Integer> unit_first_unit(@PathVariable("structureid") int structureid) {
        try {
            ResponseEntity<Integer> responseEntity = new ResponseEntity<>(srv_unit.get_all_units_by_structure(structureid), HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error getting first tuple: " + e.toString() + "\n");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "unit_by_unit_structure/{unit}/{structure}", method = RequestMethod.GET)
    public ResponseEntity<Integer> unit_by_structure_and_structure(@PathVariable("unit") String unit, @PathVariable("structure") int structure) {
        try {
            int t = get_if_unit_exists(unit, structure) ? srv_unit.get_if_unit_bystructure_exists(unit, structure) : 0;
            ResponseEntity<Integer> responseEntity = new ResponseEntity<>(t, HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error cheching if the unit exists: " + e.toString() + "\n");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "tuples_and_units/{unit}/{structure}", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<ModelMap> tuples_units(@PathVariable("unit") int unit, @PathVariable("structure") int structure) {
        try {
            ModelMap modelMap = new ModelMap();
            List<Object> obj = new ArrayList<>();
            modelMap.put("tuples", srv_tuple.tuples_by_unit(unit));
            modelMap.put("units", srv_unit_structure.units_by_structure(structure));
            System.out.println("Unit: " + unit + "  structure: " + structure);

            ResponseEntity<ModelMap> responseEntity = new ResponseEntity<>(modelMap, HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error getting units_by_structure: " + e.toString() + "\n");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    private boolean get_if_unit_exists(String name, int structure) {
        try {
            return srv_unit.get_if_unit_bystructure_exists(name, structure) > 1;
        } catch (Exception e) {
            return false;
        }

    }

    @RequestMapping(value = "get_tables_existing_db/{db}", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Mdl_existing_db_tables>> get_tables_existing_db(@PathVariable("db") String db) {
        try {
            ResponseEntity<List<Mdl_existing_db_tables>> responseEntity = new ResponseEntity<>(srv_unit.existing_db_tabl(db), HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "get_tuples_of_existingdb/{db}/{table}", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<ArrayList<String>> tuples_of_existingdb(@PathVariable("db") String db, @PathVariable("table") String table) {
        try {
            ResponseEntity<ArrayList<String>> responseEntity = new ResponseEntity<>(srv_unit.tuples_of_existingdb(db, table), HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "all_code_place", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Mdl_code_place>> code_place() {
        try {
            ResponseEntity<List<Mdl_code_place>> responseEntity = new ResponseEntity<>(srv_code_place.all_code_places(), HttpStatus.OK);
            return responseEntity;

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "all_f_end_class_attrib", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Mdl_f_end_class_attrib>> f_end_class_attrib() {
        try {
            ResponseEntity<List<Mdl_f_end_class_attrib>> responseEntity = new ResponseEntity<>(srv_f_end_class_attrib.all_f_end_class_attribs(), HttpStatus.OK);
            return responseEntity;

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "all_f_end_css", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Mdl_f_end_css>> f_end_css() {
        try {
            ResponseEntity<List<Mdl_f_end_css>> responseEntity = new ResponseEntity<>(srv_f_end_css.all_f_end_csss(), HttpStatus.OK);
            return responseEntity;

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "all_f_end_css_category", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Mdl_f_end_css_category>> f_end_css_category() {
        try {
            ResponseEntity<List<Mdl_f_end_css_category>> responseEntity = new ResponseEntity<>(srv_f_end_css_category.all_f_end_css_categorys(), HttpStatus.OK);
            return responseEntity;

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "all_f_end_datalist", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Mdl_f_end_datalist>> f_end_datalist() {
        try {
            ResponseEntity<List<Mdl_f_end_datalist>> responseEntity = new ResponseEntity<>(srv_f_end_datalist.all_f_end_datalists(), HttpStatus.OK);
            return responseEntity;

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "all_f_end_datalist_template", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Mdl_f_end_datalist_template>> f_end_datalist_template() {
        try {
            ResponseEntity<List<Mdl_f_end_datalist_template>> responseEntity = new ResponseEntity<>(srv_f_end_datalist_template.all_f_end_datalist_templates(), HttpStatus.OK);
            return responseEntity;

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "all_f_end_form", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Mdl_f_end_form>> f_end_form() {
        try {
            ResponseEntity<List<Mdl_f_end_form>> responseEntity = new ResponseEntity<>(srv_f_end_form.all_f_end_forms(), HttpStatus.OK);
            return responseEntity;

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "all_f_end_form_template", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Mdl_f_end_form_template>> f_end_form_template() {
        try {
            ResponseEntity<List<Mdl_f_end_form_template>> responseEntity = new ResponseEntity<>(srv_f_end_form_template.all_f_end_form_templates(), HttpStatus.OK);
            return responseEntity;

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "all_f_end_html_code", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Mdl_f_end_html_code>> f_end_html_code() {
        try {
            ResponseEntity<List<Mdl_f_end_html_code>> responseEntity = new ResponseEntity<>(srv_f_end_html_code.all_f_end_html_codes(), HttpStatus.OK);
            return responseEntity;

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "all_f_end_html_code_line", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Mdl_f_end_html_code_line>> f_end_html_code_line() {
        try {
            ResponseEntity<List<Mdl_f_end_html_code_line>> responseEntity = new ResponseEntity<>(srv_f_end_html_code_line.all_f_end_html_code_lines(), HttpStatus.OK);
            return responseEntity;

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "all_f_end_js_category", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Mdl_f_end_js_category>> f_end_js_category() {
        try {
            ResponseEntity<List<Mdl_f_end_js_category>> responseEntity = new ResponseEntity<>(srv_f_end_js_category.all_f_end_js_categorys(), HttpStatus.OK);
            return responseEntity;

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "all_f_end_js_code", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Mdl_f_end_js_code>> f_end_js_code() {
        try {
            ResponseEntity<List<Mdl_f_end_js_code>> responseEntity = new ResponseEntity<>(srv_f_end_js_code.all_f_end_js_codes(), HttpStatus.OK);
            return responseEntity;

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "f_end_layout_by_structure/{structure}", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Mdl_f_end_layout_type>> f_end_layout_bystructure(@PathVariable("structure") int structure) {
        try {
            ResponseEntity<List<Mdl_f_end_layout_type>> responseEntity = new ResponseEntity<>(srv_f_end_layout_type.f_end_lay_type_by_structure(structure), HttpStatus.OK);
            return responseEntity;

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "all_f_end_layout_type", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Mdl_f_end_layout_type>> f_end_layout_type() {
        try {
            ResponseEntity<List<Mdl_f_end_layout_type>> responseEntity = new ResponseEntity<>(srv_f_end_layout_type.all_f_end_layout_types(), HttpStatus.OK);
            return responseEntity;

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "all_f_layout_unit_template", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Mdl_f_layout_unit_template>> f_layout_unit_template() {
        try {
            ResponseEntity<List<Mdl_f_layout_unit_template>> responseEntity = new ResponseEntity<>(srv_f_layout_unit_template.all_f_layout_unit_templates(), HttpStatus.OK);
            return responseEntity;

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "all_g_all_tables", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Mdl_g_all_tables>> g_all_tables() {
        try {
            ResponseEntity<List<Mdl_g_all_tables>> responseEntity = new ResponseEntity<>(srv_g_all_tables.all_g_all_tabless(), HttpStatus.OK);
            return responseEntity;

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "all_g_tuple_properties", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Mdl_g_tuple_properties>> g_tuple_properties() {
        try {
            ResponseEntity<List<Mdl_g_tuple_properties>> responseEntity = new ResponseEntity<>(srv_g_tuple_properties.all_g_tuple_propertiess(), HttpStatus.OK);
            return responseEntity;

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "all_g_unit_defaults", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Mdl_g_unit_defaults>> g_unit_defaults() {
        try {
            ResponseEntity<List<Mdl_g_unit_defaults>> responseEntity = new ResponseEntity<>(srv_g_unit_defaults.all_g_unit_defaultss(), HttpStatus.OK);
            return responseEntity;

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "all_parent", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Mdl_parent>> parent() {
        try {
            ResponseEntity<List<Mdl_parent>> responseEntity = new ResponseEntity<>(srv_parent.all_parent(), HttpStatus.OK);
            return responseEntity;

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "all_query", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Mdl_query>> query() {
        try {
            ResponseEntity<List<Mdl_query>> responseEntity = new ResponseEntity<>(srv_query.all_querys(), HttpStatus.OK);
            return responseEntity;

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "all_query_details", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Mdl_query_details>> query_details() {
        try {
            ResponseEntity<List<Mdl_query_details>> responseEntity = new ResponseEntity<>(srv_query_details.all_query_detailss(), HttpStatus.OK);
            return responseEntity;

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "all_rel_query", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Mdl_rel_query>> rel_query() {
        try {
            ResponseEntity<List<Mdl_rel_query>> responseEntity = new ResponseEntity<>(srv_rel_query.all_rel_querys(), HttpStatus.OK);
            return responseEntity;

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "all_rel_query_category", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Mdl_rel_query_category>> rel_query_category() {
        try {
            ResponseEntity<List<Mdl_rel_query_category>> responseEntity = new ResponseEntity<>(srv_rel_query_category.all_rel_query_categorys(), HttpStatus.OK);
            return responseEntity;

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "all_relationship_count", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Mdl_relationship_count>> relationship_count() {
        try {
            ResponseEntity<List<Mdl_relationship_count>> responseEntity = new ResponseEntity<>(srv_relationship_count.list_all_relationship_count(), HttpStatus.OK);
            return responseEntity;

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "all_relationships", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Mdl_relationships>> relationships() {
        try {
            ResponseEntity<List<Mdl_relationships>> responseEntity = new ResponseEntity<>(srv_relationships.list_all_relationships(), HttpStatus.OK);
            return responseEntity;

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "all_structure", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Mdl_structure>> structure() {
        try {
            ResponseEntity<List<Mdl_structure>> responseEntity = new ResponseEntity<>(srvc_structure.list_all_structure(), HttpStatus.OK);
            return responseEntity;

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "all_tuple", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Mdl_tuple>> tuple() {
        try {
            ResponseEntity<List<Mdl_tuple>> responseEntity = new ResponseEntity<>(srv_tuple.all_tuples(), HttpStatus.OK);
            return responseEntity;

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "all_tuple_settings", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Mdl_tuple_settings>> tuple_settings() {
        try {
            ResponseEntity<List<Mdl_tuple_settings>> responseEntity = new ResponseEntity<>(srv_tuple_settings.all_tuple_settingss(), HttpStatus.OK);
            return responseEntity;

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "all_unit", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Mdl_unit>> unit() {
        try {
            ResponseEntity<List<Mdl_unit>> responseEntity = new ResponseEntity<>(srv_unit.list_all_units(), HttpStatus.OK);
            return responseEntity;

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "fk_orderby_layoutid/{layout_id}", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<Mdl_fk_order> fk_orderby_layout_id_list(@PathVariable("layout_id") int layout_id) {
        try {
            ResponseEntity<Mdl_fk_order> responseEntity = null;
            if (srv_fk_order.find_fk_orderBy_layout(layout_id) != null) {
                responseEntity = new ResponseEntity<>(srv_fk_order.find_fk_orderBy_layout(layout_id), HttpStatus.OK);
            } else {
                System.out.println("No data (fk order)");
            }

            return responseEntity;

        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "profileby_id/{profile_id}", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<Mdl_profile> profile_list(@PathVariable("profile_id") int profile_id) {
        try {
            ResponseEntity<Mdl_profile> responseEntity = new ResponseEntity<>(srv_profile.find_profileBy_id(profile_id), HttpStatus.OK);
            return responseEntity;

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "accountby_id/{account_id}", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<Mdl_account> account_list(@PathVariable("account_id") int account_id) {
        try {
            ResponseEntity<Mdl_account> responseEntity = new ResponseEntity<>(srv_account.find_accountBy_id(account_id), HttpStatus.OK);
            return responseEntity;

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "account_categoryby_id/{account_category_id}", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<Mdl_account_category> account_category_list(@PathVariable("account_category_id") int account_category_id) {
        try {
            ResponseEntity<Mdl_account_category> responseEntity = new ResponseEntity<>(srv_account_category.find_account_categoryBy_id(account_category_id), HttpStatus.OK);
            return responseEntity;

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "childby_id/{child_id}", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<Mdl_child> child_list(@PathVariable("child_id") int child_id) {
        try {
            ResponseEntity<Mdl_child> responseEntity = new ResponseEntity<>(srv_child.find_childBy_id(child_id), HttpStatus.OK);
            return responseEntity;

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "code_placeby_id/{code_place_id}", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<Mdl_code_place> code_place_list(@PathVariable("code_place_id") int code_place_id) {
        try {
            ResponseEntity<Mdl_code_place> responseEntity = new ResponseEntity<>(srv_code_place.find_code_placeBy_id(code_place_id), HttpStatus.OK);
            return responseEntity;

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "f_end_class_attribby_id/{f_end_class_attrib_id}", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<Mdl_f_end_class_attrib> f_end_class_attrib_list(@PathVariable("f_end_class_attrib_id") int f_end_class_attrib_id) {
        try {
            ResponseEntity<Mdl_f_end_class_attrib> responseEntity = new ResponseEntity<>(srv_f_end_class_attrib.find_f_end_class_attribBy_id(f_end_class_attrib_id), HttpStatus.OK);
            return responseEntity;

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "f_end_cssby_id/{f_end_css_id}", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<Mdl_f_end_css> f_end_css_list(@PathVariable("f_end_css_id") int f_end_css_id) {
        try {
            ResponseEntity<Mdl_f_end_css> responseEntity = new ResponseEntity<>(srv_f_end_css.find_f_end_cssBy_id(f_end_css_id), HttpStatus.OK);
            return responseEntity;

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "f_end_css_categoryby_id/{f_end_css_category_id}", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<Mdl_f_end_css_category> f_end_css_category_list(@PathVariable("f_end_css_category_id") int f_end_css_category_id) {
        try {
            ResponseEntity<Mdl_f_end_css_category> responseEntity = new ResponseEntity<>(srv_f_end_css_category.find_f_end_css_categoryBy_id(f_end_css_category_id), HttpStatus.OK);
            return responseEntity;

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "f_end_datalistby_id/{f_end_datalist_id}", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<Mdl_f_end_datalist> f_end_datalist_list(@PathVariable("f_end_datalist_id") int f_end_datalist_id) {
        try {
            ResponseEntity<Mdl_f_end_datalist> responseEntity = new ResponseEntity<>(srv_f_end_datalist.find_f_end_datalistBy_id(f_end_datalist_id), HttpStatus.OK);
            return responseEntity;

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "f_end_datalist_templateby_id/{f_end_datalist_template_id}", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<Mdl_f_end_datalist_template> f_end_datalist_template_list(@PathVariable("f_end_datalist_template_id") int f_end_datalist_template_id) {
        try {
            ResponseEntity<Mdl_f_end_datalist_template> responseEntity = new ResponseEntity<>(srv_f_end_datalist_template.find_f_end_datalist_templateBy_id(f_end_datalist_template_id), HttpStatus.OK);
            return responseEntity;

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "f_end_formby_id/{f_end_form_id}", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<Mdl_f_end_form> f_end_form_list(@PathVariable("f_end_form_id") int f_end_form_id) {
        try {
            ResponseEntity<Mdl_f_end_form> responseEntity = new ResponseEntity<>(srv_f_end_form.find_f_end_formBy_id(f_end_form_id), HttpStatus.OK);
            return responseEntity;

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "f_end_form_templateby_id/{f_end_form_template_id}", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<Mdl_f_end_form_template> f_end_form_template_list(@PathVariable("f_end_form_template_id") int f_end_form_template_id) {
        try {
            ResponseEntity<Mdl_f_end_form_template> responseEntity = new ResponseEntity<>(srv_f_end_form_template.find_f_end_form_templateBy_id(f_end_form_template_id), HttpStatus.OK);
            return responseEntity;

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "f_end_html_codeby_id/{f_end_html_code_id}", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<Mdl_f_end_html_code> f_end_html_code_list(@PathVariable("f_end_html_code_id") int f_end_html_code_id) {
        try {
            ResponseEntity<Mdl_f_end_html_code> responseEntity = new ResponseEntity<>(srv_f_end_html_code.find_f_end_html_codeBy_id(f_end_html_code_id), HttpStatus.OK);
            return responseEntity;

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "f_end_html_code_lineby_id/{f_end_html_code_line_id}", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<Mdl_f_end_html_code_line> f_end_html_code_line_list(@PathVariable("f_end_html_code_line_id") int f_end_html_code_line_id) {
        try {
            ResponseEntity<Mdl_f_end_html_code_line> responseEntity = new ResponseEntity<>(srv_f_end_html_code_line.find_f_end_html_code_lineBy_id(f_end_html_code_line_id), HttpStatus.OK);
            return responseEntity;

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "f_end_js_categoryby_id/{f_end_js_category_id}", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<Mdl_f_end_js_category> f_end_js_category_list(@PathVariable("f_end_js_category_id") int f_end_js_category_id) {
        try {
            ResponseEntity<Mdl_f_end_js_category> responseEntity = new ResponseEntity<>(srv_f_end_js_category.find_f_end_js_categoryBy_id(f_end_js_category_id), HttpStatus.OK);
            return responseEntity;

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "f_end_js_codeby_id/{f_end_js_code_id}", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<Mdl_f_end_js_code> f_end_js_code_list(@PathVariable("f_end_js_code_id") int f_end_js_code_id) {
        try {
            ResponseEntity<Mdl_f_end_js_code> responseEntity = new ResponseEntity<>(srv_f_end_js_code.find_f_end_js_codeBy_id(f_end_js_code_id), HttpStatus.OK);
            return responseEntity;

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "f_end_layoutby_id/{f_end_layout_id}", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Mdl_f_end_layout>> f_end_layout_list(@PathVariable("f_end_layout_id") int f_end_layout_id) {
        try {
            return new ResponseEntity<>(srv_f_end_layout.find_f_end_layoutBy_id(f_end_layout_id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "f_end_layout_typeby_id/{f_end_layout_type_id}", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<Mdl_f_end_layout_type> f_end_layout_type_list(@PathVariable("f_end_layout_type_id") int f_end_layout_type_id) {
        try {
            ResponseEntity<Mdl_f_end_layout_type> responseEntity = new ResponseEntity<>(srv_f_end_layout_type.find_f_end_layout_typeBy_id(f_end_layout_type_id), HttpStatus.OK);
            return responseEntity;

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "f_layout_unit_templateby_id/{f_layout_unit_template_id}", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<Mdl_f_layout_unit_template> f_layout_unit_template_list(@PathVariable("f_layout_unit_template_id") int f_layout_unit_template_id) {
        try {
            ResponseEntity<Mdl_f_layout_unit_template> responseEntity = new ResponseEntity<>(srv_f_layout_unit_template.find_f_layout_unit_templateBy_id(f_layout_unit_template_id), HttpStatus.OK);
            return responseEntity;

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "g_all_tablesby_id/{g_all_tables_id}", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<Mdl_g_all_tables> g_all_tables_list(@PathVariable("g_all_tables_id") int g_all_tables_id) {
        try {
            ResponseEntity<Mdl_g_all_tables> responseEntity = new ResponseEntity<>(srv_g_all_tables.find_g_all_tablesBy_id(g_all_tables_id), HttpStatus.OK);
            return responseEntity;

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "g_propretiesby_id/{g_propreties_id}", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<Mdl_g_properties> g_propreties_list(@PathVariable("g_propreties_id") int g_propreties_id) {
        try {
            ResponseEntity<Mdl_g_properties> responseEntity = new ResponseEntity<>(srv_g_properties.find_g_propertiesBy_id(g_propreties_id), HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "g_tuple_propertiesby_id/{g_tuple_properties_id}", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<Mdl_g_tuple_properties> g_tuple_properties_list(@PathVariable("g_tuple_properties_id") int g_tuple_properties_id) {
        try {
            ResponseEntity<Mdl_g_tuple_properties> responseEntity = new ResponseEntity<>(srv_g_tuple_properties.find_g_tuple_propertiesBy_id(g_tuple_properties_id), HttpStatus.OK);
            return responseEntity;

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "g_unit_defaultsby_id/{g_unit_defaults_id}", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<Mdl_g_unit_defaults> g_unit_defaults_list(@PathVariable("g_unit_defaults_id") int g_unit_defaults_id) {
        try {
            ResponseEntity<Mdl_g_unit_defaults> responseEntity = new ResponseEntity<>(srv_g_unit_defaults.find_g_unit_defaultsBy_id(g_unit_defaults_id), HttpStatus.OK);
            return responseEntity;

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}

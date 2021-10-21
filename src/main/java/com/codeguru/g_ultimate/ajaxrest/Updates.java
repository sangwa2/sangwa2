/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.ajaxrest;

import com.codeguru.g_ultimate.models.Mdl_f_end_layout;
import com.codeguru.g_ultimate.models.Mdl_structure;
import com.codeguru.g_ultimate.models.Mdl_tuple;
import com.codeguru.g_ultimate.models.Mdl_unit;
import com.codeguru.g_ultimate.service.Srv_account;
import com.codeguru.g_ultimate.service.Srv_account_category;
import com.codeguru.g_ultimate.service.Srv_code_place;
import com.codeguru.g_ultimate.service.Srv_f_end_class_attrib;
import com.codeguru.g_ultimate.service.Srv_f_end_class_category;
import com.codeguru.g_ultimate.service.Srv_f_end_code_line;
import com.codeguru.g_ultimate.service.Srv_f_end_css;
import com.codeguru.g_ultimate.service.Srv_f_end_data_list;
import com.codeguru.g_ultimate.service.Srv_f_end_data_list_template;
import com.codeguru.g_ultimate.service.Srv_f_end_datalist;
import com.codeguru.g_ultimate.service.Srv_f_end_form;
import com.codeguru.g_ultimate.service.Srv_f_end_form_template;
import com.codeguru.g_ultimate.service.Srv_f_end_html_code;
import com.codeguru.g_ultimate.service.Srv_f_end_js_category;
import com.codeguru.g_ultimate.service.Srv_f_end_js_code;
import com.codeguru.g_ultimate.service.Srv_f_end_layout;
import com.codeguru.g_ultimate.service.Srv_f_end_layout_type;
import com.codeguru.g_ultimate.service.Srv_f_layout_unit_template;
import com.codeguru.g_ultimate.service.Srv_g_all_tables;
import com.codeguru.g_ultimate.service.Srv_g_properties;
import com.codeguru.g_ultimate.service.Srv_g_tuple_properties;
import com.codeguru.g_ultimate.service.Srv_g_unit_defaults;
import com.codeguru.g_ultimate.service.Srv_profile;
import com.codeguru.g_ultimate.service.Srv_query;
import com.codeguru.g_ultimate.service.Srv_query_details;
import com.codeguru.g_ultimate.service.Srv_tuple;
import com.codeguru.g_ultimate.service.Srv_unit;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author SANGWA
 */
@RestController
@RequestMapping("api/update_data")
public class Updates {

    @Autowired
    Srv_unit srv_unit;
    @Autowired
    Srv_tuple srv_tuple;
//-----------------------------
    @Autowired
    Srv_profile srv_profile;
    @Autowired

    Srv_account srv_account;

    @Autowired
    Srv_account_category srv_account_category;

    @Autowired
    Srv_code_place srv_code_place;

    @Autowired
    Srv_f_end_class_attrib srv_f_end_class_attrib;

    @Autowired
    Srv_f_end_css srv_f_end_css;

    @Autowired
    Srv_f_end_class_category srv_f_end_class_category;

    @Autowired
    Srv_f_end_data_list srv_f_end_data_list;

    @Autowired
    Srv_f_end_data_list_template srv_f_end_data_list_template;

    @Autowired
    Srv_f_end_form srv_f_end_form;

    @Autowired
    Srv_f_end_form_template srv_f_end_form_template;

    @Autowired
    Srv_f_end_html_code srv_f_end_html_code;

    @Autowired
    Srv_f_end_code_line srv_f_end_code_line;

    @Autowired
    Srv_f_end_js_category srv_f_end_js_category;

    @Autowired
    Srv_f_end_js_code srv_f_end_js_code;

    @Autowired
    Srv_f_end_layout srv_f_end_layout;

    @Autowired
    Srv_f_end_layout_type srv_f_end_layout_type;

    @Autowired
    Srv_g_all_tables srv_g_all_tables;

    @Autowired
    Srv_g_properties srv_g_properties;

    @Autowired
    Srv_g_tuple_properties srv_g_tuple_properties;

    @Autowired
    Srv_g_unit_defaults srv_g_unit_defaults;

    @Autowired
    Srv_query srv_query;

    @Autowired
    Srv_query_details srv_query_details;

    @Autowired
    Srv_f_end_datalist srv_f_end_datalist;

    @Autowired
    Srv_f_layout_unit_template srv_f_layout_unit_template;

    @RequestMapping(value = "upd_tuple/{name}", method = RequestMethod.POST)
    public ResponseEntity<Mdl_tuple> update_tuple(@PathVariable("name") String name) {
        try {
            Mdl_tuple n_tuple = new Mdl_tuple();
            n_tuple.setName(name);
            srv_tuple.update_tuple(n_tuple);
            ResponseEntity<Mdl_tuple> responseEntity = new ResponseEntity<>(n_tuple, HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "upd_tuple_name/{name}/{id}", method = RequestMethod.POST)
    public ResponseEntity<Mdl_tuple> update_tuple_name(@PathVariable("name") String name, @PathVariable("id") int id) {
        try {
            Mdl_tuple n_tuple = srv_tuple.find_tupleBy_id(id);
            if (n_tuple == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            n_tuple.setTuple_id(id);
            n_tuple.setName(name);
            srv_tuple.update_tuple(n_tuple);
            ResponseEntity<Mdl_tuple> responseEntity = new ResponseEntity<>(n_tuple, HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "upd_tuple_data_type/{data_type}/{id}", method = RequestMethod.POST)
    public ResponseEntity<Mdl_tuple> update_tuple_data_type(@PathVariable("data_type") String data_type, @PathVariable("id") int id) {
        try {
            Mdl_tuple n_tuple = srv_tuple.find_tupleBy_id(id);
            if (n_tuple == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            n_tuple.setTuple_id(id);
            n_tuple.setData_type(data_type);
            srv_tuple.update_tuple(n_tuple);
            ResponseEntity<Mdl_tuple> responseEntity = new ResponseEntity<>(n_tuple, HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "upd_tuple_category/{tuple_type}/{ref_tuple}", method = RequestMethod.POST)
    public ResponseEntity<Mdl_tuple> update_tuple_category(@PathVariable("tuple_type") String tuple_type, @PathVariable("ref_tuple") int ref_tuple) {
        try {
            Mdl_tuple n_tuple = srv_tuple.find_tupleBy_id(ref_tuple);//id
            if (n_tuple == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            n_tuple.setTuple_id(ref_tuple);
            n_tuple.setCategory(tuple_type);//this is the category
            System.out.println("Verify the tuple id: " + tuple_type);
            srv_tuple.update_tuple(n_tuple);
            System.out.println("Updated successfully");
            ResponseEntity<Mdl_tuple> responseEntity = new ResponseEntity<>(n_tuple, HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "upd_display_caption/{display_caption}/{id}", method = RequestMethod.POST)
    public ResponseEntity<Mdl_tuple> update_display_caption(@PathVariable("display_caption") String display_caption, @PathVariable("id") int id) {
        try {
            Mdl_tuple n_tuple = srv_tuple.find_tupleBy_id(id);
            if (n_tuple == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            n_tuple.setTuple_id(id);
            n_tuple.setDisplay_caption(display_caption);
            srv_tuple.update_tuple(n_tuple);
            ResponseEntity<Mdl_tuple> responseEntity = new ResponseEntity<>(n_tuple, HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "update_unit_name/{name}/{id}", method = RequestMethod.POST, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<Mdl_unit> update_unit_name(@PathVariable("name") String name, @PathVariable("id") int id) {
        Mdl_unit current_mdl_unit = srv_unit.findunitBy_id(id);
        if (current_mdl_unit == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        current_mdl_unit.setUnit_id(id);
        current_mdl_unit.setName(name);
        srv_unit.update_unit(current_mdl_unit);
        System.out.println("The unit id: " + id + "The model: " + current_mdl_unit);
        try {
            ResponseEntity<Mdl_unit> responseEntity = new ResponseEntity(current_mdl_unit, HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error getting units_by_structure: " + e.toString() + "\n");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //<editor-fold defaultstate="collapsed" desc="-----------------------more --------------------------------">
    @RequestMapping(value = "update_f_end_layout_nameByid/{layout_id}/{name}", method = RequestMethod.POST, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Mdl_f_end_layout>> update_f_end_layout(@PathVariable("layout_id") int layout_id, @PathVariable("name") String name) {
        try {
            System.out.println("Name before updating dao: " + name + " Looking for layoutid: " + layout_id);
                
            List<Mdl_f_end_layout> current_mdl = srv_f_end_layout.find_f_end_layoutBy_id(layout_id);
             System.out.println("The model size: " + current_mdl.size()+" first item is: "+ current_mdl.get(0).getF_end_layout_id());
            if (current_mdl.size() > 0) {
                System.out.println("The model is: " + current_mdl);
                current_mdl.get(0).setF_end_layout_id(layout_id);
                current_mdl.get(0).setName(name);
                System.out.println("name of the layout is: " + name);
                System.out.println("Saving with the name of: " + name);
                srv_f_end_layout.update_f_end_layout(current_mdl.get(0));
            } else {
                System.out.println("The model was null!! " + current_mdl);
                new ResponseEntity("layout not found", HttpStatus.OK);
            }

            return new ResponseEntity(current_mdl, HttpStatus.OK);

        } catch (Exception e) {
            System.out.println("Another error in: " + Updates.class.getName() + " - " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
//</editor-fold>

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.ajaxrest;

import com.codeguru.g_ultimate.models.Mdl_structure;
import com.codeguru.g_ultimate.models.Mdl_unit;
import com.codeguru.g_ultimate.service.Srv_f_end_layout;
import com.codeguru.g_ultimate.service.Srv_rel_query;
import com.codeguru.g_ultimate.service.Srv_relationship_count;
import com.codeguru.g_ultimate.service.Srv_relationships;
import com.codeguru.g_ultimate.service.Srv_structure_impl;
import com.codeguru.g_ultimate.service.Srv_tuple;
import com.codeguru.g_ultimate.service.Srv_unit;
import com.codeguru.g_ultimate.service.Srvc_structure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author SANGWA
 */
@RestController
@RequestMapping("api/deletions")
public class Deletions {

    @Autowired
    Srv_unit srv_unit;
    @Autowired
    Srv_tuple srv_tuple;
    @Autowired
    Srvc_structure srvc_structure;

    @Autowired
    Srv_relationships srv_relationships;
    @Autowired
    Srv_relationship_count srv_relationship_count;

    @Autowired
    Srv_rel_query srv_rel_query;
    @Autowired
    Srv_f_end_layout srv_f_end_layout;

    @RequestMapping(value = "delete_unit/{unit_id}", method = RequestMethod.POST)
    public ResponseEntity<String> deletefrom_unit(@PathVariable("unit_id") int unit_id) {
        try {
            srv_unit.delete_unit(unit_id);
            srv_relationships.delete_relationships_by_unit(unit_id);
            //WE DONT DELETE THE QUERIES FOR FUTHER REPORT INFORMATION, 
            //---------------------------------------------------------

            ResponseEntity<String> responseEntity = new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "delete_tuple/{tuple_id}", method = RequestMethod.POST)
    public ResponseEntity<String> deletefrom_tuple(@PathVariable("tuple_id") int tuple_id) {
        try {
            srv_tuple.delete_tuple(tuple_id);
            ResponseEntity<String> responseEntity = new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "delete_structure/{structure}", method = RequestMethod.POST)
    public ResponseEntity<String> deletefrom_structure(@PathVariable("structure") int structure) {
        try {
            srvc_structure.delete_structure(structure);
            srv_relationships.delete_relationships_by_structure(structure);
            ResponseEntity<String> responseEntity = new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "delete_query_by_unit_tuple/{unit_id}/{tuple_id}", method = RequestMethod.POST)
    public ResponseEntity<String> deletefrom_rel_query(@PathVariable("unit_id") int unit_id, @PathVariable("tuple_id") int tuple_id) {
        try {
            srv_rel_query.delete_rel_query_by_unit_tuple(unit_id, tuple_id);
            ResponseEntity<String> responseEntity = new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "delete_f_end_layout/{layoutt_id}", method = RequestMethod.POST)
    public ResponseEntity<String> deletefrom_layout(@PathVariable("layoutt_id") int layoutt_id) {
        try {
            srv_f_end_layout.delete_f_end_layout(layoutt_id);
            ResponseEntity<String> responseEntity = new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println(layoutt_id+" - ->> Error in "+Deletions.class.getName()+" - " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.ajaxrest;

import com.codeguru.g_ultimate.models.Mdl_rel_query;
import com.codeguru.g_ultimate.models.Mdl_rel_query_category;
import com.codeguru.g_ultimate.models.Mdl_unit_names;
import com.codeguru.g_ultimate.models.Mdl_unit_names_wpr;
import com.codeguru.g_ultimate.service.Srv_parent;
import com.codeguru.g_ultimate.service.Srv_rel_query;
import com.codeguru.g_ultimate.service.Srv_rel_query_category;
import com.codeguru.g_ultimate.service.Srv_relationship_count;
import com.codeguru.g_ultimate.service.Srv_relationships;
import com.codeguru.g_ultimate.service.Srv_tuple;
import com.codeguru.g_ultimate.service.Srv_unit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author SANGWA
 */
@RestController
@RequestMapping("api")
public class Query_rest {

    @Autowired
    Srv_unit srv_unit;

    @Autowired
    Srv_parent srv_parent;

    @Autowired
    Srv_rel_query srv_rel_query;
    
    @Autowired
    Srv_rel_query_category srv_rel_query_category;
    @Autowired
    Srv_relationship_count srv_relationship_count;

    @Autowired
    Srv_relationships srv_relationships;
    @Autowired
    Srv_tuple srv_tuple;

    HashMap<String, ArrayList> parents = new HashMap<>();
    ArrayList<String> values = new ArrayList<>();
    ArrayList<String> values2 = new ArrayList<>();

    @RequestMapping(value = "query_filter/{unit_id}/{tuple_id}/{structure}", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> unit_names(@RequestBody Mdl_unit_names_wpr mdl_unit_names_wpr, @PathVariable("unit_id") int unit_id, @PathVariable("tuple_id") int tuple_id, @PathVariable("structure") int structure) {
        ResponseEntity<String> responseEntity2 = null;
        List<Mdl_unit_names> mdl_unit_nameses = mdl_unit_names_wpr.getMdl_unit_nameses();
        String names = "";
        System.out.println("We have also got the unitid: " + unit_id);
        int c = 0;
        System.out.println("units size: " + mdl_unit_nameses.size());
        for (Mdl_unit_names mdl_unit_namese : mdl_unit_nameses) {
            String unit_names = mdl_unit_namese.getName();
            System.out.println("also this unit: " + mdl_unit_namese.getName());
            names += (c == 0) ? "\'" + unit_names + "\'" : "," + "\'" + unit_names + "\'";
            c += 1;
        }

        //Check if this is the parent in the relationship_count
        ResponseEntity<Mdl_rel_query> responseEntity = null;
        Mdl_rel_query_category mdl_rel_query_category = new Mdl_rel_query_category();
        Mdl_rel_query mdl_rel_query = new Mdl_rel_query();
        String list = "";
        try {
            int first_tuple = 0, mdl_relationships_parent = 0, mdl_relationship_child = 9, mdl_relationship_parentchild = 0;
            int catid_by_name = 0;//query category name either: parent, child, or parent_child

            first_tuple = srv_tuple.first_tuple_by_unit(unit_id);//this is the id of the unit= primary key
            mdl_relationships_parent = srv_relationships.get_tuples_by_if_parent(first_tuple);
            mdl_relationship_child = srv_relationships.get_tuples_by_if_child(first_tuple);
            mdl_relationship_parentchild = srv_relationships.get_tuples_by_if_parentchild(first_tuple);
            
            if (mdl_relationships_parent > 0) {//if the parent has been found
                catid_by_name = srv_rel_query_category.querycategoryid_by_categoryname("parent");
            } else if (mdl_relationship_child > 0) {//Child
                catid_by_name = srv_rel_query_category.querycategoryid_by_categoryname("child");
            } else if (mdl_relationship_parentchild > 0) {//Parent-child unit
                catid_by_name = srv_rel_query_category.querycategoryid_by_categoryname("parent_child");
            } else {
                catid_by_name = srv_rel_query_category.querycategoryid_by_categoryname("parent");//dpone later but not tested the impact
                System.err.println("none: not parent, not child not parent-child");
            }
            if (catid_by_name > 0) {
                mdl_rel_query.setUnit(unit_id);
                mdl_rel_query.setTuple(tuple_id);
                mdl_rel_query.setRel_query_category(catid_by_name);
                mdl_rel_query.setStructure(structure);
                list = srv_rel_query.add_rel_query(mdl_rel_query, names);
                System.out.println("unit: " + mdl_rel_query.getUnit() + " tuple: " + mdl_rel_query.getTuple() + " structure: " + mdl_rel_query.getStructure());
                //Get the parents
                //list = srv_rel_query.get_parents();
                //  parents.setAttribute("parents", list);
                //Get the children
            }
            responseEntity2 = new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error: on " + Query_rest.class.getName() + " .. : " + e.toString());
            return null;
        }
        return responseEntity2;
    }

}

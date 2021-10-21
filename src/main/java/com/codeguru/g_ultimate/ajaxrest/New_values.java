/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.ajaxrest;

import com.codeguru.g_ultimate.controller.Existing_database_impl;
import com.codeguru.g_ultimate.dao.Dao_rel_query_impl;
import com.codeguru.g_ultimate.models.*;

import com.codeguru.g_ultimate.service.*;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author SANGWA
 */
@RestController
@RequestMapping("api/new_data")
public class New_values extends Existing_database_impl {
    //<editor-fold defaultstate="collapsed" desc="---- Srv declarations -------------">

    @Autowired
    Srv_unit srv_unit;
    @Autowired
    Srvc_structure srvc_structure;

    @Autowired
    Srv_tuple srv_tuple;

    @Autowired
    Srv_relationships srv_relationships;

    @Autowired
    Srv_relationship_count srv_relationship_count;

    @Autowired
    Srv_child srv_child;

    @Autowired
    Srv_parent srv_parent;

    @Autowired
    Srv_unit_structure srv_unit_structure;

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

    @Autowired
    Srv_rel_query_category srv_rel_query_category;

    @Autowired
    Srv_rel_query srv_rel_query;
    @Autowired
    Srv_fk_order srv_fk_order;
    @Autowired
    Srv_relations srv_relations;
//</editor-fold>

    boolean child_parent_swap = false;
    static int type_saved = 0;

    int last_parent = 0, last_child = 0;//these are used in "new_structure" and are initialized in "save_child_parent" 

    @RequestMapping(value = "new_structure/{db_name}/{db_user}/{db_password}/{cash_total}/{start_time}/{delivery_date}/{pgm_language}/{platform}/{entry_date}/{User}", method = RequestMethod.POST)
    public ResponseEntity<Mdl_structure> new_structure(@PathVariable("db_name") String db_name, @PathVariable("db_user") String db_user, @PathVariable("db_password") String db_password, @PathVariable("cash_total") String cash_total, @PathVariable("start_time") String start_time, @PathVariable("delivery_date") String delivery_date, @PathVariable("pgm_language") String pgm_language, @PathVariable("platform") String platform, @PathVariable("entry_date") String entry_date, @PathVariable("User") String User) {
        try {
            int last_profile = 0;
            int last_account = 0;
            int last_acc_category=0;
               Mdl_parent mdl_parent = new Mdl_parent();
            Mdl_child mdl_child = new Mdl_child();
            Mdl_relationships mdl_relationships = new Mdl_relationships();
            Mdl_relations mdl_relations = new Mdl_relations();
            //those above are the fields that will be used in the relationshipinc the account will be in relationship with the profile and the account category

            //--------------------Structure------------------
            Mdl_structure n_structure = new Mdl_structure();
            n_structure.setDb_name(db_name);
            n_structure.setDb_user(db_user);
            n_structure.setDb_password(db_password);
            n_structure.setCash_total(Integer.parseInt(cash_total));
            n_structure.setStart_time(start_time);
            n_structure.setDelivery_date(delivery_date);
            n_structure.setPgm_language(pgm_language);
            n_structure.setPlatform(platform);
            n_structure.setEntry_date(entry_date);
            n_structure.setUser(User);
            srvc_structure.add_structure(n_structure);
            int last_structure = srvc_structure.get_last_structure();

            //Create profile
            //<editor-fold defaultstate="collapsed" desc=" ----------------------------------Profile table-------------">
            Mdl_unit n_unit = new Mdl_unit();
            Mdl_tuple mdl_tuple = new Mdl_tuple();
            n_unit.setStructure(last_structure);
            n_unit.setName("profile");
            n_unit.setNumber_children(0);
            n_unit.setNumber_parent(0);
            srv_unit.add_unit(n_unit);
            last_profile = srv_unit.get_last_unit();
            
            
            //-------------Tuple1 profile-----------------------
            mdl_tuple.setUnit(srv_unit.get_last_unit());
            mdl_tuple.setName("profile" + "_id");
            mdl_tuple.setData_type("Integer");
            mdl_tuple.setCategory("PK");
            mdl_tuple.setDisplay_caption("profile_id");
            mdl_tuple.setToday_date("no");
            mdl_tuple.setCurr_date(TodayDate());
            srv_tuple.add_tuple(mdl_tuple);

            //-------------Tuple2 profile-----------------------
            mdl_tuple.setUnit(srv_unit.get_last_unit());
            mdl_tuple.setName("name");
            mdl_tuple.setData_type("Integer");
            mdl_tuple.setCategory("NORMAL");
            mdl_tuple.setDisplay_caption("Name");
            mdl_tuple.setToday_date("no");
            mdl_tuple.setCurr_date(TodayDate());
            srv_tuple.add_tuple(mdl_tuple);

            //-------------Tuple3 profile-----------------------
            mdl_tuple.setUnit(srv_unit.get_last_unit());
            mdl_tuple.setName("surname");
            mdl_tuple.setData_type("String");
            mdl_tuple.setCategory("NORMAL");
            mdl_tuple.setDisplay_caption("Surname");
            mdl_tuple.setToday_date("no");
            mdl_tuple.setCurr_date(TodayDate());
            srv_tuple.add_tuple(mdl_tuple);

            //-------------Tuple4 profile-----------------------
            mdl_tuple.setUnit(srv_unit.get_last_unit());
            mdl_tuple.setName("date_birth");
            mdl_tuple.setData_type("Date");
            mdl_tuple.setCategory("NORMAL");
            mdl_tuple.setDisplay_caption("Date of birth");
            mdl_tuple.setToday_date("no");
            mdl_tuple.setCurr_date(TodayDate());
            srv_tuple.add_tuple(mdl_tuple);

            //-------------Tuple5 profile-----------------------
            mdl_tuple.setUnit(srv_unit.get_last_unit());
            mdl_tuple.setName("gender");
            mdl_tuple.setData_type("String");
            mdl_tuple.setCategory("NORMAL");
            mdl_tuple.setDisplay_caption("Gender");
            mdl_tuple.setToday_date("no");
            mdl_tuple.setCurr_date(TodayDate());
            srv_tuple.add_tuple(mdl_tuple);

            //-------------Tuple6 profile-----------------------
            mdl_tuple.setUnit(srv_unit.get_last_unit());
            mdl_tuple.setName("tel");
            mdl_tuple.setData_type("String");
            mdl_tuple.setCategory("NORMAL");
            mdl_tuple.setDisplay_caption("Telephone");
            mdl_tuple.setToday_date("no");
            mdl_tuple.setCurr_date(TodayDate());
            srv_tuple.add_tuple(mdl_tuple);

            //-------------Tuple7 profile-----------------------
            mdl_tuple.setUnit(srv_unit.get_last_unit());
            mdl_tuple.setName("email");
            mdl_tuple.setData_type("String");
            mdl_tuple.setCategory("NORMAL");
            mdl_tuple.setDisplay_caption("Email");
            mdl_tuple.setToday_date("no");
            mdl_tuple.setCurr_date(TodayDate());
            srv_tuple.add_tuple(mdl_tuple);
            //-------------Tuple8 profile -----------------------
            mdl_tuple.setUnit(srv_unit.get_last_unit());
            mdl_tuple.setName("residence");
            mdl_tuple.setData_type("String");
            mdl_tuple.setCategory("NORMAL");
            mdl_tuple.setDisplay_caption("residence");
            mdl_tuple.setToday_date("no");
            mdl_tuple.setCurr_date(TodayDate());
            srv_tuple.add_tuple(mdl_tuple);
            

            //-------------Tuple9 profile-----------------------
            mdl_tuple.setUnit(srv_unit.get_last_unit());
            mdl_tuple.setName("image");
            mdl_tuple.setData_type("Integer");
            mdl_tuple.setCategory("FK");
            mdl_tuple.setDisplay_caption("Photo");
            mdl_tuple.setToday_date("no");
            mdl_tuple.setCurr_date(TodayDate());
            srv_tuple.add_tuple(mdl_tuple);
            //================PROFILE====================================PROFILE
            //</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="--------------------------------------Account table--------">
            //Create account
            n_unit.setStructure(last_structure);
            n_unit.setName("account");
            n_unit.setNumber_children(0);
            n_unit.setNumber_parent(0);
            srv_unit.add_unit(n_unit);
            last_account = srv_unit.get_last_unit();
            //-------------Tuple1 ACCOUNT-----------------------
            mdl_tuple.setUnit(srv_unit.get_last_unit());
            
            //<editor-fold defaultstate="collapsed" desc="-relations of profile and acc-">
            mdl_relations.setChild_unit_id(last_account);
            mdl_relations.setParent_unit_id(last_profile);
            mdl_relations.setDisp_type("form");
            srv_relations.add_relations(mdl_relations);
            //</editor-fold>
            
            mdl_tuple.setName("account" + "_id");
            mdl_tuple.setData_type("Integer");
            mdl_tuple.setCategory("PK");
            mdl_tuple.setDisplay_caption("Account_id");
            mdl_tuple.setToday_date("no");
            mdl_tuple.setCurr_date(TodayDate());
            srv_tuple.add_tuple(mdl_tuple);
            //-------------Tuple2 ACCOUNT-----------------------
            mdl_tuple.setUnit(srv_unit.get_last_unit());
            mdl_tuple.setName("username");
            mdl_tuple.setData_type("String");
            mdl_tuple.setCategory("NORMAL");
            mdl_tuple.setDisplay_caption("Username");
            mdl_tuple.setToday_date("no");
            mdl_tuple.setCurr_date(TodayDate());
            srv_tuple.add_tuple(mdl_tuple);
            //-------------Tuple3 ACCOUNT-----------------------
            mdl_tuple.setUnit(srv_unit.get_last_unit());
            mdl_tuple.setName("password");
            mdl_tuple.setData_type("String");
            mdl_tuple.setCategory("NORMAL");
            mdl_tuple.setDisplay_caption("Pasword");
            mdl_tuple.setToday_date("no");
            mdl_tuple.setCurr_date(TodayDate());
            srv_tuple.add_tuple(mdl_tuple);
            //-------------Tuple4 ACCOUNT-----------------------
            mdl_tuple.setUnit(srv_unit.get_last_unit());
            mdl_tuple.setName("account_category");
            mdl_tuple.setData_type("Integer");
            mdl_tuple.setCategory("FK");
            mdl_tuple.setDisplay_caption("Name");
            mdl_tuple.setToday_date("no");
            mdl_tuple.setCurr_date(TodayDate());
            srv_tuple.add_tuple(mdl_tuple);
            
            //-------------Tuple5 ACCOUNT-----------------------
            mdl_tuple.setUnit(srv_unit.get_last_unit());
            mdl_tuple.setName("profile");
            mdl_tuple.setData_type("Integer");
            mdl_tuple.setCategory("FK");
            mdl_tuple.setDisplay_caption("Profile");
            mdl_tuple.setToday_date("no");
            mdl_tuple.setCurr_date(TodayDate());
            srv_tuple.add_tuple(mdl_tuple);

            //</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="--------------------------Account category-----------------------">
            //==============================ACCOUNT CATEGORY=================================
            //Create account category
            n_unit.setStructure(srvc_structure.get_last_structure());
            n_unit.setName("account_category");
            n_unit.setNumber_children(0);
            n_unit.setNumber_parent(0);
            srv_unit.add_unit(n_unit);
            last_acc_category=srv_unit.get_last_unit();
            //<editor-fold defaultstate="collapsed" desc="-relation of acc_category and acc-">
            mdl_relations.setChild_unit_id(last_acc_category);
            mdl_relations.setParent_unit_id(last_account);
            mdl_relations.setDisp_type("combo");
            srv_relations.add_relations(mdl_relations);
            //</editor-fold>
            //================================================================
            //-------------Tuple1 for account category-----------------------
            mdl_tuple.setUnit(srv_unit.get_last_unit());
            mdl_tuple.setName("account_category_id");
            mdl_tuple.setData_type("Integer");
            mdl_tuple.setCategory("PK");
            mdl_tuple.setDisplay_caption("Account_category_id");
            mdl_tuple.setToday_date("no");
            mdl_tuple.setCurr_date(TodayDate());
            srv_tuple.add_tuple(mdl_tuple);

            //-------------Tuple2 for account category-----------------------
            mdl_tuple.setUnit(srv_unit.get_last_unit());
            mdl_tuple.setName("name");
            mdl_tuple.setData_type("String");
            mdl_tuple.setCategory("NORMAL");
            mdl_tuple.setDisplay_caption("Name");
            mdl_tuple.setToday_date("no");
            mdl_tuple.setCurr_date(TodayDate());
            srv_tuple.add_tuple(mdl_tuple);

            //</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="--------------Relationship between account, profile and account category">
            //  This saves in: parent - child - the relationship - relationship_count tables
         
            
            Mdl_relationship_count mdl_relationship_count = new Mdl_relationship_count();
            
            save_child_parent(mdl_parent, last_account, mdl_child, last_profile, mdl_relationships, mdl_relationship_count, mdl_relations);
            save_relationship_count(last_account, last_profile);
            //</editor-fold>

            ResponseEntity<Mdl_structure> responseEntity = new ResponseEntity<>(n_structure, HttpStatus.OK);
            return responseEntity;
        } catch (NumberFormatException e) {
            System.out.println("Error: on new_structure: " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    private void save_child_parent(int ref_unit_var, int unit,String fk_dispType) {//the relationship done when a stircture is created
        //There is relationship
        //----------save parent ----------------------------------------
        Mdl_parent mdl_parent = new Mdl_parent();
        mdl_parent.setUnit_id(ref_unit_var);
        mdl_parent.setRelation_type("on_many");
        srv_parent.add_parent(mdl_parent);

        //----------save child with the last parent---------------------
        Mdl_child mdl_child = new Mdl_child();
        mdl_child.setUnit_id(unit);
        mdl_child.setParent_id(srv_parent.last_parent());
        mdl_child.setRelation_type("one_many");
        srv_child.add_child(mdl_child);

        //----------save the relationship table ------------------------
        Mdl_relationships mdl_relationships = new Mdl_relationships();
        mdl_relationships.setParent_id(ref_unit_var);
        mdl_relationships.setChild_id(srv_child.last_child());
        mdl_relationships.setParent_count(0);
        mdl_relationships.setChild_count(0);
        srv_relationships.add_relationships(mdl_relationships);

        //----------save the relations table ------------------------
        Mdl_relations mdl_relations = new Mdl_relations();
        mdl_relations.setChild_unit_id(unit);
        mdl_relations.setParent_unit_id(ref_unit_var);
        mdl_relations.setDisp_type(fk_dispType);
        srv_relations.add_relations(mdl_relations);
    }

    @RequestMapping(value = "new_unit/{structure}/{name}/{number_children}/{number_parent}", method = RequestMethod.POST)
    public ResponseEntity<Mdl_unit> new_unit(@PathVariable("name") String name, @PathVariable("structure") int structure, @PathVariable("number_children") int number_children, @PathVariable("number_parent") int number_parent) {
        try {
            Mdl_unit n_structure = new Mdl_unit();
            Mdl_tuple mdl_tuple = new Mdl_tuple();
            n_structure.setStructure(structure);
            n_structure.setName(name);
            n_structure.setNumber_children(number_children);
            n_structure.setNumber_parent(number_parent);
            srv_unit.add_unit(n_structure);

            //-------------Tuple-----------------------
            mdl_tuple.setUnit(srv_unit.get_last_unit());
            mdl_tuple.setName(name + "_id");
            mdl_tuple.setData_type("Integer");
            mdl_tuple.setCategory("PK");
            mdl_tuple.setDisplay_caption(name);
            mdl_tuple.setToday_date("no");
            mdl_tuple.setCurr_date(TodayDate());
            srv_tuple.add_tuple(mdl_tuple);

            ResponseEntity<Mdl_unit> responseEntity = new ResponseEntity<>(n_structure, HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public String TodayDate() {
        Date date = new Date();
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        String d = formatter.format(date);
        return d;
    }

    @RequestMapping(value = "new_tuple/{unit}/{name}/{data_type}/{category}/{display_caption}/{today_date}/{curr_date}/{ref_unit_var}/{fk_dispType}/{structure}", method = RequestMethod.POST, produces = {MimeTypeUtils.TEXT_PLAIN_VALUE})
    public ResponseEntity<Mdl_relationship_count> new_tuple(@PathVariable("unit") int unit, @PathVariable("name") String name, @PathVariable("data_type") String data_type, @PathVariable("category") String category, @PathVariable("display_caption") String display_caption, @PathVariable("today_date") String today_date, @PathVariable("curr_date") String curr_date, @PathVariable("ref_unit_var") int ref_unit_var,@PathVariable("fk_dispType")String fk_dispType, @PathVariable("structure") int structure) {
        try {
             
            Mdl_tuple n_tuple = new Mdl_tuple();
            n_tuple.setUnit(unit);
            n_tuple.setName(name);
            n_tuple.setData_type(data_type);
            n_tuple.setCategory(category);
            n_tuple.setDisplay_caption(display_caption);
            n_tuple.setToday_date(today_date);
            n_tuple.setCurr_date(curr_date);
            srv_tuple.add_tuple(n_tuple);
            Mdl_relationship_count mdl_relationship_count = new Mdl_relationship_count();
            int first_tuple = 0;

            if ("FK".equals(category)) {
                //----------save the child and the parent tables acoordingly------------
                save_child_parent(ref_unit_var, unit,fk_dispType);

                //----------save the relationship count ------------------------
                save_relationship_count(unit, ref_unit_var);

            }
            ResponseEntity<Mdl_relationship_count> responseEntity = new ResponseEntity<>(mdl_relationship_count, HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "new_relationship/{ref_unit}/{unit}", method = RequestMethod.POST)
    public ResponseEntity<Mdl_relationships> new_relationship(@PathVariable("ref_unit") int ref_unit, @PathVariable("unit") int unit) {
        try {

            //  This saves in: parent - child - the relationship - relationship_count tables
            Mdl_parent mdl_parent = new Mdl_parent();
            Mdl_child mdl_child = new Mdl_child();
            Mdl_relationships mdl_relationships = new Mdl_relationships();
            Mdl_relations mdl_relations = new Mdl_relations();//This is another table create but it should replace relationships in futre because it is  a duplicate
            Mdl_relationship_count mdl_relationship_count = new Mdl_relationship_count();
            save_child_parent(mdl_parent, unit, mdl_child, ref_unit, mdl_relationships, mdl_relationship_count, mdl_relations);

            ResponseEntity<Mdl_relationships> responseEntity = new ResponseEntity<>(mdl_relationships, HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    private void save_child_parent(Mdl_parent mdl_parent, int unit, Mdl_child mdl_child, int ref_unit, Mdl_relationships mdl_relationships, Mdl_relationship_count mdl_relationship_count, Mdl_relations mdl_relations) {
        //----------------Parent-----------------------
        mdl_parent.setUnit_id(unit);
        mdl_parent.setRelation_type("one_many");
        srv_parent.add_parent(mdl_parent);
        last_parent = srv_parent.last_parent();
        //-----------------Child------------------------
        mdl_child.setUnit_id(ref_unit);
        mdl_child.setParent_id(last_parent);
        mdl_child.setRelation_type("one_many");
        srv_child.add_child(mdl_child);
        last_child = srv_child.last_child();
        //----------------Relationship------------------

        mdl_relationships.setParent_id(last_parent);
        mdl_relationships.setChild_id(last_child);
        mdl_relationships.setParent_count(unit);
        mdl_relationships.setChild_count(ref_unit);
        srv_relationships.add_relationships(mdl_relationships);

        

        //---------------Relationship count------------
//        mdl_relationship_count.setTuple_id(unit);
    }

    @RequestMapping(value = "relationship_count/{ref_unit}/{unit}", method = RequestMethod.POST)
    public ResponseEntity<Mdl_relationships> new_relationship_count(@PathVariable("ref_unit") int ref_unit, @PathVariable("unit") int unit) {
        try {

            Mdl_relationships mdl_relationships = new Mdl_relationships();
            mdl_relationships.setParent_id(unit);
            mdl_relationships.setChild_id(ref_unit);

            Mdl_relationships mdl_search = new Mdl_relationships();
            mdl_search = srv_relationships.find_structurBy_id(unit);
            int parent_count = mdl_search.getParent_count();
            int child_count = mdl_search.getChild_count();

            mdl_relationships.setParent_count(parent_count);
            mdl_relationships.setChild_count(child_count);
            srv_relationships.add_relationships(mdl_relationships);
            ResponseEntity<Mdl_relationships> responseEntity = new ResponseEntity<>(mdl_relationships, HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error on new_relationship_count: " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "first_tuple/{unit}", method = RequestMethod.GET)
    public ResponseEntity<Integer> first_tuple(@PathVariable("unit") int unit) {
        try {
            int t1 = srv_tuple.first_tuple_by_unit(unit);
            System.out.println("The first tuple is: " + t1);
            ResponseEntity<Integer> responseEntity = new ResponseEntity<>(t1, HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    void save_relationship_count(int unit, int ref_unit_var) {
        /* The unit is the child table and the ref_unit_var  is the parent unit
         First the child may have some parent(s) already so we would increment, the same to the parent, 
            it may have the some other existing children soe for this also will  increment the chidren   */
        // save the child
        if (relation_count_has_tuple(srv_tuple.first_tuple_by_unit(unit))) {// if the child is the relationship already
            int tupl = srv_relationship_count.find_tuple_countBy_tuple_id(srv_tuple.first_tuple_by_unit(unit));//this searches for the tuple in the relationship_count table.
            Mdl_relationship_count new_relationship_count = srv_relationship_count.rel_count_by_tuple_id(tupl);
            int usual_prents = new_relationship_count.getNumber_parent();
            new_relationship_count.setNumber_parent(usual_prents + 1);
            srv_relationship_count.update_relationship_count(new_relationship_count);
        } else {//This means that the tuple is not in relationship count.
            Mdl_relationship_count new_relationship_count = new Mdl_relationship_count();
            new_relationship_count.setTuple_id(srv_tuple.first_tuple_by_unit(unit));
            new_relationship_count.setNumber_children(0);
            new_relationship_count.setNumber_parent(1);
            srv_relationship_count.add_relationship_count(new_relationship_count);
        }
        //Save the parent
        if (relation_count_has_tuple(srv_tuple.first_tuple_by_unit(ref_unit_var))) {// if the parent is in relationship already
            int tupl = srv_relationship_count.find_tuple_countBy_tuple_id(srv_tuple.first_tuple_by_unit(ref_unit_var));
            Mdl_relationship_count new_relationship_count = srv_relationship_count.rel_count_by_tuple_id(tupl);
            int usual_children = new_relationship_count.getNumber_children();
            new_relationship_count.setNumber_children(usual_children + 1);
            srv_relationship_count.update_relationship_count(new_relationship_count);
        } else {
            Mdl_relationship_count new_relationship_count = new Mdl_relationship_count();
            new_relationship_count.setTuple_id(srv_tuple.first_tuple_by_unit(ref_unit_var));
            new_relationship_count.setNumber_children(1);
            new_relationship_count.setNumber_parent(0);
            srv_relationship_count.add_relationship_count(new_relationship_count);
        }
    }

    //<editor-fold  desc="-------------------------------------Front end---------------------------------------">
    @RequestMapping(value = "new_profile/{name}/{surname}/{date_birth}/{gender}/{tel}/{email}/{residence}/{image}", method = RequestMethod.POST, produces = {MimeTypeUtils.TEXT_PLAIN_VALUE})
    public ResponseEntity<Mdl_profile> new_profile(@PathVariable("profile_id") int profile_id, @PathVariable("name") String name, @PathVariable("surname") String surname, @PathVariable("date_birth") String date_birth, @PathVariable("gender") String gender, @PathVariable("tel") String tel, @PathVariable("email") String email, @PathVariable("residence") String residence, @PathVariable("image") int image) {
        try {
            Mdl_profile mdl_profile = new Mdl_profile();
            mdl_profile.setName(name);
            mdl_profile.setSurname(surname);
            mdl_profile.setDate_birth(date_birth);
            mdl_profile.setGender(gender);
            mdl_profile.setTel(tel);
            mdl_profile.setEmail(email);
            mdl_profile.setResidence(residence);
            mdl_profile.setImage(image);
            srv_profile.add_profile(mdl_profile);

            ResponseEntity<Mdl_profile> responseEntity = new ResponseEntity<>(mdl_profile, HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "new_fk_order/{layout_id}/{unit}/{tuple}/{disp_type}", method = RequestMethod.POST, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<Mdl_fk_order> new_fk_order(@PathVariable("layout_id") int layout_id, @PathVariable("unit") int unit, @PathVariable("tuple") int tuple, @PathVariable("disp_type") String disp_type) {
        try {
            if (srv_fk_order.find_fk_orderBy_tuple(tuple) == null) {
                Mdl_fk_order mdl_fk_order = new Mdl_fk_order();
                System.out.println("tuple:");
                System.out.println(srv_fk_order.find_fk_orderBy_tuple(tuple));
                mdl_fk_order.setLayout_id(layout_id);
                mdl_fk_order.setUnit(unit);
                mdl_fk_order.setTuple(tuple);
                mdl_fk_order.setDisp_type(disp_type);
                srv_fk_order.add_fk_order(mdl_fk_order);
            } else {
                Mdl_fk_order existing_fkorder = srv_fk_order.find_fk_orderBy_tuple(tuple);
                Mdl_fk_order mdl_fk_order = new Mdl_fk_order();
                System.out.println("initial: " + disp_type);
                String type = "";
                if ("combo".equals(srv_fk_order.find_fk_orderBy_tuple(tuple).getDisp_type())) {
                    type = "form";
                } else {
                    type = "combo";
                }
                mdl_fk_order.setTuple(tuple);
                mdl_fk_order.setUnit(existing_fkorder.getUnit());
                mdl_fk_order.setLayout_id(existing_fkorder.getLayout_id());
                mdl_fk_order.setFk_order_id(srv_fk_order.find_fk_orderBy_tuple(tuple).getFk_order_id());
                mdl_fk_order.setDisp_type(type);
                srv_fk_order.update_fk_order(mdl_fk_order);
                System.out.println("Updated to: " + type + " from: " + srv_fk_order.find_fk_orderBy_tuple(tuple).getDisp_type() + " " + srv_fk_order.find_fk_orderBy_tuple(tuple).getTuple());
            }
            Mdl_fk_order mdl_fk_order = new Mdl_fk_order();
            ResponseEntity<Mdl_fk_order> responseEntity = new ResponseEntity<>(mdl_fk_order, HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "new_account/{name}/{account_category}/{profile}", method = RequestMethod.POST, produces = {MimeTypeUtils.TEXT_PLAIN_VALUE})
    public ResponseEntity<Mdl_account> new_account(@PathVariable("account_id") int account_id, @PathVariable("name") String name, @PathVariable("account_category") int account_category, @PathVariable("profile") int profile) {
        try {
            Mdl_account mdl_account = new Mdl_account();
            mdl_account.setName(name);
            mdl_account.setAccount_category(account_category);
            mdl_account.setProfile(profile);
            srv_account.add_account(mdl_account);

            ResponseEntity<Mdl_account> responseEntity = new ResponseEntity<>(mdl_account, HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "new_account_category/{name}", method = RequestMethod.POST, produces = {MimeTypeUtils.TEXT_PLAIN_VALUE})
    public ResponseEntity<Mdl_account_category> new_account_category(@PathVariable("account_category_id") int account_category_id, @PathVariable("name") String name) {
        try {
            Mdl_account_category mdl_account_category = new Mdl_account_category();
            mdl_account_category.setName(name);
            srv_account_category.add_account_category(mdl_account_category);

            ResponseEntity<Mdl_account_category> responseEntity = new ResponseEntity<>(mdl_account_category, HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "new_code_place/{code_number}/{html_code_line}/{query}", method = RequestMethod.POST, produces = {MimeTypeUtils.TEXT_PLAIN_VALUE})
    public ResponseEntity<Mdl_code_place> new_code_place(@PathVariable("code_place_id") int code_place_id, @PathVariable("code_number") String code_number, @PathVariable("html_code_line") int html_code_line, @PathVariable("query") int query) {
        try {
            Mdl_code_place mdl_code_place = new Mdl_code_place();
            mdl_code_place.setCode_number(code_number);
            mdl_code_place.setHtml_code_line(html_code_line);
            mdl_code_place.setQuery(query);
            srv_code_place.add_code_place(mdl_code_place);

            ResponseEntity<Mdl_code_place> responseEntity = new ResponseEntity<>(mdl_code_place, HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "new_f_end_class_attrib/{attribute}/{value}", method = RequestMethod.POST, produces = {MimeTypeUtils.TEXT_PLAIN_VALUE})
    public ResponseEntity<Mdl_f_end_class_attrib> new_f_end_class_attrib(@PathVariable("f_end_class_attrib_id") int f_end_class_attrib_id, @PathVariable("attribute") String attribute, @PathVariable("value") String value) {
        try {
            Mdl_f_end_class_attrib mdl_f_end_class_attrib = new Mdl_f_end_class_attrib();
            mdl_f_end_class_attrib.setAttribute(attribute);
            mdl_f_end_class_attrib.setValue(value);
            srv_f_end_class_attrib.add_f_end_class_attrib(mdl_f_end_class_attrib);

            ResponseEntity<Mdl_f_end_class_attrib> responseEntity = new ResponseEntity<>(mdl_f_end_class_attrib, HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "new_f_end_css/{name}/{html_code_line}/{css_category}", method = RequestMethod.POST, produces = {MimeTypeUtils.TEXT_PLAIN_VALUE})
    public ResponseEntity<Mdl_f_end_css> new_f_end_css(@PathVariable("f_end_class_id") int f_end_class_id, @PathVariable("name") String name, @PathVariable("html_code_line") int html_code_line, @PathVariable("css_category") String css_category) {
        try {
            Mdl_f_end_css mdl_f_end_css = new Mdl_f_end_css();
            mdl_f_end_css.setName(name);
            mdl_f_end_css.setHtml_code_line(html_code_line);
            mdl_f_end_css.setCss_category(css_category);
            srv_f_end_css.add_f_end_css(mdl_f_end_css);

            ResponseEntity<Mdl_f_end_css> responseEntity = new ResponseEntity<>(mdl_f_end_css, HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "new_f_end_class_category/{name}", method = RequestMethod.POST, produces = {MimeTypeUtils.TEXT_PLAIN_VALUE})
    public ResponseEntity<Mdl_f_end_class_category> new_f_end_class_category(@PathVariable("f_end_class_category_id") int f_end_class_category_id, @PathVariable("name") String name) {
        try {
            Mdl_f_end_class_category mdl_f_end_class_category = new Mdl_f_end_class_category();
            mdl_f_end_class_category.setName(name);
            srv_f_end_class_category.add_f_end_class_category(mdl_f_end_class_category);

            ResponseEntity<Mdl_f_end_class_category> responseEntity = new ResponseEntity<>(mdl_f_end_class_category, HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "new_f_end_data_list/{query}", method = RequestMethod.POST, produces = {MimeTypeUtils.TEXT_PLAIN_VALUE})
    public ResponseEntity<Mdl_f_end_data_list> new_f_end_data_list(@PathVariable("f_end_data_list_id") int f_end_data_list_id, @PathVariable("query") String query) {
        try {
            Mdl_f_end_data_list mdl_f_end_data_list = new Mdl_f_end_data_list();
            mdl_f_end_data_list.setQuery(query);
            srv_f_end_data_list.add_f_end_data_list(mdl_f_end_data_list);

            ResponseEntity<Mdl_f_end_data_list> responseEntity = new ResponseEntity<>(mdl_f_end_data_list, HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "new_f_end_data_list_template/{name}/{description}", method = RequestMethod.POST, produces = {MimeTypeUtils.TEXT_PLAIN_VALUE})
    public ResponseEntity<Mdl_f_end_data_list_template> new_f_end_data_list_template(@PathVariable("f_end_data_list_template_id") int f_end_data_list_template_id, @PathVariable("name") String name, @PathVariable("description") String description) {
        try {
            Mdl_f_end_data_list_template mdl_f_end_data_list_template = new Mdl_f_end_data_list_template();
            mdl_f_end_data_list_template.setName(name);
            mdl_f_end_data_list_template.setDescription(description);
            srv_f_end_data_list_template.add_f_end_data_list_template(mdl_f_end_data_list_template);

            ResponseEntity<Mdl_f_end_data_list_template> responseEntity = new ResponseEntity<>(mdl_f_end_data_list_template, HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "new_f_end_form/{layout}/{unit}/{form_template}", method = RequestMethod.POST, produces = {MimeTypeUtils.TEXT_PLAIN_VALUE})
    public ResponseEntity<Mdl_f_end_form> new_f_end_form(@PathVariable("f_end_form_id") int f_end_form_id, @PathVariable("layout") int layout, @PathVariable("unit") int unit, @PathVariable("form_template") int form_template) {
        try {
            Mdl_f_end_form mdl_f_end_form = new Mdl_f_end_form();
            mdl_f_end_form.setLayout(layout);
            mdl_f_end_form.setUnit(unit);
            mdl_f_end_form.setForm_template(form_template);
            srv_f_end_form.add_f_end_form(mdl_f_end_form);

            ResponseEntity<Mdl_f_end_form> responseEntity = new ResponseEntity<>(mdl_f_end_form, HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "new_f_end_form_template/{name}/{html_code}", method = RequestMethod.POST, produces = {MimeTypeUtils.TEXT_PLAIN_VALUE})
    public ResponseEntity<Mdl_f_end_form_template> new_f_end_form_template(@PathVariable("f_end_form_template_id") int f_end_form_template_id, @PathVariable("name") String name, @PathVariable("html_code") int html_code) {
        try {
            Mdl_f_end_form_template mdl_f_end_form_template = new Mdl_f_end_form_template();
            mdl_f_end_form_template.setName(name);
            mdl_f_end_form_template.setHtml_code(html_code);
            srv_f_end_form_template.add_f_end_form_template(mdl_f_end_form_template);

            ResponseEntity<Mdl_f_end_form_template> responseEntity = new ResponseEntity<>(mdl_f_end_form_template, HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "new_f_end_html_code/{name}", method = RequestMethod.POST, produces = {MimeTypeUtils.TEXT_PLAIN_VALUE})
    public ResponseEntity<Mdl_f_end_html_code> new_f_end_html_code(@PathVariable("f_end_html_code_id") int f_end_html_code_id, @PathVariable("name") String name
    ) {
        try {
            Mdl_f_end_html_code mdl_f_end_html_code = new Mdl_f_end_html_code();
            mdl_f_end_html_code.setName(name);
            srv_f_end_html_code.add_f_end_html_code(mdl_f_end_html_code);

            ResponseEntity<Mdl_f_end_html_code> responseEntity = new ResponseEntity<>(mdl_f_end_html_code, HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "new_f_end_code_line/{name}/{content}/{content_type}/{html_code}", method = RequestMethod.POST, produces = {MimeTypeUtils.TEXT_PLAIN_VALUE})
    public ResponseEntity<Mdl_f_end_code_line> new_f_end_code_line(@PathVariable("f_end_code_line_id") int f_end_code_line_id, @PathVariable("name") String name, @PathVariable("content") String content, @PathVariable("content_type") String content_type, @PathVariable("html_code") int html_code
    ) {
        try {
            Mdl_f_end_code_line mdl_f_end_code_line = new Mdl_f_end_code_line();
            mdl_f_end_code_line.setName(name);
            mdl_f_end_code_line.setContent(content);
            mdl_f_end_code_line.setContent_type(content_type);
            mdl_f_end_code_line.setHtml_code(html_code);
            srv_f_end_code_line.add_f_end_code_line(mdl_f_end_code_line);

            ResponseEntity<Mdl_f_end_code_line> responseEntity = new ResponseEntity<>(mdl_f_end_code_line, HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "new_f_end_js_category/{name}", method = RequestMethod.POST, produces = {MimeTypeUtils.TEXT_PLAIN_VALUE})
    public ResponseEntity<Mdl_f_end_js_category> new_f_end_js_category(@PathVariable("f_end_js_category_id") int f_end_js_category_id, @PathVariable("name") String name) {
        try {
            Mdl_f_end_js_category mdl_f_end_js_category = new Mdl_f_end_js_category();
            mdl_f_end_js_category.setName(name);
            srv_f_end_js_category.add_f_end_js_category(mdl_f_end_js_category);

            ResponseEntity<Mdl_f_end_js_category> responseEntity = new ResponseEntity<>(mdl_f_end_js_category, HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "new_f_end_js_code/{code_line}/{css}/{js_code_category}", method = RequestMethod.POST, produces = {MimeTypeUtils.TEXT_PLAIN_VALUE})
    public ResponseEntity<Mdl_f_end_js_code> new_f_end_js_code(@PathVariable("f_end_js_code_id") int f_end_js_code_id, @PathVariable("code_line") String code_line, @PathVariable("css") int css, @PathVariable("js_code_category") int js_code_category) {
        try {
            Mdl_f_end_js_code mdl_f_end_js_code = new Mdl_f_end_js_code();
            mdl_f_end_js_code.setCode_line(code_line);
            mdl_f_end_js_code.setCss(css);
            mdl_f_end_js_code.setJs_code_category(js_code_category);
            srv_f_end_js_code.add_f_end_js_code(mdl_f_end_js_code);

            ResponseEntity<Mdl_f_end_js_code> responseEntity = new ResponseEntity<>(mdl_f_end_js_code, HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "new_f_end_layout/{name}/{layout_type}/{unit}/{order}/{supplied_index}", method = RequestMethod.POST, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<Mdl_f_end_layout> new_f_end_layout(@PathVariable("name") String name, @PathVariable("layout_type") int layout_type, @PathVariable("unit") int unit, @PathVariable("order") int order, @PathVariable("supplied_index") int supplied_index) {
        try {

            Mdl_f_end_layout mdl_f_end_layout = new Mdl_f_end_layout();
            mdl_f_end_layout.setName(name);
            mdl_f_end_layout.setLayout_type(0);
            mdl_f_end_layout.setUnit(unit);
            mdl_f_end_layout.setOrder(0);
            mdl_f_end_layout.setSupplied_index(0);
            srv_f_end_layout.add_f_end_layout(mdl_f_end_layout);

//            System.out.println("Saved, ...");
//            Mdl_f_layout_unit_template mdl_f_layout_unit_template = new Mdl_f_layout_unit_template();
//            mdl_f_layout_unit_template.setLayout(srv_f_end_layout.get_last_f_end_layout());
//            mdl_f_layout_unit_template.setUnit(unit);
//            mdl_f_layout_unit_template.setForm_template(0);
//            srv_f_layout_unit_template.add_f_layout_unit_template(mdl_f_layout_unit_template);
            ResponseEntity<Mdl_f_end_layout> responseEntity = new ResponseEntity<>(mdl_f_end_layout, HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error inserting a layout: " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "new_f_end_layout_type/{name}", method = RequestMethod.POST, produces = {MimeTypeUtils.TEXT_PLAIN_VALUE})
    public ResponseEntity<Mdl_f_end_layout_type> new_f_end_layout_type(@PathVariable("f_end_layout_type_id") int f_end_layout_type_id, @PathVariable("name") String name) {
        try {
            Mdl_f_end_layout_type mdl_f_end_layout_type = new Mdl_f_end_layout_type();
            mdl_f_end_layout_type.setName(name);
            srv_f_end_layout_type.add_f_end_layout_type(mdl_f_end_layout_type);

            ResponseEntity<Mdl_f_end_layout_type> responseEntity = new ResponseEntity<>(mdl_f_end_layout_type, HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "new_g_properties/{name}/{default_value}", method = RequestMethod.POST, produces = {MimeTypeUtils.TEXT_PLAIN_VALUE})
    public ResponseEntity<Mdl_g_properties> new_g_properties(@PathVariable("g_properties_id") int g_properties_id,
            @PathVariable("name") String name, @PathVariable("default_value") String default_value) {
        try {
            Mdl_g_properties mdl_g_properties = new Mdl_g_properties();
            mdl_g_properties.setName(name);
            mdl_g_properties.setDefault_value(default_value);
            srv_g_properties.add_g_properties(mdl_g_properties);

            ResponseEntity<Mdl_g_properties> responseEntity = new ResponseEntity<>(mdl_g_properties, HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "new_g_tuple_properties", method = RequestMethod.POST, produces = {MimeTypeUtils.TEXT_PLAIN_VALUE})
    public ResponseEntity<Mdl_g_tuple_properties> new_g_tuple_properties(@PathVariable("g_tiple_properties_id") int g_tiple_properties_id) {
        try {
            Mdl_g_tuple_properties mdl_g_tuple_properties = new Mdl_g_tuple_properties();
            srv_g_tuple_properties.add_g_tuple_properties(mdl_g_tuple_properties);

            ResponseEntity<Mdl_g_tuple_properties> responseEntity = new ResponseEntity<>(mdl_g_tuple_properties, HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "new_g_unit_defaults", method = RequestMethod.POST, produces = {MimeTypeUtils.TEXT_PLAIN_VALUE})
    public ResponseEntity<Mdl_g_unit_defaults> new_g_unit_defaults(@PathVariable("g_unit_defaults_id") int g_unit_defaults_id) {
        try {
            Mdl_g_unit_defaults mdl_g_unit_defaults = new Mdl_g_unit_defaults();
            srv_g_unit_defaults.add_g_unit_defaults(mdl_g_unit_defaults);

            ResponseEntity<Mdl_g_unit_defaults> responseEntity = new ResponseEntity<>(mdl_g_unit_defaults, HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "new_query", method = RequestMethod.POST, produces = {MimeTypeUtils.TEXT_PLAIN_VALUE})
    public ResponseEntity<Mdl_query> new_query(@PathVariable("query_id") int query_id) {
        try {
            Mdl_query mdl_query = new Mdl_query();
            srv_query.add_query(mdl_query);

            ResponseEntity<Mdl_query> responseEntity = new ResponseEntity<>(mdl_query, HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "new_query_details", method = RequestMethod.POST, produces = {MimeTypeUtils.TEXT_PLAIN_VALUE})
    public ResponseEntity<Mdl_query_details> new_query_details(@PathVariable("query_details_id") int query_details_id) {
        try {
            Mdl_query_details mdl_query_details = new Mdl_query_details();
            srv_query_details.add_query_details(mdl_query_details);

            ResponseEntity<Mdl_query_details> responseEntity = new ResponseEntity<>(mdl_query_details, HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "new_f_end_datalist/{query}/{data_list_template}/{layout}", method = RequestMethod.POST, produces = {MimeTypeUtils.TEXT_PLAIN_VALUE})
    public ResponseEntity<Mdl_f_end_datalist> new_f_end_datalist(@PathVariable("f_end_datalist_id") int f_end_datalist_id, @PathVariable("query") int query, @PathVariable("data_list_template") int data_list_template, @PathVariable("layout") int layout) {
        try {
            Mdl_f_end_datalist mdl_f_end_datalist = new Mdl_f_end_datalist();
            mdl_f_end_datalist.setQuery(query);
            mdl_f_end_datalist.setData_list_template(data_list_template);
            mdl_f_end_datalist.setLayout(layout);
            srv_f_end_datalist.add_f_end_datalist(mdl_f_end_datalist);

            ResponseEntity<Mdl_f_end_datalist> responseEntity = new ResponseEntity<>(mdl_f_end_datalist, HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "clear_and_prepareNew", method = RequestMethod.POST, produces = {MimeTypeUtils.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> clear_saving() {
        type_saved = 0;
        System.out.println("cleard: " + type_saved);
        ResponseEntity<String> responseEntity = new ResponseEntity<>("Done", HttpStatus.OK);
        return responseEntity;
    }

    @RequestMapping(value = "new_f_layout_unit_template", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> new_f_layout_unit_template (@RequestBody Mdl_layouts_and_type_wrpr mdl_layouts_and_type_wrpr) {
        System.out.println("WE are saving the unit_template: "+ this.getClass().getName());
        List<Mdl_layouts_and_type> mdl_layouts_and_types = mdl_layouts_and_type_wrpr.getMdl_layouts_and_types();
        Mdl_layouts_and_type mdl_layouts_and_type = mdl_layouts_and_types.get(0);
        System.out.println("The layouttype: "+ mdl_layouts_and_type.getName()+" - in: "+ this.getClass().getName());
        try {
            Mdl_f_end_layout_type mdl_f_end_layout_type2 = new Mdl_f_end_layout_type();
            mdl_f_end_layout_type2.setName(mdl_layouts_and_type.getName());
            mdl_f_end_layout_type2.setStructure(mdl_layouts_and_type.getStructure());
            srv_f_end_layout_type.add_f_end_layout_type(mdl_f_end_layout_type2);
            for (Mdl_layouts_and_type mdl_layouts_and_type1 : mdl_layouts_and_types) {
                Mdl_f_layout_unit_template mdl_f_layout_unit_template = new Mdl_f_layout_unit_template();
                Mdl_f_end_layout mdl_f_end_layout2 = new Mdl_f_end_layout();
                mdl_f_end_layout2.setName(mdl_layouts_and_type1.getName());
                mdl_f_end_layout2.setLayout_type(srv_f_end_layout_type.get_last_f_end_layout_type());
                mdl_f_end_layout2.setUnit(mdl_layouts_and_type1.getUnit());
                mdl_f_end_layout2.setOrder(0);
                mdl_f_end_layout2.setSupplied_index(0);
                srv_f_end_layout.add_f_end_layout(mdl_f_end_layout2);
                
                mdl_f_layout_unit_template.setLayout(srv_f_end_layout.get_last_f_end_layout());
                mdl_f_layout_unit_template.setUnit(mdl_layouts_and_type1.getUnit());
                mdl_f_layout_unit_template.setForm_template(0);
                mdl_f_layout_unit_template.setCombo_form("");//as of now
                srv_f_layout_unit_template.add_f_layout_unit_template(mdl_f_layout_unit_template);

            }

            ResponseEntity<String> responseEntity = new ResponseEntity<>("Saved", HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error in: " + New_values.class.getName() + " - " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "all_existing_structures", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<ArrayList<String>> all_existing_structures() {
        try {
            ArrayList<String> dbs = srvc_structure.existing_str();
            ResponseEntity<ArrayList<String>> responseEntity = new ResponseEntity<>(dbs, HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "new_unit_from_exitisting_db/{units}", method = RequestMethod.POST, produces = {MimeTypeUtils.TEXT_PLAIN_VALUE})
    public ResponseEntity<Mdl_f_layout_unit_template> new_unit_from_exitisting_db(@RequestBody List<String> units, @PathVariable("working_db") String working_db) {
        Mdl_f_layout_unit_template mdl_f_layout_unit_template = new Mdl_f_layout_unit_template();
        try {

            ResponseEntity<Mdl_f_layout_unit_template> responseEntity = new ResponseEntity<>(mdl_f_layout_unit_template, HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "get_last_layout/{structure}", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<Integer> get_last_layout(@PathVariable("structure") int structure) {
        try {
            if (some_layouts(structure)) {
                ResponseEntity<Integer> responseEntity = new ResponseEntity<>(srv_f_layout_unit_template.layout_by_structure(structure), HttpStatus.OK);
                return responseEntity;
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "get_last_layout", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<Integer> get_last_layout_only() {
        try {
            if (any_layout()) {
                ResponseEntity<Integer> responseEntity = new ResponseEntity<>(srv_f_end_layout.get_last_f_end_layout(), HttpStatus.OK);
                return responseEntity;
            } else {
                ResponseEntity<Integer> responseEntity = new ResponseEntity<>(0, HttpStatus.OK);
                return responseEntity;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "new_rel_query/{unit}/{tuple}/{rel_query_category}", method = RequestMethod.POST, produces = {MimeTypeUtils.TEXT_PLAIN_VALUE})
    public ResponseEntity<Mdl_rel_query> new_rel_query(@PathVariable("unit") Integer unit, @PathVariable("tuple") Integer tuple, @PathVariable("rel_query_category") Integer rel_query_category) {
        try {
            Mdl_rel_query mdl_rel_query = new Mdl_rel_query();
            mdl_rel_query.setUnit(unit);
            mdl_rel_query.setTuple(tuple);
            mdl_rel_query.setRel_query_category(rel_query_category);
            srv_rel_query.add_rel_query(mdl_rel_query,"");

            ResponseEntity<Mdl_rel_query> responseEntity = new ResponseEntity<>(mdl_rel_query, HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "new_rel_query_category/{name}", method = RequestMethod.POST, produces = {MimeTypeUtils.TEXT_PLAIN_VALUE})
    public ResponseEntity<Mdl_rel_query_category> new_rel_query_category(@PathVariable("name") String name) {
        try {
            Mdl_rel_query_category mdl_rel_query_category = new Mdl_rel_query_category();
            mdl_rel_query_category.setName(name);
            srv_rel_query_category.add_rel_query_category(mdl_rel_query_category);

            ResponseEntity<Mdl_rel_query_category> responseEntity = new ResponseEntity<>(mdl_rel_query_category, HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "new_tuples_existing_db/{working_db}/{structure}", method = RequestMethod.POST, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<ArrayList<String>> new_tuples_existing_db(@PathVariable("working_db") String working_db,
            @PathVariable("structure") int structure, @RequestBody List<String> units) {
        try {

            ResponseEntity<ArrayList<String>> responseEntity = new ResponseEntity<>(srv_unit.add_existing_units(working_db, structure, units), HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "new_relationship_update/{unit}/{ref_unit}/{fk_dispType}", method = RequestMethod.POST, produces = {MimeTypeUtils.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> new_relationship_update(@PathVariable("unit") int unit, @PathVariable("ref_unit") int ref_unit, @PathVariable("fk_dispType")String fk_dispType) {
        try {
            this.save_child_parent(ref_unit, unit,fk_dispType);
            this.save_relationship_count(unit, ref_unit);

            ResponseEntity<String> done = new ResponseEntity("saved successfully! ", HttpStatus.OK);
            return done;
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="---------------Check point (Verify first if the data are found in the database)-----------------------">
    boolean relation_count_has_tuple(int tuple  ) {
        int tupl = 0;
        try {
            tupl = srv_relationship_count.find_tuple_countBy_tuple_id(tuple);
            return tupl > 0;
        } catch (Exception e) {
            return tupl > 0;
        }

    }

    boolean first_tuple_of_unit_exists(int unit ) {
        try {
            int found = srv_tuple.first_tuple_by_unit(unit);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    boolean some_layouts(int structure  ) {
        int last = 0;
        try {
            last = srv_f_layout_unit_template.layout_by_structure(structure);
            System.out.println("last: " + last);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    boolean any_layout() {
        int id = 0;
        try {
            id = srv_f_end_layout.get_last_f_end_layout();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    //</editor-fold>
}

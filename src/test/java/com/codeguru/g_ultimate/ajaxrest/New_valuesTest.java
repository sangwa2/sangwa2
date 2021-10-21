/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.ajaxrest;

import com.codeguru.g_ultimate.models.Mdl_account;
import com.codeguru.g_ultimate.models.Mdl_account_category;
import com.codeguru.g_ultimate.models.Mdl_code_place;
import com.codeguru.g_ultimate.models.Mdl_f_end_class_attrib;
import com.codeguru.g_ultimate.models.Mdl_f_end_class_category;
import com.codeguru.g_ultimate.models.Mdl_f_end_code_line;
import com.codeguru.g_ultimate.models.Mdl_f_end_css;
import com.codeguru.g_ultimate.models.Mdl_f_end_data_list;
import com.codeguru.g_ultimate.models.Mdl_f_end_data_list_template;
import com.codeguru.g_ultimate.models.Mdl_f_end_datalist;
import com.codeguru.g_ultimate.models.Mdl_f_end_form;
import com.codeguru.g_ultimate.models.Mdl_f_end_form_template;
import com.codeguru.g_ultimate.models.Mdl_f_end_html_code;
import com.codeguru.g_ultimate.models.Mdl_f_end_js_category;
import com.codeguru.g_ultimate.models.Mdl_f_end_js_code;
import com.codeguru.g_ultimate.models.Mdl_f_end_layout;
import com.codeguru.g_ultimate.models.Mdl_f_end_layout_type;
import com.codeguru.g_ultimate.models.Mdl_f_layout_unit_template;
import com.codeguru.g_ultimate.models.Mdl_fk_order;
import com.codeguru.g_ultimate.models.Mdl_g_properties;
import com.codeguru.g_ultimate.models.Mdl_g_tuple_properties;
import com.codeguru.g_ultimate.models.Mdl_g_unit_defaults;
import com.codeguru.g_ultimate.models.Mdl_layouts_and_type_wrpr;
import com.codeguru.g_ultimate.models.Mdl_profile;
import com.codeguru.g_ultimate.models.Mdl_query;
import com.codeguru.g_ultimate.models.Mdl_query_details;
import com.codeguru.g_ultimate.models.Mdl_rel_query;
import com.codeguru.g_ultimate.models.Mdl_rel_query_category;
import com.codeguru.g_ultimate.models.Mdl_relationship_count;
import com.codeguru.g_ultimate.models.Mdl_relationships;
import com.codeguru.g_ultimate.models.Mdl_structure;
import com.codeguru.g_ultimate.models.Mdl_unit;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author SANGWA
 */
public class New_valuesTest {
     
    @Test
    public void testNew_tuple() {
        System.out.println("new_tuple");
        int unit = 905;
        String name = "name";
        String data_type = "String";
        String category = "NORMAL";
        String display_caption = "Name";
        String today_date = "2021-07-29";
        String curr_date = "2021-07-29";
        int ref_unit_var = 0;
        String fk_dispType = "";
        int structure = 142;
        New_values instance = new New_values();
        ResponseEntity<Mdl_relationship_count> expResult = null;
        ResponseEntity<Mdl_relationship_count> result = instance.new_tuple(unit, name, data_type, category, display_caption, today_date, curr_date, ref_unit_var, fk_dispType, structure);
        assertEquals(expResult, result);
       
    } 
    
}

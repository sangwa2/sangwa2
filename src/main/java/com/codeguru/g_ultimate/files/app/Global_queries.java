/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.files.app;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SANGWA
 */
public class Global_queries {
//for giga_java

    public static String unit_join_agent_join_tuples(int structure) {
        return "select  unit.unit_id,  unit.structure,  unit.name,  unit.number_children,  unit.number_parent, structure.structure_id,  structure.db_name,  structure.db_user,  structure.db_password,  structure.cash_total,  structure.start_time,  structure.delivery_date,  structure.pgm_language,  structure.platform,  structure.entry_date,  structure.User from unit join structure on structure.structure_id=unit.structure join tuple on tuple.unit=unit.unit_id where structure.structure_id='" + structure + "'";
    }
    //for giga_java

    public static String tuple_join_tuplecategories(int unit, int structure) {
        return "select  unit.unit_id,  unit.structure,  unit.name,  unit.number_children,  unit.number_parent,  structure.structure_id,  structure.db_name,  structure.db_user,  structure.db_password,  structure.cash_total,  structure.start_time,  structure.delivery_date,  structure.pgm_language,  structure.platform,  structure.entry_date,  structure.User,  tuple.tuple_id,  tuple.unit,  tuple.name,  tuple.data_type,  tuple.category,  tuple.display_caption,  tuple.today_date,  tuple.current_date from unit  join structure on structure.structureid=unit.structure join tuple on tuple.unit=unit.unit_id  where   unit.unit_id= " + unit + " and  tuple.category<>'fk' and structure.structureid=" + structure + "";
    }

    //For gigaflex
    public static String unit_join_agent_join_tuples2(int structure) {
        return "select  unit.unit_id,  unit.structure,  unit.name,  \n"
                + " structure.structure_id,  structure.db_name,  structure.db_user,  structure.db_password,  structure.cash_total,  structure.start_time, \n"
                + " structure.delivery_date, \n"
                + " structure.pgm_language,  structure.platform,  structure.entry_date,  structure.User \n"
                + " from unit \n"
                + " join structure on structure.structure_id=unit.structure \n"
                + "  \n"
                + " where structure.structure_id=" + structure;
    }

    //For gigaflex
    public static String tuple_join_tuplecategories2(int unit, int structure) {
        return    "     select  unit.unit_id,  unit.structure,  unit.name,    structure.structure_id,  structure.db_name,  structure.db_user,  structure.db_password,\n"
                + "  structure.cash_total,  structure.start_time,  structure.delivery_date,  structure.pgm_language,  structure.platform,  structure.entry_date,\n"
                + "  structure.User, "
                + " tuple.tuple_id,  tuple.unit,  tuple.name as tuple_name,  tuple.data_type,  tuple.category,  tuple.display_caption,  tuple.today_date "
                + "  from unit  \n"
                + "  join structure on structure.structure_id=unit.structure\n"
                + "  join tuple on tuple.unit=unit.unit_id \n"
                + "  where   unit.unit_id=" + unit + "    and structure.structure_id=" + structure;
    }
    public static String unit_join_tuples = "";

    
    
    
    
    
    
    
    
    
}

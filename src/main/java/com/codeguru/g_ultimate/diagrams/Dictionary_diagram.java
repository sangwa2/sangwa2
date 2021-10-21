/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.diagrams;

import org.springframework.stereotype.Controller;

/**
 *
 * @author SANGWA
 */
@Controller
public class Dictionary_diagram {
    
     
     String dict_open_table_header() {
        return "<table>\n<tr>";
    }

    String dict_header_tuples() {
        return "\t<td>TABLE</td><td>COLUMN</td><td>DATA TYPE</td><td>CONSTRAINT</td>\n";
    }

    String dict_close_table_header() {
        return "</tr>\n";
    }

    String dict_tuples(int c, String tuple, String data_type, String constraint) {
        String table = (c != 0) ? "" : tuple;
        return "<tr><td>" + table + "</td><td>" + tuple + "</td><td>" + data_type + "</td><td>" + constraint + "</td></tr>\n";
    }

    String dict_close_table() {
        return "</table>";
    }
}

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
public class Pub_methods {

    public static String app_folder = "brck.blog_musema_java";

    public void get_writer(String path_file, String content) {
        PrintWriter prnt;
        try {
            prnt = new PrintWriter(new BufferedWriter(new FileWriter(path_file)));
            prnt.println(content);
            prnt.close();
        } catch (IOException ex) {
            
        }
    }

    public String get_Capitalized(String field) {
        return field.substring(0, 1).toUpperCase() + field.substring(1);
    }

    public String get_short_int(String field) {
        return ("Integer".equals(field) | "int".equals(field)) ? "int" : "String";
    }

    public String get_pk(String field) {
        return ("pk".equals(field)) ? field + "_id" : field;
    }

    public String path(String path) {
        return "";
    }

    
    public static String get_uppercase(String field) {
        return field.toUpperCase();
    }

    
    
    
    
    
    
    
    
    
    
}

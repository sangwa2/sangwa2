/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.service;

import com.codeguru.g_ultimate.models.Mdl_structure;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SANGWA
 */
public interface Srvc_structure {

    public List<Mdl_structure> list_all_structure();

    public void add_structure(Mdl_structure structure);

    public void delete_structure(int structure);

    public void update_structure(Mdl_structure structure);

    public Mdl_structure find_structurBy_id(int id);

    public int get_last_structure();

    public String db_exists(String name);

      public ArrayList<String> existing_str();
}

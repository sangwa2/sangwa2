/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.service;

import com.codeguru.g_ultimate.models.Mdl_existing_db_tables;
import com.codeguru.g_ultimate.models.Mdl_structure;
import com.codeguru.g_ultimate.models.Mdl_unit;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SANGWA
 */
public interface Srv_unit {

    public List<Mdl_unit> list_all_units();

    public List<Mdl_unit> units_by_structure(int id, String name);

    public int get_all_units_by_structure(int structure);

   public List<Mdl_unit> units_by_layout(int layout_type,int structure);

    public void add_unit(Mdl_unit unit);

    public void delete_unit(int unit);

    public void update_unit(Mdl_unit unit);

    public Mdl_unit findunitBy_id(int id);

    public int get_if_unit_bystructure_exists(String unit, int structure);

    public int get_last_unit();

    public ArrayList<String> tuples_of_existingdb(String db, String table);

    public List<Mdl_existing_db_tables> existing_db_tabl(String db);

    public ArrayList<String> add_existing_units(String db, int structure, List<String> table);
}

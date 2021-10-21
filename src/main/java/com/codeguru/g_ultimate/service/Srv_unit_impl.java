/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.service;

import com.codeguru.g_ultimate.dao.Dao_units;
import com.codeguru.g_ultimate.models.Mdl_existing_db_tables;
import com.codeguru.g_ultimate.models.Mdl_structure;
import com.codeguru.g_ultimate.models.Mdl_unit;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SANGWA
 */
@Service
public class Srv_unit_impl implements Srv_unit {

    Dao_units dao_unit;

    @Autowired
    public void setDao_unit(Dao_units dao_unit) {
        this.dao_unit = dao_unit;
    }

    @Override
    public List<Mdl_unit> list_all_units() {
        return dao_unit.all_units();
    }

    @Override
    public void add_unit(Mdl_unit unit) {
        dao_unit.add_unit(unit);
    }

    @Override
    public void delete_unit(int unit) {
        dao_unit.delete_unit(unit);
    }

    @Override
    public void update_unit(Mdl_unit unit) {
        dao_unit.update_unit(unit);
    }

    @Override
    public Mdl_unit findunitBy_id(int id) {
        return dao_unit.find_unitBy_id(id);
    }

    @Override
    public List<Mdl_unit> units_by_structure(int id, String name) {
        return dao_unit.units_by_structure(id, name);
    }

    @Override
    public int get_all_units_by_structure(int structure) {
        return dao_unit.get_all_units_by_structure(structure);
    }

    @Override
    public int get_last_unit() {
        return dao_unit.get_last_unit();
    }

    @Override
    public int get_if_unit_bystructure_exists(String unit, int structure) {
        return dao_unit.get_if_unit_bystructure_exists(unit, structure);
    }

    @Override
    public List<Mdl_existing_db_tables> existing_db_tabl(String db) {
        return dao_unit.existing_db_tabl(db);
    }

    @Override
    public ArrayList<String> tuples_of_existingdb(String db, String table) {
        return dao_unit.tuples_of_existingdb(db, table);
    }

    @Override
    public ArrayList<String> add_existing_units(String db,int structure,List<String> table) {
      return  dao_unit.add_existing_units(db,structure, table);
    }

    @Override
    public List<Mdl_unit> units_by_layout(int layout_type, int structure) {
       return dao_unit.units_by_layout(layout_type,   structure);
    }

}

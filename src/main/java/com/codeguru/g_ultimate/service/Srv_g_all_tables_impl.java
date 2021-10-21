/*
 * Implementing the service of g_all_tables.
 *  .
 */
package com.codeguru.g_ultimate.service;

import com.codeguru.g_ultimate.dao.Dao_g_all_tables;
import com.codeguru.g_ultimate.models.Mdl_g_all_tables;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SANGWA
 */
@Service
public class Srv_g_all_tables_impl implements Srv_g_all_tables {

    Dao_g_all_tables dao_g_all_tables;

    @Autowired
    public void setDao_g_all_tables(Dao_g_all_tables dao_g_all_tables) {
        this.dao_g_all_tables = dao_g_all_tables;
    }

    @Override
    public List<Mdl_g_all_tables> all_g_all_tabless() {
       return dao_g_all_tables.all_g_all_tabless();
    }

    @Override
    public void add_g_all_tables(Mdl_g_all_tables g_all_tables) {
      dao_g_all_tables.add_g_all_tables(g_all_tables);
    }

    @Override
    public void delete_g_all_tables(int g_all_tables) {
        dao_g_all_tables.delete_g_all_tables(g_all_tables);
    }

    @Override
    public void update_g_all_tables(Mdl_g_all_tables g_all_tables) {
       dao_g_all_tables.update_g_all_tables(g_all_tables);
    }

    @Override
    public Mdl_g_all_tables find_g_all_tablesBy_id(int g_all_tables) {
        return dao_g_all_tables.find_g_all_tablesBy_id(g_all_tables);
    }

    @Override
    public int get_last_g_all_tables() {
        return dao_g_all_tables.get_last_g_all_tables();
    }
 
}


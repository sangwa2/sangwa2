/*
 * Implementing the service of f_end_data_list.
 *  .
 */
package com.codeguru.g_ultimate.service;

import com.codeguru.g_ultimate.dao.Dao_f_end_data_list;
import com.codeguru.g_ultimate.models.Mdl_f_end_data_list;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SANGWA
 */
@Service
public class Srv_f_end_data_list_impl implements Srv_f_end_data_list {

    Dao_f_end_data_list dao_f_end_data_list;

    @Autowired
    public void setDao_f_end_data_list(Dao_f_end_data_list dao_f_end_data_list) {
        this.dao_f_end_data_list = dao_f_end_data_list;
    }

    @Override
    public List<Mdl_f_end_data_list> all_f_end_data_lists() {
       return dao_f_end_data_list.all_f_end_data_lists();
    }

    @Override
    public void add_f_end_data_list(Mdl_f_end_data_list f_end_data_list) {
      dao_f_end_data_list.add_f_end_data_list(f_end_data_list);
    }

    @Override
    public void delete_f_end_data_list(int f_end_data_list) {
        dao_f_end_data_list.delete_f_end_data_list(f_end_data_list);
    }

    @Override
    public void update_f_end_data_list(Mdl_f_end_data_list f_end_data_list) {
       dao_f_end_data_list.update_f_end_data_list(f_end_data_list);
    }

    @Override
    public Mdl_f_end_data_list find_f_end_data_listBy_id(int f_end_data_list) {
        return dao_f_end_data_list.find_f_end_data_listBy_id(f_end_data_list);
    }

    @Override
    public int get_last_f_end_data_list() {
        return dao_f_end_data_list.get_last_f_end_data_list();
    }
 
}


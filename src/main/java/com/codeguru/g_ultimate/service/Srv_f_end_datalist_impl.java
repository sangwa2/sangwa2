/*
 * Implementing the service of f_end_datalist.
 *  .
 */
    package com.codeguru.g_ultimate.service;

import com.codeguru.g_ultimate.dao.Dao_f_end_datalist;
import com.codeguru.g_ultimate.models.Mdl_f_end_datalist;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SANGWA
 */
@Service
public class Srv_f_end_datalist_impl implements Srv_f_end_datalist {

    Dao_f_end_datalist dao_f_end_datalist;

    @Autowired
    public void setDao_f_end_datalist(Dao_f_end_datalist dao_f_end_datalist) {
        this.dao_f_end_datalist = dao_f_end_datalist;
    }

    @Override
    public List<Mdl_f_end_datalist> all_f_end_datalists() {
       return dao_f_end_datalist.all_f_end_datalists();
    }

    @Override
    public void add_f_end_datalist(Mdl_f_end_datalist f_end_datalist) {
      dao_f_end_datalist.add_f_end_datalist(f_end_datalist);
    }

    @Override
    public void delete_f_end_datalist(int f_end_datalist) {
        dao_f_end_datalist.delete_f_end_datalist(f_end_datalist);
    }

    @Override
    public void update_f_end_datalist(Mdl_f_end_datalist f_end_datalist) {
       dao_f_end_datalist.update_f_end_datalist(f_end_datalist);
    }

    @Override
    public Mdl_f_end_datalist find_f_end_datalistBy_id(int f_end_datalist) {
        return dao_f_end_datalist.find_f_end_datalistBy_id(f_end_datalist);
    }

    @Override
    public int get_last_f_end_datalist() {
        return dao_f_end_datalist.get_last_f_end_datalist();
    }
 
}


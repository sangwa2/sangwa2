/*
 * Implementing the service of f_end_layout.
 *  .
 */
package com.codeguru.g_ultimate.service;

import com.codeguru.g_ultimate.dao.Dao_f_end_layout;
import com.codeguru.g_ultimate.models.Mdl_f_end_layout;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SANGWA
 */
@Service
public class Srv_f_end_layout_impl implements Srv_f_end_layout {

    Dao_f_end_layout dao_f_end_layout;

    @Autowired
    public void setDao_f_end_layout(Dao_f_end_layout dao_f_end_layout) {
        this.dao_f_end_layout = dao_f_end_layout;
    }


    @Override
    public void add_f_end_layout(Mdl_f_end_layout f_end_layout) {
        dao_f_end_layout.add_f_end_layout(f_end_layout);
    }

    @Override
    public void delete_f_end_layout(int f_end_layout) {
        dao_f_end_layout.delete_f_end_layout(f_end_layout);
    }

    @Override
    public void update_f_end_layout(Mdl_f_end_layout f_end_layout) {
        dao_f_end_layout.update_f_end_layout(f_end_layout);
    }

 
    @Override
    public int get_last_f_end_layout() {
        return dao_f_end_layout.get_last_f_end_layout();
    }

    @Override
    public Mdl_f_end_layout find_single_f_end_layoutBy_id(int f_end_layout) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Mdl_f_end_layout> find_f_end_layoutBy_id(int f_end_layout) {
            return dao_f_end_layout.find_f_end_layoutBy_id(f_end_layout);
    }

    @Override
    public Mdl_f_end_layout find_single_f_end_layoutBy_name(String layout_name) {
        return dao_f_end_layout.find_single_f_end_layoutBy_name(layout_name);
    }

     

}

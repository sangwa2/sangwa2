/*
 * Implementing the service of f_end_css.
 *  .
 */
package com.codeguru.g_ultimate.service;

import com.codeguru.g_ultimate.dao.Dao_f_end_css;
import com.codeguru.g_ultimate.models.Mdl_f_end_css;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SANGWA
 */
@Service
public class Srv_f_end_css_impl implements Srv_f_end_css {

    Dao_f_end_css dao_f_end_css;

    @Autowired
    public void setDao_f_end_css(Dao_f_end_css dao_f_end_css) {
        this.dao_f_end_css = dao_f_end_css;
    }

    @Override
    public List<Mdl_f_end_css> all_f_end_csss() {
       return dao_f_end_css.all_f_end_csss();
    }

    @Override
    public void add_f_end_css(Mdl_f_end_css f_end_css) {
      dao_f_end_css.add_f_end_css(f_end_css);
    }

    @Override
    public void delete_f_end_css(int f_end_css) {
        dao_f_end_css.delete_f_end_css(f_end_css);
    }

    @Override
    public void update_f_end_css(Mdl_f_end_css f_end_css) {
       dao_f_end_css.update_f_end_css(f_end_css);
    }

    @Override
    public Mdl_f_end_css find_f_end_cssBy_id(int f_end_css) {
        return dao_f_end_css.find_f_end_cssBy_id(f_end_css);
    }

    @Override
    public int get_last_f_end_css() {
        return dao_f_end_css.get_last_f_end_css();
    }
 
}


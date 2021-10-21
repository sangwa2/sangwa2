/*
 * Implementing the service of f_end_js_category.
 *  .
 */
package com.codeguru.g_ultimate.service;

import com.codeguru.g_ultimate.dao.Dao_f_end_js_category;
import com.codeguru.g_ultimate.models.Mdl_f_end_js_category;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SANGWA
 */
@Service
public class Srv_f_end_js_category_impl implements Srv_f_end_js_category {

    Dao_f_end_js_category dao_f_end_js_category;

    @Autowired
    public void setDao_f_end_js_category(Dao_f_end_js_category dao_f_end_js_category) {
        this.dao_f_end_js_category = dao_f_end_js_category;
    }

    @Override
    public List<Mdl_f_end_js_category> all_f_end_js_categorys() {
       return dao_f_end_js_category.all_f_end_js_categorys();
    }

    @Override
    public void add_f_end_js_category(Mdl_f_end_js_category f_end_js_category) {
      dao_f_end_js_category.add_f_end_js_category(f_end_js_category);
    }

    @Override
    public void delete_f_end_js_category(int f_end_js_category) {
        dao_f_end_js_category.delete_f_end_js_category(f_end_js_category);
    }

    @Override
    public void update_f_end_js_category(Mdl_f_end_js_category f_end_js_category) {
       dao_f_end_js_category.update_f_end_js_category(f_end_js_category);
    }

    @Override
    public Mdl_f_end_js_category find_f_end_js_categoryBy_id(int f_end_js_category) {
        return dao_f_end_js_category.find_f_end_js_categoryBy_id(f_end_js_category);
    }

    @Override
    public int get_last_f_end_js_category() {
        return dao_f_end_js_category.get_last_f_end_js_category();
    }
 
}


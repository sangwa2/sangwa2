/*
 * Implementing the service of rel_query_category.
 *  .
 */
package com.codeguru.g_ultimate.service;

import com.codeguru.g_ultimate.dao.Dao_rel_query_category;
import com.codeguru.g_ultimate.models.Mdl_rel_query_category;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SANGWA
 */
@Service
public class Srv_rel_query_category_impl implements Srv_rel_query_category {

    Dao_rel_query_category dao_rel_query_category;

    @Autowired
    public void setDao_rel_query_category(Dao_rel_query_category dao_rel_query_category) {
        this.dao_rel_query_category = dao_rel_query_category;
    }

    @Override
    public List<Mdl_rel_query_category> all_rel_query_categorys() {
       return dao_rel_query_category.all_rel_query_categorys();
    }

    @Override
    public void add_rel_query_category(Mdl_rel_query_category rel_query_category) {
      dao_rel_query_category.add_rel_query_category(rel_query_category);
    }

    @Override
    public void delete_rel_query_category(int rel_query_category) {
        dao_rel_query_category.delete_rel_query_category(rel_query_category);
    }

    @Override
    public void update_rel_query_category(Mdl_rel_query_category rel_query_category) {
       dao_rel_query_category.update_rel_query_category(rel_query_category);
    }

    @Override
    public Mdl_rel_query_category find_rel_query_categoryBy_id(int rel_query_category) {
        return dao_rel_query_category.find_rel_query_categoryBy_id(rel_query_category);
    }

    @Override
    public int get_last_rel_query_category() {
        return dao_rel_query_category.get_last_rel_query_category();
    }

    @Override
    public int querycategoryid_by_categoryname(String name) {
        return dao_rel_query_category.querycategoryid_by_categoryname(name);
    }
 
}


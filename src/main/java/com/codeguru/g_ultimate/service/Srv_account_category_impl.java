/*
 * Implementing the service of account_category.
 *  .
 */
package com.codeguru.g_ultimate.service;

import com.codeguru.g_ultimate.dao.Dao_account_category;
import com.codeguru.g_ultimate.models.Mdl_account_category;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SANGWA
 */
@Service
public class Srv_account_category_impl implements Srv_account_category {

    Dao_account_category dao_account_category;

    @Autowired
    public void setDao_account_category(Dao_account_category dao_account_category) {
        this.dao_account_category = dao_account_category;
    }

    @Override
    public List<Mdl_account_category> all_account_categorys() {
       return dao_account_category.all_account_categorys();
    }

    @Override
    public void add_account_category(Mdl_account_category account_category) {
      dao_account_category.add_account_category(account_category);
    }

    @Override
    public void delete_account_category(int account_category) {
        dao_account_category.delete_account_category(account_category);
    }

    @Override
    public void update_account_category(Mdl_account_category account_category) {
       dao_account_category.update_account_category(account_category);
    }

    @Override
    public Mdl_account_category find_account_categoryBy_id(int account_category) {
        return dao_account_category.find_account_categoryBy_id(account_category);
    }

    @Override
    public int get_last_account_category() {
        return dao_account_category.get_last_account_category();
    }
 
}


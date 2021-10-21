package com.codeguru.g_ultimate.dao;

import com.codeguru.g_ultimate.models.Mdl_account_category;
import java.util.List;

/**
 *
 * @author SANGWA
 */
public interface Dao_account_category {

    public List<Mdl_account_category> all_account_categorys();

    
    public void add_account_category(Mdl_account_category account_category);

    public void delete_account_category(int account_category);

    public void update_account_category(Mdl_account_category account_category);

    
    public Mdl_account_category find_account_categoryBy_id(int account_category);
    
    public int get_last_account_category();
}


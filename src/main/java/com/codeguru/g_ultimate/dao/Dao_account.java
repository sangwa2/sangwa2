package com.codeguru.g_ultimate.dao;

import com.codeguru.g_ultimate.models.Mdl_account;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SANGWA
 */
@Repository
public interface Dao_account {

    public List<Mdl_account> all_accounts();

    
    public void add_account(Mdl_account account);

    public void delete_account(int account);

    public void update_account(Mdl_account account);

    
    public Mdl_account find_accountBy_id(int account);
    public Mdl_account find_accountBy_usename_pass(String username, String password);
    
    public int get_last_account();
}


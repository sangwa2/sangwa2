package com.codeguru.g_ultimate.service;

import com.codeguru.g_ultimate.models.Mdl_account;
import java.util.List;

/**
 *
 * For author SANGWA  
 sangwa22@gmail.com 
 */
public interface Srv_account {

    public List<Mdl_account> all_accounts();
    
    public void add_account(Mdl_account account);

    public void delete_account(int account);

    public void update_account(Mdl_account account);

    
    public Mdl_account find_accountBy_id(int account);
    
    public int get_last_account();
    
    public Mdl_account find_accountBy_usename_pass(String username, String password);
}


/*
 * Implementing the service of account.
 *  .
 */
package com.codeguru.g_ultimate.service;

import com.codeguru.g_ultimate.dao.Dao_account;
import com.codeguru.g_ultimate.models.Mdl_account;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SANGWA
 */
@Service
public class Srv_account_impl implements Srv_account {

    Dao_account dao_account;

    @Autowired
    public void setDao_account(Dao_account dao_account) {
        this.dao_account = dao_account;
    }

    @Override
    public List<Mdl_account> all_accounts() {
        return dao_account.all_accounts();
    }

    @Override
    public void add_account(Mdl_account account) {
        dao_account.add_account(account);
    }

    @Override
    public void delete_account(int account) {
        dao_account.delete_account(account);
    }

    @Override
    public void update_account(Mdl_account account) {
        dao_account.update_account(account);
    }

    @Override
    public Mdl_account find_accountBy_id(int account) {
        return dao_account.find_accountBy_id(account);
    }

    @Override
    public int get_last_account() {
        return dao_account.get_last_account();
    }

    @Override
    public Mdl_account find_accountBy_usename_pass(String username, String password) {
        return dao_account.find_accountBy_usename_pass(username, password);
    }

}

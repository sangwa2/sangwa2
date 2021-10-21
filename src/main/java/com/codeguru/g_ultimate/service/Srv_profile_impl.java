/*
 * Implementing the service of profile.
 *  .
 */
package com.codeguru.g_ultimate.service;

import com.codeguru.g_ultimate.dao.Dao_profile;
import com.codeguru.g_ultimate.models.Mdl_profile;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SANGWA
 */
@Service
public class Srv_profile_impl implements Srv_profile {

    Dao_profile dao_profile;

    @Autowired
    public void setDao_profile(Dao_profile dao_profile) {
        this.dao_profile = dao_profile;
    }

    @Override
    public List<Mdl_profile> all_profiles() {
       return dao_profile.all_profiles();
    }

    @Override
    public void add_profile(Mdl_profile profile) {
      dao_profile.add_profile(profile);
    }

    @Override
    public void delete_profile(int profile) {
        dao_profile.delete_profile(profile);
    }

    @Override
    public void update_profile(Mdl_profile profile) {
       dao_profile.update_profile(profile);
    }

    @Override
    public Mdl_profile find_profileBy_id(int profile) {
        return dao_profile.find_profileBy_id(profile);
    }

    @Override
    public int get_last_profile() {
        return dao_profile.get_last_profile();
    }
 
}


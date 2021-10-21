package com.codeguru.g_ultimate.service;

import com.codeguru.g_ultimate.models.Mdl_profile;
import java.util.List;

/**
 *
 * For author SANGWA  
 sangwa22@gmail.com 
 */
public interface Srv_profile {

    public List<Mdl_profile> all_profiles();
    
    public void add_profile(Mdl_profile profile);

    public void delete_profile(int profile);

    public void update_profile(Mdl_profile profile);

    
    public Mdl_profile find_profileBy_id(int profile);
    
    public int get_last_profile();
}


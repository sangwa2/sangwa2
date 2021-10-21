package com.codeguru.g_ultimate.service;

import com.codeguru.g_ultimate.models.Mdl_code_place;
import java.util.List;

/**
 *
 * For author SANGWA  
 sangwa22@gmail.com 
 */
public interface Srv_code_place {

    public List<Mdl_code_place> all_code_places();
    
    public void add_code_place(Mdl_code_place code_place);

    public void delete_code_place(int code_place);

    public void update_code_place(Mdl_code_place code_place);

    
    public Mdl_code_place find_code_placeBy_id(int code_place);
    
    public int get_last_code_place();
}


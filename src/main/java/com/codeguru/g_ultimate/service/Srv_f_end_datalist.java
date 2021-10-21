package com.codeguru.g_ultimate.service;

import com.codeguru.g_ultimate.models.Mdl_f_end_datalist;
import java.util.List;

/**
 *
 * For author SANGWA  
 sangwa22@gmail.com 
 */
public interface Srv_f_end_datalist {

    public List<Mdl_f_end_datalist> all_f_end_datalists();
    
    public void add_f_end_datalist(Mdl_f_end_datalist f_end_datalist);

    public void delete_f_end_datalist(int f_end_datalist);

    public void update_f_end_datalist(Mdl_f_end_datalist f_end_datalist);

    
    public Mdl_f_end_datalist find_f_end_datalistBy_id(int f_end_datalist);
    
    public int get_last_f_end_datalist();
}


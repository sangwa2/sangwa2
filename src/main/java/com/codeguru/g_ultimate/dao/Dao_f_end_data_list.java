package com.codeguru.g_ultimate.dao;

import com.codeguru.g_ultimate.models.Mdl_f_end_data_list;
import java.util.List;

/**
 *
 * @author SANGWA
 */
public interface Dao_f_end_data_list {

    public List<Mdl_f_end_data_list> all_f_end_data_lists();

    
    public void add_f_end_data_list(Mdl_f_end_data_list f_end_data_list);

    public void delete_f_end_data_list(int f_end_data_list);

    public void update_f_end_data_list(Mdl_f_end_data_list f_end_data_list);

    
    public Mdl_f_end_data_list find_f_end_data_listBy_id(int f_end_data_list);
    
    public int get_last_f_end_data_list();
}


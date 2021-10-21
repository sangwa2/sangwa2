package com.codeguru.g_ultimate.dao;

import com.codeguru.g_ultimate.models.Mdl_f_end_layout;
import java.util.List;

/**
 *
 * @author SANGWA
 */
public interface Dao_f_end_layout {

     
    public void add_f_end_layout(Mdl_f_end_layout f_end_layout);

    public void delete_f_end_layout(int f_end_layout);

    public void update_f_end_layout(Mdl_f_end_layout f_end_layout);

    public List<Mdl_f_end_layout> find_f_end_layoutBy_id(int f_end_layout);

    public Mdl_f_end_layout find_single_f_end_layoutBy_name(String layout_name);

    

    public int get_last_f_end_layout();
}

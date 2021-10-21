package com.codeguru.g_ultimate.service;

import com.codeguru.g_ultimate.models.Mdl_f_end_class_attrib;
import java.util.List;

/**
 *
 * For author SANGWA  
 sangwa22@gmail.com 
 */
public interface Srv_f_end_class_attrib {

    public List<Mdl_f_end_class_attrib> all_f_end_class_attribs();
    
    public void add_f_end_class_attrib(Mdl_f_end_class_attrib f_end_class_attrib);

    public void delete_f_end_class_attrib(int f_end_class_attrib);

    public void update_f_end_class_attrib(Mdl_f_end_class_attrib f_end_class_attrib);

    
    public Mdl_f_end_class_attrib find_f_end_class_attribBy_id(int f_end_class_attrib);
    
    public int get_last_f_end_class_attrib();
}


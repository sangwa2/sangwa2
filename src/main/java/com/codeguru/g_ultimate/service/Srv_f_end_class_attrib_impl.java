/*
 * Implementing the service of f_end_class_attrib.
 *  .
 */
package com.codeguru.g_ultimate.service;

import com.codeguru.g_ultimate.dao.Dao_f_end_class_attrib;
import com.codeguru.g_ultimate.models.Mdl_f_end_class_attrib;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SANGWA
 */
@Service
public class Srv_f_end_class_attrib_impl implements Srv_f_end_class_attrib {

    Dao_f_end_class_attrib dao_f_end_class_attrib;

    @Autowired
    public void setDao_f_end_class_attrib(Dao_f_end_class_attrib dao_f_end_class_attrib) {
        this.dao_f_end_class_attrib = dao_f_end_class_attrib;
    }

    @Override
    public List<Mdl_f_end_class_attrib> all_f_end_class_attribs() {
       return dao_f_end_class_attrib.all_f_end_class_attribs();
    }

    @Override
    public void add_f_end_class_attrib(Mdl_f_end_class_attrib f_end_class_attrib) {
      dao_f_end_class_attrib.add_f_end_class_attrib(f_end_class_attrib);
    }

    @Override
    public void delete_f_end_class_attrib(int f_end_class_attrib) {
        dao_f_end_class_attrib.delete_f_end_class_attrib(f_end_class_attrib);
    }

    @Override
    public void update_f_end_class_attrib(Mdl_f_end_class_attrib f_end_class_attrib) {
       dao_f_end_class_attrib.update_f_end_class_attrib(f_end_class_attrib);
    }

    @Override
    public Mdl_f_end_class_attrib find_f_end_class_attribBy_id(int f_end_class_attrib) {
        return dao_f_end_class_attrib.find_f_end_class_attribBy_id(f_end_class_attrib);
    }

    @Override
    public int get_last_f_end_class_attrib() {
        return dao_f_end_class_attrib.get_last_f_end_class_attrib();
    }
 
}


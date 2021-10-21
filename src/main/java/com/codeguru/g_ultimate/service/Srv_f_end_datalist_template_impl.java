/*
 * Implementing the service of f_end_datalist_template.
 *  .
 */
package com.codeguru.g_ultimate.service;

import com.codeguru.g_ultimate.dao.Dao_f_end_datalist_template;
import com.codeguru.g_ultimate.models.Mdl_f_end_datalist_template;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SANGWA
 */
@Service
public class Srv_f_end_datalist_template_impl implements Srv_f_end_datalist_template {

    Dao_f_end_datalist_template dao_f_end_datalist_template;

    @Autowired
    public void setDao_f_end_datalist_template(Dao_f_end_datalist_template dao_f_end_datalist_template) {
        this.dao_f_end_datalist_template = dao_f_end_datalist_template;
    }

    @Override
    public List<Mdl_f_end_datalist_template> all_f_end_datalist_templates() {
       return dao_f_end_datalist_template.all_f_end_datalist_templates();
    }

    @Override
    public void add_f_end_datalist_template(Mdl_f_end_datalist_template f_end_datalist_template) {
      dao_f_end_datalist_template.add_f_end_datalist_template(f_end_datalist_template);
    }

    @Override
    public void delete_f_end_datalist_template(int f_end_datalist_template) {
        dao_f_end_datalist_template.delete_f_end_datalist_template(f_end_datalist_template);
    }

    @Override
    public void update_f_end_datalist_template(Mdl_f_end_datalist_template f_end_datalist_template) {
       dao_f_end_datalist_template.update_f_end_datalist_template(f_end_datalist_template);
    }

    @Override
    public Mdl_f_end_datalist_template find_f_end_datalist_templateBy_id(int f_end_datalist_template) {
        return dao_f_end_datalist_template.find_f_end_datalist_templateBy_id(f_end_datalist_template);
    }

    @Override
    public int get_last_f_end_datalist_template() {
        return dao_f_end_datalist_template.get_last_f_end_datalist_template();
    }
 
}


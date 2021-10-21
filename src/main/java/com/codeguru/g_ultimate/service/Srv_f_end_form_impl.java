/*
 * Implementing the service of f_end_form.
 *  .
 */
package com.codeguru.g_ultimate.service;

import com.codeguru.g_ultimate.dao.Dao_f_end_form;
import com.codeguru.g_ultimate.models.Mdl_f_end_form;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SANGWA
 */
@Service
public class Srv_f_end_form_impl implements Srv_f_end_form {

    Dao_f_end_form dao_f_end_form;

    @Autowired
    public void setDao_f_end_form(Dao_f_end_form dao_f_end_form) {
        this.dao_f_end_form = dao_f_end_form;
    }

    @Override
    public List<Mdl_f_end_form> all_f_end_forms() {
       return dao_f_end_form.all_f_end_forms();
    }

    @Override
    public void add_f_end_form(Mdl_f_end_form f_end_form) {
      dao_f_end_form.add_f_end_form(f_end_form);
    }

    @Override
    public void delete_f_end_form(int f_end_form) {
        dao_f_end_form.delete_f_end_form(f_end_form);
    }

    @Override
    public void update_f_end_form(Mdl_f_end_form f_end_form) {
       dao_f_end_form.update_f_end_form(f_end_form);
    }

    @Override
    public Mdl_f_end_form find_f_end_formBy_id(int f_end_form) {
        return dao_f_end_form.find_f_end_formBy_id(f_end_form);
    }

    @Override
    public int get_last_f_end_form() {
        return dao_f_end_form.get_last_f_end_form();
    }
 
}


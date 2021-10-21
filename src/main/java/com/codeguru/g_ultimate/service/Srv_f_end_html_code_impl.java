/*
 * Implementing the service of f_end_html_code.
 *  .
 */
package com.codeguru.g_ultimate.service;

import com.codeguru.g_ultimate.dao.Dao_f_end_html_code;
import com.codeguru.g_ultimate.models.Mdl_f_end_html_code;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SANGWA
 */
@Service
public class Srv_f_end_html_code_impl implements Srv_f_end_html_code {

    Dao_f_end_html_code dao_f_end_html_code;

    @Autowired
    public void setDao_f_end_html_code(Dao_f_end_html_code dao_f_end_html_code) {
        this.dao_f_end_html_code = dao_f_end_html_code;
    }

    @Override
    public List<Mdl_f_end_html_code> all_f_end_html_codes() {
       return dao_f_end_html_code.all_f_end_html_codes();
    }

    @Override
    public void add_f_end_html_code(Mdl_f_end_html_code f_end_html_code) {
      dao_f_end_html_code.add_f_end_html_code(f_end_html_code);
    }

    @Override
    public void delete_f_end_html_code(int f_end_html_code) {
        dao_f_end_html_code.delete_f_end_html_code(f_end_html_code);
    }

    @Override
    public void update_f_end_html_code(Mdl_f_end_html_code f_end_html_code) {
       dao_f_end_html_code.update_f_end_html_code(f_end_html_code);
    }

    @Override
    public Mdl_f_end_html_code find_f_end_html_codeBy_id(int f_end_html_code) {
        return dao_f_end_html_code.find_f_end_html_codeBy_id(f_end_html_code);
    }

    @Override
    public int get_last_f_end_html_code() {
        return dao_f_end_html_code.get_last_f_end_html_code();
    }
 
}


/*
 * Implementing the service of f_end_js_code.
 *  .
 */
package com.codeguru.g_ultimate.service;

import com.codeguru.g_ultimate.dao.Dao_f_end_js_code;
import com.codeguru.g_ultimate.models.Mdl_f_end_js_code;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SANGWA
 */
@Service
public class Srv_f_end_js_code_impl implements Srv_f_end_js_code {

    Dao_f_end_js_code dao_f_end_js_code;

    @Autowired
    public void setDao_f_end_js_code(Dao_f_end_js_code dao_f_end_js_code) {
        this.dao_f_end_js_code = dao_f_end_js_code;
    }

    @Override
    public List<Mdl_f_end_js_code> all_f_end_js_codes() {
       return dao_f_end_js_code.all_f_end_js_codes();
    }

    @Override
    public void add_f_end_js_code(Mdl_f_end_js_code f_end_js_code) {
      dao_f_end_js_code.add_f_end_js_code(f_end_js_code);
    }

    @Override
    public void delete_f_end_js_code(int f_end_js_code) {
        dao_f_end_js_code.delete_f_end_js_code(f_end_js_code);
    }

    @Override
    public void update_f_end_js_code(Mdl_f_end_js_code f_end_js_code) {
       dao_f_end_js_code.update_f_end_js_code(f_end_js_code);
    }

    @Override
    public Mdl_f_end_js_code find_f_end_js_codeBy_id(int f_end_js_code) {
        return dao_f_end_js_code.find_f_end_js_codeBy_id(f_end_js_code);
    }

    @Override
    public int get_last_f_end_js_code() {
        return dao_f_end_js_code.get_last_f_end_js_code();
    }
 
}


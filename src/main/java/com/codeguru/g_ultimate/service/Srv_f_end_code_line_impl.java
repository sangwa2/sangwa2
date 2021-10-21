/*
 * Implementing the service of f_end_code_line.
 *  .
 */
package com.codeguru.g_ultimate.service;

import com.codeguru.g_ultimate.dao.Dao_f_end_code_line;
import com.codeguru.g_ultimate.models.Mdl_f_end_code_line;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SANGWA
 */
@Service
public class Srv_f_end_code_line_impl implements Srv_f_end_code_line {

    Dao_f_end_code_line dao_f_end_code_line;

    @Autowired
    public void setDao_f_end_code_line(Dao_f_end_code_line dao_f_end_code_line) {
        this.dao_f_end_code_line = dao_f_end_code_line;
    }

    @Override
    public List<Mdl_f_end_code_line> all_f_end_code_lines() {
       return dao_f_end_code_line.all_f_end_code_lines();
    }

    @Override
    public void add_f_end_code_line(Mdl_f_end_code_line f_end_code_line) {
      dao_f_end_code_line.add_f_end_code_line(f_end_code_line);
    }

    @Override
    public void delete_f_end_code_line(int f_end_code_line) {
        dao_f_end_code_line.delete_f_end_code_line(f_end_code_line);
    }

    @Override
    public void update_f_end_code_line(Mdl_f_end_code_line f_end_code_line) {
       dao_f_end_code_line.update_f_end_code_line(f_end_code_line);
    }

    @Override
    public Mdl_f_end_code_line find_f_end_code_lineBy_id(int f_end_code_line) {
        return dao_f_end_code_line.find_f_end_code_lineBy_id(f_end_code_line);
    }

    @Override
    public int get_last_f_end_code_line() {
        return dao_f_end_code_line.get_last_f_end_code_line();
    }
 
}


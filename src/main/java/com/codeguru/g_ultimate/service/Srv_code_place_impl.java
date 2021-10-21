/*
 * Implementing the service of code_place.
 *  .
 */
package com.codeguru.g_ultimate.service;

import com.codeguru.g_ultimate.dao.Dao_code_place;
import com.codeguru.g_ultimate.models.Mdl_code_place;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SANGWA
 */
@Service
public class Srv_code_place_impl implements Srv_code_place {

    Dao_code_place dao_code_place;

    @Autowired
    public void setDao_code_place(Dao_code_place dao_code_place) {
        this.dao_code_place = dao_code_place;
    }

    @Override
    public List<Mdl_code_place> all_code_places() {
       return dao_code_place.all_code_places();
    }

    @Override
    public void add_code_place(Mdl_code_place code_place) {
      dao_code_place.add_code_place(code_place);
    }

    @Override
    public void delete_code_place(int code_place) {
        dao_code_place.delete_code_place(code_place);
    }

    @Override
    public void update_code_place(Mdl_code_place code_place) {
       dao_code_place.update_code_place(code_place);
    }

    @Override
    public Mdl_code_place find_code_placeBy_id(int code_place) {
        return dao_code_place.find_code_placeBy_id(code_place);
    }

    @Override
    public int get_last_code_place() {
        return dao_code_place.get_last_code_place();
    }
 
}


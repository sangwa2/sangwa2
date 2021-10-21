/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.dao;

import com.codeguru.g_ultimate.models.Mdl_relations;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SANGWA
 */
public interface Dao_relations {
    public List<Mdl_relations> all_relationss();
    
    public void add_relations(Mdl_relations relations);

    public void delete_relations(int relations);
    public void delete_relations_by_unit(int unit);

    public void update_relations(Mdl_relations relations);
    
    public Mdl_relations find_relationsBy_id(int relations);
    
    public int get_last_relations();
}

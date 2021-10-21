/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.service;

import com.codeguru.g_ultimate.models.Mdl_unit_structure;
import java.util.List;

/**
 *
 * @author SANGWA
 */
public interface Srv_unit_structure {

    public List<Mdl_unit_structure> units_by_structure(int id);

    public List<Mdl_unit_structure> units_by_structure_no_tuples(int id);
}

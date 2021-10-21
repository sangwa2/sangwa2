/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.dao.queies;

import com.codeguru.g_ultimate.models.Mdl_rel_query;
import java.util.List;

/**
 *
 * @author SANGWA
 */
public class Loop_all_units extends Initials {

    public Loop_all_units() {
    }

    public Loop_all_units(List<Mdl_rel_query> unit) {
        int c = 0;
        for (Mdl_rel_query u : unit) {
            whole_join += "\n  " + c + " => " + u.getChild() + " vs " + u.getParent();
            c += 1;
        }
    }

}

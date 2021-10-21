/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.dao.query;

import com.codeguru.g_ultimate.models.Mdl_rel_query;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SANGWA
 */
public class Search_if_found extends Initials {

    public boolean Search_if_item_found(String c, String p) {

        loop_items_given_index = new ArrayList<>();
        boolean flag = false;
        System.out.println("This time the final array is: " + final_join.size());
        for (int j = 0; j < final_join.size(); j++) {
            if (!curr_child.equals(c) && !curr_parent.equals(p)) {
                if (c.equals(final_join.get(j).getChild()) 
                        || c.equals(final_join.get(j).getParent()) 
                        || p.equals(final_join.get(j).getChild()) 
                        || p.equals(final_join.get(j).getParent())) {
                    flag = true;
                    break;
                } else {
                    flag = false;
                }

            }
        }
        return flag;
    }

    public boolean search_single_item(String item) {
        boolean flag = false;
        for (int j = 0; j < final_join.size(); j++) {
            if (item.equals(final_join.get(j).getChild()) || item.equals(final_join.get(j).getParent())) {
                flag = true;
                break;
            } else {
                flag = false;
            }

        }
        return flag;
    }

    

}

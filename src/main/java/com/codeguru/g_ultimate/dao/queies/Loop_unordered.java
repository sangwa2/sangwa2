package com.codeguru.g_ultimate.dao.queies;

import com.codeguru.g_ultimate.models.Mdl_rel_query;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SANGWA
 */
public class Loop_unordered extends Loop_all_units {

    public void get_unordered_list(List<Mdl_rel_query> unit) {
        for (int i = 0; i < unit.size(); i++) {
            curr_child = unit.get(i).getChild();
            curr_parent = unit.get(i).getParent();
            if (i == 0) {
                prev_child = "";
            } else {
                prev_child = unit.get(i - 1).getChild();
                prev_parent = unit.get(i - 1).getParent();
            }
            if (!prev_child.equals(curr_child) && !prev_parent.equals(curr_child)
                    && !prev_child.equals(curr_parent) && !prev_parent.equals(curr_parent)) {
                if (i > 0) {
                    unordered.add(new Mdl_rel_query(curr_parent, curr_child));
                    unmatch += "\n =>  " + curr_child + " vs " + curr_parent;
                }
            }
        }
    }
   

}

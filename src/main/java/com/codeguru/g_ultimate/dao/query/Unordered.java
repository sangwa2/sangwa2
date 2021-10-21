/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.dao.query;

import com.codeguru.g_ultimate.models.Mdl_rel_query;
import java.util.List;

/**
 *
 * @author SANGWA
 */
public class Unordered extends Cross_join {

    public Unordered() {
    }

    public void unordered(List<Mdl_rel_query> unit) {
        for (int i = 0; i < unit.size(); i++) {

            String c = unit.get(i).getChild();
            String p = unit.get(i).getParent();
            if ((curr_child.equals(c) || curr_parent.equals(c))) {
                if (super.search_single_item(p)) {//p is found if the above indexes
                    if (!reps.contains(c)) {
                        reps.add(c);
                        final_join.add(new Mdl_rel_query(p, c, c));//of c/c
                    }else if (!reps.contains(p)){
                        reps.add(p);
                        final_join.add(new Mdl_rel_query(p, c, p));//of c/c
                    }
                    if (curr_child.equals(c)) {
                        if (!reps.contains(curr_parent)) {
                            reps.add(curr_parent);
                            final_join.add(new Mdl_rel_query(curr_parent, curr_child, curr_parent));// of current
                            break;
                        }else if (!reps.contains(curr_child)){
                            reps.add(curr_child);
                            final_join.add(new Mdl_rel_query(curr_parent, curr_child, curr_child));// of current
                            break;
                        }
                        
                    } else if (curr_parent.equals(p)) {
                        if (!reps.contains(curr_child)) {
                            reps.add(curr_child);
                            final_join.add(new Mdl_rel_query(curr_parent, curr_child, curr_child));// of current
                            break;
                        }else if (!reps.contains(curr_parent)){
                            reps.add(curr_parent);
                            final_join.add(new Mdl_rel_query(curr_parent, curr_child, curr_parent));// of current
                            break;
                        }
                    }
                }
            } else if ((curr_child.equals(p) || prev_parent.equals(p))) {
                if (super.search_single_item(c)) {
                    if (!reps.contains(p)) {
                        reps.add(p);
                        final_join.add(new Mdl_rel_query(p, c, p));
                    }else if (!reps.contains(c)){
                        reps.add(c);
                        final_join.add(new Mdl_rel_query(p, c, c));
                    }
                    if (curr_child.equals(p)) {
                        if (!reps.contains(curr_parent)) {
                            reps.add(curr_parent);
                            final_join.add(new Mdl_rel_query(curr_parent, curr_child, curr_parent));// of current
                            break;
                        }else  if (!reps.contains(curr_child)){
                            reps.add(curr_child);
                            final_join.add(new Mdl_rel_query(curr_parent, curr_child, curr_child));// of current
                            break;
                        }
                    } else if (curr_parent.equals(c)) {
                        if (!reps.contains(curr_child)) {
                            reps.add(curr_child);
                            final_join.add(new Mdl_rel_query(curr_parent, curr_child, curr_child));// of current
                            break;
                        }else if (!reps.contains(curr_parent)){
                             reps.add(curr_parent);
                            final_join.add(new Mdl_rel_query(curr_parent, curr_child, curr_parent));// of current
                            break;
                        }
                    }
                }
            }
        }
    }

    private void curr_save(String c1, String p1) {
        if (c1.equals(curr_child) || c1.equals(curr_child)) {
            curr_p(c1, p1);
        } else if (c1.equals(curr_child) || c1.equals(curr_parent)) {
            curr_c(c1, p1);
        } else {
            //This is the cross join
            new Cross_join().add_cross_join(new Mdl_rel_query(p1, c1, c1));
            System.out.println("The cross join: " + c1 + " vs " + p1);
        }
        curr_child = c1;
        curr_parent = p1;

        prev_child = get_the_last_in_final().getChild();
        prev_parent = get_the_last_in_final().getParent();
        //change the current parent and the current parent
    }

    public void c_join(String c3, String p3) {
        if (!reps.contains(c3)) {
            final_join.add(new Mdl_rel_query(p3, c3, c3));
            reps.add(c3);

        }

    }

    public void p_join(String c3, String p3) {
        if (!reps.contains(p3)) {
            final_join.add(new Mdl_rel_query(p3, c3, p3));
            reps.add(p3);

        }

    }

    private Mdl_rel_query get_the_last_in_final() {
        int size = final_join.size() - 1;
        Mdl_rel_query md = final_join.get(size);
        return md;
    }

}

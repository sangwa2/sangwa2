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
public class Main_loop extends Unordered {

    public String get_query(List<Mdl_rel_query> unit) {
        for (int i = 0; i < unit.size(); i++) {
            curr_child = unit.get(i).getChild();
            curr_parent = unit.get(i).getParent();

            if (i == 0) {
                prev_child = "";
                all_q += "";
                reps.add(curr_parent);
                reps.add(curr_child);
                final_join.add(new Mdl_rel_query(curr_child, curr_parent, curr_parent + "(k)"));

            } else if (i > 0) {
                prev_child = unit.get(i - 1).getChild();
                prev_parent = unit.get(i - 1).getParent();

                if (curr_child.equals(prev_child) || curr_child.equals(prev_parent)) {
                    if (!reps.contains(curr_parent)) {
                        reps.add(curr_parent);
                        final_join.add(new Mdl_rel_query(curr_parent, curr_child, curr_parent));
                    }
                } else if (curr_parent.equals(prev_child) || curr_parent.equals(prev_parent)) {
                    if (!reps.contains(curr_child)) {
                        reps.add(curr_child);
                        final_join.add(new Mdl_rel_query(curr_parent, curr_child, curr_child));
                        get_last_item(unit, i);
                    }
                } else if (!prev_child.equals(curr_child) && !prev_parent.equals(curr_child) && !prev_child.equals(curr_parent) && !prev_parent.equals(curr_parent)) {
                    get_last_item(unit, i);

                    if (super.Search_if_item_found(curr_child, curr_parent)) {//if not found
                        //Get the 
                        final_join.add(new Mdl_rel_query(curr_parent, curr_child, curr_parent));
                    } else {
                        System.out.println("We gone to solve");
                        super.unordered(unit);//go and resolve the qiuery from the unordered
                    }
                }
            }
        }

        
        //finally ge the finished list and print it
        for (int i = 0; i < final_join.size(); i++) {
            if (i == 0) {//this is the first item  use select instead of Join
                whole_join+="  FROM  "+ final_join.get(i).getChild()+"\n<br/> join "+final_join.get(i).getParent()+" on "+ join(final_join.get(i).getChild(), final_join.get(i).getParent());
            } else {
                whole_join += "\n<br/> join " + final_join.get(i).getJoin() + " on  " + join3(final_join.get(i).getChild(), final_join.get(i).getParent());
            }

        }

        System.out.println("\n----This is the fnished stuff----\n");
        System.out.println(whole_join);
        System.out.println("\n===================PARTY DONE====================\n");
        all_q = "";
        int c1 = 0;
        for (Mdl_rel_query u : unit) {
            all_q += "\n  " + c1 + " => " + u.getChild() + " vs " + u.getParent();
            c1 += 1;
        }
        System.out.println("\n\nAll\n");
        System.out.println(all_q);
        System.out.println("\n\nUnordered\n");
        System.out.println(unordered);
        return whole_join;
    }

    private void get_last_item(List<Mdl_rel_query> unit, int i) {
//        prev_child = final_join.get(final_join.size() - 1).getChild();
//        prev_parent = final_join.get(final_join.size() - 1).getParent();
//        curr_child = unit.get(i).getChild();
//        curr_parent = unit.get(i).getParent();
    }

    public String join3(String curr_child, String curr_parent) {
        return curr_child + "." + curr_parent + " = " + curr_parent + "." + curr_parent + "_id";
    }

    public void Loop_all_units2(List<Mdl_rel_query> unit) {
        int c1 = 0;
        all_q = "";
        for (Mdl_rel_query u : unit) {
            all_q += "\n  " + c1 + " => " + u.getChild() + " vs " + u.getParent();
            c1 += 1;
        }
    }
}

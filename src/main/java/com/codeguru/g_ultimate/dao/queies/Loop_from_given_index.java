/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.dao.queies;

import com.codeguru.g_ultimate.models.Mdl_rel_query;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SANGWA
 */
public class Loop_from_given_index extends Loop_unordered {

    String at_what_item = "";

    public Loop_from_given_index() {
    }

    public Loop_from_given_index(List<Mdl_rel_query> all_unit, int start, int end) {
        for (int j = start; j < end; j++) {
            loop_items_given_index.add(new Mdl_rel_query(all_unit.get(j).getChild(), all_unit.get(j).getParent() + " - not found from the unordered "));
        }
    }

    private void get_remaining_items(int start, int end, List<Mdl_rel_query> all_unit, String c, String p) {
        loop_items_given_index = new ArrayList<>();
        at_what_item += "";
        for (int j = start; j < end; j++) {
            if (c.equals(all_unit.get(j).getChild()) || c.equals(all_unit.get(j).getParent())) {
                loop_items_given_index.add(new Mdl_rel_query(all_unit.get(j).getChild(), all_unit.get(j).getParent() + " - not found from the unordered "));
                super.items_not_found_down += at_what_item + "\n" + j + ". " + all_unit.get(j).getParent() + " vs " + all_unit.get(j).getParent();
            } else if (p.equals(all_unit.get(j).getChild()) || p.equals(all_unit.get(j).getParent())) {
                loop_items_given_index.add(new Mdl_rel_query(all_unit.get(j).getChild(), all_unit.get(j).getParent() + " - not found from the unordered "));
                super.items_not_found_down += at_what_item + "\n" + j + ". " + all_unit.get(j).getChild() + " vs " + all_unit.get(j).getParent();
            }

        }
    } 

    public void search_reorder_final_query(List<Mdl_rel_query> unordered, List<Mdl_rel_query> unit) {

        for (int i = 0; i < unit.size(); i++) {
            curr_child = unit.get(i).getChild();
            curr_parent = unit.get(i).getParent();
            if (i == 0) {
                prev_child = "";
                all_q += "";
            } else {
                prev_child = unit.get(i - 1).getChild();
                prev_parent = unit.get(i - 1).getParent();
            }
            if (i == 0) {
                final_join.add(new Mdl_rel_query("SELECT * FROM \n"+curr_child, curr_parent, curr_parent));
            } else if (i > 0) {
                if (!prev_child.equals(curr_child) && !prev_parent.equals(curr_child) && !prev_child.equals(curr_parent) && !prev_parent.equals(curr_parent)) {
                    
                    for (int j = 0; j < unordered.size(); j++) {
                        c = unordered.get(j).getChild();
                        p = unordered.get(j).getParent();
                        final_join.add(new Mdl_rel_query(curr_child, curr_parent, curr_parent + " -  unrelated"));
                        if ((prev_child.equals(c) || prev_parent.equals(c))) {
                            break;
                        } else if (prev_child.equals(p) || prev_parent.equals(p)) {
                            break;
                        } else if (c.equals(curr_child) || c.equals(curr_parent)) {
                            break;
                        } else if (p.equals(curr_child) || p.equals(curr_parent)) {
                            break;
                        } else {
                            at_what_item += "\n------" + c + "  " + p + "------ \n";
                            get_remaining_items(i, unit.size(), unit, curr_child,curr_parent);
                        }

                    }
                } else {
                    final_join.add(new Mdl_rel_query(curr_child, curr_parent, curr_parent));
                }
            }

        }

    }

    private void one_item_from_box(String c, String p, List<Mdl_rel_query> md, List<Mdl_rel_query> dest_box) {
        for (int i = 0; i < md.size(); i++) {
            String ch = md.get(i).getChild();
            String pa = md.get(i).getParent();
            if (c.equals(ch) || c.equals(pa) && !reps.contains(pa)) {
                dest_box.add(new Mdl_rel_query(ch, pa, pa));
            } else if (p.equals(ch) || p.equals(pa) && !reps.contains(ch)) {
                dest_box.add(new Mdl_rel_query(ch, pa, ch));
            }
        }

    }

    private void get_binary(String a, String b, String c, String d, List<Mdl_rel_query> md) {
        for (int i = 0; i < md.size(); i++) {
            if (md.get(i).getChild().equals(a) || md.get(i).getParent().equals(a) && !reps.contains(b)) {
                final_join.add(new Mdl_rel_query(a, b, b));
            }
            if (md.get(i).getChild().equals(b) || md.get(i).getParent().equals(b) && !reps.contains(a)) {
                final_join.add(new Mdl_rel_query(a, b, a));
            }
            if (md.get(i).getChild().equals(c) || md.get(i).getParent().equals(c) && !reps.contains(d)) {
                final_join.add(new Mdl_rel_query(a, b, d));
            }
            if (md.get(i).getChild().equals(d) || md.get(i).getParent().equals(d) && !reps.contains(c)) {
                final_join.add(new Mdl_rel_query(a, b, c));
            }
        }
    }
}

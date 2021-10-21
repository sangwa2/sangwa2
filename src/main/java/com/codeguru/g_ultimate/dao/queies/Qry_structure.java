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
public class Qry_structure {

    List<Mdl_rel_query> unordered = new ArrayList<>();
    ArrayList reps = new ArrayList();
    List<Mdl_rel_query> final_join = new ArrayList<>();
    String item_found = "", found_child = "", found_parent = "", unmatch = "";

    String pf_prev_child = "", pf_prev_parent = "";//these are the parent for loop previous child and the parent loop previous parent

    String pf_curr_child = "", pf_curr_parent = "";//these are the parent for loop current child and the parent loop current parent
    String whole_join = "", all_q = "";
    String overall = "";
    String curr_child = "", curr_parent = "", prev_child, prev_parent = "";
    boolean matching = false, some_items_not_found_up = false;//this is the varible that flags whenever we dont find some items from top to bottom in the second loop
    List<Mdl_rel_query> unit;

    public Qry_structure(List<Mdl_rel_query> unit, String all_q) {
        this.unit = unit;
        this.all_q = all_q;
    }

    public Qry_structure() {

    }

    public void loop_get_the_unordered(List<Mdl_rel_query> unit) {

        for (int i = 0; i < unit.size(); i++) {
            System.out.println("outside - Child: " + unit.get(i).getChild() + " parent: " + unit.get(i).getParent());
            curr_child = unit.get(i).getChild();
            curr_parent = unit.get(i).getParent();
            if (i == 0) {
                prev_child = "";
            } else {
                prev_child = unit.get(i - 1).getChild();
                prev_parent = unit.get(i - 1).getParent();
            }
            if (!prev_child.equals(curr_child) && !prev_parent.equals(curr_child) && !prev_child.equals(curr_parent) && !prev_parent.equals(curr_parent)) {
                if (i > 0) {
                    unordered.add(new Mdl_rel_query(curr_parent, curr_child));
                }
            }
        }
    }

    public void loop_order_whole_list(List<Mdl_rel_query> unit) {

        //CHECK THE WHOLE LIST
        for (int i = 0; i < unit.size(); i++) {

            curr_child = unit.get(i).getChild();
            curr_parent = unit.get(i).getParent();
            pf_curr_child = curr_child;
            pf_curr_parent = curr_parent;
            if (i == 0) {
                prev_child = "";
            } else {
                prev_child = unit.get(i - 1).getChild();
                prev_parent = unit.get(i - 1).getParent();
                pf_prev_child = prev_child;
                pf_prev_parent = prev_parent;
            }
            if (!prev_child.equals(curr_child) && !prev_parent.equals(curr_child) && !prev_child.equals(curr_parent) && !prev_parent.equals(curr_parent)) {
                loop_checkBy_prev_and_current(i, curr_child, curr_parent, prev_child, prev_parent);
            } else if (prev_child.equals(curr_child) && !prev_parent.equals(curr_child) && !prev_child.equals(curr_parent) && !prev_parent.equals(curr_parent)) {
                curr_p(curr_parent, curr_child);
            } else if (!prev_child.equals(curr_child) && prev_parent.equals(curr_child) && !prev_child.equals(curr_parent) && !prev_parent.equals(curr_parent)) {
                curr_p(curr_parent, curr_child);
            } else if (!prev_child.equals(curr_child) && !prev_parent.equals(curr_child) && prev_child.equals(curr_parent) && !prev_parent.equals(curr_parent)) {
                curr_c(curr_child, curr_parent);
            } else if (!prev_child.equals(curr_child) && !prev_parent.equals(curr_child) && !prev_child.equals(curr_parent) && prev_parent.equals(curr_parent)) {
                curr_c(curr_child, curr_parent);
            }
        }
    }

    public void loop_checkBy_prev_and_current(int i, String curr_child, String curr_parent, String prev_child, String prev_parent) {
        if (i > 0) {
            unmatch += "\n" + i + ". Child: " + curr_child + " parent: " + curr_parent;
            String c = "", p = "";

            for (int j = 0; j < unordered.size(); j++) {
                c = unordered.get(j).getChild();
                p = unordered.get(j).getParent();
                if ((prev_child.equals(c) || prev_parent.equals(c))) {
                    if ((c.equals(curr_child) || p.equals(curr_child))) {
                        p_join(p, c);
                        curr_p(curr_parent, curr_child);
                    } else if ((p.equals(curr_parent) || p.equals(curr_parent))) {
                        p_join(p, c);
                        curr_c(curr_child, curr_parent);
                    }
                    break;
                } else if (prev_child.equals(p) || prev_parent.equals(p)) {
                    if ((c.equals(curr_child) || p.equals(curr_child))) {
                        c_join(c, p);
                        curr_p(curr_parent, curr_child);
                    } else if ((p.equals(curr_parent) || p.equals(curr_parent))) {
                        c_join(c, p);
                        curr_c(curr_child, curr_parent);
                    }
                    break;
                } else if (c.equals(curr_child) && p.equals(curr_parent)) {
                    if (prev_child.equals(c) || prev_parent.equals(c) || prev_child.equals(p) || prev_parent.equals(p)) {
                        if (!reps.contains(p)) {
                            all_q += "\njoin " + p + " on " + join(c, p) + "  but prev (" + prev_child + " vs " + prev_parent + ")";
                            reps.add(p);
                            final_join.add(new Mdl_rel_query(p, c, p));
                        } else if (!reps.contains(c)) {
                            all_q += "\njoin " + c + " on " + join(c, p) + "  but prev (" + prev_child + " vs " + prev_parent + ")";;
                            reps.add(c);
                            final_join.add(new Mdl_rel_query(p, c, c));
                        }
                    } else {
                        all_q += redo_search_unmatching(prev_child, prev_parent);
                    }
                }
            }

        } else {
            if (!reps.contains(curr_child)) {
                all_q += "SELECT * FROM " + curr_parent + "\n" + " join  " + curr_child + " on " + join(curr_child, curr_parent);
                final_join.add(new Mdl_rel_query(curr_parent, curr_child, curr_parent));
            }
        }
    }

    public void revise_non_resolved() {
        for (int i = 0; i < revise().size(); i++) {
            String corresponding = "";
            for (Mdl_rel_query u : unordered) {
                if (u.getChild().equals(revise().get(i))) {
                    corresponding = u.getChild();
                    final_join.add(new Mdl_rel_query(u.getParent(), u.getChild(), corresponding));
                    break;
                } else if (u.getParent().equals(revise().get(i))) {
                    corresponding = u.getParent();
                    final_join.add(new Mdl_rel_query(u.getParent(), u.getChild(), corresponding));
                    break;
                }
            }

        }
        all_q = "";
        for (Mdl_rel_query f : final_join) {
            all_q += "\n join " + f.getJoin() + " on " + join(f.getChild(), f.getParent());
        }
    }

    public void c_join(String c, String p) {
        if (!reps.contains(c)) {
            all_q += "\njoin " + c + " on " + join(c, p);
            final_join.add(new Mdl_rel_query(p, c, c));
            reps.add(c);

        }
    }

    public void p_join(String p, String c) {
        if (!reps.contains(p)) {
            all_q += "\njoin " + p + " on " + join(c, p);
            final_join.add(new Mdl_rel_query(p, c, p));
            reps.add(p);
        }
    }

    public void curr_c(String curr_child, String curr_parent) {
        if (!reps.contains(curr_child)) {
            all_q += "\njoin " + curr_child + " on " + join(curr_child, curr_parent);
            final_join.add(new Mdl_rel_query(curr_parent, curr_child, curr_child));
            reps.add(curr_child);
        }

    }

    public void curr_p(String curr_parent, String curr_child) {
        if (!reps.contains(curr_parent)) {
            all_q += "\njoin " + curr_parent + " on " + join(curr_child, curr_parent);
            final_join.add(new Mdl_rel_query(curr_parent, curr_child, curr_parent));
            reps.add(curr_parent);
        }

    }

    public String redo_search_unmatching(String c, String p) {
        String c2 = "", p2 = "", ans = "";
        for (Mdl_rel_query mdl_rel_query : unordered) {
            c2 = mdl_rel_query.getChild();
            p2 = mdl_rel_query.getParent();
            if (c.equals(c2) || c.equals(p2)) {
                if (reps.contains(p2)) {
                    ans = "\njoin " + c2 + " on " + join(c2, p2);
                    final_join.add(new Mdl_rel_query(p, c, c2));
                    reps.add(c2);
                } else {
                    ans = "\njoin " + p2 + " on " + join(c2, p2);
                    final_join.add(new Mdl_rel_query(p, c, p2));
                    reps.add(p2);
                }
                break;
            } else if (p.equals(c2) || p.equals(p2)) {
                if (reps.contains(c2)) {
                    ans = "\njoin " + p2 + " on " + join(c2, p2);
                    final_join.add(new Mdl_rel_query(p, c, p2));
                    reps.add(p2);
                } else {
                    ans = "\njoin " + c2 + " on " + join(c2, p2);
                    final_join.add(new Mdl_rel_query(p, c, c2));
                    reps.add(c2);
                }
                break;
            }
        }
        return ans;
    }

    public ArrayList revise() {
        ArrayList curr = new ArrayList<>();
        ArrayList unresoved = new ArrayList<>();
        boolean found = false, found2 = false;
        for (Mdl_rel_query u : unordered) {

            for (Mdl_rel_query f : final_join) {
                if (u.getParent().equals(f.getParent()) || u.getParent().equals(f.getChild()) || u.getParent().equals(f.getJoin())) {
                    found = true;
                }
            }
            if (!found && !unresoved.contains(u.getParent())) {
                curr.add(u.getParent());
                unresoved.add(u.getParent());
            }

            for (Mdl_rel_query f : final_join) {
                if (u.getChild().equals(f.getChild()) || u.getChild().equals(f.getParent()) || u.getChild().equals(f.getJoin())) {
                    found2 = true;
                }
            }
            if (!found2 && !unresoved.contains(u.getChild())) {

                curr.add(u.getChild());
                unresoved.add(u.getChild());
            }

        }

        return curr;

    }

    public String join(String curr_child, String curr_parent) {
        return curr_child + "." + curr_parent + " = " + curr_parent + "." + curr_parent + "_id";
    }

}

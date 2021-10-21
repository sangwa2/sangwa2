/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.dao.query;

import com.codeguru.g_ultimate.dao.queies.*;
import com.codeguru.g_ultimate.models.Mdl_rel_query;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SANGWA
 */
public class Initials {

    List<Mdl_rel_query> unordered = new ArrayList<>();
    ArrayList reps = new ArrayList();
    List<Mdl_rel_query> final_join = new ArrayList<>();
    String item_found = "", found_child = "", found_parent = "",
            items_not_found_down = "", unmatch = "";
    int cc, pp;
    String whole_join = "", all_q = "";

    String curr_child = "", curr_parent = "", prev_child, prev_parent = "";
    boolean matching = false, some_items_not_found_up = false;//this is the varible that flags whenever we dont find some items from top to bottom in the second loop

    List<Mdl_rel_query>   loop_items_given_index, cross_join;

    public List<Mdl_rel_query> getUnordered() {
        return unordered;
    }

    public void setUnordered(List<Mdl_rel_query> unordered) {
        this.unordered = unordered;
    }

    public ArrayList getReps() {
        return reps;
    }

    public void setReps(ArrayList reps) {
        this.reps = reps;
    }

    public List<Mdl_rel_query> getFinal_join() {
        return final_join;
    }

    public void setFinal_join(List<Mdl_rel_query> final_join) {
        this.final_join = final_join;
    }

    public String getItem_found() {
        return item_found;
    }

    public void setItem_found(String item_found) {
        this.item_found = item_found;
    }

    public String getFound_child() {
        return found_child;
    }

    public void setFound_child(String found_child) {
        this.found_child = found_child;
    }

    public String getFound_parent() {
        return found_parent;
    }

    public void setFound_parent(String found_parent) {
        this.found_parent = found_parent;
    }

    public String getUnmatch() {
        return unmatch;
    }

    public void setUnmatch(String unmatch) {
        this.unmatch = unmatch;
    }

    public String getWhole_join() {
        return whole_join;
    }

    public void setWhole_join(String whole_join) {
        this.whole_join = whole_join;
    }

    public String getAll_q() {
        return all_q;
    }

    public void setAll_q(String all_q) {
        this.all_q = all_q;
    }

    public String getCurr_child() {
        return curr_child;
    }

    public void setCurr_child(String curr_child) {
        this.curr_child = curr_child;
    }

    public String getCurr_parent() {
        return curr_parent;
    }

    public void setCurr_parent(String curr_parent) {
        this.curr_parent = curr_parent;
    }

    public String getPrev_child() {
        return prev_child;
    }

    public void setPrev_child(String prev_child) {
        this.prev_child = prev_child;
    }

    public String getPrev_parent() {
        return prev_parent;
    }

    public void setPrev_parent(String prev_parent) {
        this.prev_parent = prev_parent;
    }

    public boolean isMatching() {
        return matching;
    }

    public void setMatching(boolean matching) {
        this.matching = matching;
    }

    public boolean isSome_items_not_found_up() {
        return some_items_not_found_up;
    }

    public void setSome_items_not_found_up(boolean some_items_not_found_up) {
        this.some_items_not_found_up = some_items_not_found_up;
    }

  
 
    public void c_join(String c, String p, String prev_curr_c, String prev_curr_p) {
        if (!reps.contains(c)) {
            all_q += "\njoin " + c + " on " + join(c, p);
            final_join.add(new Mdl_rel_query(p, c, ".." + c));
            reps.add(c);

            prev_curr_c = c;
            prev_curr_p = p;
        }
    }

    public void p_join(String p, String c, String prev_curr_c, String prev_curr_p) {
        if (!reps.contains(p)) {
            all_q += "\njoin " + p + " on " + join(c, ".." + p);
            final_join.add(new Mdl_rel_query(p, c, ".." + p));
            reps.add(p);
            prev_curr_c = c;
            prev_curr_p = p;
        }
    }

    public void curr_c(String curr_child, String curr_parent, boolean signal, String prevc, String prevp) {
        if (!reps.contains(curr_child)) {
            all_q += "\n..join " + curr_child + " on " + join(curr_child, curr_parent);
            final_join.add(new Mdl_rel_query(curr_parent, curr_child, "curr.." + curr_child + " ---- prev " + "(" + prevc + " vs " + prevp + ")"));
            reps.add(curr_child);
            signal = false;
        }

    }

    public void curr_p(String curr_parent, String curr_child, boolean signal, String prevc, String prevp) {
        if (!reps.contains(curr_parent)) {
            all_q += "\n..join " + curr_parent + " on " + join(curr_child, curr_parent);
            final_join.add(new Mdl_rel_query(curr_parent, curr_child, "curr.." + curr_parent + " ---- prev " + "(" + prevc + " vs " + prevp + ")"));
            reps.add(curr_parent);
            signal = false;
        }

    }

    public void curr_p(String curr_parent, String curr_child) {
        if (!reps.contains(curr_parent)) {
            all_q += "\n..join " + curr_parent + " on " + join(curr_child, curr_parent);
            final_join.add(new Mdl_rel_query(curr_parent, curr_child, "curr..s " + curr_parent + " --- prev(" + prev_child + " vs " + prev_parent + ")"));
            prev_child = final_join.get(final_join.size() - 1).getChild();
            prev_parent = final_join.get(final_join.size() - 1).getParent();

            reps.add(curr_parent);
        } else if (!reps.contains(curr_child)) {
            all_q += "\n..join " + curr_parent + " on " + join(curr_child, curr_parent);
            final_join.add(new Mdl_rel_query(curr_parent, curr_child, "curr..s " + curr_child + " --- prev(" + prev_child + " vs " + prev_parent + ")"));
            prev_child = final_join.get(final_join.size() - 1).getChild();
            prev_parent = final_join.get(final_join.size() - 1).getParent();
            reps.add(curr_parent);
        }

    }

    public void curr_c(String curr_child, String curr_parent) {
        if (!reps.contains(curr_child)) {
            all_q += "\n..join " + curr_child + " on " + join(curr_child, curr_parent);
            final_join.add(new Mdl_rel_query(curr_parent, curr_child, "curr..s " + curr_child));
            prev_child = final_join.get(final_join.size() - 1).getChild();
            prev_parent = final_join.get(final_join.size() - 1).getParent();

            reps.add(curr_child);
        } else if (!reps.contains(curr_parent)) {
            all_q += "\n..join " + curr_parent + " on " + join(curr_child, curr_parent);
            final_join.add(new Mdl_rel_query(curr_parent, curr_child, "curr..s " + curr_parent));
            prev_child = final_join.get(final_join.size() - 1).getChild();
            prev_parent = final_join.get(final_join.size() - 1).getParent();

            reps.add(curr_parent);
        }

    }

    public String join(String curr_child, String curr_parent) {
        return curr_child + "." + curr_parent + " = " + curr_parent + "." + curr_parent + "_id";
    }

    /**
     * This is the method that searches the item from unordered and compares it
     * to the arrayList
     *
     * @param i this is the index of the loop
     * @param c this is the child of the mdl_rel_query
     * @param p this is the parent of the mdl_rel_query
     * @param fl this is the final loop variable
     * @return this is the returned string
     */
    private String redo_search_unordered_last_item(String c, String p, String curr_c, String curr_p, List<Mdl_rel_query> fl) {
        String c2 = "", p2 = "", ans = "";
        for (int j = 0; j < fl.size(); j++) {
            c2 = fl.get(j).getChild();// this is the child of the final_join, it (fl) my be changed based on the further invocations
            p2 = fl.get(j).getParent();//This is the parent of the final_join, it (fl) my be changed based on the further invocations
            if ((c2.equals(c) || c2.equals(p)) && !reps.contains(p)) {
                all_q += "\n join " + p + " on " + join(c, p);
                fl.add(new Mdl_rel_query(p, c, p));
                reps.add(p);

            } else if ((p2.equals(c) || p2.equals(p)) && !reps.contains(c)) {
                all_q += "\n join " + p + " on " + join(c, p);
                fl.add(new Mdl_rel_query(p, c, c));
                reps.add(c);

            } else if ((curr_p.equals(c2) || curr_p.equals(p2)) && !reps.contains(curr_c)) {
                all_q += "\n join " + curr_c + " on " + join(curr_c, curr_p);
                fl.add(new Mdl_rel_query(curr_p, curr_c, curr_c));
                reps.add(curr_c);
            } else if (!reps.contains(curr_p)) {
                all_q += "\n join " + curr_p + " on " + join(curr_c, curr_p);
                fl.add(new Mdl_rel_query(curr_p, curr_c, curr_p));
                reps.add(curr_p);
            }

        }
        return ans;

    }

    public String getItems_not_found_down() {
        return items_not_found_down;
    }

    public void setItems_not_found_down(String items_not_found_down) {
        this.items_not_found_down = items_not_found_down;
    }

}

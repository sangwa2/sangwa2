package com.codeguru.g_ultimate.dao;

import com.codeguru.g_ultimate.models.Mdl_f_end_layout;
import com.codeguru.g_ultimate.models.Mdl_f_layout_unit_template;
import java.util.List;

/**
 *
 * @author SANGWA
 */
public interface Dao_f_layout_unit_template {

    public List<Mdl_f_layout_unit_template> all_f_layout_unit_templates();


    public void add_f_layout_unit_template(Mdl_f_layout_unit_template f_layout_unit_template);

    public void delete_f_layout_unit_template(int f_layout_unit_template);

    public void update_f_layout_unit_template(Mdl_f_layout_unit_template f_layout_unit_template);

    public Mdl_f_layout_unit_template find_f_layout_unit_templateBy_id(int f_layout_unit_template);

    public int get_last_f_layout_unit_template();

    public int layout_by_structure(int structure);
}

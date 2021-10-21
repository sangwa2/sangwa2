/*
 * Implementing the service of f_layout_unit_template.
 *  .
 */
package com.codeguru.g_ultimate.service;

import com.codeguru.g_ultimate.dao.Dao_f_layout_unit_template;
import com.codeguru.g_ultimate.models.Mdl_f_layout_unit_template;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SANGWA
 */
@Service
public class Srv_f_layout_unit_template_impl implements Srv_f_layout_unit_template {

    Dao_f_layout_unit_template dao_f_layout_unit_template;

    @Autowired
    public void setDao_f_layout_unit_template(Dao_f_layout_unit_template dao_f_layout_unit_template) {
        this.dao_f_layout_unit_template = dao_f_layout_unit_template;
    }

    @Override
    public List<Mdl_f_layout_unit_template> all_f_layout_unit_templates() {
        return dao_f_layout_unit_template.all_f_layout_unit_templates();
    }

    @Override
    public void add_f_layout_unit_template(Mdl_f_layout_unit_template f_layout_unit_template) {
        dao_f_layout_unit_template.add_f_layout_unit_template(f_layout_unit_template);
    }

    @Override
    public void delete_f_layout_unit_template(int f_layout_unit_template) {
        dao_f_layout_unit_template.delete_f_layout_unit_template(f_layout_unit_template);
    }

    @Override
    public void update_f_layout_unit_template(Mdl_f_layout_unit_template f_layout_unit_template) {
        dao_f_layout_unit_template.update_f_layout_unit_template(f_layout_unit_template);
    }

    @Override
    public Mdl_f_layout_unit_template find_f_layout_unit_templateBy_id(int f_layout_unit_template) {
        return dao_f_layout_unit_template.find_f_layout_unit_templateBy_id(f_layout_unit_template);
    }

    @Override
    public int get_last_f_layout_unit_template() {
        return dao_f_layout_unit_template.get_last_f_layout_unit_template();
    }

    @Override
    public int layout_by_structure(int structure) {
        return dao_f_layout_unit_template.layout_by_structure(structure);
    }

}

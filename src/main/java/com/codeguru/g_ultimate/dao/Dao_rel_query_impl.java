/*
 * Interface implementation of Rel_query.
 * @For Author SANGWA 
 sangwa22@gmail.com.
 */
package com.codeguru.g_ultimate.dao;

import com.codeguru.g_ultimate.dao.queies.Loop_all_units;
import com.codeguru.g_ultimate.dao.queies.Loop_from_given_index;
import com.codeguru.g_ultimate.dao.queies.Qry_structure;
import com.codeguru.g_ultimate.dao.queies.Loop_unordered;
import com.codeguru.g_ultimate.dao.query.Main_loop;
import com.codeguru.g_ultimate.models.Mdl_rel_query;
import com.codeguru.g_ultimate.models.Mdl_relationships;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SANGWA
 */
@Repository
public class Dao_rel_query_impl extends Loop_unordered implements Dao_rel_query {

    List<Mdl_rel_query> unordered = new ArrayList<>();
    ArrayList reps = new ArrayList();
    List<Mdl_rel_query> final_join = new ArrayList<>();
    String item_found = "", found_child = "", found_parent = "", unmatch = "";

    String pf_prev_child = "", pf_prev_parent = "";//these are the parent for loop previous child and the parent loop previous parent

    String pf_curr_child = "", pf_curr_parent = "";//these are the parent for loop current child and the parent loop current parent
    String whole_join = "", all_q = "";
    String overall = "";
    boolean matching = false, some_items_not_found_up = false;//this is the varible that flags whenever we dont find some items from top to bottom in the second loop
    Loop_from_given_index q = new Loop_from_given_index();

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    Dao_Joins dj = new Dao_Joins();

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Mdl_rel_query> all_rel_querys() {
        String sql = "SELECT  rel_query.rel_query_idrel_query.unit ,rel_query.tuple ,rel_query.rel_query_category    FROM rel_query";
        List<Mdl_rel_query> list = namedParameterJdbcTemplate.query(sql, getParameterByModel(null), new rel_query_mapper());

        return list;

    }

    @Override
    public String add_rel_query(Mdl_rel_query rel_query, String filter) {
        String list = "";
        System.out.println("The filrer: " + filter);

        if (this.get_tuple_by_unitTuple(rel_query.getUnit(), rel_query.getTuple())) {//The unit and tuple exists in the db
            System.out.println("The unit and tuple exists!.. nothing done");
//            list="exits, sangwa you have to try and give another functionality if possible";
            list = " ";
            List<Mdl_rel_query> unit = this.query(rel_query.getStructure(), filter);
            list = new Main_loop().get_query(unit);
        } else {
            String sql = " INSERT INTO rel_query (unit,tuple,rel_query_category) VALUES  (:unit ,:tuple ,:rel_query_category)";
            namedParameterJdbcTemplate.update(sql, getParameterByModel(rel_query));

            List<Mdl_rel_query> unit = this.query(rel_query.getStructure(), filter);
            list = new Main_loop().get_query(unit);
        }
        return list;
    }

    /**
     * Where we have finished making the query but then there are other
     * unresolved stuff!!, this goes back in he unmatching items and do some
     * double check
     */
    int tot_unmatching_items(int structure, String filter) {
        int c = 0;
        List<Mdl_rel_query> unit = this.query(structure, filter);
        String curr_child = "", curr_parent = "", prev_child, prev_parent = "", next_parent = "", next_child = "";
        for (int i = 0; i < unit.size(); i++) {
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
                    c += 1;
                }
            }
        }
        return c;
    }

    private List<Mdl_rel_query> query(int structure, String filter) {
        List<Mdl_rel_query> parents = null;
        try {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("structure1", structure);
            parents = namedParameterJdbcTemplate.query(this.query1(filter), params, new parent_child_rel_query_mapper());
            //Get the related children
        } catch (EmptyResultDataAccessException e) {
            System.out.println("Error in Dao_rel_query_impl" + e.toString());
        }
        return parents;
    }

    private List<Mdl_rel_query> get_query_by_type(String type) throws DataAccessException {
        List<Mdl_rel_query> parents = null;
        try {
            //Get children of each parent
            String parents_sql = "SELECT  rel_query.rel_query_id,rel_query.unit ,rel_query.tuple ,rel_query.rel_query_category, unit.name   FROM rel_query "
                    + " join rel_query_category on rel_query_category.rel_query_category_id=rel_query.rel_query_category "
                    + " join unit on unit.unit_id=rel_query.unit"
                    + "  where rel_query_category.name=:type ";
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("type", type);
            parents = namedParameterJdbcTemplate.query(parents_sql, params, new rel_query_mapper());
            //Get the related children
        } catch (EmptyResultDataAccessException e) {
            System.out.println("Empty : " + type + " " + e.toString() + "  type: " + type);
        }
        return parents;
    }

    public List<Mdl_rel_query> top_parents() throws DataAccessException {
        List<Mdl_rel_query> parents = null;
        try {
            //Get children of each parent
            String parents_sql = "select  u_p.name as parent ,  u_c.name as child ,"
                    + "    rel_query.rel_query_id, rel_query.unit,  rel_query.tuple,  rel_query.rel_query_category  \n"
                    + "    from relationships \n"
                    + "    join rel_query on rel_query.unit= relationships.parent_id\n"
                    + "    join rel_query_category on rel_query_category.rel_query_category_id=rel_query.rel_query_category\n"
                    + "    join unit as u_p on u_p.unit_id=rel_query.unit\n"
                    + "    join child on child.child_id=relationships.child_id\n"
                    + "    join unit as u_c on u_c.unit_id=child.unit_id\n"
                    + "    join tuple on tuple.tuple_id=rel_query.tuple\n"
                    + "    where relationships.parent_id  not in (select relationships.child_id from relationships) \n"
                    + "	   and rel_query_category.name='parent'\n"
                    + "    and relationships.child_id in (select child.child_id from child)\n"
                    + "      \n"
                    + "    order by rel_query.rel_query_id ";
            MapSqlParameterSource params = new MapSqlParameterSource();
            parents = namedParameterJdbcTemplate.query(parents_sql, getParameterByModel(null), new Dao_rel_query_impl.rel_query_mapper());
            //Get the related children
        } catch (EmptyResultDataAccessException e) {
            System.out.println("Error getting top parent: " + new Dao_Joins().getClass().getName() + "\n\n" + e.toString());
        }
        return parents;
    }

    @Override
    public void delete_rel_query(int rel_query) {
        String sql = "DELETE from rel_query where rel_query_id=:rel_query_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(new Mdl_rel_query(rel_query)));
    }

    @Override
    public void update_rel_query(Mdl_rel_query rel_query) {
        String sql = "UPDATE child SET   rel_query_id_id= : rel_query_id_idunit_id= : unit_id ,:tuple_id= : tuple_id ,:rel_query_category_id= : rel_query_category_id  WHERE rel_query_id =:rel_query_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(rel_query));
    }

    @Override
    public Mdl_rel_query find_rel_queryBy_id(int rel_query) {
        String sql = "SELECT * FROM rel_query where rel_query_id=:rel_query_id";
        return namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(new Mdl_rel_query(rel_query)), new rel_query_mapper());
    }

    @Override
    public int get_last_rel_query() {
        String sql = "select rel_query_id from rel_query order by rel_query_id desc limit 1";
        int n = namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(null), Integer.class);
        return n;
    }

    @Override
    public void delete_rel_query_by_unit_tuple(int unit, int tuple) {
        String sql = "DELETE from rel_query where unit=:unit and tuple=:tuple";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("unit", unit);
        params.addValue("tuple", tuple);
        namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public boolean get_tuple_by_unitTuple(int unit_id, int tuple_id) {
        try {
            String sql = "SELECT rel_query.rel_query_id FROM rel_query where rel_query.unit=:unit and rel_query.tuple=:tuple";
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("unit", unit_id);
            params.addValue("tuple", tuple_id);
            return namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class) > 0;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }

    }

    @Override
    public List<Mdl_rel_query> get_parents() {
        return get_query_by_type("parent");
    }

    private String query1(String filter) {
        String sql = "  select distinct p.name as parent, c.name as child\n"
                + "  from relationship_count as r\n"
                + "  join tuple on tuple.tuple_id=r.tuple_id\n"
                + "  join unit on unit.unit_id=tuple.unit\n"
                + "  join structure as s on s.structure_id=unit.structure\n"
                + "  join rel_query  as rel on rel.unit=unit.unit_id\n"
                + "  left  join relationships as r2 on r2.parent_id=unit.unit_id\n"
                + "  left join unit p  on p.unit_id=r2.parent_id   \n"
                + "  left join child   on child.child_id=r2.child_id\n"
                + "  join unit c  on c.unit_id=child.unit_id\n"
                + "  where s.structure_id=:structure1"
                + " and p.name in (" + filter + ")  and c.name in  (" + filter + ")  \n"
                + "  order by r.number_children desc, parent asc, child asc";

        return " select  p.name as parent, c.name as child\n"
                + " from relations as r\n"
                + " join unit p on p.unit_id=r.parent_unit_id\n"
                + " join unit c on c.unit_id=r.child_unit_id\n"
                + " where p.name in (" + filter + ")  and c.name in  (" + filter + ")  \n";

    }

    @Override
    public List<Object> children_for_each_parent() {

        return null;
    }

    private SqlParameterSource getParameterByModel(Mdl_rel_query mdl_rel_query) {
        MapSqlParameterSource paramsource = new MapSqlParameterSource();
        if (mdl_rel_query != null) {
            paramsource.addValue("rel_query_id", mdl_rel_query.getRel_query_id());
            paramsource.addValue("unit", mdl_rel_query.getUnit());
            paramsource.addValue("tuple", mdl_rel_query.getTuple());
            paramsource.addValue("rel_query_category", mdl_rel_query.getRel_query_category());
//            paramsource.addValue("name", mdl_rel_query.getName());
            paramsource.addValue("parent", mdl_rel_query.getParent());
            paramsource.addValue("child", mdl_rel_query.getChild());
        }
        return paramsource;
    }

    @Override
    public Mdl_rel_query get_rel_query_by_tupleName(String tuple_name) {
        String sql = "   SELECT * FROM rel_query where rel_query.unit=:unit ";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("unit", tuple_name);
        return namedParameterJdbcTemplate.queryForObject(sql, params, new rel_query_mapper());
    }

    private static final class rel_query_mapper implements RowMapper<Mdl_rel_query> {

        @Override
        public Mdl_rel_query mapRow(ResultSet rs, int i) throws SQLException {
            Mdl_rel_query mdl_rel_query = new Mdl_rel_query();
            mdl_rel_query.setRel_query_id(rs.getInt("rel_query_id"));
            mdl_rel_query.setUnit(rs.getInt("unit"));
            mdl_rel_query.setTuple(rs.getInt("tuple"));
//            mdl_rel_query.setName(rs.getString("name"));
            mdl_rel_query.setRel_query_category(rs.getInt("rel_query_category"));
            mdl_rel_query.setParent(rs.getString("parent"));
            mdl_rel_query.setChild(rs.getString("child"));

            return mdl_rel_query;
        }

    }

    private static final class parent_child_rel_query_mapper implements RowMapper<Mdl_rel_query> {

        @Override
        public Mdl_rel_query mapRow(ResultSet rs, int i) throws SQLException {
            Mdl_rel_query mdl_rel_query = new Mdl_rel_query();

            mdl_rel_query.setParent(rs.getString("parent"));
            mdl_rel_query.setChild(rs.getString("child"));

            return mdl_rel_query;
        }

    }

    final class Make_query {

    }
}

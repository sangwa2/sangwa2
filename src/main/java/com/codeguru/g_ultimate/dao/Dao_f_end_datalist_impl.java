/*
 * Interface implementation of F_end_datalist.
 * @For Author SANGWA 
 sangwa22@gmail.com.
 */
package com.codeguru.g_ultimate.dao;

import com.codeguru.g_ultimate.models.Mdl_f_end_datalist;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
public class Dao_f_end_datalist_impl implements Dao_f_end_datalist {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Mdl_f_end_datalist> all_f_end_datalists() {
        String sql = "SELECT  f_end_datalist.f_end_datalist_idf_end_datalist.query ,f_end_datalist.data_list_template ,f_end_datalist.layout    FROM f_end_datalist";
        List<Mdl_f_end_datalist> list = namedParameterJdbcTemplate.query(sql, getParameterByModel(null), new f_end_datalist_mapper());
        return list;
    }

    @Override
    public void add_f_end_datalist(Mdl_f_end_datalist f_end_datalist) {
        String sql =  " INSERT INTO f_end_datalist (f_end_datalist_idquery,data_list_template,layout) VALUES  (:f_end_datalist_id:query ,:data_list_template ,:layout)";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(f_end_datalist));
    }

    @Override
    public void delete_f_end_datalist(int f_end_datalist) {
        String sql = "DELETE from f_end_datalist where f_end_datalist_id=:f_end_datalist_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(new Mdl_f_end_datalist(f_end_datalist)));
    }

    @Override
    public void update_f_end_datalist(Mdl_f_end_datalist f_end_datalist) {
        String sql = "UPDATE child SET   f_end_datalist_id_id= : f_end_datalist_id_idquery_id= : query_id ,:data_list_template_id= : data_list_template_id ,:layout_id= : layout_id  WHERE f_end_datalist_id =:f_end_datalist_id";
        namedParameterJdbcTemplate.update(sql, getParameterByModel(f_end_datalist));
    }

    @Override
    public Mdl_f_end_datalist find_f_end_datalistBy_id(int f_end_datalist) {
        String sql = "SELECT * FROM f_end_datalist where f_end_datalist_id=:f_end_datalist_id";
        return namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(new Mdl_f_end_datalist(f_end_datalist)), new f_end_datalist_mapper());
    }

    @Override
    public int get_last_f_end_datalist() {
       String sql="select f_end_datalist_id from f_end_datalist order by f_end_datalist_id desc limit 1";
        int n= namedParameterJdbcTemplate.queryForObject(sql, getParameterByModel(null), Integer.class);
        return n;
    }
    
    
    
    
    

    private SqlParameterSource getParameterByModel(Mdl_f_end_datalist mdl_f_end_datalist) {
        MapSqlParameterSource paramsource = new MapSqlParameterSource();
        if (mdl_f_end_datalist != null) {
            paramsource.addValue("f_end_datalist_id", mdl_f_end_datalist.getF_end_datalist_id());
            paramsource.addValue("query", mdl_f_end_datalist.getQuery());
            paramsource.addValue("data_list_template", mdl_f_end_datalist.getData_list_template());
            paramsource.addValue("layout", mdl_f_end_datalist.getLayout());
        }
        return paramsource;
    }

    private static final class f_end_datalist_mapper implements RowMapper<Mdl_f_end_datalist> {

        @Override
        public Mdl_f_end_datalist mapRow(ResultSet rs, int i) throws SQLException {
            Mdl_f_end_datalist mdl_f_end_datalist = new Mdl_f_end_datalist();
            mdl_f_end_datalist.setF_end_datalist_id(rs.getInt("f_end_datalist_id"));
            mdl_f_end_datalist.setQuery(rs.getInt("query"));
            mdl_f_end_datalist.setData_list_template(rs.getInt("data_list_template"));
            mdl_f_end_datalist.setLayout(rs.getInt("layout"));

            return mdl_f_end_datalist;
        }

    }
}


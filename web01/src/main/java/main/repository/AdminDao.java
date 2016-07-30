package main.repository;

import main.domain.Admin;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * jdbcTemplate的使用
 */
@Repository
public class AdminDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    public Admin getById(long id){
        String sql = "select *from sys_admin where id=?";
        RowMapper<Admin> rowMapper = new BeanPropertyRowMapper<Admin>(Admin.class);
        return jdbcTemplate.queryForObject(sql,rowMapper,id);
    }
}

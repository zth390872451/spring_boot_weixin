/*
package main.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

*/
/**
 * Created by Administrator on 2016/7/27.
 *权限的CRUD操作
 *//*

@Component
public class AuthorityRepository {

    public static final String SQL_INSERT = "insert into sys_role_authority";
    public static final String SQL_QUERY_ONE = "SELECT * FROM sys_role_authority?";
    public static final String SQL_QUERY_ALL = "SELECT * FROM sys_role_authority WHERE holder_id = ?";

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getByHolderId(Long holderId) {
        List<Map<String, Object>> authorityList = new ArrayList<Map<String, Object>>();
        authorityList = jdbcTemplate.queryForList(SQL_QUERY_ALL);
        return authorityList;
    }




}
*/

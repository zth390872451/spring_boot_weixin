package main.repository;

import main.domain.DataSourceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2016/8/4 0004.
 */
@Service
public class DataSourceEntityDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 不指定数据源:使用默认数据源
     * @return
     */
    public List<DataSourceEntity> getList(){
        String sql = "select *from datasourceentity";
       return (List<DataSourceEntity>) jdbcTemplate.queryForObject(sql, new RowMapper<DataSourceEntity>() {
            @Override
            public DataSourceEntity mapRow(ResultSet resultSet, int i) throws SQLException {
                DataSourceEntity dataSourceEntity = new DataSourceEntity();
                dataSourceEntity.setId(resultSet.getLong("id"));
                dataSourceEntity.setName(resultSet.getString("name"));
                return dataSourceEntity;
            }
        });
    }

    /**
     * 指定数据源：
     * 在service层进行指定
     * @return
     */
    public List<DataSourceEntity> getListByDs1(){
        /* 这张表示复制的主库到ds1的，在ds中并没有此表.
         * 需要自己自己进行复制，不然会报错：Table 'test1.demo1' doesn't exist
         */
        String sql = "select id,name from datasourceentity";
        List<DataSourceEntity> objectList = jdbcTemplate.query(sql, new DataSourceEntityMapper());
        return objectList;
 }



   private class DataSourceEntityMapper implements RowMapper<DataSourceEntity>{
       @Override
       public DataSourceEntity mapRow(ResultSet resultSet, int i) throws SQLException {
           DataSourceEntity dataSourceEntity = new DataSourceEntity();
           dataSourceEntity.setId(resultSet.getLong("id"));
           dataSourceEntity.setName(resultSet.getString("name"));
          return dataSourceEntity;
       }
   }
}

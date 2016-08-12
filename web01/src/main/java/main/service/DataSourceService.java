package main.service;

import main.conf.datasource.TargetDataSource;
import main.domain.DataSourceEntity;
import main.repository.DataSourceEntityDao;
import main.repository.DataSourceEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2016/8/4 0004.
 */
@Service
public class DataSourceService {
    @Resource
    private DataSourceEntityDao dataSourceEntityDao;

    @Autowired
    private DataSourceEntityRepository dataSourceEntityRepository;
    /**
     * 不指定数据源 使用默认数据源
     * @return
     */
    public List<DataSourceEntity> getList(){
        return dataSourceEntityRepository.findAll();
//        return dataSourceEntityDao.getList();
    }

    @TargetDataSource("ds1")
    public List<DataSourceEntity> getListByDs1(){
        return dataSourceEntityRepository.findAll();
//        return dataSourceEntityDao.getListByDs1();
    }
}

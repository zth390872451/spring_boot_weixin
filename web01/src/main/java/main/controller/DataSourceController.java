package main.controller;

import main.domain.DataSourceEntity;
import main.service.DataSourceService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2016/8/4 0004.
 * 多数据源 代码测试
 */
@RestController
@RequestMapping("datasource")
public class DataSourceController {

    @Resource
    private DataSourceService dataSourceService;

    @RequestMapping("default")
    public String defaultDataSource(){
        List<DataSourceEntity> list = dataSourceService.getList();
        for (int i = 0; i < list.size(); i++) {
            System.out.println("list = " + list.get(i).getName());
        }
        return "从默认数据源得到的list的大小为 "+list.size();
    }


    @RequestMapping("custom")
    public String customDataSource(){
        List<DataSourceEntity> list = dataSourceService.getListByDs1();
        for (int i = 0; i < list.size(); i++) {
            System.out.println("list["+i+"] = name " + list.get(i).getName());
        }
        return "从指定数据源得到的list的大小为 "+list.size();
    }

}

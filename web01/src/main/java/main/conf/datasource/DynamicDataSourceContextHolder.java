package main.conf.datasource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/3 0003.
 * 动态数据源上下文
 */
public class DynamicDataSourceContextHolder {
    /**
     * ThreadLocal为每个线程存储一个String类型变量
     */
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
    /**
     * 管理所有的数据源的id;
    * 主要是判断数据源是否存在;
     */
    public static List<String> dataSourceIds = new ArrayList<String>();

    /**
     * 设置当前线程 数据源类型
     */
    public static void setDataSourceType(String dataSourceType){
        contextHolder.set(dataSourceType);
    }

    /**
     * @return 当前线程使用的数据源
     */
    public static String getDataSourceType(){
        return contextHolder.get();
    }

    /**
     *  移除当前线程使用的数据源
     */
    public static void clearDataSourceType(){
        contextHolder.remove();
    }

    /**
     * 判断指定的 DataSource 是否存在
     * @param dataSourceId
     * @return
     */
    public static boolean containsDataSource(String dataSourceId){
        return dataSourceIds.contains(dataSourceId);
    }



}

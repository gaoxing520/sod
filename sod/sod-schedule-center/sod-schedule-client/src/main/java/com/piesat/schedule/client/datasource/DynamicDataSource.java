package com.piesat.schedule.client.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: sod
 * @description:
 * @author: zzj
 * @create: 2020-02-07 10:09
 **/
public class DynamicDataSource extends AbstractRoutingDataSource {
    public static Map<Object, Object> _targetDataSources = new HashMap();
    @Override
    protected Object determineCurrentLookupKey() {
        String dataSourceName = DataSourceContextHolder.getDataSource();
        if(null!=dataSourceName){
              this.selectDataSource(dataSourceName);
        }
        return dataSourceName;
    }
    private void selectDataSource(String dataSourceName) {
        Object obj = _targetDataSources.get(dataSourceName);
        if (obj != null) {
            return;
        }
        DataSource dataSource = this.getDataSource(dataSourceName);
        if (null != dataSource) {
            this.setDataSource(dataSourceName, dataSource);
        }

    }
    @Override
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        _targetDataSources = targetDataSources;
        super.setTargetDataSources(_targetDataSources);
        afterPropertiesSet();
    }

    private void addTargetDataSource(String key, DataSource dataSource) {
        _targetDataSources.put(key, dataSource);
        this.setTargetDataSources(_targetDataSources);
    }

    private DataSource createDataSource(String driverClassName, String url,
                                        String username, String password) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    private void setDataSource(String dataSourceName, DataSource dataSource) {
        this.addTargetDataSource(dataSourceName, dataSource);
        //DataSourceContextHolder.setDataSource(dataSourceName);
    }
    private DataSource getDataSource(String dataSourceName) {
        String driverClassName="com.xugu.cloudjdbc.Driver";
        String userName="USR_SOD";
        String url="jdbc:xugu://10.1.6.117:5138/BABJ_SMDB?char_set=utf8&compatibleoracle=false";
        String password="Pnmic_qwe123";
        DataSource dataSource = this.createDataSource(
                driverClassName, url, userName, password);
        return dataSource;
    }
}

package org.jeecgframework.core.extend.datasource;
 import java.util.Map;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.jdbc.datasource.lookup.DataSourceLookup;
public class DynamicDataSource extends AbstractRoutingDataSource{


public void setDefaultTargetDataSource(Object defaultTargetDataSource){
    super.setDefaultTargetDataSource(defaultTargetDataSource);
}


public Object determineCurrentLookupKey(){
    DataSourceType dataSourceType = DataSourceContextHolder.getDataSourceType();
    return dataSourceType;
}


public void setTargetDataSources(Map targetDataSources){
    super.setTargetDataSources(targetDataSources);
}


public void setDataSourceLookup(DataSourceLookup dataSourceLookup){
    super.setDataSourceLookup(dataSourceLookup);
}


}
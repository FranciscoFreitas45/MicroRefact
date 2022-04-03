package com.kingen.util;
 import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
public class DynamicDataSource extends AbstractRoutingDataSource{


@Override
public Object determineCurrentLookupKey(){
    return DBContextHolder.getDBType();
}


}
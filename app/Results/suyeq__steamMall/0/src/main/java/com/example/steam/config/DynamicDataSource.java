package com.example.steam.config;
 import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
public class DynamicDataSource extends AbstractRoutingDataSource{

 public  Logger logger;

 private  List<Object> slaveDataSources;

 private  AtomicInteger squence;


public void setSlaveDataSources(List<Object> slaveDataSources){
    this.slaveDataSources = slaveDataSources;
}


@Override
public void afterPropertiesSet(){
    super.afterPropertiesSet();
}


@Override
public Object determineCurrentLookupKey(){
    Object key = "";
    // 主库
    if (DynamicDataSourceHolder.isMaster()) {
        key = DynamicDataSourceHolder.MASTER;
    } else {
        // 从库
        key = getSlaveKey();
    // key=DynamicDataSourceHolder.SLAVE;
    }
    logger.info("==> select datasource key [{}]", key);
    return key;
}


public Object getSlaveKey(){
    if (squence.intValue() == Integer.MAX_VALUE) {
        synchronized (squence) {
            if (squence.intValue() == Integer.MAX_VALUE) {
                // squence = new AtomicInteger(0);
                squence.compareAndSet(Integer.MAX_VALUE, 0);
            }
        }
    }
    int idx = squence.getAndIncrement() % slaveDataSources.size();
    return slaveDataSources.get(idx);
}


}
package com.example.steam.config;
 import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
public class DynamicDataSourceTransactionManager extends DataSourceTransactionManager{


@Override
public void doBegin(Object transaction,TransactionDefinition definition){
    // 设置数据源
    boolean readOnly = definition.isReadOnly();
    if (readOnly) {
        DynamicDataSourceHolder.putDataSource(DynamicDataSourceHolder.SLAVE);
    } else {
        DynamicDataSourceHolder.putDataSource(DynamicDataSourceHolder.MASTER);
    }
    super.doBegin(transaction, definition);
}


@Override
public void doCleanupAfterCompletion(Object transaction){
    super.doCleanupAfterCompletion(transaction);
    DynamicDataSourceHolder.clearDataSource();
}


}
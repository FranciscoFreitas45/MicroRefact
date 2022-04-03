package com.crontab;
 import java.util.List;
import javax.sql.DataSource;
import com.ssc.faw.util.GenException;
public interface FxOrderDao {


public void setDataSource(DataSource dataSource)
;

public List<LegacyFxOrder> getFxOrderInRange(int dateRange)
;

}
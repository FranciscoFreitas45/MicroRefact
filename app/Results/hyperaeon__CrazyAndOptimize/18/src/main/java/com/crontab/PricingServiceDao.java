package com.crontab;
 import java.util.List;
import javax.sql.DataSource;
import com.ssc.faw.util.GenException;
public interface PricingServiceDao {


public List<PricingService> loadAll()
;

public void initJdbcCall(DataSource dataSource)
;

}
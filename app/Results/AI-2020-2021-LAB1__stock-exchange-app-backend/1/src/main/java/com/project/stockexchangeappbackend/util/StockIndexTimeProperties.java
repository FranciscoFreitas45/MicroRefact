package com.project.stockexchangeappbackend.util;
 import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
@ConfigurationProperties(prefix = "application.stock")
@Data
public class StockIndexTimeProperties {

 private  Integer maxPriceHistoryPeriod;

 private  Integer fixingPriceCycle;

 private  Integer stockPriceChangeRatioPeriod;

 private  Integer systemResourcesMonitorInterval;

 private  Integer systemResourcesMonitorHistory;


}
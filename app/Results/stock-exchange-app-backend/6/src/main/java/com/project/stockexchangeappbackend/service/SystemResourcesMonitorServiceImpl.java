package com.project.stockexchangeappbackend.service;
 import com.project.stockexchangeappbackend.entity.SystemResourcesMonitor;
import com.project.stockexchangeappbackend.repository.SystemResourcesMonitorRepository;
import com.project.stockexchangeappbackend.util.timemeasuring.LogicBusinessMeasureTime;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import oshi.hardware.GlobalMemory;
import java.lang.management.ManagementFactory;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;
import com.project.stockexchangeappbackend.Interface.StockIndexTimeProperties;
@Service
@AllArgsConstructor
@Slf4j
public class SystemResourcesMonitorServiceImpl implements SystemResourcesMonitorService{

 private  SystemResourcesMonitorRepository systemResourcesMonitorRepository;

 private final GlobalMemory globalMemory;

 private  StockIndexTimeProperties stockIndexTimeProperties;


@Override
@Transactional(readOnly = true)
@LogicBusinessMeasureTime
public List<SystemResourcesMonitor> getInfo(Specification<SystemResourcesMonitor> specification){
    return systemResourcesMonitorRepository.findAll(specification, Sort.by("timestamp").ascending());
}


@Override
@Transactional
public void addSystemResources(){
    long memoryUsed = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
    systemResourcesMonitorRepository.save(SystemResourcesMonitor.builder().timestamp(OffsetDateTime.now(ZoneId.systemDefault())).cpuUsage(ManagementFactory.getOperatingSystemMXBean().getSystemLoadAverage()).memoryUsage((double) memoryUsed / (globalMemory.getTotal()) * 100).memoryUsed(memoryUsed).build());
    int records = (int)stockIndexTimeProperties.getSystemResourcesMonitorHistory() * 3600000 / (int)stockIndexTimeProperties.getSystemResourcesMonitorInterval();
    if (systemResourcesMonitorRepository.count() > records) {
        systemResourcesMonitorRepository.delete(systemResourcesMonitorRepository.findFirstByOrderByTimestampAsc());
    }
}


}
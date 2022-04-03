package com.project.stockexchangeappbackend.scheduler;
 import com.project.stockexchangeappbackend.service.SystemResourcesMonitorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
@Component
@Slf4j
@AllArgsConstructor
public class HardwareResourceInfoScheduler {

 private  SystemResourcesMonitorService systemResourcesMonitorService;


@Scheduled(fixedDelayString = "${application.stock.systemResourcesMonitorInterval}")
public void run(){
    systemResourcesMonitorService.addSystemResources();
}


}
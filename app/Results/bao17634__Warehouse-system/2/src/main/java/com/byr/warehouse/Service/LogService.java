package com.byr.warehouse.Service;
 import com.byr.warehouse.dao.LogOperationRepository;
import com.byr.warehouse.dao.LogSystemRepository;
import com.byr.warehouse.pojo.LogOperation;
import com.byr.warehouse.pojo.LogSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
@Service
public class LogService {

@Autowired
 private  LogSystemRepository logSystemRepository;

@Autowired
 private  LogOperationRepository logOperationRepository;


public void saveOpLog(String username,String operation,String result,String detail){
    LogOperation logOperation = new LogOperation();
    logOperation.setUsername(username);
    logOperation.setResult(result);
    logOperation.setOperation(operation);
    if (detail.length() > 1023) {
        detail = detail.substring(0, 1023);
    }
    logOperation.setDetail(detail);
    logOperation.setDate(new Date());
    logOperationRepository.save(logOperation);
}


public void saveSysLog(String logMessage){
    LogSystem logSystem = new LogSystem();
    logSystem.setLogMessage(logMessage.substring(0, 1023));
    logSystem.setLogDate(new Date());
    logSystemRepository.save(logSystem);
}


}
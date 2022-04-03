package com.lingxiang2014.service.impl;
 import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.lingxiang2014.dao.LogDao;
import com.lingxiang2014.entity.Log;
import com.lingxiang2014.service.LogService;
@Service("logServiceImpl")
public class LogServiceImpl extends BaseServiceImpl<Log, Long>implements LogService{

@Resource(name = "logDaoImpl")
 private  LogDao logDao;


@Resource(name = "logDaoImpl")
public void setBaseDao(LogDao logDao){
    super.setBaseDao(logDao);
}


public void clear(){
    logDao.removeAll();
}


}
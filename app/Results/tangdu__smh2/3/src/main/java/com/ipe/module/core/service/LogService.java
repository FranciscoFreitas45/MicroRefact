package com.ipe.module.core.service;
 import com.ipe.common.dao.CustomerRepository;
import com.ipe.common.service.BaseService;
import com.ipe.module.core.entity.Log;
import com.ipe.module.core.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class LogService extends BaseService<Log, String>{

@Autowired
 private  LogRepository logRepository;


@Override
public CustomerRepository getRepository(){
    return logRepository;
}


}
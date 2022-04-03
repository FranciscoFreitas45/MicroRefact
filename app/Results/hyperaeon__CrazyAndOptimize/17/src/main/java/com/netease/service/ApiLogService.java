package com.netease.service;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.netease.domain.ApiLog;
import com.netease.repositories.ApiLogRepository;
@Service
public class ApiLogService {

@Autowired
 private  ApiLogRepository apiLogRepository;


public List<ApiLog> getAll(){
    return (List<ApiLog>) apiLogRepository.findAll();
}


public ApiLog save(ApiLog apiLog){
    return apiLogRepository.save(apiLog);
}


}
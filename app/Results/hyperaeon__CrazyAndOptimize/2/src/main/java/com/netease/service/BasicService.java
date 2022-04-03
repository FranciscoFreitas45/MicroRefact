package com.netease.service;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.netease.domain.Basic;
import com.netease.repositories.BasicRepository;
@Service
public class BasicService {

@Autowired
 private  BasicRepository basicRespository;


public List<Basic> findByUserIdOrderByReportTime(Integer userId){
    return basicRespository.findByUserIdOrderByReportTimeDesc(userId);
}


public Basic save(Basic basic){
    return basicRespository.save(basic);
}


public Basic findByReportId(Integer id){
    return basicRespository.findById(id);
}


public List<Basic> findAll(){
    return basicRespository.findAll();
}


public Basic findByReportNumber(String reportNumber){
    return basicRespository.findByReportNumber(reportNumber);
}


}
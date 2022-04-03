package com.uec.imonitor.dic.service.impl;
 import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.uec.imonitor.common.base.BaseService;
import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.common.exception.RequestParamException;
import com.uec.imonitor.dic.bean.WebsiteDicEntity;
import com.uec.imonitor.dic.dao.IWebsiteDicJPARepository;
import com.uec.imonitor.dic.service.IWebsiteDicService;
@Service("websiteDicService")
@Transactional(value = "transactionManager")
public class WebsiteDicServiceImpl extends BaseServiceimplements IWebsiteDicService{

@Autowired
 private  IWebsiteDicJPARepository websiteDicRepository;


@Override
public WebsiteDicEntity findWebsiteByName(String name){
    if (StringUtils.isBlank(name)) {
        throw new RequestParamException(new String[] { "name" });
    }
    WebsiteDicEntity entity = websiteDicRepository.findWebsiteByName(name, 0);
    return entity;
}


@Override
public WebsiteDicEntity findById(int id){
    if (id == 0) {
        throw new RequestParamException(new String[] { "id" });
    }
    WebsiteDicEntity website = websiteDicRepository.findOne(id);
    return website;
}


@Override
public List<WebsiteDicEntity> listWebsiteClassifi(){
    List<WebsiteDicEntity> list = websiteDicRepository.findByParentId(0, 0);
    return list;
}


@Override
public List<WebsiteDicEntity> listWebsite(){
    List<WebsiteDicEntity> list = websiteDicRepository.findAllWebsite(0);
    return list;
}


}
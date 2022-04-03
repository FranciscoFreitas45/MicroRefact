package com.uec.imonitor.news.service.impl;
 import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.uec.imonitor.common.base.BaseService;
import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.common.exception.RequestParamException;
import com.uec.imonitor.news.bean.NewsContentEntity;
import com.uec.imonitor.news.dao.INewsContentJPARepository;
import com.uec.imonitor.news.service.INewsContentService;
@Service("newsContentService")
@Transactional(value = "transactionManager")
public class NewsContentServiceImpl extends BaseServiceimplements INewsContentService{

@Autowired
 private  INewsContentJPARepository newsContentRepository;


@Override
public NewsContentEntity findByWebpageCode(String webpageCode){
    if (StringUtils.isBlank(webpageCode)) {
        throw new RequestParamException(new String[] { "webpageCode" });
    }
    NewsContentEntity entity = newsContentRepository.findOne(webpageCode);
    return entity;
}


}
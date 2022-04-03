package com.uec.imonitor.news.service.impl;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.uec.imonitor.common.base.BaseService;
import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.common.exception.RequestParamException;
import com.uec.imonitor.news.bean.NewsWebpageEntity;
import com.uec.imonitor.news.dao.INewsWebpageJPARepository;
import com.uec.imonitor.news.service.INewsWebpageService;
@Service("newsWebpageService")
@Transactional(value = "transactionManager")
public class NewsWebpageServiceImpl extends BaseServiceimplements INewsWebpageService{

@Autowired
 private  INewsWebpageJPARepository newsWebpageRepository;


@Override
public List<NewsWebpageEntity> findByNewsType(Integer newsType){
    if (null == newsType) {
        throw new RequestParamException(new String[] { "newsType" });
    }
    List<NewsWebpageEntity> list = newsWebpageRepository.findByNewsType(newsType);
    return list;
}


@Override
public NewsWebpageEntity findByWebpageCode(String webpageCode){
    NewsWebpageEntity entity = newsWebpageRepository.findOne(webpageCode);
    return entity;
}


}
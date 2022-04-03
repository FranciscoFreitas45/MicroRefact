package com.uec.imonitor.news.service;
 import java.util.List;
import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.news.bean.NewsWebpageEntity;
public interface INewsWebpageService {


public List<NewsWebpageEntity> findByNewsType(Integer newsType)
;

public NewsWebpageEntity findByWebpageCode(String webpageCode)
;

}
package com.uec.imonitor.news.service;
 import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.news.bean.NewsContentEntity;
public interface INewsContentService {


public NewsContentEntity findByWebpageCode(String webpageCode)
;

}
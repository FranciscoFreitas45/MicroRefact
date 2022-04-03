package com.uec.imonitor.news.service.impl;
 import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.uec.imonitor.common.base.BaseService;
import com.uec.imonitor.common.base.PageResponse;
import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.common.exception.RequestParamException;
import com.uec.imonitor.es.bean.result.BaseQueryResult;
import com.uec.imonitor.news.bean.NewsContentEntity;
import com.uec.imonitor.news.bean.QueryNewsParam;
import com.uec.imonitor.news.dao.INewsContentJPARepository;
import com.uec.imonitor.news.service.INewsContentService;
import com.uec.imonitor.news.service.IRequestNewsPoiService;
import com.uec.imonitor.request.bean.RequestNewsDetail;
import com.uec.imonitor.request.bean.RequestNewsEntity;
import com.uec.imonitor.request.bean.RequestNewsShow;
import com.uec.imonitor.request.dao.IRequestNewsJPARepository;
import com.uec.imonitor.request.service.IRequestNewsService;
import com.uec.imonitor.Interface.IRequestNewsJPARepository;
@Service("requestNewsPoiService")
@Transactional(value = "transactionManager")
public class RequestNewsPoiServiceImpl extends BaseServiceimplements IRequestNewsPoiService{

@Autowired
 private  IRequestNewsJPARepository requestNewsRepository;


}
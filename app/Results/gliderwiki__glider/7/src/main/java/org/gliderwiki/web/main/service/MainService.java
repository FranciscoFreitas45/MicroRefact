package org.gliderwiki.web.main.service;
 import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.gliderwiki.framework.util.DateUtil;
import org.gliderwiki.util.RequestManager;
import org.gliderwiki.web.common.service.EntityService;
import org.gliderwiki.web.domain.VisitCounter;
import org.gliderwiki.web.domain.WeSpace;
import org.gliderwiki.web.domain.WeWiki;
import org.gliderwiki.web.domain.WeWikiTag;
import org.gliderwiki.web.main.dao.MainDao;
import org.gliderwiki.web.system.SystemConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.gliderwiki.Interface.EntityService;
import org.gliderwiki.Interface.RequestManager;
@Service("mainService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class MainService {

 private Logger logger;

@Resource(name = "entityService")
 private  EntityService entityService;

@Resource(name = "mainDao")
 private  MainDao mainDao;

@Resource(name = "requestManager")
 private  RequestManager requestManager;


public List<Map<String,String>> getUserPointList(){
    return mainDao.getUserPointList();
}


public List<Map<String,String>> getHomeWikiList(String listType){
    return mainDao.getHomeWikiList(listType);
}


public int getToMePeopleCount(int userIdx){
    return mainDao.getToMePeopleCount(userIdx);
}


public Map<String,Integer> getVisitCount(HttpServletRequest request){
    VisitCounter visitCounter = new VisitCounter(request);
    return visitCounter.getAllVisitCountInfo();
}


public int getMyWikiCount(int userIdx){
    return mainDao.getMyWikiCount(userIdx);
}


public int getWikiCount(){
    return mainDao.getWikiCount();
}


public int getTagCount(){
    return mainDao.getTagCount();
}


public List<Map<String,String>> getFavorList(){
    return mainDao.getFavorList();
}


public List<Map<String,String>> getWikiList(Integer userIdx,String listType){
    int startRow = 0;
    int endRow = 0;
    if (listType.equals("recent")) {
        startRow = 0;
        endRow = 5;
    } else {
        startRow = 0;
        endRow = 20;
    }
    return this.getMoreWikiList(userIdx, listType, startRow, endRow);
}


public List<Map<String,String>> getMoreWikiList(Integer userIdx,String listType,Integer startRow,Integer endRow){
    logger.debug("##userIdx : " + userIdx);
    logger.debug("##listType : " + listType);
    logger.debug("##startRow : " + startRow);
    logger.debug("##endRow : " + endRow);
    return mainDao.getWikiList(userIdx, listType, startRow, endRow);
}


public List<Map<String,String>> getAgreeList(){
    return mainDao.getAgreeList();
}


public int getAllWikiCount(){
    WeWiki weWiki = new WeWiki();
    weWiki.setWe_use_yn("Y");
    return entityService.getCountEntity(weWiki);
}


public List<Map<String,String>> getMyNotiList(Integer weUserIdx,Integer startRow,Integer endRow){
    // 최근 한달로 조정해야 할 경우 아래 코드 추가
    // String month = DateUtil.getOffsetDate(SystemConst.SEACH_MONTH);
    return mainDao.getMyNotiList(weUserIdx, startRow, endRow);
}


public List getAllTagList(){
    WeWikiTag tags = new WeWikiTag();
    return entityService.getListEntity(tags);
}


public int getMyPeopleCount(int userIdx){
    return mainDao.getMyPeopleCount(userIdx);
}


public int getAllSpaceCount(){
    WeSpace weSpace = new WeSpace();
    weSpace.setWe_use_yn("Y");
    return entityService.getCountEntity(weSpace);
}


public List<Map<String,String>> getUpdatedList(Integer startRow,Integer endRow){
    return mainDao.getUpdatedList(startRow, endRow);
}


}
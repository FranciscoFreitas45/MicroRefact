package org.gliderwiki.admin.service;
 import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.gliderwiki.admin.dao.AdminSpaceDao;
import org.gliderwiki.admin.dao.AdminWikiDao;
import org.gliderwiki.framework.exception.DBHandleException;
import org.gliderwiki.web.common.service.EntityService;
import org.gliderwiki.web.domain.WeGroupUser;
import org.gliderwiki.web.domain.WeSpace;
import org.gliderwiki.web.domain.WeWiki;
import org.gliderwiki.web.domain.WeWikiTag;
import org.gliderwiki.web.wiki.common.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.gliderwiki.Interface.EntityService;
import org.gliderwiki.Interface.CommonService;
import org.gliderwiki.Interface.AdminKeywordService;
import org.gliderwiki.DTO.WeWiki;
@Service("adminWikiService")
@RemoteProxy(name = "AdminWikiService")
public class AdminWikiServiceImpl implements AdminWikiService{

 private Logger logger;

@Inject
 private  PlatformTransactionManager tx;

@Autowired
 private  EntityService entityService;

@Autowired
 private  AdminWikiDao adminWikiDao;

@Autowired
 private  CommonService commonService;

@Autowired
 private  AdminSpaceDao adminSpaceDao;

@Autowired
 private  AdminKeywordService adminKeywordService;


@Override
@RemoteMethod
public Object[] getWikiDetailInfo(Integer weWikiIdx,String attrId){
    // 1. 위키 정보 조회
    WeWiki weWiki = adminWikiDao.getWikiDetailInfo(weWikiIdx);
    // 2. 위키의 공간 정보 조회
    WeSpace weSpace = adminSpaceDao.getSpaceDetailInfo(weWiki.getWe_space_idx());
    // 3. 공간에 접근 가능한 그룹 (추후)
    // 4. 공간에 접근 가능한 사용자 리스트 (추후)
    Object[] returnArrayObject = new Object[3];
    // 위키 정보
    returnArrayObject[0] = weWiki;
    // 공간 정보
    returnArrayObject[1] = weSpace;
    returnArrayObject[2] = attrId;
    return returnArrayObject;
}


@SuppressWarnings("unchecked")
@Override
@RemoteMethod
public Map<String,Object> entityDeleteWiki(Integer wikiIdx,Integer wikiRevision,Integer weUserIdx){
    // TODO Auto-generated method stub
    // 위키 태그(키워드) 삭제
    Map<String, Object> result = new HashMap<String, Object>();
    WeWiki domain = new WeWiki();
    domain.setWe_wiki_idx(wikiIdx);
    domain.setWe_use_yn("N");
    domain.setWe_upd_user(weUserIdx);
    // 관리자 삭제
    domain.setWe_wiki_protect("9");
    TransactionStatus status = tx.getTransaction(new DefaultTransactionDefinition());
    try {
        // 위키 삭제
        adminWikiDao.updateWeWikiByAdmin(domain);
        // 태그(키워드)삭제
        adminKeywordService.deleteKeywordWiki(wikiIdx, wikiRevision, weUserIdx);
        tx.commit(status);
    } catch (Exception e) {
        e.printStackTrace();
        result.put("msg", "실패했습니다. 관리자에게 문의하세요.");
        result.put("flag", "fail");
        logger.error("***위키삭제 처리중  Error " + e.getCause());
        tx.rollback(status);
    }
    result.put("msg", "처리 되었습니다.");
    result.put("flag", "success");
    return result;
}


@RemoteMethod
public int updateWikiStatus(Integer wikiIdx,Integer weUserIdx,String status){
    WeWiki wiki = commonService.getWikiInfo(wikiIdx);
    wiki.setWe_wiki_protect(status);
    wiki.setWe_upd_user(weUserIdx);
    wiki.setWe_upd_date(new Date());
    int result = 0;
    result = entityService.updateEntity(wiki);
    return result;
}


@Override
public List<WeWiki> getWikiListMonth(Date month){
    return adminWikiDao.getWikiListMonth(month);
}


@Override
public List<WeWiki> getWikiSearchList(String weUserNick,String weWikiTitle,String weWikiText,String weSpaceName){
    return adminWikiDao.getWikiSearchList(weUserNick, weWikiTitle, weWikiText, weSpaceName);
}


@Override
@RemoteMethod
public int arrayDeleteWiki(String wikiIdx,Integer weUserIdx){
    String[] arrayIdx = wikiIdx.split(",");
    int size = arrayIdx.length;
    int result = 0;
    for (int i = 0; i < size; i++) {
        WeWiki domain = new WeWiki();
        domain.setWe_wiki_idx(Integer.parseInt(arrayIdx[i].trim()));
        domain.setWe_use_yn("N");
        domain.setWe_upd_user(weUserIdx);
        // 관리자 삭제
        domain.setWe_wiki_protect("9");
        try {
            result = adminWikiDao.updateWeWikiByAdmin(domain);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    return result;
}


}
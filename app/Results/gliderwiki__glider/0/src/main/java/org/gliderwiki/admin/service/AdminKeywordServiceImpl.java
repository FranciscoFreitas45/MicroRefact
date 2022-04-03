package org.gliderwiki.admin.service;
 import java.util.List;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.gliderwiki.admin.dao.AdminKeywordDao;
import org.gliderwiki.web.common.service.EntityService;
import org.gliderwiki.web.domain.WeWikiTag;
import org.gliderwiki.web.system.SystemConst;
import org.gliderwiki.web.vo.KeywordVo;
import org.gliderwiki.web.wiki.common.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.gliderwiki.DTO.WeWikiTag;
@Service("adminKeywordService")
@RemoteProxy(name = "AdminKeywordService")
public class AdminKeywordServiceImpl implements AdminKeywordService{

 private Logger logger;

@Autowired
 private  EntityService entityService;

@Autowired
 private  AdminKeywordDao adminKeywordDao;


@RemoteMethod
public List<KeywordVo> getMoreKeyword(Integer startRow,Integer endRow){
    logger.debug("#startRow : " + startRow);
    logger.debug("#endRow : " + endRow);
    KeywordVo keyword = new KeywordVo();
    // 잘라올 시작점
    keyword.setStartRow(startRow);
    // 21개
    keyword.setEndRow(endRow);
    return this.getKeywordList(keyword);
}


@Override
public int deleteKeywordWiki(Integer wikiIdx,Integer weWikiRevisionIdx,Integer weUserIdx){
    WeWikiTag tag = new WeWikiTag();
    tag.setWe_wiki_idx(wikiIdx);
    tag.setWe_wiki_revision(weWikiRevisionIdx);
    tag.setWe_use_yn("N");
    int result = adminKeywordDao.updateKeywordWiki(tag);
    return result;
}


@Override
public List<KeywordVo> getKeywordList(KeywordVo keyword){
    return adminKeywordDao.getKeywordList(keyword);
}


@Override
@RemoteMethod
public int deleteKeyword(Integer weWikiTagIdx,Integer weUserIdx){
    WeWikiTag domain = new WeWikiTag();
    domain.setWe_wiki_tag_idx(weWikiTagIdx);
    WeWikiTag tag = (WeWikiTag) entityService.getRowEntity(domain);
    tag.setWe_use_yn("N");
    int result = entityService.updateEntity(tag);
    return result;
}


}
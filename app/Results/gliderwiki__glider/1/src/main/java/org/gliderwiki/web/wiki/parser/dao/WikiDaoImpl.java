package org.gliderwiki.web.wiki.parser.dao;
 import java.util.List;
import java.util.Map;
import org.gliderwiki.web.domain.WeTemplate;
import org.gliderwiki.web.domain.WeWiki;
import org.gliderwiki.web.domain.WeWikiBak;
import org.gliderwiki.web.domain.WeWikiLog;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import com.google.common.collect.Maps;
@Repository("wikiDao")
public class WikiDaoImpl extends SqlSessionDaoSupportimplements WikiDao{


@Override
public int insertWikiLog(WeWikiLog wikiLog){
    int result = getSqlSession().update("WikiManage.insertWikiLog", wikiLog);
    return result;
}


@Override
public List<WeTemplate> getWeTemplateList(WeTemplate temp){
    return (List<WeTemplate>) getSqlSession().selectList("WikiManage.getWeTemplateList", temp);
}


@Override
public List<WeWiki> getWikiList(WeWiki searchWiki){
    return (List<WeWiki>) getSqlSession().selectList("WikiManage.getWikiList", searchWiki);
}


@Override
public int insertSelectedWikiBak(WeWikiBak wikiBak){
    return getSqlSession().update("WikiManage.insertSelectedWikiBak", wikiBak);
}


@Override
public int delWeWikiTag(Integer weWikiIdx,int weWikiRevision,String weUseYn){
    Map<String, Object> mapper = Maps.newHashMap();
    mapper.put("weWikiIdx", weWikiIdx);
    mapper.put("weWikiRevision", weWikiRevision);
    mapper.put("weUseYn", weUseYn);
    int result = getSqlSession().update("WikiManage.delWeWikiTag", mapper);
    return result;
}


@Override
public int updateWikiFile(Integer weWikiIdx,int weWikiRevision,String weUseYn){
    Map<String, Object> mapper = Maps.newHashMap();
    mapper.put("weWikiIdx", weWikiIdx);
    mapper.put("weWikiRevision", weWikiRevision);
    mapper.put("weUseYn", weUseYn);
    int result = getSqlSession().update("WikiManage.updateWikiFile", mapper);
    return result;
}


@Override
public int delWeWikiNote(Integer weWikiIdx,int weWikiRevision,String weUseYn){
    Map<String, Object> mapper = Maps.newHashMap();
    mapper.put("weWikiIdx", weWikiIdx);
    mapper.put("weWikiRevision", weWikiRevision);
    mapper.put("weUseYn", weUseYn);
    int result = getSqlSession().update("WikiManage.delWeWikiNote", mapper);
    return result;
}


@Override
public int delWeWikiSummary(Integer weWikiIdx,int weWikiRevision,String weUseYn){
    Map<String, Object> mapper = Maps.newHashMap();
    mapper.put("weWikiIdx", weWikiIdx);
    mapper.put("weWikiRevision", weWikiRevision);
    mapper.put("weUseYn", weUseYn);
    int result = getSqlSession().update("WikiManage.delWeWikiSummary", mapper);
    return result;
}


@Override
public int delWeWikiFile(Integer weFileIdx){
    return getSqlSession().update("WikiManage.delWeWikiFile", weFileIdx);
}


@Override
public int delWeWikiGraph(Integer weWikiIdx,int weWikiRevision,String weUseYn){
    Map<String, Object> mapper = Maps.newHashMap();
    mapper.put("weWikiIdx", weWikiIdx);
    mapper.put("weWikiRevision", weWikiRevision);
    mapper.put("weUseYn", weUseYn);
    int result = getSqlSession().update("WikiManage.delWeWikiGraph", mapper);
    return result;
}


@Override
public List<WeTemplate> getWeTemplateIdx(WeTemplate temp){
    return (List<WeTemplate>) getSqlSession().selectList("WikiManage.getWeTemplateIdx", temp);
}


@Override
public int delWeWikiLink(Integer weWikiIdx,int weWikiRevision,String weUseYn){
    Map<String, Object> mapper = Maps.newHashMap();
    mapper.put("weWikiIdx", weWikiIdx);
    mapper.put("weWikiRevision", weWikiRevision);
    mapper.put("weUseYn", weUseYn);
    int result = getSqlSession().update("WikiManage.delWeWikiLink", mapper);
    return result;
}


}
package org.gliderwiki.web.wiki.parser.dao;
 import java.util.List;
import java.util.Map;
import org.gliderwiki.web.domain.WeWiki;
import org.gliderwiki.web.vo.WikiLogVo;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.google.common.collect.Maps;
@Repository("wikiRegistDao")
public class WikiRegistDaoImpl extends SqlSessionDaoSupportimplements WikiRegistDao{

 private Logger logger;


@Override
public int getCurrentWikiIdx(){
    return (Integer) getSqlSession().selectOne("WikiManage.getCurrentWikiIdx");
}


@Override
public String getMinDepthIdx(WeWiki weWiki){
    return (String) getSqlSession().selectOne("WikiManage.getMinDepthIdx", weWiki);
}


@Override
public int getNextWikiIdx(){
    return (Integer) getSqlSession().selectOne("WikiManage.getNextWikiIdx");
}


@Override
public int updateParentDepthIdx(WeWiki weWiki,String minDepthIdx){
    weWiki.setWe_wiki_order_idx(Integer.parseInt(minDepthIdx));
    return getSqlSession().update("WikiManage.updateParentDepthIdx", weWiki);
}


@Override
public String getMaxDepthIdx(WeWiki weWiki){
    return (String) getSqlSession().selectOne("WikiManage.getMaxDepthIdx", weWiki);
}


@Override
public int insertArrayFileList(String[] weFileIdx,int currIdx,int weWikiRevision){
    Map<String, Object> mapper = Maps.newHashMap();
    mapper.put("weWikiIdx", currIdx);
    mapper.put("weWikiRevision", weWikiRevision);
    mapper.put("list", weFileIdx);
    int result = getSqlSession().update("WikiManage.insertArrayFileList", mapper);
    return result;
}


}
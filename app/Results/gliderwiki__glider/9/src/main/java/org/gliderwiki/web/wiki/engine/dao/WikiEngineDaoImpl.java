package org.gliderwiki.web.wiki.engine.dao;
 import java.util.List;
import org.gliderwiki.web.domain.WeWiki;
import org.gliderwiki.web.domain.WeWikiBak;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
@Repository("wikiEngineDao")
public class WikiEngineDaoImpl extends SqlSessionDaoSupportimplements WikiEngineDao{

 private Logger logger;


@Override
public int insertWeWikiBak(WeWiki weWiki){
    return getSqlSession().insert("WikiManage.insertWeWikiBak", weWiki);
}


@Override
public List<WeWikiBak> getListWikiRevision(WeWikiBak weWikiBak){
    return getSqlSession().selectList("WikiManage.getListWikiRevision", weWikiBak);
}


@Override
public WeWiki getOriginWiki(WeWiki weWiki){
    return (WeWiki) getSqlSession().selectOne("WikiManage.getOriginWiki", weWiki);
}


}
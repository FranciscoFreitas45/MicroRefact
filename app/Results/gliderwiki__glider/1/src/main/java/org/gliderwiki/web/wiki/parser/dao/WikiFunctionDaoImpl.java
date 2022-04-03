package org.gliderwiki.web.wiki.parser.dao;
 import org.gliderwiki.web.domain.WeWiki;
import org.gliderwiki.web.domain.WeWikiAgree;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
@Repository("wikiFunctionDao")
public class WikiFunctionDaoImpl extends SqlSessionDaoSupportimplements WikiFunctionDao{


@Override
public int insertWeAgree(WeWikiAgree wewikiagree){
    return getSqlSession().update("WikiManage.insertWeAgree", wewikiagree);
}


@Override
public int updateWikiByUser(WeWiki weWiki){
    return getSqlSession().update("WikiManage.updateWikiByUser", weWiki);
}


}
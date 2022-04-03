package org.gliderwiki.admin.dao;
 import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.gliderwiki.web.domain.WeSpace;
import org.gliderwiki.web.domain.WeWiki;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
@Repository("adminWikiDao")
public class AdminWikiDaoImpl extends SqlSessionDaoSupportimplements AdminWikiDao{


@Override
public int updateWeWikiByAdmin(WeWiki domain){
    int result = getSqlSession().update("AdminManage.updateWeWikiByAdmin", domain);
    return result;
}


@Override
public WeWiki getWikiDetailInfo(Integer weWikiIdx){
    return (WeWiki) getSqlSession().selectOne("AdminManage.getWikiDetailInfo", weWikiIdx);
}


@Override
public List<WeWiki> getWikiListMonth(Date month){
    List<WeWiki> list = null;
    list = (List<WeWiki>) getSqlSession().selectList("AdminManage.getWikiListMonth", month);
    return list;
}


@Override
public List<WeWiki> getWikiSearchList(String weUserNick,String weWikiTitle,String weWikiText,String weSpaceName){
    Map<String, String> mapper = new HashMap<String, String>();
    mapper.put("weUserNick", weUserNick);
    mapper.put("weWikiTitle", weWikiTitle);
    mapper.put("weWikiText", weWikiText);
    mapper.put("weSpaceName", weSpaceName);
    List<WeWiki> list = null;
    list = (List<WeWiki>) getSqlSession().selectList("AdminManage.getWikiSearchList", mapper);
    return list;
}


}
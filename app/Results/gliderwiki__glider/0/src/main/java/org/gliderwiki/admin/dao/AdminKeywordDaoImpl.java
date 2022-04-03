package org.gliderwiki.admin.dao;
 import java.util.List;
import org.gliderwiki.web.domain.WeProfile;
import org.gliderwiki.web.domain.WeUser;
import org.gliderwiki.web.domain.WeWikiTag;
import org.gliderwiki.web.vo.KeywordVo;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
@Repository("adminKeywordDao")
public class AdminKeywordDaoImpl extends SqlSessionDaoSupportimplements AdminKeywordDao{


@Override
public Integer updateKeywordWiki(WeWikiTag weWikiTag){
    // TODO Auto-generated method stub
    return getSqlSession().update("AdminManage.updateKeywordWiki", weWikiTag);
}


@Override
public List<KeywordVo> getKeywordList(KeywordVo keyword){
    List<KeywordVo> list = null;
    list = (List<KeywordVo>) getSqlSession().selectList("AdminManage.getKeywordList", keyword);
    return list;
}


}
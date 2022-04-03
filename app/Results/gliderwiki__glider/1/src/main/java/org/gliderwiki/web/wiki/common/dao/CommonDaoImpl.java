package org.gliderwiki.web.wiki.common.dao;
 import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.gliderwiki.web.domain.WeAddFriend;
import org.gliderwiki.web.domain.WeFavorite;
import org.gliderwiki.web.domain.WeGroupInfo;
import org.gliderwiki.web.domain.WeProfile;
import org.gliderwiki.web.vo.WikiVo;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import com.google.common.collect.Maps;
@Repository("commonDao")
public class CommonDaoImpl extends SqlSessionDaoSupportimplements CommonDao{


@Override
public void notiAllRead(int userIdx){
    getSqlSession().update("CommonManage.updateAllRead", userIdx);
}


@Override
public List<WeGroupInfo> getGroupList(WeGroupInfo domain){
    return getSqlSession().selectList("CommonManage.getGroupList", domain);
}


@Override
public String realNotiView(int userIdx){
    return (String) getSqlSession().selectOne("CommonManage.realNotiView", userIdx);
}


@Override
public void changeRealTimeView(int userIdx,String isView){
    Map<String, Object> mapper = Maps.newHashMap();
    mapper.put("userIdx", userIdx);
    mapper.put("isView", isView);
    getSqlSession().update("CommonManage.changeRealTimeView", mapper);
}


@Override
public int delRelationDWR(WeAddFriend weAddFriend){
    return getSqlSession().update("CommonManage.delRelationDWR", weAddFriend);
}


@Override
public int updateFavorite(WeFavorite weFavorite){
    return getSqlSession().update("CommonManage.updateFavorite", weFavorite);
}


@Override
public List<WikiVo> getWikiSearchList(String wiki_text){
    Map<String, String> mapper = new HashMap<String, String>();
    mapper.put("wiki_text", wiki_text);
    return getSqlSession().selectList("CommonManage.getWikiSearchList", mapper);
}


@Override
public int delFavorite(WeFavorite weFavorite){
    return getSqlSession().update("CommonManage.delFavorite", weFavorite);
}


@Override
public void updateUserPoint(WeProfile domain){
    getSqlSession().update("CommonManage.updateUserPoint", domain);
}


}
package org.gliderwiki.admin.dao;
 import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.gliderwiki.web.domain.WeBbs;
import org.gliderwiki.web.domain.WeSpace;
import org.gliderwiki.web.domain.WeWiki;
import org.gliderwiki.web.vo.WikiTagVo;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
@Repository("adminSpaceDao")
public class AdminSpaceDaoImpl extends SqlSessionDaoSupportimplements AdminSpaceDao{


@Override
public List<WeSpace> getSpaceListMonth(WeSpace space,Date month){
    Map<String, Object> mapper = new HashMap<String, Object>();
    mapper.put("month", month);
    mapper.put("space", space);
    List<WeSpace> list = null;
    list = (List<WeSpace>) getSqlSession().selectList("AdminManage.getSpaceListMonth", mapper);
    return list;
}


@Override
public int updateWikiUseYn(Integer weSpaceIdx,Integer weUserIdx){
    Map<String, Integer> mapper = new HashMap<String, Integer>();
    mapper.put("weSpaceIdx", weSpaceIdx);
    mapper.put("weUserIdx", weUserIdx);
    return getSqlSession().update("AdminManage.updateWikiUseYn", mapper);
}


@Override
public List<WeSpace> getSpaceSearchList(WeSpace weSpace){
    List<WeSpace> list = null;
    list = (List<WeSpace>) getSqlSession().selectList("AdminManage.getSpaceSearchList", weSpace);
    return list;
}


@Override
public int updateSpaceByAdmin(WeSpace space){
    return getSqlSession().update("AdminManage.updateSpaceByAdmin", space);
}


@Override
public List<WeWiki> getWikiListInSpace(Integer weSpaceIdx){
    List<WeWiki> list = null;
    list = (List<WeWiki>) getSqlSession().selectList("AdminManage.getWikiListInSpace", weSpaceIdx);
    return list;
}


@Override
public List<WeBbs> getBbsSearchList(WeBbs weBbs){
    List<WeBbs> list = null;
    list = (List<WeBbs>) getSqlSession().selectList("AdminManage.getBbsSearchList", weBbs);
    return list;
}


@Override
public WeSpace getSpaceDetailInfo(Integer weSpaceIdx){
    return (WeSpace) getSqlSession().selectOne("AdminManage.getSpaceDetailInfo", weSpaceIdx);
}


}
package org.gliderwiki.web.wiki.keyword.dao;
 import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.gliderwiki.web.domain.WeSpace;
import org.gliderwiki.web.vo.WikiTagVo;
import org.gliderwiki.web.vo.WikiVo;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
@Repository("tagDao")
public class TagDaoImpl extends SqlSessionDaoSupportimplements TagDao{


@Override
public List<WikiVo> getWikiListByTagIdx(List<Integer> wikiListIdx){
    List<WikiVo> wikiList = null;
    wikiList = (List<WikiVo>) getSqlSession().selectList("CommonManage.getWikiListByTagIdx", wikiListIdx);
    return wikiList;
}


@Override
public List<Integer> getTagNameList(String tagName){
    List<Integer> tagList = null;
    tagList = (List<Integer>) getSqlSession().selectList("CommonManage.getTagNameList", tagName);
    return tagList;
}


@Override
public List<WeSpace> getSpaceIdxByWikiIdx(List<Integer> wikiListIdx){
    List<WeSpace> spaceList = null;
    spaceList = (List<WeSpace>) getSqlSession().selectList("CommonManage.getSpaceIdxByWikiIdx", wikiListIdx);
    return spaceList;
}


@Override
public List<WikiTagVo> getTagList(Date month,Integer maxLimit){
    Map<String, Object> mapper = new HashMap<String, Object>();
    mapper.put("month", month);
    mapper.put("maxLimit", maxLimit);
    List<WikiTagVo> tagList = null;
    tagList = (List<WikiTagVo>) getSqlSession().selectList("CommonManage.getTagList", mapper);
    return tagList;
}


@Override
public List<WeSpace> getSpaceInfoBySpaceIdx(List<Integer> spaceListIdx){
    List<WeSpace> spaceList = null;
    spaceList = (List<WeSpace>) getSqlSession().selectList("CommonManage.getSpaceInfoByTagIdx", spaceListIdx);
    return spaceList;
}


}
package org.gliderwiki.web.main.dao;
 import java.util.List;
import java.util.Map;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import com.google.common.collect.Maps;
@Repository("mainDao")
public class MainDao extends SqlSessionDaoSupport{


@SuppressWarnings("unchecked")
public List<Map<String,String>> getUserPointList(){
    List<Map<String, String>> userPointList = (List<Map<String, String>>) getSqlSession().selectList("MainManage.getUserPointList");
    return userPointList;
}


@SuppressWarnings("unchecked")
public List<Map<String,String>> getHomeWikiList(String listType){
    List<Map<String, String>> list;
    Map<String, Object> mapper = Maps.newHashMap();
    mapper.put("listType", listType);
    list = (List<Map<String, String>>) getSqlSession().selectList("MainManage.getHomeWikiList", mapper);
    return list;
}


@SuppressWarnings("unchecked")
public int getToMePeopleCount(int userIdx){
    return (Integer) getSqlSession().selectOne("MainManage.getToMePeopleCount", userIdx);
}


@SuppressWarnings("unchecked")
public List<Map<String,String>> getFavorList(){
    List<Map<String, String>> favoriteList = (List<Map<String, String>>) getSqlSession().selectList("MainManage.getFavorList");
    return favoriteList;
}


@SuppressWarnings("unchecked")
public List<Map<String,String>> getWikiList(int userIdx,String listType,Integer startRow,Integer endRow){
    List<Map<String, String>> wikiList = null;
    Map<String, Object> mapper = Maps.newHashMap();
    mapper.put("userIdx", userIdx);
    mapper.put("listType", listType);
    mapper.put("startRow", startRow);
    mapper.put("endRow", endRow);
    wikiList = (List<Map<String, String>>) getSqlSession().selectList("MainManage.getWikiList", mapper);
    return wikiList;
}


@SuppressWarnings("unchecked")
public List<Map<String,String>> getAgreeList(){
    List<Map<String, String>> agreeList = (List<Map<String, String>>) getSqlSession().selectList("MainManage.getAgreeList");
    return agreeList;
}


@SuppressWarnings("unchecked")
public int getMyWikiCount(int userIdx){
    return (Integer) getSqlSession().selectOne("MainManage.getMyWikiCount", userIdx);
}


@SuppressWarnings("unchecked")
public int getWikiCount(){
    return (Integer) getSqlSession().selectOne("MainManage.getWikiCount");
}


@SuppressWarnings("unchecked")
public int getTagCount(){
    return (Integer) getSqlSession().selectOne("MainManage.getTagCount");
}


public List<Map<String,String>> getMyNotiList(Integer weUserIdx,Integer startRow,Integer endRow){
    Map<String, Object> mapper = Maps.newHashMap();
    mapper.put("weUserIdx", weUserIdx);
    mapper.put("startRow", startRow);
    mapper.put("endRow", endRow);
    List<Map<String, String>> list = null;
    list = (List<Map<String, String>>) getSqlSession().selectList("MainManage.getMyNotiList", mapper);
    return list;
}


@SuppressWarnings("unchecked")
public int getMyPeopleCount(int userIdx){
    return (Integer) getSqlSession().selectOne("MainManage.getMyPeopleCount", userIdx);
}


@SuppressWarnings("unchecked")
public List<Map<String,String>> getUpdatedList(Integer startRow,Integer endRow){
    Map<String, Object> mapper = Maps.newHashMap();
    mapper.put("startRow", startRow);
    mapper.put("endRow", endRow);
    List<Map<String, String>> updatedList = (List<Map<String, String>>) getSqlSession().selectList("MainManage.getUpdatedList", mapper);
    return updatedList;
}


}
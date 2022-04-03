package com.circle.dao.good.impl;
 import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import com.circle.constant.CircleTable;
import com.circle.dao.good.IGoodDao;
import com.circle.pojo.good.GoodBean;
import com.xwtec.xwserver.dao.common.ICommonDao;
import com.xwtec.xwserver.exception.SPTException;
import com.xwtec.xwserver.pojo.Page;
import com.xwtec.xwserver.util.StringUtil;
import com.Interface.ICommonDao;
import com.DTO.Page;
@Repository
public class GoodDaoImpl implements IGoodDao{

 public  Logger log;

 public  String QUERY_GOODLIST_SQL;

 public  String QUERY_BATCH_GOODLIST_SQL;

 public  String QUERY_GOOD_SQL;

 public  String QUERY_ATTR_VALUES_SQL;

 public  String QUERY_ARG_VALUES_SQL;

 public  String QUERY_GOOD_IMAGE_SQL;

 public  String QUERY_GOOD_COMMENT_SQL;

 public  String UPDATE_GOOD_BUYNUM;

@Resource
 private ICommonDao commonDao;


@Override
public Map<String,Object> queryGood(String id){
    Map<String, Object> paramMap = new HashMap<String, Object>();
    paramMap.put("id", id);
    return commonDao.queryForMap(QUERY_GOOD_SQL, paramMap);
}


@Override
public List<Map<String,Object>> queryImageList(String id){
    Map<String, Object> paramMap = new HashMap<String, Object>();
    paramMap.put("good_id", id);
    return commonDao.queryForList(QUERY_GOOD_IMAGE_SQL, paramMap);
}


@Override
public List<Map<String,Object>> queryGoodTypeArgValues(String id){
    Map<String, Object> paramMap = new HashMap<String, Object>();
    paramMap.put("good_id", id);
    return commonDao.queryForList(QUERY_ARG_VALUES_SQL, paramMap);
}


@Override
public List<Map<String,Object>> queryGoodList(Page page){
    log.debug("[GoodDaoImpl.queryGoodList] start...");
    StringBuilder sql = new StringBuilder(QUERY_GOODLIST_SQL);
    if (page != null && page.getSearchParam() != null) {
        if (page.getSearchParam().containsKey("keyWord") && !StringUtil.isEmpty(page.getSearchParam().get("keyWord"))) {
            sql.append(" and (good_name LIKE '%" + page.getSearchParam().get("keyWord") + "%' ");
            sql.append(" or title LIKE '%" + page.getSearchParam().get("keyWord") + "%' ");
            sql.append(" or intro LIKE '%" + page.getSearchParam().get("keyWord") + "%') ");
        // page.getSearchParam().put("keyWord", "%"+page.getSearchParam().get("keyWord")+"%");
        }
        if (page.getSearchParam().containsKey("farm_id") && !StringUtil.isEmpty(page.getSearchParam().get("farm_id"))) {
            sql.append(" and good.farm_id =:farm_id");
        }
        if (page.getSearchParam().containsKey("type_id") && !StringUtil.isEmpty(page.getSearchParam().get("type_id"))) {
            sql.append(" and good.type_id = :type_id");
        }
    }
    sql.append(" group by good.id order by good.recommend desc ,good.sort_id asc");
    List<Map<String, Object>> goodList = commonDao.queryForList(sql.toString(), page.getSearchParam(), page);
    log.debug("[GoodDaoImpl.queryGoodList] end...");
    return goodList;
}


public void batchAddBuyNum(List<Map<String,?>> paramListMap){
    commonDao.batchUpdate(UPDATE_GOOD_BUYNUM, paramListMap);
}


public List<Map<String,Object>> queryCurrentBatchGoodList(Page page,int batchId){
    if (page.getSearchParam() == null) {
        page.setSearchParam(new HashMap<String, String>());
    }
    log.debug("[GoodDaoImpl.queryGoodList] start...");
    StringBuilder sql = new StringBuilder(QUERY_BATCH_GOODLIST_SQL);
    if (page != null && page.getSearchParam() != null) {
        if (page.getSearchParam().containsKey("keyWord") && !StringUtil.isEmpty(page.getSearchParam().get("keyWord"))) {
            sql.append(" and (good_name LIKE '%" + page.getSearchParam().get("keyWord") + "%' ");
            sql.append(" or title LIKE '%" + page.getSearchParam().get("keyWord") + "%' ");
            sql.append(" or intro LIKE '%" + page.getSearchParam().get("keyWord") + "%') ");
        // page.getSearchParam().put("keyWord", "%"+page.getSearchParam().get("keyWord")+"%");
        }
        if (page.getSearchParam().containsKey("farm_id") && !StringUtil.isEmpty(page.getSearchParam().get("farm_id"))) {
            sql.append(" and good.farm_id =:farm_id");
        }
        if (page.getSearchParam().containsKey("type_id") && !StringUtil.isEmpty(page.getSearchParam().get("type_id"))) {
            sql.append(" and good.type_id = :type_id");
        }
    }
    if (batchId != 0) {
        sql.append(" and sg.batch_id = :batchId");
        page.getSearchParam().put("batchId", batchId + "");
    }
    sql.append(" group by good.id order by good.recommend desc ,good.sort_id asc");
    List<Map<String, Object>> goodList = commonDao.queryForList(sql.toString(), page.getSearchParam(), page);
    log.debug("[GoodDaoImpl.queryGoodList] end...");
    return goodList;
}


@Override
public List<Map<String,Object>> queryGoodComment(Page page){
    return commonDao.queryForList(QUERY_GOOD_COMMENT_SQL, page.getSearchParam(), page);
}


public void addBuyNum(String goodId,int buyNum){
    Map<String, Object> paramMap = new HashMap<String, Object>();
    paramMap.put("goodId", goodId);
    paramMap.put("buyNum", buyNum);
    commonDao.update(UPDATE_GOOD_BUYNUM, paramMap);
}


public List<GoodBean> queryGoods(){
    List<GoodBean> list = new ArrayList<GoodBean>();
    try {
        list = commonDao.queryForList(QUERY_GOODLIST_SQL, GoodBean.class);
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return list;
}


@Override
public List<Map<String,Object>> queryGoodTypeAttrValues(String id){
    Map<String, Object> paramMap = new HashMap<String, Object>();
    paramMap.put("good_id", id);
    return commonDao.queryForList(QUERY_ATTR_VALUES_SQL, paramMap);
}


public boolean executeSql(String sql,Map<String,Object> paramMap){
    try {
        int count = commonDao.update(sql, paramMap);
        return count > 0 ? true : false;
    } catch (SPTException e) {
        log.error("执行sql异常");
        return false;
    }
}


}
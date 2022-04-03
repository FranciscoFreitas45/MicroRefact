package com.circle.dao.goodtype.impl;
 import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import com.circle.constant.CircleTable;
import com.circle.dao.goodtype.IGoodTypeDao;
import com.circle.pojo.goodtype.GoodType;
import com.xwtec.xwserver.dao.common.ICommonDao;
import com.xwtec.xwserver.exception.SPTException;
import com.Interface.ICommonDao;
@Repository
public class GoodTypeDaoImpl implements IGoodTypeDao{

 public  Logger log;

@Resource
 private ICommonDao commonDao;

 public  String QUERY_GOODTYPES_SQL;

 public  String QUERY_GOODTYPE_SQL;

 public  String QUERY_GOODTYPEARG_SQL;

 public  String QUERY_GOODTYPEATTR_SQL;

 public  String INSERT_GOODTYPE_SQL;

 public  String UPDATE_GOODTYPE_SQL;

 public  String DELETE_GOODTYPEARG_SQL;

 public  String DELETE_GOODTYPEATTR_SQL;

 public  String INSERT_GOODTYPEARG_SQL;

 public  String INSERT_GOODTYPEATTR_SQL;


@Override
public void saveAttr(String name,String english_name,String attrValues,int typeId){
    Map<String, Object> paramMap = new HashMap<String, Object>();
    paramMap.put("name", name);
    paramMap.put("english_name", english_name);
    paramMap.put("attr_value", attrValues);
    paramMap.put("type_id", typeId);
    commonDao.update(INSERT_GOODTYPEATTR_SQL, paramMap);
}


@Override
public void deleteArg(int typeId){
    Map<String, Object> paramMap = new HashMap<String, Object>();
    paramMap.put("type_id", typeId);
    commonDao.update(DELETE_GOODTYPEARG_SQL, paramMap);
}


@Override
public GoodType getGoodType(String id){
    Map<String, Object> paramsMap = new HashMap<String, Object>();
    paramsMap.put("id", id);
    GoodType goodType = commonDao.queryForObject(QUERY_GOODTYPE_SQL, paramsMap, GoodType.class);
    return goodType;
}


@Override
public List<Map<String,Object>> queryGoodTypeArgList(String type_id){
    Map<String, Object> paramMap = new HashMap<String, Object>();
    paramMap.put("type_id", type_id);
    return commonDao.queryForList(QUERY_GOODTYPEARG_SQL, paramMap);
}


@Override
public boolean save(GoodType goodType){
    Map<String, Object> paramMap = getGoodTypeParams(goodType);
    int i = commonDao.update(INSERT_GOODTYPE_SQL, paramMap);
    boolean flag = i > 0 ? true : false;
    if (flag) {
        goodType.setId(commonDao.getLastId(CircleTable.GOOD_TYPE.getTableName(), "id"));
    }
    return flag;
}


@Override
public boolean update(GoodType goodType){
    Map<String, Object> paramMap = getGoodTypeParams(goodType);
    int i = commonDao.update(UPDATE_GOODTYPE_SQL, paramMap);
    boolean flag = i > 0 ? true : false;
    return flag;
}


public Map<String,Object> getGoodTypeParams(GoodType goodType){
    Map<String, Object> paramMap = new HashMap<String, Object>();
    paramMap.put("name", goodType.getName());
    paramMap.put("parent_id", goodType.getParent_id());
    paramMap.put("sort_id", goodType.getSort_id());
    paramMap.put("id", goodType.getId());
    return paramMap;
}


@Override
public void deleteAttr(int typeId){
    Map<String, Object> paramMap = new HashMap<String, Object>();
    paramMap.put("type_id", typeId);
    commonDao.update(DELETE_GOODTYPEATTR_SQL, paramMap);
}


@Override
public void saveArg(String name,String english_name,int typeId){
    Map<String, Object> paramMap = new HashMap<String, Object>();
    paramMap.put("name", name);
    paramMap.put("english_name", english_name);
    paramMap.put("type_id", typeId);
    commonDao.update(INSERT_GOODTYPEARG_SQL, paramMap);
}


@Override
public List<Map<String,Object>> queryGoodTypeAttrList(String type_id){
    Map<String, Object> paramMap = new HashMap<String, Object>();
    paramMap.put("type_id", type_id);
    return commonDao.queryForList(QUERY_GOODTYPEATTR_SQL, paramMap);
}


@Override
public List<Map<String,Object>> queryGoodTypes(){
    return commonDao.queryForList(QUERY_GOODTYPES_SQL);
}


}
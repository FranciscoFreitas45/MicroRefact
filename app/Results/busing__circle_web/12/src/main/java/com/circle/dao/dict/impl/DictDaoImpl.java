package com.circle.dao.dict.impl;
 import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import com.circle.dao.dict.IDictDao;
import com.circle.pojo.dict.DictBean;
import com.circle.pojo.farm.Farm;
import com.circle.pojo.goodtype.GoodType;
import com.circle.pojo.goodtype.GoodTypeArg;
import com.circle.pojo.goodtype.GoodTypeAttr;
import com.xwtec.xwserver.dao.common.ICommonDao;
import com.xwtec.xwserver.exception.SPTException;
import com.Interface.ICommonDao;
@Repository
public class DictDaoImpl implements IDictDao{

 public  String QUERY_DICT_LIST;

 public  String QUERY_GOOD_TYPE_LIST;

 public  String QUERY_GOODTYPE_ATTR_LIST;

 public  String QUERY_GOODTYPE_ARG_LIST;

 public  String QUERY_FARM_LIST;

@Resource
 private ICommonDao commonDao;


public List<Farm> queryFarmList(){
    return commonDao.queryForList(QUERY_FARM_LIST, Farm.class);
}


public List<GoodTypeArg> queryGoodTypeArgList(){
    return commonDao.queryForList(QUERY_GOODTYPE_ARG_LIST, GoodTypeArg.class);
}


public List<GoodTypeAttr> queryGoodTypeAttrList(){
    return commonDao.queryForList(QUERY_GOODTYPE_ATTR_LIST, GoodTypeAttr.class);
}


public List<GoodType> queryGoodTypeList(){
    return commonDao.queryForList(QUERY_GOOD_TYPE_LIST, GoodType.class);
}


public List<DictBean> queryDictList(){
    return commonDao.queryForList(QUERY_DICT_LIST, DictBean.class);
}


}
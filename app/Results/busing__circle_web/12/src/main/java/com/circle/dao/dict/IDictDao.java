package com.circle.dao.dict;
 import java.util.List;
import com.circle.pojo.dict.DictBean;
import com.circle.pojo.farm.Farm;
import com.circle.pojo.goodtype.GoodType;
import com.circle.pojo.goodtype.GoodTypeArg;
import com.circle.pojo.goodtype.GoodTypeAttr;
import com.xwtec.xwserver.exception.SPTException;
public interface IDictDao {


public List<Farm> queryFarmList()
;

public List<GoodTypeArg> queryGoodTypeArgList()
;

public List<GoodTypeAttr> queryGoodTypeAttrList()
;

public List<GoodType> queryGoodTypeList()
;

public List<DictBean> queryDictList()
;

}
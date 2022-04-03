package com.circle.dao.goodtype;
 import java.util.List;
import java.util.Map;
import com.circle.pojo.goodtype.GoodType;
import com.xwtec.xwserver.exception.SPTException;
public interface IGoodTypeDao {


public void saveAttr(String name,String english_name,String attrValues,int typeId)
;

public void deleteArg(int typeId)
;

public GoodType getGoodType(String id)
;

public List<Map<String,Object>> queryGoodTypeArgList(String type_id)
;

public boolean save(GoodType goodType)
;

public boolean update(GoodType goodType)
;

public void deleteAttr(int typeId)
;

public void saveArg(String name,String english_name,int typeId)
;

public List<Map<String,Object>> queryGoodTypeAttrList(String type_id)
;

public List<Map<String,Object>> queryGoodTypes()
;

}
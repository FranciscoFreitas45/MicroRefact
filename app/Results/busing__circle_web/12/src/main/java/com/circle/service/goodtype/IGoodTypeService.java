package com.circle.service.goodtype;
 import java.util.List;
import java.util.Map;
import com.circle.pojo.goodtype.GoodType;
import com.xwtec.xwserver.exception.SPTException;
public interface IGoodTypeService {


public List<Map<String,Object>> getGoodTypeAttr(String type_id)
;

public GoodType getGoodType(String id)
;

public boolean save(GoodType goodType,String argStr,String attrStr)
;

public List<Map<String,Object>> getGoodTypeArg(String type_id)
;

public List<Map<String,Object>> queryGoodTypes()
;

}
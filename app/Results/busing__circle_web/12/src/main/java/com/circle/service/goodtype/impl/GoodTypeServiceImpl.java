package com.circle.service.goodtype.impl;
 import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.circle.dao.goodtype.IGoodTypeDao;
import com.circle.pojo.goodtype.GoodType;
import com.circle.service.dct.IDictService;
import com.circle.service.goodtype.IGoodTypeService;
import com.xwtec.xwserver.exception.SPTException;
@Service
@Transactional
public class GoodTypeServiceImpl implements IGoodTypeService{

@Resource
 private  IGoodTypeDao goodTypeDao;

@Resource
 private  IDictService dictService;


@Override
public List<Map<String,Object>> getGoodTypeAttr(String type_id){
    List<Map<String, Object>> attr_list = goodTypeDao.queryGoodTypeAttrList(type_id);
    for (Map<String, Object> map : attr_list) {
        StringBuilder sb = new StringBuilder();
        String jsonString = map.get("attr_value").toString();
        JSONArray jsonArr = JSONArray.fromObject(jsonString);
        for (Object object : jsonArr) {
            JSONObject jo = (JSONObject) object;
            sb.append(jo.get("attr_name"));
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        map.put("attr_str", sb.toString());
    }
    return attr_list;
}


@Override
public GoodType getGoodType(String id){
    return goodTypeDao.getGoodType(id);
}


public boolean save(GoodType goodType,String argStr,String attrStr){
    boolean flag;
    try {
        if (goodType.getId() == 0) {
            flag = goodTypeDao.save(goodType);
        } else {
            flag = goodTypeDao.update(goodType);
        }
        if (flag) {
            goodTypeDao.deleteArg(goodType.getId());
            goodTypeDao.deleteAttr(goodType.getId());
            JSONArray jsonArg = JSONArray.fromObject(argStr);
            for (Object object : jsonArg) {
                JSONObject jo = (JSONObject) object;
                goodTypeDao.saveArg(jo.getString("name"), jo.getString("english_name"), goodType.getId());
            }
            JSONArray jsonAttr = JSONArray.fromObject(attrStr);
            for (Object object : jsonAttr) {
                JSONObject jo = (JSONObject) object;
                String s = jo.get("attr_values").toString();
                String[] attrArr = s.split(",");
                StringBuilder attrValues = new StringBuilder("[");
                for (int i = 0; i < attrArr.length; i++) {
                    attrValues.append("{");
                    attrValues.append("id:" + (i + 1) + ",");
                    attrValues.append("attr_name:'" + attrArr[i] + "'");
                    attrValues.append("},");
                }
                attrValues.deleteCharAt(attrValues.length() - 1);
                attrValues.append("]");
                goodTypeDao.saveAttr(jo.getString("name"), jo.getString("english_name"), attrValues.toString(), goodType.getId());
            }
            reloadGoodType();
        }
    } catch (SPTException e) {
        flag = false;
        e.printStackTrace();
    }
    return flag;
}


public void reloadGoodType(){
    dictService.initGoodType();
    dictService.initGoodTypeArg();
    dictService.initGoodTypeAttr();
}


@Override
public List<Map<String,Object>> getGoodTypeArg(String type_id){
    return goodTypeDao.queryGoodTypeArgList(type_id);
}


@Override
public List<Map<String,Object>> queryGoodTypes(){
    return goodTypeDao.queryGoodTypes();
}


}
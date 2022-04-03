package com.circle.service.dct.impl;
 import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.circle.constant.SystemDict;
import com.circle.dao.dict.IDictDao;
import com.circle.dao.msg.IMsgDao;
import com.circle.pojo.dict.DictBean;
import com.circle.pojo.goodtype.GoodTypeArg;
import com.circle.pojo.goodtype.GoodTypeAttr;
import com.circle.pojo.goodtype.GoodTypeAttrValues;
import com.circle.pojo.msg.MessageTypeBean;
import com.circle.service.dct.IDictService;
import com.xwtec.xwserver.exception.SPTException;
import com.xwtec.xwserver.util.json.JSONArray;
import com.Interface.IMsgDao;
@Service("dictService")
public class DictServiceImpl implements IDictService{

@Resource
 private  IDictDao dictDao;

@Resource
 private  IMsgDao msgDao;


public void initGoodTypeArg(){
    SystemDict.GOOD_TYPE_ARG_MAP = new HashMap<String, List<GoodTypeArg>>();
    List<GoodTypeArg> argList = dictDao.queryGoodTypeArgList();
    List<GoodTypeArg> argTemp;
    for (GoodTypeArg arg : argList) {
        argTemp = SystemDict.GOOD_TYPE_ARG_MAP.get(arg.getType_id() + "") == null ? new ArrayList<GoodTypeArg>() : SystemDict.GOOD_TYPE_ARG_MAP.get(arg.getType_id() + "");
        argTemp.add(arg);
        SystemDict.GOOD_TYPE_ARG_MAP.put(arg.getType_id() + "", argTemp);
    }
}


public void initDictMap(){
    List<DictBean> dictList = dictDao.queryDictList();
    List<DictBean> tempDict;
    String type_code;
    for (DictBean dictBean : dictList) {
        if (dictBean.getParent_id() == 0) {
            SystemDict.PARENT_DICT_MAP.put(dictBean.getType_id(), dictBean);
        } else {
            type_code = SystemDict.PARENT_DICT_MAP.get(dictBean.getParent_id()).getType_code();
            tempDict = SystemDict.DICT_MAP.get(type_code) == null ? new ArrayList<DictBean>() : SystemDict.DICT_MAP.get(type_code);
            tempDict.add(dictBean);
            SystemDict.DICT_MAP.put(type_code, tempDict);
        }
    }
}


public void initGoodType(){
    SystemDict.GOOD_TYPE = dictDao.queryGoodTypeList();
}


@Override
public void initMessageTypeBeans(){
    List<MessageTypeBean> messageTypeBeans = msgDao.queryMessageTypeBeans();
    for (MessageTypeBean messageTypeBean : messageTypeBeans) {
        SystemDict.MESSAGE_TYPE_MAP.put(messageTypeBean.getId(), messageTypeBean);
    }
}


public void initGoodTypeAttr(){
    SystemDict.GOOD_TYPE_ATTR_MAP = new HashMap<String, List<GoodTypeAttr>>();
    List<GoodTypeAttr> attrList = dictDao.queryGoodTypeAttrList();
    List<GoodTypeAttr> argTemp;
    List<GoodTypeAttrValues> attr_value_list;
    for (GoodTypeAttr attr : attrList) {
        String jsonString = attr.getAttr_value();
        attr_value_list = JSONArray.toList(JSONArray.fromObject(jsonString), GoodTypeAttrValues.class);
        attr.setAttr_value_list(attr_value_list);
        argTemp = SystemDict.GOOD_TYPE_ATTR_MAP.get(attr.getType_id() + "") == null ? new ArrayList<GoodTypeAttr>() : SystemDict.GOOD_TYPE_ATTR_MAP.get(attr.getType_id() + "");
        argTemp.add(attr);
        SystemDict.GOOD_TYPE_ATTR_MAP.put(attr.getType_id() + "", argTemp);
    }
}


public void initFarm(){
    SystemDict.FARM = dictDao.queryFarmList();
}


}
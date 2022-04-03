package com.circle.constant;
 import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.circle.pojo.dict.DictBean;
import com.circle.pojo.farm.Farm;
import com.circle.pojo.goodtype.GoodType;
import com.circle.pojo.goodtype.GoodTypeArg;
import com.circle.pojo.goodtype.GoodTypeAttr;
import com.circle.pojo.goodtype.GoodTypeAttrValues;
import com.circle.pojo.msg.MessageTypeBean;
public class SystemDict {

 public  String SEND_MSG_STATUS_REG;

 public  String SEND_MSG_STATUS_UPPASS;

 public  String MESSAGE_CIRCLE;

 public  String MESSAGE_NORMAL;

 public  String DEFAULT_USER_HEAD_PATH;

 public  String CIRCLE_AUTO_JOIN;

 public  String CIRCLE_JOIN;

 public  String NORMAL_USER;

 public  String FARMER;

 public  String EXAMINEING;

 public  String EXAMINE_OVER;

 public  String USER_TYPE;

 public  String LOGIN_RESULT;

 public  String ORDER_STATUS;

 public  String ORDER_STATUS_SUCCESS;

 public  String ORDER_STATUS_DEAL;

 public  String ORDER_STATUS_COMPLETE;

 public  String ORDER_STATUS_CANCEL;

 public  String PAY_STATUS;

 public  String PAY_STATUS_NO;

 public  String PAY_STATUS_YES;

 public  String SHIPPING_STATUS;

 public  String SHIPPING_STATUS_PREPARE;

 public  String SHIPPING_STATUS_PREPAREING;

 public  String SHIPPING_STATUS_NO;

 public  String SHIPPING_STATUS_YES;

 public  String SHIPPING_STATUS_SIGN;

 public  String PAY_TYPE;

 public  String JOIN_TYPE;

 public  String CIRCLE_HISTORY_TYPE;

 public  String CIRCLE_HISTORY_TYPE_INTRO;

 public  String CIRCLE_HISTORY_TYPE_ISSUE;

 public  String CIRCLE_STATUS;

 public  String CIRCLE_STATUS_DELETE;

 public  String CIRCLE_STATUS_AUDIT;

 public  String CIRCLE_STATUS_AUDIT_PASS;

 public  String CIRCLE_STATUS_AUDIT_REFUSE;

 public  String CIRCLE_HISTORY_STATUS_DELETE;

 public  String CIRCLE_HISTORY_STATUS_AUDIT;

 public  String CIRCLE_HISTORY_STATUS_AUDIT_PASS;

 public  String CIRCLE_HISTORY_STATUS_AUDIT_REFUSE;

 public  String MSG_TYPE;

 public  String MSG_TYPE_ONE;

 public  String MSG_TYPE_TWO;

 public  String SHIPPING_TYPE;

 public  String SHIPPING_TYPE_ONE;

 public  String COMMISSION_TYPE;

 public  String COMMISSION_TYPE_INVITE;

 public  String COMMISSION_TYPE_ORDER;

 public  String COMMISSION_TYPE_SELLER;

 public  String UNIT;

 public  Map<Integer,DictBean> PARENT_DICT_MAP;

 public  Map<String,List<DictBean>> DICT_MAP;

 public  Map<String,MessageTypeBean> MESSAGE_TYPE_MAP;

 public  List<GoodType> GOOD_TYPE;

 public  List<Farm> FARM;

 public  Map<String,List<GoodTypeArg>> GOOD_TYPE_ARG_MAP;

 public  Map<String,List<GoodTypeAttr>> GOOD_TYPE_ATTR_MAP;


public List<GoodTypeAttrValues> getGoodTypeAttrValueList(String type_id,String attr_id){
    List<GoodTypeAttr> goodTypeList = GOOD_TYPE_ATTR_MAP.get(type_id) == null ? new ArrayList<GoodTypeAttr>() : GOOD_TYPE_ATTR_MAP.get(type_id);
    for (GoodTypeAttr goodTypeAttr : goodTypeList) {
        if ((goodTypeAttr.getId() + "").equals(attr_id)) {
            return goodTypeAttr.getAttr_value_list();
        }
    }
    return null;
}


public DictBean getDict(String type_code,String type_id){
    List<DictBean> dictList = getDictList(type_code);
    if (dictList.size() > 0) {
        for (DictBean dictBean : dictList) {
            if (String.valueOf(dictBean.getType_id()).equals(type_id)) {
                return dictBean;
            }
        }
    }
    return null;
}


public Farm getFarm(String farm_id){
    for (Farm f : FARM) {
        if (String.valueOf(f.getId()).equals(farm_id)) {
            return f;
        }
    }
    return null;
}


public GoodType getGoodType(String type_id){
    for (GoodType type : GOOD_TYPE) {
        if (String.valueOf(type.getId()).equals(type_id)) {
            return type;
        }
    }
    return null;
}


public List<GoodTypeAttr> getGoodTypeAttrList(String type_id){
    return GOOD_TYPE_ATTR_MAP.get(type_id) == null ? new ArrayList<GoodTypeAttr>() : GOOD_TYPE_ATTR_MAP.get(type_id);
}


public List<GoodTypeArg> getGoodTypeArgList(String type_id){
    return GOOD_TYPE_ARG_MAP.get(type_id) == null ? new ArrayList<GoodTypeArg>() : GOOD_TYPE_ARG_MAP.get(type_id);
}


public List<DictBean> getDictList(String type_code){
    return DICT_MAP.get(type_code) == null ? new ArrayList<DictBean>() : DICT_MAP.get(type_code);
}


}
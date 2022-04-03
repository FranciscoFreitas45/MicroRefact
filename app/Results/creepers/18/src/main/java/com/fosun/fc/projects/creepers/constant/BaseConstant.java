package com.fosun.fc.projects.creepers.constant;
 import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fosun.fc.projects.creepers.utils.PropertiesUtil;
public class BaseConstant {

 public  String HASH_ALGORITHM;

 public  int HASH_INTERATIONS;

 public  int SALT_SIZE;

 public  String HEXSTR;

 public  String SUPPER_MAN_CLIENT;

 public  String LOGIC_DELETE_FLAG__NO_DELETE;

 public  String LOGIC_DELETE_FLAG__DELETED;

 public  String SYSTEM_OS_NAME;

 public  String SYSTEM_TYPE_WINDOWS;

 public  String PARAM_DTO_KEY;

 public  String PARAM_EXTRA_MAP_KEY;

 public  String PARAM_EXTRA_THREAD_NUM;

 public  String PARAM_EXTRA_CURRENT_PAGE_NO;

 public  String PARAM_EXTRA_TOTAL_PAGE_NO;

 public  String PARAM_CHAO_REN_IMAGE_ID;

 public  String HEADER_NAME_LOCATION;

 public  String PAGE_FILE_INPUTSREAM_STRING;

 public  String PAGE_FILE_PATH;

 public  String PAGE_SEARCH_PARAMS;

 public  String POST_NAME_VALUE_PAIR;

 public  String POST_HEADER;

 public  String SELECT_TASK_LIST_TYPE_NAME;

 public  String SELECT_TASK_LIST_TYPE_VALUE;

 public  String IMAGE_FILE_BASE_PATH;

 public  String IMAGE_FILE_SUFFIX_JPG;

 public  String FILE_PATH_SEPARATION;

 public  String FILE_NAME_UNDERLINED;

 public  String SK_UPLOAD_URL;

 public  String SK_DOWNLOAD_URL;

 public  String SK_REQUESTCODE;

 public  String SK_INVOKERNAME;

 public  String SK_BIZ_GROUP;

 public  String SK_SYS_NAME;

 public  String MAIL_SUBJECT;

 public  String WORD_CONTENT_KEY;

 public  String RESULT_DATA_KEY;

 public  String TABLE_COLUMN_JSON_CONTENT;

 public  SerializerFeature[] features;

 private  long serialVersionUID;

 public  List<String> IGNORE_PROPERTIES;

 public  String[] USER_AGENT_ARRAY;

 private  String code;

 private  String code;

 private  String code;

 private  String value;

 private  String value;

 private  String value;

 private  String value;

 private  String nameChinese;

 private  boolean isDisplay;

 private  TaskListParentType type;

 private  String value;

 private  String code;

 private  String value;

 private  String name;

 private  String value;

 private  String[] arrayString;

 private  String value;

 private  String value;

 private  String value;


public boolean getIsDisplay(){
    return isDisplay;
}


public String getName(){
    return name;
}


public String[] getArr(){
    return arrayString;
}


public List<Map<String,String>> getAllDataTaskListTypeList(){
    return getTaskListTypeList(TaskListParentType.SINGLE_SEARCH_ALL_DATA);
}


public String getValue(){
    return value;
}


public String getNameChinese(){
    return nameChinese;
}


public List<Map<String,String>> getTouristBlackListType(){
    List<Map<String, String>> result = new ArrayList<Map<String, String>>();
    TouristBlackListType[] taskListType = TouristBlackListType.values();
    for (TouristBlackListType eachType : taskListType) {
        Map<String, String> map = new HashMap<String, String>();
        map.put(SELECT_TASK_LIST_TYPE_NAME, eachType.name);
        map.put(SELECT_TASK_LIST_TYPE_VALUE, eachType.value);
        result.add(map);
    }
    return result;
}


public TaskListParentType getType(){
    return type;
}


public List<Map<String,String>> getLoginTaskListTypeList(){
    return getTaskListTypeList(TaskListParentType.SINGLE_LOGIN_AND_TARGET_URL);
}


public String getOne(int i){
    return arrayString[i];
}


@Override
public String toString(){
    return String.valueOf(this.code);
}


public List<Map<String,String>> getTaskListTypeList(TaskListParentType type){
    List<Map<String, String>> result = new ArrayList<Map<String, String>>();
    TaskListType[] taskListType = TaskListType.values();
    for (TaskListType eachType : taskListType) {
        if (eachType.isDisplay && eachType.type.equals(type)) {
            Map<String, String> map = new HashMap<String, String>();
            map.put(SELECT_TASK_LIST_TYPE_NAME, eachType.nameChinese);
            map.put(SELECT_TASK_LIST_TYPE_VALUE, eachType.value);
            result.add(map);
        }
    }
    return result;
}


public String getCode(){
    return code;
}


}
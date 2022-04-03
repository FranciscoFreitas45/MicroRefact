package DTO;
 import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class CgSubTableVO {

 private CgFormHeadEntity head;

 private List<Map<String,Object>> fieldList;

 private List<Map<String,Object>> hiddenFieldList;

 private List<Map<String,Object>> fieldNoAreaList;

 private List<Map<String,Object>> fieldAreaList;


public List<Map<String,Object>> getFieldList(){
    return fieldList;
}


public List<Map<String,Object>> getFieldNoAreaList(){
    return fieldNoAreaList;
}


public List<Map<String,Object>> getFieldAreaList(){
    return fieldAreaList;
}


public CgFormHeadEntity getHead(){
    return head;
}


public List<Map<String,Object>> getHiddenFieldList(){
    return hiddenFieldList;
}


}
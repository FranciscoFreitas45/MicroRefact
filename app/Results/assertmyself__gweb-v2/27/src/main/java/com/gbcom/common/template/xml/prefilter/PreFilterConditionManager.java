package com.gbcom.common.template.xml.prefilter;
 import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import com.gbcom.op.util.xml.XStreamUtil;
public class PreFilterConditionManager {

 private  Logger LOG;

 private  String FILEPATH;

 private  Map<String,PreFilterCondition> conditionMap;

 private  PreFilterConditionManager instance;

 private  boolean deprecated;

/**
 * 私有构造器
 */
private PreFilterConditionManager() {
    init();
}
public boolean filter(Object apDevice,String operId){
    if (deprecated) {
        return deprecated;
    }
    try {
        // must replace
        // implement  object.match
        PreFilterCondition devFilterCondition = conditionMap.get(apDevice.toString());
        if (devFilterCondition == null) {
            // 如果没有配置该型号，返回true
            return true;
        }
        String[] regexs = devFilterCondition.getVersion().split(",");
        for (String regex : regexs) {
            if (regex.equals("*")) {
                regex = ".*";
            }
            boolean isMatch = apDevice.toString().matches(regex);
            if (isMatch) {
                String[] values = devFilterCondition.getValue().split(",");
                List<String> valueList = Arrays.asList(values);
                boolean isContain = valueList.contains(operId);
                return isContain;
            } else {
                return false;
            }
        }
    } catch (Exception e) {
        LOG.error(e.getMessage(), e);
        return false;
    }
    return true;
}


public void init(){
    try {
        if (conditionMap == null) {
            conditionMap = new HashMap<String, PreFilterCondition>();
        }
        Class<?>[] classContext = { PreFilterConditionContext.class, PreFilterCondition.class };
        PreFilterConditionContext devConditionContext = XStreamUtil.fromXML(PreFilterConditionContext.class, FILEPATH, classContext);
        List<PreFilterCondition> conList = devConditionContext.getList();
        for (PreFilterCondition devFilterCondition : conList) {
            conditionMap.put(devFilterCondition.getSysmodel(), devFilterCondition);
        }
        deprecated = !devConditionContext.isFilterSwitch();
    } catch (Exception e) {
        LOG.error("INIT FAILE", e);
        deprecated = true;
    }
}


public PreFilterConditionManager getInstance(){
    if (instance == null) {
        instance = new PreFilterConditionManager();
    }
    return instance;
}


}
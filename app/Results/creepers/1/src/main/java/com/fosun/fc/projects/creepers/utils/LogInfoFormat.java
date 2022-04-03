package com.fosun.fc.projects.creepers.utils;
 import org.nlpcn.commons.lang.util.StringUtil;
import com.fosun.fc.projects.creepers.dto.CreepersLoginParamDTO;
import com.fosun.fc.projects.creepers.dto.CreepersParamDTO;
public class LogInfoFormat {

 private  String NEW_LINE;

 private  String SPILT_LINE;

 private  String LOG_START;

 private  String LOG_END;

 private  String TASK_TYPE;

 private  String ERROR_PATH;

 private  String ERROR_INFO;

 private  String PARAM_SEARCH_KEY_WORD;

 private  String VALUE_LEFT;

 private  String VALUE_RIGHT;


public String errorLog(CreepersLoginParamDTO param){
    StringBuffer errorInfo = new StringBuffer();
    errorInfo.append(NEW_LINE);
    errorInfo.append(SPILT_LINE).append(NEW_LINE);
    errorInfo.append(LOG_START).append(NEW_LINE);
    errorInfo.append(SPILT_LINE).append(NEW_LINE).append(NEW_LINE);
    errorInfo.append(ERROR_PATH).append(param.getErrorPath()).append(NEW_LINE).append(NEW_LINE);
    errorInfo.append(TASK_TYPE).append(param.getTaskType()).append(NEW_LINE).append(NEW_LINE);
    errorInfo.append(ERROR_INFO).append(param.getErrorInfo()).append(NEW_LINE).append(NEW_LINE);
    if (!StringUtil.isBlank(param.getSearchKeyWordForString())) {
        errorInfo.append(PARAM_SEARCH_KEY_WORD).append(NEW_LINE);
        errorInfo.append(VALUE_LEFT).append(param.getLoginName()).append(VALUE_RIGHT).append(NEW_LINE);
        errorInfo.append(NEW_LINE);
    }
    errorInfo.append(SPILT_LINE).append(NEW_LINE);
    errorInfo.append(LOG_END).append(NEW_LINE);
    errorInfo.append(SPILT_LINE).append(NEW_LINE);
    return errorInfo.toString();
}


public void main(String[] args){
    CreepersParamDTO dto = new CreepersParamDTO();
    dto.putSearchKeyWord("searchKeyWord");
    dto.setErrorPath("errorPath");
    dto.setErrorInfo("errorInfo");
    dto.setTaskType("taskType");
    System.out.println(LogInfoFormat.errorLog(dto));
}


}
package com.kingen.repository.activiti;
 import java.io.Serializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.kingen.repository.CommonDao;
@Component
public class ActivitiDao extends // 必须要加泛型，不然会认为是commondao，导致commonservice里的commondao多出来几个
CommonDao<Object, Serializable>{

 private  Logger logger;


public void deleteAllMemerShip(){
    String sql = "delete from ACT_ID_MEMBERSHIP";
    executeSql(sql);
    logger.debug("deleted from activiti membership.");
}


public void deleteAllGroup(){
    String sql = "delete from ACT_ID_GROUP";
    executeSql(sql);
    logger.debug("deleted from activiti group.");
}


public void deleteAllUser(){
    String sql = "delete from ACT_ID_USER";
    executeSql(sql);
    logger.debug("deleted from activiti user.");
}


}
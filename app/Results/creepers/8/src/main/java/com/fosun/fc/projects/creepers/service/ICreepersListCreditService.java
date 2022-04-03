package com.fosun.fc.projects.creepers.service;
 import java.util.Map;
public interface ICreepersListCreditService extends BaseService{


public void updateImageAndHtmlPath(String loginName,String imagePath,String filePath)
;

public Map<String,Object> findByUserCodeAndMessageCode(String loginName,String messageCaptchaValue)
;

public void updateImagePath(String loginName,String imagePath)
;

public void updateTaskListStatusSucceed(String loginName)
;

public void updateHtmlFilePath(String loginName,String filePath)
;

public void updateTaskListStatus(String loginName,String status)
;

}
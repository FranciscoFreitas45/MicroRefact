package com.fosun.fc.projects.creepers.pageprocessor;
 import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.service.ICreepersExceptionHandleService;
public class BaseCreepersListProcessor {

@SuppressWarnings("unused")
 private  Logger logger;

@Autowired
 protected  ICreepersExceptionHandleService creepersExceptionHandleServiceImpl;


}
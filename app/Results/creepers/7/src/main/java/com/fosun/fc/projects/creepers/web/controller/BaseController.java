package com.fosun.fc.projects.creepers.web.controller;
 import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.service.ICreepersErrorListService;
import com.fosun.fc.projects.creepers.service.ICreepersListService;
import com.fosun.fc.projects.creepers.Interface.ICreepersErrorListService;
import com.fosun.fc.projects.creepers.Interface.ICreepersListService;
public class BaseController {

@Autowired
 protected  ICreepersErrorListService creepersErrorListServiceImpl;

@Autowired
 protected  ICreepersListService creepersListServiceImpl;


}
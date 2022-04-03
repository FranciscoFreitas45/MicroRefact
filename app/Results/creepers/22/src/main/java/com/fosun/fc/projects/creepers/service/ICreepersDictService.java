package com.fosun.fc.projects.creepers.service;
 import java.util.List;
import com.fosun.fc.projects.creepers.entity.TCreepersDict;
public interface ICreepersDictService extends BaseService{


public void saveTCreepersDict(TCreepersDict entity)
;

public List<TCreepersDict> queryAllDict(TCreepersDict entity)
;

}
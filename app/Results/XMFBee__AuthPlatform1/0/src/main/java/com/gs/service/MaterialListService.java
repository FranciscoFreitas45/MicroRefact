package com.gs.service;
 import com.gs.bean.MaterialList;
import com.gs.bean.User;
import com.gs.bean.view.MaterialURTemp;
import com.gs.bean.view.MaterialView;
import com.gs.common.bean.Pager;
import java.util.List;
import java.util.Map;
public interface MaterialListService extends BaseService<String, MaterialList>{


public int countRecordAccs(String recordId,User user)
;

public void insertList(List<MaterialList> materialLists)
;

public int count(String userId)
;

public MaterialURTemp queryFlowVarsByRecordId(String recordId,String accId)
;

public List<MaterialList> recordAccsByPager(String recordId,User user,Pager pager)
;

public List<MaterialView> queryByPager(String userId,Pager pager)
;

}
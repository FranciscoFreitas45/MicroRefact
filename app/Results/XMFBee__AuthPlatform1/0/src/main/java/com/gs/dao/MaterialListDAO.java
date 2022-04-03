package com.gs.dao;
 import com.gs.bean.MaterialList;
import com.gs.bean.view.MaterialURTemp;
import com.gs.bean.view.MaterialView;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
@Repository
public interface MaterialListDAO extends BaseDAO<String, MaterialList>{


public int countRecordAccs(Map paramMap)
;

public void insertList(List<MaterialList> materialLists)
;

public int count(String userId)
;

public List<MaterialURTemp> queryFlowVarsByRecordId(Map map)
;

public List<MaterialList> recordAccsByPager(Map paramMap)
;

public List<MaterialView> queryByPager(String userId,Pager pager)
;

}
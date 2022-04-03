package com.empl.mgr.service;
 import com.empl.mgr.support.JSONReturn;
public interface PositionService {


public JSONReturn deletePosition(long id,String acctName)
;

public JSONReturn findPositionByDeptId(long deptId)
;

public JSONReturn findPositionById(long id)
;

public JSONReturn findPositionPage(int page,long deptId,String searchValue,String acctName)
;

public JSONReturn addPosition(long deptId,String name,String desc,String acctName)
;

public JSONReturn modifyPosition(long id,long deptId,String name,String desc,String acctName)
;

public JSONReturn findPositionListInfo(int page,long deptId,String searchValue,String acctName)
;

}
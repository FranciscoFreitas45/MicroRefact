package com.gs.service;
 import com.gs.bean.User;
import com.gs.bean.WorkInfo;
import com.gs.common.bean.Pager;
import java.util.List;
public interface WorkInfoService extends BaseService<String, WorkInfo>{


public List<WorkInfo> queryByPagerschelude(Pager pager)
;

public int countByFront(User frontUser)
;

public int count(User user,String status)
;

public List<WorkInfo> queryByFront(Pager pager)
;

public List<WorkInfo> queryByCondition(String start,String end,String companyId,String maintainOrFix,String type)
;

public List<WorkInfo> queryByPager(Pager pager,String status)
;

}
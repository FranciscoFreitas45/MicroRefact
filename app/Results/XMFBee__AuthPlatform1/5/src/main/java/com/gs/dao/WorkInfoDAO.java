package com.gs.dao;
 import com.gs.bean.User;
import com.gs.bean.WorkInfo;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;
@Repository
public interface WorkInfoDAO extends BaseDAO<String, WorkInfo>{


public List<WorkInfo> queryByPagerschelude(Pager pager)
;

public int countByFront(User frontUser)
;

public int countByBlurred(WorkInfo workInfo,User user)
;

public int count(Map paramMap)
;

public List<WorkInfo> blurredQuery(Pager pager,WorkInfo workInfo)
;

public List<WorkInfo> queryByFront(Pager pager)
;

public List<WorkInfo> queryByCondition(String start,String end,String companyId,String maintainOrFix,String type)
;

public List<WorkInfo> queryByPager(Map paramMap)
;

}
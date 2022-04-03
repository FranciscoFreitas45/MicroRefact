package com.gs.dao;
 import com.gs.bean.MaintainDetail;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface MaintainDetailDAO extends BaseDAO<String, MaintainDetail>{


public List<MaintainDetail> queryByDetailByPager(Pager pager,String maintainRecordId)
;

public int countByDetail(String maintainRecordId)
;

public List<MaintainDetail> queryByRecordId(String maintainRecordId)
;

public List<MaintainDetail> queryByOwner(Pager pager,String recordId,String userId)
;

public List<MaintainDetail> queryByFrontpage(Pager pager)
;

public int countByOwner(String recordId,String userId)
;

public List<MaintainDetail> queryByCondition(String start,String end,String companyId,String maintainId,String type)
;

}
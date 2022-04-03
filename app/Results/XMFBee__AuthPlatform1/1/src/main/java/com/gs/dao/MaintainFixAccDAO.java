package com.gs.dao;
 import com.gs.bean.MaintainFix;
import com.gs.bean.MaintainFixAcc;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface MaintainFixAccDAO extends BaseDAO<String, MaintainFixAcc>{


public List<MaintainFixAcc> queryByRecord(String fixId)
;

public int countByDetails(String maintainId,User user)
;

public List<MaintainFixAcc> queryByDetailsByPager(Pager pager,String maintainId)
;

}
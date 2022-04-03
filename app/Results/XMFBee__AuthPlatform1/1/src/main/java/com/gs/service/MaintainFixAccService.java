package com.gs.service;
 import com.gs.bean.MaintainFix;
import com.gs.bean.MaintainFixAcc;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import java.util.List;
public interface MaintainFixAccService extends BaseService<String, MaintainFixAcc>{


public List<MaintainFixAcc> queryByRecord(String fixId)
;

public int countByDetails(String maintainId,User user)
;

public List<MaintainFixAcc> queryByDetailsByPager(Pager pager,String maintainId)
;

}
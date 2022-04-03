package com.gs.service;
 import com.gs.bean.MaintainRemind;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import java.util.List;
public interface MaintainRemindService extends BaseService<String, MaintainRemind>{


public List<MaintainRemind> queryByPagerNull(Pager pager)
;

public int countUser(String userId)
;

public int countNull(User user)
;

public void insertBatch(List<MaintainRemind> maintainReminds)
;

public List<MaintainRemind> queryByPagerUser(Pager pager,String userId)
;

}
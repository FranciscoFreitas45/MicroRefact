package com.gs.dao;
 import com.gs.bean.MaintainRemind;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface MaintainRemindDAO extends BaseDAO<String, MaintainRemind>{


public int countByBlurred(MaintainRemind maintainRemind,User user)
;

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

public List<MaintainRemind> blurredQuery(Pager pager,MaintainRemind maintainRemind)
;

}
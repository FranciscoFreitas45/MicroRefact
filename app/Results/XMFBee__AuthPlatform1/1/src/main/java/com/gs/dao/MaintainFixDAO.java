package com.gs.dao;
 import com.gs.bean.MaintainDetail;
import com.gs.bean.MaintainFix;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.activiti.engine.impl.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import sun.applet.Main;
import java.util.List;
@Repository
public interface MaintainFixDAO extends BaseDAO<String, MaintainFix>{


public int querymaintainName(String maintainName,String maintainId)
;

public List<MaintainFix> queryByPagerDisable(Pager pager)
;

public int countByDisableService(User user)
;

public int countMaintain(User user)
;

public int countqueryByPagerAll(User user)
;

public List<MaintainFix> queryByPagerDisableService(Pager pager)
;

public List<MaintainFix> queryByPagerMaintain(Pager pager)
;

public int countByDisable(User user)
;

public List<MaintainFix> queryByCompanyId(String companyId)
;

public int querymaintainNameMaintain(String maintainName,String maintainId)
;

public List<MaintainFix> queryByPagerAll(Pager pager)
;

public List<MaintainFix> queryByMaintainName(String companyId,String maintainOrFix)
;

public List<MaintainFix> queryByCondition(String start,String end,String maintainId,String companyId,String maintainOrFix,String type)
;

}
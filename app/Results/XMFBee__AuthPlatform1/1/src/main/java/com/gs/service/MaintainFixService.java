package com.gs.service;
 import com.gs.bean.MaintainDetail;
import com.gs.bean.MaintainFix;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import java.util.List;
public interface MaintainFixService extends BaseService<String, MaintainFix>{


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
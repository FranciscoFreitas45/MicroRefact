package cn.maxcj.core.common.constant.factory;
 import cn.maxcj.modular.system.model.Dict;
import java.util.List;
public interface IConstantFactory {


public String getSingleRoleName(Integer roleId)
;

public String getUserAccountById(Integer userId)
;

public String getMenuNameByCode(String code)
;

public String getUserNameById(Integer userId)
;

public String getMenuNames(String menuIds)
;

public String getSexName(Integer sex)
;

public String getDictsByName(String name,Integer val)
;

public String getSingleRoleTip(Integer roleId)
;

public List<Dict> findInDict(Integer id)
;

public String getActivity_state(Integer stateCode)
;

public String getStatusName(Integer status)
;

public String getFinance(Integer finance_catory)
;

public List<Integer> getParentDeptIds(Integer deptid)
;

public String getMenuName(Long menuId)
;

public String getUserAcademy(Integer academyCode)
;

public String getDictName(Integer dictId)
;

public String getRoleName(String roleIds)
;

public String getActivity_category(Integer categoryCode)
;

public String getCacheObject(String para)
;

public String getDeptName(Integer deptId)
;

public String getNoticeTitle(Integer dictId)
;

public List<Integer> getSubDeptId(Integer deptid)
;

public String getActivityName(Integer activity_id)
;

public String getClub_category(Integer category)
;

public String getMenuStatusName(Integer status)
;

}
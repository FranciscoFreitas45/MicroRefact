package com.sobey.cmop.mvc.dao.custom;
 import java.util.List;
public interface AccountDaoCustom {


@SuppressWarnings("rawtypes")
public List getUserGroupByUserId(Integer userId)
;

public List<String> getGroupPermissionByGroupId(Integer groupId)
;

}
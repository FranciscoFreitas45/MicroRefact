package cn.maxcj.core.common.constant.dictmap;
 import cn.maxcj.core.common.constant.dictmap.base.AbstractDictMap;
public class RoleDict extends AbstractDictMap{


@Override
public void init(){
    put("roleId", "角色名称");
    put("num", "角色排序");
    put("pid", "角色的父级");
    put("name", "角色名称");
    put("deptid", "部门名称");
    put("tips", "备注");
    put("ids", "资源名称");
}


@Override
public void initBeWrapped(){
    putFieldWrapperMethodName("pid", "getSingleRoleName");
    putFieldWrapperMethodName("deptid", "getDeptName");
    putFieldWrapperMethodName("roleId", "getSingleRoleName");
    putFieldWrapperMethodName("ids", "getMenuNames");
}


}
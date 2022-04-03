package cn.maxcj.core.common.constant.factory;
 import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.maxcj.core.common.constant.cache.Cache;
import cn.maxcj.core.common.constant.cache.CacheKey;
import cn.maxcj.core.common.constant.state.ManagerStatus;
import cn.maxcj.core.common.constant.state.MenuStatus;
import cn.maxcj.core.log.LogObjectHolder;
import cn.maxcj.modular.system.dao;
import cn.maxcj.modular.system.model;
import cn.stylefeng.roses.core.util.SpringContextHolder;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import cn.maxcj.Interface.RoleMapper;
import cn.maxcj.Interface.DeptMapper;
import cn.maxcj.Interface.DictMapper;
import cn.maxcj.Interface.UserMapper;
import cn.maxcj.Interface.MenuMapper;
import cn.maxcj.Interface.ActivityMapper;
import cn.maxcj.DTO.Role;
import cn.maxcj.DTO.User;
import cn.maxcj.DTO.Menu;
import cn.maxcj.DTO.Dict;
import cn.maxcj.DTO.Dept;
import cn.maxcj.DTO.Notice;
@Component
@DependsOn("springContextHolder")
public class ConstantFactory implements IConstantFactory{

 private  RoleMapper roleMapper;

 private  DeptMapper deptMapper;

 private  DictMapper dictMapper;

 private  UserMapper userMapper;

 private  MenuMapper menuMapper;

 private  ActivityMapper activityMapper;

 private  NoticeMapper noticeMapper;


@Override
@Cacheable(value = Cache.CONSTANT, key = "'" + CacheKey.SINGLE_ROLE_NAME + "'+#roleId")
public String getSingleRoleName(Integer roleId){
    if (0 == roleId) {
        return "--";
    }
    Role roleObj = roleMapper.selectById(roleId);
    if (ToolUtil.isNotEmpty(roleObj) && ToolUtil.isNotEmpty(roleObj.getName())) {
        return roleObj.getName();
    }
    return "";
}


@Override
public String getUserAccountById(Integer userId){
    User user = userMapper.selectById(userId);
    if (user != null) {
        return user.getAccount();
    } else {
        return "--";
    }
}


@Override
public String getMenuNameByCode(String code){
    if (ToolUtil.isEmpty(code)) {
        return "";
    } else {
        Menu param = new Menu();
        param.setCode(code);
        Menu menu = menuMapper.selectOne(param);
        if (menu == null) {
            return "";
        } else {
            return menu.getName();
        }
    }
}


@Override
public String getUserNameById(Integer userId){
    User user = userMapper.selectById(userId);
    if (user != null) {
        return user.getName();
    } else {
        return "--";
    }
}


@Override
public String getMenuNames(String menuIds){
    Integer[] menus = Convert.toIntArray(menuIds);
    StringBuilder sb = new StringBuilder();
    for (int menu : menus) {
        Menu menuObj = menuMapper.selectById(menu);
        if (ToolUtil.isNotEmpty(menuObj) && ToolUtil.isNotEmpty(menuObj.getName())) {
            sb.append(menuObj.getName()).append(",");
        }
    }
    return StrUtil.removeSuffix(sb.toString(), ",");
}


@Override
public String getSexName(Integer sex){
    return getDictsByName("性别", sex);
}


@Override
public String getDictsByName(String name,Integer val){
    Dict temp = new Dict();
    temp.setName(name);
    Dict dict = dictMapper.selectOne(temp);
    if (dict == null) {
        return "";
    } else {
        Wrapper<Dict> wrapper = new EntityWrapper<>();
        wrapper = wrapper.eq("pid", dict.getId());
        List<Dict> dicts = dictMapper.selectList(wrapper);
        for (Dict item : dicts) {
            if (item.getNum() != null && item.getNum().equals(val)) {
                return item.getName();
            }
        }
        return "";
    }
}


@Override
@Cacheable(value = Cache.CONSTANT, key = "'" + CacheKey.SINGLE_ROLE_TIP + "'+#roleId")
public String getSingleRoleTip(Integer roleId){
    if (0 == roleId) {
        return "--";
    }
    Role roleObj = roleMapper.selectById(roleId);
    if (ToolUtil.isNotEmpty(roleObj) && ToolUtil.isNotEmpty(roleObj.getName())) {
        return roleObj.getTips();
    }
    return "";
}


@Override
public List<Dict> findInDict(Integer id){
    if (ToolUtil.isEmpty(id)) {
        return null;
    } else {
        EntityWrapper<Dict> wrapper = new EntityWrapper<>();
        List<Dict> dicts = dictMapper.selectList(wrapper.eq("pid", id));
        if (dicts == null || dicts.size() == 0) {
            return null;
        } else {
            return dicts;
        }
    }
}


@Override
public String getActivity_state(Integer stateCode){
    return getDictsByName("一个活动的状态", stateCode);
}


@Override
public String getStatusName(Integer status){
    return ManagerStatus.valueOf(status);
}


@Override
public String getFinance(Integer finance_catory){
    return getDictsByName("社团财务类型", finance_catory);
}


@Override
public List<Integer> getParentDeptIds(Integer deptid){
    Dept dept = deptMapper.selectById(deptid);
    String pids = dept.getPids();
    String[] split = pids.split(",");
    ArrayList<Integer> parentDeptIds = new ArrayList<>();
    for (String s : split) {
        parentDeptIds.add(Integer.valueOf(StrUtil.removeSuffix(StrUtil.removePrefix(s, "["), "]")));
    }
    return parentDeptIds;
}


public IConstantFactory me(){
    return SpringContextHolder.getBean("constantFactory");
}


@Override
public String getMenuName(Long menuId){
    if (ToolUtil.isEmpty(menuId)) {
        return "";
    } else {
        Menu menu = menuMapper.selectById(menuId);
        if (menu == null) {
            return "";
        } else {
            return menu.getName();
        }
    }
}


@Override
public String getUserAcademy(Integer academyCode){
    return getDictsByName("学院名称", academyCode);
}


@Override
public String getDictName(Integer dictId){
    if (ToolUtil.isEmpty(dictId)) {
        return "";
    } else {
        Dict dict = dictMapper.selectById(dictId);
        if (dict == null) {
            return "";
        } else {
            return dict.getName();
        }
    }
}


@Override
@Cacheable(value = Cache.CONSTANT, key = "'" + CacheKey.ROLES_NAME + "'+#roleIds")
public String getRoleName(String roleIds){
    if (ToolUtil.isEmpty(roleIds)) {
        return "";
    }
    Integer[] roles = Convert.toIntArray(roleIds);
    StringBuilder sb = new StringBuilder();
    for (int role : roles) {
        Role roleObj = roleMapper.selectById(role);
        if (ToolUtil.isNotEmpty(roleObj) && ToolUtil.isNotEmpty(roleObj.getName())) {
            sb.append(roleObj.getName()).append(",");
        }
    }
    return StrUtil.removeSuffix(sb.toString(), ",");
}


@Override
public String getActivity_category(Integer categoryCode){
    return getDictsByName("活动类别", categoryCode);
}


@Override
public String getCacheObject(String para){
    return LogObjectHolder.me().get().toString();
}


@Override
@Cacheable(value = Cache.CONSTANT, key = "'" + CacheKey.DEPT_NAME + "'+#deptId")
public String getDeptName(Integer deptId){
    Dept dept = deptMapper.selectById(deptId);
    if (ToolUtil.isNotEmpty(dept) && ToolUtil.isNotEmpty(dept.getFullname())) {
        return dept.getFullname();
    }
    return "";
}


@Override
public String getNoticeTitle(Integer dictId){
    if (ToolUtil.isEmpty(dictId)) {
        return "";
    } else {
        Notice notice = noticeMapper.selectById(dictId);
        if (notice == null) {
            return "";
        } else {
            return notice.getTitle();
        }
    }
}


@Override
public List<Integer> getSubDeptId(Integer deptid){
    Wrapper<Dept> wrapper = new EntityWrapper<>();
    wrapper = wrapper.like("pids", "%[" + deptid + "]%");
    List<Dept> depts = this.deptMapper.selectList(wrapper);
    ArrayList<Integer> deptids = new ArrayList<>();
    if (depts != null && depts.size() > 0) {
        for (Dept dept : depts) {
            deptids.add(dept.getId());
        }
    }
    return deptids;
}


@Override
public String getActivityName(Integer activity_id){
    return activityMapper.getActivityName(activity_id);
}


@Override
public String getClub_category(Integer category){
    return getDictsByName("社团类别", category);
}


@Override
public String getMenuStatusName(Integer status){
    return MenuStatus.valueOf(status);
}


}
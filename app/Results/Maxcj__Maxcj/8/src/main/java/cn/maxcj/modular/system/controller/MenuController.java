package cn.maxcj.modular.system.controller;
 import cn.hutool.core.bean.BeanUtil;
import cn.maxcj.core.common.annotion.BussinessLog;
import cn.maxcj.core.common.annotion.Permission;
import cn.maxcj.core.common.constant.Const;
import cn.maxcj.core.common.constant.dictmap.MenuDict;
import cn.maxcj.core.common.constant.factory.ConstantFactory;
import cn.maxcj.core.common.constant.state.MenuStatus;
import cn.maxcj.core.common.exception.BizExceptionEnum;
import cn.maxcj.core.common.node.ZTreeNode;
import cn.maxcj.core.log.LogObjectHolder;
import cn.maxcj.modular.system.model.Menu;
import cn.maxcj.modular.system.service.IMenuService;
import cn.maxcj.modular.system.warpper.MenuWarpper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import cn.maxcj.DTO.ZTreeNode;
@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController{

 private  String PREFIX;

@Autowired
 private  IMenuService menuService;


@Permission(Const.ADMIN_NAME)
@RequestMapping(value = "/add")
@BussinessLog(value = "菜单新增", key = "name", dict = MenuDict.class)
@ResponseBody
public ResponseData add(Menu menu,BindingResult result){
    if (result.hasErrors()) {
        throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
    }
    // 判断是否存在该编号
    String existedMenuName = ConstantFactory.me().getMenuNameByCode(menu.getCode());
    if (ToolUtil.isNotEmpty(existedMenuName)) {
        throw new ServiceException(BizExceptionEnum.EXISTED_THE_MENU);
    }
    // 设置父级菜单编号
    menuSetPcode(menu);
    menu.setStatus(MenuStatus.ENABLE.getCode());
    this.menuService.insert(menu);
    return SUCCESS_TIP;
}


@RequestMapping(value = "/view/{menuId}")
@ResponseBody
public ResponseData view(Long menuId){
    if (ToolUtil.isEmpty(menuId)) {
        throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
    }
    this.menuService.selectById(menuId);
    return SUCCESS_TIP;
}


@RequestMapping(value = "/menuTreeList")
@ResponseBody
public List<ZTreeNode> menuTreeList(){
    return this.menuService.menuTreeList();
}


@Permission(Const.ADMIN_NAME)
@RequestMapping(value = "/menu_edit/{menuId}")
public String menuEdit(Long menuId,Model model){
    if (ToolUtil.isEmpty(menuId)) {
        throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
    }
    Menu menu = this.menuService.selectById(menuId);
    // 获取父级菜单的id
    Menu temp = new Menu();
    temp.setCode(menu.getPcode());
    Menu pMenu = this.menuService.selectOne(new EntityWrapper<>(temp));
    // 如果父级是顶级菜单
    if (pMenu == null) {
        menu.setPcode("0");
    } else {
        // 设置父级菜单的code为父级菜单的id
        menu.setPcode(String.valueOf(pMenu.getId()));
    }
    Map<String, Object> menuMap = BeanUtil.beanToMap(menu);
    // menuMap.put("pcodeName", ConstantFactory.me().getMenuNameByCode(temp.getCode()));
    model.addAttribute("menu", menuMap);
    LogObjectHolder.me().set(menu);
    return PREFIX + "menu_edit.html";
}


@Permission(Const.ADMIN_NAME)
@RequestMapping(value = "/edit")
@BussinessLog(value = "修改菜单", key = "name", dict = MenuDict.class)
@ResponseBody
public ResponseData edit(Menu menu,BindingResult result){
    if (result.hasErrors()) {
        throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
    }
    // 设置父级菜单编号
    menuSetPcode(menu);
    this.menuService.updateById(menu);
    return SUCCESS_TIP;
}


@RequestMapping(value = "/menu_add")
public String menuAdd(){
    return PREFIX + "menu_add.html";
}


@RequestMapping(value = "/selectMenuTreeList")
@ResponseBody
public List<ZTreeNode> selectMenuTreeList(){
    List<ZTreeNode> roleTreeList = this.menuService.menuTreeList();
    roleTreeList.add(ZTreeNode.createParent());
    return roleTreeList;
}


public void menuSetPcode(Menu menu){
    if (ToolUtil.isEmpty(menu.getPcode()) || menu.getPcode().equals("0")) {
        menu.setPcode("0");
        menu.setPcodes("[0],");
        menu.setLevels(1);
    } else {
        long code = Long.parseLong(menu.getPcode());
        Menu pMenu = menuService.selectById(code);
        Integer pLevels = pMenu.getLevels();
        menu.setPcode(pMenu.getCode());
        // 如果编号和父编号一致会导致无限递归
        if (menu.getCode().equals(menu.getPcode())) {
            throw new ServiceException(BizExceptionEnum.MENU_PCODE_COINCIDENCE);
        }
        menu.setLevels(pLevels + 1);
        menu.setPcodes(pMenu.getPcodes() + "[" + pMenu.getCode() + "],");
    }
}


@RequestMapping("")
public String index(){
    return PREFIX + "menu.html";
}


@Permission(Const.ADMIN_NAME)
@RequestMapping(value = "/list")
@ResponseBody
public Object list(String menuName,String level){
    List<Map<String, Object>> menus = this.menuService.selectMenus(menuName, level);
    return super.warpObject(new MenuWarpper(menus));
}


@Permission(Const.ADMIN_NAME)
@RequestMapping(value = "/remove")
@BussinessLog(value = "删除菜单", key = "menuId", dict = MenuDict.class)
@ResponseBody
public ResponseData remove(Long menuId){
    if (ToolUtil.isEmpty(menuId)) {
        throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
    }
    // 缓存菜单的名称
    LogObjectHolder.me().set(ConstantFactory.me().getMenuName(menuId));
    this.menuService.delMenuContainSubMenus(menuId);
    return SUCCESS_TIP;
}


@RequestMapping(value = "/menuTreeListByRoleId/{roleId}")
@ResponseBody
public List<ZTreeNode> menuTreeListByRoleId(Integer roleId){
    List<Long> menuIds = this.menuService.getMenuIdsByRoleId(roleId);
    if (ToolUtil.isEmpty(menuIds)) {
        return this.menuService.menuTreeList();
    } else {
        return this.menuService.menuTreeListByMenuIds(menuIds);
    }
}


}
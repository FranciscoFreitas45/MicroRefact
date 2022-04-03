package cn.maxcj.modular.system.controller;
 import cn.maxcj.core.common.annotion.BussinessLog;
import cn.maxcj.core.common.annotion.Permission;
import cn.maxcj.core.common.constant.Const;
import cn.maxcj.core.common.constant.dictmap.DictMap;
import cn.maxcj.core.common.constant.factory.ConstantFactory;
import cn.maxcj.core.common.exception.BizExceptionEnum;
import cn.maxcj.core.log.LogObjectHolder;
import cn.maxcj.modular.system.model.Dict;
import cn.maxcj.modular.system.service.IDictService;
import cn.maxcj.modular.system.warpper.DictWarpper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import java.util.Map;
@Controller
@RequestMapping("/dict")
public class DictController extends BaseController{

 private  String PREFIX;

@Autowired
 private  IDictService dictService;


@BussinessLog(value = "添加字典记录", key = "dictName,dictValues", dict = DictMap.class)
@RequestMapping(value = "/add")
@Permission(Const.ADMIN_NAME)
@ResponseBody
public Object add(String dictCode,String dictTips,String dictName,String dictValues){
    if (ToolUtil.isOneEmpty(dictCode, dictName, dictValues)) {
        throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
    }
    this.dictService.addDict(dictCode, dictName, dictTips, dictValues);
    return SUCCESS_TIP;
}


@RequestMapping("/dict_add")
public String deptAdd(){
    return PREFIX + "dict_add.html";
}


@Permission(Const.ADMIN_NAME)
@RequestMapping("/dict_edit/{dictId}")
public String deptUpdate(Integer dictId,Model model){
    Dict dict = dictService.selectById(dictId);
    model.addAttribute("dict", dict);
    List<Dict> subDicts = dictService.selectList(new EntityWrapper<Dict>().eq("pid", dictId));
    model.addAttribute("subDicts", subDicts);
    LogObjectHolder.me().set(dict);
    return PREFIX + "dict_edit.html";
}


@RequestMapping("")
public String index(){
    return PREFIX + "dict.html";
}


@BussinessLog(value = "修改字典", key = "dictName,dictValues", dict = DictMap.class)
@RequestMapping(value = "/update")
@Permission(Const.ADMIN_NAME)
@ResponseBody
public Object update(Integer dictId,String dictCode,String dictName,String dictTips,String dictValues){
    if (ToolUtil.isOneEmpty(dictId, dictCode, dictName, dictValues)) {
        throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
    }
    dictService.editDict(dictId, dictCode, dictName, dictTips, dictValues);
    return SUCCESS_TIP;
}


@RequestMapping(value = "/detail/{dictId}")
@Permission(Const.ADMIN_NAME)
@ResponseBody
public Object detail(Integer dictId){
    return dictService.selectById(dictId);
}


@RequestMapping(value = "/list")
@Permission(Const.ADMIN_NAME)
@ResponseBody
public Object list(String condition){
    List<Map<String, Object>> list = this.dictService.list(condition);
    return super.warpObject(new DictWarpper(list));
}


@BussinessLog(value = "删除字典记录", key = "dictId", dict = DictMap.class)
@RequestMapping(value = "/delete")
@Permission(Const.ADMIN_NAME)
@ResponseBody
public Object delete(Integer dictId){
    // 缓存被删除的名称
    LogObjectHolder.me().set(ConstantFactory.me().getDictName(dictId));
    this.dictService.delteDict(dictId);
    return SUCCESS_TIP;
}


}
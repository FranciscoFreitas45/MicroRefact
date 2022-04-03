package com.zis.purchase.controller;
 import javax.validation.Valid;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zis.common.cache.SysVarCache;
import com.zis.toolkit.dto.UpdateSysVarDTO;
@Controller
@RequestMapping(value = "/purchase")
public class SysVarUpdateController {

@Autowired
 private  SysVarCache sysVarCache;


@RequiresPermissions(value = { "toolkit:toolkit" })
@RequestMapping(value = "/updateSysVarAction")
public String updateSysVar(UpdateSysVarDTO dto,BindingResult br){
    if (br.hasErrors()) {
        return "purchase/updateSysVar";
    }
    sysVarCache.updateSysVar(dto.getDepKey(), dto.getDepValue());
    return "redirect:/purchase/querySysVarAction";
}


}
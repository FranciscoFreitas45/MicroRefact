package org.danyuan.application.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SysDbmsTabsColsInfoController {

 private SysDbmsTabsColsInfoDao sysdbmstabscolsinfodao;


@PutMapping
("/setTabsUuid/{id}")
public void setTabsUuid(@PathVariable(name = "id") String id,@RequestParam(name = "tabsUuid") String tabsUuid){
 sysdbmstabscolsinfodao.setTabsUuid(id,tabsUuid);
}


}
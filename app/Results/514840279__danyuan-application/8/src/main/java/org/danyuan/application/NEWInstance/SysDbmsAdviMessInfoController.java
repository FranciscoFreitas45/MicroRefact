package org.danyuan.application.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SysDbmsAdviMessInfoController {

 private SysDbmsAdviMessInfoDao sysdbmsadvimessinfodao;


@PutMapping
("/setExecuteSql/{id}")
public void setExecuteSql(@PathVariable(name = "id") String id,@RequestParam(name = "executeSql") String executeSql){
 sysdbmsadvimessinfodao.setExecuteSql(id,executeSql);
}


}
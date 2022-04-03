package com.cym.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ConfControllerController {

 private ConfController confcontroller;


@GetMapping
("/checkBase")
public JsonResult checkBase(){
  return confcontroller.checkBase();
}


@GetMapping
("/reload")
public JsonResult reload(@RequestParam(name = "nginxPath") String nginxPath,@RequestParam(name = "nginxExe") String nginxExe,@RequestParam(name = "nginxDir") String nginxDir){
  return confcontroller.reload(nginxPath,nginxExe,nginxDir);
}


@GetMapping
("/getReplaceJson")
public String getReplaceJson(){
  return confcontroller.getReplaceJson();
}


@GetMapping
("/replace")
public JsonResult replace(@RequestParam(name = "json") String json,@RequestParam(name = "request") HttpServletRequest request,@RequestParam(name = "adminName") String adminName){
  return confcontroller.replace(json,request,adminName);
}


@GetMapping
("/runCmd")
public JsonResult runCmd(@RequestParam(name = "cmd") String cmd,@RequestParam(name = "type") String type){
  return confcontroller.runCmd(cmd,type);
}


}
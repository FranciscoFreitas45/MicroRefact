package com.cym.Interface;
public interface NginxApiController {

   public JsonResult<List<String>> getNginxStartCmd();
   public JsonResult<List<String>> getNginxStopCmd();
}
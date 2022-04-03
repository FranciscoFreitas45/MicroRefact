package com.lingxiang2014.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PluginServiceController {

 private PluginService pluginservice;


@GetMapping
("/getStoragePlugins")
public List<StoragePlugin> getStoragePlugins(@RequestParam(name = "isEnabled") boolean isEnabled){
  return pluginservice.getStoragePlugins(isEnabled);
}


}
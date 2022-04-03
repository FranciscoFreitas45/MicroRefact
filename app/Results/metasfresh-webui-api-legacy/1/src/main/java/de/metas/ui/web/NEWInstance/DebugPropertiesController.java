package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DebugPropertiesController {

 private DebugProperties debugproperties;


@GetMapping
("/isEmpty")
public boolean isEmpty(){
  return debugproperties.isEmpty();
}


@GetMapping
("/ofNullableMap")
public DebugProperties ofNullableMap(@RequestParam(name = "map") Map<String,?> map){
  return debugproperties.ofNullableMap(map);
}


}
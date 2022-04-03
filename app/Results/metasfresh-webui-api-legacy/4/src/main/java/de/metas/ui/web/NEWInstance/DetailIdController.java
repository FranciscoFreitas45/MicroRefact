package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DetailIdController {

 private DetailId detailid;


@GetMapping
("/fromJson")
public DetailId fromJson(@RequestParam(name = "json") String json){
  return detailid.fromJson(json);
}


}
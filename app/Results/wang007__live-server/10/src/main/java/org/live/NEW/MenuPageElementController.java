package org.live.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.live.sys.entity.Menu;
@RestController
@CrossOrigin
public class MenuPageElementController {

@Autowired
 private MenuPageElementService menupageelementservice;


@GetMapping
("/PageElement/{id}/Menu/getMenu")
public Menu getMenu(@PathVariable(name="id") String idVM7Z){
return menupageelementservice.getMenu(idVM7Z);
}


@PutMapping
("/PageElement/{id}/Menu/setMenu")
public void setMenu(@PathVariable(name="id") String idVM7Z,@RequestBody Menu menu){
menupageelementservice.setMenu(idVM7Z,menu);
}


}
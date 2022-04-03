package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class RightDataController {

 private RightData rightdata;

 private RightData rightdata;


@PutMapping
("/setExpanded")
public void setExpanded(@RequestParam(name = "expanded") Boolean expanded){
rightdata.setExpanded(expanded);
}


@PutMapping
("/setMenuId")
public void setMenuId(@RequestParam(name = "menuId") String menuId){
rightdata.setMenuId(menuId);
}


@PutMapping
("/setMenuName")
public void setMenuName(@RequestParam(name = "menuName") String menuName){
rightdata.setMenuName(menuName);
}


@PutMapping
("/setPmenuId")
public void setPmenuId(@RequestParam(name = "pmenuId") String pmenuId){
rightdata.setPmenuId(pmenuId);
}


}
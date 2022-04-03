package run.halo.app.controller.content.api;
 import org.springframework.data.domain.Sort.Direction.DESC;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import run.halo.app.model.dto.MenuDTO;
import run.halo.app.model.vo.MenuVO;
import run.halo.app.service.MenuService;
@RestController("ApiContentMenuController")
@RequestMapping("/api/content/menus")
public class MenuController {

 private  MenuService menuService;

public MenuController(MenuService menuService) {
    this.menuService = menuService;
}
@GetMapping(value = "tree_view")
@ApiOperation("Lists menus with tree view")
public List<MenuVO> listMenusTree(Sort sort){
    return menuService.listAsTree(sort);
}


@GetMapping
@ApiOperation("Lists all menus")
public List<MenuDTO> listAll(Sort sort){
    return menuService.listDtos(sort);
}


}
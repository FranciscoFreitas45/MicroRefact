package run.halo.app.controller.admin.api;
 import org.springframework.data.domain.Sort.Direction.ASC;
import org.springframework.data.domain.Sort.Direction.DESC;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import run.halo.app.model.dto.MenuDTO;
import run.halo.app.model.dto.base.InputConverter;
import run.halo.app.model.entity.Menu;
import run.halo.app.model.params.MenuParam;
import run.halo.app.model.vo.MenuVO;
import run.halo.app.service.MenuService;
@RestController
@RequestMapping("/api/admin/menus")
public class MenuController {

 private  MenuService menuService;

public MenuController(MenuService menuService) {
    this.menuService = menuService;
}
@GetMapping("tree_view")
@ApiOperation("Lists menus as tree")
public List<MenuVO> listAsTree(Sort sort){
    return menuService.listAsTree(sort.and(Sort.by(ASC, "priority")));
}


@PostMapping
@ApiOperation("Creates a menu")
public MenuDTO createBy(MenuParam menuParam){
    return new MenuDTO().convertFrom(menuService.createBy(menuParam));
}


@DeleteMapping("/batch")
public List<MenuDTO> deleteBatchBy(List<Integer> menuIds){
    List<Menu> menus = menuService.listAllByIds(menuIds);
    menuService.removeInBatch(menuIds);
    return menus.stream().map(menu -> (MenuDTO) new MenuDTO().convertFrom(menu)).collect(Collectors.toList());
}


@PostMapping("/batch")
public List<MenuDTO> createBatchBy(List<MenuParam> menuParams){
    List<Menu> menus = menuParams.stream().map(InputConverter::convertTo).collect(Collectors.toList());
    return menuService.createInBatch(menus).stream().map(menu -> (MenuDTO) new MenuDTO().convertFrom(menu)).collect(Collectors.toList());
}


@GetMapping("teams")
@ApiOperation("Lists all menu teams")
public List<String> teams(){
    return menuService.listAllTeams();
}


@PutMapping("{menuId:\\d+}")
@ApiOperation("Updates a menu")
public MenuDTO updateBy(Integer menuId,MenuParam menuParam){
    // Get the menu
    Menu menu = menuService.getById(menuId);
    // Update changed properties of the menu
    menuParam.update(menu);
    // Update menu in database
    return new MenuDTO().convertFrom(menuService.update(menu));
}


@GetMapping("team/tree_view")
@ApiOperation("Lists menus as tree by team")
public List<MenuVO> listDefaultsAsTreeByTeam(Sort sort,String team){
    return menuService.listByTeamAsTree(team, sort);
}


@GetMapping("{menuId:\\d+}")
@ApiOperation("Gets menu detail by id")
public MenuDTO getBy(Integer menuId){
    return new MenuDTO().convertFrom(menuService.getById(menuId));
}


@PutMapping("/batch")
public List<MenuDTO> updateBatchBy(List<MenuParam> menuParams){
    List<Menu> menus = menuParams.stream().map(InputConverter::convertTo).collect(Collectors.toList());
    return menuService.updateInBatch(menus).stream().map(menu -> (MenuDTO) new MenuDTO().convertFrom(menu)).collect(Collectors.toList());
}


@DeleteMapping("{menuId:\\d+}")
@ApiOperation("Deletes a menu")
public MenuDTO deleteBy(Integer menuId){
    List<Menu> menus = menuService.listByParentId(menuId);
    if (null != menus && menus.size() > 0) {
        menus.forEach(menu -> {
            menu.setParentId(0);
            menuService.update(menu);
        });
    }
    return new MenuDTO().convertFrom(menuService.removeById(menuId));
}


@GetMapping
@ApiOperation("Lists all menus")
public List<MenuDTO> listAll(Sort sort){
    return menuService.listDtos(sort.and(Sort.by(ASC, "priority")));
}


}
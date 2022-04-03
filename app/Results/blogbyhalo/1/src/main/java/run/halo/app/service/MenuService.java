package run.halo.app.service;
 import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;
import run.halo.app.model.dto.MenuDTO;
import run.halo.app.model.entity.Menu;
import run.halo.app.model.params.MenuParam;
import run.halo.app.model.vo.MenuTeamVO;
import run.halo.app.model.vo.MenuVO;
import run.halo.app.service.base.CrudService;
public interface MenuService extends CrudService<Menu, Integer>{


@NonNull
public List<MenuTeamVO> listTeamVos(Sort sort)
;

public List<MenuVO> listAsTree(Sort sort)
;

public List<Menu> listByParentId(Integer id)
;

@NonNull
public Menu createBy(MenuParam menuParam)
;

public List<String> listAllTeams()
;

public List<MenuDTO> listByTeam(String team,Sort sort)
;

public List<MenuVO> listByTeamAsTree(String team,Sort sort)
;

@NonNull
public List<MenuDTO> listDtos(Sort sort)
;

}
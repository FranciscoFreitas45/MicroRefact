package run.halo.app.service;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;
import run.halo.app.model.dto.PhotoDTO;
import run.halo.app.model.entity.Photo;
import run.halo.app.model.params.PhotoParam;
import run.halo.app.model.params.PhotoQuery;
import run.halo.app.model.vo.PhotoTeamVO;
import run.halo.app.service.base.CrudService;
public interface PhotoService extends CrudService<Photo, Integer>{


public List<PhotoTeamVO> listTeamVos(Sort sort)
;

public Page<PhotoDTO> pageBy(Pageable pageable)
;

@NonNull
public Photo createBy(PhotoParam photoParam)
;

@NonNull
public Page<PhotoDTO> pageDtosBy(Pageable pageable,PhotoQuery photoQuery)
;

public List<String> listAllTeams()
;

public List<PhotoDTO> listByTeam(String team,Sort sort)
;

public List<PhotoDTO> listDtos(Sort sort)
;

}
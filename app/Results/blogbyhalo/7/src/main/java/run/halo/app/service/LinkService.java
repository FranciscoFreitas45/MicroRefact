package run.halo.app.service;
 import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;
import run.halo.app.model.dto.LinkDTO;
import run.halo.app.model.entity.Link;
import run.halo.app.model.params.LinkParam;
import run.halo.app.model.vo.LinkTeamVO;
import run.halo.app.service.base.CrudService;
public interface LinkService extends CrudService<Link, Integer>{


@NonNull
public List<LinkTeamVO> listTeamVos(Sort sort)
;

@NonNull
public Link createBy(LinkParam linkParam)
;

public boolean existByUrl(String url)
;

public List<String> listAllTeams()
;

@NonNull
public Link updateBy(Integer id,LinkParam linkParam)
;

public boolean existByName(String name)
;

@NonNull
public List<Link> listAllByRandom()
;

@NonNull
public List<LinkTeamVO> listTeamVosByRandom(Sort sort)
;

@NonNull
public List<LinkDTO> listDtos(Sort sort)
;

}
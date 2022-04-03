package run.halo.app.controller.content.api;
 import org.springframework.data.domain.Sort.Direction.DESC;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import run.halo.app.model.dto.LinkDTO;
import run.halo.app.model.vo.LinkTeamVO;
import run.halo.app.service.LinkService;
@RestController("ApiContentLinkController")
@RequestMapping("/api/content/links")
public class LinkController {

 private  LinkService linkService;

public LinkController(LinkService linkService) {
    this.linkService = linkService;
}
@GetMapping("team_view")
@ApiOperation("List all links with team view")
public List<LinkTeamVO> listTeamVos(Sort sort){
    return linkService.listTeamVos(sort);
}


@GetMapping
@ApiOperation("List all links")
public List<LinkDTO> listLinks(Sort sort){
    return linkService.listDtos(sort);
}


}
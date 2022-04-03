package run.halo.app.controller.admin.api;
 import org.springframework.data.domain.Sort.Direction.ASC;
import org.springframework.data.domain.Sort.Direction.DESC;
import io.swagger.annotations.ApiOperation;
import java.util.List;
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
import org.springframework.web.bind.annotation.RestController;
import run.halo.app.model.dto.LinkDTO;
import run.halo.app.model.entity.Link;
import run.halo.app.model.params.LinkParam;
import run.halo.app.service.LinkService;
@RestController
@RequestMapping("/api/admin/links")
public class LinkController {

 private  LinkService linkService;

public LinkController(LinkService linkService) {
    this.linkService = linkService;
}
@GetMapping
@ApiOperation("Lists links")
public List<LinkDTO> listLinks(Sort sort){
    return linkService.listDtos(sort.and(Sort.by(ASC, "priority")));
}


@PostMapping
@ApiOperation("Creates a link")
public LinkDTO createBy(LinkParam linkParam){
    Link link = linkService.createBy(linkParam);
    return new LinkDTO().convertFrom(link);
}


@GetMapping("teams")
@ApiOperation("Lists all link teams")
public List<String> teams(){
    return linkService.listAllTeams();
}


@PutMapping("{id:\\d+}")
@ApiOperation("Updates a link")
public LinkDTO updateBy(Integer id,LinkParam linkParam){
    Link link = linkService.updateBy(id, linkParam);
    return new LinkDTO().convertFrom(link);
}


@GetMapping("{id:\\d+}")
@ApiOperation("Gets link detail by id")
public LinkDTO getBy(Integer id){
    return new LinkDTO().convertFrom(linkService.getById(id));
}


@DeleteMapping("{id:\\d+}")
@ApiOperation("Delete link by id")
public void deletePermanently(Integer id){
    linkService.removeById(id);
}


}
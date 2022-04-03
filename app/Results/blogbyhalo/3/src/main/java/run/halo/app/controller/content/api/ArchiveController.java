package run.halo.app.controller.content.api;
 import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import run.halo.app.model.vo.ArchiveMonthVO;
import run.halo.app.model.vo.ArchiveYearVO;
import run.halo.app.service.PostService;
@RestController("ApiContentArchiveController")
@RequestMapping("/api/content/archives")
public class ArchiveController {

 private  PostService postService;

public ArchiveController(PostService postService) {
    this.postService = postService;
}
@GetMapping("months")
public List<ArchiveMonthVO> listMonthArchives(){
    return postService.listMonthArchives();
}


@GetMapping("years")
public List<ArchiveYearVO> listYearArchives(){
    return postService.listYearArchives();
}


}
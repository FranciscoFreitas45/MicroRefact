package run.halo.app.controller.content;
 import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import run.halo.app.controller.content.model.PostModel;
import run.halo.app.model.entity.Post;
import run.halo.app.model.enums.PostPermalinkType;
import run.halo.app.service.OptionService;
import run.halo.app.service.PostService;
import run.halo.app.Interface.OptionService;
@Slf4j
@Controller
@RequestMapping
public class ContentIndexController {

 private  PostService postService;

 private  OptionService optionService;

 private  PostModel postModel;

public ContentIndexController(PostService postService, OptionService optionService, PostModel postModel) {
    this.postService = postService;
    this.optionService = optionService;
    this.postModel = postModel;
}
@GetMapping(value = "page/{page}")
public String index(Model model,Integer page){
    return postModel.list(page, model);
}


}
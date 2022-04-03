package run.halo.app.controller.content;
 import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import run.halo.app.cache.lock.CacheLock;
import run.halo.app.controller.content.model.CategoryModel;
import run.halo.app.controller.content.model.JournalModel;
import run.halo.app.controller.content.model.LinkModel;
import run.halo.app.controller.content.model.PhotoModel;
import run.halo.app.controller.content.model.PostModel;
import run.halo.app.controller.content.model.SheetModel;
import run.halo.app.controller.content.model.TagModel;
import run.halo.app.exception.NotFoundException;
import run.halo.app.exception.UnsupportedException;
import run.halo.app.model.dto.CategoryDTO;
import run.halo.app.model.dto.post.BasePostMinimalDTO;
import run.halo.app.model.entity.Post;
import run.halo.app.model.entity.Sheet;
import run.halo.app.model.enums.EncryptTypeEnum;
import run.halo.app.model.enums.PostPermalinkType;
import run.halo.app.model.enums.PostStatus;
import run.halo.app.model.enums.SheetPermalinkType;
import run.halo.app.service.AuthenticationService;
import run.halo.app.service.CategoryService;
import run.halo.app.service.OptionService;
import run.halo.app.service.PostService;
import run.halo.app.service.SheetService;
import run.halo.app.Interface.SheetModel;
import run.halo.app.Interface.JournalModel;
import run.halo.app.Interface.PhotoModel;
import run.halo.app.Interface.LinkModel;
import run.halo.app.Interface.OptionService;
import run.halo.app.Interface.SheetService;
@Slf4j
@Controller
@RequestMapping
public class ContentContentController {

 private  PostModel postModel;

 private  SheetModel sheetModel;

 private  CategoryModel categoryModel;

 private  TagModel tagModel;

 private  JournalModel journalModel;

 private  PhotoModel photoModel;

 private  LinkModel linkModel;

 private  OptionService optionService;

 private  PostService postService;

 private  SheetService sheetService;

 private  AuthenticationService authenticationService;

 private  CategoryService categoryService;

public ContentContentController(PostModel postModel, SheetModel sheetModel, CategoryModel categoryModel, TagModel tagModel, JournalModel journalModel, PhotoModel photoModel, LinkModel linkModel, OptionService optionService, PostService postService, SheetService sheetService, AuthenticationService authenticationService, CategoryService categoryService) {
    this.postModel = postModel;
    this.sheetModel = sheetModel;
    this.categoryModel = categoryModel;
    this.tagModel = tagModel;
    this.journalModel = journalModel;
    this.photoModel = photoModel;
    this.linkModel = linkModel;
    this.optionService = optionService;
    this.postService = postService;
    this.sheetService = sheetService;
    this.authenticationService = authenticationService;
    this.categoryService = categoryService;
}
public String doAuthenticationPost(String slug,String password){
    Post post = postService.getBy(PostStatus.INTIMATE, slug);
    post.setSlug(URLEncoder.encode(post.getSlug(), StandardCharsets.UTF_8.name()));
    authenticationService.postAuthentication(post, password);
    BasePostMinimalDTO postMinimalDTO = postService.convertToMinimal(post);
    StringBuilder redirectUrl = new StringBuilder();
    if (!optionService.isEnabledAbsolutePath()) {
        redirectUrl.append(optionService.getBlogBaseUrl());
    }
    redirectUrl.append(postMinimalDTO.getFullPath());
    return redirectUrl.toString();
}


@PostMapping(value = "content/{type}/{slug:.*}/authentication")
@CacheLock(traceRequest = true, expired = 2)
public String password(String type,String slug,String password){
    String redirectUrl;
    if (EncryptTypeEnum.POST.getName().equals(type)) {
        redirectUrl = doAuthenticationPost(slug, password);
    } else if (EncryptTypeEnum.CATEGORY.getName().equals(type)) {
        redirectUrl = doAuthenticationCategory(slug, password);
    } else {
        throw new UnsupportedException("未知的加密类型");
    }
    return "redirect:" + redirectUrl;
}


public String doAuthenticationCategory(String slug,String password){
    CategoryDTO category = categoryService.convertTo(categoryService.getBySlugOfNonNull(slug, true));
    authenticationService.categoryAuthentication(category.getId(), password);
    StringBuilder redirectUrl = new StringBuilder();
    if (!optionService.isEnabledAbsolutePath()) {
        redirectUrl.append(optionService.getBlogBaseUrl());
    }
    redirectUrl.append(category.getFullPath());
    return redirectUrl.toString();
}


@GetMapping("{year:\\d+}/{month:\\d+}/{day:\\d+}/{slug}")
public String content(Integer year,Integer month,Integer day,String slug,String token,Model model){
    PostPermalinkType postPermalinkType = optionService.getPostPermalinkType();
    if (postPermalinkType.equals(PostPermalinkType.DAY)) {
        Post post = postService.getBy(year, month, day, slug);
        return postModel.content(post, token, model);
    }
    throw buildPathNotFoundException();
}


public NotFoundException buildPathNotFoundException(){
    var requestAttributes = RequestContextHolder.currentRequestAttributes();
    var requestUri = "";
    if (requestAttributes instanceof ServletRequestAttributes) {
        requestUri = ((ServletRequestAttributes) requestAttributes).getRequest().getRequestURI();
    }
    return new NotFoundException("无法定位到该路径：" + requestUri);
}


}
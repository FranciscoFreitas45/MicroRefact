package run.halo.app.controller.content;
 import org.springframework.data.domain.Sort.Direction.DESC;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.OptionalLong;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RegExUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import run.halo.app.model.dto.CategoryDTO;
import run.halo.app.model.entity.Category;
import run.halo.app.model.entity.Post;
import run.halo.app.model.enums.PostStatus;
import run.halo.app.model.vo.PostDetailVO;
import run.halo.app.service.CategoryService;
import run.halo.app.service.OptionService;
import run.halo.app.service.PostCategoryService;
import run.halo.app.service.PostService;
import run.halo.app.Interface.OptionService;
@Slf4j
@Controller
public class ContentFeedController {

 private  String UTF_8_SUFFIX;

 private  String XML_INVALID_CHAR;

 private  String XML_MEDIA_TYPE;

 private  String LAST_MODIFIED_HEADER;

 private  PostService postService;

 private  CategoryService categoryService;

 private  PostCategoryService postCategoryService;

 private  OptionService optionService;

 private  FreeMarkerConfigurer freeMarker;

public ContentFeedController(PostService postService, CategoryService categoryService, PostCategoryService postCategoryService, OptionService optionService, FreeMarkerConfigurer freeMarker) {
    this.postService = postService;
    this.categoryService = categoryService;
    this.postCategoryService = postCategoryService;
    this.optionService = optionService;
    this.freeMarker = freeMarker;
}
@NonNull
public Pageable buildPostPageable(int size){
    return PageRequest.of(0, size, Sort.by(DESC, "createTime"));
}


@GetMapping(value = { "feed/categories/{slug}", "feed/categories/{slug}.xml" }, produces = XML_MEDIA_TYPE)
@ResponseBody
public String feed(Model model,String slug,HttpServletResponse response){
    Category category = categoryService.getBySlugOfNonNull(slug);
    CategoryDTO categoryDTO = categoryService.convertTo(category);
    List<PostDetailVO> posts = buildCategoryPosts(buildPostPageable(optionService.getRssPageSize()), categoryDTO);
    model.addAttribute("category", categoryDTO);
    model.addAttribute("posts", posts);
    Timestamp lastModified = this.getLastModifiedTime(posts);
    this.lastModified2ResponseHeader(response, lastModified);
    model.addAttribute("lastModified", lastModified);
    Template template = freeMarker.getConfiguration().getTemplate("common/web/rss.ftl");
    return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
}


public List<PostDetailVO> buildPosts(Pageable pageable){
    Assert.notNull(pageable, "Pageable must not be null");
    Page<Post> postPage = postService.pageBy(PostStatus.PUBLISHED, pageable);
    Page<PostDetailVO> posts = postService.convertToDetailVo(postPage);
    posts.getContent().forEach(postDetailVO -> {
        postDetailVO.setFormatContent(RegExUtils.replaceAll(postDetailVO.getFormatContent(), XML_INVALID_CHAR, ""));
        postDetailVO.setSummary(RegExUtils.replaceAll(postDetailVO.getSummary(), XML_INVALID_CHAR, ""));
    });
    return posts.getContent();
}


public List<PostDetailVO> buildCategoryPosts(Pageable pageable,CategoryDTO category){
    Assert.notNull(pageable, "Pageable must not be null");
    Assert.notNull(category, "Category slug must not be null");
    Page<Post> postPage = postCategoryService.pagePostBy(category.getId(), PostStatus.PUBLISHED, pageable);
    Page<PostDetailVO> posts = postService.convertToDetailVo(postPage);
    posts.getContent().forEach(postDetailVO -> {
        postDetailVO.setFormatContent(RegExUtils.replaceAll(postDetailVO.getFormatContent(), XML_INVALID_CHAR, ""));
        postDetailVO.setSummary(RegExUtils.replaceAll(postDetailVO.getSummary(), XML_INVALID_CHAR, ""));
    });
    return posts.getContent();
}


@GetMapping(value = { "sitemap", "sitemap.xml" }, produces = XML_MEDIA_TYPE)
@ResponseBody
public String sitemapXml(Model model,Pageable pageable){
    model.addAttribute("posts", buildPosts(pageable));
    Template template = freeMarker.getConfiguration().getTemplate("common/web/sitemap_xml.ftl");
    return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
}


public Timestamp getLastModifiedTime(List<PostDetailVO> posts){
    OptionalLong lastModifiedTimestamp = posts.stream().mapToLong(post -> post.getEditTime().getTime()).max();
    if (lastModifiedTimestamp.isEmpty()) {
        return new Timestamp(System.currentTimeMillis());
    }
    return new Timestamp(lastModifiedTimestamp.getAsLong());
}


public void lastModified2ResponseHeader(HttpServletResponse response,Timestamp time){
    SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.ENGLISH);
    response.setHeader(LAST_MODIFIED_HEADER, dateFormat.format(time));
}


@GetMapping(value = "robots.txt", produces = MediaType.TEXT_PLAIN_VALUE)
@ResponseBody
public String robots(Model model){
    Template template = freeMarker.getConfiguration().getTemplate("common/web/robots.ftl");
    return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
}


@GetMapping(value = { "atom/categories/{slug}", "atom/categories/{slug}.xml" }, produces = XML_MEDIA_TYPE)
@ResponseBody
public String atom(Model model,String slug,HttpServletResponse response){
    Category category = categoryService.getBySlugOfNonNull(slug);
    CategoryDTO categoryDTO = categoryService.convertTo(category);
    List<PostDetailVO> posts = buildCategoryPosts(buildPostPageable(optionService.getRssPageSize()), categoryDTO);
    model.addAttribute("category", categoryDTO);
    model.addAttribute("posts", posts);
    Timestamp lastModified = this.getLastModifiedTime(posts);
    this.lastModified2ResponseHeader(response, lastModified);
    model.addAttribute("lastModified", lastModified);
    Template template = freeMarker.getConfiguration().getTemplate("common/web/atom.ftl");
    return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
}


@GetMapping(value = "sitemap.html")
public String sitemapHtml(Model model,Pageable pageable){
    model.addAttribute("posts", buildPosts(pageable));
    return "common/web/sitemap_html";
}


}
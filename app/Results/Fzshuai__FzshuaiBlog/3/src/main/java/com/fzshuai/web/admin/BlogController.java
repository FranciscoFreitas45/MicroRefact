package com.fzshuai.web.admin;
 import com.fzshuai.po.Blog;
import com.fzshuai.po.Type;
import com.fzshuai.po.User;
import com.fzshuai.service.BlogService;
import com.fzshuai.service.TagService;
import com.fzshuai.service.TypeService;
import com.fzshuai.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpSession;
import com.fzshuai.Interface.BlogService;
import com.fzshuai.Interface.TagService;
@Controller
@RequestMapping("admin")
public class BlogController {

 private  String INPUT;

 private  String LIST;

 private  String REDIRECT_LIST;

@Autowired
 private  BlogService blogService;

@Autowired
 private  TypeService typeService;

@Autowired
 private  TagService tagService;


@GetMapping("/blogs/input")
public String input(Model model){
    setTypeAndTag(model);
    model.addAttribute("blog", new Blog());
    return INPUT;
}


@PostMapping("/blogs/search")
public String search(Pageable pageable,BlogQuery blog,Model model){
    model.addAttribute("page", blogService.listBlog(pageable, blog));
    return "admin/blogs :: blogList";
}


@PostMapping("/blogs")
public String post(Blog blog,RedirectAttributes attributes,HttpSession session){
    blog.setUser((User) session.getAttribute("user"));
    blog.setType(typeService.getType(blog.getType().getId()));
    blog.setTags(tagService.listTag(blog.getTagIds()));
    Blog b;
    if (blog.getId() == null) {
        b = blogService.saveBlog(blog);
    } else {
        b = blogService.updateBlog(blog.getId(), blog);
    }
    if (b == null) {
        attributes.addFlashAttribute("message", "发布失败！");
    } else {
        attributes.addFlashAttribute("message", "发布成功！");
    }
    return REDIRECT_LIST;
}


@GetMapping("/blogs")
public String blogs(Pageable pageable,BlogQuery blog,Model model){
    model.addAttribute("types", typeService.listType());
    model.addAttribute("page", blogService.listBlog(pageable, blog));
    return LIST;
}


public void setTypeAndTag(Model model){
    model.addAttribute("types", typeService.listType());
    model.addAttribute("tags", tagService.listTag());
}


@GetMapping("/blogs/{id}/delete")
public String delete(Long id,RedirectAttributes attributes){
    blogService.deleteBlog(id);
    attributes.addFlashAttribute("message", "删除成功");
    return REDIRECT_LIST;
}


@GetMapping("/blogs/{id}/input")
public String editInput(Long id,Model model){
    setTypeAndTag(model);
    Blog blog = blogService.getBlog(id);
    blog.init();
    model.addAttribute("blog", blog);
    return INPUT;
}


}
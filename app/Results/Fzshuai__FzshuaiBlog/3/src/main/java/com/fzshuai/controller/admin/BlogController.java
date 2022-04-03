package com.fzshuai.controller.admin;
 import com.fzshuai.po.Blog;
import com.fzshuai.po.User;
import com.fzshuai.service.BlogService;
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
import javax.lang.model.SourceVersion;
import javax.servlet.http.HttpSession;
import com.fzshuai.Interface.BlogService;
@Controller
@RequestMapping("/admin")
public class BlogController {

 private  String INPUT;

 private  String LIST;

 private  String REDIRECT_LIST;

@Autowired
 private  BlogService blogService;

@Autowired
 private  TypeService typeService;


@GetMapping("/blogs/input")
public String input(Model model){
    setType(model);
    model.addAttribute("blog", new Blog());
    return INPUT;
}


@PostMapping("/blogs/search")
public String search(Pageable pageable,BlogQuery blog,Model model){
    model.addAttribute("page", blogService.listBlog(pageable, blog));
    // 这里只刷新结果部分的segment，实现区域刷新
    // 前端使用 th:fragment="blogList"指定刷新的区域
    return "admin/blogs :: blogList";
}


@PostMapping("/blogs")
public String post(Blog blog,RedirectAttributes attributes,HttpSession session){
    blog.setUser((User) session.getAttribute("user"));
    // 获取创建好的博客指定的类别
    blog.setType(typeService.getType(blog.getType().getId()));
    // System.out.println(typeService.getType(blog.getType().getId()));
    Blog b;
    if (blog.getId() == null) {
        b = blogService.saveBlog(blog);
    } else {
        b = blogService.updateBlog(blog.getId(), blog);
    }
    if (b == null) {
        attributes.addFlashAttribute("message", "添加失败");
    } else {
        attributes.addFlashAttribute("message", "添加成功");
    }
    return REDIRECT_LIST;
}


@GetMapping("/blogs")
public String blogs(Pageable pageable,BlogQuery blog,Model model){
    // 获取所有已有的博客类别
    // types : 类别结果
    model.addAttribute("types", typeService.listType());
    // 分页查询
    // page : 分页查询结果
    model.addAttribute("page", blogService.listBlog(pageable, blog));
    return LIST;
}


public void setType(Model model){
    model.addAttribute("types", typeService.listType());
}


@GetMapping("/blogs/{id}/delete")
public String delete(Long id,RedirectAttributes attributes){
    blogService.deleteBlog(id);
    attributes.addFlashAttribute("message", "删除成功");
    return REDIRECT_LIST;
}


@GetMapping("/blogs/{id}/input")
public String editInput(Long id,Model model){
    setType(model);
    // 获取要编辑的博客
    Blog blog = blogService.getBlog(id);
    model.addAttribute("blog", blog);
    // 最后跳转到博客新增页进行编辑，然后再保存
    return INPUT;
}


}
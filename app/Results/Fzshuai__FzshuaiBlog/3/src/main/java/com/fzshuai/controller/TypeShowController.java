package com.fzshuai.controller;
 import com.fzshuai.po.Type;
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
import java.util.List;
import com.fzshuai.Interface.BlogService;
@Controller
public class TypeShowController {

@Autowired
 private  TypeService typeService;

@Autowired
 private  BlogService blogService;


@GetMapping("/types/{id}")
public String types(Pageable pageable,Long id,Model model){
    List<Type> types = typeService.listTypeTop(10000);
    if (id == -1) {
        id = types.get(0).getId();
    }
    BlogQuery blogQuery = new BlogQuery();
    blogQuery.setTypeId(id);
    model.addAttribute("types", types);
    model.addAttribute("page", blogService.listBlog(pageable, blogQuery));
    model.addAttribute("activeTypeId", id);
    return "types";
}


}
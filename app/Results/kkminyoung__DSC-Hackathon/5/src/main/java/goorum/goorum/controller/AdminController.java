package goorum.goorum.controller;
 import goorum.goorum.domain.Member;
import goorum.goorum.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import goorum.goorum.util.Constants;
@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {

@Autowired
 private  CategoryService categoryService;


@GetMapping
public ModelAndView showAdminPage(){
    return null;
}


@GetMapping("add-category")
public void addCategory(String category,HttpServletRequest request,HttpServletResponse response){
    JSONObject res = new JSONObject();
    Member member = (Member) request.getSession().getAttribute("loginMember");
    if (Objects.isNull(member) || member.getMemberId() != ADMIN_ID) {
        res.put(RESULT, INVALID_APPROACH);
    } else if (categoryService.addCategory(category)) {
        res.put(RESULT, SUCCESS);
    } else {
        res.put(RESULT, FAIL);
    }
    response.setContentType("application/json; charset=utf-8");
    response.getWriter().print(res);
}


}
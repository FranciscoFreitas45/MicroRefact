package com.fosun.fc.projects.creepers.web.controller;
 import java.util.Date;
import javax.servlet.ServletRequest;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.fosun.fc.projects.creepers.dto.UserDTO;
import com.fosun.fc.projects.creepers.service.IUserService;
public class UserController {

@SuppressWarnings("unused")
@Autowired
 private  IUserService userService;

 private  Logger log;


@RequestMapping(value = "insert", method = RequestMethod.GET)
public String init(Model model,RedirectAttributes redirectAttributes){
    log.debug("----mongo init page");
    model.addAttribute("action", "insert");
    model.addAttribute("longTime", new Date().getTime());
    return "mongo/mongoEdit";
}


@RequestMapping(value = "query", method = RequestMethod.GET)
public String initQuery(Model model,RedirectAttributes redirectAttributes){
    log.debug("----mongo list page");
    model.addAttribute("action", "list");
    return "mongo/mongoList";
}


@RequestMapping(value = "insert", method = RequestMethod.POST)
public String create(UserDTO userDTO,RedirectAttributes redirectAttributes){
    /*
         * User user = new User(); BeanMapper.copy(userDTO, user);
         * userService.saveUser(user);
         */
    redirectAttributes.addFlashAttribute("message", "mongo insert 測試成功");
    return "redirect:/mongo/list";
}


@RequestMapping(value = "update", method = RequestMethod.POST)
public String update(UserDTO userDTO,RedirectAttributes redirectAttributes){
    // User entity = BeanMapper.map(userDTO, User.class);
    // userService.updateUser(userDTO);
    redirectAttributes.addFlashAttribute("message", "客户信息更新成功");
    return "redirect:/mongo/mongoList";
}


@RequestMapping(value = "list", method = RequestMethod.GET)
public String list(int pageNumber,int pageSize,String sortType,Model model,ServletRequest request){
    @SuppressWarnings("unused")
    String name = request.getParameter("userName");
    // List<User> users = userService.findByName(name);
    // model.addAttribute("users", users);
    model.addAttribute("sortType", sortType);
    return "mongo/mongoList";
}


}
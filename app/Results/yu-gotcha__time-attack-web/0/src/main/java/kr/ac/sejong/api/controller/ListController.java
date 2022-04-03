package kr.ac.sejong.api.controller;
 import kr.ac.sejong.api.domain.User;
import kr.ac.sejong.api.service.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
@Controller
@RequestMapping(value = "")
public class ListController {

 private  FileService fileService;

public ListController(FileService fileService) {
    this.fileService = fileService;
}
@GetMapping(value = "list")
public String list(Model model,HttpSession session){
    User user = (User) session.getAttribute("userdata");
    List<Map<String, Object>> fileList = fileService.getFileListByUser(user);
    // System.out.println(fileList);
    model.addAttribute("file", fileList);
    System.out.println(model);
    return "list";
}


}
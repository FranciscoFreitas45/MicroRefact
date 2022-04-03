package kr.ac.sejong.api.controller;
 import kr.ac.sejong.api.domain.User;
import kr.ac.sejong.api.service.ResultService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
@Controller
@RequestMapping(value = "")
public class ResultController {

 private  ResultService resultService;

public ResultController(ResultService resultService) {
    this.resultService = resultService;
}
@GetMapping(value = "/result")
public String result(Model model,HttpSession session,String id,String no){
    User user = (User) session.getAttribute("userdata");
    List<Map<String, Object>> resultList = resultService.getResultByUploadId(id);
    Map<String, Object> file = resultService.getFileByUploadId(id);
    model.addAttribute("resultList", resultList);
    model.addAttribute("file", file);
    model.addAttribute("no", no);
    return "result";
}


}
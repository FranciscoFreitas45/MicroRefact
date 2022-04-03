package com.gs.controller;
 import com.gs.bean.ceshi;
import com.gs.common.bean.ControllerResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
@Controller
@RequestMapping("/summerNote")
public class SummerNoteController {


@ResponseBody
@RequestMapping(value = "query", method = RequestMethod.POST)
public List<ceshi> query(){
    return null;
}


@ResponseBody
@RequestMapping(value = "add/{str}", method = RequestMethod.POST)
public ControllerResult addFile(String str){
    System.out.print(str);
    return ControllerResult.getSuccessResult("上传成功");
}


@RequestMapping(value = "summerNote", method = RequestMethod.GET)
public ModelAndView tableIndex(){
    ModelAndView mav = new ModelAndView("summerNote/summerNote");
    return mav;
}


}
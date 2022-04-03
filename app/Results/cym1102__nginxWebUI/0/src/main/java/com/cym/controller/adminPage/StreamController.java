package com.cym.controller.adminPage;
 import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.cym.model.Stream;
import com.cym.service.StreamService;
import com.cym.utils.BaseController;
import com.cym.utils.JsonResult;
import com.cym.utils.SnowFlakeUtils;
import cn.craccd.sqlHelper.bean.Sort;
import cn.craccd.sqlHelper.bean.Sort.Direction;
import cn.hutool.core.util.StrUtil;
@Controller
@RequestMapping("/adminPage/stream")
public class StreamController extends BaseController{

@Autowired
 private StreamService streamService;


@RequestMapping("setOrder")
@ResponseBody
public JsonResult setOrder(String id,Integer count){
    streamService.setSeq(id, count);
    return renderSuccess();
}


@RequestMapping("addOver")
@ResponseBody
public JsonResult addOver(Stream stream){
    if (StrUtil.isEmpty(stream.getId())) {
        stream.setSeq(SnowFlakeUtils.getId());
    }
    sqlHelper.insertOrUpdate(stream);
    return renderSuccess();
}


@RequestMapping("addTemplate")
@ResponseBody
public JsonResult addTemplate(String templateId){
    streamService.addTemplate(templateId);
    return renderSuccess();
}


@RequestMapping("")
public ModelAndView index(HttpSession httpSession,ModelAndView modelAndView){
    List<Stream> streamList = streamService.findAll();
    modelAndView.addObject("streamList", streamList);
    modelAndView.setViewName("/adminPage/stream/index");
    return modelAndView;
}


@RequestMapping("del")
@ResponseBody
public JsonResult del(String id){
    sqlHelper.deleteById(id, Stream.class);
    return renderSuccess();
}


@RequestMapping("detail")
@ResponseBody
public JsonResult detail(String id){
    return renderSuccess(sqlHelper.findById(id, Stream.class));
}


}
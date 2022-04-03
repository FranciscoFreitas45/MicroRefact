package org.live.live.controller;
 import org.live.common.page.JqGridModel;
import org.live.common.page.PageUtils;
import org.live.common.response.ResponseModel;
import org.live.common.response.SimpleResponseModel;
import org.live.common.systemlog.SystemLog;
import org.live.live.service.AnchorService;
import org.live.live.vo.AnchorInfoVo;
import org.live.live.vo.AnchorVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
@Controller
@RequestMapping("/live")
public class AnchorController {

 private  Logger LOGGER;

@Resource
 private  AnchorService service;


@SystemLog(description = "进入主播管理界面")
@RequestMapping("/anchor/page")
public String toAnchorPage(){
    return "live/anchor";
}


@RequestMapping(value = "/anchor", method = RequestMethod.GET)
@ResponseBody
public JqGridModel<AnchorVo> findAnchors(HttpServletRequest request,String searchStr){
    try {
        PageRequest page = PageUtils.getPage4JqGrid(request);
        Page<AnchorVo> pageResult = service.findAnchors(page, searchStr);
        JqGridModel<AnchorVo> model = PageUtils.pageConvertJqGrid(pageResult);
        return model;
    } catch (Exception e) {
        LOGGER.error("查询主播信息异常", e);
    }
    return null;
}


@RequestMapping(value = "/anchor/{anchorId}", method = RequestMethod.GET)
@ResponseBody
public ResponseModel<Object> findAnchorInfo(String anchorId){
    ResponseModel<Object> model = new SimpleResponseModel<Object>();
    try {
        AnchorInfoVo info = service.findAnchorInfo(anchorId);
        model.setData(info);
        model.success();
    } catch (Exception e) {
        LOGGER.error("查询单条主播信息异常", e);
        model.error();
    }
    return model;
}


}
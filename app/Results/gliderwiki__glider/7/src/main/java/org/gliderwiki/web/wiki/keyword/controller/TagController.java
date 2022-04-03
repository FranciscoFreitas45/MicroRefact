package org.gliderwiki.web.wiki.keyword.controller;
 import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.gliderwiki.web.domain.WeSpace;
import org.gliderwiki.web.vo.WikiTagVo;
import org.gliderwiki.web.vo.WikiVo;
import org.gliderwiki.web.wiki.keyword.service.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class TagController {

 private Logger logger;

@Autowired
 private  TagService tagService;


@RequestMapping(value = "/space/tag/{tagName}", method = RequestMethod.GET)
public ModelAndView tagCloud(String tagName,HttpServletRequest request,HttpServletResponse response,ModelAndView modelAndView){
    // String queryTagName = new String( tagName.getBytes("ISO-8859-1"),"UTF-8");
    String queryTagName = tagName;
    logger.debug("#### queryTagName : " + queryTagName);
    List<WikiVo> wikiList = null;
    List<WeSpace> weSpaceList = null;
    // 최근 한달간의 Tag Cloud 목록을 조회한다.
    List<WikiTagVo> tagVoList = tagService.getTagList();
    wikiList = tagService.createWikiListWithTag(queryTagName);
    weSpaceList = tagService.createSpaceListWithTag(wikiList);
    modelAndView.addObject("tagVoList", tagVoList);
    modelAndView.addObject("wikiList", wikiList);
    modelAndView.addObject("weSpaceList", weSpaceList);
    modelAndView.setViewName("tag/Tag");
    return modelAndView;
}


}
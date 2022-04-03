package org.gliderwiki.web.wiki.parser.controller;
 import java.util.HashMap;
import java.util.Map;
import org.gliderwiki.util.GliderTagParser;
import org.gliderwiki.web.common.service.EntityService;
import org.gliderwiki.web.system.SystemConst;
import org.gliderwiki.web.wiki.common.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.gliderwiki.Interface.EntityService;
import org.gliderwiki.Interface.CommonService;
@Controller
public class WikiTagController {

 private Logger logger;

@SuppressWarnings("rawtypes")
@Autowired
 private  EntityService entityService;

@Autowired
 private  CommonService commonService;


@RequestMapping(value = "/wikiPreviewTag", method = RequestMethod.POST)
public ModelAndView wikiPreviewTag(String tagHtml){
    Map<String, Object> param = new HashMap<String, Object>();
    GliderTagParser gtp = new GliderTagParser();
    logger.debug("####### getWe_wiki_text() : {}", tagHtml);
    Map<String, Object> resultMap = null;
    try {
        resultMap = gtp.getHtml(tagHtml);
    } catch (Exception e) {
        e.printStackTrace();
    }
    // List<Object> linkList = (List<Object>) resultMap.get("linkTagList");
    // List<Object> headList = (List<Object>) resultMap.get("h1TagList");
    // List<Object> noteList = (List<Object>) resultMap.get("noteTagList");
    // Integer graphCnt = (Integer) resultMap.get("graphCnt");
    logger.debug("#####################################");
    logger.debug("htmltag : {}", resultMap.get("htmltag").toString());
    logger.debug("#####################################");
    try {
        param.put("result", "SUCCESS");
        param.put("status", SystemConst.CALL_SUCCESS);
        // param.put("wikiMarkup", resultMap.get("htmltag").toString().replaceAll("\r\n", "\r\n<br class=\"br\"/>\r\n"));
        param.put("wikiMarkup", resultMap.get("htmltag").toString());
        param.put("wikiTitle", "미리보기");
        param.put("wikiRevision", "0");
        param.put("weWikiIdx", "0");
    } catch (Exception e) {
        param.put("result", "SUCCESS");
        param.put("status", SystemConst.CALL_SUCCESS);
        param.put("wikiMarkup", "");
        param.put("wikiTitle", "");
        param.put("wikiRevision", "");
        param.put("weWikiIdx", "");
    }
    return new ModelAndView("json_").addObject("param", param);
}


}
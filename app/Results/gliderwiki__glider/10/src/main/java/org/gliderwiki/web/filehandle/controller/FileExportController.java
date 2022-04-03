package org.gliderwiki.web.filehandle.controller;
 import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.gliderwiki.framework.util.GliderFileUtils;
import org.gliderwiki.web.domain.AttachmentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
@RequestMapping("/exp")
public class FileExportController {

 private Logger logger;


@RequestMapping(value = "/pdf/{wikiIdx}", method = RequestMethod.GET)
public void exportToPdf(HttpServletRequest request,HttpServletResponse response,String wikiIdx){
    GliderFileUtils fileUtil = new GliderFileUtils();
    fileUtil.htmlToPdfExport(request, response, wikiIdx);
}


}
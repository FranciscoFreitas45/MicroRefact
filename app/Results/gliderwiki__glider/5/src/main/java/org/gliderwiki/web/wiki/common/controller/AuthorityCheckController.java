package org.gliderwiki.web.wiki.common.controller;
 import javax.annotation.Resource;
import org.gliderwiki.web.domain.WebConstant;
import org.gliderwiki.web.system.argumentresolver.LoginUser;
import org.gliderwiki.web.vo.MemberSessionVo;
import org.gliderwiki.web.wiki.common.service.AuthorityCheckService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class AuthorityCheckController {

@Resource(name = "authorityCheckService")
 private  AuthorityCheckService authorityCheckService;


@RequestMapping(value = "/api/authority/wiki", method = RequestMethod.GET)
@ResponseBody
public WebConstant accessCheckAuthorityWikiPage(MemberSessionVo loginUser,int spaceIdx){
    return authorityCheckService.accessCheckAuthorityWikiPage(loginUser.getWeUserIdx(), spaceIdx);
}


}
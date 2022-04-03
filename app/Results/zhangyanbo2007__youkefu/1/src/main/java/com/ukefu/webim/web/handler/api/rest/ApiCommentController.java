package com.ukefu.webim.web.handler.api.rest;
 import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ukefu.util.Menu;
import com.ukefu.webim.service.acd.ServiceQuene;
import com.ukefu.webim.service.repository.ChatMessageRepository;
import com.ukefu.webim.util.CallCenterUtils;
import com.ukefu.webim.util.RestResult;
import com.ukefu.webim.util.RestResultType;
import com.ukefu.webim.util.server.message.ChatMessage;
import com.ukefu.webim.web.handler.Handler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import Interface.ChatMessageRepository;
import DTO.ChatMessage;
@RestController
@RequestMapping("/rest/webim")
@Api(value = "在线客服", description = "在线客服接口功能")
public class ApiCommentController extends Handler{

@Autowired
 private  ChatMessageRepository chatMessageRes;

@Value("${web.upload-path}")
 private  String path;


@RequestMapping(value = "/agent")
@Menu(type = "apps", subtype = "webim", access = true)
@ApiOperation("获取在线客服会话历史消息")
public ResponseEntity<RestResult> agent(HttpServletRequest request,String orgi){
    return new ResponseEntity<>(new RestResult(RestResultType.OK, ServiceQuene.getAgentReport(orgi)), HttpStatus.OK);
}


@RequestMapping(value = "/host")
@Menu(type = "apps", subtype = "webim", access = true)
@ApiOperation("拦截请求")
public ResponseEntity<String> host(HttpServletRequest request,String ip,String domain,String called,String caller){
    return new ResponseEntity<>(String.valueOf(CallCenterUtils.callCheck(ip, domain, CallCenterUtils.pbxhost(domain), caller, called)), HttpStatus.OK);
}


@RequestMapping(value = "/message/useful")
@Menu(type = "apps", subtype = "webim", access = true)
@ApiOperation("获取满意度调查")
public ResponseEntity<RestResult> useful(HttpServletRequest request,String orgi,String id){
    if (!StringUtils.isBlank(id)) {
        ChatMessage chatMessage = chatMessageRes.findById(id);
        chatMessage.setUseful(true);
        chatMessage.setUsetime(new Date());
        chatMessageRes.save(chatMessage);
    }
    return new ResponseEntity<>(new RestResult(RestResultType.OK), HttpStatus.OK);
}


@RequestMapping(value = "/message/unuseful")
@Menu(type = "apps", subtype = "webim", access = true)
@ApiOperation("获取满意度调查")
public ResponseEntity<RestResult> unuseful(HttpServletRequest request,String orgi,String id){
    if (!StringUtils.isBlank(id)) {
        ChatMessage chatMessage = chatMessageRes.findById(id);
        if (chatMessage != null) {
            chatMessage.setUseful(false);
            chatMessage.setUsetime(new Date());
            chatMessageRes.save(chatMessage);
        }
    }
    return new ResponseEntity<>(new RestResult(RestResultType.OK), HttpStatus.OK);
}


}
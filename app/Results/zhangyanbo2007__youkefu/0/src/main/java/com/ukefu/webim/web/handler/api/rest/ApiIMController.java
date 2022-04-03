package com.ukefu.webim.web.handler.api.rest;
 import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.ukefu.core.UKDataContext;
import com.ukefu.util.Menu;
import com.ukefu.util.UKTools;
import com.ukefu.util.extra.DataExchangeInterface;
import com.ukefu.webim.service.acd.ServiceQuene;
import com.ukefu.webim.service.repository.AgentServiceSatisRepository;
import com.ukefu.webim.service.repository.ChatMessageRepository;
import com.ukefu.webim.service.repository.ConsultInviteRepository;
import com.ukefu.webim.service.repository.LeaveMsgRepository;
import com.ukefu.webim.util.MessageUtils;
import com.ukefu.webim.util.OnlineUserUtils;
import com.ukefu.webim.util.RestResult;
import com.ukefu.webim.util.RestResultType;
import com.ukefu.webim.util.server.message.ChatMessage;
import com.ukefu.webim.web.handler.Handler;
import com.ukefu.webim.web.model.AgentServiceSatis;
import com.ukefu.webim.web.model.AiConfig;
import com.ukefu.webim.web.model.LeaveMsg;
import com.ukefu.webim.web.model.SystemConfig;
import com.ukefu.webim.web.model.UKeFuDic;
import com.ukefu.webim.web.model.UploadStatus;
import freemarker.template.TemplateException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import DTO.DataExchangeInterface;
@RestController
@RequestMapping("/api/webim")
@Api(value = "在线客服", description = "在线客服接口功能")
public class ApiIMController extends Handler{

@Autowired
 private  ConsultInviteRepository consultInviteRepository;

@Autowired
 private  ChatMessageRepository chatMessageRes;

@Autowired
 private  AgentServiceSatisRepository agentServiceSatisRes;

@Autowired
 private  LeaveMsgRepository leaveMsgRes;

@Value("${web.upload-path}")
 private  String path;


@RequestMapping(value = "/agent")
@Menu(type = "apps", subtype = "webim", access = true)
@ApiOperation("获取在线客服会话历史消息")
public ResponseEntity<RestResult> agent(HttpServletRequest request,String orgi){
    return new ResponseEntity<>(new RestResult(RestResultType.OK, ServiceQuene.getAgentReport(orgi)), HttpStatus.OK);
}


@RequestMapping(value = "/leavemsg/save", method = RequestMethod.POST)
@Menu(type = "apps", subtype = "webim", access = true)
@ApiOperation("保存留言功能")
public ResponseEntity<RestResult> leavemsg(HttpServletRequest request,String orgi,LeaveMsg msg){
    if (msg != null && !StringUtils.isBlank(msg.getUserid())) {
        leaveMsgRes.save(msg);
    }
    return new ResponseEntity<>(new RestResult(RestResultType.OK), HttpStatus.OK);
}


@RequestMapping(value = "/suggest/mobile/{appid}")
@Menu(type = "im", subtype = "index", access = true)
public ResponseEntity<RestResult> mobilesuggest(ModelMap map,HttpServletRequest request,HttpServletResponse response,String appid,String q,String traceid,String aiid,String p,String exchange,String title,String url,String skill,String id,String userid,String agent,String name,String email,String phone,String ai,String orgi,String product,String description,String imgurl,String pid,String purl){
    return new ResponseEntity<>(new RestResult(RestResultType.OK, !StringUtils.isBlank(appid) && !StringUtils.isBlank(q) ? OnlineUserUtils.suggest(q, orgi, userid, OnlineUserUtils.cousult(appid, null, consultInviteRepository), aiid, skill) : null), HttpStatus.OK);
}


@RequestMapping("/image/upload")
@Menu(type = "im", subtype = "image", access = true)
public ModelAndView upload(ModelMap map,HttpServletRequest request,MultipartFile imgFile,String channel,String userid,String username,String appid,String orgi,String paste){
    ModelAndView view = request(super.createRequestPageTempletResponse("/apps/im/upload"));
    UploadStatus upload = null;
    String fileName = null;
    if (imgFile != null && imgFile.getOriginalFilename().lastIndexOf(".") > 0 && !StringUtils.isBlank(userid)) {
        File uploadDir = new File(path, "upload");
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        String fileid = UKTools.md5(imgFile.getBytes());
        /**
         * 上传图片最大10M
         */
        if (imgFile.getContentType() != null && imgFile.getContentType().indexOf("image") >= 0 && imgFile.getSize() < 1024 * 1024 * 10) {
            fileName = "upload/" + fileid + "_original";
            File imageFile = new File(path, fileName);
            FileCopyUtils.copy(imgFile.getBytes(), imageFile);
            String thumbnailsFileName = "upload/" + fileid;
            UKTools.processImage(new File(path, thumbnailsFileName), imageFile);
            upload = new UploadStatus("0", "/res/image.html?id=" + thumbnailsFileName);
            String image = "/res/image.html?id=" + thumbnailsFileName;
            if (request.getServerPort() == 80) {
                image = "/res/image.html?id=" + thumbnailsFileName;
            } else {
                image = "/res/image.html?id=" + thumbnailsFileName;
            }
            if (paste == null) {
                if (!StringUtils.isBlank(channel)) {
                    MessageUtils.uploadImage(image, fileid, (int) imgFile.getSize(), imgFile.getName(), channel, userid, username, appid, orgi);
                } else {
                    MessageUtils.uploadImage(image, fileid, (int) imgFile.getSize(), imgFile.getName(), userid);
                }
            }
        } else {
            upload = new UploadStatus("请选择图片文件");
        }
    } else {
        upload = new UploadStatus("请选择图片文件");
    }
    map.addAttribute("upload", upload);
    return view;
}


@RequestMapping(value = "/ai")
@Menu(type = "apps", subtype = "webim", access = true)
@ApiOperation("获取在线客服会话ID")
public ResponseEntity<RestResult> session(HttpServletRequest request,String aiid,String orgi){
    AiConfig aiConfig = null;
    if (UKDataContext.model.get("xiaoe") != null && !StringUtils.isBlank(aiid)) {
        // 启用 AI ， 并且 AI优先 接待
        DataExchangeInterface dataInterface = (DataExchangeInterface) UKDataContext.getContext().getBean("aiconfig");
        aiConfig = (AiConfig) dataInterface.getDataByIdAndOrgi(aiid, orgi);
    }
    return new ResponseEntity<>(new RestResult(RestResultType.OK, aiConfig), HttpStatus.OK);
}


@RequestMapping(value = "/query")
@Menu(type = "apps", subtype = "webim", access = true)
@ApiOperation("获取推荐知识")
public ResponseEntity<RestResult> query(HttpServletRequest request,String q,String appid){
    return new ResponseEntity<>(new RestResult(RestResultType.OK, OnlineUserUtils.search(q, super.getOrgi(request), super.getUser(request), appid)), HttpStatus.OK);
}


@RequestMapping(value = "/chat/his")
@Menu(type = "apps", subtype = "webim", access = true)
@ApiOperation("获取在线客服会话历史消息")
public ResponseEntity<RestResult> history(HttpServletRequest request,String userid,String orgi){
    return new ResponseEntity<>(new RestResult(RestResultType.OK, chatMessageRes.findByUsessionAndOrgi(userid, orgi, new PageRequest(super.getP(request), super.getPs(request), Direction.DESC, "updatetime"))), HttpStatus.OK);
}


@RequestMapping(value = "/{id}")
@Menu(type = "apps", subtype = "webim", access = true)
@ApiOperation("获取在线客服")
public ResponseEntity<RestResult> list(HttpServletRequest request,String id){
    Map<String, Object> configMap = new HashMap<String, Object>();
    configMap.put("invite", OnlineUserUtils.cousult(id, null, consultInviteRepository));
    configMap.put("commentList", UKeFuDic.getInstance().getDic(UKDataContext.UKEFU_SYSTEM_COMMENT_DIC));
    configMap.put("commentItemList", UKeFuDic.getInstance().getDic(UKDataContext.UKEFU_SYSTEM_COMMENT_ITEM_DIC));
    return new ResponseEntity<>(new RestResult(RestResultType.OK, configMap), HttpStatus.OK);
}


@RequestMapping(value = "/hot")
@Menu(type = "apps", subtype = "webim", access = true)
@ApiOperation("获取推荐知识")
public ResponseEntity<RestResult> hot(HttpServletRequest request,String q,String aiid){
    return new ResponseEntity<>(new RestResult(RestResultType.OK, OnlineUserUtils.suggest(q, super.getOrgi(request), super.getUser(request).getId(), null, aiid, null)), HttpStatus.OK);
}


@RequestMapping(value = "/url")
@Menu(type = "apps", subtype = "webim", access = true)
@ApiOperation("获取图片和语音信息资源的访问URL地址信息")
public ResponseEntity<RestResult> url(HttpServletRequest request,String orgi){
    SystemConfig systemConfig = UKTools.getSystemConfig();
    return new ResponseEntity<>(new RestResult(RestResultType.OK, systemConfig != null ? systemConfig.getIconstr() : ""), HttpStatus.OK);
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


@RequestMapping(value = "/satis")
@Menu(type = "apps", subtype = "webim", access = true)
@ApiOperation("获取满意度调查")
public ResponseEntity<RestResult> satis(HttpServletRequest request,String orgi){
    Map<String, Object> statfMap = new HashMap<String, Object>();
    statfMap.put("commentList", UKeFuDic.getInstance().getDic(UKDataContext.UKEFU_SYSTEM_COMMENT_DIC));
    statfMap.put("commentItemList", UKeFuDic.getInstance().getDic(UKDataContext.UKEFU_SYSTEM_COMMENT_ITEM_DIC));
    return new ResponseEntity<>(new RestResult(RestResultType.OK, statfMap), HttpStatus.OK);
}


@RequestMapping(value = "/satis/save", method = RequestMethod.POST)
@Menu(type = "apps", subtype = "webim", access = true)
@ApiOperation("获取满意度调查")
public ResponseEntity<RestResult> satissave(HttpServletRequest request,String orgi,AgentServiceSatis satis){
    if (satis != null && !StringUtils.isBlank(satis.getId())) {
        int count = agentServiceSatisRes.countById(satis.getId());
        if (count == 1) {
            if (!StringUtils.isBlank(satis.getSatiscomment()) && satis.getSatiscomment().length() > 255) {
                satis.setSatiscomment(satis.getSatiscomment().substring(0, 255));
            }
            satis.setSatisfaction(true);
            satis.setSatistime(new Date());
            agentServiceSatisRes.save(satis);
        }
    }
    return new ResponseEntity<>(new RestResult(RestResultType.OK), HttpStatus.OK);
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


}
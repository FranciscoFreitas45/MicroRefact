package com.cym.controller.adminPage;
 import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.cym.ext.ServerExt;
import com.cym.model.Cert;
import com.cym.model.Http;
import com.cym.model.Location;
import com.cym.model.Password;
import com.cym.model.Server;
import com.cym.model.Stream;
import com.cym.model.Upstream;
import com.cym.model.Www;
import com.cym.service.ConfService;
import com.cym.service.ParamService;
import com.cym.service.ServerService;
import com.cym.service.SettingService;
import com.cym.service.UpstreamService;
import com.cym.utils.BaseController;
import com.cym.utils.JsonResult;
import com.cym.utils.SnowFlakeUtils;
import com.cym.utils.TelnetUtils;
import com.cym.utils.ToolUtils;
import com.github.odiszapc.nginxparser.NgxBlock;
import com.github.odiszapc.nginxparser.NgxConfig;
import com.github.odiszapc.nginxparser.NgxDumper;
import com.github.odiszapc.nginxparser.NgxParam;
import cn.craccd.sqlHelper.bean.Page;
import cn.craccd.sqlHelper.bean.Sort;
import cn.craccd.sqlHelper.bean.Sort.Direction;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.cym.Interface.UpstreamService;
import com.cym.Interface.ParamService;
import com.cym.Interface.SettingService;
import com.cym.DTO.Stream;
import com.cym.DTO.Http;
@Controller
@RequestMapping("/adminPage/server")
public class ServerController extends BaseController{

@Autowired
 private ServerService serverService;

@Autowired
 private UpstreamService upstreamService;

@Autowired
 private ParamService paramService;

@Autowired
 private SettingService settingService;

@Autowired
 private ConfService confService;


@RequestMapping("testPort")
@ResponseBody
public JsonResult testPort(){
    List<Server> servers = sqlHelper.findAll(Server.class);
    List<String> ips = new ArrayList<>();
    for (Server server : servers) {
        String ip = "";
        String port = "";
        if (server.getListen().contains(":")) {
            ip = server.getListen().split(":")[0];
            port = server.getListen().split(":")[1];
        } else {
            ip = "127.0.0.1";
            port = server.getListen();
        }
        if (TelnetUtils.isRunning(ip, Integer.parseInt(port)) && !ips.contains(server.getListen())) {
            ips.add(server.getListen());
        }
    }
    if (ips.size() == 0) {
        return renderSuccess();
    } else {
        return renderError(m.get("serverStr.portUserdList") + ": " + StrUtil.join(" , ", ips));
    }
}


@RequestMapping("preview")
@ResponseBody
public JsonResult preview(String id,String type){
    NgxBlock ngxBlock = null;
    if (type.equals("server")) {
        Server server = sqlHelper.findById(id, Server.class);
        ngxBlock = confService.bulidBlockServer(server);
    } else if (type.equals("upstream")) {
        Upstream upstream = sqlHelper.findById(id, Upstream.class);
        ngxBlock = confService.buildBlockUpstream(upstream);
    } else if (type.equals("http")) {
        List<Http> httpList = sqlHelper.findAll(new Sort("seq", Direction.ASC), Http.class);
        ngxBlock = new NgxBlock();
        ngxBlock.addValue("http");
        for (Http http : httpList) {
            NgxParam ngxParam = new NgxParam();
            ngxParam.addValue(http.getName().trim() + " " + http.getValue().trim());
            ngxBlock.addEntry(ngxParam);
        }
    } else if (type.equals("stream")) {
        List<Stream> streamList = sqlHelper.findAll(new Sort("seq", Direction.ASC), Stream.class);
        ngxBlock = new NgxBlock();
        ngxBlock.addValue("stream");
        for (Stream stream : streamList) {
            NgxParam ngxParam = new NgxParam();
            ngxParam.addValue(stream.getName() + " " + stream.getValue());
            ngxBlock.addEntry(ngxParam);
        }
    }
    NgxConfig ngxConfig = new NgxConfig();
    ngxConfig.addEntry(ngxBlock);
    String conf = ToolUtils.handleConf(new NgxDumper(ngxConfig).dump());
    return renderSuccess(conf);
}


@RequestMapping("setOrder")
@ResponseBody
public JsonResult setOrder(String id,Integer count){
    serverService.setSeq(id, count);
    return renderSuccess();
}


@RequestMapping("addOver")
@ResponseBody
public JsonResult addOver(String serverJson,String serverParamJson,String locationJson){
    Server server = JSONUtil.toBean(serverJson, Server.class);
    List<Location> locations = JSONUtil.toList(JSONUtil.parseArray(locationJson), Location.class);
    if (StrUtil.isEmpty(server.getId())) {
        server.setSeq(SnowFlakeUtils.getId());
    }
    if (server.getProxyType() == 0) {
        try {
            serverService.addOver(server, serverParamJson, locations);
        } catch (Exception e) {
            return renderError(e.getMessage());
        }
    } else {
        serverService.addOverTcp(server, serverParamJson);
    }
    return renderSuccess();
}


@RequestMapping("importServer")
@ResponseBody
public JsonResult importServer(String nginxPath){
    if (StrUtil.isEmpty(nginxPath) || !FileUtil.exist(nginxPath)) {
        return renderError(m.get("serverStr.fileNotExist"));
    }
    try {
        serverService.importServer(nginxPath);
        return renderSuccess(m.get("serverStr.importSuccess"));
    } catch (Exception e) {
        e.printStackTrace();
        return renderError(m.get("serverStr.importFail"));
    }
}


@RequestMapping("setEnable")
@ResponseBody
public JsonResult setEnable(Server server){
    sqlHelper.updateById(server);
    return renderSuccess();
}


@RequestMapping("editDescr")
@ResponseBody
public JsonResult editDescr(String id,String descr){
    Server server = new Server();
    server.setId(id);
    server.setDescr(descr);
    sqlHelper.updateById(server);
    return renderSuccess();
}


@RequestMapping("getDescr")
@ResponseBody
public JsonResult getDescr(String id){
    Server server = sqlHelper.findById(id, Server.class);
    return renderSuccess(server.getDescr());
}


public String buildLocationStr(String id){
    List<String> str = new ArrayList<String>();
    List<Location> locations = serverService.getLocationByServerId(id);
    for (Location location : locations) {
        if (location.getType() == 0) {
            str.add("<span class='path'>" + location.getPath() + "</span><br><span class='value'>" + location.getValue() + "</span>");
        } else if (location.getType() == 1) {
            str.add("<span class='path'>" + location.getPath() + "</span><br><span class='value'>" + location.getRootPath() + "</span>");
        } else if (location.getType() == 2) {
            Upstream upstream = sqlHelper.findById(location.getUpstreamId(), Upstream.class);
            if (upstream != null) {
                str.add("<span class='path'>" + location.getPath() + "</span><br><span class='value'>http://" + upstream.getName() + (location.getUpstreamPath() != null ? location.getUpstreamPath() : "") + "</span>");
            }
        } else if (location.getType() == 3) {
            str.add("<span class='path'>" + location.getPath() + "</span>");
        }
    }
    return StrUtil.join("<br>", str);
}


@RequestMapping("")
public ModelAndView index(HttpSession httpSession,ModelAndView modelAndView,Page page,String keywords){
    page = serverService.search(page, keywords);
    List<ServerExt> exts = new ArrayList<ServerExt>();
    for (Server server : (List<Server>) page.getRecords()) {
        ServerExt serverExt = new ServerExt();
        if (server.getEnable() == null) {
            server.setEnable(false);
        }
        // 描述回车转<br>
        if (StrUtil.isNotEmpty(server.getDescr())) {
            server.setDescr(server.getDescr().replace("\n", "<br>"));
        }
        serverExt.setServer(server);
        if (server.getProxyType() == 0) {
            serverExt.setLocationStr(buildLocationStr(server.getId()));
        } else {
            Upstream upstream = sqlHelper.findById(server.getProxyUpstreamId(), Upstream.class);
            serverExt.setLocationStr(m.get("serverStr.server") + ": " + (upstream != null ? upstream.getName() : ""));
        }
        exts.add(serverExt);
    }
    page.setRecords(exts);
    modelAndView.addObject("page", page);
    List<Upstream> upstreamList = upstreamService.getListByProxyType(0);
    modelAndView.addObject("upstreamList", upstreamList);
    modelAndView.addObject("upstreamSize", upstreamList.size());
    List<Upstream> upstreamTcpList = upstreamService.getListByProxyType(1);
    modelAndView.addObject("upstreamTcpList", upstreamTcpList);
    modelAndView.addObject("upstreamTcpSize", upstreamTcpList.size());
    modelAndView.addObject("certList", sqlHelper.findAll(Cert.class));
    modelAndView.addObject("wwwList", sqlHelper.findAll(Www.class));
    modelAndView.addObject("passwordList", sqlHelper.findAll(Password.class));
    modelAndView.addObject("keywords", keywords);
    modelAndView.setViewName("/adminPage/server/index");
    return modelAndView;
}


@RequestMapping("del")
@ResponseBody
public JsonResult del(String id){
    serverService.deleteById(id);
    return renderSuccess();
}


@RequestMapping("detail")
@ResponseBody
public JsonResult detail(String id){
    Server server = sqlHelper.findById(id, Server.class);
    ServerExt serverExt = new ServerExt();
    serverExt.setServer(server);
    List<Location> list = serverService.getLocationByServerId(id);
    for (Location location : list) {
        String json = paramService.getJsonByTypeId(location.getId(), "location");
        location.setLocationParamJson(json != null ? json : null);
    }
    serverExt.setLocationList(list);
    String json = paramService.getJsonByTypeId(server.getId(), "server");
    serverExt.setParamJson(json != null ? json : null);
    return renderSuccess(serverExt);
}


}
package com.cym.controller.api;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.cym.model.Location;
import com.cym.model.Server;
import com.cym.service.ParamService;
import com.cym.service.ServerService;
import com.cym.utils.BaseController;
import com.cym.utils.JsonResult;
import com.cym.utils.SnowFlakeUtils;
import cn.craccd.sqlHelper.bean.Page;
import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.cym.Interface.ParamService;
@Api(tags = "反向代理(server)接口")
@RestController
@RequestMapping("/api/server")
public class ServerApiController extends BaseController{

@Autowired
 private ServerService serverService;

@Autowired
 private ParamService paramService;


@ApiOperation("添加或编辑location")
@PostMapping("insertOrUpdateLocation")
public JsonResult<?> insertOrUpdateLocation(Location location){
    if (StrUtil.isEmpty(location.getServerId())) {
        return renderError("serverId" + m.get("apiStr.notFill"));
    }
    if (StrUtil.isEmpty(location.getPath())) {
        return renderError("path" + m.get("apiStr.notFill"));
    }
    sqlHelper.insertOrUpdate(location);
    return renderSuccess(location);
}


@ApiOperation("删除location")
@PostMapping("deleteLocation")
public JsonResult<?> deleteLocation(String id){
    sqlHelper.deleteById(id, Location.class);
    return renderSuccess();
}


@SuppressWarnings("unchecked")
@ApiOperation("获取server分页列表")
@PostMapping("getPage")
public JsonResult<Page<Server>> getPage(Integer current,Integer limit,String keywords){
    Page page = new Page();
    page.setCurr(current);
    page.setLimit(limit);
    page = serverService.search(page, keywords);
    return renderSuccess(page);
}


@ApiOperation("根据serverId获取location列表")
@PostMapping("getLocationByServerId")
public JsonResult<List<Location>> getLocationByServerId(String serverId){
    List<Location> locationList = serverService.getLocationByServerId(serverId);
    for (Location location : locationList) {
        String json = paramService.getJsonByTypeId(location.getId(), "location");
        location.setLocationParamJson(json != null ? json : null);
    }
    return renderSuccess(locationList);
}


@ApiOperation("删除server")
@PostMapping("delete")
public JsonResult<?> delete(String id){
    serverService.deleteById(id);
    return renderSuccess();
}


@ApiOperation("添加或编辑server")
@PostMapping("insertOrUpdate")
public JsonResult<?> insertOrUpdate(Server server){
    if (StrUtil.isEmpty(server.getListen())) {
        return renderError("listen" + m.get("apiStr.notFill"));
    }
    if (StrUtil.isEmpty(server.getId())) {
        server.setSeq(SnowFlakeUtils.getId());
    }
    sqlHelper.insertOrUpdate(server);
    return renderSuccess(server);
}


}
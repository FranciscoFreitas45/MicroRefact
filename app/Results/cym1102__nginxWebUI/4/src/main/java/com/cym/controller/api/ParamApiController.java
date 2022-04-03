package com.cym.controller.api;
 import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cym.model.Param;
import com.cym.service.ParamService;
import com.cym.utils.BaseController;
import com.cym.utils.JsonResult;
import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
@Api(tags = "额外参数接口")
@RestController
@RequestMapping("/api/param")
public class ParamApiController extends BaseController{

@Autowired
 private ParamService paramService;


@ApiOperation("根据项目获取参数列表")
@PostMapping("getList")
public JsonResult<List<Param>> getList(String serverId,String locationId,String upstreamId){
    if (StrUtil.isEmpty(serverId) && StrUtil.isEmpty(locationId) && StrUtil.isEmpty(upstreamId)) {
        return renderError(m.get("apiStr.paramError"));
    }
    List<Param> list = paramService.getList(serverId, locationId, upstreamId);
    return renderSuccess(list);
}


@ApiOperation("删除")
@PostMapping("del")
public JsonResult<?> del(String id){
    sqlHelper.deleteById(id, Param.class);
    return renderSuccess();
}


@ApiOperation("添加或编辑参数")
@PostMapping("insertOrUpdate")
public JsonResult<?> insertOrUpdate(Param param){
    Integer count = 0;
    if (StrUtil.isNotEmpty(param.getLocationId())) {
        count++;
    }
    if (StrUtil.isNotEmpty(param.getServerId())) {
        count++;
    }
    if (StrUtil.isNotEmpty(param.getUpstreamId())) {
        count++;
    }
    if (count != 1) {
        return renderError(m.get("apiStr.paramError"));
    }
    sqlHelper.insertOrUpdate(param);
    return renderSuccess(param);
}


}
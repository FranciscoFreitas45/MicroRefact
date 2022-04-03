package com.ukefu.webim.web.handler.api.rest;
 import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ukefu.util.Menu;
import com.ukefu.webim.service.repository.OrganRepository;
import com.ukefu.webim.util.RestResult;
import com.ukefu.webim.util.RestResultType;
import com.ukefu.webim.web.handler.Handler;
import com.ukefu.webim.web.model.Organ;
import Interface.OrganRepository;
@RestController
@RequestMapping("/api/organ")
@Api(value = "机构服务", description = "组织机构/部门/技能组功能")
public class ApiOrganController extends Handler{

@Autowired
 private  OrganRepository organRepository;


@RequestMapping(method = RequestMethod.GET)
@Menu(type = "apps", subtype = "organ", access = true)
@ApiOperation("返回所有部门")
public ResponseEntity<RestResult> list(HttpServletRequest request){
    return new ResponseEntity<>(new RestResult(RestResultType.OK, organRepository.findByOrgi(super.getOrgi(request))), HttpStatus.OK);
}


@RequestMapping(method = RequestMethod.DELETE)
@Menu(type = "apps", subtype = "user", access = true)
@ApiOperation("删除机构，只提供 按照用户ID删除")
public ResponseEntity<RestResult> delete(HttpServletRequest request,String id){
    RestResult result = new RestResult(RestResultType.OK);
    Organ organ = null;
    if (!StringUtils.isBlank(id)) {
        organ = organRepository.findByIdAndOrgi(id, super.getOrgi(request));
        if (organ != null) {
            // 系统管理员， 不允许 使用 接口删除
            organRepository.delete(organ);
        } else {
            result.setStatus(RestResultType.ORGAN_DELETE);
        }
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
}


@RequestMapping(method = RequestMethod.PUT)
@Menu(type = "apps", subtype = "organ", access = true)
@ApiOperation("新增或修改部门")
public ResponseEntity<RestResult> put(HttpServletRequest request,Organ organ){
    if (organ != null && !StringUtils.isBlank(organ.getName())) {
        organRepository.save(organ);
    }
    return new ResponseEntity<>(new RestResult(RestResultType.OK), HttpStatus.OK);
}


}
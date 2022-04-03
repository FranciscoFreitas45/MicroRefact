package com.ukefu.webim.web.handler.api.rest;
 import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Date;
import java.util.List;
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
import com.ukefu.webim.service.es.QuickReplyRepository;
import com.ukefu.webim.service.repository.QuickTypeRepository;
import com.ukefu.webim.util.RestResult;
import com.ukefu.webim.util.RestResultType;
import com.ukefu.webim.web.handler.Handler;
import com.ukefu.webim.web.model.QuickType;
@RestController
@RequestMapping("/api/quicktype")
@Api(value = "快捷回复分类服务", description = "快捷回复分类管理功能")
public class ApiQuickTypeController extends Handler{

@Autowired
 private  QuickTypeRepository quickTypeRepository;

@Autowired
 private  QuickReplyRepository quickReplyRepository;


@RequestMapping(method = RequestMethod.GET)
@Menu(type = "apps", subtype = "quicktype", access = true)
@ApiOperation("返回快捷回复分类")
public ResponseEntity<RestResult> list(HttpServletRequest request,String id,String quicktype){
    if (StringUtils.isNotBlank(id)) {
        return new ResponseEntity<>(new RestResult(RestResultType.OK, quickTypeRepository.findOne(id)), HttpStatus.OK);
    }
    List<QuickType> quickTypeList = quickTypeRepository.findByOrgiAndQuicktype(getOrgi(request), quicktype);
    return new ResponseEntity<>(new RestResult(RestResultType.OK, quickTypeList), HttpStatus.OK);
}


@RequestMapping(method = RequestMethod.DELETE)
@Menu(type = "apps", subtype = "reply", access = true)
@ApiOperation("删除快捷回复分类,并且删除分类下的快捷回复")
public ResponseEntity<RestResult> delete(HttpServletRequest request,String id){
    RestResult result = new RestResult(RestResultType.OK);
    if (!StringUtils.isBlank(id)) {
        QuickType quickType = quickTypeRepository.findOne(id);
        if (quickType != null) {
            quickReplyRepository.deleteByCate(quickType.getId(), quickType.getOrgi());
            quickTypeRepository.delete(quickType);
        } else {
            return new ResponseEntity<>(new RestResult(RestResultType.ORGAN_DELETE), HttpStatus.OK);
        }
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
}


@RequestMapping(method = RequestMethod.PUT)
@Menu(type = "apps", subtype = "quicktype", access = true)
@ApiOperation("新增或修改快捷回复")
public ResponseEntity<RestResult> put(HttpServletRequest request,QuickType quickType){
    if (quickType != null && !StringUtils.isBlank(quickType.getName())) {
        quickType.setOrgi(getOrgi(request));
        quickType.setCreater(getUser(request).getId());
        quickType.setCreatetime(new Date());
        if (StringUtils.isNotBlank(quickType.getId())) {
            quickType.setUpdatetime(new Date());
        }
        quickType = quickTypeRepository.save(quickType);
    }
    return new ResponseEntity<>(new RestResult(RestResultType.OK, quickType), HttpStatus.OK);
}


}
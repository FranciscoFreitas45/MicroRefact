package com.cym.controller.api;
 import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.cym.controller.adminPage.CertController;
import com.cym.model.Cert;
import com.cym.service.CertService;
import com.cym.utils.BaseController;
import com.cym.utils.JsonResult;
import com.cym.utils.SystemTool;
import cn.craccd.sqlHelper.bean.Page;
import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.cym.DTO.JsonResult;
@Api(tags = "证书接口")
@RestController
@RequestMapping("/api/cert")
public class CertApiController extends BaseController{

@Autowired
 private CertController certController;

@Autowired
 private CertService certService;


@ApiOperation("添加或编辑证书")
@PostMapping("addOver")
public JsonResult addOver(Cert cert){
    if (StrUtil.isEmpty(cert.getDomain())) {
        return renderError("域名为空");
    }
    if (cert.getType() == 0) {
        if (StrUtil.isEmpty(cert.getDnsType())) {
            return renderError("dns提供商为空");
        }
        if (cert.getDnsType().equals("ali") && (StrUtil.isEmpty(cert.getAliKey()) || StrUtil.isEmpty(cert.getAliSecret()))) {
            return renderError("aliKey 或 aliSecret为空");
        }
        if (cert.getDnsType().equals("dp") && (StrUtil.isEmpty(cert.getDpId()) || StrUtil.isEmpty(cert.getDpKey()))) {
            return renderError("dpId 或 dpKey为空");
        }
        if (cert.getDnsType().equals("cf") && (StrUtil.isEmpty(cert.getCfEmail()) || StrUtil.isEmpty(cert.getCfKey()))) {
            return renderError("cfEmail 或 cfKey为空");
        }
        if (cert.getDnsType().equals("gd") && (StrUtil.isEmpty(cert.getGdKey()) || StrUtil.isEmpty(cert.getGdSecret()))) {
            return renderError("gdKey 或 gdSecret为空");
        }
    }
    return certController.addOver(cert, null, null, null);
}


@ApiOperation("下载证书文件")
@PostMapping("download")
public void download(String id,HttpServletResponse response){
    certController.download(id, response);
}


@ApiOperation("设置证书自动续签")
@PostMapping("setAutoRenew")
public JsonResult setAutoRenew(String id,Integer autoRenew){
    Cert cert = new Cert();
    cert.setId(id);
    cert.setAutoRenew(autoRenew);
    certController.setAutoRenew(cert);
    return renderSuccess();
}


@ApiOperation("执行申请")
@PostMapping("apply")
public JsonResult apply(String id,String type){
    return certController.apply(id, type);
}


@ApiOperation("获取证书分页列表")
@PostMapping("getPage")
public JsonResult<Page<Cert>> getPage(Integer current,Integer limit,String keywords){
    Page page = new Page();
    page.setCurr(current);
    page.setLimit(limit);
    page = certService.getPage(keywords, page);
    return renderSuccess(page);
}


@ApiOperation("删除证书")
@PostMapping("del")
public JsonResult del(String id){
    return certController.del(id);
}


@ApiOperation("获取域名解析码")
@PostMapping("getTxtValue")
public JsonResult getTxtValue(String certId){
    if (!SystemTool.isLinux()) {
        return renderError(m.get("certStr.error2"));
    }
    Cert cert = sqlHelper.findById(certId, Cert.class);
    JsonResult jsonResult = certController.getTxtValue(cert.getDomain());
    List<String> domains = new ArrayList<>();
    List<String> types = new ArrayList<>();
    List<String> values = new ArrayList<>();
    List<Map<String, String>> list = (List<Map<String, String>>) jsonResult.getObj();
    if (list != null && list.size() > 0) {
        for (Map<String, String> map : list) {
            domains.add(map.get("domain"));
            types.add(map.get("type"));
            values.add(map.get("value"));
        }
    }
    certService.insertOrUpdate(cert, domains.toArray(new String[] {}), types.toArray(new String[] {}), values.toArray(new String[] {}));
    return jsonResult;
}


}
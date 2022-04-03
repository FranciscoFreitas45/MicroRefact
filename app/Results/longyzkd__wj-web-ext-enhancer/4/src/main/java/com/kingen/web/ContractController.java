package com.kingen.web;
 import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.activiti.engine.repository.Deployment;
import org.apache.commons.io.FilenameUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.kingen.aop.ControllerLogAnnotation;
import com.kingen.bean.ClientContact;
import com.kingen.bean.Contract;
import com.kingen.bean.ContractAttach;
import com.kingen.service.contract.ContractService;
import com.kingen.util.JsonResultBuilder;
import com.kingen.util.Page;
import com.kingen.util.Parameter;
import com.kingen.util.StringUtils;
import com.kingen.vo.Comboable;
@Controller
@RequestMapping(value = "/contract")
public class ContractController extends CommonController{

 private  Logger logger;

@Autowired
 private  ContractService service;


@RequestMapping(value = "/")
@ControllerLogAnnotation(moduleName = "服务管理", option = "合同管理")
@RequiresPermissions("contract:view")
public String serviceLv(Model m,HttpServletResponse response){
    return "contract/list";
}


@RequestMapping(value = "data")
public void data(String clientId,Page<Contract> page,HttpServletResponse response,Model model){
    if (!StringUtils.isEmpty(clientId)) {
        Parameter p = new Parameter(clientId);
        page = service.findContractVo(page, p);
    }
    writeJson(response, page);
}


@RequestMapping(value = "/attach/upload")
public void upload(String contractId,MultipartFile file,RedirectAttributes redirectAttributes,HttpServletResponse response){
    String fileName = file.getOriginalFilename();
    JSONObject jsonObject = new JSONObject();
    try {
        InputStream fileInputStream = file.getInputStream();
        String extension = FilenameUtils.getExtension(fileName);
        ContractAttach attach = new ContractAttach();
        attach.setAttachName(fileName);
        attach.setContractId(contractId);
        // hibernate4已经取消createBlob
        // Blob blob = Hibernate.createBlob(file.getInputStream());
        byte[] attachContent = FileCopyUtils.copyToByteArray(fileInputStream);
        attach.setAttachContent(attachContent);
        service.addObj(attach);
        // 
        // if (extension.equals("zip") || extension.equals("rar")) {
        // ZipInputStream zip = new ZipInputStream(fileInputStream);
        // deployment = repositoryService.createDeployment().addZipInputStream(zip).deploy();
        // 
        // attach.setAttach(attach);
        // } else {
        // deployment = repositoryService.createDeployment().addInputStream(fileName, fileInputStream).deploy();
        // 
        // attach.setAttach(attach);
        // }
        jsonObject = JsonResultBuilder.success(true).msg("上传成功！").json();
    } catch (Exception e) {
        // redirectAttributes.addFlashAttribute("message", "流程部署失败！");
        jsonObject = JsonResultBuilder.success(false).msg("上传失败！").json();
        logger.error("上传失败！", e);
    }
    writeJson(response, jsonObject);
}


@RequestMapping(value = "one")
public void one(String id,HttpServletResponse response){
    Contract u = service.unique(id);
    writeJson(response, u);
}


@RequestMapping(value = "clientContactData/{clientId}")
public void clientContactData(String clientId,HttpServletResponse response){
    List<ClientContact> result = service.findClientContacts(clientId);
    List<Comboable> com = Lists.newArrayList();
    if (!CollectionUtils.isEmpty(result)) {
        for (Object o : result) {
            if (o instanceof Comboable) {
                com.add((Comboable) o);
            }
        }
    }
    writeJson(response, com);
}


@RequestMapping(value = "save")
@ControllerLogAnnotation(moduleName = "服务管理-合同管理", option = "新增")
public void save(Contract data,HttpServletResponse response){
    JSONObject json = new JSONObject();
    try {
        service.add(data);
        json = JsonResultBuilder.success(true).msg("保存成功").json();
    } catch (Exception e) {
        // service ：回滚、记录异常日志
        // TODO Auto-generated catch block
        e.printStackTrace();
        json = JsonResultBuilder.success(false).msg(e.getMessage()).json();
        logger.error(e.getMessage());
    }
    writeJson(response, json);
}


@RequestMapping(value = "update")
@ControllerLogAnnotation(moduleName = "服务管理-合同管理", option = "编辑")
public void update(Contract data,HttpServletResponse response){
    JSONObject json = new JSONObject();
    try {
        service.update(data);
        json = JsonResultBuilder.success(true).msg("保存成功").json();
    } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        json = JsonResultBuilder.success(false).msg(e.getMessage()).json();
        logger.error(e.getMessage());
    }
    writeJson(response, json);
}


@RequestMapping(value = "toEdit")
public String toEdit(String id,String action,String clientId,HttpServletResponse response,HttpServletRequest request,Model model){
    model.addAttribute("action", action);
    // null的话 前台是空串
    model.addAttribute("id", id);
    // null的话 前台是空串
    model.addAttribute("clientId", clientId);
    return "contract/edit";
}


@ControllerLogAnnotation(moduleName = "服务管理-合同管理", option = "删除")
@RequestMapping(value = "deleteThem")
public void deleteThem(String[] ids,HttpServletResponse response){
    JSONObject json = new JSONObject();
    try {
        service.delThem(Arrays.asList(ids));
        json = JsonResultBuilder.success(true).msg("删除成功").json();
    } catch (Exception e) {
        json = JsonResultBuilder.success(false).msg("系统出错").json();
        logger.error(e.getMessage());
        e.printStackTrace();
    }
    writeJson(response, json);
}


@RequestMapping(value = "attach/data")
public void attachData(Page<ContractAttach> page,HttpServletResponse response){
    page = service.find(page, ContractAttach.class);
    writeJson(response, page);
}


}
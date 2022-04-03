package com.ukefu.webim.web.handler.apps.setting;
 import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.ukefu.core.UKDataContext;
import com.ukefu.util.Menu;
import com.ukefu.util.UKTools;
import com.ukefu.util.task.DSData;
import com.ukefu.util.task.DSDataEvent;
import com.ukefu.util.task.ExcelImportProecess;
import com.ukefu.util.task.export.ExcelExporterProcess;
import com.ukefu.util.task.process.QuickReplyProcess;
import com.ukefu.webim.service.es.QuickReplyRepository;
import com.ukefu.webim.service.repository.MetadataRepository;
import com.ukefu.webim.service.repository.QuickTypeRepository;
import com.ukefu.webim.service.repository.ReporterRepository;
import com.ukefu.webim.web.handler.Handler;
import com.ukefu.webim.web.model.MetadataTable;
import com.ukefu.webim.web.model.QuickReply;
import com.ukefu.webim.web.model.QuickType;
import Interface.MetadataRepository;
import Interface.ReporterRepository;
import DTO.DSDataEvent;
import DTO.ExcelExporterProcess;
@Controller
@RequestMapping("/setting/quickreply")
public class QuickReplyController extends Handler{

@Autowired
 private  QuickReplyRepository quickReplyRes;

@Autowired
 private  QuickTypeRepository quickTypeRes;

@Autowired
 private  MetadataRepository metadataRes;

@Autowired
 private  ReporterRepository reporterRes;

@Value("${web.upload-path}")
 private  String path;


@RequestMapping({ "/addtype" })
@Menu(type = "apps", subtype = "kbs")
public ModelAndView addtype(ModelMap map,HttpServletRequest request,String typeid){
    map.addAttribute("quickTypeList", quickTypeRes.findByOrgiAndQuicktype(super.getOrgi(request), UKDataContext.QuickTypeEnum.PUB.toString()));
    if (!StringUtils.isBlank(typeid)) {
        map.addAttribute("quickType", quickTypeRes.findByIdAndOrgi(typeid, super.getOrgi(request)));
    }
    return request(super.createRequestPageTempletResponse("/apps/setting/quickreply/addtype"));
}


@RequestMapping("/save")
@Menu(type = "setting", subtype = "quickreply", admin = true)
public ModelAndView quickreplysave(ModelMap map,HttpServletRequest request,QuickReply quickReply){
    if (!StringUtils.isBlank(quickReply.getTitle()) && !StringUtils.isBlank(quickReply.getContent())) {
        quickReply.setOrgi(super.getOrgi(request));
        quickReply.setCreater(super.getUser(request).getId());
        quickReply.setType(UKDataContext.QuickTypeEnum.PUB.toString());
        quickReplyRes.save(quickReply);
    }
    return request(super.createRequestPageTempletResponse("redirect:/setting/quickreply/index.html?typeid=" + quickReply.getCate()));
}


@RequestMapping("/update")
@Menu(type = "setting", subtype = "quickreply", admin = true)
public ModelAndView quickreplyupdate(ModelMap map,HttpServletRequest request,QuickReply quickReply){
    if (!StringUtils.isBlank(quickReply.getId())) {
        QuickReply temp = quickReplyRes.findOne(quickReply.getId());
        quickReply.setOrgi(super.getOrgi(request));
        quickReply.setCreater(super.getUser(request).getId());
        if (temp != null) {
            quickReply.setCreatetime(temp.getCreatetime());
        }
        quickReply.setType(UKDataContext.QuickTypeEnum.PUB.toString());
        quickReplyRes.save(quickReply);
    }
    return request(super.createRequestPageTempletResponse("redirect:/setting/quickreply/index.html?typeid=" + quickReply.getCate()));
}


@RequestMapping("/impsave")
@Menu(type = "setting", subtype = "quickreplyimpsave")
public ModelAndView impsave(ModelMap map,HttpServletRequest request,MultipartFile cusfile,String type){
    DSDataEvent event = new DSDataEvent();
    String fileName = "quickreply/" + UKTools.getUUID() + cusfile.getOriginalFilename().substring(cusfile.getOriginalFilename().lastIndexOf("."));
    File excelFile = new File(path, fileName);
    if (!excelFile.getParentFile().exists()) {
        excelFile.getParentFile().mkdirs();
    }
    MetadataTable table = metadataRes.findByTablename("uk_quickreply");
    if (table != null) {
        FileUtils.writeByteArrayToFile(new File(path, fileName), cusfile.getBytes());
        event.setDSData(new DSData(table, excelFile, cusfile.getContentType(), super.getUser(request)));
        event.getDSData().setClazz(QuickReply.class);
        event.setOrgi(super.getOrgi(request));
        if (!StringUtils.isBlank(type)) {
            event.getValues().put("cate", type);
        } else {
            event.getValues().put("cate", UKDataContext.DEFAULT_TYPE);
        }
        event.getValues().put("type", UKDataContext.QuickTypeEnum.PUB.toString());
        event.getValues().put("creater", super.getUser(request).getId());
        event.getDSData().setProcess(new QuickReplyProcess(quickReplyRes));
        reporterRes.save(event.getDSData().getReport());
        // 启动导入任务
        new ExcelImportProecess(event).process();
    }
    return request(super.createRequestPageTempletResponse("redirect:/setting/quickreply/index.html" + (!StringUtils.isBlank(type) ? "?typeid=" + type : "")));
}


@RequestMapping("/index")
@Menu(type = "setting", subtype = "quickreply", admin = true)
public ModelAndView index(ModelMap map,HttpServletRequest request,String typeid){
    List<QuickType> quickTypeList = quickTypeRes.findByOrgiAndQuicktype(super.getOrgi(request), UKDataContext.QuickTypeEnum.PUB.toString());
    if (!StringUtils.isBlank(typeid)) {
        map.put("quickType", quickTypeRes.findByIdAndOrgi(typeid, super.getOrgi(request)));
        map.put("quickReplyList", quickReplyRes.getByOrgiAndCate(super.getOrgi(request), typeid, null, new PageRequest(super.getP(request), super.getPs(request))));
    } else {
        map.put("quickReplyList", quickReplyRes.getByOrgiAndType(super.getOrgi(request), UKDataContext.QuickTypeEnum.PUB.toString(), null, new PageRequest(super.getP(request), super.getPs(request))));
    }
    map.put("pubQuickTypeList", quickTypeList);
    return request(super.createAppsTempletResponse("/apps/setting/quickreply/index"));
}


@RequestMapping("/type/save")
@Menu(type = "apps", subtype = "kbs")
public ModelAndView typesave(HttpServletRequest request,QuickType quickType){
    QuickType qr = quickTypeRes.findByOrgiAndName(super.getOrgi(request), quickType.getName());
    if (qr == null) {
        quickType.setOrgi(super.getOrgi(request));
        quickType.setCreater(super.getUser(request).getId());
        quickType.setCreatetime(new Date());
        quickType.setQuicktype(UKDataContext.QuickTypeEnum.PUB.toString());
        quickTypeRes.save(quickType);
    } else {
        return request(super.createRequestPageTempletResponse("redirect:/setting/quickreply/index.html?msg=qr_type_exist"));
    }
    return request(super.createRequestPageTempletResponse("redirect:/setting/quickreply/index.html"));
}


@RequestMapping("/replylist")
@Menu(type = "setting", subtype = "quickreply", admin = true)
public ModelAndView list(ModelMap map,HttpServletRequest request,String typeid){
    if (!StringUtils.isBlank(typeid) && !typeid.equals("0")) {
        map.put("quickReplyList", quickReplyRes.getByOrgiAndCate(super.getOrgi(request), typeid, null, new PageRequest(super.getP(request), super.getPs(request))));
    } else {
        map.put("quickReplyList", quickReplyRes.getByOrgiAndType(super.getOrgi(request), UKDataContext.QuickTypeEnum.PUB.toString(), null, new PageRequest(super.getP(request), super.getPs(request))));
    }
    map.put("quickType", quickTypeRes.findByIdAndOrgi(typeid, super.getOrgi(request)));
    return request(super.createRequestPageTempletResponse("/apps/setting/quickreply/replylist"));
}


@RequestMapping("/expsearch")
@Menu(type = "setting", subtype = "quickreplyexpsearch")
public void expall(ModelMap map,HttpServletRequest request,HttpServletResponse response,String q,String type){
    Iterable<QuickReply> topicList = quickReplyRes.getQuickReplyByOrgi(super.getOrgi(request), type, UKDataContext.QuickTypeEnum.PUB.toString(), q);
    MetadataTable table = metadataRes.findByTablename("uk_quickreply");
    List<Map<String, Object>> values = new ArrayList<Map<String, Object>>();
    for (QuickReply topic : topicList) {
        values.add(UKTools.transBean2Map(topic));
    }
    response.setHeader("content-disposition", "attachment;filename=UCKeFu-QuickReply-" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ".xls");
    if (table != null) {
        ExcelExporterProcess excelProcess = new ExcelExporterProcess(values, table, response.getOutputStream());
        excelProcess.process();
    }
    return;
}


@RequestMapping("/imp")
@Menu(type = "setting", subtype = "quickreplyimp")
public ModelAndView imp(ModelMap map,HttpServletRequest request,String type){
    map.addAttribute("type", type);
    return request(super.createRequestPageTempletResponse("/apps/setting/quickreply/imp"));
}


@RequestMapping("/delete")
@Menu(type = "setting", subtype = "quickreply", admin = true)
public ModelAndView quickreplydelete(ModelMap map,HttpServletRequest request,String id){
    QuickReply quickReply = quickReplyRes.findOne(id);
    if (quickReply != null) {
        quickReplyRes.delete(quickReply);
    }
    return request(super.createRequestPageTempletResponse("redirect:/setting/quickreply/index.html?typeid=" + quickReply.getCate()));
}


@RequestMapping({ "/edittype" })
@Menu(type = "apps", subtype = "kbs")
public ModelAndView edittype(ModelMap map,HttpServletRequest request,String id){
    map.addAttribute("quickType", quickTypeRes.findByIdAndOrgi(id, super.getOrgi(request)));
    map.addAttribute("quickTypeList", quickTypeRes.findByOrgiAndQuicktype(super.getOrgi(request), UKDataContext.QuickTypeEnum.PUB.toString()));
    return request(super.createRequestPageTempletResponse("/apps/setting/quickreply/edittype"));
}


@RequestMapping("/edit")
@Menu(type = "setting", subtype = "quickreply", admin = true)
public ModelAndView quickreplyedit(ModelMap map,HttpServletRequest request,String id){
    QuickReply quickReply = quickReplyRes.findOne(id);
    map.put("quickReply", quickReply);
    if (quickReply != null) {
        map.put("quickType", quickTypeRes.findByIdAndOrgi(quickReply.getCate(), super.getOrgi(request)));
    }
    map.addAttribute("quickTypeList", quickTypeRes.findByOrgiAndQuicktype(super.getOrgi(request), UKDataContext.QuickTypeEnum.PUB.toString()));
    return request(super.createRequestPageTempletResponse("/apps/setting/quickreply/edit"));
}


@RequestMapping("/expids")
@Menu(type = "setting", subtype = "quickreplyexpids")
public void expids(ModelMap map,HttpServletRequest request,HttpServletResponse response,String[] ids){
    if (ids != null && ids.length > 0) {
        Iterable<QuickReply> topicList = quickReplyRes.findAll(Arrays.asList(ids));
        MetadataTable table = metadataRes.findByTablename("uk_quickreply");
        List<Map<String, Object>> values = new ArrayList<Map<String, Object>>();
        for (QuickReply topic : topicList) {
            values.add(UKTools.transBean2Map(topic));
        }
        response.setHeader("content-disposition", "attachment;filename=UCKeFu-QuickReply-" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ".xls");
        if (table != null) {
            ExcelExporterProcess excelProcess = new ExcelExporterProcess(values, table, response.getOutputStream());
            excelProcess.process();
        }
    }
    return;
}


@RequestMapping({ "/batdel/reply" })
@Menu(type = "apps", subtype = "kbs")
public ModelAndView batdelReply(ModelMap map,HttpServletRequest request,String id){
    if (!StringUtils.isBlank(id)) {
        Page<QuickReply> quickReplyList = quickReplyRes.getByOrgiAndCate(super.getOrgi(request), id, null, new PageRequest(0, 10000));
        quickReplyRes.delete(quickReplyList.getContent());
    }
    return request(super.createRequestPageTempletResponse("redirect:/setting/quickreply/index.html?typeid=" + id));
}


@RequestMapping({ "/deletetype" })
@Menu(type = "apps", subtype = "kbs")
public ModelAndView deletetype(ModelMap map,HttpServletRequest request,String id){
    if (!StringUtils.isBlank(id)) {
        QuickType tempQuickType = quickTypeRes.findByIdAndOrgi(id, super.getOrgi(request));
        quickTypeRes.delete(tempQuickType);
        Page<QuickReply> quickReplyList = quickReplyRes.getByOrgiAndCate(super.getOrgi(request), id, null, new PageRequest(0, 10000));
        quickReplyRes.delete(quickReplyList.getContent());
    }
    return request(super.createRequestPageTempletResponse("redirect:/setting/quickreply/index.html"));
}


@RequestMapping("/type/update")
@Menu(type = "apps", subtype = "kbs")
public ModelAndView typeupdate(HttpServletRequest request,QuickType quickType){
    QuickType tempQuickType = quickTypeRes.findByIdAndOrgi(quickType.getId(), super.getOrgi(request));
    if (tempQuickType != null) {
        // 判断名称是否重复
        QuickType qr = quickTypeRes.findByOrgiAndName(super.getOrgi(request), quickType.getName());
        if (qr != null && !qr.getId().equals(quickType.getId())) {
            return request(super.createRequestPageTempletResponse("redirect:/setting/quickreply/index.html?msg=qr_type_exist&typeid=" + quickType.getId()));
        }
        tempQuickType.setName(quickType.getName());
        tempQuickType.setDescription(quickType.getDescription());
        tempQuickType.setInx(quickType.getInx());
        tempQuickType.setParentid(quickType.getParentid());
        quickTypeRes.save(tempQuickType);
    }
    return request(super.createRequestPageTempletResponse("redirect:/setting/quickreply/index.html?typeid=" + quickType.getId()));
}


@RequestMapping("/add")
@Menu(type = "setting", subtype = "quickreplyadd", admin = true)
public ModelAndView quickreplyadd(ModelMap map,HttpServletRequest request,String parentid){
    if (!StringUtils.isBlank(parentid)) {
        map.addAttribute("quickType", quickTypeRes.findByIdAndOrgi(parentid, super.getOrgi(request)));
    }
    map.addAttribute("quickTypeList", quickTypeRes.findByOrgiAndQuicktype(super.getOrgi(request), UKDataContext.QuickTypeEnum.PUB.toString()));
    return request(super.createRequestPageTempletResponse("/apps/setting/quickreply/add"));
}


@RequestMapping("/batdelete")
@Menu(type = "setting", subtype = "quickreplybatdelete")
public ModelAndView batdelete(ModelMap map,HttpServletRequest request,HttpServletResponse response,String[] ids,String type){
    if (ids != null && ids.length > 0) {
        Iterable<QuickReply> topicList = quickReplyRes.findAll(Arrays.asList(ids));
        quickReplyRes.delete(topicList);
    }
    return request(super.createRequestPageTempletResponse("redirect:/setting/quickreply/index.html" + (!StringUtils.isBlank(type) ? "?typeid=" + type : "")));
}


}
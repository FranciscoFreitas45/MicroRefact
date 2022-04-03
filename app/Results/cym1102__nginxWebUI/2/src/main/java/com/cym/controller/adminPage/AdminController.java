package com.cym.controller.adminPage;
 import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.cym.ext.AdminExt;
import com.cym.ext.Tree;
import com.cym.model.Admin;
import com.cym.model.Group;
import com.cym.service.AdminService;
import com.cym.service.GroupService;
import com.cym.service.SettingService;
import com.cym.utils.AuthUtils;
import com.cym.utils.BaseController;
import com.cym.utils.JsonResult;
import com.cym.utils.SendMailUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import cn.craccd.sqlHelper.bean.Page;
import cn.hutool.core.util.StrUtil;
import com.cym.Interface.SettingService;
import com.cym.Interface.GroupService;
import com.cym.Interface.RemoteController;
@Controller
@RequestMapping("/adminPage/admin")
public class AdminController extends BaseController{

@Autowired
 private AdminService adminService;

@Autowired
 private SettingService settingService;

@Autowired
 private SendMailUtils sendCloudUtils;

@Autowired
 private AuthUtils authUtils;

@Autowired
 private GroupService groupService;

@Autowired
 private RemoteController remoteController;


@RequestMapping("getMailSetting")
@ResponseBody
public JsonResult getMailSetting(){
    Map<String, String> map = new HashMap<>();
    map.put("mail_host", settingService.get("mail_host"));
    map.put("mail_port", settingService.get("mail_port"));
    map.put("mail_from", settingService.get("mail_from"));
    map.put("mail_user", settingService.get("mail_user"));
    map.put("mail_pass", settingService.get("mail_pass"));
    map.put("mail_ssl", settingService.get("mail_ssl"));
    map.put("mail_interval", settingService.get("mail_interval"));
    return renderSuccess(map);
}


@RequestMapping("addOver")
@ResponseBody
public JsonResult addOver(Admin admin,String[] parentId){
    if (StrUtil.isEmpty(admin.getId())) {
        Long count = adminService.getCountByName(admin.getName());
        if (count > 0) {
            return renderError(m.get("adminStr.nameRepetition"));
        }
    } else {
        Long count = adminService.getCountByNameWithOutId(admin.getName(), admin.getId());
        if (count > 0) {
            return renderError(m.get("adminStr.nameRepetition"));
        }
    }
    if (admin.getAuth()) {
        admin.setKey(authUtils.makeKey());
    } else {
        admin.setKey("");
    }
    adminService.addOver(admin, parentId);
    return renderSuccess();
}


@RequestMapping("testAuth")
@ResponseBody
public JsonResult testAuth(String key,String code){
    Boolean rs = authUtils.testKey(key, code);
    return renderSuccess(rs);
}


@RequestMapping("")
public ModelAndView index(HttpSession httpSession,ModelAndView modelAndView,Page page){
    page = adminService.search(page);
    modelAndView.addObject("page", page);
    modelAndView.setViewName("/adminPage/admin/index");
    return modelAndView;
}


@RequestMapping("del")
@ResponseBody
public JsonResult del(String id){
    sqlHelper.deleteById(id, Admin.class);
    return renderSuccess();
}


@RequestMapping("detail")
@ResponseBody
public JsonResult detail(String id){
    AdminExt adminExt = new AdminExt();
    adminExt.setAdmin(sqlHelper.findById(id, Admin.class));
    adminExt.setGroupIds(adminService.getGroupIds(adminExt.getAdmin().getId()));
    return renderSuccess(adminExt);
}


@RequestMapping("testMail")
@ResponseBody
public JsonResult testMail(String mail){
    if (StrUtil.isEmpty(mail)) {
        return renderError(m.get("adminStr.emailEmpty"));
    }
    try {
        sendCloudUtils.sendMailSmtp(mail, m.get("adminStr.emailTest"), m.get("adminStr.emailTest"));
        return renderSuccess();
    } catch (Exception e) {
        e.printStackTrace();
        return renderError(e.getMessage());
    }
}


@RequestMapping("updateMailSetting")
@ResponseBody
public JsonResult updateMailSetting(String mailType,String mail_user,String mail_host,String mail_port,String mail_from,String mail_pass,String mail_ssl,String mail_interval){
    settingService.set("mail_host", mail_host);
    settingService.set("mail_port", mail_port);
    settingService.set("mail_user", mail_user);
    settingService.set("mail_from", mail_from);
    settingService.set("mail_pass", mail_pass);
    settingService.set("mail_ssl", mail_ssl);
    settingService.set("mail_interval", mail_interval);
    return renderSuccess();
}


@RequestMapping(value = "qr")
public void getqcode(HttpServletResponse resp,String url,Integer w,Integer h){
    if (url != null && !"".equals(url)) {
        ServletOutputStream stream = null;
        if (w == null) {
            w = 300;
        }
        if (h == null) {
            h = 300;
        }
        try {
            stream = resp.getOutputStream();
            Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            hints.put(EncodeHintType.MARGIN, 0);
            BitMatrix matrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, w, h, hints);
            MatrixToImageWriter.writeToStream(matrix, "png", stream);
        } catch (WriterException e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                stream.flush();
                stream.close();
            }
        }
    }
}


@RequestMapping("getGroupTree")
@ResponseBody
public JsonResult getGroupTree(){
    List<Group> groups = groupService.getListByParent(null);
    List<Tree> treeList = new ArrayList<>();
    remoteController.fillTree(groups, treeList);
    return renderSuccess(treeList);
}


}
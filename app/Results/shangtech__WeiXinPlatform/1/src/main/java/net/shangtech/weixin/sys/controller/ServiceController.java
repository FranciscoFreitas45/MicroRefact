package net.shangtech.weixin.sys.controller;
 import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.shangtech.ssh.core.base.BaseController;
import net.shangtech.ssh.core.base.Page;
import net.shangtech.ssh.core.util.FileUtils;
import net.shangtech.weixin.Config;
import net.shangtech.weixin.HttpUtil;
import net.shangtech.weixin.sys.entity.SysUser;
import net.shangtech.weixin.weixin.entity.WxMenu;
import net.shangtech.weixin.weixin.entity.WxMessage;
import net.shangtech.weixin.weixin.service.WxMenuService;
import net.shangtech.weixin.weixin.service.WxMessageService;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
@Controller
@RequestMapping("/manage/service")
public class ServiceController extends BaseController{

@Autowired
 private  WxMenuService menuService;

@Autowired
 private  WxMessageService messageService;

 private  List<String> ALLOW_TYPES;


@RequestMapping("/wxmenu/delete")
public String deleteMenu(HttpServletRequest request,HttpServletResponse response){
    Integer id = getId(request);
    if (id == null) {
        return failed(response, "未选择");
    }
    WxMenu menu = menuService.find(id);
    if (menu == null) {
        return failed(response, "记录不存在");
    }
    SysUser user = getUser(request);
    if (menu.getSysUserId() != user.getId()) {
        return failed(response, "无权限");
    }
    if (menu.getParentId() == 0) {
        int count = menuService.countByUserAndParent(user.getId(), id);
        if (count > 0) {
            return failed(response, "请先删除子菜单");
        }
    }
    menuService.delete(id);
    return success(response, String.valueOf(id));
}


@RequestMapping("/messages/delete")
public String deleteMessages(HttpServletRequest request,HttpServletResponse response){
    return null;
}


@RequestMapping("/messages/single")
public String messageEditSingle(HttpServletRequest request){
    WxMessage message = new WxMessage();
    message.setCreateTime(new Date());
    Integer id = getId(request);
    if (id != null) {
        message = messageService.find(id);
    }
    request.setAttribute("message", message);
    return "user/service/single-messages";
}


@RequestMapping("/messages/single/save")
public String singleMessagesSave(HttpServletRequest request,WxMessage message,HttpServletResponse response){
    SysUser user = getUser(request);
    message.setSysUserId(user.getId());
    message.setCreateTime(new Date());
    messageService.saveMessages(Arrays.asList(message));
    return success(response);
}


@RequestMapping("/messages/multiple")
public String messageEditMultiple(HttpServletRequest request){
    WxMessage message = new WxMessage();
    message.setCreateTime(new Date());
    Integer id = getId(request);
    if (id != null) {
        message = messageService.find(id);
        message.setSubMessages(messageService.find("where mainId=?", message.getId()));
    }
    request.setAttribute("message", message);
    return "user/service/multiple-messages";
}


@RequestMapping("/wxmenu/save")
public String saveMenu(HttpServletRequest request,HttpServletResponse response){
    Integer id = super.getId(request);
    WxMenu menu = new WxMenu();
    String menuName = request.getParameter("menuName");
    String parentId = request.getParameter("parentId");
    String menuUrl = request.getParameter("menuUrl");
    if (StringUtils.isBlank(parentId)) {
        parentId = "0";
    }
    SysUser user = getUser(request);
    if (id != null) {
        menu = menuService.find(id);
        if (menu == null)
            return failed(response, "记录不存在");
        if (menu.getSysUserId() != user.getId()) {
        // return failed("无权限");
        }
        menu.setMenuName(menuName);
        menu.setMenuUrl(menuUrl);
        menuService.update(menu);
    } else {
        menu.setMenuName(menuName);
        menu.setMenuUrl(menuUrl);
        int parent = Integer.parseInt(parentId);
        WxMenu parentMenu = menuService.find(parent);
        if (parentMenu == null) {
        // return failed("一级菜单错误");
        }
        if (parentMenu != null && parentMenu.getSysUserId() != user.getId()) {
        // return failed("无权限");
        }
        menu.setParentId(parent);
        int count = menuService.countByUserAndParent(0, parent);
        if (parent == 0 && count >= 3) {
            return failed(response, "一级菜单最多三个");
        }
        if (parent != 0 && count >= 5) {
            return failed(response, "二级菜单最多五个");
        }
        menu.setSort(count + 1);
        menu.setSysUserId(user.getId());
        menuService.add(menu);
    }
    JSONObject obj = new JSONObject();
    obj.put("success", true);
    obj.put("parentId", menu.getParentId());
    obj.put("menuName", menu.getMenuName());
    obj.put("menuUrl", menu.getMenuUrl());
    obj.put("id", menu.getId());
    out(response, obj.toJSONString());
    return null;
}


@RequestMapping("/wxmenu/sort")
public String sortMenu(HttpServletRequest request,HttpServletResponse response){
    String sortStr = request.getParameter("sort");
    if (StringUtils.isBlank(sortStr)) {
        return failed(response, "参数无效");
    }
    String[] sorts = sortStr.split(",");
    SysUser user = getUser(request);
    List<WxMenu> list = new ArrayList<WxMenu>();
    for (String sort : sorts) {
        String[] menuSort = sort.split(":");
        if (menuSort.length != 2) {
            return failed(response, "参数无效");
        }
        Integer id = Integer.parseInt(menuSort[0]);
        WxMenu menu = menuService.find(id);
        if (menu == null || menu.getSysUserId() != user.getId()) {
            return failed(response, "参数无效");
        }
        menu.setSort(Integer.parseInt(menuSort[1]));
        list.add(menu);
    }
    menuService.update(list);
    return success(response);
}


@RequestMapping("/messages/multiple/save")
public String multipleMessagesSave(HttpServletRequest request,HttpServletResponse response){
    SysUser user = getUser(request);
    String ids = request.getParameter("ids");
    // ids是各个表单字段后缀,ids出现的顺序也就是消息的顺序
    String[] idArr = ids.split(",");
    List<WxMessage> list = new LinkedList<WxMessage>();
    for (String id : idArr) {
        WxMessage message = new WxMessage();
        message.setSysUserId(user.getId());
        message.setId(getInt(request, "id_" + id));
        message.setTitle(getString(request, "title_" + id));
        message.setImage(getString(request, "image_" + id));
        message.setContent(getString(request, "content_" + id));
        list.add(message);
    }
    messageService.saveMessages(list);
    return success(response);
}


@RequestMapping("/messages")
public String messages(HttpServletRequest request){
    return messagesPage(request, "1");
}


@RequestMapping("/messages/p{pageInfo}")
public String messagesPage(HttpServletRequest request,String pageInfo){
    int pageSize = super.parsePageSize(pageInfo, 8);
    int pageNo = super.parsePageNo(pageInfo);
    SysUser user = super.getUser(request);
    Page<WxMessage> page = messageService.findMessageByPage(pageNo, pageSize, user.getId());
    request.setAttribute("page", page);
    return "user/service/messages";
}


@RequestMapping("/wxmenu")
public String wxmenu(HttpServletRequest request){
    SysUser user = getUser(request);
    List<WxMenu> list = menuService.findBySysUser(user.getId());
    request.setAttribute("list", list);
    return "user/service/menu";
}


@RequestMapping("/wxmenu/sync")
public String syncMenu(HttpServletRequest request,HttpServletResponse response){
    SysUser user = getUser(request);
    JSONArray array = new JSONArray();
    List<WxMenu> list = menuService.findBySysUser(user.getId());
    for (WxMenu menu : list) {
        JSONObject obj = new JSONObject();
        obj.put("name", menu.getMenuName());
        List<WxMenu> children = menu.getChildren();
        JSONArray subs = new JSONArray();
        // 如果有二级菜单就不用判断类型
        for (WxMenu child : children) {
            JSONObject sub = new JSONObject();
            sub.put("name", child.getMenuName());
            sub.put("type", "view");
            String url = child.getMenuUrl();
            if (url.contains("?")) {
                url += "&";
            } else {
                url += "?";
            }
            url += "a=" + user.getAppid();
            sub.put("url", url);
            // if(MenuType.CLICK.getType().equals(child.getMenuType())){
            // sub.put("type", child.getMenuType());
            // sub.put("key", child.getMenuKey());
            // }else if(MenuType.VIEW.getType().equals(child.getMenuType())){
            // sub.put("type", child.getMenuType());
            // sub.put("url", child.getMenuUrl());
            // }else{
            // continue;
            // }
            subs.add(sub);
        }
        if (!subs.isEmpty()) {
            obj.put("sub_button", subs);
            array.add(obj);
            continue;
        }
        obj.put("type", "view");
        String url = menu.getMenuUrl();
        if (url.contains("?")) {
            url += "&";
        } else {
            url += "?";
        }
        url += "a=" + user.getAppid();
        obj.put("url", url);
        // if(MenuType.CLICK.getType().equals(menu.getMenuType())){
        // obj.put("type", menu.getMenuType());
        // obj.put("key", menu.getMenuKey());
        // }else if(MenuType.VIEW.getType().equals(menu.getMenuType())){
        // obj.put("type", menu.getMenuType());
        // obj.put("url", menu.getMenuUrl());
        // }else{
        // continue;
        // }
        array.add(obj);
    }
    JSONObject result = new JSONObject();
    result.put("button", array);
    HttpClient client = HttpUtil.createSSLInsecureClient();
    String accessToken = HttpUtil.getAccessToken(client, user);
    if (accessToken == null) {
        return failed(response, "同步发生异常,请联系系统管理员");
    }
    HttpPost post = new HttpPost(Config.URL_POST_MENU_CREATE + "access_token=" + accessToken);
    HttpEntity entity = new StringEntity(result.toJSONString(), "UTF-8");
    post.setEntity(entity);
    try {
        String resp = HttpUtil.post(client, post);
        if (StringUtils.isBlank(resp)) {
            return failed(response, "同步失败");
        }
        JSONObject json = JSON.parseObject(resp);
        if ("0".equals(json.getString("errcode"))) {
            return success(response, "同步成功");
        }
        return failed(response, "同步失败");
    } catch (Exception e) {
        e.printStackTrace();
        return failed(response, "同步发生异常,请联系系统管理员");
    }
}


@RequestMapping("/messages/image/save")
public String imageSave(HttpServletResponse response,HttpServletRequest request){
    MultipartFile file = null;
    try {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        file = multipartRequest.getFile("image");
        if (file == null) {
            return failed(response, "文件上传失败");
        }
        String name = file.getOriginalFilename();
        if (StringUtils.isBlank(name) || !name.contains(".")) {
            return failed(response, "文件格式错误");
        }
        String extend = name.substring(name.lastIndexOf("."));
        if (!ALLOW_TYPES.contains(extend)) {
            return failed(response, "不支持的文件格式");
        }
        String path = FileUtils.saveStreamToFile(file.getInputStream(), file.getOriginalFilename());
        return success(response, path);
    } catch (ClassCastException e) {
        e.printStackTrace();
        return failed(response, "保存文件失败");
    } catch (IOException e) {
        e.printStackTrace();
        return failed(response, "保存文件失败");
    }
}


}
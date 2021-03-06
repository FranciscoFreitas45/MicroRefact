package com.ukefu.webim.web.handler.admin.callcenter;
 import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ukefu.util.Menu;
import com.ukefu.webim.service.cache.CacheHelper;
import com.ukefu.webim.service.repository.PbxHostRepository;
import com.ukefu.webim.service.repository.SipTrunkRepository;
import com.ukefu.webim.web.handler.Handler;
import com.ukefu.webim.web.model.SipTrunk;
@Controller
@RequestMapping("/admin/callcenter")
public class CallCenterSipTrunkController extends Handler{

@Autowired
 private  PbxHostRepository pbxHostRes;

@Autowired
 private  SipTrunkRepository sipTrunkRes;


@RequestMapping(value = "/siptrunk/edit")
@Menu(type = "callcenter", subtype = "extention", access = false, admin = true)
public ModelAndView siptrunkedit(ModelMap map,HttpServletRequest request,String id,String hostid){
    map.addAttribute("siptrunk", sipTrunkRes.findByIdAndOrgi(id, super.getOrgi(request)));
    map.put("pbxHost", pbxHostRes.findByIdAndOrgi(hostid, super.getOrgi(request)));
    return request(super.createRequestPageTempletResponse("/admin/callcenter/siptrunk/edit"));
}


@RequestMapping(value = "/siptrunk/code/update")
@Menu(type = "callcenter", subtype = "extention", access = false, admin = true)
public ModelAndView siptrunkcodeupdate(ModelMap map,HttpServletRequest request,SipTrunk siptrunk){
    if (!StringUtils.isBlank(siptrunk.getId())) {
        SipTrunk oldSipTrunk = sipTrunkRes.findByIdAndOrgi(siptrunk.getId(), super.getOrgi(request));
        if (!StringUtils.isBlank(siptrunk.getSipcontent())) {
            oldSipTrunk.setSipcontent(siptrunk.getSipcontent());
            sipTrunkRes.save(oldSipTrunk);
            CacheHelper.getSystemCacheBean().put(oldSipTrunk.getId(), oldSipTrunk, oldSipTrunk.getOrgi());
        }
    }
    return request(super.createRequestPageTempletResponse("redirect:/admin/callcenter/siptrunk.html?hostid=" + siptrunk.getHostid()));
}


@RequestMapping(value = "/siptrunk/add")
@Menu(type = "callcenter", subtype = "extention", access = false, admin = true)
public ModelAndView extentionadd(ModelMap map,HttpServletRequest request,String hostid){
    map.put("pbxHost", pbxHostRes.findByIdAndOrgi(hostid, super.getOrgi(request)));
    return request(super.createRequestPageTempletResponse("/admin/callcenter/siptrunk/add"));
}


@RequestMapping(value = "/siptrunk/code")
@Menu(type = "callcenter", subtype = "extention", access = false, admin = true)
public ModelAndView siptrunkcode(ModelMap map,HttpServletRequest request,String id,String hostid){
    map.addAttribute("siptrunk", sipTrunkRes.findByIdAndOrgi(id, super.getOrgi(request)));
    map.put("pbxHost", pbxHostRes.findByIdAndOrgi(hostid, super.getOrgi(request)));
    return request(super.createRequestPageTempletResponse("/admin/callcenter/siptrunk/code"));
}


@RequestMapping(value = "/siptrunk/update")
@Menu(type = "callcenter", subtype = "extention", access = false, admin = true)
public ModelAndView pbxhostupdate(ModelMap map,HttpServletRequest request,SipTrunk siptrunk){
    if (!StringUtils.isBlank(siptrunk.getId())) {
        SipTrunk oldSipTrunk = sipTrunkRes.findByIdAndOrgi(siptrunk.getId(), super.getOrgi(request));
        if (oldSipTrunk != null) {
            oldSipTrunk.setName(siptrunk.getName());
            oldSipTrunk.setSipserver(siptrunk.getSipserver());
            oldSipTrunk.setPort(siptrunk.getPort());
            oldSipTrunk.setProtocol(siptrunk.getProtocol());
            oldSipTrunk.setRegister(siptrunk.isRegister());
            oldSipTrunk.setDefaultsip(siptrunk.isDefaultsip());
            oldSipTrunk.setTitle(siptrunk.getTitle());
            oldSipTrunk.setEnablecallagent(siptrunk.isEnablecallagent());
            oldSipTrunk.setOutnumber(siptrunk.getOutnumber());
            oldSipTrunk.setBusyext(siptrunk.getBusyext());
            oldSipTrunk.setNotready(siptrunk.getNotready());
            oldSipTrunk.setNoname(siptrunk.getNoname());
            oldSipTrunk.setProvince(siptrunk.getProvince());
            oldSipTrunk.setCity(siptrunk.getCity());
            oldSipTrunk.setPrefix(siptrunk.getPrefix());
            oldSipTrunk.setPrefixstr(siptrunk.getPrefixstr());
            sipTrunkRes.save(oldSipTrunk);
            CacheHelper.getSystemCacheBean().put(oldSipTrunk.getId(), oldSipTrunk, oldSipTrunk.getOrgi());
        }
    }
    return request(super.createRequestPageTempletResponse("redirect:/admin/callcenter/siptrunk.html?hostid=" + siptrunk.getHostid()));
}


@RequestMapping(value = "/siptrunk/save")
@Menu(type = "callcenter", subtype = "extention", access = false, admin = true)
public ModelAndView extentionsave(ModelMap map,HttpServletRequest request,SipTrunk siptrunk){
    if (!StringUtils.isBlank(siptrunk.getName())) {
        int count = sipTrunkRes.countByNameAndOrgi(siptrunk.getName(), super.getOrgi(request));
        if (count == 0) {
            siptrunk.setOrgi(super.getOrgi(request));
            siptrunk.setCreater(super.getUser(request).getId());
            sipTrunkRes.save(siptrunk);
            CacheHelper.getSystemCacheBean().put(siptrunk.getId(), siptrunk, siptrunk.getOrgi());
        }
    }
    return request(super.createRequestPageTempletResponse("redirect:/admin/callcenter/siptrunk.html?hostid=" + siptrunk.getHostid()));
}


@RequestMapping(value = "/siptrunk")
@Menu(type = "callcenter", subtype = "callcenterresource", access = false, admin = true)
public ModelAndView skill(ModelMap map,HttpServletRequest request,String hostid){
    if (!StringUtils.isBlank(hostid)) {
        map.addAttribute("pbxHost", pbxHostRes.findByIdAndOrgi(hostid, super.getOrgi(request)));
        map.addAttribute("sipTrunkListList", sipTrunkRes.findByHostidAndOrgi(hostid, super.getOrgi(request)));
    }
    return request(super.createRequestPageTempletResponse("/admin/callcenter/siptrunk/index"));
}


@RequestMapping(value = "/siptrunk/delete")
@Menu(type = "callcenter", subtype = "extention", access = false, admin = true)
public ModelAndView extentiondelete(ModelMap map,HttpServletRequest request,String id,String hostid){
    if (!StringUtils.isBlank(id)) {
        sipTrunkRes.delete(id);
    }
    return request(super.createRequestPageTempletResponse("redirect:/admin/callcenter/siptrunk.html?hostid=" + hostid));
}


}
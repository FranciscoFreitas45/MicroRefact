package com.sobey.cmop.mvc.web.resource;
 import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.sobey.cmop.mvc.comm.BaseController;
import com.sobey.cmop.mvc.entity.Resources;
import com.sobey.cmop.mvc.service.redmine.RedmineService;
@Controller
@RequestMapping(value = "/resources/update")
public class ResourcesExtensionController extends BaseController{

 private  String REDIRECT_SUCCESS_URL;

 private  String SUCCESS_MESSAGE_TEXT;


@RequestMapping(value = "/dns", method = RequestMethod.POST)
public String updateDns(Integer id,String domainName,Integer domainType,String cnameDomain,String[] eipIds,Integer serviceTagId,String changeDescription,RedirectAttributes redirectAttributes){
    Resources resources = comm.resourcesService.getResources(id);
    comm.dnsService.saveResourcesByDns(resources, serviceTagId, domainName, domainType, cnameDomain, eipIds, changeDescription);
    redirectAttributes.addFlashAttribute("message", SUCCESS_MESSAGE_TEXT);
    return REDIRECT_SUCCESS_URL;
}


@RequestMapping(value = "/compute", method = RequestMethod.POST)
public String updateCompute(Integer id,Integer osType,Integer osBit,Integer serverType,String[] esgIds,String remark,String[] applicationNames,String[] applicationVersions,String[] applicationDeployPaths,Integer serviceTagId,String changeDescription,RedirectAttributes redirectAttributes){
    Resources resources = comm.resourcesService.getResources(id);
    comm.computeService.saveResourcesByCompute(resources, serviceTagId, osType, osBit, serverType, esgIds, remark, applicationNames, applicationVersions, applicationDeployPaths, changeDescription);
    redirectAttributes.addFlashAttribute("message", SUCCESS_MESSAGE_TEXT);
    return REDIRECT_SUCCESS_URL;
}


@RequestMapping(value = "/eip", method = RequestMethod.POST)
public String updateElb(Integer id,String linkType,String linkId,String[] protocols,String[] sourcePorts,String[] targetPorts,Integer serviceTagId,String changeDescription,RedirectAttributes redirectAttributes){
    Resources resources = comm.resourcesService.getResources(id);
    comm.eipService.saveResourcesByEip(resources, serviceTagId, linkType, linkId, protocols, sourcePorts, targetPorts, changeDescription);
    redirectAttributes.addFlashAttribute("message", SUCCESS_MESSAGE_TEXT);
    return REDIRECT_SUCCESS_URL;
}


@RequestMapping(value = "/monitorCompute", method = RequestMethod.POST)
public String updateMonitorCompute(Integer id,Integer serviceTagId,String changeDescription,String ipAddress,String cpuWarn,String cpuCritical,String memoryWarn,String memoryCritical,String pingLossWarn,String pingLossCritical,String diskWarn,String diskCritical,String pingDelayWarn,String pingDelayCritical,String maxProcessWarn,String maxProcessCritical,String port,String process,String mountPoint,RedirectAttributes redirectAttributes){
    Resources resources = comm.resourcesService.getResources(id);
    comm.monitorComputeServcie.saveResourcesByMonitorCompute(resources, serviceTagId, changeDescription, ipAddress, cpuWarn, cpuCritical, memoryWarn, memoryCritical, pingLossWarn, pingLossCritical, diskWarn, diskCritical, pingDelayWarn, pingDelayCritical, maxProcessWarn, maxProcessCritical, port, process, mountPoint);
    redirectAttributes.addFlashAttribute("message", SUCCESS_MESSAGE_TEXT);
    return REDIRECT_SUCCESS_URL;
}


@RequestMapping(value = "/{id}/vod/{vodId}", method = RequestMethod.GET)
public String updateMdnVodForm(Integer id,Integer vodId,Model model){
    model.addAttribute("mdnVod", comm.mdnService.getMdnVodItem(vodId));
    model.addAttribute("resources", comm.resourcesService.getResources(id));
    model.addAttribute("change", comm.changeServcie.findChangeBySubResourcesId(id, vodId));
    return "resource/form/mdnVod";
}


@RequestMapping(value = "/{id}/vod/{vodId}", method = RequestMethod.POST)
public String updateVod(Integer id,Integer vodId,String vodDomain,String vodProtocol,String sourceOutBandwidth,String sourceStreamerUrl,String changeDescription,RedirectAttributes redirectAttributes){
    Resources resources = comm.resourcesService.getResources(id);
    if (resources.getUsedby() == null) {
        // 指派给默认的MDN处理人
        resources.setUsedby(RedmineService.MDN_REDMINE_ASSIGNEE);
    }
    comm.mdnService.saveResourcesByMdnVod(resources, changeDescription, vodId, vodDomain, vodProtocol, sourceOutBandwidth, sourceStreamerUrl);
    redirectAttributes.addFlashAttribute("message", "变更MDN点播成功");
    return "redirect:/resources/update/" + id;
}


@RequestMapping(value = "/{id}/live/{liveId}", method = RequestMethod.GET)
public String updateMdnLiveForm(Integer id,Integer liveId,Model model){
    model.addAttribute("mdnLive", comm.mdnService.getMdnLiveItem(liveId));
    model.addAttribute("resources", comm.resourcesService.getResources(id));
    model.addAttribute("change", comm.changeServcie.findChangeBySubResourcesId(id, liveId));
    return "resource/form/mdnLive";
}


@RequestMapping(value = "/cp", method = RequestMethod.POST)
public String updateCP(Integer id,Integer serviceTagId,String changeDescription,String recordStreamUrl,String recordBitrate,String exportEncode,Integer recordType,String recordTime,Integer recordDuration,String publishUrl,String isPushCtp,String videoFtpIp,String videoFtpPort,String videoFtpUsername,String videoFtpPassword,String videoFtpRootpath,String videoFtpUploadpath,String videoOutputGroup,String videoOutputWay,String pictrueFtpIp,String pictrueFtpPort,String pictrueFtpUsername,String pictrueFtpPassword,String pictrueFtpRootpath,String pictrueFtpUploadpath,String pictrueOutputGroup,String pictrueOutputMedia,RedirectAttributes redirectAttributes){
    Resources resources = comm.resourcesService.getResources(id);
    comm.cpService.saveResourcesByCP(resources, serviceTagId, changeDescription, recordStreamUrl, recordBitrate, exportEncode, recordType, recordTime, recordDuration, publishUrl, isPushCtp, videoFtpIp, videoFtpPort, videoFtpUsername, videoFtpPassword, videoFtpRootpath, videoFtpUploadpath, videoOutputGroup, videoOutputWay, pictrueFtpIp, pictrueFtpPort, pictrueFtpUsername, pictrueFtpPassword, pictrueFtpRootpath, pictrueFtpUploadpath, pictrueOutputGroup, pictrueOutputMedia);
    redirectAttributes.addFlashAttribute("message", SUCCESS_MESSAGE_TEXT);
    return REDIRECT_SUCCESS_URL;
}


@RequestMapping(value = "/storage", method = RequestMethod.POST)
public String updateStorage(Integer id,Integer storageType,Integer space,String[] computeIds,Integer serviceTagId,String changeDescription,RedirectAttributes redirectAttributes){
    Resources resources = comm.resourcesService.getResources(id);
    comm.es3Service.saveResourcesByStorage(resources, serviceTagId, storageType, space, computeIds, changeDescription);
    redirectAttributes.addFlashAttribute("message", SUCCESS_MESSAGE_TEXT);
    return REDIRECT_SUCCESS_URL;
}


@RequestMapping(value = "/monitorElb", method = RequestMethod.POST)
public String updateMonitorElb(Integer id,Integer serviceTagId,Integer elbId,String changeDescription,RedirectAttributes redirectAttributes){
    Resources resources = comm.resourcesService.getResources(id);
    comm.monitorElbServcie.saveResourcesByMonitorElb(resources, serviceTagId, elbId, changeDescription);
    redirectAttributes.addFlashAttribute("message", SUCCESS_MESSAGE_TEXT);
    return REDIRECT_SUCCESS_URL;
}


@RequestMapping(value = "/{id}/live/{liveId}", method = RequestMethod.POST)
public String updateLive(Integer id,Integer liveId,String bandwidth,String name,String guid,String liveDomain,String liveProtocol,Integer streamOutMode,Integer encoderMode,String httpUrlEncoder,String httpBitrateEncoder,String hlsUrlEncoder,String hlsBitrateEncoder,String httpUrl,String httpBitrate,String hlsUrl,String hlsBitrate,String changeDescription,RedirectAttributes redirectAttributes){
    Resources resources = comm.resourcesService.getResources(id);
    if (resources.getUsedby() == null) {
        // 指派给默认的MDN处理人
        resources.setUsedby(RedmineService.MDN_REDMINE_ASSIGNEE);
    }
    comm.mdnService.saveResourcesByMdnLive(resources, changeDescription, liveId, bandwidth, name, guid, liveDomain, liveProtocol, streamOutMode, encoderMode, httpUrlEncoder, httpBitrateEncoder, hlsUrlEncoder, hlsBitrateEncoder, httpUrl, httpBitrate, hlsUrl, hlsBitrate);
    redirectAttributes.addFlashAttribute("message", "变更MDN直播成功");
    return "redirect:/resources/update/" + id;
}


@RequestMapping(value = "/mdn", method = RequestMethod.POST)
public String updateMdn(Integer id,Integer serviceTagId,String changeDescription,String coverArea,String coverIsp,String bandwidth,RedirectAttributes redirectAttributes){
    Resources resources = comm.resourcesService.getResources(id);
    comm.mdnService.saveResourcesByMdn(resources, serviceTagId, changeDescription, coverArea, coverIsp, bandwidth);
    redirectAttributes.addFlashAttribute("message", SUCCESS_MESSAGE_TEXT);
    return REDIRECT_SUCCESS_URL;
}


}
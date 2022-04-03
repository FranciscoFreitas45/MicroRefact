package com.sobey.cmop.mvc.web.apply.paas;
 import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.sobey.cmop.mvc.comm.BaseController;
import com.sobey.cmop.mvc.entity.Apply;
import com.sobey.cmop.mvc.entity.MdnItem;
import com.sobey.cmop.mvc.entity.MdnLiveItem;
import com.sobey.cmop.mvc.entity.MdnVodItem;
import com.sobey.cmop.mvc.DTO.Apply;
@Controller
@RequestMapping(value = "/apply/mdn")
public class MdnContoller extends BaseController{

 private  String REDIRECT_SUCCESS_URL;


@RequestMapping(value = "/mdnVod/update/{id}/applyId", method = RequestMethod.POST)
public String updateVod(Integer id,Integer applyId,String vodDomain,String vodProtocol,String sourceOutBandwidth,String sourceStreamerUrl,RedirectAttributes redirectAttributes){
    MdnVodItem mdnVodItem = comm.mdnService.getMdnVodItem(id);
    mdnVodItem.setSourceOutBandwidth(sourceOutBandwidth);
    mdnVodItem.setSourceStreamerUrl(sourceStreamerUrl);
    mdnVodItem.setVodDomain(vodDomain);
    mdnVodItem.setVodProtocol(vodProtocol);
    comm.mdnService.saveOrUpdate(mdnVodItem);
    redirectAttributes.addFlashAttribute("message", "修改MDN点播成功");
    return "redirect:/apply/update/" + applyId;
}


@RequestMapping(value = "/save", method = RequestMethod.GET)
public String createForm(Model model){
    return "apply/mdn/mdnForm";
}


@RequestMapping(value = "/mdnVod/update/{id}/applyId/{applyId}", method = RequestMethod.GET)
public String updateVodForm(Integer id,Integer applyId,Model model){
    model.addAttribute("mdnVod", comm.mdnService.getMdnVodItem(id));
    return "apply/mdn/mdnVodUpateForm";
}


@RequestMapping(value = "/save", method = RequestMethod.POST)
public String save(String serviceTag,Integer priority,String serviceStart,String serviceEnd,String description,String coverArea,String coverIsp,String bandwidth,String[] vodDomains,String[] vodProtocols,String[] sourceOutBandwidths,String[] sourceStreamerUrls,String[] liveDomains,String[] liveProtocols,String[] bandwidths,String[] channelNames,String[] channelGUIDs,String[] streamOutModes,String[] encoderModes,String[] httpUrls,String[] httpBitrates,String[] hlsUrls,String[] hlsBitrates,RedirectAttributes redirectAttributes){
    Apply apply = new Apply();
    apply.setServiceTag(serviceTag);
    apply.setPriority(priority);
    apply.setServiceStart(serviceStart);
    apply.setServiceEnd(serviceEnd);
    apply.setDescription(description);
    comm.mdnService.saveMdnToApply(apply, coverArea, coverIsp, bandwidth, vodDomains, vodProtocols, sourceOutBandwidths, sourceStreamerUrls, liveDomains, liveProtocols, bandwidths, channelNames, channelGUIDs, streamOutModes, encoderModes, httpUrls, httpBitrates, hlsUrls, hlsBitrates);
    redirectAttributes.addFlashAttribute("message", "创建MDN成功.");
    return REDIRECT_SUCCESS_URL;
}


@RequestMapping(value = "/update/{id}/applyId", method = RequestMethod.POST)
public String update(Integer id,Integer applyId,String coverArea,String coverIsp,String bandwidth,RedirectAttributes redirectAttributes){
    MdnItem mdnItem = comm.mdnService.getMdnItem(id);
    mdnItem.setCoverArea(coverArea);
    mdnItem.setCoverIsp(coverIsp);
    mdnItem.setBandwidth(bandwidth);
    comm.mdnService.saveOrUpdate(mdnItem);
    redirectAttributes.addFlashAttribute("message", "修改MDN " + mdnItem.getIdentifier() + " 成功");
    return "redirect:/apply/update/" + applyId;
}


@RequestMapping(value = "/update/{id}/applyId/{applyId}", method = RequestMethod.GET)
public String updateForm(Integer id,Integer applyId,Model model){
    model.addAttribute("mdn", comm.mdnService.getMdnItem(id));
    return "apply/mdn/mdnUpateForm";
}


@RequestMapping(value = "/mdnLive/update/{id}/applyId", method = RequestMethod.POST)
public String updateLive(Integer id,Integer applyId,String bandwidth,String name,String guid,String liveDomain,String liveProtocol,Integer streamOutMode,Integer encoderMode,String httpUrlEncoder,String httpBitrateEncoder,String hlsUrlEncoder,String hlsBitrateEncoder,String httpUrl,String httpBitrate,String hlsUrl,String hlsBitrate,RedirectAttributes redirectAttributes){
    MdnLiveItem mdnLiveItem = comm.mdnService.getMdnLiveItem(id);
    comm.mdnService.updateMdnLiveItemToApply(mdnLiveItem, bandwidth, name, guid, liveDomain, liveProtocol, streamOutMode, encoderMode, httpUrlEncoder, httpBitrateEncoder, hlsUrlEncoder, hlsBitrateEncoder, httpUrl, httpBitrate, hlsUrl, hlsBitrate);
    redirectAttributes.addFlashAttribute("message", "修改MDN直播成功");
    return "redirect:/apply/update/" + applyId;
}


@RequestMapping(value = "/mdnVod/delete/{id}/applyId/{applyId}")
public String deleteVod(Integer id,Integer applyId,RedirectAttributes redirectAttributes){
    comm.mdnService.deleteMdnVodItem(id);
    redirectAttributes.addFlashAttribute("message", "删除MDN点播成功");
    return "redirect:/apply/update/" + applyId;
}


@RequestMapping(value = "/mdnLive/update/{id}/applyId/{applyId}", method = RequestMethod.GET)
public String updateLiveForm(Integer id,Integer applyId,Model model){
    model.addAttribute("mdnLive", comm.mdnService.getMdnLiveItem(id));
    return "apply/mdn/mdnLiveUpateForm";
}


@RequestMapping(value = "/mdnLive/delete/{id}/applyId/{applyId}")
public String deleteLive(Integer id,Integer applyId,RedirectAttributes redirectAttributes){
    comm.mdnService.deleteMdnLiveItem(id);
    redirectAttributes.addFlashAttribute("message", "删除MDN直播成功");
    return "redirect:/apply/update/" + applyId;
}


}
package com.sobey.cmop.mvc.service.paas;
 import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sobey.cmop.mvc.comm.BaseSevcie;
import com.sobey.cmop.mvc.constant.ApplyConstant;
import com.sobey.cmop.mvc.constant.MdnConstant;
import com.sobey.cmop.mvc.constant.MdnConstant.EncoderMode;
import com.sobey.cmop.mvc.constant.NetworkConstant;
import com.sobey.cmop.mvc.constant.ResourcesConstant;
import com.sobey.cmop.mvc.dao.MdnItemDao;
import com.sobey.cmop.mvc.dao.MdnLiveItemDao;
import com.sobey.cmop.mvc.dao.MdnVodItemDao;
import com.sobey.cmop.mvc.entity.Apply;
import com.sobey.cmop.mvc.entity.Change;
import com.sobey.cmop.mvc.entity.MdnItem;
import com.sobey.cmop.mvc.entity.MdnLiveItem;
import com.sobey.cmop.mvc.entity.MdnVodItem;
import com.sobey.cmop.mvc.entity.Resources;
import com.sobey.cmop.mvc.entity.ServiceTag;
import com.sobey.cmop.mvc.DTO.ServiceTag;
@Service
@Transactional(readOnly = true)
public class MdnService extends BaseSevcie{

 private  Logger logger;

@Resource
 private  MdnItemDao mdnItemDao;

@Resource
 private  MdnLiveItemDao mdnLiveItemDao;

@Resource
 private  MdnVodItemDao mdnVodItemDao;


@Transactional(readOnly = false)
public void updateMdnLiveItemToApply(MdnLiveItem mdnLiveItem,String bandwidth,String name,String guid,String liveDomain,String liveProtocol,Integer streamOutMode,Integer encoderMode,String httpUrlEncoder,String httpBitrateEncoder,String hlsUrlEncoder,String hlsBitrateEncoder,String httpUrl,String httpBitrate,String hlsUrl,String hlsBitrate){
    mdnLiveItem.setBandwidth(bandwidth);
    mdnLiveItem.setName(name);
    mdnLiveItem.setGuid(guid);
    mdnLiveItem.setLiveDomain(liveDomain);
    mdnLiveItem.setLiveProtocol(liveProtocol);
    mdnLiveItem.setStreamOutMode(streamOutMode);
    if (MdnConstant.OutputMode.Encoder模式.toInteger().equals(streamOutMode)) {
        mdnLiveItem.setEncoderMode(encoderMode);
        if (MdnConstant.EncoderMode.拉流模式.toInteger().equals(encoderMode)) {
            mdnLiveItem.setHttpBitrate(StringUtils.defaultIfBlank(httpUrlEncoder, null));
            mdnLiveItem.setHttpUrl(StringUtils.defaultIfBlank(httpBitrateEncoder, null));
            mdnLiveItem.setHlsBitrate(null);
            mdnLiveItem.setHlsUrl(null);
        } else if (MdnConstant.EncoderMode.推流模式.toInteger().equals(encoderMode)) {
            mdnLiveItem.setHttpBitrate(null);
            mdnLiveItem.setHttpUrl(null);
            mdnLiveItem.setHlsBitrate(StringUtils.defaultIfBlank(hlsBitrateEncoder, null));
            mdnLiveItem.setHlsUrl(StringUtils.defaultIfBlank(hlsUrlEncoder, null));
        } else {
            mdnLiveItem.setHttpBitrate(null);
            mdnLiveItem.setHttpUrl(null);
            mdnLiveItem.setHlsBitrate(null);
            mdnLiveItem.setHlsUrl(null);
        }
    } else {
        mdnLiveItem.setEncoderMode(MdnConstant.EncoderMode.缺省模式.toInteger());
        mdnLiveItem.setHttpBitrate(httpBitrate);
        mdnLiveItem.setHttpUrl(httpUrl);
        mdnLiveItem.setHlsBitrate(hlsBitrate);
        mdnLiveItem.setHlsUrl(hlsUrl);
    }
    comm.mdnService.saveOrUpdate(mdnLiveItem);
}


@Transactional(readOnly = false)
public void saveResourcesByMdnVod(Resources resources,String changeDescription,Integer vodId,String vodDomain,String vodProtocol,String sourceOutBandwidth,String sourceStreamerUrl){
    /* 新增或更新资源Resources的服务变更Change. */
    Change change = comm.changeServcie.saveOrUpdateChangeByResources(resources, vodId, changeDescription);
    MdnVodItem mdnVodItem = this.getMdnVodItem(vodId);
    ServiceTag serviceTag = resources.getServiceTag();
    /* 比较资源变更前和变更后的值. */
    boolean isChange = comm.compareResourcesService.compareMdnVodItem(resources, change, mdnVodItem, vodDomain, vodProtocol, sourceOutBandwidth, sourceStreamerUrl);
    // 当资源有更改的时候,更改状态.如果和资源不相关的如:服务标签,指派人等变更,则不变更资源的状态.
    if (isChange) {
        serviceTag.setStatus(ResourcesConstant.Status.已变更.toInteger());
        resources.setStatus(ResourcesConstant.Status.已变更.toInteger());
    }
    resources.setServiceTag(serviceTag);
    comm.serviceTagService.saveOrUpdate(serviceTag);
    mdnVodItem.setSourceOutBandwidth(sourceOutBandwidth);
    mdnVodItem.setSourceStreamerUrl(sourceStreamerUrl);
    mdnVodItem.setVodDomain(vodDomain);
    mdnVodItem.setVodProtocol(vodProtocol);
    this.saveOrUpdate(mdnVodItem);
    // 更新resources
    comm.resourcesService.saveOrUpdate(resources);
}


public MdnLiveItem getMdnLiveItem(Integer id){
    if (id != null) {
        return mdnLiveItemDao.findOne(id);
    }
    return null;
}


public List<MdnLiveItem> getMdnLiveItemByMdnId(Integer mdnId){
    return mdnLiveItemDao.findByMdnItemId(mdnId);
}


@Transactional(readOnly = false)
public void saveResourcesByMdnLive(Resources resources,String changeDescription,Integer liveId,String bandwidth,String name,String guid,String liveDomain,String liveProtocol,Integer streamOutMode,Integer encoderMode,String httpUrlEncoder,String httpBitrateEncoder,String hlsUrlEncoder,String hlsBitrateEncoder,String httpUrl,String httpBitrate,String hlsUrl,String hlsBitrate){
    /* 新增或更新资源Resources的服务变更Change. */
    Change change = comm.changeServcie.saveOrUpdateChangeByResources(resources, liveId, changeDescription);
    MdnLiveItem mdnLiveItem = this.getMdnLiveItem(liveId);
    ServiceTag serviceTag = resources.getServiceTag();
    /* 比较资源变更前和变更后的值. */
    boolean isChange = comm.compareResourcesService.compareMdnLiveItem(resources, change, mdnLiveItem, bandwidth, name, guid, liveDomain, liveProtocol, streamOutMode, encoderMode, httpUrlEncoder, httpBitrateEncoder, hlsUrlEncoder, hlsBitrateEncoder, httpUrl, httpBitrate, hlsUrl, hlsBitrate);
    // 当资源有更改的时候,更改状态.如果和资源不相关的如:服务标签,指派人等变更,则不变更资源的状态.
    if (isChange) {
        serviceTag.setStatus(ResourcesConstant.Status.已变更.toInteger());
        resources.setStatus(ResourcesConstant.Status.已变更.toInteger());
    }
    resources.setServiceTag(serviceTag);
    comm.serviceTagService.saveOrUpdate(serviceTag);
    this.updateMdnLiveItemToApply(mdnLiveItem, bandwidth, name, guid, liveDomain, liveProtocol, streamOutMode, encoderMode, httpUrlEncoder, httpBitrateEncoder, hlsUrlEncoder, hlsBitrateEncoder, httpUrl, httpBitrate, hlsUrl, hlsBitrate);
    // 更新resources
    comm.resourcesService.saveOrUpdate(resources);
}


@Transactional(readOnly = false)
public void saveResourcesByMdn(Resources resources,Integer serviceTagId,String changeDescription,String coverArea,String coverIsp,String bandwidth){
    /* 新增或更新资源Resources的服务变更Change. */
    Change change = comm.changeServcie.saveOrUpdateChangeByResources(resources, changeDescription);
    MdnItem mdnItem = this.getMdnItem(resources.getServiceId());
    /* 比较资源变更前和变更后的值. */
    boolean isChange = comm.compareResourcesService.compareMdnItem(resources, change, mdnItem, coverArea, coverIsp, bandwidth);
    ServiceTag serviceTag = comm.serviceTagService.getServiceTag(serviceTagId);
    // 当资源有更改的时候,更改状态.如果和资源不相关的如:服务标签,指派人等变更,则不变更资源的状态.
    if (isChange) {
        serviceTag.setStatus(ResourcesConstant.Status.已变更.toInteger());
        resources.setStatus(ResourcesConstant.Status.已变更.toInteger());
    }
    resources.setServiceTag(serviceTag);
    comm.serviceTagService.saveOrUpdate(serviceTag);
    mdnItem.setCoverArea(coverArea);
    mdnItem.setCoverIsp(coverIsp);
    mdnItem.setBandwidth(bandwidth);
    this.saveOrUpdate(mdnItem);
    // 更新resources
    comm.resourcesService.saveOrUpdate(resources);
}


@Transactional(readOnly = false)
public void saveMdnToApply(Apply apply,String coverArea,String coverIsp,String bandwidth,String[] vodDomains,String[] vodProtocols,String[] sourceOutBandwidths,String[] sourceStreamerUrls,String[] liveDomains,String[] liveProtocols,String[] bandwidths,String[] channelNames,String[] channelGUIDs,String[] streamOutModes,String[] encoderModes,String[] httpUrls,String[] httpBitrates,String[] hlsUrls,String[] hlsBitrates){
    // Step1. 创建一个服务申请Apply
    comm.applyService.saveApplyByServiceType(apply, ApplyConstant.ServiceType.MDN.toInteger());
    // Step2. 创建一个MDN.
    MdnItem mdnItem = new MdnItem();
    mdnItem.setApply(apply);
    mdnItem.setCoverArea(coverArea);
    mdnItem.setCoverIsp(coverIsp);
    mdnItem.setBandwidth(bandwidth);
    mdnItem.setIdentifier(comm.applyService.generateIdentifier(ResourcesConstant.ServiceType.MDN.toInteger()));
    this.saveOrUpdate(mdnItem);
    // Step2. 创建vod MDN.
    this.saveMdnVodItem(mdnItem, vodDomains, vodProtocols, sourceOutBandwidths, sourceStreamerUrls);
    // Step3. 创建live MDN.
    this.saveMdnLiveItem(mdnItem, liveDomains, liveProtocols, bandwidths, channelNames, channelGUIDs, streamOutModes, encoderModes, httpUrls, httpBitrates, hlsUrls, hlsBitrates);
}


public String wrapStringByMDNCoverIsp(String coverIsp){
    String isp = "";
    String[] coverIsps = StringUtils.split(coverIsp, ",");
    for (String ispKey : coverIsps) {
        isp += NetworkConstant.ISPType.get(Integer.valueOf(ispKey)) + ",";
    }
    return isp.substring(0, isp.length() - 1);
}


@Transactional(readOnly = false)
public void deleteMdnLiveItem(Integer id){
    mdnLiveItemDao.delete(id);
}


@Transactional(readOnly = false)
public void deleteMdnVodItem(Integer id){
    mdnVodItemDao.delete(id);
}


public MdnVodItem getMdnVodItem(Integer id){
    if (id != null) {
        return mdnVodItemDao.findOne(id);
    }
    return null;
}


@Transactional(readOnly = false)
public void saveMdnLiveItem(MdnItem mdnItem,String[] liveDomains,String[] liveProtocols,String[] bandwidths,String[] channelNames,String[] channelGUIDs,String[] streamOutModes,String[] encoderModes,String[] httpUrls,String[] httpBitrates,String[] hlsUrls,String[] hlsBitrates){
    if (liveDomains != null) {
        for (int i = 0; i < liveDomains.length; i++) {
            MdnLiveItem mdnLiveItem = new MdnLiveItem();
            Integer streamOutMode = Integer.valueOf(streamOutModes[i]);
            Integer encoderMode = Integer.valueOf(encoderModes[i]);
            mdnLiveItem.setMdnItem(mdnItem);
            mdnLiveItem.setLiveDomain(liveDomains[i]);
            mdnLiveItem.setLiveProtocol(StringUtils.replace(liveProtocols[i], "-", ","));
            mdnLiveItem.setStreamOutMode(streamOutMode);
            mdnLiveItem.setEncoderMode(encoderMode);
            mdnLiveItem.setName(channelNames[i]);
            mdnLiveItem.setGuid(channelGUIDs[i]);
            mdnLiveItem.setBandwidth(bandwidths[i]);
            if (MdnConstant.OutputMode.Encoder模式.toInteger().equals(streamOutMode)) {
                if (MdnConstant.EncoderMode.拉流模式.toInteger().equals(encoderMode)) {
                    if (httpBitrates.length != 0) {
                        mdnLiveItem.setHttpBitrate(StringUtils.defaultIfBlank(httpBitrates[i], null));
                    }
                    if (httpUrls.length != 0) {
                        mdnLiveItem.setHttpUrl(StringUtils.defaultIfBlank(httpUrls[i], null));
                    }
                } else if (MdnConstant.EncoderMode.推流模式.toInteger().equals(encoderMode)) {
                    if (hlsBitrates.length != 0) {
                        mdnLiveItem.setHlsBitrate(StringUtils.defaultIfBlank(hlsBitrates[i], null));
                    }
                    if (hlsUrls.length != 0) {
                        mdnLiveItem.setHlsUrl(StringUtils.defaultIfBlank(hlsUrls[i], null));
                    }
                }
            } else {
                mdnLiveItem.setEncoderMode(EncoderMode.缺省模式.toInteger());
                mdnLiveItem.setHttpBitrate(httpBitrates[i]);
                mdnLiveItem.setHttpUrl(httpUrls[i]);
                mdnLiveItem.setHlsBitrate(hlsBitrates[i]);
                mdnLiveItem.setHlsUrl(hlsUrls[i]);
            }
            this.saveOrUpdate(mdnLiveItem);
        }
    }
}


@Transactional(readOnly = false)
public void deleteMdnItem(Integer id){
    mdnItemDao.delete(id);
}


@Transactional(readOnly = false)
public void saveMdnVodItem(MdnItem mdnItem,String[] vodDomains,String[] vodProtocols,String[] sourceOutBandwidths,String[] sourceStreamerUrls){
    if (vodDomains != null) {
        for (int i = 0; i < vodDomains.length; i++) {
            MdnVodItem mdnVodItem = new MdnVodItem();
            mdnVodItem.setMdnItem(mdnItem);
            mdnVodItem.setSourceOutBandwidth(sourceOutBandwidths[i]);
            mdnVodItem.setSourceStreamerUrl(sourceStreamerUrls[i]);
            mdnVodItem.setVodDomain(vodDomains[i]);
            mdnVodItem.setVodProtocol(StringUtils.replace(vodProtocols[i], "-", ","));
            this.saveOrUpdate(mdnVodItem);
        }
    }
}


public MdnItem getMdnItem(Integer id){
    return mdnItemDao.findOne(id);
}


@Transactional(readOnly = false)
public MdnLiveItem saveOrUpdate(MdnLiveItem mdnLiveItem){
    return mdnLiveItemDao.save(mdnLiveItem);
}


public List<MdnVodItem> getMdnVodItemByMdnId(Integer mdnId){
    return mdnVodItemDao.findByMdnItemId(mdnId);
}


}
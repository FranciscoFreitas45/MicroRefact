package com.sobey.cmop.mvc.service.iaas.imp;
 import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sobey.cmop.mvc.comm.BaseSevcie;
import com.sobey.cmop.mvc.constant.CPConstant;
import com.sobey.cmop.mvc.constant.ComputeConstant;
import com.sobey.cmop.mvc.constant.FieldNameConstant;
import com.sobey.cmop.mvc.constant.MdnConstant;
import com.sobey.cmop.mvc.constant.MonitorConstant;
import com.sobey.cmop.mvc.constant.NetworkConstant;
import com.sobey.cmop.mvc.constant.ResourcesConstant;
import com.sobey.cmop.mvc.constant.StorageConstant;
import com.sobey.cmop.mvc.entity.Application;
import com.sobey.cmop.mvc.entity.Change;
import com.sobey.cmop.mvc.entity.ChangeItem;
import com.sobey.cmop.mvc.entity.ComputeItem;
import com.sobey.cmop.mvc.entity.CpItem;
import com.sobey.cmop.mvc.entity.EipPortItem;
import com.sobey.cmop.mvc.entity.ElbPortItem;
import com.sobey.cmop.mvc.entity.MdnItem;
import com.sobey.cmop.mvc.entity.MdnLiveItem;
import com.sobey.cmop.mvc.entity.MdnVodItem;
import com.sobey.cmop.mvc.entity.MonitorCompute;
import com.sobey.cmop.mvc.entity.MonitorElb;
import com.sobey.cmop.mvc.entity.NetworkDnsItem;
import com.sobey.cmop.mvc.entity.NetworkEipItem;
import com.sobey.cmop.mvc.entity.NetworkElbItem;
import com.sobey.cmop.mvc.entity.NetworkEsgItem;
import com.sobey.cmop.mvc.entity.Resources;
import com.sobey.cmop.mvc.entity.StorageItem;
import com.sobey.cmop.mvc.service.iaas.ICompareResourcesService;
import com.sobey.framework.utils.Collections3;
import com.sobey.cmop.mvc.DTO.EipPortItem;
import com.sobey.cmop.mvc.DTO.StorageItem;
import com.sobey.cmop.mvc.DTO.NetworkDnsItem;
import com.sobey.cmop.mvc.DTO.NetworkElbItem;
import com.sobey.cmop.mvc.DTO.ElbPortItem;
import com.sobey.cmop.mvc.DTO.NetworkEipItem;
import com.sobey.cmop.mvc.DTO.ComputeItem;
import com.sobey.cmop.mvc.DTO.Change;
import com.sobey.cmop.mvc.DTO.Resources;
import com.sobey.cmop.mvc.DTO.ChangeItem;
import com.sobey.cmop.mvc.DTO.MonitorElb;
import com.sobey.cmop.mvc.DTO.NetworkEsgItem;
import com.sobey.cmop.mvc.DTO.MonitorCompute;
import com.sobey.cmop.mvc.DTO.Application;
import com.sobey.cmop.mvc.DTO.CpItem;
@Service
@Transactional
public class CompareResourcesServiceImp extends BaseSevcieimplements ICompareResourcesService{

 private  Logger logger;

 private  String UN_SELECTED_STRING;


public boolean compareEipPortItem(List<EipPortItem> eipPortItems,String[] protocols,String[] sourcePorts,String[] targetPorts){
    // === OldValue === //
    List<String> oldProtocolList = new ArrayList<String>();
    List<String> oldSourcePortList = new ArrayList<String>();
    List<String> oldTargetPortList = new ArrayList<String>();
    for (EipPortItem eipPortItem : eipPortItems) {
        oldProtocolList.add(eipPortItem.getProtocol());
        oldSourcePortList.add(eipPortItem.getSourcePort());
        oldTargetPortList.add(eipPortItem.getTargetPort());
    }
    // === NewValue === //
    List<String> protocolList = new ArrayList<String>();
    List<String> sourcePortList = new ArrayList<String>();
    List<String> targetPortList = new ArrayList<String>();
    if (sourcePorts != null && sourcePorts.length > 0) {
        for (int i = 0; i < protocols.length; i++) {
            protocolList.add(protocols[i]);
            sourcePortList.add(sourcePorts[i]);
            targetPortList.add(targetPorts[i]);
        }
    }
    // ??????OldValue???NewValue???List.
    return CollectionUtils.isEqualCollection(protocolList, oldProtocolList) && CollectionUtils.isEqualCollection(sourcePortList, oldSourcePortList) && CollectionUtils.isEqualCollection(targetPortList, oldTargetPortList) ? false : true;
}


@Override
public boolean compareStorage(Resources resources,Change change,StorageItem storageItem,Integer storageType,Integer space,String[] computeIds){
    /**
     * ???????????????????????????????????????????????? compareCompute??????
     */
    // Step.1????????????boolean?????????flag.????????????????????????????????????.
    boolean flag = false;
    boolean isChange = false;
    // ????????????????????????computeId?????????","????????????,????????????????????????????????????.
    String oldId = Collections3.extractToString(storageItem.getComputeItemList(), "id", ",");
    String newId = computeIds != null ? StringUtils.join(computeIds, ",") : "";
    if (!oldId.equals(newId)) {
        String fieldName = FieldNameConstant.Storage.????????????.toString();
        String oldValue = oldId;
        String oldString = storageItem.getMountComputes();
        // ??????computeIds??????compute???List,??????????????????.
        List<ComputeItem> list = new ArrayList<ComputeItem>();
        if (computeIds != null) {
            for (int i = 0; i < computeIds.length; i++) {
                ComputeItem computeItem = comm.computeService.getComputeItem(Integer.valueOf(computeIds[i]));
                list.add(computeItem);
            }
        }
        String newValue = newId;
        String newString = StorageItem.extractToString(list);
        isChange = this.saveChangeItemByFieldName(resources, change, fieldName, oldValue, oldString, newValue, newString);
        // Step.1 ???????????????,?????????true.
        flag = isChange;
    }
    // ????????????
    if (!storageItem.getStorageType().equals(storageType)) {
        String fieldName = FieldNameConstant.Storage.????????????.toString();
        String oldValue = storageItem.getStorageType().toString();
        String oldString = StorageConstant.StorageType.get(storageItem.getStorageType());
        String newValue = storageType.toString();
        String newString = StorageConstant.StorageType.get(storageType);
        isChange = this.saveChangeItemByFieldName(resources, change, fieldName, oldValue, oldString, newValue, newString);
    }
    // ????????????
    if (!storageItem.getSpace().equals(space)) {
        String fieldName = FieldNameConstant.Storage.????????????.toString();
        String oldValue = storageItem.getSpace().toString();
        String oldString = storageItem.getSpace().toString();
        String newValue = space.toString();
        String newString = space.toString();
        isChange = this.saveChangeItemByFieldName(resources, change, fieldName, oldValue, oldString, newValue, newString);
    }
    // Step.2 ????????????????????????,???????????????????????????,????????????????????????????????????????????????,????????????????????????????????????.
    if (isChange && !flag) {
        String fieldName = FieldNameConstant.Storage.????????????.toString();
        String oldValue = oldId;
        String oldString = storageItem.getMountComputes();
        // ??????computeIds??????compute???List,??????????????????.
        List<ComputeItem> list = new ArrayList<ComputeItem>();
        if (computeIds != null) {
            for (int i = 0; i < computeIds.length; i++) {
                ComputeItem computeItem = comm.computeService.getComputeItem(Integer.valueOf(computeIds[i]));
                list.add(computeItem);
            }
        }
        String newValue = newId;
        String newString = StorageItem.extractToString(list);
        this.saveChangeItemByFieldName(resources, change, fieldName, oldValue, oldString, newValue, newString);
    }
    return isChange;
}


public String wrapApplicationToString(String[] applicationNames,String[] applicationVersions,String[] applicationDeployPaths){
    StringBuilder sb = new StringBuilder();
    if (applicationNames != null) {
        for (int i = 0; i < applicationNames.length; i++) {
            sb.append(applicationNames[i]).append(",").append(applicationVersions[i]).append(",").append(applicationDeployPaths[i]).append("<br>");
        }
    }
    return sb.toString();
}


public String wrapPortItemToString(String[] protocols,String[] sourcePorts,String[] targetPorts){
    StringBuilder sb = new StringBuilder();
    if (sourcePorts != null && sourcePorts.length > 0) {
        for (int i = 0; i < protocols.length; i++) {
            sb.append(protocols[i]).append(",").append(sourcePorts[i]).append(",").append(targetPorts[i]).append("<br>");
        }
    }
    return sb.toString();
}


@Override
public boolean compareMdnLiveItem(Resources resources,Change change,MdnLiveItem mdnLiveItem,String bandwidth,String name,String guid,String liveDomain,String liveProtocol,Integer streamOutMode,Integer encoderMode,String httpUrlEncoder,String httpBitrateEncoder,String hlsUrlEncoder,String hlsBitrateEncoder,String httpUrl,String httpBitrate,String hlsUrl,String hlsBitrate){
    boolean isChange = false;
    if (!mdnLiveItem.getLiveDomain().equals(liveDomain)) {
        String fieldName = FieldNameConstant.MdnLiveItem.??????????????????.toString();
        String oldValue = mdnLiveItem.getLiveDomain();
        String oldString = mdnLiveItem.getLiveDomain();
        String newValue = liveDomain;
        String newString = liveDomain;
        isChange = this.saveChangeItemByFieldName(resources, change, mdnLiveItem.getId(), fieldName, oldValue, oldString, newValue, newString);
    }
    if (!mdnLiveItem.getLiveProtocol().equals(liveProtocol)) {
        String fieldName = FieldNameConstant.MdnLiveItem.????????????????????????.toString();
        String oldValue = mdnLiveItem.getLiveProtocol();
        String oldString = mdnLiveItem.getLiveProtocol();
        String newValue = liveProtocol;
        String newString = liveProtocol;
        isChange = this.saveChangeItemByFieldName(resources, change, mdnLiveItem.getId(), fieldName, oldValue, oldString, newValue, newString);
    }
    if (!mdnLiveItem.getBandwidth().equals(bandwidth)) {
        String fieldName = FieldNameConstant.MdnLiveItem.????????????????????????.toString();
        String oldValue = mdnLiveItem.getBandwidth();
        String oldString = mdnLiveItem.getBandwidth();
        String newValue = bandwidth;
        String newString = bandwidth;
        isChange = this.saveChangeItemByFieldName(resources, change, mdnLiveItem.getId(), fieldName, oldValue, oldString, newValue, newString);
    }
    if (!mdnLiveItem.getName().equals(name)) {
        String fieldName = FieldNameConstant.MdnLiveItem.????????????.toString();
        String oldValue = mdnLiveItem.getName();
        String oldString = mdnLiveItem.getName();
        String newValue = name;
        String newString = name;
        isChange = this.saveChangeItemByFieldName(resources, change, mdnLiveItem.getId(), fieldName, oldValue, oldString, newValue, newString);
    }
    if (!mdnLiveItem.getGuid().equals(guid)) {
        String fieldName = FieldNameConstant.MdnLiveItem.????????????.toString();
        String oldValue = mdnLiveItem.getGuid();
        String oldString = mdnLiveItem.getGuid();
        String newValue = guid;
        String newString = guid;
        isChange = this.saveChangeItemByFieldName(resources, change, mdnLiveItem.getId(), fieldName, oldValue, oldString, newValue, newString);
    }
    // ???????????????????????????
    if (!mdnLiveItem.getStreamOutMode().equals(streamOutMode)) {
        String fieldName = FieldNameConstant.MdnLiveItem.?????????????????????.toString();
        String oldValue = mdnLiveItem.getStreamOutMode().toString();
        String oldString = MdnConstant.OutputMode.get(mdnLiveItem.getStreamOutMode());
        String newValue = streamOutMode.toString();
        String newString = MdnConstant.OutputMode.get(streamOutMode);
        isChange = this.saveChangeItemByFieldName(resources, change, mdnLiveItem.getId(), fieldName, oldValue, oldString, newValue, newString);
        if (MdnConstant.OutputMode.Transfer??????.toInteger().equals(streamOutMode)) {
            /* ???Encoder????????????Transfer???????????? */
            isChange = this.saveChangeItemByFieldName(resources, change, mdnLiveItem.getId(), FieldNameConstant.MdnLiveItem.???????????????.toString(), mdnLiveItem.getEncoderMode().toString(), MdnConstant.EncoderMode.get(mdnLiveItem.getEncoderMode()), encoderMode.toString(), MdnConstant.EncoderMode.get(encoderMode));
            isChange = this.saveChangeItemByFieldName(resources, change, mdnLiveItem.getId(), FieldNameConstant.MdnLiveItem.HTTP?????????.toString(), "", "", httpUrl, httpUrl);
            isChange = this.saveChangeItemByFieldName(resources, change, mdnLiveItem.getId(), FieldNameConstant.MdnLiveItem.HTTP???????????????.toString(), "", "", httpBitrate, httpBitrate);
            isChange = this.saveChangeItemByFieldName(resources, change, mdnLiveItem.getId(), FieldNameConstant.MdnLiveItem.HSL?????????.toString(), "", "", hlsUrl, hlsUrl);
            isChange = this.saveChangeItemByFieldName(resources, change, mdnLiveItem.getId(), FieldNameConstant.MdnLiveItem.HSL???????????????.toString(), "", "", hlsBitrate, hlsBitrate);
            if (MdnConstant.EncoderMode.????????????.toInteger().equals(mdnLiveItem.getEncoderMode())) {
                if (!httpUrlEncoder.equals(mdnLiveItem.getHttpUrl())) {
                    isChange = this.saveChangeItemByFieldName(resources, change, mdnLiveItem.getId(), FieldNameConstant.MdnLiveItem.????????????.toString(), mdnLiveItem.getHttpUrl(), mdnLiveItem.getHttpUrl(), "", "");
                }
                if (!httpBitrateEncoder.equals(mdnLiveItem.getHttpBitrate())) {
                    isChange = this.saveChangeItemByFieldName(resources, change, mdnLiveItem.getId(), FieldNameConstant.MdnLiveItem.??????????????????.toString(), mdnLiveItem.getHttpBitrate(), mdnLiveItem.getHttpBitrate(), "", "");
                }
            } else if (MdnConstant.EncoderMode.????????????.toInteger().equals(mdnLiveItem.getEncoderMode())) {
                if (!hlsUrlEncoder.equals(mdnLiveItem.getHlsUrl())) {
                    isChange = this.saveChangeItemByFieldName(resources, change, mdnLiveItem.getId(), FieldNameConstant.MdnLiveItem.????????????.toString(), mdnLiveItem.getHlsUrl(), mdnLiveItem.getHlsUrl(), "", "");
                }
                if (!hlsBitrateEncoder.equals(mdnLiveItem.getHlsBitrate())) {
                    isChange = this.saveChangeItemByFieldName(resources, change, mdnLiveItem.getId(), FieldNameConstant.MdnLiveItem.??????????????????.toString(), mdnLiveItem.getHlsBitrate(), mdnLiveItem.getHlsBitrate(), "", "");
                }
            }
        } else {
            /* ???Transfer??????????????????Encoder?????? */
            if (MdnConstant.EncoderMode.????????????.toInteger().equals(encoderMode)) {
                isChange = this.saveChangeItemByFieldName(resources, change, mdnLiveItem.getId(), FieldNameConstant.MdnLiveItem.????????????.toString(), "", "", httpUrlEncoder, httpUrlEncoder);
                isChange = this.saveChangeItemByFieldName(resources, change, mdnLiveItem.getId(), FieldNameConstant.MdnLiveItem.??????????????????.toString(), "", "", httpBitrateEncoder, httpBitrateEncoder);
            } else if (MdnConstant.EncoderMode.????????????.toInteger().equals(encoderMode)) {
                isChange = this.saveChangeItemByFieldName(resources, change, mdnLiveItem.getId(), FieldNameConstant.MdnLiveItem.????????????.toString(), "", "", hlsUrlEncoder, hlsUrlEncoder);
                isChange = this.saveChangeItemByFieldName(resources, change, mdnLiveItem.getId(), FieldNameConstant.MdnLiveItem.??????????????????.toString(), "", "", hlsBitrateEncoder, hlsBitrateEncoder);
            }
            isChange = this.saveChangeItemByFieldName(resources, change, mdnLiveItem.getId(), FieldNameConstant.MdnLiveItem.HTTP?????????.toString(), mdnLiveItem.getHttpUrl(), mdnLiveItem.getHttpUrl(), "", "");
            isChange = this.saveChangeItemByFieldName(resources, change, mdnLiveItem.getId(), FieldNameConstant.MdnLiveItem.HTTP???????????????.toString(), mdnLiveItem.getHttpBitrate(), mdnLiveItem.getHttpBitrate(), "", "");
            isChange = this.saveChangeItemByFieldName(resources, change, mdnLiveItem.getId(), FieldNameConstant.MdnLiveItem.HSL?????????.toString(), mdnLiveItem.getHlsUrl(), mdnLiveItem.getHlsUrl(), "", "");
            isChange = this.saveChangeItemByFieldName(resources, change, mdnLiveItem.getId(), FieldNameConstant.MdnLiveItem.HSL???????????????.toString(), mdnLiveItem.getHlsBitrate(), mdnLiveItem.getHlsBitrate(), "", "");
        }
    } else {
        // ??????????????????????????????
        if (MdnConstant.OutputMode.Encoder??????.toInteger().equals(mdnLiveItem.getStreamOutMode())) {
            /* ????????????live??????????????????????????????Encoder?????? */
            if (!mdnLiveItem.getEncoderMode().equals(encoderMode)) {
                /* ?????????????????? */
                String fieldName = FieldNameConstant.MdnLiveItem.???????????????.toString();
                String oldValue = mdnLiveItem.getEncoderMode().toString();
                String oldString = MdnConstant.EncoderMode.get(mdnLiveItem.getEncoderMode());
                String newValue = encoderMode.toString();
                String newString = MdnConstant.EncoderMode.get(encoderMode);
                isChange = this.saveChangeItemByFieldName(resources, change, mdnLiveItem.getId(), fieldName, oldValue, oldString, newValue, newString);
                if (MdnConstant.EncoderMode.????????????.toInteger().equals(mdnLiveItem.getEncoderMode()) && MdnConstant.EncoderMode.????????????.toInteger().equals(encoderMode)) {
                    // ???????????? -> ????????????
                    if (!httpUrlEncoder.equals(mdnLiveItem.getHttpUrl())) {
                        isChange = this.saveChangeItemByFieldName(resources, change, mdnLiveItem.getId(), FieldNameConstant.MdnLiveItem.????????????.toString(), mdnLiveItem.getHttpUrl(), mdnLiveItem.getHttpUrl(), "", "");
                    }
                    if (!httpBitrateEncoder.equals(mdnLiveItem.getHttpBitrate())) {
                        isChange = this.saveChangeItemByFieldName(resources, change, mdnLiveItem.getId(), FieldNameConstant.MdnLiveItem.??????????????????.toString(), mdnLiveItem.getHttpBitrate(), mdnLiveItem.getHttpBitrate(), "", "");
                    }
                } else if (MdnConstant.EncoderMode.????????????.toInteger().equals(mdnLiveItem.getEncoderMode()) && MdnConstant.EncoderMode.????????????.toInteger().equals(encoderMode)) {
                    // ???????????? -> ????????????
                    if (!hlsUrlEncoder.equals(mdnLiveItem.getHlsUrl())) {
                        isChange = this.saveChangeItemByFieldName(resources, change, mdnLiveItem.getId(), FieldNameConstant.MdnLiveItem.????????????.toString(), mdnLiveItem.getHlsUrl(), mdnLiveItem.getHlsUrl(), "", "");
                    }
                    if (!hlsBitrateEncoder.equals(mdnLiveItem.getHlsBitrate())) {
                        isChange = this.saveChangeItemByFieldName(resources, change, mdnLiveItem.getId(), FieldNameConstant.MdnLiveItem.??????????????????.toString(), mdnLiveItem.getHlsBitrate(), mdnLiveItem.getHlsBitrate(), "", "");
                    }
                } else if (MdnConstant.EncoderMode.????????????.toInteger().equals(mdnLiveItem.getEncoderMode()) && MdnConstant.EncoderMode.????????????.toInteger().equals(encoderMode)) {
                    // ???????????? -> ????????????
                    if (!hlsUrlEncoder.equals(mdnLiveItem.getHlsUrl())) {
                        isChange = this.saveChangeItemByFieldName(resources, change, mdnLiveItem.getId(), FieldNameConstant.MdnLiveItem.????????????.toString(), mdnLiveItem.getHlsUrl(), mdnLiveItem.getHlsUrl(), "", "");
                    }
                    if (!hlsBitrateEncoder.equals(mdnLiveItem.getHlsBitrate())) {
                        isChange = this.saveChangeItemByFieldName(resources, change, mdnLiveItem.getId(), FieldNameConstant.MdnLiveItem.??????????????????.toString(), mdnLiveItem.getHlsBitrate(), mdnLiveItem.getHlsBitrate(), "", "");
                    }
                    if (!httpUrlEncoder.equals(mdnLiveItem.getHttpUrl())) {
                        isChange = this.saveChangeItemByFieldName(resources, change, mdnLiveItem.getId(), FieldNameConstant.MdnLiveItem.????????????.toString(), "", "", httpUrlEncoder, httpUrlEncoder);
                    }
                    if (!httpBitrateEncoder.equals(mdnLiveItem.getHttpBitrate())) {
                        isChange = this.saveChangeItemByFieldName(resources, change, mdnLiveItem.getId(), FieldNameConstant.MdnLiveItem.??????????????????.toString(), "", "", httpBitrateEncoder, httpBitrateEncoder);
                    }
                } else if (MdnConstant.EncoderMode.????????????.toInteger().equals(mdnLiveItem.getEncoderMode()) && MdnConstant.EncoderMode.????????????.toInteger().equals(encoderMode)) {
                    // ???????????? -> ????????????
                    if (!httpUrlEncoder.equals(mdnLiveItem.getHttpUrl())) {
                        isChange = this.saveChangeItemByFieldName(resources, change, mdnLiveItem.getId(), FieldNameConstant.MdnLiveItem.????????????.toString(), mdnLiveItem.getHttpUrl(), mdnLiveItem.getHttpUrl(), "", "");
                    }
                    if (!httpBitrateEncoder.equals(mdnLiveItem.getHttpBitrate())) {
                        isChange = this.saveChangeItemByFieldName(resources, change, mdnLiveItem.getId(), FieldNameConstant.MdnLiveItem.??????????????????.toString(), mdnLiveItem.getHttpBitrate(), mdnLiveItem.getHttpBitrate(), "", "");
                    }
                    if (!hlsUrlEncoder.equals(mdnLiveItem.getHlsUrl())) {
                        isChange = this.saveChangeItemByFieldName(resources, change, mdnLiveItem.getId(), FieldNameConstant.MdnLiveItem.????????????.toString(), "", "", hlsUrlEncoder, hlsUrlEncoder);
                    }
                    if (!hlsBitrateEncoder.equals(mdnLiveItem.getHlsBitrate())) {
                        isChange = this.saveChangeItemByFieldName(resources, change, mdnLiveItem.getId(), FieldNameConstant.MdnLiveItem.??????????????????.toString(), "", "", hlsBitrateEncoder, hlsBitrateEncoder);
                    }
                } else if (MdnConstant.EncoderMode.????????????.toInteger().equals(mdnLiveItem.getEncoderMode()) && MdnConstant.EncoderMode.????????????.toInteger().equals(encoderMode)) {
                    // ???????????? -> ????????????
                    if (!hlsUrlEncoder.equals(mdnLiveItem.getHlsUrl())) {
                        isChange = this.saveChangeItemByFieldName(resources, change, mdnLiveItem.getId(), FieldNameConstant.MdnLiveItem.????????????.toString(), "", "", hlsUrlEncoder, hlsUrlEncoder);
                    }
                    if (!hlsBitrateEncoder.equals(mdnLiveItem.getHlsBitrate())) {
                        isChange = this.saveChangeItemByFieldName(resources, change, mdnLiveItem.getId(), FieldNameConstant.MdnLiveItem.??????????????????.toString(), "", "", hlsBitrateEncoder, hlsBitrateEncoder);
                    }
                } else if (MdnConstant.EncoderMode.????????????.toInteger().equals(mdnLiveItem.getEncoderMode()) && MdnConstant.EncoderMode.????????????.toInteger().equals(encoderMode)) {
                    // ???????????? -> ????????????
                    if (!httpUrlEncoder.equals(mdnLiveItem.getHttpUrl())) {
                        isChange = this.saveChangeItemByFieldName(resources, change, mdnLiveItem.getId(), FieldNameConstant.MdnLiveItem.????????????.toString(), "", "", httpUrlEncoder, httpUrlEncoder);
                    }
                    if (!httpBitrateEncoder.equals(mdnLiveItem.getHttpBitrate())) {
                        isChange = this.saveChangeItemByFieldName(resources, change, mdnLiveItem.getId(), FieldNameConstant.MdnLiveItem.??????????????????.toString(), "", "", httpBitrateEncoder, httpBitrateEncoder);
                    }
                }
            } else {
                /* ???????????????????????? */
                if (MdnConstant.EncoderMode.????????????.toInteger().equals(encoderMode)) {
                    // HTTP????????????
                    if (!httpUrlEncoder.equals(mdnLiveItem.getHttpUrl())) {
                        isChange = this.saveChangeItemByFieldName(resources, change, mdnLiveItem.getId(), FieldNameConstant.MdnLiveItem.????????????.toString(), mdnLiveItem.getHttpUrl(), mdnLiveItem.getHttpUrl(), httpUrlEncoder, httpUrlEncoder);
                    }
                    if (!httpBitrateEncoder.equals(mdnLiveItem.getHttpBitrate())) {
                        isChange = this.saveChangeItemByFieldName(resources, change, mdnLiveItem.getId(), FieldNameConstant.MdnLiveItem.??????????????????.toString(), mdnLiveItem.getHttpBitrate(), mdnLiveItem.getHttpBitrate(), httpBitrateEncoder, httpBitrateEncoder);
                    }
                } else if (MdnConstant.EncoderMode.????????????.toInteger().equals(encoderMode)) {
                    if (!hlsUrlEncoder.equals(mdnLiveItem.getHlsUrl())) {
                        isChange = this.saveChangeItemByFieldName(resources, change, mdnLiveItem.getId(), FieldNameConstant.MdnLiveItem.????????????.toString(), mdnLiveItem.getHlsUrl(), mdnLiveItem.getHlsUrl(), hlsUrlEncoder, hlsUrlEncoder);
                    }
                    if (!hlsBitrateEncoder.equals(mdnLiveItem.getHlsBitrate())) {
                        isChange = this.saveChangeItemByFieldName(resources, change, mdnLiveItem.getId(), FieldNameConstant.MdnLiveItem.??????????????????.toString(), mdnLiveItem.getHlsBitrate(), mdnLiveItem.getHlsBitrate(), hlsBitrateEncoder, hlsBitrateEncoder);
                    }
                }
            }
        } else {
            // Transfer??????
            /* ????????????live??????????????????????????????Transfer?????? */
            if (!mdnLiveItem.getHttpUrl().equals(httpUrl)) {
                isChange = this.saveChangeItemByFieldName(resources, change, mdnLiveItem.getId(), FieldNameConstant.MdnLiveItem.HTTP?????????.toString(), mdnLiveItem.getHttpUrl(), mdnLiveItem.getHttpUrl(), httpUrl, httpUrl);
            }
            if (!mdnLiveItem.getHttpBitrate().equals(httpBitrate)) {
                isChange = this.saveChangeItemByFieldName(resources, change, mdnLiveItem.getId(), FieldNameConstant.MdnLiveItem.HTTP???????????????.toString(), mdnLiveItem.getHttpBitrate(), mdnLiveItem.getHttpBitrate(), httpBitrate, httpBitrate);
            }
            if (!mdnLiveItem.getHlsUrl().equals(hlsUrl)) {
                isChange = this.saveChangeItemByFieldName(resources, change, mdnLiveItem.getId(), FieldNameConstant.MdnLiveItem.HSL?????????.toString(), mdnLiveItem.getHlsUrl(), mdnLiveItem.getHlsUrl(), hlsUrl, hlsUrl);
            }
            if (!mdnLiveItem.getHlsBitrate().equals(hlsBitrate)) {
                isChange = this.saveChangeItemByFieldName(resources, change, mdnLiveItem.getId(), FieldNameConstant.MdnLiveItem.HSL???????????????.toString(), mdnLiveItem.getHlsBitrate(), mdnLiveItem.getHlsBitrate(), hlsBitrate, hlsBitrate);
            }
        }
    }
    return isChange;
}


@Override
public boolean compareDns(Resources resources,Change change,NetworkDnsItem networkDnsItem,String domainName,Integer domainType,String cnameDomain,String[] eipIds){
    /**
     * ???????????????????????????????????????????????? compareCompute??????
     */
    // Step.1????????????boolean?????????flag.????????????????????????????????????.
    boolean flag = false;
    boolean isChange = false;
    if (networkDnsItem.getDomainType().equals(domainType) && NetworkConstant.DomainType.CNAME.toInteger().equals(domainType)) {
        // ??????????????????????????????CNAME,????????????CNAME????????????.
        if (!networkDnsItem.getCnameDomain().equals(cnameDomain)) {
            String fieldName = FieldNameConstant.Dns.CNAME??????.toString();
            String oldValue = networkDnsItem.getCnameDomain();
            String oldString = networkDnsItem.getCnameDomain();
            String newValue = cnameDomain;
            String newString = cnameDomain;
            isChange = this.saveChangeItemByFieldName(resources, change, fieldName, oldValue, oldString, newValue, newString);
        }
    } else if (!NetworkConstant.DomainType.CNAME.toInteger().equals(networkDnsItem.getDomainType()) && !NetworkConstant.DomainType.CNAME.toInteger().equals(domainType)) {
        // ?????????????????????????????? GSLB???A,????????????IP.
        // ????????????????????????eipIds?????????","????????????,????????????????????????????????????.
        String oldId = Collections3.extractToString(networkDnsItem.getNetworkEipItemList(), "id", ",");
        String newId = eipIds != null ? StringUtils.join(eipIds, ",") : "";
        if (!oldId.equals(newId)) {
            String fieldName = FieldNameConstant.Dns.??????IP.toString();
            String oldValue = oldId;
            String oldString = networkDnsItem.getMountElbs();
            // ??????computeIds??????compute???List,??????????????????.
            List<NetworkEipItem> list = new ArrayList<NetworkEipItem>();
            for (int i = 0; i < eipIds.length; i++) {
                NetworkEipItem networkEipItem = comm.eipService.getNetworkEipItem(Integer.valueOf(eipIds[i]));
                list.add(networkEipItem);
            }
            String newValue = newId;
            String newString = NetworkDnsItem.extractToString(list);
            isChange = this.saveChangeItemByFieldName(resources, change, fieldName, oldValue, oldString, newValue, newString);
        }
    } else if (NetworkConstant.DomainType.CNAME.toInteger().equals(networkDnsItem.getDomainType()) && !NetworkConstant.DomainType.CNAME.toInteger().equals(domainType)) {
        // ????????????CNAME,????????????GSLB???A.???CNAME?????????????????????"",??????IP????????????"".
        // Old
        String fieldNameCNAME = FieldNameConstant.Dns.CNAME??????.toString();
        String oldValueCNAME = networkDnsItem.getCnameDomain();
        String oldStringCNAME = networkDnsItem.getCnameDomain();
        String newValueCNAME = "";
        String newStringCNAME = "";
        isChange = this.saveChangeItemByFieldName(resources, change, fieldNameCNAME, oldValueCNAME, oldStringCNAME, newValueCNAME, newStringCNAME);
        // New
        String fieldName = FieldNameConstant.Dns.??????IP.toString();
        String oldValue = "";
        String oldString = "";
        // ??????computeIds??????compute???List,??????????????????.
        List<NetworkEipItem> list = new ArrayList<NetworkEipItem>();
        if (eipIds != null) {
            for (int i = 0; i < eipIds.length; i++) {
                NetworkEipItem networkEipItem = comm.eipService.getNetworkEipItem(Integer.valueOf(eipIds[i]));
                list.add(networkEipItem);
            }
        }
        String newValue = eipIds != null ? StringUtils.join(eipIds, ",") : "";
        String newString = NetworkDnsItem.extractToString(list);
        isChange = this.saveChangeItemByFieldName(resources, change, fieldName, oldValue, oldString, newValue, newString);
    } else if (!NetworkConstant.DomainType.CNAME.toInteger().equals(networkDnsItem.getDomainType()) && NetworkConstant.DomainType.CNAME.toInteger().equals(domainType)) {
        // ????????????GSLB???A,????????????CNAME.
        // Old
        String fieldName = FieldNameConstant.Dns.??????IP.toString();
        String oldValue = Collections3.extractToString(networkDnsItem.getNetworkEipItemList(), "id", ",");
        String oldString = networkDnsItem.getMountElbs();
        String newValue = "";
        String newString = "";
        isChange = this.saveChangeItemByFieldName(resources, change, fieldName, oldValue, oldString, newValue, newString);
        // New
        String fieldNameCNAME = FieldNameConstant.Dns.CNAME??????.toString();
        String oldValueCNAME = "";
        String oldStringCNAME = "";
        String newValueCNAME = cnameDomain;
        String newStringCNAME = cnameDomain;
        isChange = this.saveChangeItemByFieldName(resources, change, fieldNameCNAME, oldValueCNAME, oldStringCNAME, newValueCNAME, newStringCNAME);
    }
    // ????????????
    if (!networkDnsItem.getDomainType().equals(domainType)) {
        String fieldName = FieldNameConstant.Dns.????????????.toString();
        String oldValue = networkDnsItem.getDomainType().toString();
        String oldString = NetworkConstant.DomainType.get(networkDnsItem.getDomainType());
        String newValue = domainType.toString();
        String newString = NetworkConstant.DomainType.get(domainType);
        isChange = this.saveChangeItemByFieldName(resources, change, fieldName, oldValue, oldString, newValue, newString);
    }
    // Step.1 ???????????????,?????????true.
    flag = isChange;
    // ??????
    // ???????????????,????????????.
    if (true) {
        String fieldName = FieldNameConstant.Dns.??????.toString();
        String oldValue = networkDnsItem.getDomainName();
        String oldString = networkDnsItem.getDomainName() + " ";
        String newValue = domainName;
        String newString = domainName;
        this.saveChangeItemByFieldName(resources, change, fieldName, oldValue, oldString, newValue, newString);
    }
    // Step.2 ????????????????????????,???????????????????????????,????????????????????????????????????????????????,?????????????????????????????????ESG.
    if (isChange && !flag) {
        if (NetworkConstant.DomainType.CNAME.toInteger().equals(networkDnsItem.getDomainType())) {
            // CNAME(3)
            String fieldNameCNAME = FieldNameConstant.Dns.CNAME??????.toString();
            String oldValueCNAME = networkDnsItem.getCnameDomain();
            String oldStringCNAME = networkDnsItem.getCnameDomain();
            String newValueCNAME = networkDnsItem.getCnameDomain();
            String newStringCNAME = networkDnsItem.getCnameDomain();
            this.saveChangeItemByFieldName(resources, change, fieldNameCNAME, oldValueCNAME, oldStringCNAME, newValueCNAME, newStringCNAME);
        } else {
            // GSLB(1), A(2)
            String oldId = Collections3.extractToString(networkDnsItem.getNetworkEipItemList(), "id", ",");
            String fieldName = FieldNameConstant.Dns.??????IP.toString();
            String oldValue = oldId;
            String oldString = networkDnsItem.getMountElbs();
            String newValue = oldId;
            String newString = networkDnsItem.getMountElbs();
            this.saveChangeItemByFieldName(resources, change, fieldName, oldValue, oldString, newValue, newString);
        }
    }
    return isChange;
}


public String wrapElbPortItemFromNetworkElbItemToString(NetworkElbItem networkElbItem){
    StringBuilder sb = new StringBuilder();
    List<ElbPortItem> elbPortItems = comm.elbService.getElbPortItemListByElbId(networkElbItem.getId());
    for (ElbPortItem elbPortItem : elbPortItems) {
        sb.append(elbPortItem.getProtocol()).append(",").append(elbPortItem.getSourcePort()).append(",").append(elbPortItem.getTargetPort()).append("<br>");
    }
    return sb.toString();
}


@Override
public boolean compareEip(Resources resources,Change change,NetworkEipItem networkEipItem,List<EipPortItem> eipPortItems,String linkType,String linkId,String[] protocols,String[] sourcePorts,String[] targetPorts){
    if ("".equals(linkId)) {
        linkId = "0";
    }
    /**
     * ???????????????????????????????????????????????? compareCompute??????
     */
    // Step.1????????????boolean?????????flag.????????????????????????????????????.
    boolean flag = false;
    boolean isChange = false;
    // ????????????????????????.
    String oldLinkType = networkEipItem.getComputeItem() != null ? NetworkConstant.LinkType.????????????.toString() : NetworkConstant.LinkType.??????ELB.toString();
    // ????????????????????????
    String newLinkType = linkType;
    if (newLinkType.equals(oldLinkType) && NetworkConstant.LinkType.????????????.toString().equals(newLinkType)) {
        // ????????????????????????????????????,???????????????????????????????????????????????????????????????Change???.
        String fieldName = FieldNameConstant.Eip.????????????.toString();
        String oldValue = networkEipItem.getComputeItem() != null ? networkEipItem.getComputeItem().getId().toString() : "";
        String oldString = networkEipItem.getComputeItem() != null ? this.wrapStringByComputeItem(networkEipItem.getComputeItem().getId().toString()) : "";
        String newValue = linkId.toString();
        String newString = this.wrapStringByComputeItem(linkId);
        if (!oldValue.equals(newValue)) {
            isChange = this.saveChangeItemByFieldName(resources, change, fieldName, oldValue, oldString, newValue, newString);
        }
    } else if (newLinkType.equals(oldLinkType) && NetworkConstant.LinkType.??????ELB.toString().equals(newLinkType)) {
        // ????????????????????????????????????,???????????????????????????????????????ELB??????????????????Change???.
        String fieldName = FieldNameConstant.Eip.??????ELB.toString();
        String oldValue = networkEipItem.getNetworkElbItem() != null ? networkEipItem.getNetworkElbItem().getId().toString() : "";
        String oldString = networkEipItem.getNetworkElbItem() != null ? this.wrapStringByNetworkElbItem(networkEipItem.getNetworkElbItem().getId()) : "";
        String newValue = linkId.toString();
        String newString = this.wrapStringByNetworkElbItem(Integer.valueOf(linkId));
        if (!oldValue.equals(newValue)) {
            isChange = this.saveChangeItemByFieldName(resources, change, fieldName, oldValue, oldString, newValue, newString);
        }
    } else if (!newLinkType.equals(oldLinkType)) {
        // ?????????????????????????????????.??????????????????null?????????,????????????Change,?????????????????????ELB?????????.
        if (networkEipItem.getComputeItem() != null) {
            // ?????????????????? "" ??????????????????.
            // Old
            String fieldNameCompute = FieldNameConstant.Eip.????????????.toString();
            String oldValueCompute = networkEipItem.getComputeItem() != null ? networkEipItem.getComputeItem().getId().toString() : "";
            String oldStringCompute = networkEipItem.getComputeItem() != null ? this.wrapStringByComputeItem(networkEipItem.getComputeItem().getId().toString()) : "";
            String newValueCompute = UN_SELECTED_STRING;
            String newStringCompute = "";
            isChange = this.saveChangeItemByFieldName(resources, change, fieldNameCompute, oldValueCompute, oldStringCompute, newValueCompute, newStringCompute);
            // New
            String fieldNameElb = FieldNameConstant.Eip.??????ELB.toString();
            String oldValueElb = UN_SELECTED_STRING;
            String oldStringElb = "";
            String newValueElb = linkId.toString();
            String newStringElb = this.wrapStringByNetworkElbItem(Integer.valueOf(linkId));
            isChange = this.saveChangeItemByFieldName(resources, change, fieldNameElb, oldValueElb, oldStringElb, newValueElb, newStringElb);
        } else {
            // Old
            String fieldNameCompute = FieldNameConstant.Eip.????????????.toString();
            String oldValueCompute = UN_SELECTED_STRING;
            String oldStringCompute = "";
            String newValueCompute = linkId.toString();
            String newStringCompute = this.wrapStringByComputeItem(linkId);
            isChange = this.saveChangeItemByFieldName(resources, change, fieldNameCompute, oldValueCompute, oldStringCompute, newValueCompute, newStringCompute);
            // New
            String fieldNameElb = FieldNameConstant.Eip.??????ELB.toString();
            String oldValueElb = networkEipItem.getNetworkElbItem() != null ? networkEipItem.getNetworkElbItem().getId().toString() : "";
            String oldStringElb = networkEipItem.getNetworkElbItem() != null ? this.wrapStringByNetworkElbItem(networkEipItem.getNetworkElbItem().getId()) : "";
            String newValueElb = UN_SELECTED_STRING;
            String newStringElb = "";
            isChange = this.saveChangeItemByFieldName(resources, change, fieldNameElb, oldValueElb, oldStringElb, newValueElb, newStringElb);
        }
    }
    flag = isChange;
    // ????????????
    if (this.compareEipPortItem(eipPortItems, protocols, sourcePorts, targetPorts)) {
        String fieldName = FieldNameConstant.Eip.????????????.toString();
        String oldValue = this.wrapEipPortItemFromNetworkEipItemToString(networkEipItem);
        String oldString = this.wrapEipPortItemFromNetworkEipItemToString(networkEipItem);
        String newValue = "";
        String newString = this.wrapPortItemToString(protocols, sourcePorts, targetPorts);
        isChange = this.saveChangeItemByFieldName(resources, change, fieldName, oldValue, oldString, newValue, newString);
    }
    // Step.2
    // ????????????&??????ELB????????????,???????????????????????????,????????????????????????&??????ELB????????????????????????,????????????????????????????????????&??????ELB.
    if (isChange && !flag) {
        if (NetworkConstant.LinkType.??????ELB.toString().equals(newLinkType)) {
            String fieldName = FieldNameConstant.Eip.??????ELB.toString();
            String oldValue = networkEipItem.getNetworkElbItem() != null ? networkEipItem.getNetworkElbItem().getId().toString() : "";
            String oldString = networkEipItem.getNetworkElbItem() != null ? this.wrapStringByNetworkElbItem(networkEipItem.getNetworkElbItem().getId()) : "";
            String newValue = linkId.toString();
            String newString = this.wrapStringByNetworkElbItem(Integer.valueOf(linkId));
            this.saveChangeItemByFieldName(resources, change, fieldName, oldValue, oldString, newValue, newString);
        } else {
            String fieldName = FieldNameConstant.Eip.????????????.toString();
            String oldValue = networkEipItem.getComputeItem() != null ? networkEipItem.getComputeItem().getId().toString() : "";
            String oldString = networkEipItem.getComputeItem() != null ? this.wrapStringByComputeItem(networkEipItem.getComputeItem().getId().toString()) : "";
            String newValue = linkId.toString();
            String newString = this.wrapStringByComputeItem(linkId);
            this.saveChangeItemByFieldName(resources, change, fieldName, oldValue, oldString, newValue, newString);
        }
    }
    return isChange;
}


public boolean saveChangeItemByFieldName(Resources resources,Change change,Integer subResourcesId,String fieldName,String oldValue,String oldString,String newValue,String newString){
    // ??????changeId???fieldName??????????????????ChangeItem list
    List<ChangeItem> changeItems = comm.changeServcie.getChangeItemListByChangeIdAndFieldName(change.getId(), fieldName);
    // ??????ChangeItem,???????????????????????????????????????(???????????????????????????)
    if (changeItems.isEmpty() || resources.getStatus().equals(ResourcesConstant.Status.?????????.toInteger()) || resources.getStatus().equals(ResourcesConstant.Status.?????????.toInteger())) {
        if (!newString.equals(oldString)) {
            // ??????????????????ChangeItem
            ChangeItem changeItem = new ChangeItem();
            changeItem.setChange(change);
            changeItem.setFieldName(fieldName);
            changeItem.setOldValue(StringUtils.defaultIfEmpty(oldValue, ""));
            changeItem.setOldString(StringUtils.defaultIfEmpty(oldString, ""));
            changeItem.setNewValue(StringUtils.defaultIfEmpty(newValue, ""));
            changeItem.setNewString(StringUtils.defaultIfEmpty(newString, ""));
            comm.changeServcie.saveOrUpdateChangeItem(changeItem);
        }
    } else {
        // ???????????????ChangeItem
        ChangeItem changeItem = changeItems.get(0);
        changeItem.setNewValue(StringUtils.defaultIfEmpty(newValue, ""));
        changeItem.setNewString(StringUtils.defaultIfEmpty(newString, ""));
        comm.changeServcie.saveOrUpdateChangeItem(changeItem);
    }
    return true;
}


@Override
public boolean compareMonitorElb(Resources resources,Change change,MonitorElb monitorElb,Integer elbId){
    boolean isChange = false;
    // ??????ELB
    if (!monitorElb.getNetworkElbItem().getId().equals(elbId)) {
        String fieldName = FieldNameConstant.monitorElb.??????ELB.toString();
        String oldValue = monitorElb.getNetworkElbItem().getId().toString();
        String oldString = this.wrapStringByNetworkElbItem(monitorElb.getNetworkElbItem().getId());
        String newValue = elbId.toString();
        String newString = this.wrapStringByNetworkElbItem(elbId);
        isChange = this.saveChangeItemByFieldName(resources, change, fieldName, oldValue, oldString, newValue, newString);
    }
    return isChange;
}


public String wrapStringByComputeItem(String computeId){
    if ("".equals(computeId)) {
        computeId = "0";
    }
    ComputeItem computeItem = comm.computeService.getComputeItem(Integer.valueOf(computeId));
    String value = "";
    if (computeItem != null) {
        String innerIp = "";
        if (computeItem.getInnerIp() != null) {
            innerIp = computeItem.getInnerIp();
        }
        value += computeItem.getIdentifier() + "(" + innerIp + ")";
    }
    return value;
}


public String wrapStringByNetworkEsgItem(Integer esgId){
    NetworkEsgItem networkEsgItem = comm.esgService.getNetworkEsgItem(esgId);
    String value = "";
    if (networkEsgItem != null) {
        String description = "";
        if (networkEsgItem.getDescription() != null) {
            description = networkEsgItem.getDescription();
        }
        value += networkEsgItem.getIdentifier() + "(" + description + ")";
    }
    return value;
}


@Override
public boolean compareMdnVodItem(Resources resources,Change change,MdnVodItem mdnVodItem,String vodDomain,String vodProtocol,String sourceOutBandwidth,String sourceStreamerUrl){
    boolean isChange = false;
    if (!mdnVodItem.getVodDomain().equals(vodDomain)) {
        String fieldName = FieldNameConstant.MdnVodItem.??????????????????.toString();
        String oldValue = mdnVodItem.getVodDomain();
        String oldString = mdnVodItem.getVodDomain();
        String newValue = vodDomain;
        String newString = vodDomain;
        isChange = this.saveChangeItemByFieldName(resources, change, mdnVodItem.getId(), fieldName, oldValue, oldString, newValue, newString);
    }
    if (!mdnVodItem.getVodProtocol().equals(vodProtocol)) {
        String fieldName = FieldNameConstant.MdnVodItem.????????????????????????.toString();
        String oldValue = mdnVodItem.getVodProtocol();
        String oldString = mdnVodItem.getVodProtocol();
        String newValue = vodProtocol;
        String newString = vodProtocol;
        isChange = this.saveChangeItemByFieldName(resources, change, mdnVodItem.getId(), fieldName, oldValue, oldString, newValue, newString);
    }
    if (!mdnVodItem.getSourceOutBandwidth().equals(sourceOutBandwidth)) {
        String fieldName = FieldNameConstant.MdnVodItem.????????????????????????.toString();
        String oldValue = mdnVodItem.getSourceOutBandwidth();
        String oldString = mdnVodItem.getSourceOutBandwidth();
        String newValue = sourceOutBandwidth;
        String newString = sourceOutBandwidth;
        isChange = this.saveChangeItemByFieldName(resources, change, mdnVodItem.getId(), fieldName, oldValue, oldString, newValue, newString);
    }
    if (!mdnVodItem.getSourceStreamerUrl().equals(sourceStreamerUrl)) {
        String fieldName = FieldNameConstant.MdnVodItem.Streamer??????.toString();
        String oldValue = mdnVodItem.getSourceStreamerUrl();
        String oldString = mdnVodItem.getSourceStreamerUrl();
        String newValue = sourceStreamerUrl;
        String newString = sourceStreamerUrl;
        isChange = this.saveChangeItemByFieldName(resources, change, mdnVodItem.getId(), fieldName, oldValue, oldString, newValue, newString);
    }
    return isChange;
}


@Override
public boolean compareMonitorCompute(Resources resources,Change change,MonitorCompute monitorCompute,String ipAddress,String cpuWarn,String cpuCritical,String memoryWarn,String memoryCritical,String pingLossWarn,String pingLossCritical,String diskWarn,String diskCritical,String pingDelayWarn,String pingDelayCritical,String maxProcessWarn,String maxProcessCritical,String port,String process,String mountPoint){
    // ????????????
    boolean isChange = false;
    if (!monitorCompute.getIpAddress().equals(ipAddress)) {
        isChange = this.saveChangeItemByFieldName(resources, change, FieldNameConstant.monitorCompute.????????????.toString(), monitorCompute.getIpAddress(), monitorCompute.getIpAddress(), ipAddress, ipAddress);
    }
    if (!monitorCompute.getPort().equals(port)) {
        isChange = this.saveChangeItemByFieldName(resources, change, FieldNameConstant.monitorCompute.????????????.toString(), monitorCompute.getPort(), monitorCompute.getPort(), port, port);
    }
    if (!monitorCompute.getProcess().equals(process)) {
        isChange = this.saveChangeItemByFieldName(resources, change, FieldNameConstant.monitorCompute.????????????.toString(), monitorCompute.getProcess(), monitorCompute.getProcess(), process, process);
    }
    if (!monitorCompute.getMountPoint().equals(mountPoint)) {
        isChange = this.saveChangeItemByFieldName(resources, change, FieldNameConstant.monitorCompute.????????????.toString(), monitorCompute.getMountPoint(), monitorCompute.getMountPoint(), mountPoint, mountPoint);
    }
    if (!monitorCompute.getCpuWarn().equals(cpuWarn)) {
        isChange = this.saveChangeItemByFieldName(resources, change, FieldNameConstant.monitorCompute.CPU?????????????????????.toString(), monitorCompute.getCpuWarn(), MonitorConstant.THRESHOLD_GT_STRING_KEY.get(monitorCompute.getCpuWarn()), cpuWarn, MonitorConstant.THRESHOLD_GT_STRING_KEY.get(cpuWarn));
    }
    if (!monitorCompute.getCpuCritical().equals(cpuCritical)) {
        isChange = this.saveChangeItemByFieldName(resources, change, FieldNameConstant.monitorCompute.CPU?????????????????????.toString(), monitorCompute.getCpuCritical(), MonitorConstant.THRESHOLD_GT_STRING_KEY.get(monitorCompute.getCpuCritical()), cpuCritical, MonitorConstant.THRESHOLD_GT_STRING_KEY.get(cpuCritical));
    }
    if (!monitorCompute.getMemoryWarn().equals(memoryWarn)) {
        isChange = this.saveChangeItemByFieldName(resources, change, FieldNameConstant.monitorCompute.???????????????????????????.toString(), monitorCompute.getMemoryWarn(), MonitorConstant.THRESHOLD_GT_STRING_KEY.get(monitorCompute.getMemoryWarn()), memoryWarn, MonitorConstant.THRESHOLD_GT_STRING_KEY.get(memoryWarn));
    }
    if (!monitorCompute.getMemoryCritical().equals(memoryCritical)) {
        isChange = this.saveChangeItemByFieldName(resources, change, FieldNameConstant.monitorCompute.???????????????????????????.toString(), monitorCompute.getMemoryCritical(), MonitorConstant.THRESHOLD_GT_STRING_KEY.get(monitorCompute.getMemoryCritical()), memoryCritical, MonitorConstant.THRESHOLD_GT_STRING_KEY.get(memoryCritical));
    }
    if (!monitorCompute.getPingLossWarn().equals(pingLossWarn)) {
        isChange = this.saveChangeItemByFieldName(resources, change, FieldNameConstant.monitorCompute.???????????????????????????.toString(), monitorCompute.getPingLossWarn(), MonitorConstant.THRESHOLD_GT_STRING_KEY.get(monitorCompute.getPingLossWarn()), pingLossWarn, MonitorConstant.THRESHOLD_GT_STRING_KEY.get(pingLossWarn));
    }
    if (!monitorCompute.getPingLossCritical().equals(pingLossCritical)) {
        isChange = this.saveChangeItemByFieldName(resources, change, FieldNameConstant.monitorCompute.???????????????????????????.toString(), monitorCompute.getPingLossCritical(), MonitorConstant.THRESHOLD_GT_STRING_KEY.get(monitorCompute.getPingLossCritical()), pingLossCritical, MonitorConstant.THRESHOLD_GT_STRING_KEY.get(pingLossCritical));
    }
    if (!monitorCompute.getDiskWarn().equals(diskWarn)) {
        isChange = this.saveChangeItemByFieldName(resources, change, FieldNameConstant.monitorCompute.???????????????????????????.toString(), monitorCompute.getDiskWarn(), MonitorConstant.THRESHOLD_LT_STRING_KEY.get(monitorCompute.getDiskWarn()), diskWarn, MonitorConstant.THRESHOLD_LT_STRING_KEY.get(diskWarn));
    }
    if (!monitorCompute.getDiskCritical().equals(diskCritical)) {
        isChange = this.saveChangeItemByFieldName(resources, change, FieldNameConstant.monitorCompute.???????????????????????????.toString(), monitorCompute.getDiskCritical(), MonitorConstant.THRESHOLD_LT_STRING_KEY.get(monitorCompute.getDiskCritical()), diskCritical, MonitorConstant.THRESHOLD_LT_STRING_KEY.get(diskCritical));
    }
    if (!monitorCompute.getPingDelayWarn().equals(pingDelayWarn)) {
        isChange = this.saveChangeItemByFieldName(resources, change, FieldNameConstant.monitorCompute.???????????????????????????.toString(), monitorCompute.getPingDelayWarn(), MonitorConstant.THRESHOLD_NET_GT_STRING_KEY.get(monitorCompute.getPingDelayWarn()), pingDelayWarn, MonitorConstant.THRESHOLD_NET_GT_STRING_KEY.get(pingDelayWarn));
    }
    if (!monitorCompute.getPingDelayCritical().equals(pingDelayCritical)) {
        isChange = this.saveChangeItemByFieldName(resources, change, FieldNameConstant.monitorCompute.???????????????????????????.toString(), monitorCompute.getPingDelayCritical(), MonitorConstant.THRESHOLD_NET_GT_STRING_KEY.get(monitorCompute.getPingDelayCritical()), pingDelayCritical, MonitorConstant.THRESHOLD_NET_GT_STRING_KEY.get(pingDelayCritical));
    }
    if (!monitorCompute.getMaxProcessWarn().equals(maxProcessWarn)) {
        isChange = this.saveChangeItemByFieldName(resources, change, FieldNameConstant.monitorCompute.???????????????????????????.toString(), monitorCompute.getMaxProcessWarn(), MonitorConstant.MAX_PROCESS_STRING_KEY.get(monitorCompute.getMaxProcessWarn()), maxProcessWarn, MonitorConstant.MAX_PROCESS_STRING_KEY.get(maxProcessWarn));
    }
    if (!monitorCompute.getMaxProcessCritical().equals(maxProcessCritical)) {
        isChange = this.saveChangeItemByFieldName(resources, change, FieldNameConstant.monitorCompute.???????????????????????????.toString(), monitorCompute.getMaxProcessCritical(), MonitorConstant.MAX_PROCESS_STRING_KEY.get(monitorCompute.getMaxProcessCritical()), maxProcessCritical, MonitorConstant.MAX_PROCESS_STRING_KEY.get(maxProcessCritical));
    }
    return isChange;
}


@Override
public boolean compareCompute(Resources resources,Change change,ComputeItem computeItem,Integer osType,Integer osBit,Integer serverType,String[] esgIds,String remark,String[] applicationNames,String[] applicationVersions,String[] applicationDeployPaths){
    /**
     * ???????????????:???????????????????????????.PCS & ECS??? ??????ESG, ES3??? ????????????, ELB??? ???????????? ?????????.EIP???????????????&??????ELB
     *
     * 1.????????????boolean?????????flag.??????????????????ESG????????????.
     *
     * 2.???flag???false(?????????ESG????????????.),???????????????????????????,??????????????????ESG????????????????????????,?????????????????????????????????ESG
     *
     * ???????????????????????????.????????????????????????ESG????????????.????????????isChange????????????????????????.
     */
    // ?????????????????????,?????????????????????
    boolean isChange = false;
    // Step.1????????????boolean?????????flag.??????????????????ESG????????????.
    boolean flag = false;
    // ESG.????????????????????????esgId?????????","????????????,????????????????????????????????????.
    String oldId = Collections3.extractToString(computeItem.getNetworkEsgItemList(), "id", ",");
    String newId = esgIds != null ? StringUtils.join(esgIds, ",") : "";
    if (!oldId.equals(newId)) {
        String fieldName = FieldNameConstant.Compate.ESG.toString();
        String oldValue = oldId;
        String oldString = computeItem.getMountESG();
        // ??????computeIds??????compute???List,??????????????????.
        List<NetworkEsgItem> networkEsgItemList = new ArrayList<NetworkEsgItem>();
        if (!"".equals(esgIds) && esgIds != null) {
            for (int i = 0; i < esgIds.length; i++) {
                networkEsgItemList.add(comm.esgService.getNetworkEsgItem(Integer.valueOf(esgIds[i])));
            }
        }
        String newValue = newId;
        String newString = ComputeItem.extractToString(networkEsgItemList);
        isChange = this.saveChangeItemByFieldName(resources, change, fieldName, oldValue, oldString, newValue, newString);
        // Step.1 ???????????????,?????????true.
        flag = isChange;
    }
    // ????????????
    if (!computeItem.getOsType().equals(osType)) {
        String fieldName = FieldNameConstant.Compate.????????????.toString();
        String oldValue = computeItem.getOsType().toString();
        String oldString = ComputeConstant.OS_TYPE_MAP.get(computeItem.getOsType());
        String newValue = osType.toString();
        String newString = ComputeConstant.OS_TYPE_MAP.get(osType);
        isChange = this.saveChangeItemByFieldName(resources, change, fieldName, oldValue, oldString, newValue, newString);
    }
    // ??????
    if (!computeItem.getOsBit().equals(osBit)) {
        String fieldName = FieldNameConstant.Compate.????????????.toString();
        String oldValue = computeItem.getOsBit().toString();
        String oldString = ComputeConstant.OS_BIT_MAP.get(computeItem.getOsBit());
        String newValue = osBit.toString();
        String newString = ComputeConstant.OS_BIT_MAP.get(osBit);
        isChange = this.saveChangeItemByFieldName(resources, change, fieldName, oldValue, oldString, newValue, newString);
    }
    // ??????
    if (!computeItem.getServerType().equals(serverType)) {
        String fieldName = FieldNameConstant.Compate.??????.toString();
        String oldValue = computeItem.getServerType().toString();
        // ??????PCS???ECS
        String oldString = computeItem.getComputeType().equals(ComputeConstant.ComputeType.PCS.toInteger()) ? ComputeConstant.PCSServerType.get(computeItem.getServerType()) : ComputeConstant.ECSServerType.get(computeItem.getServerType());
        String newValue = serverType.toString();
        String newString = computeItem.getComputeType().equals(ComputeConstant.ComputeType.PCS.toInteger()) ? ComputeConstant.PCSServerType.get(serverType) : ComputeConstant.ECSServerType.get(serverType);
        isChange = this.saveChangeItemByFieldName(resources, change, fieldName, oldValue, oldString, newValue, newString);
    }
    // Remark
    if (!computeItem.getRemark().equals(remark)) {
        String fieldName = FieldNameConstant.Compate.????????????.toString();
        String oldValue = computeItem.getRemark().toString();
        String oldString = computeItem.getRemark().toString();
        String newValue = remark;
        String newString = remark;
        isChange = this.saveChangeItemByFieldName(resources, change, fieldName, oldValue, oldString, newValue, newString);
    }
    // Application
    if (this.compareApplication(computeItem, applicationNames, applicationVersions, applicationDeployPaths)) {
        String fieldName = FieldNameConstant.Compate.????????????.toString();
        List<Application> applications = comm.computeService.getApplicationByComputeItemId(computeItem.getId());
        StringBuilder applicationSb = new StringBuilder();
        if (applicationNames != null) {
            for (Application application : applications) {
                applicationSb.append(application.getName()).append(",").append(application.getVersion()).append(",").append(application.getDeployPath()).append("<br>");
            }
        }
        String oldValue = applicationSb.toString();
        String oldString = applicationSb.toString();
        String newValue = "";
        String newString = this.wrapApplicationToString(applicationNames, applicationVersions, applicationDeployPaths);
        isChange = this.saveChangeItemByFieldName(resources, change, fieldName, oldValue, oldString, newValue, newString);
    }
    // Step.2 ??????ESG????????????,???????????????????????????,??????????????????ESG????????????????????????,?????????????????????????????????ESG.
    if (isChange && !flag) {
        String fieldName = FieldNameConstant.Compate.ESG.toString();
        String oldValue = oldId;
        String oldString = computeItem.getMountESG();
        // ??????computeIds??????compute???List,??????????????????.
        List<NetworkEsgItem> networkEsgItemList = new ArrayList<NetworkEsgItem>();
        if (!"".equals(esgIds) && esgIds != null) {
            for (int i = 0; i < esgIds.length; i++) {
                networkEsgItemList.add(comm.esgService.getNetworkEsgItem(Integer.valueOf(esgIds[i])));
            }
        }
        String newValue = newId;
        String newString = ComputeItem.extractToString(networkEsgItemList);
        this.saveChangeItemByFieldName(resources, change, fieldName, oldValue, oldString, newValue, newString);
    }
    return isChange;
}


@Override
public boolean compareCP(Resources resources,Change change,CpItem cpItem,String recordStreamUrl,String recordBitrate,String exportEncode,Integer recordType,String recordTime,Integer recordDuration,String publishUrl,String isPushCtp,String videoFtpIp,String videoFtpPort,String videoFtpUsername,String videoFtpPassword,String videoFtpRootpath,String videoFtpUploadpath,String videoOutputGroup,String videoOutputWay,String pictrueFtpIp,String pictrueFtpPort,String pictrueFtpUsername,String pictrueFtpPassword,String pictrueFtpRootpath,String pictrueFtpUploadpath,String pictrueOutputGroup,String pictrueOutputMedia){
    boolean isChange = false;
    if (!cpItem.getRecordStreamUrl().equals(recordStreamUrl)) {
        String fieldName = FieldNameConstant.CpItem.?????????URL.toString();
        String oldValue = cpItem.getRecordStreamUrl();
        String oldString = cpItem.getRecordStreamUrl();
        String newValue = recordStreamUrl;
        String newString = recordStreamUrl;
        isChange = this.saveChangeItemByFieldName(resources, change, fieldName, oldValue, oldString, newValue, newString);
    }
    if (!cpItem.getRecordBitrate().equals(recordBitrate)) {
        String fieldName = FieldNameConstant.CpItem.????????????.toString();
        String oldValue = cpItem.getRecordBitrate();
        String oldString = CPConstant.RECORDBITRATE_MAP_STRING_KEY.get(cpItem.getRecordBitrate());
        String newValue = recordBitrate;
        String newString = CPConstant.RECORDBITRATE_MAP_STRING_KEY.get(recordBitrate);
        isChange = this.saveChangeItemByFieldName(resources, change, fieldName, oldValue, oldString, newValue, newString);
    }
    if (!cpItem.getExportEncode().equals(exportEncode)) {
        String fieldName = FieldNameConstant.CpItem.????????????.toString();
        String oldValue = cpItem.getExportEncode();
        String oldString = "";
        String[] oldExportEncodes = StringUtils.split(cpItem.getExportEncode(), ",");
        for (String key : oldExportEncodes) {
            oldString += CPConstant.EXPORTENCODE_MAP_STRING_KEY.get(key) + "<br>";
        }
        String newValue = exportEncode;
        String newString = "";
        String[] newExportEncodes = StringUtils.split(exportEncode, ",");
        for (String key : newExportEncodes) {
            newString += CPConstant.EXPORTENCODE_MAP_STRING_KEY.get(key) + "<br>";
        }
        isChange = this.saveChangeItemByFieldName(resources, change, fieldName, oldValue, oldString, newValue, newString);
    }
    if (!cpItem.getRecordType().equals(recordType)) {
        String fieldName = FieldNameConstant.CpItem.????????????.toString();
        String oldValue = cpItem.getRecordType().toString();
        String oldString = CPConstant.RecordType.get(cpItem.getRecordType());
        String newValue = recordType.toString();
        String newString = CPConstant.RecordType.get(recordType);
        isChange = this.saveChangeItemByFieldName(resources, change, fieldName, oldValue, oldString, newValue, newString);
    }
    if (!cpItem.getRecordTime().equals(recordTime)) {
        String fieldName = FieldNameConstant.CpItem.????????????.toString();
        String oldValue = cpItem.getRecordTime();
        String oldString = cpItem.getRecordTime();
        String newValue = recordTime;
        String newString = recordTime;
        isChange = this.saveChangeItemByFieldName(resources, change, fieldName, oldValue, oldString, newValue, newString);
    }
    if (!cpItem.getRecordDuration().equals(recordDuration)) {
        String fieldName = FieldNameConstant.CpItem.????????????.toString();
        String oldValue = cpItem.getRecordDuration().toString();
        String oldString = cpItem.getRecordDuration().toString();
        String newValue = recordDuration.toString();
        String newString = recordDuration.toString();
        isChange = this.saveChangeItemByFieldName(resources, change, fieldName, oldValue, oldString, newValue, newString);
    }
    // ???????????????null,????????????3??????????????????.
    if ((cpItem.getPublishUrl() != null & publishUrl != null && !cpItem.getPublishUrl().equals(publishUrl)) || (cpItem.getPublishUrl() == null && publishUrl != null) || (cpItem.getPublishUrl() != null && publishUrl == null)) {
        String fieldName = FieldNameConstant.CpItem.??????????????????.toString();
        String oldValue = cpItem.getPublishUrl();
        String oldString = cpItem.getPublishUrl();
        String newValue = publishUrl;
        String newString = publishUrl;
        isChange = this.saveChangeItemByFieldName(resources, change, fieldName, oldValue, oldString, newValue, newString);
    }
    if (!cpItem.getIsPushCtp().toString().equals(isPushCtp)) {
        String fieldName = FieldNameConstant.CpItem.??????????????????????????????.toString();
        String oldValue = cpItem.getIsPushCtp().toString();
        String oldString = CPConstant.IsPushCtp.get(cpItem.getIsPushCtp());
        String newValue = isPushCtp;
        String newString = CPConstant.IsPushCtp.get(CPConstant.IsPushCtp.??????.toString().equals(isPushCtp) ? true : false);
        isChange = this.saveChangeItemByFieldName(resources, change, fieldName, oldValue, oldString, newValue, newString);
    }
    if (!cpItem.getVideoFtpIp().equals(videoFtpIp)) {
        isChange = this.saveChangeItemByFieldName(resources, change, FieldNameConstant.CpItem.??????FTP??????IP.toString(), cpItem.getVideoFtpIp(), cpItem.getVideoFtpIp(), videoFtpIp, videoFtpIp);
    }
    if (!cpItem.getVideoFtpPort().equals(videoFtpPort)) {
        isChange = this.saveChangeItemByFieldName(resources, change, FieldNameConstant.CpItem.????????????.toString(), cpItem.getVideoFtpPort(), cpItem.getVideoFtpPort(), videoFtpPort, videoFtpPort);
    }
    if (!cpItem.getVideoFtpUsername().equals(videoFtpUsername)) {
        isChange = this.saveChangeItemByFieldName(resources, change, FieldNameConstant.CpItem.??????FTP?????????.toString(), cpItem.getVideoFtpUsername(), cpItem.getVideoFtpUsername(), videoFtpUsername, videoFtpUsername);
    }
    if (!cpItem.getVideoFtpPassword().equals(videoFtpPassword)) {
        isChange = this.saveChangeItemByFieldName(resources, change, FieldNameConstant.CpItem.??????FTP??????.toString(), cpItem.getVideoFtpPassword(), cpItem.getVideoFtpPassword(), videoFtpPassword, videoFtpPassword);
    }
    if (!cpItem.getVideoFtpRootpath().equals(videoFtpRootpath)) {
        isChange = this.saveChangeItemByFieldName(resources, change, FieldNameConstant.CpItem.??????FTP?????????.toString(), cpItem.getVideoFtpRootpath(), cpItem.getVideoFtpRootpath(), videoFtpRootpath, videoFtpRootpath);
    }
    if (!cpItem.getVideoFtpUploadpath().equals(videoFtpUploadpath)) {
        isChange = this.saveChangeItemByFieldName(resources, change, FieldNameConstant.CpItem.??????FTP????????????.toString(), cpItem.getVideoFtpUploadpath(), cpItem.getVideoFtpUploadpath(), videoFtpUploadpath, videoFtpUploadpath);
    }
    if (!cpItem.getVideoOutputGroup().equals(videoOutputGroup)) {
        isChange = this.saveChangeItemByFieldName(resources, change, FieldNameConstant.CpItem.?????????????????????.toString(), cpItem.getVideoOutputGroup(), cpItem.getVideoOutputGroup(), videoOutputGroup, videoOutputGroup);
    }
    if (!cpItem.getVideoOutputWay().equals(videoOutputWay)) {
        String fieldName = FieldNameConstant.CpItem.??????????????????.toString();
        String oldValue = cpItem.getVideoOutputWay();
        String oldString = CPConstant.VideoOutputWay.get(Integer.valueOf(cpItem.getVideoOutputWay()));
        String newValue = videoOutputWay;
        String newString = CPConstant.VideoOutputWay.get(Integer.valueOf(videoOutputWay));
        isChange = this.saveChangeItemByFieldName(resources, change, fieldName, oldValue, oldString, newValue, newString);
    }
    if (!cpItem.getPictrueFtpIp().equals(pictrueFtpIp)) {
        isChange = this.saveChangeItemByFieldName(resources, change, FieldNameConstant.CpItem.??????FTP??????IP.toString(), cpItem.getPictrueFtpIp(), cpItem.getPictrueFtpIp(), pictrueFtpIp, pictrueFtpIp);
    }
    if (!cpItem.getPictrueFtpPort().equals(pictrueFtpPort)) {
        isChange = this.saveChangeItemByFieldName(resources, change, FieldNameConstant.CpItem.????????????.toString(), cpItem.getPictrueFtpPort(), cpItem.getPictrueFtpPort(), pictrueFtpPort, pictrueFtpPort);
    }
    if (!cpItem.getPictrueFtpUsername().equals(pictrueFtpUsername)) {
        isChange = this.saveChangeItemByFieldName(resources, change, FieldNameConstant.CpItem.??????FTP?????????.toString(), cpItem.getPictrueFtpUsername(), cpItem.getPictrueFtpUsername(), pictrueFtpUsername, pictrueFtpUsername);
    }
    if (!cpItem.getPictrueFtpPassword().equals(pictrueFtpPassword)) {
        isChange = this.saveChangeItemByFieldName(resources, change, FieldNameConstant.CpItem.??????FTP??????.toString(), cpItem.getPictrueFtpPassword(), cpItem.getPictrueFtpPassword(), pictrueFtpPassword, pictrueFtpPassword);
    }
    if (!cpItem.getPictrueFtpRootpath().equals(pictrueFtpRootpath)) {
        isChange = this.saveChangeItemByFieldName(resources, change, FieldNameConstant.CpItem.??????FTP?????????.toString(), cpItem.getPictrueFtpRootpath(), cpItem.getPictrueFtpRootpath(), pictrueFtpRootpath, pictrueFtpRootpath);
    }
    if (!cpItem.getPictrueFtpUploadpath().equals(pictrueFtpUploadpath)) {
        isChange = this.saveChangeItemByFieldName(resources, change, FieldNameConstant.CpItem.??????FTP????????????.toString(), cpItem.getPictrueFtpUploadpath(), cpItem.getPictrueFtpUploadpath(), pictrueFtpUploadpath, pictrueFtpUploadpath);
    }
    if (!cpItem.getPictrueOutputGroup().equals(pictrueOutputGroup)) {
        isChange = this.saveChangeItemByFieldName(resources, change, FieldNameConstant.CpItem.?????????????????????.toString(), cpItem.getPictrueOutputGroup(), cpItem.getPictrueOutputGroup(), pictrueOutputGroup, pictrueOutputGroup);
    }
    if (!cpItem.getPictrueOutputMedia().equals(pictrueOutputMedia)) {
        isChange = this.saveChangeItemByFieldName(resources, change, FieldNameConstant.CpItem.??????????????????.toString(), cpItem.getPictrueOutputMedia(), cpItem.getPictrueOutputMedia(), pictrueOutputMedia, pictrueOutputMedia);
    }
    return isChange;
}


public String wrapStringByNetworkElbItem(Integer elbId){
    NetworkElbItem networkElbItem = comm.elbService.getNetworkElbItem(elbId);
    String value = "";
    if (networkElbItem != null) {
        String virtualIp = "";
        if (networkElbItem.getVirtualIp() != null) {
            virtualIp = networkElbItem.getVirtualIp();
        }
        value += networkElbItem.getIdentifier() + "(" + virtualIp + ")";
    }
    return value;
}


public boolean compareApplication(ComputeItem computeItem,String[] applicationNames,String[] applicationVersions,String[] applicationDeployPaths){
    // === OldValue === //
    List<String> oldNameList = new ArrayList<String>();
    List<String> oldVersionList = new ArrayList<String>();
    List<String> oldDeployPathList = new ArrayList<String>();
    List<Application> applications = comm.computeService.getApplicationByComputeItemId(computeItem.getId());
    for (Application application : applications) {
        oldNameList.add(application.getName());
        oldVersionList.add(application.getVersion());
        oldDeployPathList.add(application.getDeployPath());
    }
    // === NewValue === //
    List<String> nameList = new ArrayList<String>();
    List<String> versionList = new ArrayList<String>();
    List<String> deployPathList = new ArrayList<String>();
    for (int i = 0; i < applicationNames.length; i++) {
        nameList.add(applicationNames[i]);
        versionList.add(applicationVersions[i]);
        deployPathList.add(applicationDeployPaths[i]);
    }
    // ??????OldValue???NewValue???List.
    return CollectionUtils.isEqualCollection(nameList, oldNameList) && CollectionUtils.isEqualCollection(versionList, oldVersionList) && CollectionUtils.isEqualCollection(deployPathList, oldDeployPathList) ? false : true;
}


@Override
public boolean compareMdnItem(Resources resources,Change change,MdnItem mdnItem,String coverArea,String coverIsp,String bandwidth){
    boolean isChange = false;
    if (!mdnItem.getCoverArea().equals(coverArea)) {
        // coverArea
        String fieldName = FieldNameConstant.MdnItem.??????????????????.toString();
        String oldValue = mdnItem.getCoverArea();
        String oldString = mdnItem.getCoverArea();
        String newValue = coverArea;
        String newString = coverArea;
        isChange = this.saveChangeItemByFieldName(resources, change, fieldName, oldValue, oldString, newValue, newString);
    }
    if (!mdnItem.getCoverIsp().equals(coverIsp)) {
        // coverIsp
        String fieldName = FieldNameConstant.MdnItem.????????????ISP.toString();
        String oldValue = mdnItem.getCoverIsp();
        String oldString = comm.mdnService.wrapStringByMDNCoverIsp(mdnItem.getCoverIsp());
        String newValue = coverIsp;
        String newString = comm.mdnService.wrapStringByMDNCoverIsp(coverIsp);
        isChange = this.saveChangeItemByFieldName(resources, change, fieldName, oldValue, oldString, newValue, newString);
    }
    if (!mdnItem.getBandwidth().equals(bandwidth)) {
        // coverIsp
        String fieldName = FieldNameConstant.MdnItem.??????????????????.toString();
        String oldValue = mdnItem.getBandwidth();
        String oldString = mdnItem.getBandwidth();
        String newValue = bandwidth;
        String newString = bandwidth;
        isChange = this.saveChangeItemByFieldName(resources, change, fieldName, oldValue, oldString, newValue, newString);
    }
    return isChange;
}


public boolean compareElbPortItem(List<ElbPortItem> elbPortItems,String[] protocols,String[] sourcePorts,String[] targetPorts){
    // === OldValue === //
    List<String> oldProtocolList = new ArrayList<String>();
    List<String> oldSourcePortList = new ArrayList<String>();
    List<String> oldTargetPortList = new ArrayList<String>();
    for (ElbPortItem elbPortItem : elbPortItems) {
        oldProtocolList.add(elbPortItem.getProtocol());
        oldSourcePortList.add(elbPortItem.getSourcePort());
        oldTargetPortList.add(elbPortItem.getTargetPort());
    }
    // === NewValue === //
    List<String> protocolList = new ArrayList<String>();
    List<String> sourcePortList = new ArrayList<String>();
    List<String> targetPortList = new ArrayList<String>();
    if (sourcePorts != null && sourcePorts.length > 0) {
        for (int i = 0; i < protocols.length; i++) {
            protocolList.add(protocols[i]);
            sourcePortList.add(sourcePorts[i]);
            targetPortList.add(targetPorts[i]);
        }
    }
    // ??????OldValue???NewValue???List.
    return CollectionUtils.isEqualCollection(protocolList, oldProtocolList) && CollectionUtils.isEqualCollection(sourcePortList, oldSourcePortList) && CollectionUtils.isEqualCollection(targetPortList, oldTargetPortList) ? false : true;
}


@Override
public boolean compareElb(Resources resources,Change change,NetworkElbItem networkElbItem,List<ElbPortItem> elbPortItems,String keepSession,String[] protocols,String[] sourcePorts,String[] targetPorts,String[] computeIds){
    /**
     * ???????????????????????????????????????????????? compareCompute??????
     */
    // Step.1????????????boolean?????????flag.????????????????????????????????????.
    boolean flag = false;
    boolean isChange = false;
    // ????????????????????????computeId?????????","????????????,????????????????????????????????????.
    String oldId = Collections3.extractToString(networkElbItem.getComputeItemList(), "id", ",");
    String newId = computeIds != null ? StringUtils.join(computeIds, ",") : "";
    if (!oldId.equals(newId)) {
        String fieldName = FieldNameConstant.Elb.????????????.toString();
        String oldValue = oldId;
        String oldString = networkElbItem.getMountComputes();
        // ??????computeIds??????compute???List,??????????????????.
        List<ComputeItem> list = new ArrayList<ComputeItem>();
        if (computeIds != null) {
            for (int i = 0; i < computeIds.length; i++) {
                ComputeItem computeItem = comm.computeService.getComputeItem(Integer.valueOf(computeIds[i]));
                list.add(computeItem);
            }
        }
        String newValue = newId;
        String newString = StorageItem.extractToString(list);
        isChange = this.saveChangeItemByFieldName(resources, change, fieldName, oldValue, oldString, newValue, newString);
        flag = isChange;
    }
    // ????????????
    if (!networkElbItem.getKeepSession().toString().equals(keepSession)) {
        String fieldName = FieldNameConstant.Elb.??????????????????.toString();
        String oldValue = networkElbItem.getKeepSession().toString();
        String oldString = NetworkConstant.KeepSession.get(networkElbItem.getKeepSession());
        String newValue = keepSession;
        String newString = NetworkConstant.KeepSession.get(NetworkConstant.KeepSession.??????.toString().equals(keepSession) ? true : false);
        isChange = this.saveChangeItemByFieldName(resources, change, fieldName, oldValue, oldString, newValue, newString);
    }
    // ????????????
    if (this.compareElbPortItem(elbPortItems, protocols, sourcePorts, targetPorts)) {
        String fieldName = FieldNameConstant.Elb.????????????.toString();
        String oldValue = this.wrapElbPortItemFromNetworkElbItemToString(networkElbItem);
        String oldString = this.wrapElbPortItemFromNetworkElbItemToString(networkElbItem);
        String newValue = "";
        String newString = this.wrapPortItemToString(protocols, sourcePorts, targetPorts);
        isChange = this.saveChangeItemByFieldName(resources, change, fieldName, oldValue, oldString, newValue, newString);
    }
    // Step.2 ????????????????????????,???????????????????????????,????????????????????????????????????????????????,?????????????????????????????????ESG.
    if (isChange && !flag) {
        String fieldName = FieldNameConstant.Elb.????????????.toString();
        String oldValue = oldId;
        String oldString = networkElbItem.getMountComputes();
        // ??????computeIds??????compute???List,??????????????????.
        List<ComputeItem> list = new ArrayList<ComputeItem>();
        if (computeIds != null) {
            for (int i = 0; i < computeIds.length; i++) {
                ComputeItem computeItem = comm.computeService.getComputeItem(Integer.valueOf(computeIds[i]));
                list.add(computeItem);
            }
        }
        String newValue = newId;
        String newString = StorageItem.extractToString(list);
        this.saveChangeItemByFieldName(resources, change, fieldName, oldValue, oldString, newValue, newString);
    }
    return isChange;
}


public String wrapEipPortItemFromNetworkEipItemToString(NetworkEipItem networkEipItem){
    StringBuilder sb = new StringBuilder();
    List<EipPortItem> eipPortItems = comm.eipService.getEipPortItemListByEipId(networkEipItem.getId());
    for (EipPortItem eipPortItem : eipPortItems) {
        sb.append(eipPortItem.getProtocol()).append(",").append(eipPortItem.getSourcePort()).append(",").append(eipPortItem.getTargetPort()).append("<br>");
    }
    return sb.toString();
}


}
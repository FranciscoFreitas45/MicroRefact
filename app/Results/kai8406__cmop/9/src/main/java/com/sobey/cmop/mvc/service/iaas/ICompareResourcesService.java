package com.sobey.cmop.mvc.service.iaas;
 import java.util.List;
import com.sobey.cmop.mvc.entity.Change;
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
import com.sobey.cmop.mvc.entity.Resources;
import com.sobey.cmop.mvc.entity.StorageItem;
public interface ICompareResourcesService {


public boolean compareMonitorElb(Resources resources,Change change,MonitorElb monitorElb,Integer elbId)
;

public boolean compareStorage(Resources resources,Change change,StorageItem storageItem,Integer storageType,Integer space,String[] computeIds)
;

public boolean compareMdnVodItem(Resources resources,Change change,MdnVodItem mdnVodItem,String vodDomain,String vodProtocol,String sourceOutBandwidth,String sourceStreamerUrl)
;

public boolean compareMonitorCompute(Resources resources,Change change,MonitorCompute monitorCompute,String ipAddress,String cpuWarn,String cpuCritical,String memoryWarn,String memoryCritical,String pingLossWarn,String pingLossCritical,String diskWarn,String diskCritical,String pingDelayWarn,String pingDelayCritical,String maxProcessWarn,String maxProcessCritical,String port,String process,String mountPoint)
;

public boolean compareCompute(Resources resources,Change change,ComputeItem computeItem,Integer osType,Integer osBit,Integer serverType,String[] esgIds,String remark,String[] applicationNames,String[] applicationVersions,String[] applicationDeployPaths)
;

public boolean compareCP(Resources resources,Change change,CpItem cpItem,String recordStreamUrl,String recordBitrate,String exportEncode,Integer recordType,String recordTime,Integer recordDuration,String publishUrl,String isPushCtp,String videoFtpIp,String videoFtpPort,String videoFtpUsername,String videoFtpPassword,String videoFtpRootpath,String videoFtpUploadpath,String videoOutputGroup,String videoOutputWay,String pictrueFtpIp,String pictrueFtpPort,String pictrueFtpUsername,String pictrueFtpPassword,String pictrueFtpRootpath,String pictrueFtpUploadpath,String pictrueOutputGroup,String pictrueOutputMedia)
;

public boolean compareMdnItem(Resources resources,Change change,MdnItem mdnItem,String coverArea,String coverIsp,String bandwidth)
;

public boolean compareMdnLiveItem(Resources resources,Change change,MdnLiveItem mdnLiveItem,String bandwidth,String name,String guid,String liveBandwidth,String liveProtocol,Integer streamOutMode,Integer encoderMode,String httpUrlEncoder,String httpBitrateEncoder,String hlsUrlEncoder,String hlsBitrateEncoder,String httpUrl,String httpBitrate,String hlsUrl,String hlsBitrate)
;

public boolean compareDns(Resources resources,Change change,NetworkDnsItem networkDnsItem,String domainName,Integer domainType,String cnameDomain,String[] eipIds)
;

public boolean compareElb(Resources resources,Change change,NetworkElbItem networkElbItem,List<ElbPortItem> elbPortItems,String keepSession,String[] protocols,String[] sourcePorts,String[] targetPorts,String[] computeIds)
;

public boolean compareEip(Resources resources,Change change,NetworkEipItem networkEipItem,List<EipPortItem> eipPortItems,String linkType,String linkId,String[] protocols,String[] sourcePorts,String[] targetPorts)
;

}
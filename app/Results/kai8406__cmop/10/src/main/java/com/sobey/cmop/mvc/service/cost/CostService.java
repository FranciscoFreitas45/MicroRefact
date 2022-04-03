package com.sobey.cmop.mvc.service.cost;
 import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sobey.cmop.mvc.comm.BaseSevcie;
import com.sobey.cmop.mvc.constant.CPConstant;
import com.sobey.cmop.mvc.constant.ComputeConstant;
import com.sobey.cmop.mvc.constant.CostingConstant;
import com.sobey.cmop.mvc.constant.NetworkConstant;
import com.sobey.cmop.mvc.constant.RedmineConstant;
import com.sobey.cmop.mvc.constant.StorageConstant;
import com.sobey.cmop.mvc.entity.Apply;
import com.sobey.cmop.mvc.entity.ComputeItem;
import com.sobey.cmop.mvc.entity.CpItem;
import com.sobey.cmop.mvc.entity.MdnItem;
import com.sobey.cmop.mvc.entity.NetworkEipItem;
import com.sobey.cmop.mvc.entity.StorageItem;
import com.sobey.cmop.mvc.service.onecmdb.OneCmdbService;
import com.sobey.framework.utils.MathsUtil;
import com.sobey.cmop.mvc.DTO.Apply;
import com.sobey.cmop.mvc.DTO.CpItem;
import com.sobey.cmop.mvc.DTO.MdnItem;
@Service
@Transactional(readOnly = true)
public class CostService extends BaseSevcie{

 public  int RESOURCE_COUNT;


public double cpCost(Apply apply,double workTime){
    double price = 0;
    if (!apply.getCpItems().isEmpty()) {
        double recordServerCosting = Double.valueOf(OneCmdbService.findCiBeanByAlias(CostingConstant.Costing.收录服务器硬件单位成本.toString()).getDescription());
        double partitionTime = Double.valueOf(OneCmdbService.findCiBeanByAlias(CostingConstant.Costing.拆条时长.toString()).getDescription());
        double transcodingCosting = Double.valueOf(OneCmdbService.findCiBeanByAlias(CostingConstant.Costing.转码服务器硬件单位成本.toString()).getDescription());
        double humanCosting = Double.valueOf(OneCmdbService.findCiBeanByAlias(CostingConstant.Costing.拆条人工单位成本.toString()).getDescription());
        double platformCostring = Double.valueOf(OneCmdbService.findCiBeanByAlias(CostingConstant.Costing.云平台各项服务成本.toString()).getDescription());
        double es3Costing = Double.valueOf(OneCmdbService.findCiBeanByAlias(CostingConstant.Costing.ES3.toString()).getDescription());
        for (CpItem cpItem : apply.getCpItems()) {
            double recordBitrate = getRecordBitrateForK(cpItem);
            double recordTime = getRecordTimeForHours(cpItem);
            // CP核算成本公式:每月服务成本 = 收录时长 × 收录服务器硬件单位成本 + 拆条时长 ×（转码服务器硬件单位成本 +
            // 拆条人工单位成本） +
            // 收录码率 × 收录时长 × 25200 × 云平台各项服务成本 ÷ 8388608 - ES3
            // 收录
            double record = MathsUtil.mul(recordTime, recordServerCosting);
            // 拆条
            double partition = MathsUtil.mul(partitionTime, MathsUtil.add(transcodingCosting, humanCosting));
            double other1 = MathsUtil.mul(recordBitrate, recordTime);
            // 合并 3600 ×七天
            other1 = MathsUtil.mul(other1, 25200);
            other1 = MathsUtil.mul(other1, platformCostring);
            // 合并 1024 ÷ 1024 ÷ 8
            other1 = MathsUtil.div(other1, 8388608);
            price = MathsUtil.add(price, record);
            price = MathsUtil.add(price, partition);
            price = MathsUtil.add(price, other1);
            price = MathsUtil.sub(price, es3Costing);
        }
        price = MathsUtil.mul(price, workTime);
    }
    return price;
}


public String costPrice(Apply apply){
    StringBuilder sb = new StringBuilder();
    double workTime = this.applyDaysBetween(apply);
    double intiCost = this.humanCost(apply, workTime);
    double ecsCost = this.computeCost(apply, workTime);
    double es3Cost = this.es3Cost(apply, workTime);
    double elbCost = this.elbCost(apply, workTime);
    double eipCost = this.eipCost(apply, workTime);
    double dnsCost = this.dnsCost(apply, workTime);
    double mdnCost = this.mdnCost(apply, workTime);
    double cpCost = this.cpCost(apply, workTime);
    double totalPrice = this.totalCost(intiCost, ecsCost, es3Cost, elbCost, eipCost, dnsCost, mdnCost, cpCost);
    if (intiCost != 0) {
        sb.append("人工成本&nbsp;:").append(intiCost).append("&nbsp;元").append("<br>");
    }
    if (ecsCost != 0) {
        sb.append("ECS成本&nbsp;:").append(ecsCost).append("&nbsp;元").append("<br>");
    }
    if (es3Cost != 0) {
        sb.append("ES3成本&nbsp;:").append(es3Cost).append("&nbsp;元").append("<br>");
    }
    if (elbCost != 0) {
        sb.append("ELB成本&nbsp;:").append(elbCost).append("&nbsp;元").append("<br>");
    }
    if (eipCost != 0) {
        sb.append("EIP成本&nbsp;:").append(eipCost).append("&nbsp;元").append("<br>");
    }
    if (dnsCost != 0) {
        sb.append("DNS成本&nbsp;:").append(dnsCost).append("&nbsp;元").append("<br>");
    }
    if (mdnCost != 0) {
        sb.append("MDN成本&nbsp;:").append(mdnCost).append("&nbsp;元").append("<br>");
    }
    if (cpCost != 0) {
        sb.append("云生产成本&nbsp;:").append(cpCost).append("&nbsp;元").append("<br>");
    }
    sb.append("总成本&nbsp;:<strong class='text-error'>").append(totalPrice).append("</strong>&nbsp;元").append("<br>");
    return sb.toString();
}


public Double humanCost(Apply apply,double workTime){
    double price = Double.valueOf(OneCmdbService.findCiBeanByAlias(CostingConstant.Costing.服务人力成本.toString()).getDescription());
    Integer priority = apply.getPriority();
    // (“高”和“紧急”分别乘以2和3倍。)
    if (RedmineConstant.Priority.高.toInteger().equals(priority)) {
        price = MathsUtil.mul(price, 2);
    } else if (RedmineConstant.Priority.紧急.toInteger().equals(priority)) {
        price = MathsUtil.mul(price, 3);
    } else {
        // 普通人工不收费，其他收费.
        price = 0;
    }
    return price;
}


public double applyDaysBetween(Apply apply){
    /**
     * 默认的每月天数
     */
    int DEFAULT_DAY_NUMBER = 30;
    DateTime startTime = new DateTime(apply.getServiceStart());
    DateTime endTime = new DateTime(apply.getServiceEnd());
    double costTime = MathsUtil.div(Days.daysBetween(startTime, endTime).getDays(), DEFAULT_DAY_NUMBER, 2);
    // 如果是当天,时间差算成1天.
    return costTime == 0 ? 0.04 : costTime;
}


public double getRecordBitrateForK(CpItem cpItem){
    double bitrate = 0;
    if (cpItem.getRecordBitrate().equals("1")) {
        bitrate = 800;
    } else if (cpItem.getRecordBitrate().equals("2")) {
        bitrate = 1024;
    } else if (cpItem.getRecordBitrate().equals("3")) {
        bitrate = 2048;
    } else if (cpItem.getRecordBitrate().equals("4")) {
        bitrate = 4816;
    } else {
        // 默认 800K
        bitrate = 800;
    }
    return bitrate;
}


public Double elbCost(double workTime,int number){
    double price = 0;
    // ELB核算成本公式:每月服务成本=云平台各项服务成本 - EFW
    double platformPrice = Double.valueOf(OneCmdbService.findCiBeanByAlias(CostingConstant.Costing.云平台各项服务成本.toString()).getDescription());
    double EFWPrice = Double.valueOf(OneCmdbService.findCiBeanByAlias(CostingConstant.Costing.EFW.toString()).getDescription());
    price = MathsUtil.mul(number, MathsUtil.sub(platformPrice, EFWPrice));
    return MathsUtil.mul(price, workTime);
}


public Double dnsCost(double workTime,int number){
    double price = 0;
    // DNS核算成本公式:每月服务成本=云平台各项服务成本 - DNS
    double platformPrice = Double.valueOf(OneCmdbService.findCiBeanByAlias(CostingConstant.Costing.云平台各项服务成本.toString()).getDescription());
    double dnsPrice = Double.valueOf(OneCmdbService.findCiBeanByAlias(CostingConstant.Costing.DNS.toString()).getDescription());
    price = MathsUtil.mul(number, MathsUtil.sub(platformPrice, dnsPrice));
    return MathsUtil.mul(price, workTime);
}


public Double computeCost(ComputeConstant.ECSServerType serverType,double workTime,int number){
    double price = 0;
    if (ComputeConstant.ECSServerType.Small_CPUx1_Memoryx1GB_DISKx20GB.toInteger().equals(serverType.toInteger())) {
        price = MathsUtil.mul(number, Double.valueOf(OneCmdbService.findCiBeanByAlias(CostingConstant.Costing.Small服务器单价.toString()).getDescription()));
    } else if (ComputeConstant.ECSServerType.Middle_CPUx2_Memoryx2GB_DISKx20GB.toInteger().equals(serverType.toInteger())) {
        price = MathsUtil.mul(number, Double.valueOf(OneCmdbService.findCiBeanByAlias(CostingConstant.Costing.Middle服务器单价.toString()).getDescription()));
    } else if (ComputeConstant.ECSServerType.Large_CPUx4_Memoryx4GB_DISKx20GB.toInteger().equals(serverType.toInteger())) {
        price = MathsUtil.mul(number, Double.valueOf(OneCmdbService.findCiBeanByAlias(CostingConstant.Costing.Large服务器单价.toString()).getDescription()));
    }
    return MathsUtil.mul(price, workTime);
}


public double getRecordTimeForHours(CpItem cpItem){
    double time = 0;
    if (CPConstant.RecordType.每天.toInteger().equals(cpItem.getRecordType())) {
        time = Double.valueOf(cpItem.getRecordDuration());
    } else if (CPConstant.RecordType.每周.toInteger().equals(cpItem.getRecordType())) {
        time = MathsUtil.mul(7, Double.valueOf(cpItem.getRecordDuration()));
    } else {
        // 每月
        time = MathsUtil.mul(168, Double.valueOf(cpItem.getRecordDuration()));
    }
    return time;
}


public Double es3Cost(StorageConstant.StorageType storageType,double workTime,double space){
    double price = 0;
    if (StorageConstant.StorageType.Netapp_高IOPS.toInteger().equals(storageType.toInteger())) {
        price = MathsUtil.mul(space, Double.valueOf(OneCmdbService.findCiBeanByAlias(CostingConstant.Costing.业务存储单价.toString()).getDescription()));
    } else {
        price = MathsUtil.mul(space, Double.valueOf(OneCmdbService.findCiBeanByAlias(CostingConstant.Costing.数据存储单价.toString()).getDescription()));
    }
    return MathsUtil.mul(price, workTime);
}


public Double eipCost(NetworkConstant.ISPType type,double workTime,int number){
    double price = 0;
    /**
     * 接入速率
     */
    double accessRate = Double.valueOf(OneCmdbService.findCiBeanByAlias(CostingConstant.CostingParameter.接入速率.toString()).getDescription());
    /**
     * 双线
     */
    double bandwidthRate = Double.valueOf(OneCmdbService.findCiBeanByAlias(CostingConstant.Costing.双线.toString()).getDescription());
    /**
     * 单价
     */
    double ispPrice = 0;
    if (NetworkConstant.ISPType.中国电信.toInteger().equals(type.toInteger())) {
        ispPrice = Double.valueOf(OneCmdbService.findCiBeanByAlias(CostingConstant.Costing.电信带宽单价.toString()).getDescription());
    } else if (NetworkConstant.ISPType.中国联通.toInteger().equals(type.toInteger())) {
        ispPrice = Double.valueOf(OneCmdbService.findCiBeanByAlias(CostingConstant.Costing.联通带宽单价.toString()).getDescription());
    } else {
    // TODO 中国移动
    }
    // EIP核算成本公式:每月服务成本=(接入速率 × 电信带宽单价 + 接入速率 × 联通带宽单价)+(电信IP数量 × 电信带宽单价 + 联通IP数量 × 联通带宽单价)
    // 带宽成本 + IP成本
    price = MathsUtil.add(MathsUtil.mul(accessRate, bandwidthRate), MathsUtil.mul(number, ispPrice));
    return MathsUtil.mul(price, workTime);
}


public double mdnCost(Apply apply,double workTime){
    // MDN 核算成本公式: 每月服务成本 = 每M带宽占用 x 带宽
    double price = 0;
    if (!apply.getMdnItems().isEmpty()) {
        double bandwidthPrice = Double.valueOf(OneCmdbService.findCiBeanByAlias(CostingConstant.Costing.每M带宽占用.toString()).getDescription());
        for (MdnItem mdnItem : apply.getMdnItems()) {
            price = MathsUtil.mul(bandwidthPrice, Double.valueOf(mdnItem.getBandwidth()));
        }
        price = MathsUtil.mul(price, workTime);
    }
    return price;
}


public double totalCost(double intiCost,double ecsCost,double es3Cost,double elbCost,double eipCost,double dnsCost,double mdnCost,double cpCost){
    double totalPrice = 0;
    totalPrice = MathsUtil.add(totalPrice, intiCost);
    totalPrice = MathsUtil.add(totalPrice, ecsCost);
    totalPrice = MathsUtil.add(totalPrice, es3Cost);
    totalPrice = MathsUtil.add(totalPrice, elbCost);
    totalPrice = MathsUtil.add(totalPrice, eipCost);
    totalPrice = MathsUtil.add(totalPrice, dnsCost);
    totalPrice = MathsUtil.add(totalPrice, mdnCost);
    totalPrice = MathsUtil.add(totalPrice, cpCost);
    return totalPrice;
}


}
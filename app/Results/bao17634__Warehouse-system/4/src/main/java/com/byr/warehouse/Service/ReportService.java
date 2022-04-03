package com.byr.warehouse.Service;
 import com.byr.warehouse.dao.ApplyEnterRepository;
import com.byr.warehouse.dao.ApplyOutPutRepository;
import com.byr.warehouse.dao.EntrepotStatusRepository;
import com.byr.warehouse.dao.RelationShipRepository;
import com.byr.warehouse.pojo;
import com.byr.warehouse.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.byr.warehouse.Interface.EntrepotStatusRepository;
import com.byr.warehouse.Interface.RelationShipRepository;
import com.byr.warehouse.Interface.ApplyOutPutRepository;
import com.byr.warehouse.DTO.StockHUB;
@Service
public class ReportService {

@Autowired
 private EntrepotStatusRepository entrepotStatusRepository;

@Autowired
 private RelationShipRepository relationShipRepository;

@Autowired
 private ApplyEnterRepository applyEnterRepository;

@Autowired
 private ApplyOutPutRepository applyOutPutRepository;


public List<OutReport> generateOutputReport(Date startDate,Date endDate){
    List<ApplyOutPut> betweenDays = applyOutPutRepository.findBetweenDays(startDate, endDate);
    List<OutReport> reports = new ArrayList<OutReport>();
    if (betweenDays.size() == 0) {
        throw new Exception("没找到相应库存");
    }
    for (ApplyOutPut report : betweenDays) {
        ApplyEnter enter = applyEnterRepository.findApplyEnterByEnterCodeAndMaterialCode(report.getEnterCode(), report.getMaterialCode()).get(0);
        OutReport outReport = new OutReport();
        outReport.setDemandName(report.getDemandName());
        outReport.setEnterCode(report.getEnterCode());
        outReport.setEnterProductName(enter.getProductName());
        outReport.setGoodsFrom(enter.getGoodsFrom());
        outReport.setMaterialCode(enter.getMaterialCode());
        outReport.setOutCode(report.getOutCode());
        outReport.setOutDate(DateUtil.dateToString(report.getApplyDate()));
        outReport.setOutProductName(enter.getProductName());
        outReport.setOutMaterialCode(report.getOutMaterialCode());
        outReport.setProduceDate(enter.getProduceDate());
        outReport.setGoodsSize(report.getSize());
        outReport.setGoodsSpec(enter.getSpec());
        // outReport.setUnit();
        reports.add(outReport);
    }
    return reports;
}


public List<StockTheWay> generateOnSendReport(Date date){
    List<StockTheWay> stores = new ArrayList<StockTheWay>();
    // StockTheWay stockTheWay = new StockTheWay();
    List<ApplyEnter> enterBeforeDate = applyEnterRepository.findBeforeDate(date);
    System.out.println("要生成报表的库存:" + enterBeforeDate);
    // TODO 产生报表记录，这里使用比较笨的方法，以后再优化
    for (ApplyEnter status : enterBeforeDate) {
        StockTheWay stockTheWay = new StockTheWay();
        stockTheWay.setSupplierName(status.getGoodsFrom());
        stockTheWay.setProdate(status.getProduceDate());
        stockTheWay.setSupplyMateriCode(status.getMaterialCode());
        stockTheWay.setStockTheway(status.getNumber() + "");
        stockTheWay.setWeixinCode(status.getSpec());
        // 维信料号就是出库料号
        // 1.根据供货商名查询供提关系找到供货商编码
        List<RelationShip> codes = relationShipRepository.findRelationShipsBysupplyName(status.getGoodsFrom());
        if (codes.size() > 0) {
            stockTheWay.setVendorCode(codes.get(0).getSupplyCode());
            System.out.println("找到供货商编号");
            stores.add(stockTheWay);
        } else {
            throw new Exception("供货商" + status.getGoodsFrom() + "没有在供、提关系中设置");
        }
    // VendorCode就是供货商编号
    // 2.VendorCode暂时有疑问，暂不处理，先使用默认值
    // stockTheWay.setWeixinCode("维信料号暂未存储");
    }
    return stores;
}


public List<EnterReport> generateEnterReport(Date startDate,Date endDate){
    List<ApplyEnter> betweenDays = applyEnterRepository.findBetweenDays(startDate, endDate);
    ArrayList<EnterReport> reports = new ArrayList<EnterReport>();
    for (ApplyEnter enter : betweenDays) {
        EnterReport report = new EnterReport();
        report.setApplyDate(enter.getApplyDate());
        report.setBillNumber(enter.getBillNumber());
        report.setEnterCode(enter.getEnterCode());
        report.setGoodsFrom(enter.getGoodsFrom());
        report.setMaterialCode(enter.getMaterialCode());
        report.setGoodsNumber(enter.getNumber());
        report.setProduceDate(enter.getProduceDate());
        report.setRoughWeight(enter.getRoughWeight());
        report.setProductName(enter.getProductName());
        report.setGoodsSpec(enter.getSpec());
        report.setSuitNumber(enter.getSuitNumber());
        report.setSuttleWeight(enter.getSuttleWeight());
        // report.setUnit(enter.getUnit());
        reports.add(report);
    }
    return reports;
}


public List<StockHUB> generateStoreReoport(Date date){
    List<StockHUB> stores = new ArrayList<StockHUB>();
    List<EntrepotStatus> entrepotBeforeDate = entrepotStatusRepository.findBeforeDate(date);
    System.out.println("要生成报表的库存:" + entrepotBeforeDate);
    // TODO 产生报表记录，这里使用比较笨的方法，以后再优化
    for (EntrepotStatus status : entrepotBeforeDate) {
        StockHUB stockHUB = new StockHUB();
        stockHUB.setSupplierName(status.getSupplyName());
        stockHUB.setGoodNum(status.getTotalSize() + "");
        stockHUB.setStorageDate(DateUtil.dateToString(status.getEntranceDate()));
        stockHUB.setSupplyMateriCode(status.getMaterialCode());
        stockHUB.setProdate(status.getProduceDate());
        stockHUB.setWeixinCode(status.getMaterialSpec());
        // 维信料号就是出库料号
        // 1.根据供货商名查询供提关系找到供货商编码
        List<RelationShip> codes = relationShipRepository.findRelationShipsBysupplyName(status.getSupplyName());
        if (codes.size() > 0) {
            stockHUB.setVendorCode(codes.get(0).getSupplyCode());
            System.out.println("找到供货商编号");
            stores.add(stockHUB);
        } else {
            throw new Exception("供货商【" + status.getSupplyName() + "】没有在供、提关系中设置");
        }
    // VendorCode就是供货商编号
    // 2.VendorCode暂时有疑问，暂不处理，先使用默认值
    }
    return stores;
}


}
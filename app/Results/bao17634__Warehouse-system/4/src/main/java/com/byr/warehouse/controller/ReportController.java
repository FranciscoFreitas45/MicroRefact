package com.byr.warehouse.controller;
 import com.byr.warehouse.Service.EntrepotStatusService;
import com.byr.warehouse.Service.ExcelService;
import com.byr.warehouse.Service.ReportService;
import com.byr.warehouse.dao.ApplyEnterRepository;
import com.byr.warehouse.dao.ApplyOutPutRepository;
import com.byr.warehouse.dao.EntrepotStatusRepository;
import com.byr.warehouse.dao.RelationShipRepository;
import com.byr.warehouse.pojo;
import com.byr.warehouse.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.byr.warehouse.Interface.ApplyOutPutRepository;
import com.byr.warehouse.Interface.EntrepotStatusRepository;
import com.byr.warehouse.Interface.ExcelService;
import com.byr.warehouse.Interface.RelationShipRepository;
import com.byr.warehouse.Interface.EntrepotStatusService;
@Controller
public class ReportController {

 private Logger logger;

@Autowired
 private ApplyEnterRepository applyEnterRepository;

@Autowired
 private ApplyOutPutRepository applyOutPutRepository;

@Autowired
 private EntrepotStatusRepository entrepotStatusRepository;

@Autowired
 private ReportService reportService;

@Autowired
 private ExcelService excelService;

@Autowired
 private RelationShipRepository relationShipRepository;

@Autowired
 private EntrepotStatusService entrepotStatusService;


@RequestMapping("/report-enterReportExcel")
public void enterReportExcel(String startDate,String endDate,HttpServletResponse response){
    List<EnterReport> outReports = null;
    try {
        System.out.print("导出入库报表");
        outReports = reportService.generateEnterReport(DateUtil.stringToDate(startDate), DateUtil.stringToDate(endDate));
        excelService.ExportEcelService(outReports, "入库报表[" + startDate + "-" + endDate + "]", "出库报表", response, "入库报表" + startDate + "-" + endDate + ".xls", EnterReport.class);
    } catch (IllegalAccessException e) {
        e.printStackTrace();
    } catch (InstantiationException e) {
        e.printStackTrace();
    } catch (Exception e) {
        e.printStackTrace();
    }
}


@RequestMapping("/report-makeOnSendReport")
public String makeOnSendReport(String endDate,ModelMap modelMap){
    logger.debug("开始生成在途报表===================");
    String page = "report_on_send";
    Date date = DateUtil.stringToDate(endDate);
    logger.debug("截止时间：" + DateUtil.dateToString(date));
    List<StockTheWay> beforeDate = reportService.generateOnSendReport(date);
    logger.debug("报表中的信息:" + beforeDate);
    modelMap.addAttribute("report", beforeDate);
    modelMap.addAttribute("date", endDate);
    logger.debug("报表在途生成结束===================");
    return page;
}


@RequestMapping("/report-makeOutputReport")
public String makeOutputReport(String startDate,String endDate,ModelMap modelMap){
    String page = "report_exit_info";
    List<OutReport> applyOutPuts = reportService.generateOutputReport(DateUtil.stringToDate(startDate), DateUtil.stringToDate(endDate));
    modelMap.addAttribute("startDate", startDate);
    modelMap.addAttribute("endDate", endDate);
    if (applyOutPuts.size() == 0) {
        modelMap.addAttribute("report", "");
        return page;
    }
    System.err.println("出库报表生成" + applyOutPuts);
    modelMap.addAttribute("report", applyOutPuts);
    return page;
}


@RequestMapping("/report-exitInfo")
public String toEnterPage(){
    String page = "report_exit_info";
    return page;
}


@RequestMapping("/report-onSendReportToExcel")
public void onSendReportToExcel(String date,HttpServletResponse response){
    List<StockTheWay> stockTheWays = null;
    try {
        System.out.print("导出在途报表");
        stockTheWays = reportService.generateOnSendReport(DateUtil.stringToDate(date));
        excelService.ExportEcelService(stockTheWays, "货物在途报表[" + date + "]", "货物在途报表", response, "货物在途报表" + date + ".xls", StockTheWay.class);
    } catch (IllegalAccessException e) {
        e.printStackTrace();
    } catch (InstantiationException e) {
        e.printStackTrace();
    } catch (Exception e) {
        e.printStackTrace();
    }
}


@RequestMapping("/report-store")
public String toExitPage(){
    String page = "report_stores";
    return page;
}


@RequestMapping("/report-makeEnterReport")
public String makeEnterReport(String startDate,String endDate,ModelMap modelMap){
    String page = "report_entrance_info";
    List<EnterReport> applyOutPuts = reportService.generateEnterReport(DateUtil.stringToDate(startDate), DateUtil.stringToDate(endDate));
    modelMap.addAttribute("startDate", startDate);
    modelMap.addAttribute("endDate", endDate);
    if (applyOutPuts.size() == 0) {
        modelMap.addAttribute("report", "");
        return page;
    }
    System.err.println("入库报表生成" + applyOutPuts);
    modelMap.addAttribute("report", applyOutPuts);
    return page;
}


@RequestMapping("/report-makeStoreReport")
public String makeStoreReport(String endDate,String goodsFrom,ModelMap modelMap){
    logger.debug("开始生成报表===================");
    // 日期格式：2018-11-04
    String page = "report_stores";
    List<StockHUB> stockHUBS = new ArrayList<>();
    Date date = DateUtil.stringToDate(endDate);
    logger.debug("截止时间" + DateUtil.dateToString(date));
    List<StockHUB> beforeDate = reportService.generateStoreReoport(date);
    if (StringUtils.isEmpty(goodsFrom.trim())) {
        stockHUBS = beforeDate;
    } else {
        for (StockHUB stockHUB : beforeDate) {
            if (stockHUB.getSupplierName().equals(goodsFrom.trim())) {
                stockHUBS.add(stockHUB);
            }
        }
    }
    logger.debug("报表中的信息:" + beforeDate);
    modelMap.addAttribute("report", stockHUBS);
    modelMap.addAttribute("date", endDate);
    logger.debug("报表生成结束===================");
    return page;
}


@RequestMapping("/report-outputReportExcel")
public void outputReportExcel(String startDate,String endDate,HttpServletResponse response){
    List<OutReport> outReports = null;
    try {
        System.out.print("导出出库报表");
        outReports = reportService.generateOutputReport(DateUtil.stringToDate(startDate), DateUtil.stringToDate(endDate));
        excelService.ExportEcelService(outReports, "出库报表[" + startDate + "-" + endDate + "]", "出库报表", response, "出库报表" + startDate + "-" + endDate + ".xls", OutReport.class);
    } catch (IllegalAccessException e) {
        e.printStackTrace();
    } catch (InstantiationException e) {
        e.printStackTrace();
    } catch (Exception e) {
        e.printStackTrace();
    }
}


@RequestMapping("/report-onSend")
public String toSendPage(){
    String page = "report_on_send";
    return page;
}


@RequestMapping("/report-toSendForm")
public String toSendFormPage(){
    String page = "send_goods_form";
    return page;
}


@RequestMapping("/report-entranceInfo")
public String toStorePage(){
    String page = "report_entrance_info";
    return page;
}


@RequestMapping("report-generateSendForm")
public String generateSendForm(String outCode,ModelMap modelMap,HttpSession session){
    List<ApplyOutPut> allByOutCode = applyOutPutRepository.findAllByOutCode(outCode);
    if (null == allByOutCode || allByOutCode.size() == 0) {
        session.setAttribute("outCode", outCode);
        session.setAttribute("message", "查询不到出仓编号未" + outCode + "的货物");
        return "send_goods_form";
    }
    int sum = 0;
    for (ApplyOutPut applyOutPut : allByOutCode) {
        sum = sum + applyOutPut.getSize();
    }
    // 一、查找VendorCode，即供应商编号
    // 1.查找供应商名称
    List<ApplyEnter> vendorCodes = applyEnterRepository.findApplyEnterByEnterCodeAndMaterialCode(allByOutCode.get(0).getEnterCode(), allByOutCode.get(0).getMaterialCode());
    // 库别
    modelMap.addAttribute("type", vendorCodes.get(0).getTreasury());
    // 2.根据供应商名称找代码
    List<RelationShip> ships = relationShipRepository.findRelationShipsBysupplyName(vendorCodes.get(0).getGoodsFrom());
    // 供货商代码
    modelMap.addAttribute("vendor", ships.get(0).getSupplyCode());
    // 客户
    modelMap.addAttribute("customer", allByOutCode.get(0).getDemandName());
    // 厂商名称
    modelMap.addAttribute("supply", vendorCodes.get(0).getGoodsFrom());
    // 日期
    modelMap.addAttribute("date", DateUtil.dateToString(new Date()));
    modelMap.addAttribute("tickets", allByOutCode);
    modelMap.addAttribute("size", sum);
    return "send_goods_form";
}


@RequestMapping("/report-onStoreReportToExcel")
public void onSoredReportToExcel(String date,HttpServletResponse response){
    List<StockHUB> stockTheWays = null;
    try {
        System.out.print("导出库存报表");
        stockTheWays = reportService.generateStoreReoport(DateUtil.stringToDate(date));
        excelService.ExportEcelService(stockTheWays, "货物库存报表[" + date + "]", "货物在途报表", response, "货物库存报表" + date + ".xls", StockHUB.class);
    } catch (IllegalAccessException e) {
        e.printStackTrace();
    } catch (InstantiationException e) {
        e.printStackTrace();
    } catch (Exception e) {
        e.printStackTrace();
    }
}


}
package org.sdrc.childinfo.web;
 import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.sdrc.childinfo.model.LineSeries;
import org.sdrc.childinfo.model.UtDataCollection;
import org.sdrc.childinfo.model.ValueObject;
import org.sdrc.childinfo.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class DashboardController {

 private  DashboardService dashboardService;

@Autowired
public DashboardController(DashboardService dashboardService) {
    this.dashboardService = dashboardService;
}
@RequestMapping(value = { "/timeperiod" }, method = { RequestMethod.GET })
@ResponseBody
public List<ValueObject> fetchUtTimeperiod(String iusnid,String sourceNid){
    List<ValueObject> valueObjects = new ArrayList<>();
    if (iusnid != null && sourceNid != null) {
        // valueObjects = new ArrayList<>();
        valueObjects = dashboardService.fetchUtTimeperiod(Integer.parseInt(iusnid), Integer.parseInt(sourceNid));
    }
    return valueObjects;
}


@RequestMapping(value = { "/indicators" }, method = { RequestMethod.GET })
@ResponseBody
public List<ValueObject> getIndicators(String indicatorType){
    List<ValueObject> valueObjects = new ArrayList<>();
    if (indicatorType != null) {
        valueObjects = dashboardService.fetchIndicators(indicatorType);
    }
    return valueObjects;
}


@RequestMapping(value = { "/" }, method = { RequestMethod.GET })
public String getCounter(Model model){
    model.addAttribute("noOfCounter", dashboardService.getCounter().getNoOfCounter());
    return "welcome";
}


@RequestMapping(value = "/data", method = RequestMethod.GET)
@ResponseBody
public UtDataCollection fetchData(String indicatorId,String sourceNid,String areaId,String timeperiodId,Integer childLevel){
    UtDataCollection valList = new UtDataCollection();
    System.out.println("Checking the parameter values for UT DATA ====> " + indicatorId + "  ==  " + sourceNid + "  ==  " + areaId + "  ==  " + timeperiodId + "  ==  " + childLevel);
    if (indicatorId != null && sourceNid != null && timeperiodId != null) {
        valList = dashboardService.fetchData(indicatorId, sourceNid, areaId, timeperiodId, childLevel);
    }
    return valList;
}


@RequestMapping(value = { "/getCounterJson" }, method = { RequestMethod.GET })
@ResponseBody
public String getCounterJson(Model model){
    // model.addAttribute("noOfCounter", dashboardService.getCounter().getNoOfCounter());
    return dashboardService.fetchCounter().getNoOfCounter().toString();
}


@RequestMapping(value = "/lineData", method = RequestMethod.GET)
@ResponseBody
public List<List<LineSeries>> fetchLineData(Integer iusNid,Integer areaNid){
    return dashboardService.fetchChartData(iusNid, areaNid);
}


@RequestMapping(value = { "/sectors" }, method = { RequestMethod.GET })
@ResponseBody
public List<ValueObject> fetchAllSectors(String indicatorType){
    System.out.println("Sectors---------");
    return dashboardService.fetchAllSectors(indicatorType);
}


@RequestMapping(value = { "/getsourcesbylevel" }, method = { RequestMethod.GET })
@ResponseBody
public List<ValueObject> fetchSourcesByLevel(String iusnid,Integer childLevel){
    List<ValueObject> valueObjects = new ArrayList<>();
    if (iusnid != null) {
        valueObjects = dashboardService.fetchSourcesByLevel(iusnid, childLevel);
    }
    return valueObjects;
}


@RequestMapping(value = "/burdendata", method = RequestMethod.GET)
@ResponseBody
public UtDataCollection fetchBurdenData(Integer iusNIdForProjection,Integer iusNId,Integer iusNIdForIMRorU5MR,String areaId,String timeperiodId,Integer childLevel,Integer sourceNIdForProjection,Integer sourceNId,Integer sourceNIdForIMRorU5MR){
    UtDataCollection valList = new UtDataCollection();
    System.out.println("Checking the parameter values for UT DATA ====> " + iusNIdForProjection + "  ==  " + areaId + "  ==  " + timeperiodId + "  ==  " + childLevel);
    if (iusNIdForProjection != null && timeperiodId != null) {
        valList = dashboardService.fetchBurdenData(iusNIdForProjection, iusNId, iusNIdForIMRorU5MR, areaId, timeperiodId, childLevel, sourceNIdForProjection, sourceNId, sourceNIdForIMRorU5MR);
    }
    return valList;
}


@RequestMapping(value = "/ColData", method = RequestMethod.GET)
@ResponseBody
public List<List<Map<Object,String>>> fetchColData(Integer iusNid,Integer areaNid){
    return dashboardService.fetchColChartData(iusNid, areaNid);
}


@RequestMapping(value = "/computedata", method = RequestMethod.GET)
@ResponseBody
public UtDataCollection fetchComputeData(String indicatorId,String areaId,String timeperiodId,Integer childLevel,Integer sourceNId){
    UtDataCollection valList = new UtDataCollection();
    System.out.println("Checking the parameter values for UT DATA ====> " + indicatorId + "  ==  " + areaId + "  ==  " + timeperiodId + "  ==  " + childLevel);
    if (indicatorId != null && timeperiodId != null) {
        valList = dashboardService.fetchComputeData(indicatorId, areaId, timeperiodId, childLevel, sourceNId);
    }
    return valList;
}


@RequestMapping(value = { "/sources" }, method = { RequestMethod.GET })
@ResponseBody
public List<ValueObject> fetchSources(String iusnid){
    List<ValueObject> valueObjects = new ArrayList<>();
    if (iusnid != null) {
        valueObjects = dashboardService.fetchSources(iusnid);
    }
    return valueObjects;
}


}
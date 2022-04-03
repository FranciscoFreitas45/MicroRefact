package com.gbcom.system.controller;
 import java.io.File;
import java.io.Serializable;
import java.net.InetAddress;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import com.gbcom.common.hibernate.GridJq;
import com.gbcom.common.hibernate.QueryTranslateJq;
import com.gbcom.common.template.xml.Sys;
import com.gbcom.common.template.xml.oem.Oem;
import com.gbcom.common.template.xml.oem.OemManager;
import com.gbcom.omc.verify.SoftDogVerfier;
import com.gbcom.op.util.SystemUtils;
import com.gbcom.system.aop.UserLog;
import com.gbcom.system.common.CpuInfoBean;
import com.gbcom.system.common.MemInfoBean;
import com.gbcom.system.common.ProInfoBean;
import com.gbcom.system.daoservice.SysLogService;
import com.gbcom.system.domain.SysLog;
import com.gbcom.system.mysql.SqlExportManager;
import com.gbcom.system.mysql.SqlImportManager;
import com.gbcom.system.service.DogService;
import com.gbcom.system.service.SysInfoSington;
import com.gbcom.system.utils.CmUtil;
import com.gbcom.system.utils.Constants;
import com.gbcom.system.utils.JsonUtil;
import com.gbcom.system.utils.StatisticsUtil;
import com.hc.core.controller.BaseCRUDActionController;
import com.hc.core.orm.hibernate.Page;
import com.gbcom.Interface.SysLogService;
import com.gbcom.DTO.SqlImportManager;
import com.gbcom.DTO.QueryTranslateJq;
import com.gbcom.DTO.SqlExportManager;
@Controller
public class SysInfoController extends BaseCRUDActionController<Serializable>{

 private  Logger logger;

@Autowired
 private  SysLogService sysLogService;


@RequestMapping
public void getDogInfo(HttpServletResponse response){
    try {
        if (!System.getProperty("os.name").toLowerCase().contains("windows")) {
            // linux 为无穷
            sendSuccessJSON(response, "成功", "----");
            return;
        }
        // throw new RuntimeException("adsf");
        long result = SoftDogVerfier.validate();
        if (result != 0L) {
            sendSuccessJSON(response, "成功", "未安装加密狗，默认接入数：" + DogService.getInstance().getDogAccessNum() + "<br>");
            return;
        } else {
            String msg = "加密狗序列号：" + SoftDogVerfier.getID() + "<br>" + "加密狗授权数：" + SoftDogVerfier.readAuthorNum() + "<br>" + "加密狗商家：" + SoftDogVerfier.readOEM() + "<br>";
            sendSuccessJSON(response, "成功", msg);
        }
    } catch (Exception e) {
        super.processException(response, e);
    }
}


@RequestMapping
@UserLog(eventType = UserLog.USERLOG_EVENTTYPE_QUERY)
public String init(String m,Model model){
    String title = "系统维护";
    if (m.equals("sysSet")) {
        title = "服务器配置";
    }
    if (m.equals("license")) {
        title = "License升级";
    }
    if (m.equals("dbBackup")) {
        title = "数据库备份";
    }
    if (m.equals("recovery")) {
        title = "数据库还原";
    }
    if (m.equals("l2tp")) {
        title = "L2TP服务";
    }
    model.addAttribute("title", title);
    model.addAttribute("method", m);
    model.addAttribute("bean", new HashMap<String, String>());
    return "view/system/sysInfo/init";
}


@RequestMapping
@UserLog(eventType = UserLog.USERLOG_EVENTTYPE_EXPORT)
public void getHelpChm(HttpServletResponse response,HttpServletRequest request){
    String path = getServletContext().getRealPath("/") + "upload/guide.chm";
    sendSuccessJSON(response, "操作成功", path);
}


public float keepNdecimal(float decimal,int number){
    float base = (float) Math.pow(10, number);
    float reasult = (float) ((Math.round(decimal * base)) / base);
    return reasult;
}


@RequestMapping
public void getTreadRunTime(HttpServletRequest request,HttpServletResponse response){
    try {
        List<ProInfoBean> sysInfo = SysInfoSington.getInstance().getProInfo();
        Float[] floatArray = sortSysInfo(sysInfo, 8);
        Map<Object, Object> data = new HashMap<Object, Object>();
        Map<String, Map<Object, Object>> tempData = new HashMap<String, Map<Object, Object>>();
        for (int k = 0; k < floatArray.length; k++) {
            Float value = floatArray[k];
            for (int i = 0; i < sysInfo.size(); i++) {
                ProInfoBean proInfoBean = sysInfo.get(i);
                String proInfoName = proInfoBean.getName();
                String proInfoMem = proInfoBean.getMemUse();
                String part = proInfoMem.substring(proInfoMem.length() - 1);
                proInfoMem = proInfoMem.substring(0, proInfoMem.length() - 1);
                if (part.equals("M")) {
                    proInfoMem = Float.parseFloat(proInfoMem) * 1024 + "";
                }
                String startTime = proInfoBean.getStartTime();
                if (Float.parseFloat(proInfoMem) == value) {
                    if (proInfoName.toLowerCase().contains("java")) {
                        if (proInfoName.contains(".")) {
                            proInfoName = proInfoName.substring(proInfoName.lastIndexOf(".") + 1);
                        }
                    }
                    data.put(proInfoName, calculateTimeSpace(startTime));
                }
            }
        }
        tempData.put("运行时间", data);
        List<Object> legends = new ArrayList<Object>();
        legends.add("运行时间");
        String result = StatisticsUtil.parseVectorData(tempData, legends);
        sendSuccessJSON(response, "", result);
    } catch (Exception e) {
        super.processException(response, e);
    }
}


public String getMemTotal(List<MemInfoBean> memInfo){
    String memtotal = "";
    for (int i = 0; i < memInfo.size(); i++) {
        memtotal = memInfo.get(i).getMemTotal();
        memtotal = memtotal.substring(0, memtotal.length() - 1);
    }
    return memtotal;
}


@RequestMapping
public void recovery(HttpServletRequest request,CommonsMultipartFile uploadFile,HttpServletResponse response){
    String fileName = uploadFile.getOriginalFilename();
    try {
        if (fileName == null || fileName.trim().equals("")) {
            sendFailureJSON(response, "文件名为空，可能为浏览器问题");
            return;
        }
        String fileIp = fileName.substring(fileName.indexOf("-") + 1, fileName.lastIndexOf("-"));
        LinkedHashMap<String, String> ipMap = CmUtil.getLocalAddress();
        if (!ipMap.containsKey(fileIp)) {
            sendFailureJSON(response, "该文件不是此服务器的数据库备份脚本，不能还原!");
            return;
        }
    } catch (Exception e1) {
        sendFailureJSON(response, "该文件不是此服务器的数据库备份脚本，不能还原!");
        return;
    }
    String realPath = request.getSession().getServletContext().getRealPath("/upload");
    boolean isSuccess = false;
    File file = new File(realPath, fileName);
    try {
        FileUtils.copyInputStreamToFile(uploadFile.getInputStream(), file);
        String filePath = realPath + File.separator + uploadFile.getOriginalFilename();
        // 业务操作
        final SqlImportManager sqlService = new SqlImportManager();
        isSuccess = sqlService.importSql(filePath);
    } catch (Exception e) {
        super.processException(response, e);
    } finally {
        try {
            file.delete();
        } catch (Exception e) {
        }
    }
    if (!isSuccess) {
        sendFailureJSON(response, "数据库还原失败");
    } else {
        sendSuccessJSON(response, "数据库还原成功");
    }
}


@RequestMapping
public void sysSet(String method,HttpServletRequest request,HttpServletResponse response,String ftp,String ftpw){
    String ftpIp = ftp;
    String ftpWIp = ftpw;
    try {
        /*
			 * if(params.get("snmp")!=null){ snmpIp = ((String
			 * [])params.get("snmp"))[0].toString(); }else{ snmpIp=""; }
			 */
        if (ftpIp == null) {
            sendFailureJSON(response, "FTP IP 为空");
        }
        if (ftpWIp == null || ftpWIp.trim().equals("")) {
            Sys.sysCfgM().getConfig().getFtp().setFtpWIp(ftpIp);
            Sys.sysCfgM().getConfig().getFtp().setFtpWport("21");
        } else {
            String[] args = ftpWIp.split(":");
            if (args.length == 1) {
                Sys.sysCfgM().getConfig().getFtp().setFtpWIp(args[0]);
            } else if (args.length == 2) {
                Sys.sysCfgM().getConfig().getFtp().setFtpWIp(args[0]);
                Sys.sysCfgM().getConfig().getFtp().setFtpWport(args[1]);
            } else {
                Sys.sysCfgM().getConfig().getFtp().setFtpWIp(ftpIp);
                Sys.sysCfgM().getConfig().getFtp().setFtpWport("21");
            }
        }
        Sys.sysCfgM().getConfig().getFtp().setFtpIp(ftpIp);
        // Sys.sysCfgM().getConfig().setSnmpIp(snmpIp);
        Sys.sysCfgM().save();
        // sysCfgDBService.setCfg();// 保存到数据库
        sendSuccessJSON(response, "成功了");
    } catch (Exception e) {
        super.processException(response, e);
    }
}


public Float[] sortSysInfo(List<ProInfoBean> sysInfo,int num){
    Float[] floatArray = new Float[sysInfo.size()];
    for (int i = 0; i < sysInfo.size(); i++) {
        ProInfoBean proInfoBean = sysInfo.get(i);
        String memuse = proInfoBean.getMemUse();
        String part = memuse.substring(memuse.length() - 1);
        memuse = memuse.substring(0, memuse.length() - 1);
        if (part.equals("M")) {
            memuse = Float.parseFloat(memuse) * 1024 + "";
        }
        Float memuseValue = Float.parseFloat(memuse);
        floatArray[i] = memuseValue;
    }
    Arrays.sort(floatArray);
    Float[] sixArray = new Float[num];
    if (floatArray.length >= num) {
        sixArray = Arrays.copyOfRange(floatArray, floatArray.length - num - 1, floatArray.length);
        floatArray = sixArray;
    }
    return floatArray;
}


@RequestMapping
@UserLog(eventType = UserLog.USERLOG_EVENTTYPE_MODIFY)
public String input(String m,HttpServletRequest request,Model model){
    model.addAttribute("method", m);
    if (m.equals("sysSet")) {
        LinkedHashMap<String, String> valueMap = CmUtil.getLocalAddress();
        model.addAttribute("ftp_current", Sys.sysCfgM().getConfig().getFtp().getFtpIp());
        model.addAttribute("ftpw_current", Sys.sysCfgM().getConfig().getFtp().getFtpWIp() + ":" + Sys.sysCfgM().getConfig().getFtp().getFtpWport());
        model.addAttribute("snmp_current", Sys.sysCfgM().getConfig().getSnmpIp());
        model.addAttribute("ipValues", valueMap);
    }
    if (m.equals("license")) {
        model.addAttribute("accessNum", DogService.getInstance().getDogAccessNum());
    }
    if (m.equals("l2tp")) {
        model.addAttribute("l2tpServerIp", Sys.sysCfgM().getConfig().getL2tp().getL2tpServerIp());
        model.addAttribute("l2tpUsrName", Sys.sysCfgM().getConfig().getL2tp().getL2tpUsrName());
        model.addAttribute("l2tpUsrPass", Sys.sysCfgM().getConfig().getL2tp().getL2tpUsrPass());
    }
    // test
    model.addAttribute("bean", new HashMap<String, String>());
    return "view/system/sysInfo/input_" + m;
}


@RequestMapping
public void license(String method,HttpServletRequest request,HttpServletResponse response,String character){
    try {
        if (character == null) {
            sendFailureJSON(response, "license values is invalid");
            return;
        }
        boolean flag = DogService.getInstance().upgradeLicenseStr(character);
        if (!flag) {
            sendFailureJSON(response, "操作失败，");
        } else {
            sendSuccessJSON(response, "成功了");
        }
    } catch (Exception e) {
        super.processException(response, e);
    }
}


@RequestMapping
@UserLog
public String view(Model model,Long id){
    SysLog sysLog = sysLogService.get(id);
    model.addAttribute("bean", sysLog);
    return "view/system/sysLog/view";
}


@RequestMapping
public void l2tp(String method,HttpServletRequest request,HttpServletResponse response,String l2tpServerIp,String l2tpUsrPass,String l2tpUsrName){
    try {
        Sys.sysCfgM().getConfig().getL2tp().setL2tpServerIp(l2tpServerIp);
        Sys.sysCfgM().getConfig().getL2tp().setL2tpUsrName(l2tpUsrName);
        Sys.sysCfgM().getConfig().getL2tp().setL2tpUsrPass(l2tpUsrPass);
        Sys.sysCfgM().save();
        sendSuccessJSON(response, "成功了");
    } catch (Exception e) {
        super.processException(response, e);
    }
}


@RequestMapping
public void oemInfo(HttpServletResponse response,HttpServletRequest request){
    Oem oem = OemManager.getInstance().getOem();
    sendSuccessJSON(response, JsonUtil.beanToJSON(oem));
}


@RequestMapping
public void getCpuUsedPercent(HttpServletRequest request,HttpServletResponse response){
    List<CpuInfoBean> cpuInfo;
    try {
        cpuInfo = SysInfoSington.getInstance().getCpuInfo();
        List<String> nameList = new ArrayList<String>();
        List<Object> valueList = new ArrayList<Object>();
        float totalCpuUser = 0f, totalCpuSys = 0f, totalCpuWait = 0f;
        nameList.add("用户使用率");
        nameList.add("系统使用率");
        nameList.add("当前等待率");
        nameList.add("当前空闲率");
        for (int i = 0; i < cpuInfo.size(); i++) {
            String cpuUser = cpuInfo.get(i).getCpuUser();
            cpuUser = cpuUser.substring(0, cpuUser.length() - 1);
            totalCpuUser = totalCpuUser + Float.parseFloat(cpuUser);
            String cpuSys = cpuInfo.get(i).getCpuSys();
            cpuSys = cpuSys.substring(0, cpuSys.length() - 1);
            totalCpuSys = totalCpuSys + Float.parseFloat(cpuSys);
            String cpuWait = cpuInfo.get(i).getCpuWait();
            cpuWait = cpuWait.substring(0, cpuWait.length() - 1);
            totalCpuWait = totalCpuWait + Float.parseFloat(cpuWait);
        }
        float cpuCombined = totalCpuUser + totalCpuSys + totalCpuWait;
        cpuCombined = keepNdecimal(cpuCombined, 2);
        totalCpuUser = keepNdecimal(totalCpuUser, 2);
        totalCpuSys = keepNdecimal(totalCpuSys, 2);
        totalCpuWait = keepNdecimal(totalCpuWait, 2);
        float cpuIdle = 100.0f - cpuCombined;
        valueList.add(totalCpuUser);
        valueList.add(totalCpuSys);
        valueList.add(totalCpuWait);
        valueList.add(cpuIdle);
        Object[] data = toJsonString(nameList, valueList);
        String result = StatisticsUtil.parseSingleData(data, "CPU信息");
        sendSuccessJSON(response, "", result);
    } catch (Exception e) {
        super.processException(response, e);
    }
}


@RequestMapping
@UserLog(eventType = UserLog.USERLOG_EVENTTYPE_QUERY)
public String grid(Model model){
    return "view/system/sysInfo/grid";
}


@RequestMapping
public void getMemUsedPercent(HttpServletRequest request,HttpServletResponse response){
    List<MemInfoBean> memInfo;
    try {
        memInfo = SysInfoSington.getInstance().getMemInfo();
        String memtotal = "";
        List<String> nameList = new ArrayList<String>();
        nameList.add("内存使用率");
        nameList.add("内存剩余率");
        List<Object> valueList = new ArrayList<Object>();
        for (int i = 0; i < memInfo.size(); i++) {
            String memused = memInfo.get(i).getMemUsed();
            memused = memused.substring(0, memused.length() - 1);
            String memrem = memInfo.get(i).getMemFree();
            memrem = memrem.substring(0, memrem.length() - 1);
            memtotal = memInfo.get(i).getMemTotal();
            memtotal = memtotal.substring(0, memtotal.length() - 1);
            float memusedPercent = Float.parseFloat(memused) / Float.parseFloat(memtotal);
            memusedPercent = Math.round(keepNdecimal(memusedPercent, 2) * 100);
            float memremPercent = Float.parseFloat(memrem) / Float.parseFloat(memtotal);
            memremPercent = Math.round(keepNdecimal(memremPercent, 2) * 100);
            valueList.add(memusedPercent);
            valueList.add(memremPercent);
        }
        Object[] data = toJsonString(nameList, valueList);
        String result = StatisticsUtil.parseSingleData(data, "百分比");
        sendSuccessJSON(response, "", result);
    } catch (Exception e) {
        super.processException(response, e);
    }
}


@SuppressWarnings("unchecked")
@RequestMapping
public void gridDataCustom(HttpServletResponse response,String filters,String columns,int page,int rows,HttpSession session){
    try {
        Page pageModel = new Page(page, rows, true);
        String hql = "from SysLog order by id desc";
        // 增加自定义查询条件
        // 执行查询
        QueryTranslateJq queryTranslate = new QueryTranslateJq(hql, filters);
        String query = queryTranslate.toString();
        pageModel = sysLogService.findByPage(pageModel, query);
        session.setAttribute(Constants.GRID_SQL_KEY, query);
        // 输出显示
        String json = GridJq.toJSON(columns, pageModel);
        sendJSON(response, json);
    } catch (Exception e) {
        log.error("error", e);
        super.processException(response, e);
    }
}


@RequestMapping
public void getThreadUsedPercent(HttpServletRequest request,HttpServletResponse response){
    List<ProInfoBean> sysInfo;
    List<MemInfoBean> memInfo;
    try {
        sysInfo = SysInfoSington.getInstance().getProInfo();
        memInfo = SysInfoSington.getInstance().getMemInfo();
        String memtotal = getMemTotal(memInfo);
        List<String> nameList = new ArrayList<String>();
        List<Object> valueList = new ArrayList<Object>();
        // 对进程使用率进行排序
        Float[] floatArray = sortSysInfo(sysInfo, 8);
        Float threadTotalPer = 0.0f;
        for (int k = 0; k < floatArray.length; k++) {
            Float value = floatArray[k];
            for (int i = 0; i < sysInfo.size(); i++) {
                ProInfoBean proInfoBean = sysInfo.get(i);
                String proInfoName = proInfoBean.getName();
                String proInfoMem = proInfoBean.getMemUse();
                String part = proInfoMem.substring(proInfoMem.length() - 1);
                proInfoMem = proInfoMem.substring(0, proInfoMem.length() - 1);
                if (part.equals("M")) {
                    proInfoMem = Float.parseFloat(proInfoMem) * 1024 + "";
                }
                if (Float.parseFloat(proInfoMem) == value) {
                    Float proInfoMemPercent = Float.parseFloat(proInfoMem) / (Float.parseFloat(memtotal) * 1024);
                    proInfoMemPercent = ((Math.round((proInfoMemPercent * 100))) / 100.0f) * 100;
                    threadTotalPer = threadTotalPer + proInfoMemPercent;
                    if (proInfoName.toLowerCase().contains("java")) {
                        proInfoName = proInfoName.substring(0, proInfoName.indexOf(":"));
                    } else {
                        if (proInfoName.contains(".")) {
                            proInfoName = proInfoName.substring(0, proInfoName.indexOf("."));
                        }
                    }
                    nameList.add(proInfoName);
                    valueList.add(proInfoMemPercent);
                }
            }
        }
        Object[] data = toJsonString(nameList, valueList);
        String result = StatisticsUtil.parseSingleData(data, "进程信息(内存)");
        sendSuccessJSON(response, "", result);
    } catch (Exception e) {
        super.processException(response, e);
    }
}


public long calculateTimeSpace(String startTime){
    String[] hms = startTime.split(":");
    if (hms.length < 3) {
        startTime = startTime + ":00";
    }
    Date date = new Date();
    SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
    String time = df.format(date);
    Date date1 = null;
    Date date2 = null;
    try {
        date1 = df.parse(startTime);
        date2 = df.parse(time);
        long timespace = date2.getTime() - date1.getTime();
        return timespace / 1000 / 60;
    } catch (ParseException e) {
        // logger.error("ParseException：", e);
        return Math.round(10000f);
    }
}


@RequestMapping
public void dbBackup(Model model,HttpServletRequest request,HttpServletResponse response){
    try {
        Date date = new Date();
        String product = OemManager.getInstance().getOem().getVendor().getProduct();
        // LinkedHashMap<String, String> map = CmUtil.getLocalAddress();
        String ip = "";
        /*
			 * for ( Entry<String, String> entry : map.entrySet()) { ip =
			 * entry.getValue(); }
			 */
        InetAddress addr = InetAddress.getLocalHost();
        ip = addr.getHostAddress();
        String fileName = product + "-" + ip + "-" + date.getTime() + ".sql";
        String path = SystemUtils.USER_HOME + File.separator + fileName;
        SqlExportManager sqlService = new SqlExportManager();
        boolean isSuccess = sqlService.exportSql(path);
        if (isSuccess) {
            sendSuccessJSON(response, "操作成功", path);
        } else {
            sendFailureJSON(response, "数据库备份失败");
            logger.info("数据库备份失败");
        }
    } catch (Exception e) {
        super.processException(response, e);
    }
}


@RequestMapping
public void checkDogVerify(HttpServletResponse response){
    boolean isValidate = DogService.getInstance().isValidate();
    String message = DogService.getInstance().getMessage();
    if (!isValidate) {
        sendFailureJSON(response, message);
        return;
    }
    sendSuccessJSON(response, "成功");
}


public Object[] toJsonString(List<String> name,List<Object> value){
    int nameSize = name.size();
    int valueSize = value.size();
    if (nameSize != valueSize) {
        throw new Exception("数据长度不一致");
    }
    String json = "[";
    List<Object[]> resultData = new ArrayList<Object[]>();
    for (int i = 0; i < nameSize; i++) {
        List<Object> data = new ArrayList<Object>();
        data.add(0, name.get(i));
        data.add(1, value.get(i));
        resultData.add(data.toArray());
    }
    return resultData.toArray();
}


}
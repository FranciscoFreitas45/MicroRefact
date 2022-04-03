package org.sdrc.childinfo.service;
 import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.sdrc.childinfo.domain.TemplateUploadMeta;
import org.sdrc.childinfo.domain.UUIdGenerator;
import org.sdrc.childinfo.model.DataEntryModel;
import org.sdrc.childinfo.model.DataModel;
import org.sdrc.childinfo.model.IUSModel;
import org.sdrc.childinfo.model.UserDetailsModel;
import org.sdrc.childinfo.model.ValueObject;
import org.sdrc.childinfo.repository.TemplateUploadMetaRepository;
import org.sdrc.childinfo.repository.UUIdGeneratorRepository;
import org.sdrc.childinfo.util.ChildInfoUtil;
import org.sdrc.childinfo.util.Constants;
import org.sdrc.childinfo.util.CustomErrorMessageModel;
import org.sdrc.devinfo.domain.UtAreaEn;
import org.sdrc.devinfo.domain.UtData;
import org.sdrc.devinfo.domain.UtIndicatorClassificationsEn;
import org.sdrc.devinfo.domain.UtTimeperiod;
import org.sdrc.devinfo.repository.UtAreaEnRepository;
import org.sdrc.devinfo.repository.UtDataRepository;
import org.sdrc.devinfo.repository.UtIndicatorClassificationsEnRepository;
import org.sdrc.devinfo.repository.UtIndicatorUnitSubgroupRepository;
import org.sdrc.devinfo.repository.UtTimeperiodRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.sdrc.Interface.UtDataRepository;
import org.sdrc.Interface.UtAreaEnRepository;
import org.sdrc.Interface.UtIndicatorClassificationsEnRepository;
import org.sdrc.Interface.UtIndicatorUnitSubgroupRepository;
import org.sdrc.Interface.UtTimeperiodRepository;
import org.sdrc.Interface.TemplateUploadMetaRepository;
import org.sdrc.DTO.TemplateUploadMeta;
@Service
@EnableScheduling
public class DataEntryServiceImpl implements DataEntryService{

@Autowired
 public  UtDataRepository utDataRepository;

@Autowired
 private  UtAreaEnRepository areaRepository;

@Autowired
 private  UtIndicatorClassificationsEnRepository utIndicatorClassificationsEnRepository;

@Autowired
 private  UtIndicatorUnitSubgroupRepository utIndicatorUnitSubgroupRepository;

@Autowired
 private  ServletContext servletContext;

@Autowired
 private  ResourceBundleMessageSource messageSource;

@Autowired
 private  UtTimeperiodRepository utTimeperiodRepository;

@Autowired
 private  UUIdGeneratorRepository uuIdGeneratorRepository;

@Autowired
 private TemplateUploadMetaRepository templateUploadMetaRepository;

 private  SimpleDateFormat timestampFormat;

 private  SimpleDateFormat simpleDateformat;

 private  SimpleDateFormat simpleDateformater;

 private  DateFormat formatter;

 private  Logger LOGGER;


@Override
public List<ValueObject> getSubsector(){
    List<UtIndicatorClassificationsEn> utIndicatorClassifications = utIndicatorClassificationsEnRepository.getSubsector();
    List<ValueObject> valueObject = new ArrayList<>();
    for (UtIndicatorClassificationsEn utIndicatorClassificationsEn : utIndicatorClassifications) {
        ValueObject object = new ValueObject();
        object.setNid(utIndicatorClassificationsEn.getIC_NId());
        object.setDescription(utIndicatorClassificationsEn.getIC_Name());
        valueObject.add(object);
    }
    return valueObject;
}


@Override
@Scheduled(cron = "0 0 0 1 * *")
public void createPreviousMonth(){
    try {
        Calendar startDateCalendar = Calendar.getInstance();
        startDateCalendar.add(Calendar.MONTH, -1);
        startDateCalendar.set(Calendar.DATE, 1);
        Date sDate = startDateCalendar.getTime();
        String startDateStr = simpleDateformater.format(sDate);
        Date startDate = (Date) formatter.parse(startDateStr + " 00:00:00.000");
        startDateCalendar.set(Calendar.DATE, startDateCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date eDate = startDateCalendar.getTime();
        String endDateStr = simpleDateformater.format(eDate);
        Date endDate = (Date) formatter.parse(endDateStr + " 00:00:00.000");
        UtTimeperiod utTime = utTimeperiodRepository.findByStartDateAndEndDateAndPeriodicity(startDate, endDate, "1");
        if (utTime == null) {
            UtTimeperiod utTimePeriod = new UtTimeperiod();
            utTimePeriod.setStartDate(startDate);
            utTimePeriod.setEndDate(endDate);
            // for monthly aggregation periodicity is 1
            utTimePeriod.setPeriodicity("1");
            utTimePeriod.setTimePeriod(simpleDateformat.format(startDate));
            utTimeperiodRepository.save(utTimePeriod);
        }
    } catch (Exception e) {
        LOGGER.error("Error while converting String to Date format." + e);
    }
}


public String getIpAddr(HttpServletRequest request){
    String ip = request.getHeader("x-forwarded-for");
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getHeader("Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getHeader("WL-Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getRemoteAddr();
    }
    return ip;
}


public boolean saveExcelData(XSSFSheet sheet,Map<String,Integer> areaMap,List<UtData> utDatas){
    boolean flag = false;
    try {
        XSSFRow row = sheet.getRow(3);
        XSSFCell cell = row.getCell(1);
        String subSectorName = cell.getStringCellValue();
        int subgroupValNid = utIndicatorClassificationsEnRepository.getSubsectorId(subSectorName);
        cell = row.getCell(4);
        int subSector = ((Double) cell.getNumericCellValue()).intValue();
        row = sheet.getRow(4);
        cell = row.getCell(4);
        int indicator = ((Double) cell.getNumericCellValue()).intValue();
        row = sheet.getRow(5);
        cell = row.getCell(4);
        int unit = ((Double) cell.getNumericCellValue()).intValue();
        row = sheet.getRow(6);
        cell = row.getCell(4);
        int subgroup = ((Double) cell.getNumericCellValue()).intValue();
        row = sheet.getRow(7);
        cell = row.getCell(4);
        int timePeriod = ((Double) cell.getNumericCellValue()).intValue();
        row = sheet.getRow(8);
        cell = row.getCell(1);
        String providedBy;
        if (cell.getCellTypeEnum() == CellType.STRING) {
            providedBy = cell.getStringCellValue();
        }
        /**
         * Checking if the user already uploaded the data
         */
        List<UtData> datas = utDataRepository.getData(subSector, timePeriod, indicator, unit, subgroup);
        for (int i = 12; i <= sheet.getLastRowNum(); i++) {
            row = sheet.getRow(i);
            cell = row.getCell(0);
            UtData data = checkData(datas, areaMap.get(cell.getStringCellValue()));
            if (data == null) {
                int areaName = areaMap.get(cell.getStringCellValue());
                cell = row.getCell(3);
                if (cell.getCellTypeEnum() != CellType.BLANK) {
                    if (cell.getNumericCellValue() < 0) {
                        flag = true;
                        LOGGER.error("Please enter Positive value.");
                    } else {
                        UtData utData = new UtData();
                        utData.setArea_NId(areaName);
                        utData.setData_Value(cell.getNumericCellValue());
                        utData.setIUSNId(subSector);
                        utData.setTimePeriod_NId(timePeriod);
                        utData.setIndicator_NId(indicator);
                        utData.setUnit_NId(unit);
                        utData.setSubgroup_Val_NId(subgroup);
                        utData.setIUNId(subgroup + "_" + unit);
                        utData.setSource_NId(subgroupValNid);
                        utDatas.add(utData);
                    }
                }
            } else {
                cell = row.getCell(3);
                if (cell.getNumericCellValue() < 0) {
                    flag = true;
                    LOGGER.error("Please enter Positive value.");
                } else {
                    data.setData_Value(Double.toString(cell.getNumericCellValue()) == null ? data.getData_Value() : cell.getCellTypeEnum() == CellType.BLANK ? data.getData_Value() : Double.toString(cell.getNumericCellValue()).equals("0.0") ? data.getData_Value() : cell.getNumericCellValue());
                    utDatas.add(data);
                }
            }
        }
    } catch (Exception e) {
        flag = true;
        LOGGER.error("Please enter Numeric value.");
    }
    if (flag != true) {
        LOGGER.info("Data saved successfully.");
        return flag;
    } else {
        LOGGER.error("Please enter Numeric value.");
        return flag;
    }
}


public UtData checkData(List<UtData> datas,Integer areaId){
    UtData data = null;
    for (UtData utData : datas) {
        if (utData.getArea_NId() == areaId)
            data = utData;
    }
    return data;
}


public CustomErrorMessageModel errorMsg(CustomErrorMessageModel customErrorMessageModel,String msg){
    customErrorMessageModel.setMessage(msg);
    customErrorMessageModel.setStatusCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    return customErrorMessageModel;
}


public Map<String,Object> getValue(List<Object[]> list){
    // JSONObject newJSON = new JSONObject();
    Map<String, Object> value = new HashMap<String, Object>();
    int maxTimeperiod = 0;
    for (Object[] obj : list) {
        if (Integer.parseInt(obj[0].toString()) > maxTimeperiod) {
            maxTimeperiod = Integer.parseInt(obj[0].toString());
        }
    }
    Double maxVal = null;
    for (Object[] obj : list) {
        if (Integer.parseInt(obj[0].toString()) == maxTimeperiod) {
            maxVal = Double.parseDouble(obj[7].toString());
            // newJSON.put("dataValue", maxVal);
            // newJSON.put("timeperiod", obj[1]);
            value.put("dataValue", maxVal);
            value.put("timeperiod", obj[1]);
        }
    }
    // return newJSON;
    return value;
}


@Override
public List<ValueObject> getTimePeriod(){
    List<UtTimeperiod> utTime = utTimeperiodRepository.findByPeriodicity("1");
    List<ValueObject> valueObject = new ArrayList<>();
    for (UtTimeperiod utTimeperiod : utTime) {
        ValueObject object = new ValueObject();
        object.setNid(utTimeperiod.getTimePeriod_NId());
        object.setDescription(utTimeperiod.getTimePeriod());
        valueObject.add(object);
    }
    return valueObject;
}


public String getUUId(){
    String uuid = UUID.randomUUID().toString();
    UUIdGenerator uuidGenerator = new UUIdGenerator();
    uuidGenerator.setCreatedDate(new Timestamp(new Date().getTime()));
    uuidGenerator.setUuid(uuid);
    uuidGenerator.setIsLive(true);
    uuIdGeneratorRepository.save(uuidGenerator);
    return uuid;
}


@Override
public String downloadExcelFile(DataEntryModel dataEntryModel){
    XSSFWorkbook workbook;
    FileInputStream fileInputStream;
    String path = messageSource.getMessage("childinfo.output.path", null, null);
    File file = new File(path);
    if (!file.exists()) {
        file.mkdirs();
    }
    String outputPath = path + "CI_DATA_MGMT_MODULE_FRS_" + timestampFormat.format(new Date()) + ".xlsx";
    FileOutputStream fileOutputStream;
    try {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        UserDetailsModel user = (UserDetailsModel) request.getSession().getAttribute(Constants.USER_PRINCIPAL);
        fileInputStream = new FileInputStream(new File(servletContext.getRealPath(messageSource.getMessage("excel.template", null, null))));
        workbook = new XSSFWorkbook(fileInputStream);
        int rowNum;
        XSSFSheet sheet;
        XSSFRow row;
        XSSFCell cell;
        // Getting style sheet for unlocking
        XSSFCellStyle styleForUnLocking = ChildInfoUtil.getStyleForUnLocking(workbook);
        int colUnlock = 3;
        int rowUnlock = 12;
        int sheetNum = 1;
        for (IUSModel iusModel : dataEntryModel.getIusModel()) {
            rowNum = 2;
            sheet = workbook.cloneSheet(1);
            workbook.setSheetName(workbook.getSheetIndex(sheet), "Data sheet(" + sheetNum + ")");
            Map<String, Integer> dataValueMap = new LinkedHashMap<>();
            dataValueMap.put(dataEntryModel.getProgram().getDescription(), iusModel.getIusNId());
            dataValueMap.put(iusModel.getIndicatorName(), iusModel.getIndicatorId());
            dataValueMap.put(iusModel.getUnitName(), iusModel.getUnitId());
            dataValueMap.put(iusModel.getSubgroupName(), iusModel.getSubgroupId());
            dataValueMap.put(dataEntryModel.getTimePeriod().getDescription(), dataEntryModel.getTimePeriod().getNid());
            dataValueMap.put(null, null);
            dataValueMap.put(user.getUserName(), null);
            setAllValues(rowNum, sheet, dataValueMap);
            putAllArea(sheet, dataEntryModel);
            ChildInfoUtil.sheetLock(sheet);
            row = sheet.getRow(8);
            cell = row.createCell(1);
            cell.setCellType(CellType.STRING);
            cell.setCellStyle(styleForUnLocking);
            cell = row.createCell(2);
            cell.setCellStyle(styleForUnLocking);
            cell = row.createCell(3);
            cell.setCellStyle(styleForUnLocking);
            for (int i = 0; i < dataEntryModel.getDataModel().size(); i++) {
                row = sheet.getRow(rowUnlock);
                cell = row.createCell(colUnlock);
                cell.setCellStyle(styleForUnLocking);
                rowUnlock++;
            }
            sheet.setColumnHidden(4, true);
            rowUnlock = 12;
            colUnlock = 3;
            sheetNum += 1;
        }
        sheet = workbook.getSheetAt(1);
        row = sheet.createRow(1);
        cell = row.createCell(6);
        String uuid = getUUId();
        cell.setCellValue(uuid);
        workbook.setSheetHidden(1, true);
        workbook.lockStructure();
        fileOutputStream = new FileOutputStream(new File(outputPath));
        workbook.write(fileOutputStream);
        fileInputStream.close();
        fileOutputStream.flush();
        fileOutputStream.close();
    } catch (FileNotFoundException e) {
        LOGGER.error("File not found while downloading excel file", e);
    } catch (IOException e) {
        LOGGER.error("Failed or interrupted I/O operations", e);
    }
    return outputPath;
}


public void setAllValues(int rowNum,XSSFSheet sheet,Map<String,Integer> dataValueMap){
    int rowNumber = rowNum;
    XSSFRow row;
    XSSFCell cell;
    for (Map.Entry<String, Integer> obj : dataValueMap.entrySet()) {
        rowNumber += 1;
        int colNum = 1;
        row = sheet.getRow(rowNumber);
        cell = row.createCell(colNum);
        cell.setCellValue(obj.getKey());
        colNum = colNum + 3;
        cell = row.createCell(colNum);
        if (obj.getValue() != null)
            cell.setCellValue(obj.getValue());
    }
}


@Override
public String downloadFactsheet(DataEntryModel dataEntryModel){
    XSSFWorkbook workbook;
    FileInputStream fileInputStream;
    ValueObject timeperiod = dataEntryModel.getTimePeriod();
    DataModel areaData = dataEntryModel.getDataModel().get(0);
    String output = Constants.FACTSHEET_NAME + timestampFormat.format(new Date()) + ".xlsx";
    FileOutputStream fileOutputStream;
    int uttatpradeshAreaId = Constants.UTTAR_PRADESH_AREA_ID_BY_BLOCKS;
    try {
        fileInputStream = new FileInputStream(new File(servletContext.getRealPath(messageSource.getMessage("factsheet.excel.template", null, null))));
        workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet;
        XSSFRow row;
        sheet = workbook.getSheet("Sheet2");
        List<Integer> areaList = new ArrayList<>();
        areaList.add(areaData.getAreaNId());
        areaList.add(uttatpradeshAreaId);
        List<Object[]> data = utDataRepository.findDataByTimePeriodAndArea(timeperiod.getNid(), areaList, Constants.FACTSHEET_REQUIRED_IUS_NIDS);
        List<String> abrccArea = new ArrayList<>();
        abrccArea.add(areaData.getAreaName());
        abrccArea.add(Constants.ABRCC_STATE_NAME);
        List<Object[]> abrccData = utDataRepository.getABRCCData(abrccArea);
        List<Object[]> stateSchoolHavingGirlsToilet = new ArrayList<>();
        List<Object[]> districtSchoolHavingGirlsToilet = new ArrayList<>();
        List<Object[]> stateSchoolWithBounaryWall = new ArrayList<>();
        List<Object[]> districtSchoolWithBounaryWall = new ArrayList<>();
        List<Object[]> stateSchoolWithDrinkingWater = new ArrayList<>();
        List<Object[]> districtSchoolWithDrinkingWater = new ArrayList<>();
        List<Object[]> stateSchoolWithElectricity = new ArrayList<>();
        List<Object[]> districtSchoolWithElectricity = new ArrayList<>();
        for (Object[] obj : abrccData) {
            if (obj[3].toString().equals("Percentage of schools having girls toilets")) {
                if (obj[2].toString().equals(Constants.ABRCC_STATE_NAME))
                    stateSchoolHavingGirlsToilet.add(obj);
                else
                    districtSchoolHavingGirlsToilet.add(obj);
            } else if (obj[3].toString().equals("Percentage of schools with boundary wall")) {
                if (obj[2].toString().equals(Constants.ABRCC_STATE_NAME))
                    stateSchoolWithBounaryWall.add(obj);
                else
                    districtSchoolWithBounaryWall.add(obj);
            } else if (obj[3].toString().equals("Percentage of schools with drinking water")) {
                if (obj[2].toString().equals(Constants.ABRCC_STATE_NAME))
                    stateSchoolWithDrinkingWater.add(obj);
                else
                    districtSchoolWithDrinkingWater.add(obj);
            } else if (obj[3].toString().equals("Percentage of schools with electricity")) {
                if (obj[2].toString().equals(Constants.ABRCC_STATE_NAME))
                    stateSchoolWithElectricity.add(obj);
                else
                    districtSchoolWithElectricity.add(obj);
            }
        }
        // Schools having girls' toilet	DISE - State
        row = sheet.getRow(59);
        row.createCell(1).setCellValue(Double.parseDouble(getValue(stateSchoolHavingGirlsToilet).get("dataValue").toString()));
        // Schools having girls' toilet	DISE - District
        row.createCell(2).setCellValue(Double.parseDouble(getValue(districtSchoolHavingGirlsToilet).get("dataValue").toString()));
        row.createCell(6).setCellValue(getValue(districtSchoolHavingGirlsToilet).get("timeperiod").toString());
        // Schools with boundary wall DISE - State
        row = sheet.getRow(49);
        row.createCell(1).setCellValue(Double.parseDouble(getValue(stateSchoolWithBounaryWall).get("dataValue").toString()));
        // Schools with boundary wall DISE - District
        row.createCell(2).setCellValue(Double.parseDouble(getValue(districtSchoolWithBounaryWall).get("dataValue").toString()));
        row.createCell(6).setCellValue(getValue(districtSchoolWithBounaryWall).get("timeperiod").toString());
        // Schools with electricity DISE - State
        row = sheet.getRow(54);
        row.createCell(1).setCellValue(Double.parseDouble(getValue(stateSchoolWithElectricity).get("dataValue").toString()));
        // Schools with electricity DISE - District
        row.createCell(2).setCellValue(Double.parseDouble(getValue(districtSchoolWithElectricity).get("dataValue").toString()));
        row.createCell(6).setCellValue(getValue(districtSchoolWithElectricity).get("timeperiod").toString());
        // Schools having drinking water facility DISE - State
        row = sheet.getRow(64);
        row.createCell(1).setCellValue(Double.parseDouble(getValue(stateSchoolWithDrinkingWater).get("dataValue").toString()));
        // Schools having drinking water facility DISE - District
        row.createCell(2).setCellValue(Double.parseDouble(getValue(districtSchoolWithDrinkingWater).get("dataValue").toString()));
        row.createCell(6).setCellValue(getValue(districtSchoolWithDrinkingWater).get("timeperiod").toString());
        Date date = new SimpleDateFormat("yyyy.MM").parse(timeperiod.getDescription());
        String factsheetTimeperiod = new SimpleDateFormat("MMMMMMMMMM").format(date) + " " + new SimpleDateFormat("yyyy").format(date);
        row = sheet.getRow(0);
        row.createCell(7).setCellValue(factsheetTimeperiod);
        row = sheet.getRow(1);
        // area
        row.createCell(2).setCellValue(areaData.getAreaName());
        // area
        row.createCell(4).setCellValue(areaData.getAreaName() + " - " + areaData.getAreaNId());
        // time period
        row.createCell(5).setCellValue(timeperiod.getDescription() + " - " + timeperiod.getNid());
        row = sheet.getRow(23);
        row.createCell(0).setCellValue(areaData.getAreaName());
        row = sheet.getRow(28);
        row.createCell(0).setCellValue(areaData.getAreaName());
        row = sheet.getRow(34);
        row.createCell(0).setCellValue(areaData.getAreaName());
        row = sheet.getRow(42);
        row.createCell(6).setCellValue(areaData.getAreaName());
        row = sheet.getRow(48);
        row.createCell(2).setCellValue(areaData.getAreaName());
        row = sheet.getRow(53);
        row.createCell(2).setCellValue(areaData.getAreaName());
        row = sheet.getRow(58);
        row.createCell(2).setCellValue(areaData.getAreaName());
        row = sheet.getRow(63);
        row.createCell(2).setCellValue(areaData.getAreaName());
        for (int i = 0; i < data.size(); i++) {
            UtData utData = (UtData) data.get(i)[0];
            switch(utData.getIUSNId()) {
                // Full immunization coverage among children (12-23 months)
                case // rural
                482:
                    row = sheet.getRow(3);
                    if (utData.getUnit_NId() == 2) {
                        if (utData.getArea_NId() == uttatpradeshAreaId)
                            row.createCell(1).setCellValue(utData.getData_Value());
                        else
                            row.createCell(2).setCellValue(utData.getData_Value());
                    }
                    break;
                case // total
                485:
                    row = sheet.getRow(2);
                    if (utData.getUnit_NId() == 2) {
                        if (utData.getArea_NId() == uttatpradeshAreaId)
                            row.createCell(1).setCellValue(utData.getData_Value());
                        else
                            row.createCell(2).setCellValue(utData.getData_Value());
                    }
                    break;
                case // urban
                486:
                    row = sheet.getRow(4);
                    if (utData.getUnit_NId() == 2) {
                        if (utData.getArea_NId() == uttatpradeshAreaId)
                            row.createCell(1).setCellValue(utData.getData_Value());
                        else
                            row.createCell(2).setCellValue(utData.getData_Value());
                    }
                    break;
                // Home visits reported by ASHAs
                case 3212:
                    row = sheet.getRow(22);
                    if (utData.getUnit_NId() == 2) {
                        if (utData.getArea_NId() == uttatpradeshAreaId)
                            row.createCell(1).setCellValue(utData.getData_Value());
                    }
                    row = sheet.getRow(23);
                    if (utData.getUnit_NId() == 2) {
                        if (utData.getArea_NId() != uttatpradeshAreaId)
                            row.createCell(1).setCellValue(utData.getData_Value());
                    }
                    break;
                // Bed occupancy rate in SNCU
                case 3213:
                    row = sheet.getRow(27);
                    if (utData.getUnit_NId() == 2) {
                        if (utData.getArea_NId() == uttatpradeshAreaId)
                            row.createCell(1).setCellValue(utData.getData_Value());
                    }
                    row = sheet.getRow(28);
                    if (utData.getUnit_NId() == 2) {
                        if (utData.getArea_NId() != uttatpradeshAreaId)
                            row.createCell(1).setCellValue(utData.getData_Value());
                    }
                    break;
                // WATER SANITATION AND HYGIENE (WASH)
                case // IHHL coverage
                3214:
                    row = sheet.getRow(33);
                    if (utData.getUnit_NId() == 2) {
                        if (utData.getArea_NId() == uttatpradeshAreaId)
                            row.createCell(1).setCellValue(utData.getData_Value());
                    }
                    row = sheet.getRow(34);
                    if (utData.getUnit_NId() == 2) {
                        if (utData.getArea_NId() != uttatpradeshAreaId)
                            row.createCell(1).setCellValue(utData.getData_Value());
                    }
                    break;
                case // IHHL geo-tagged
                3215:
                    row = sheet.getRow(33);
                    if (utData.getUnit_NId() == 2) {
                        if (utData.getArea_NId() == uttatpradeshAreaId)
                            row.createCell(2).setCellValue(utData.getData_Value());
                    }
                    row = sheet.getRow(34);
                    if (utData.getUnit_NId() == 2) {
                        if (utData.getArea_NId() != uttatpradeshAreaId)
                            row.createCell(2).setCellValue(utData.getData_Value());
                    }
                    break;
                case // Swachhgrahi mobilized
                3216:
                    row = sheet.getRow(33);
                    if (utData.getUnit_NId() == 2) {
                        if (utData.getArea_NId() == uttatpradeshAreaId)
                            row.createCell(3).setCellValue(utData.getData_Value());
                    }
                    row = sheet.getRow(34);
                    if (utData.getUnit_NId() == 2) {
                        if (utData.getArea_NId() != uttatpradeshAreaId)
                            row.createCell(3).setCellValue(utData.getData_Value());
                    }
                    break;
                case // Villages declared ODF
                3217:
                    row = sheet.getRow(33);
                    if (utData.getUnit_NId() == 2) {
                        if (utData.getArea_NId() == uttatpradeshAreaId)
                            row.createCell(4).setCellValue(utData.getData_Value());
                    }
                    row = sheet.getRow(34);
                    if (utData.getUnit_NId() == 2) {
                        if (utData.getArea_NId() != uttatpradeshAreaId)
                            row.createCell(4).setCellValue(utData.getData_Value());
                    }
                    break;
                case // Villages verified ODF
                3218:
                    row = sheet.getRow(33);
                    if (utData.getUnit_NId() == 2) {
                        if (utData.getArea_NId() == uttatpradeshAreaId)
                            row.createCell(5).setCellValue(utData.getData_Value());
                    }
                    row = sheet.getRow(34);
                    if (utData.getUnit_NId() == 2) {
                        if (utData.getArea_NId() != uttatpradeshAreaId)
                            row.createCell(5).setCellValue(utData.getData_Value());
                    }
                    break;
                // Children in the UW category (both red and Yellow): Number
                case 3219:
                    row = sheet.getRow(43);
                    if (utData.getUnit_NId() == 1) {
                        if (utData.getArea_NId() == uttatpradeshAreaId)
                            row.createCell(1).setCellValue(utData.getData_Value());
                        else
                            row.createCell(2).setCellValue(utData.getData_Value());
                    }
                    break;
                // Children 6 months to 3 years receiving SNP: Number
                case 3221:
                    row = sheet.getRow(44);
                    if (utData.getUnit_NId() == 1) {
                        if (utData.getArea_NId() == uttatpradeshAreaId)
                            row.createCell(1).setCellValue(utData.getData_Value());
                        else
                            row.createCell(2).setCellValue(utData.getData_Value());
                    }
                    break;
                // Children in the UW category (both red and Yellow): Percent
                case 3220:
                    row = sheet.getRow(43);
                    if (utData.getUnit_NId() == 2) {
                        if (utData.getArea_NId() == uttatpradeshAreaId)
                            row.createCell(5).setCellValue(utData.getData_Value());
                        else
                            row.createCell(6).setCellValue(utData.getData_Value());
                    }
                    break;
                // Children 6 months-3 years receiving SNP: Percent
                case 3222:
                    row = sheet.getRow(44);
                    if (utData.getUnit_NId() == 2) {
                        if (utData.getArea_NId() == uttatpradeshAreaId)
                            row.createCell(5).setCellValue(utData.getData_Value());
                        else
                            row.createCell(6).setCellValue(utData.getData_Value());
                    }
                    break;
                // Distribution of block Cold Chain Points in A, B, C and D
                case // A
                3211:
                    if (utData.getUnit_NId() == 2) {
                        if (utData.getArea_NId() == uttatpradeshAreaId) {
                            row = sheet.getRow(8);
                            row.createCell(1).setCellValue(utData.getData_Value());
                        } else {
                            row = sheet.getRow(15);
                            row.createCell(1).setCellValue(utData.getData_Value());
                        }
                    }
                    break;
                case // B
                3247:
                    if (utData.getUnit_NId() == 2) {
                        if (utData.getArea_NId() == uttatpradeshAreaId) {
                            row = sheet.getRow(9);
                            row.createCell(1).setCellValue(utData.getData_Value());
                        } else {
                            row = sheet.getRow(16);
                            row.createCell(1).setCellValue(utData.getData_Value());
                        }
                    }
                    break;
                case // C
                3248:
                    if (utData.getUnit_NId() == 2) {
                        if (utData.getArea_NId() == uttatpradeshAreaId) {
                            row = sheet.getRow(10);
                            row.createCell(1).setCellValue(utData.getData_Value());
                        } else {
                            row = sheet.getRow(17);
                            row.createCell(1).setCellValue(utData.getData_Value());
                        }
                    }
                    break;
                case // D
                3249:
                    if (utData.getUnit_NId() == 2) {
                        if (utData.getArea_NId() == uttatpradeshAreaId) {
                            row = sheet.getRow(11);
                            row.createCell(1).setCellValue(utData.getData_Value());
                        } else {
                            row = sheet.getRow(18);
                            row.createCell(1).setCellValue(utData.getData_Value());
                        }
                    }
                    break;
                // Schools with boundary wall
                case 1992:
                    if (utData.getUnit_NId() == 2) {
                        if (utData.getArea_NId() == uttatpradeshAreaId) {
                            row = sheet.getRow(50);
                            row.createCell(1).setCellValue(utData.getData_Value());
                        } else {
                            row = sheet.getRow(50);
                            row.createCell(2).setCellValue(utData.getData_Value());
                        }
                    }
                    break;
                // Schools with electricity
                case 2001:
                    if (utData.getUnit_NId() == 2) {
                        if (utData.getArea_NId() == uttatpradeshAreaId) {
                            row = sheet.getRow(55);
                            row.createCell(1).setCellValue(utData.getData_Value());
                        } else {
                            row = sheet.getRow(55);
                            row.createCell(2).setCellValue(utData.getData_Value());
                        }
                    }
                    break;
                // Schools having girls' toilet
                case 1986:
                    if (utData.getUnit_NId() == 2) {
                        if (utData.getArea_NId() == uttatpradeshAreaId) {
                            row = sheet.getRow(60);
                            row.createCell(1).setCellValue(utData.getData_Value());
                        } else {
                            row = sheet.getRow(60);
                            row.createCell(2).setCellValue(utData.getData_Value());
                        }
                    }
                    break;
                // Schools having drinking water facility
                case 1998:
                    if (utData.getUnit_NId() == 2) {
                        if (utData.getArea_NId() == uttatpradeshAreaId) {
                            row = sheet.getRow(65);
                            row.createCell(1).setCellValue(utData.getData_Value());
                        } else {
                            row = sheet.getRow(65);
                            row.createCell(2).setCellValue(utData.getData_Value());
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        sheet.setForceFormulaRecalculation(true);
        workbook.lockStructure();
        fileOutputStream = new FileOutputStream(new File(output));
        workbook.write(fileOutputStream);
        fileInputStream.close();
        fileOutputStream.flush();
        fileOutputStream.close();
    } catch (FileNotFoundException e) {
        LOGGER.error("File not found while downloading excel file", e);
    } catch (IOException e) {
        LOGGER.error("Failed or interrupted I/O operations", e);
    } catch (ParseException e) {
        LOGGER.error("Exception occured while parsing from string to date type.", e);
    }
    return output;
}


@Override
public List<IUSModel> getIUS(int id){
    List<Object[]> utIndicatorUnitSubgroups = utIndicatorUnitSubgroupRepository.getIUS(id);
    List<IUSModel> iusModel = new ArrayList<>();
    for (Object[] utIndicatorUnitSubgroup : utIndicatorUnitSubgroups) {
        IUSModel object = new IUSModel();
        object.setIusNId(Integer.parseInt(utIndicatorUnitSubgroup[0].toString()));
        object.setIusName(utIndicatorUnitSubgroup[2].toString() + "," + utIndicatorUnitSubgroup[6].toString() + "(" + utIndicatorUnitSubgroup[4].toString() + ")");
        object.setIndicatorId(Integer.parseInt(utIndicatorUnitSubgroup[1].toString()));
        object.setIndicatorName(utIndicatorUnitSubgroup[2].toString());
        object.setUnitId(Integer.parseInt(utIndicatorUnitSubgroup[3].toString()));
        object.setUnitName(utIndicatorUnitSubgroup[4].toString());
        object.setSubgroupId(Integer.parseInt(utIndicatorUnitSubgroup[5].toString()));
        object.setSubgroupName(utIndicatorUnitSubgroup[6].toString());
        iusModel.add(object);
    }
    return iusModel;
}


@Override
public File generateRawDataExcel(ValueObject timeperiodId){
    File fileWritten = null;
    try {
        String path = messageSource.getMessage("childinfo.output.path", null, null);
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        String filename = path + "RAW_DATA_" + timestampFormat.format(new Date());
        String[] columns = { "Area", "Indicator Name", "Unit Name", "Subgroup", "Source", "Data Value" };
        FileOutputStream outputStream;
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        // Create a Sheet
        SXSSFSheet sheet = workbook.createSheet("RawData");
        sheet.setColumnWidth(0, 8000);
        sheet.setColumnWidth(1, 16000);
        sheet.setColumnWidth(2, 4000);
        sheet.setColumnWidth(3, 4000);
        sheet.setColumnWidth(4, 4000);
        sheet.setColumnWidth(5, 4000);
        CellStyle getStyleForCenter = ChildInfoUtil.getStyleForCenter(workbook);
        CellStyle getStyleForProject = ChildInfoUtil.getStyleForProjectName(workbook);
        CellStyle getStyleForOtherHeader = ChildInfoUtil.getStyleForOtherHeader(workbook);
        CellStyle getStyleForOtherDateHeader = ChildInfoUtil.getStyleForOtherDateHeader(workbook);
        int rowNum = 0;
        int colNum = 0;
        // Create a Row
        SXSSFRow headerRow = sheet.createRow(rowNum);
        Cell cell = headerRow.createCell(colNum);
        cell.setCellValue("UP-ChildInfo");
        cell.setCellStyle(getStyleForProject);
        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, colNum, 1));
        rowNum += 1;
        colNum = 0;
        headerRow = sheet.createRow(rowNum);
        cell = headerRow.createCell(colNum);
        cell.setCellValue("Time Period");
        cell.setCellStyle(getStyleForOtherHeader);
        colNum += 1;
        cell = headerRow.createCell(colNum);
        cell.setCellValue(timeperiodId.getDescription());
        cell.setCellStyle(getStyleForOtherHeader);
        rowNum += 1;
        colNum = 0;
        headerRow = sheet.createRow(rowNum);
        cell = headerRow.createCell(colNum);
        cell.setCellValue("Report type");
        cell.setCellStyle(getStyleForOtherHeader);
        colNum += 1;
        cell = headerRow.createCell(colNum);
        cell.setCellValue("Raw data report");
        cell.setCellStyle(getStyleForOtherHeader);
        rowNum += 1;
        colNum = 0;
        headerRow = sheet.createRow(rowNum);
        cell = headerRow.createCell(colNum);
        cell.setCellValue("Generated Date");
        cell.setCellStyle(getStyleForOtherHeader);
        colNum += 1;
        cell = headerRow.createCell(colNum);
        cell.setCellValue(new Date());
        cell.setCellStyle(getStyleForOtherDateHeader);
        headerRow = sheet.createRow(4);
        // Create cells
        for (int i = 0; i < columns.length; i++) {
            cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(getStyleForOtherHeader);
        }
        // Create Other rows and cells with ut data
        rowNum = 5;
        List<Object[]> dataRetrived = utDataRepository.getByTimePeriod(timeperiodId.getNid());
        SXSSFRow row;
        for (Object[] objects : dataRetrived) {
            row = sheet.createRow(rowNum++);
            // cell = row.createCell(0);
            // cell.setCellValue(objects[0].toString());
            // cell.setCellStyle(getStyleForCenter);
            cell = row.createCell(0);
            cell.setCellValue(objects[1].toString());
            cell.setCellStyle(getStyleForCenter);
            cell = row.createCell(1);
            cell.setCellValue(objects[2].toString());
            cell.setCellStyle(getStyleForCenter);
            cell = row.createCell(2);
            cell.setCellValue(objects[3].toString());
            cell.setCellStyle(getStyleForCenter);
            cell = row.createCell(3);
            cell.setCellValue(objects[4].toString());
            cell.setCellStyle(getStyleForCenter);
            cell = row.createCell(4);
            cell.setCellValue(objects[5].toString());
            cell.setCellStyle(getStyleForCenter);
            cell = row.createCell(5);
            cell.setCellValue(objects[6].toString());
            cell.setCellStyle(getStyleForCenter);
        }
        sheet.createFreezePane(6, 5);
        // Write the output to a file
        fileWritten = File.createTempFile(filename + "_" + timestampFormat.format(new Date()), ".xlsx");
        outputStream = new FileOutputStream(fileWritten);
        workbook.write(outputStream);
        workbook.close();
    } catch (Exception e) {
        LOGGER.error("Error while workbook write." + e);
    }
    return fileWritten;
}


@Override
@Transactional
public CustomErrorMessageModel excelFileUpload(byte[] bytes){
    CustomErrorMessageModel messageModel = new CustomErrorMessageModel();
    InputStream fileInStream;
    try {
        /**
         * Getting all the area list
         */
        List<UtAreaEn> areaList = areaRepository.findAll();
        Map<String, Integer> areaMap = new HashMap<>();
        /**
         * Storing areaCode and areaName in a map
         */
        areaList.forEach(area -> areaMap.put(String.valueOf(area.getArea_ID()), area.getArea_NId()));
        /**
         * This line will throw FileNotFoundException when a file with the specified pathname does not exist
         */
        fileInStream = new ByteArrayInputStream(bytes);
        /**
         * This line will throw IOException for failed or interrupted I/O operations
         */
        XSSFWorkbook workbook = new XSSFWorkbook(fileInStream);
        List<UUIdGenerator> uuids = uuIdGeneratorRepository.findAll();
        List<String> allUUids = new ArrayList<>();
        uuids.forEach(value -> allUUids.add(value.getUuid()));
        /**
         * Checking UUID of the uploaded template
         */
        String wrongFile = messageSource.getMessage("wrong.excel", null, null);
        XSSFSheet mainSheet = workbook.getSheet("Main");
        if (mainSheet == null) {
            LOGGER.error(wrongFile);
            workbook.close();
            return errorMsg(messageModel, wrongFile);
        }
        XSSFRow row = mainSheet.getRow(1);
        if (row == null) {
            LOGGER.error(wrongFile);
            workbook.close();
            return errorMsg(messageModel, wrongFile);
        }
        XSSFCell cell = row.getCell(6);
        if (!allUUids.contains(cell.getStringCellValue())) {
            LOGGER.error(wrongFile);
            workbook.close();
            return errorMsg(messageModel, wrongFile);
        }
        /**
         * Getting all the Sheets from workbook and iterating the sheets
         */
        String sheetName = null;
        boolean checkData = false;
        List<UtData> utDatas = new ArrayList<>();
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            if (workbook.getSheetIndex(workbook.getSheetAt(i)) != 0 && workbook.getSheetIndex(workbook.getSheetAt(i)) != 1) {
                XSSFSheet sheet = workbook.getSheetAt(i);
                checkData = saveExcelData(sheet, areaMap, utDatas);
                if (checkData == true) {
                    sheetName = sheet.getSheetName();
                    break;
                }
            }
        }
        if (checkData == true) {
            workbook.close();
            return errorMsg(messageModel, messageSource.getMessage("wrong.cell.value", null, null) + "(" + sheetName + ").");
        }
        /**
         * Saving all sheet values in ut_data table
         */
        utDataRepository.save(utDatas);
        // saving the file
        String path = messageSource.getMessage("uploaded.file.path", null, null);
        String fileName = path + "des_" + timestampFormat.format(new Date()) + ".xlsx";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        FileOutputStream fos = new FileOutputStream(fileName);
        workbook.write(fos);
        workbook.close();
        fileInStream.close();
        LOGGER.info("File data saved successfully.");
        TemplateUploadMeta templateUploadMeta = new TemplateUploadMeta();
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attr.getRequest();
        String ipAddress = getIpAddr(request);
        XSSFSheet firstSheet = workbook.getSheet("Data sheet(1)");
        XSSFRow row1 = firstSheet.getRow(8);
        CellType type = row1.getCell(1).getCellTypeEnum();
        String dataProvidedBy = null;
        if (type == CellType.NUMERIC && !Integer.toString((int) row1.getCell(1).getNumericCellValue()).equals(""))
            dataProvidedBy = Integer.toString((int) row1.getCell(1).getNumericCellValue());
        else if (type == CellType.STRING && !row1.getCell(1).getStringCellValue().equals(""))
            dataProvidedBy = row1.getCell(1).getStringCellValue();
        else {
            UserDetailsModel user = (UserDetailsModel) request.getSession().getAttribute(Constants.USER_PRINCIPAL);
            dataProvidedBy = user.getUserName();
        }
        templateUploadMeta.setFileName(fileName);
        templateUploadMeta.setDateOfUpload(new Timestamp(new Date().getTime()));
        templateUploadMeta.setUserIp(ipAddress);
        templateUploadMeta.setDataProvidedBy(dataProvidedBy);
        templateUploadMetaRepository.save(templateUploadMeta);
        LOGGER.info("Successfully inserted in meta information table");
        messageModel.setMessage("Success");
        messageModel.setStatusCode(HttpServletResponse.SC_OK);
    } catch (FileNotFoundException e) {
        LOGGER.error("File not found.");
        messageModel.setMessage("Fail");
        messageModel.setStatusCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    } catch (IOException e) {
        LOGGER.error("Failed or interrupted I/O operations");
        messageModel.setMessage("Fail");
        messageModel.setStatusCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
    return messageModel;
}


public void putAllArea(XSSFSheet sheet,DataEntryModel dataEntryModel){
    int rowNum = 12;
    int colNum = 0;
    for (DataModel datamodel : dataEntryModel.getDataModel()) {
        XSSFRow row = sheet.createRow(rowNum);
        XSSFCell cell = row.createCell(colNum);
        cell.setCellValue(datamodel.getAreaCode());
        colNum++;
        cell = row.createCell(colNum);
        cell.setCellValue(datamodel.getLevel());
        colNum++;
        cell = row.createCell(colNum);
        cell.setCellValue(datamodel.getAreaName());
        rowNum++;
        colNum = 0;
    }
}


@Override
public List<DataModel> getArea(){
    List<UtAreaEn> areaList = areaRepository.findByAreaShortName("Block");
    List<DataModel> dataModelList = new ArrayList<>();
    for (UtAreaEn utAreaEn : areaList) {
        DataModel dataModel = new DataModel();
        dataModel.setAreaCode(utAreaEn.getArea_ID());
        dataModel.setLevel(utAreaEn.getArea_Level() == 1 ? "State" : utAreaEn.getArea_Level() == 2 ? "Division" : utAreaEn.getArea_Level() == 3 ? "District" : "Block");
        dataModel.setArealevel(utAreaEn.getArea_Level());
        dataModel.setAreaName(utAreaEn.getArea_Name());
        dataModel.setAreaParentNId(utAreaEn.getArea_Parent_NId());
        dataModel.setIsVisible(false);
        dataModel.setIsSelected(false);
        dataModel.setAreaCode(utAreaEn.getArea_ID());
        dataModel.setAreaNId(utAreaEn.getArea_NId());
        dataModelList.add(dataModel);
    }
    return dataModelList;
}


}
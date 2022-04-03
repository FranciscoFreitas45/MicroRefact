package com.ukefu.util.task;
 import org.elasticsearch.index.query.QueryBuilders.termQuery;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.springframework.data.domain.PageImpl;
import com.google.common.collect.ArrayListMultimap;
import com.ukefu.core.UKDataContext;
import com.ukefu.util.UKTools;
import com.ukefu.util.es.SearchTools;
import com.ukefu.util.es.UKDataBean;
import com.ukefu.util.extra.DataExchangeInterface;
import com.ukefu.webim.service.repository.JobDetailRepository;
import com.ukefu.webim.service.repository.ReporterRepository;
import com.ukefu.webim.web.model.JobDetail;
import com.ukefu.webim.web.model.MetadataTable;
import com.ukefu.webim.web.model.SysDic;
import com.ukefu.webim.web.model.TableProperties;
import com.ukefu.webim.web.model.UKeFuDic;
import DTO.DataExchangeInterface;
import DTO.SysDic;
import DTO.DataExchangeInterface;
public class ExcelImportProecess extends DataProcess{

 private  DecimalFormat format;

 private  AtomicInteger pages;

public ExcelImportProecess(DSDataEvent event) {
    super(event);
}
public TableProperties getTableProperties(DSDataEvent event,String title){
    TableProperties tableProperties = null;
    for (TableProperties tp : event.getDSData().getTask().getTableproperty()) {
        if (tp.getName().equals(title) || tp.getFieldname().equals(title)) {
            tableProperties = tp;
            break;
        }
    }
    return tableProperties;
}


public DecimalFormat getNumberFormat(String dataformat){
    DecimalFormat numberFormat = null;
    int index = dataformat.indexOf("_") > 0 ? dataformat.indexOf("_") : dataformat.indexOf(";");
    if (index > 0) {
        String format = dataformat.substring(0, index);
        if (format.matches("[\\d.]{1,}")) {
            numberFormat = new DecimalFormat(format);
        }
    }
    return numberFormat;
}


@SuppressWarnings("deprecation")
public String getValue(Cell cell){
    String strCell = "";
    if (cell != null) {
        short dt = cell.getCellStyle().getDataFormat();
        switch(cell.getCellType()) {
            case HSSFCell.CELL_TYPE_STRING:
                strCell = cell.getStringCellValue();
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN:
                strCell = String.valueOf(cell.getBooleanCellValue());
                break;
            case HSSFCell.CELL_TYPE_BLANK:
                strCell = "";
                break;
            case HSSFCell.CELL_TYPE_NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat sdf = null;
                    if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {
                        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    } else {
                        // 日期
                        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    }
                    strCell = sdf.format(cell.getDateCellValue());
                } else if (cell.getCellStyle().getDataFormat() == 58) {
                    // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    double value = cell.getNumericCellValue();
                    strCell = sdf.format(org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value));
                } else {
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        SimpleDateFormat sdf = null;
                        if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {
                            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        } else {
                            // 日期
                            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        }
                        strCell = sdf.format(cell.getDateCellValue());
                    } else {
                        boolean isNumber = isNumberFormat(dt);
                        if (isNumber) {
                            DecimalFormat numberFormat = getNumberFormat(cell.getCellStyle().getDataFormatString());
                            if (numberFormat != null) {
                                strCell = String.valueOf(numberFormat.format(cell.getNumericCellValue()));
                            } else {
                                strCell = String.valueOf(cell.getNumericCellValue());
                            }
                        } else {
                            strCell = String.valueOf(format.format(cell.getNumericCellValue()));
                        }
                    }
                }
                break;
            case HSSFCell.CELL_TYPE_FORMULA:
                {
                    // 判断当前的cell是否为Date
                    boolean isNumber = isNumberFormat(dt);
                    try {
                        if (isNumber) {
                            strCell = String.valueOf(cell.getNumericCellValue());
                        } else {
                            strCell = "";
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        strCell = cell.getRichStringCellValue().getString();
                    }
                    break;
                }
            default:
                strCell = "";
                break;
        }
        if (strCell.equals("") || strCell == null) {
            return "";
        }
    }
    return strCell;
}


@Override
public void process(){
    processExcel(event);
}


public boolean isExcel2007(String fileName){
    return fileName.matches("^.+\\.(?i)(xlsx)$");
}


@SuppressWarnings({ "deprecation", "unused" })
public String getDataType(Cell cell){
    String dataType = "string";
    if (cell != null) {
        short dt = cell.getCellStyle().getDataFormat();
        switch(cell.getCellType()) {
            case HSSFCell.CELL_TYPE_STRING:
                dataType = "string";
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN:
                dataType = "number";
                break;
            case HSSFCell.CELL_TYPE_BLANK:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {
                        dataType = "datetime";
                    } else {
                        // 日期
                        dataType = "datetime";
                    }
                } else if (cell.getCellStyle().getDataFormat() == 58) {
                    dataType = "datetime";
                } else {
                    boolean isNumber = isNumberFormat(dt);
                    if (isNumber) {
                        dataType = "number";
                    } else {
                        dataType = "string";
                    }
                }
                break;
            case HSSFCell.CELL_TYPE_NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {
                        dataType = "datetime";
                    } else {
                        // 日期
                        dataType = "datetime";
                    }
                } else if (cell.getCellStyle().getDataFormat() == 58) {
                    dataType = "datetime";
                } else {
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {
                            dataType = "datetime";
                        } else {
                            // 日期
                            dataType = "datetime";
                        }
                    } else {
                        boolean isNumber = isNumberFormat(dt);
                        if (isNumber) {
                            dataType = "number";
                        } else {
                            dataType = "string";
                        }
                    }
                }
                break;
            case HSSFCell.CELL_TYPE_FORMULA:
                {
                    // 判断当前的cell是否为Date
                    boolean isNumber = isNumberFormat(dt);
                    if (isNumber) {
                        dataType = "number";
                    } else {
                        dataType = "string";
                    }
                    break;
                }
            default:
                dataType = "string";
                break;
        }
    }
    return dataType;
}


public boolean isNumberFormat(short dataType){
    boolean number = false;
    switch(dataType) {
        case 180:
            number = true;
            break;
        case 181:
            number = true;
            break;
        case 182:
            number = true;
            break;
        case 178:
            number = true;
            break;
        case 177:
            number = true;
            break;
        case 176:
            number = true;
            break;
        case 183:
            number = true;
            break;
        case 185:
            number = true;
            break;
        case 186:
            number = true;
            break;
        case 179:
            number = true;
            break;
        case 187:
            number = true;
            break;
        case 7:
            number = true;
            break;
        case 8:
            number = true;
            break;
        case 44:
            number = true;
            break;
        case 10:
            number = true;
            break;
        case 12:
            number = true;
            break;
        case 13:
            number = true;
            break;
        case 188:
            number = true;
            break;
        case 189:
            number = true;
            break;
        case 190:
            number = true;
            break;
        case 191:
            number = true;
            break;
        case 192:
            number = true;
            break;
        case 193:
            number = true;
            break;
        case 194:
            number = true;
            break;
        case 11:
            number = true;
            break;
    }
    return number;
}


@SuppressWarnings({ "unchecked", "rawtypes" })
public void processExcel(DSDataEvent event){
    InputStream is = null;
    try {
        event.getDSData().getReport().setTableid(event.getDSData().getTask().getId());
        if (event.getDSData().getUser() != null) {
            event.getDSData().getReport().setUserid(event.getDSData().getUser().getId());
            event.getDSData().getReport().setUsername(event.getDSData().getUser().getUsername());
        }
        try {
            is = new FileInputStream(event.getDSData().getFile());
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        boolean isExcel2003 = true;
        if (isExcel2007(event.getDSData().getFile().getName())) {
            isExcel2003 = false;
        }
        Workbook wb = null;
        try {
            wb = isExcel2003 ? new HSSFWorkbook(is) : new XSSFWorkbook(is);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        long start = System.currentTimeMillis();
        if (wb != null) {
            Sheet sheet = wb.getSheetAt(0);
            Row titleRow = sheet.getRow(0);
            Row valueRow = sheet.getRow(1);
            int totalRows = sheet.getPhysicalNumberOfRows();
            int colNum = titleRow.getPhysicalNumberOfCells();
            for (int i = 2; i < totalRows && valueRow == null; i++) {
                valueRow = sheet.getRow(i);
                if (valueRow != null) {
                    break;
                }
            }
            /**
             * 需要检查Mapping 是否存在
             */
            Map<Object, List> refValues = new HashMap<Object, List>();
            MetadataTable table = event.getDSData().getTask();
            for (TableProperties tp : table.getTableproperty()) {
                if (tp.isReffk() && !StringUtils.isBlank(tp.getReftbid())) {
                    DataExchangeInterface exchange = (DataExchangeInterface) UKDataContext.getContext().getBean(tp.getReftbid());
                    refValues.put(tp.getFieldname(), exchange.getListDataByIdAndOrgi(null, null, event.getOrgi()));
                }
            }
            for (int i = 1; i < totalRows; i++) {
                Row row = sheet.getRow(i);
                Object data = null;
                if (row != null && !isRowEmpty(row)) {
                    if (event.getDSData().getClazz() != null) {
                        data = event.getDSData().getClazz().newInstance();
                    }
                    Map<Object, Object> values = new HashMap<Object, Object>();
                    ArrayListMultimap<String, Object> multiValues = ArrayListMultimap.create();
                    // 跳过数据校验
                    boolean skipDataVal = false;
                    StringBuffer pkStr = new StringBuffer(), allStr = new StringBuffer();
                    for (int col = 0; col < colNum; col++) {
                        Cell value = row.getCell(col);
                        Cell title = titleRow.getCell(col);
                        String titleValue = getValue(title);
                        if (!StringUtils.isBlank(titleValue)) {
                            titleValue = titleValue.trim();
                        }
                        TableProperties tableProperties = getTableProperties(event, titleValue);
                        if (tableProperties != null && value != null) {
                            String valuestr = getValue(value);
                            if (!StringUtils.isBlank(valuestr)) {
                                if (tableProperties.isModits()) {
                                    if (!StringUtils.isBlank(valuestr)) {
                                        multiValues.put(tableProperties.getFieldname(), valuestr);
                                    }
                                } else {
                                    if (tableProperties.isSeldata()) {
                                        SysDic sysDic = UKeFuDic.getInstance().getDicItem(valuestr);
                                        if (sysDic != null) {
                                            values.put(tableProperties.getFieldname(), sysDic.getName());
                                        } else {
                                            List<SysDic> dicItemList = UKeFuDic.getInstance().getSysDic(tableProperties.getSeldatacode());
                                            if (dicItemList != null && dicItemList.size() > 0) {
                                                for (SysDic dicItem : dicItemList) {
                                                    if (dicItem.getName().equals(valuestr)) {
                                                        values.put(tableProperties.getFieldname(), dicItem.isDiscode() ? dicItem.getCode() : dicItem.getId());
                                                        break;
                                                    }
                                                }
                                            }
                                        }
                                    } else if (tableProperties.isReffk() && refValues.get(tableProperties.getFieldname()) != null) {
                                        List keys = refValues.get(tableProperties.getFieldname());
                                        if (keys != null) {
                                            values.put(tableProperties.getFieldname(), getRefid(tableProperties, refValues.get(tableProperties.getFieldname()), valuestr));
                                        }
                                    } else {
                                        values.put(tableProperties.getFieldname(), valuestr);
                                    }
                                    if (tableProperties.isPk() && !tableProperties.getFieldname().equalsIgnoreCase("id")) {
                                        pkStr.append(valuestr);
                                    }
                                }
                                allStr.append(valuestr);
                            }
                            event.getDSData().getReport().setBytes(event.getDSData().getReport().getBytes() + valuestr.length());
                            event.getDSData().getReport().getAtompages().incrementAndGet();
                        }
                    }
                    values.put("orgi", event.getOrgi());
                    if (values.get("id") == null) {
                        if (pkStr.length() > 0) {
                            values.put("id", UKTools.md5(pkStr.append(event.getDSData().getTask().getTablename()).toString()));
                        } else {
                            values.put("id", UKTools.md5(allStr.append(event.getDSData().getTask().getTablename()).toString()));
                        }
                    }
                    if (event.getValues() != null && event.getValues().size() > 0) {
                        values.putAll(event.getValues());
                    }
                    values.putAll(multiValues.asMap());
                    String validFaildMessage = null;
                    for (TableProperties tp : table.getTableproperty()) {
                        if (!StringUtils.isBlank(tp.getDefaultvaluetitle())) {
                            String valuestr = (String) values.get(tp.getFieldname());
                            if (tp.getDefaultvaluetitle().indexOf("required") >= 0 && StringUtils.isBlank(valuestr)) {
                                skipDataVal = true;
                                validFaildMessage = "required";
                                break;
                            } else if (valuestr != null && (tp.getDefaultvaluetitle().indexOf("numstr") >= 0 && !valuestr.matches("[\\d]{1,}"))) {
                                skipDataVal = true;
                                validFaildMessage = "numstr";
                                break;
                            } else if (valuestr != null && (tp.getDefaultvaluetitle().indexOf("datenum") >= 0 || tp.getDefaultvaluetitle().indexOf("datetime") >= 0)) {
                                if (!valuestr.matches("[\\d]{4,4}-[\\d]{2,2}-[\\d]{2,2}") && !valuestr.matches("[\\d]{4,4}-[\\d]{2,2}-[\\d]{2} [\\d]{2,2}:[\\d]{2,2}:[\\d]{2,2}")) {
                                    skipDataVal = true;
                                    validFaildMessage = "datenum";
                                    break;
                                } else {
                                    if (valuestr.matches("[\\d]{4,4}-[\\d]{2,2}-{1,1}")) {
                                        if ("date".equals(tp.getDefaultfieldvalue())) {
                                            values.put(tp.getFieldname(), UKTools.simpleDateFormat.parse(valuestr));
                                        } else {
                                            values.put(tp.getFieldname(), UKTools.simpleDateFormat.format(UKTools.simpleDateFormat.parse(valuestr)));
                                        }
                                    } else if (valuestr.matches("[\\d]{4,4}-[\\d]{2,2}-[\\d]{2,2} [\\d]{2,2}:[\\d]{2,2}:[\\d]{2,2}")) {
                                        if ("date".equals(tp.getDefaultfieldvalue())) {
                                            values.put(tp.getFieldname(), UKTools.dateFormate.parse(valuestr));
                                        } else {
                                            values.put(tp.getFieldname(), UKTools.simpleDateFormat.format(UKTools.dateFormate.parse(valuestr)));
                                        }
                                    }
                                }
                            }
                        }
                        if (tp.isReffk() && !StringUtils.isBlank(tp.getReftbid()) && refValues.get(tp.getFieldname()) == null) {
                            DataExchangeInterface exchange = (DataExchangeInterface) UKDataContext.getContext().getBean(tp.getReftbid());
                            exchange.process(data, event.getOrgi());
                        }
                    }
                    if (!values.containsKey("orgi")) {
                        skipDataVal = true;
                    }
                    event.getDSData().getReport().setTotal(pages.intValue());
                    values.put("creater", event.getValues().get("creater"));
                    if (data != null && skipDataVal == false) {
                        UKTools.populate(data, values);
                        pages.incrementAndGet();
                        event.getDSData().getProcess().process(data);
                    } else if (data == null) {
                        /**
                         * 导入的数据，只写入ES
                         */
                        if (skipDataVal == true) {
                            // 跳过
                            values.put("status", "invalid");
                            values.put("validresult", "invalid");
                            values.put("validmessage", validFaildMessage != null ? validFaildMessage : "");
                        } else {
                            values.put("validresult", "valid");
                        }
                        values.put("status", UKDataContext.NamesDisStatusType.NOT.toString());
                        values.put("batid", event.getBatid());
                        values.put("createtime", System.currentTimeMillis());
                        values.put("callstatus", UKDataContext.NameStatusTypeEnum.NOTCALL.toString());
                        values.put("execid", event.getDSData().getReport().getId());
                        if (i % 500 == 0) {
                            UKDataContext.getContext().getBean(ReporterRepository.class).save(event.getDSData().getReport());
                        }
                        if (values.get("cusid") == null) {
                            /**
                             */
                            values.put("cusid", values.get("id"));
                        }
                        pages.incrementAndGet();
                        event.getDSData().getProcess().process(values);
                    /**
                     * 访客信息表
                     */
                    }
                    if (skipDataVal == true) {
                        // 跳过
                        errors.incrementAndGet();
                        continue;
                    }
                }
            }
        }
        event.setTimes(System.currentTimeMillis() - start);
        event.getDSData().getReport().setEndtime(new Date());
        event.getDSData().getReport().setAmount(String.valueOf((float) event.getTimes() / 1000f));
        event.getDSData().getReport().setStatus(UKDataContext.TaskStatusType.END.getType());
        event.getDSData().getReport().setTotal(pages.intValue());
        event.getDSData().getReport().setPages(pages.intValue());
        event.getDSData().getReport().setErrors(errors.intValue());
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (event.getDSData().getFile().exists()) {
            event.getDSData().getFile().delete();
        }
        event.getDSData().getProcess().end();
        /**
         * 更新数据
         */
        UKDataContext.getContext().getBean(ReporterRepository.class).save(event.getDSData().getReport());
        if (event.getDSData().getClazz() == null && !StringUtils.isBlank(event.getBatid())) {
            JobDetailRepository batchRes = UKDataContext.getContext().getBean(JobDetailRepository.class);
            JobDetail batch = this.event.getDSData().getJobDetail();
            if (batch == null) {
                batch = batchRes.findByIdAndOrgi(event.getBatid(), event.getOrgi());
            }
            if (batch != null) {
                BoolQueryBuilder queryBuilder = new BoolQueryBuilder();
                queryBuilder.must(termQuery("orgi", batch.getOrgi()));
                queryBuilder.must(termQuery("batid", batch.getId()));
                PageImpl<UKDataBean> aggregationBatchData = SearchTools.aggregationBatchData(queryBuilder, true, 0, 1, "batid");
                if (aggregationBatchData != null && aggregationBatchData.getContent().size() > 0) {
                    UKDataBean uKDataBean = aggregationBatchData.getContent().get(0);
                    // 总数
                    if (uKDataBean.getValues().get("total") != null) {
                        batch.setNamenum(Integer.parseInt(uKDataBean.getValues().get("total").toString()));
                    } else {
                        batch.setNamenum(0);
                    }
                    // 未分配
                    if (uKDataBean.getValues().get("status.not") != null) {
                        batch.setNotassigned(Integer.parseInt(uKDataBean.getValues().get("status.not").toString()));
                        // 已分配
                        batch.setAssigned(batch.getNamenum() - Integer.parseInt(uKDataBean.getValues().get("status.not").toString()));
                    } else {
                        batch.setNotassigned(0);
                        // 已分配
                        batch.setAssigned(batch.getNamenum());
                    }
                    // 有效
                    if (uKDataBean.getValues().get("validresult.valid") != null) {
                        batch.setValidnum(Integer.parseInt(uKDataBean.getValues().get("validresult.valid").toString()));
                    } else {
                        batch.setValidnum(0);
                    }
                    // 无效
                    if (uKDataBean.getValues().get("validresult.invalid") != null) {
                        batch.setInvalidnum(Integer.parseInt(uKDataBean.getValues().get("validresult.invalid").toString()));
                    } else {
                        batch.setInvalidnum(0);
                    }
                }
                batchRes.save(batch);
            }
        }
    }
}


public String getRefid(TableProperties tp,List<Object> dataList,String value){
    String id = "";
    for (Object data : dataList) {
        Object target = null;
        if (PropertyUtils.isReadable(data, "name")) {
            target = BeanUtils.getProperty(data, "name");
            if (target != null && target.equals(value)) {
                id = BeanUtils.getProperty(data, "id");
            }
        }
        if (PropertyUtils.isReadable(data, "tag")) {
            target = BeanUtils.getProperty(data, "tag");
            if (target != null && target.equals(value)) {
                id = BeanUtils.getProperty(data, "id");
            }
        }
        if (StringUtils.isBlank(id) && PropertyUtils.isReadable(data, "title")) {
            target = BeanUtils.getProperty(data, "title");
            if (target != null && target.equals(value)) {
                id = BeanUtils.getProperty(data, "id");
            }
        }
        if (StringUtils.isBlank(id)) {
            target = BeanUtils.getProperty(data, "id");
            if (target != null && target.equals(value)) {
                id = target.toString();
            }
        }
    }
    return id;
}


@SuppressWarnings("deprecation")
public boolean isRowEmpty(Row row){
    for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
        Cell cell = row.getCell(c);
        if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK)
            return false;
    }
    return true;
}


}
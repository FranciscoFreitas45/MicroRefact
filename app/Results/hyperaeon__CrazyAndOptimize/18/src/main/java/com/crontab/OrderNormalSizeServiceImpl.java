package com.crontab;
 import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import com.google.gson.Gson;
import com.ssc.faw.util.GenException;
import com.test.CurrencyPairGroup;
public class OrderNormalSizeServiceImpl implements OrderNormalSizeService{

 private  Logger LOG;

 public  String NO_FX_ORDER_FROM_DB;

 public  String GET_EXISTING_NORMAL_SIZE_FAIL;

 public  String HOST;

 public  String MODULE;

 public  String DATE_RANGE_KEY;

 public  String MIN_ORDER_COUNT_KEY;

 public  String KEY_4_CALC_SIZE_FLAG;

 public  String CALC_SIZE_FLAG_YES;

 public  String CALC_SIZE_FLAG_NO;

 public  String VALIDATION_ID;

 public  int DEFAULT_DATE_RANGE;

 public  int DEFAULT_MIN_ORDER_COUNT;

 private  FxOrderService fxOrderService;

 private  OrderNormalSizeDao orderNormalSizeDao;

 private  PricingServiceDao pricingServiceDao;

 private  NbaResourceConfigDao nbaResourceConfigDao;

 private  FutureTask<String> future;


public void initSummary(OrderNormalSizeSummary summary,OrderNormalSizeKey orderKey,FxOrder fxOrder){
    List<BigDecimal> ccy1AmountList = new ArrayList<BigDecimal>();
    List<BigDecimal> ccy2AmountList = new ArrayList<BigDecimal>();
    summary.setKey(orderKey);
    ccy1AmountList.add(fxOrder.getBaseCrncyAmt());
    ccy2AmountList.add(fxOrder.getCntrCrncyAmt());
    summary.setCcy1AmountList(ccy1AmountList);
    summary.setCcy2AmountList(ccy2AmountList);
}


public OrderNormalSizeKey createOrderKey(FxOrder fxOrder){
    OrderNormalSizeKey orderKey = new OrderNormalSizeKey();
    orderKey.setPricingServiceId(fxOrder.getPricingServiceId());
    orderKey.setFund(fxOrder.getCustId());
    orderKey.setCcy1(fxOrder.getBaseCrncyId());
    orderKey.setCcy2(fxOrder.getCntrCrncyId());
    return orderKey;
}


public String getRealCalculateValue(){
    if (future.get() != null) {
        return future.get();
    }
    return "Calculating...";
}


public void setFxOrderService(FxOrderService fxOrderService){
    this.fxOrderService = fxOrderService;
}


@Override
public String calculateAllNormalSize(Calculator calculator,String user,String filterJson){
    String returnMsg = "Success";
    List<String> logNumKeyList = null;
    JsonCalculationFilter filter = null;
    if (filterJson != null) {
        try {
            Gson gson = new Gson();
            filter = gson.fromJson(filterJson, JsonCalculationFilter.class);
            logNumKeyList = filter.getLogNumKeyList();
        } catch (Exception e) {
            LOG.warn("Exception when parse filterJson, so ignore it.", e);
        }
    }
    // get date range, if 0 then use defaultDateRange
    int dateRange = getDateRage();
    // get fx order for normal size calculation by date range and filterJson
    List<FxOrder> fxOrderList = getFxOrderForNormalSize(dateRange, logNumKeyList);
    if (fxOrderList == null) {
        return NO_FX_ORDER_FROM_DB;
    }
    // group the fx order by OrderNormalSizeKey
    List<OrderNormalSizeSummary> summaryList = groupFxOrder(fxOrderList);
    // get minimal order count
    int minOrderCount = getMinOrderCount();
    Map<String, BigDecimal> defaultValueMap = getDefaultValue();
    // calculate normal size for all summary, if size < minOrderCount then set ZERO
    calculateNormalSizeForSummary(calculator, summaryList, minOrderCount, defaultValueMap);
    // convert summary list to JsonOrderNormalSize
    List<JsonOrderNormalSize> jsonList = toJsonList(summaryList);
    // get existing order normal size
    List<JsonOrderNormalSize> existingJsonList = null;
    try {
        existingJsonList = orderNormalSizeDao.queryAllWithoutValue();
        if (existingJsonList != null) {
            for (JsonOrderNormalSize jons : existingJsonList) {
                BigDecimal value = defaultValueMap.get(jons.getCurrencyPairGroup().getPricingServiceId());
                if (value != null) {
                    jons.setCcy1NormalSize(value);
                    jons.setCcy2NormalSize(value);
                } else {
                    jons.setCcy1NormalSize(BigDecimal.ZERO);
                    jons.setCcy2NormalSize(BigDecimal.ZERO);
                }
            }
            LOG.debug("Existing order normal size count: " + existingJsonList.size());
        } else {
            LOG.debug("Existing order normal size is null.");
            return GET_EXISTING_NORMAL_SIZE_FAIL;
        }
    } catch (Exception e1) {
        LOG.error(GET_EXISTING_NORMAL_SIZE_FAIL, e1);
        return GET_EXISTING_NORMAL_SIZE_FAIL;
    }
    // It is for testing purpose to reduce calculation and order normal size calculation scope
    List<JsonOrderNormalSize> filteredExistingJsonList = filterExistingJsonList(existingJsonList, filter);
    // combine existing order normal size with new order normal size
    List<JsonOrderNormalSize> toBeUpdatedList = combineWithExistingRecords(jsonList, filteredExistingJsonList);
    // FIXME need to remove belows ?
    if (toBeUpdatedList != null) {
        for (JsonOrderNormalSize json : toBeUpdatedList) {
            LOG.debug(json.toString());
        }
    }
    // save all JsonOrderNormalSize
    List<JsonOrderNormalSize> fails = null;
    try {
        fails = orderNormalSizeDao.updateJsonOrderNormalSize(toBeUpdatedList, user);
    } catch (Exception e) {
        LOG.error(e.getMessage(), e);
        return e.getMessage();
    }
    if (fails != null && !fails.isEmpty()) {
        returnMsg = "There are " + fails.size() + " failed order normal size when inserting them to database.";
    }
    return returnMsg;
}


public void setOrderNormalSizeDao(OrderNormalSizeDao orderNormalSizeDao){
    this.orderNormalSizeDao = orderNormalSizeDao;
}


public Map<String,BigDecimal> getDefaultValue(){
    Map<String, BigDecimal> defaultValueMap = null;
    try {
        defaultValueMap = orderNormalSizeDao.queryDefaultValue(VALIDATION_ID);
    } catch (GenException e2) {
        LOG.error(e2.getMessage(), e2);
    }
    return defaultValueMap;
}


public void setCCY1DefaultValue(OrderNormalSizeSummary summary,Map<String,BigDecimal> map){
    BigDecimal value = map.get(summary.getKey().getPricingServiceId());
    if (value != null) {
        summary.setCcy1NormalSize(value);
    } else {
        summary.setCcy1NormalSize(BigDecimal.ZERO);
    }
}


public List<JsonOrderNormalSize> toJsonList(List<OrderNormalSizeSummary> summaryList){
    List<JsonOrderNormalSize> list = new ArrayList<JsonOrderNormalSize>();
    for (OrderNormalSizeSummary summary : summaryList) {
        JsonOrderNormalSize jsonOrder = new JsonOrderNormalSize();
        jsonOrder.setCcy1NormalSize(summary.getCcy1NormalSize());
        jsonOrder.setCcy2NormalSize(summary.getCcy2NormalSize());
        jsonOrder.setCustId(summary.getKey().getFund());
        CurrencyPairGroup pairGroup = new CurrencyPairGroup();
        pairGroup.setCcy1(summary.getKey().getCcy1());
        pairGroup.setCcy2(summary.getKey().getCcy2());
        pairGroup.setPricingServiceId(summary.getKey().getPricingServiceId());
        jsonOrder.setCurrencyPairGroup(pairGroup);
        list.add(jsonOrder);
    }
    return list;
}


@Override
public void calculateNormalSizeAsNeeded(){
    LOG.info("start to init normal size...");
    NbaResourceConfigDao dao = this.nbaResourceConfigDao;
    NbaResourceConfig config = dao.queryResourceConfigByKey(HOST, MODULE, KEY_4_CALC_SIZE_FLAG);
    if (config != null && StringUtils.equalsIgnoreCase(CALC_SIZE_FLAG_YES, config.getValue())) {
        LOG.error("calc size starts...");
        // calc size first
        this.calculateAllNormalSize();
        // set flag to N
        config.setValue(CALC_SIZE_FLAG_NO);
        config.setUpdateDate(new Date());
        dao.updateResourceConfig(config);
        LOG.error("end of calc-ing size");
    }
    LOG.info("end of initializing normal size: success");
}


public void setCCY2DefaultValue(OrderNormalSizeSummary summary,Map<String,BigDecimal> map){
    BigDecimal value = map.get(summary.getKey().getPricingServiceId());
    if (value != null) {
        summary.setCcy2NormalSize(value);
    } else {
        summary.setCcy2NormalSize(BigDecimal.ZERO);
    }
}


public void setNbaResourceConfigDao(NbaResourceConfigDao nbaResourceConfigDao){
    this.nbaResourceConfigDao = nbaResourceConfigDao;
}


@Override
public void deleteOrderNormalSize(String productCategoryId,String customerId,String ccy1,String ccy2){
    try {
        orderNormalSizeDao.deleteOrderNormalSize(productCategoryId, customerId, ccy1, ccy2);
    } catch (Exception e) {
        LOG.error(e.getMessage(), e);
    }
}


public int getMinOrderCountFromDB(){
    int result;
    try {
        NbaResourceConfig config = nbaResourceConfigDao.queryResourceConfigByKey(HOST, MODULE, MIN_ORDER_COUNT_KEY);
        if (config == null) {
            result = 0;
        } else {
            LOG.debug("MinOrderCount : " + config.getValue());
            result = Integer.parseInt(config.getValue());
        }
    } catch (Exception e) {
        LOG.error("Cannot get a min order count from db", e);
        result = 0;
    }
    LOG.debug("final MinOrderCount is " + result);
    return result;
}


public boolean isAggregate(LegacyFxOrder legacyFxOrder){
    boolean result = false;
    if ("Y".equalsIgnoreCase(legacyFxOrder.getIsAggregate())) {
        result = true;
    } else {
        result = false;
    }
    return result;
}


public List<JsonOrderNormalSize> filterExistingJsonList(List<JsonOrderNormalSize> existingJsonList,JsonCalculationFilter filter){
    if (filter == null) {
        return existingJsonList;
    }
    if (existingJsonList == null) {
        return existingJsonList;
    }
    List<JsonOrderNormalSize> filteredExistingJsonList = new ArrayList<JsonOrderNormalSize>();
    List<String> custIdList = filter.getCustIdList();
    List<List<String>> ccyPairList = filter.getSplitedCurrencyPairList();
    for (JsonOrderNormalSize json : existingJsonList) {
        boolean flag1 = false;
        if (custIdList != null) {
            for (String custId : custIdList) {
                if (json.getCustId().equals(custId)) {
                    flag1 = true;
                    break;
                }
            }
        } else {
            flag1 = true;
        }
        boolean flag2 = false;
        if (ccyPairList != null) {
            for (List<String> ccyPair : ccyPairList) {
                if (json.getCurrencyPairGroup().getCcy1().equals(ccyPair.get(0)) && json.getCurrencyPairGroup().getCcy2().equals(ccyPair.get(1))) {
                    flag2 = true;
                    break;
                }
            }
        } else {
            flag2 = true;
        }
        if (flag1 && flag2) {
            filteredExistingJsonList.add(json);
        }
    }
    return filteredExistingJsonList;
}


public List<FxOrder> getFxOrderForNormalSize(int dateRange,List<String> logNumKeyList){
    List<LegacyFxOrder> legacyFxOrderList = fxOrderService.getFxOrderInRange(dateRange, logNumKeyList);
    if (legacyFxOrderList == null) {
        LOG.warn(NO_FX_ORDER_FROM_DB);
        return null;
    }
    List<PricingService> pricingServiceList;
    try {
        pricingServiceList = pricingServiceDao.loadAll();
    } catch (GenException e) {
        LOG.error("Exception found when get pricing service from database", e);
        pricingServiceList = null;
    }
    if (pricingServiceList == null) {
        LOG.error("No pricing service retrieved from database.");
        return null;
    }
    Map<String, String> pricingServiceIdMap = new HashMap<String, String>();
    for (PricingService pricingService : pricingServiceList) {
        pricingServiceIdMap.put(pricingService.getOmsProdCateId(), pricingService.getPricingServiceId());
    }
    List<FxOrder> returnList = new ArrayList<FxOrder>();
    for (LegacyFxOrder legacyFxOrder : legacyFxOrderList) {
        // Exclude aggregate order
        if (isAggregate(legacyFxOrder)) {
            LOG.debug(legacyFxOrder.getOrdrId() + " is aggregate order, skip.");
            continue;
        }
        // Exclude if it is offsetting or position trade
        if (!isClient(legacyFxOrder)) {
            LOG.debug(legacyFxOrder.getOrdrId() + " is not client order, skip.");
            continue;
        }
        FxOrder fxOrder = convertToFxOrder(legacyFxOrder, pricingServiceIdMap);
        if (fxOrder.getPricingServiceId() == null) {
            continue;
        }
        returnList.add(fxOrder);
    }
    for (FxOrder order : returnList) {
        LOG.debug(order.getOrdrId() + ", " + order.getPricingServiceId() + ", " + order.getCustId() + ", " + order.getBaseCrncyId() + ", " + order.getBaseCrncyAmt() + ", " + order.getCntrCrncyId() + ", " + order.getCntrCrncyAmt());
    }
    return returnList;
}


public void calculateNormalSizeForSummary(Calculator calculator,List<OrderNormalSizeSummary> summaryList,int minOrderCount,Map<String,BigDecimal> map){
    for (OrderNormalSizeSummary summary : summaryList) {
        if (summary.getCcy1AmountList().size() > minOrderCount) {
            BigDecimal ccy1NormalSize = calculator.calculate(summary.getCcy1AmountList());
            summary.setCcy1NormalSize(ccy1NormalSize);
        } else {
            setCCY1DefaultValue(summary, map);
        }
        if (summary.getCcy2AmountList().size() > minOrderCount) {
            BigDecimal ccy2NormalSize = calculator.calculate(summary.getCcy2AmountList());
            summary.setCcy2NormalSize(ccy2NormalSize);
        } else {
            setCCY2DefaultValue(summary, map);
        }
    }
}


public int getDateRage(){
    int dbDateRange = getDateRangeFromDB();
    int dateRange;
    if (dbDateRange == 0) {
        dateRange = DEFAULT_DATE_RANGE;
    } else {
        dateRange = dbDateRange;
    }
    LOG.debug("final dateRange is " + dateRange);
    return dateRange;
}


public List<OrderNormalSizeSummary> groupFxOrder(List<FxOrder> fxOrderList){
    List<OrderNormalSizeSummary> list = new ArrayList<OrderNormalSizeSummary>();
    Map<OrderNormalSizeKey, OrderNormalSizeSummary> summaryMap = new HashMap<OrderNormalSizeKey, OrderNormalSizeSummary>();
    for (FxOrder fxOrder : fxOrderList) {
        OrderNormalSizeKey orderKey = createOrderKey(fxOrder);
        if (summaryMap.get(orderKey) != null) {
            OrderNormalSizeSummary summary = summaryMap.get(orderKey);
            addOrderToSummary(summary, fxOrder);
        } else {
            OrderNormalSizeSummary summary = new OrderNormalSizeSummary();
            initSummary(summary, orderKey, fxOrder);
            summaryMap.put(orderKey, summary);
        }
    }
    list.addAll(summaryMap.values());
    return list;
}


public boolean isClient(LegacyFxOrder legacyFxOrder){
    boolean result = false;
    if (!"Y".equalsIgnoreCase(legacyFxOrder.getIsBackToBack())) {
        result = true;
    } else {
        if ("MO".equalsIgnoreCase(legacyFxOrder.getOrdrTypeId()) || "BO".equalsIgnoreCase(legacyFxOrder.getOrdrTypeId())) {
            result = true;
        } else {
            result = false;
        }
    }
    return result;
}


public void setPricingServiceDao(PricingServiceDao pricingServiceDao){
    this.pricingServiceDao = pricingServiceDao;
}


public int getDateRangeFromDB(){
    int result;
    try {
        NbaResourceConfig config = nbaResourceConfigDao.queryResourceConfigByKey(HOST, MODULE, DATE_RANGE_KEY);
        if (config == null) {
            result = 0;
        } else {
            LOG.debug("DATE_RANGE : " + config.getValue());
            result = Integer.parseInt(config.getValue());
        }
    } catch (Exception e) {
        LOG.warn("Cannot get a valid date range from db", e);
        result = 0;
    }
    return result;
}


@Override
public String call(){
    return calculateAllNormalSize();
}


public boolean isSameJsonOrderNormalSizeKey(JsonOrderNormalSize json1,JsonOrderNormalSize json2){
    if (!json1.getCustId().equals(json2.getCustId())) {
        return false;
    }
    CurrencyPairGroup cpg1 = json1.getCurrencyPairGroup();
    CurrencyPairGroup cpg2 = json2.getCurrencyPairGroup();
    if (!cpg1.getPricingServiceId().equals(cpg2.getPricingServiceId())) {
        return false;
    }
    if (cpg1.getCcy1().equals(cpg2.getCcy1()) && cpg1.getCcy2().equals(cpg2.getCcy2())) {
        return true;
    }
    if (cpg1.getCcy1().equals(cpg2.getCcy2()) && cpg1.getCcy2().equals(cpg2.getCcy1())) {
        return true;
    }
    return false;
}


public String calculateAllNormalSizeAsync(){
    CalculateAsync ca = new CalculateAsync();
    future = new FutureTask<String>(ca);
    new Thread(future).start();
    return "Calculating";
}


public int getMinOrderCount(){
    int dbMinOrderCount = getMinOrderCountFromDB();
    int minOrderCount;
    if (dbMinOrderCount == 0) {
        minOrderCount = DEFAULT_MIN_ORDER_COUNT;
    } else {
        minOrderCount = dbMinOrderCount;
    }
    return minOrderCount;
}


public List<JsonOrderNormalSize> combineWithExistingRecords(List<JsonOrderNormalSize> jsonList,List<JsonOrderNormalSize> existingJsonList){
    List<JsonOrderNormalSize> result = new ArrayList<JsonOrderNormalSize>();
    // Add all of jsonList to result
    result.addAll(jsonList);
    for (JsonOrderNormalSize json1 : existingJsonList) {
        boolean exist = false;
        for (JsonOrderNormalSize json2 : jsonList) {
            if (isSameJsonOrderNormalSizeKey(json1, json2)) {
                exist = true;
                break;
            }
        }
        if (!exist) {
            result.add(json1);
        }
    }
    return result;
}


public void addOrderToSummary(OrderNormalSizeSummary summary,FxOrder fxOrder){
    if (summary.getKey().getCcy1().equals(fxOrder.getBaseCrncyId())) {
        summary.getCcy1AmountList().add(fxOrder.getBaseCrncyAmt());
        summary.getCcy2AmountList().add(fxOrder.getCntrCrncyAmt());
    } else {
        summary.getCcy1AmountList().add(fxOrder.getCntrCrncyAmt());
        summary.getCcy2AmountList().add(fxOrder.getBaseCrncyAmt());
    }
}


public FxOrder convertToFxOrder(LegacyFxOrder legacyFxOrder,Map<String,String> pricingServiceIdMap){
    FxOrder fxOrder = new FxOrder();
    fxOrder.setOrdrId(legacyFxOrder.getOrdrId());
    String pricingServiceId = pricingServiceIdMap.get(legacyFxOrder.getProductCategoryId());
    fxOrder.setPricingServiceId(pricingServiceId);
    if (pricingServiceId == null) {
        LOG.debug(fxOrder.getOrdrId() + " cannot get pricing service id, skip.");
    }
    fxOrder.setCustId(legacyFxOrder.getCustId());
    fxOrder.setBaseCrncyId(legacyFxOrder.getBaseCrncyId());
    fxOrder.setCntrCrncyId(legacyFxOrder.getCntrCrncyId());
    fxOrder.setBaseCrncyAmt(legacyFxOrder.getBaseCrncyAmt());
    fxOrder.setCntrCrncyAmt(legacyFxOrder.getCntrCrncyAmt());
    return fxOrder;
}


}
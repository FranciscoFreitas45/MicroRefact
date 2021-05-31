import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.sdrc.childinfo.domain.CounterCount;
import org.sdrc.childinfo.model.LineSeries;
import org.sdrc.childinfo.model.UtDataCollection;
import org.sdrc.childinfo.model.UtDataModel;
import org.sdrc.childinfo.model.ValueObject;
import org.sdrc.childinfo.repository.CounterCountRepository;
import org.sdrc.childinfo.util.Constants;
import org.sdrc.childinfo.util.DataComparator;
import org.sdrc.devinfo.domain.UtAreaEn;
import org.sdrc.devinfo.domain.UtData;
import org.sdrc.devinfo.domain.UtIcIus;
import org.sdrc.devinfo.domain.UtIndicatorClassificationsEn;
import org.sdrc.devinfo.domain.UtIndicatorEn;
import org.sdrc.devinfo.domain.UtSubgroupValsEn;
import org.sdrc.devinfo.domain.UtTimeperiod;
import org.sdrc.devinfo.domain.UtUnitEn;
import org.sdrc.devinfo.repository.IndicatorRepository;
import org.sdrc.devinfo.repository.SectorRepository;
import org.sdrc.devinfo.repository.SourceRepository;
import org.sdrc.devinfo.repository.UtDataRepository;
import org.sdrc.devinfo.repository.UtTimeperiodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class DashboardServiceImpl implements org.sdrc.childinfo.service.DashboardService,DashboardService{

@Autowired
 private  IndicatorRepository indicatorRepository;

@Autowired
 private  UtTimeperiodRepository utTimeperiodRepository;

@Autowired
 private  SectorRepository SectorRepository;

@Autowired
 private  SourceRepository sourceRepository;

@Autowired
 private  UtDataRepository dataRepository;

 private  Map<String,Integer> ranks;

 private  Map<String,List<LineSeries>> dataByArea;

 private  Map<String,Map<String,List<ValueObject>>> dataByTPBYSource;

 private  List<String> topPerformers;

 private  List<String> bottomPerformers;

@Autowired
 private  CounterCountRepository counterCountRepository;


public Double getProjectionDataCalculation(List<Object[]> data,Integer areaNId,double timePeriod2Value,Integer calculatedYear){
    double projectedValue = 0.00;
    if (data != null && !data.isEmpty()) {
        List<ValueObject> list = new ArrayList<ValueObject>();
        UtData utData = null;
        UtAreaEn utAreaEn = null;
        Double value = null;
        for (Object[] dataObject : data) {
            UtDataModel utDataModel = new UtDataModel();
            utData = (UtData) dataObject[0];
            utAreaEn = (UtAreaEn) dataObject[1];
            value = utData.getData_Value();
            double pt1, pt2, growthRate;
            pt1 = timePeriod2Value;
            pt2 = utData.getData_Value();
            if (areaNId == utAreaEn.getArea_NId()) {
                System.out.println("--Pt1----" + pt1 + "-----------Pt2---" + pt2 + "-----" + utAreaEn.getArea_Name());
                // growthRate=((pt2-pt1)/pt1*100)/10;
                // projectedValue=Math.round(pt2+((growthRate*pt2)*calculatedYear)/100);
                growthRate = (pt2 - pt1) / 10;
                projectedValue = Math.round(pt2 + (growthRate * calculatedYear));
            }
        }
    }
    return projectedValue != 0.0 ? projectedValue : 0;
}


@Override
public UtDataCollection fetchBurdenData(Integer iusNIdForProjection,Integer iusNId,Integer iusNIdForIMRorU5MR,String parentAreaCode,String timeperiodId,Integer childLevel,Integer sourceNIdForProjection,Integer sourceNId,Integer sourceNIdForIMRorU5MR){
    System.out.println("Fetch Data of DashBoard Controller is called");
    if (parentAreaCode != null) {
        System.out.println(" === Area Code === " + parentAreaCode);
    }
    dataByArea = null;
    ranks = null;
    dataByTPBYSource = null;
    // get all areas less than or equal to the selected level
    UtAreaEn[] utAreas = dataRepository.getAreaNid(parentAreaCode, childLevel);
    // get parentArea from the area list
    UtAreaEn area = getParentUtArea(utAreas, parentAreaCode);
    ArrayList<UtAreaEn> list = new ArrayList<UtAreaEn>();
    // get children of the select area.
    getChildren(utAreas, childLevel, area.getArea_NId(), list);
    Integer[] areaNids = new Integer[list.size()];
    int i = 0;
    for (UtAreaEn utAreaEn : list) {
        areaNids[i] = utAreaEn.getArea_NId();
        i++;
    }
    UtDataCollection utDataCollection = getBurdenUtdataCollection(iusNIdForProjection, iusNId, iusNIdForIMRorU5MR, areaNids, timeperiodId, sourceNIdForProjection, sourceNId, sourceNIdForIMRorU5MR);
    return utDataCollection;
}


public Double getBurdenDataCalculation(List<Object[]> data,Integer areaNId,double projectedValue){
    double value = 0.00;
    if (data != null && !data.isEmpty()) {
        List<ValueObject> list = new ArrayList<ValueObject>();
        UtData utData = null;
        UtAreaEn utAreaEn = null;
        for (Object[] dataObject : data) {
            UtDataModel utDataModel = new UtDataModel();
            utData = (UtData) dataObject[0];
            utAreaEn = (UtAreaEn) dataObject[1];
            double cbrValue = utData.getData_Value();
            if (areaNId == utAreaEn.getArea_NId()) {
                System.out.println(cbrValue + "--cbrValue-------------p2--" + projectedValue);
                value = Math.round((cbrValue * projectedValue) / 1000);
            }
        }
    }
    return value != 0.0 ? value : 0;
}


public UtDataCollection getComputeUtdataCollection(String iusNId,Integer[] areaNid,String timePeriodNid,Integer sourceNId){
    UtDataCollection collection = new UtDataCollection();
    // // this will fetch the data for the selected time-period
    // // fetch the data for the selected time-period
    String timePeriodStr = getDecadesvalue(Double.parseDouble(timePeriodNid));
    List<String> decadeTimePeriodList = new ArrayList<String>();
    decadeTimePeriodList.add(timePeriodStr.split(",")[0]);
    decadeTimePeriodList.add(timePeriodStr.split(",")[1]);
    List<UtTimeperiod> timeperioddata = dataRepository.findTimePeriodNIdByDecadeTimePeriod(decadeTimePeriodList);
    List<Integer> decadeTimePeriodNId = new ArrayList<Integer>();
    List<Object[]> fetchdata = new ArrayList<Object[]>();
    List<List<Object[]>> datalist = new ArrayList<List<Object[]>>();
    for (UtTimeperiod timeperioddataObject : timeperioddata) {
        decadeTimePeriodNId.add(timeperioddataObject.getTimePeriod_NId());
        fetchdata = dataRepository.findDataByTimePeriod(Integer.parseInt(iusNId), timeperioddataObject.getTimePeriod_NId(), sourceNId, areaNid);
        datalist.add(fetchdata);
    }
    collection = getProjectionData(datalist, Integer.parseInt(timePeriodStr.split(",")[2]), iusNId);
    return collection;
}


@Override
public List<List<LineSeries>> fetchChartData(Integer iusNid,Integer areaNid){
    System.out.println("chart data controller called");
    List<List<LineSeries>> LineCharts = new ArrayList<>();
    List<LineSeries> dataSeries = new ArrayList<>();
    List<Object[]> listData = dataRepository.findData(iusNid, areaNid);
    populateDataByTimePeroid(listData);
    String indicatorId = iusNid.toString();
    if (listData != null && !listData.isEmpty()) {
        for (int i = 0; i < listData.size(); i++) {
            Object[] dataObjects = listData.get(i);
            LineSeries lineChat = new LineSeries();
            for (Object dataObject : dataObjects) {
                if (dataObject instanceof UtIndicatorClassificationsEn) {
                    UtIndicatorClassificationsEn classificationsEn = (UtIndicatorClassificationsEn) dataObject;
                    lineChat.setSource(classificationsEn.getIC_Short_Name());
                } else if (dataObject instanceof UtData) {
                    UtData data = (UtData) dataObject;
                    lineChat.setValue(data.getData_Value());
                } else if (dataObject instanceof UtTimeperiod) {
                    UtTimeperiod timeperiod = (UtTimeperiod) dataObject;
                    lineChat.setDate(timeperiod.getTimePeriod());
                }
            }
            for (Object[] dataObject : listData) {
                UtData utData = (UtData) dataObject[0];
                UtAreaEn utAreaEn = (UtAreaEn) dataObject[1];
                UtTimeperiod utTimeperiod = (UtTimeperiod) dataObject[2];
                UtIndicatorClassificationsEn classificationsEn = (UtIndicatorClassificationsEn) dataObject[3];
                String areaCode = utAreaEn.getArea_ID();
                List<ValueObject> list = dataByTPBYSource.get(areaCode).get(classificationsEn.getIC_Short_Name());
                if (utData.getUnit_NId() == 2) {
                    BigDecimal percentChange = list.size() >= 2 && (Double) (list.get(list.size() - 2).getValue()) > 0 ? new BigDecimal(((Math.abs((Double) (list.get(list.size() - 2).getValue()) - (Double) (list.get(list.size() - 1).getValue()))) / ((Double) (list.get(list.size() - 2).getValue()))) * 100).setScale(2, BigDecimal.ROUND_HALF_UP) : null;
                    lineChat.setPercentageChange(percentChange != null ? percentChange.toString() : null);
                    lineChat.setIsPositive(percentChange != null && ((Double) (list.get(listData.size() - 1).getValue()) > (Double) (list.get(listData.size() - 2).getValue()) ? isPositveIndicator(indicatorId) == 1 : isPositveIndicator(indicatorId) != 0));
                } else {
                    BigDecimal percentChange = list.size() >= 2 ? new BigDecimal((Double) (list.get(list.size() - 1).getValue()) - (Double) (list.get(list.size() - 2).getValue())).setScale(2, BigDecimal.ROUND_HALF_UP) : null;
                    lineChat.setPercentageChange(percentChange != null ? percentChange.toString() : null);
                    // lineChat.setIsPositive(percentChange != null ? percentChange.doubleValue() >= 0 ? isPositveIndicator(indicatorId)==1 ? true: false: isPositveIndicator(indicatorId)==0 ? false: true: false);
                    lineChat.setIsPositive(percentChange != null && (percentChange.doubleValue() >= 0 ? isPositveIndicator(indicatorId) == 1 : isPositveIndicator(indicatorId) != 1));
                }
            }
            dataSeries.add(lineChat);
        }
    }
    LineCharts.add(dataSeries);
    return LineCharts;
}


@Override
public List<ValueObject> fetchAllSectors(String indicatorType){
    // TODO Auto-generated method stub
    List<Object[]> listofSectors = SectorRepository.findByIC_Type(indicatorType);
    List<ValueObject> list = new ArrayList<ValueObject>();
    for (Object[] object : listofSectors) {
        list.add(new ValueObject((Integer) object[0], object[1].toString(), (Integer) object[2]));
    }
    return list;
}


@Override
public List<ValueObject> fetchSourcesByLevel(String param,Integer level){
    // TODO Auto-generated method stub
    System.out.println("IusNid==>" + Integer.parseInt(param));
    List<UtIndicatorClassificationsEn> classificationsEns = sourceRepository.findByIUSandLevel_Nid(Integer.parseInt(param), level);
    List<ValueObject> valueObjects = new ArrayList<>();
    for (UtIndicatorClassificationsEn classificationsEn : classificationsEns) {
        ValueObject object = new ValueObject();
        object.setKey(new Integer(classificationsEn.getIC_NId()).toString());
        object.setValue(classificationsEn.getIC_Name());
        valueObjects.add(object);
    }
    return valueObjects;
}


@Override
public List<List<Map<Object,String>>> fetchColChartData(Integer iusNid,Integer areaNid){
    List<List<Map<Object, String>>> ColCharts = new ArrayList<>();
    System.out.println("chart data controller called");
    List<Map<Object, String>> columnSeries = new ArrayList<>();
    List<Object[]> listData = dataRepository.findData(iusNid, areaNid);
    if (listData != null && !listData.isEmpty()) {
        for (int i = 0; i < listData.size(); i++) {
            Object[] dataObjects = listData.get(i);
            Map<Object, String> map = new HashMap<>();
            Double dataw = null;
            String source = null;
            String tm = null;
            for (Object dataObject : dataObjects) {
                if (dataObject instanceof UtIndicatorClassificationsEn) {
                    UtIndicatorClassificationsEn classificationsEn = (UtIndicatorClassificationsEn) dataObject;
                    source = classificationsEn.getIC_Short_Name();
                } else if (dataObject instanceof UtData) {
                    UtData data = (UtData) dataObject;
                    dataw = data.getData_Value();
                } else if (dataObject instanceof UtTimeperiod) {
                    UtTimeperiod timeperiod = (UtTimeperiod) dataObject;
                    tm = timeperiod.getTimePeriod();
                }
            }
            map.put(source, dataw.toString());
            map.put("timePeriod", tm);
            columnSeries.add(map);
        }
    }
    ColCharts.add(columnSeries);
    return ColCharts;
}


public UtDataCollection getUtdataCollection(String indicatorId,String timePeriodNid,String sourceId,Integer[] areaNid){
    UtDataCollection collection = new UtDataCollection();
    // this will fetch the data for the selected time-period
    // fetch the data for the selected time-period
    List<Object[]> data = dataRepository.findDataByTimePeriod(Integer.parseInt(indicatorId), Integer.parseInt(timePeriodNid), Integer.parseInt(sourceId), areaNid);
    data.sort(new DataComparator());
    if (data != null && !data.isEmpty()) {
        List<ValueObject> list = new ArrayList<ValueObject>();
        // this will fetch the data for the selected time-period and
        // populate the legend
        list = populateLegends(data, indicatorId);
        collection.setLegends(list);
        collection.setTopPerformers(topPerformers);
        collection.setBottomPerformers(bottomPerformers);
        UtData utData = null;
        UtAreaEn utAreaEn = null;
        Double value = null;
        for (Object[] dataObject : data) {
            UtDataModel utDataModel = new UtDataModel();
            utData = (UtData) dataObject[0];
            utAreaEn = (UtAreaEn) dataObject[1];
            // value = getFormattedDouble(utData != null && utData.getUnit_NId() == 2 ? utData.getData_Value() : new Double(Math.round(utData.getData_Value())));
            value = utData.getData_Value();
            utDataModel.setValue(utData.getData_Value().toString());
            // System.out.println(utData.getData_Value().toString()+"first");
            // System.out.println(utDataModel.getValue()+"second");
            utDataModel.setAreaCode(utAreaEn.getArea_ID());
            utDataModel.setAreaName(utAreaEn.getArea_Name());
            utDataModel.setAreaNid(utAreaEn.getArea_NId());
            utDataModel.setRank(ranks != null && ranks.get(utAreaEn.getArea_ID()) != null ? Integer.toString(ranks.get(utAreaEn.getArea_ID())) : null);
            // if(list != null){
            // setCssForDataModel(list, utDataModel, value , indicatorId);
            // }
            utDataModel.setUnit("percent");
            collection.dataCollection.add(utDataModel);
        }
    }
    return collection;
}


public void populateRank(List<Object[]> data,String indicatorId){
    ranks = new HashMap<String, Integer>();
    topPerformers = new ArrayList<String>();
    bottomPerformers = new ArrayList<String>();
    if (data != null && !data.isEmpty()) {
        int rank = 0;
        double dataValue = 0.0;
        UtAreaEn utArea = null;
        UtData utData = null;
        if (isPositveIndicator(indicatorId) == 1) {
            for (int index = data.size() - 1; index >= 0; index--) {
                utData = (UtData) data.get(index)[0];
                utArea = (UtAreaEn) data.get(index)[1];
                // populate the performance by area list
                if (data.size() >= 6) {
                    if (index >= data.size() - 3) {
                        topPerformers.add(utArea.getArea_Name() + " - " + utData.getData_Value());
                    }
                    if (index < 3) {
                        bottomPerformers.add(utArea.getArea_Name() + " - " + utData.getData_Value());
                    }
                } else if (index <= 2) {
                    topPerformers.add(utArea.getArea_Name() + " - " + utData.getData_Value());
                } else {
                    bottomPerformers.add(utArea.getArea_Name() + " - " + utData.getData_Value());
                }
                if (dataValue == utData.getData_Value() && index != data.size() - 1) {
                    ranks.put(utArea.getArea_ID(), rank);
                    continue;
                }
                rank = data.size() - index;
                dataValue = utData.getData_Value();
                ranks.put(utArea.getArea_ID(), rank);
            }
        } else if (isPositveIndicator(indicatorId) == 2) {
            for (int index = 0; index < data.size(); index++) {
                utData = (UtData) data.get(index)[0];
                utArea = (UtAreaEn) data.get(index)[1];
                // populate the performance by area list
                if (data.size() >= 6) {
                    if (index < 3) {
                        topPerformers.add(utArea.getArea_Name() + " - " + utData.getData_Value());
                    }
                    if (index >= data.size() - 3) {
                        bottomPerformers.add(utArea.getArea_Name() + " - " + utData.getData_Value());
                    }
                } else if (index <= 2) {
                    topPerformers.add(utArea.getArea_Name() + " - " + utData.getData_Value());
                } else {
                    bottomPerformers.add(utArea.getArea_Name() + " - " + utData.getData_Value());
                }
                if (dataValue == utData.getData_Value() && index != 0) {
                    ranks.put(utArea.getArea_ID(), rank);
                    continue;
                }
                rank++;
                dataValue = utData.getData_Value();
                ranks.put(utArea.getArea_ID(), rank);
            }
        } else {
            for (int index = 0; index < data.size(); index++) {
                utData = (UtData) data.get(index)[0];
                utArea = (UtAreaEn) data.get(index)[1];
                // populate the performance by area list
                if (data.size() >= 6) {
                    if (index < 3) {
                        topPerformers.add(utArea.getArea_Name() + " - " + utData.getData_Value());
                    }
                    if (index >= data.size() - 3) {
                        bottomPerformers.add(utArea.getArea_Name() + " - " + utData.getData_Value());
                    }
                } else if (index <= 2) {
                    topPerformers.add(utArea.getArea_Name() + " - " + utData.getData_Value());
                } else {
                    bottomPerformers.add(utArea.getArea_Name() + " - " + utData.getData_Value());
                }
                if (dataValue == utData.getData_Value() && index != 0) {
                    ranks.put(utArea.getArea_ID(), rank);
                    continue;
                }
                rank++;
                dataValue = utData.getData_Value();
                ranks.put(utArea.getArea_ID(), rank);
            }
        }
    }
// TODO Auto-generated method stub
}


@Override
public UtDataCollection fetchComputeData(String indicatorId,String parentAreaCode,String timeperiodId,Integer childLevel,Integer sourceNId){
    System.out.println("Fetch Data of DashBoard Controller is called");
    if (parentAreaCode != null) {
        System.out.println(" === Area Code === " + parentAreaCode);
    }
    dataByArea = null;
    ranks = null;
    dataByTPBYSource = null;
    // get all areas less than or equal to the selected level
    UtAreaEn[] utAreas = dataRepository.getAreaNid(parentAreaCode, childLevel);
    // get parentArea from the area list
    UtAreaEn area = getParentUtArea(utAreas, parentAreaCode);
    ArrayList<UtAreaEn> list = new ArrayList<UtAreaEn>();
    // get children of the select area.
    getChildren(utAreas, childLevel, area.getArea_NId(), list);
    Integer[] areaNids = new Integer[list.size()];
    int i = 0;
    for (UtAreaEn utAreaEn : list) {
        areaNids[i] = utAreaEn.getArea_NId();
        i++;
    }
    UtDataCollection utDataCollection = getComputeUtdataCollection(indicatorId, areaNids, timeperiodId, sourceNId);
    return utDataCollection;
}


public List<ValueObject> populateLegends(List<Object[]> data,String indicatorId){
    // TO DO: make this configuration based.
    int range = 4;
    Double minDataValue = null;
    Double maxDataValue = null;
    String firstslices = Constants.Slices.FIRST_SLICE;
    String secondslices = Constants.Slices.SECOND_SLICE;
    String thirdslices = Constants.Slices.THIRD_SLICE;
    String fourthslices = Constants.Slices.FOUTRH_SLICE;
    String fifthslices = Constants.Slices.FIFTHSLICES;
    String firstslicesNeutral = Constants.SlicesNeutral.FIRST_SLICE_NEUTRAL;
    String secondslicesNeutral = Constants.SlicesNeutral.SECOND_SLICE_NEUTRAL;
    String thirdslicesNeutral = Constants.SlicesNeutral.THIRD_SLICE_NEUTRAL;
    String fourthslicesNeutral = Constants.SlicesNeutral.FOUTRH_SLICE_NEUTRAL;
    String fifthslicesNeutral = Constants.SlicesNeutral.FIFTHSLICES_NEUTRAL;
    List<ValueObject> list = new ArrayList<ValueObject>();
    if (data != null && !data.isEmpty()) {
        minDataValue = getFormattedDouble(((UtData) data.get(0)[0]).getData_Value());
        maxDataValue = getFormattedDouble(((UtData) data.get(data.size() - 1)[0]).getData_Value());
        Double difference = (maxDataValue - minDataValue) / range;
        if (difference == 0) {
            String firstSliceValue = minDataValue + " - " + getFormattedDouble(minDataValue + difference);
            list.add(isPositveIndicator(indicatorId) == 1 ? new ValueObject(firstSliceValue, firstslices) : new ValueObject(firstSliceValue, fourthslices));
        } else {
            String firstSliceValue, secondSliceValue, thirdSliceValue, fourthSliceValue;
            if (((UtData) data.get(0)[0]).getUnit_NId() == 3 || ((UtData) data.get(0)[0]).getUnit_NId() == 1) {
                firstSliceValue = Math.round(minDataValue.intValue()) + " - " + Math.round(minDataValue + difference);
                secondSliceValue = Math.round((minDataValue + difference + 1)) + " - " + Math.round((minDataValue + 2 * difference));
                thirdSliceValue = Math.round((minDataValue + 2 * difference + 1)) + " - " + Math.round((minDataValue + 3 * difference));
                fourthSliceValue = Math.round((minDataValue + 3 * difference + 1)) + " - " + Math.round(maxDataValue.intValue());
            } else {
                firstSliceValue = getFormattedDouble(minDataValue) + " - " + getFormattedDouble(minDataValue + difference);
                secondSliceValue = getFormattedDouble(minDataValue + difference + 0.1) + " - " + getFormattedDouble(minDataValue + 2 * difference);
                thirdSliceValue = getFormattedDouble(minDataValue + 2 * difference + 0.1) + " - " + getFormattedDouble(minDataValue + 3 * difference);
                fourthSliceValue = getFormattedDouble(minDataValue + 3 * difference + 0.1) + " - " + getFormattedDouble(maxDataValue);
            }
            if (isPositveIndicator(indicatorId) == 1) {
                list.add(new ValueObject(firstSliceValue, firstslices));
                list.add(new ValueObject(secondSliceValue, secondslices));
                list.add(new ValueObject(thirdSliceValue, thirdslices));
                list.add(new ValueObject(fourthSliceValue, fourthslices));
                list.add(new ValueObject("Not Available", fifthslices));
            } else if (isPositveIndicator(indicatorId) == 2) {
                list.add(new ValueObject(firstSliceValue, firstslicesNeutral));
                list.add(new ValueObject(secondSliceValue, secondslicesNeutral));
                list.add(new ValueObject(thirdSliceValue, thirdslicesNeutral));
                list.add(new ValueObject(fourthSliceValue, fourthslicesNeutral));
                list.add(new ValueObject("Not Available", fifthslicesNeutral));
            } else {
                list.add(new ValueObject(firstSliceValue, fourthslices));
                list.add(new ValueObject(secondSliceValue, thirdslices));
                list.add(new ValueObject(thirdSliceValue, secondslices));
                list.add(new ValueObject(fourthSliceValue, firstslices));
                list.add(new ValueObject("Not Available", fifthslices));
            }
        }
    }
    // calculates the rank for the area codes for the selected time-period
    populateRank(data, indicatorId);
    return list != null && !list.isEmpty() ? list : null;
}


@Override
public List<ValueObject> fetchUtTimeperiod(Integer iusNid,Integer SourceNid){
    List<UtTimeperiod> utTimeperiods = utTimeperiodRepository.findBySource_Nid(iusNid, SourceNid);
    List<ValueObject> valueObjects = new ArrayList<>();
    for (UtTimeperiod utTimeperiod : utTimeperiods) {
        ValueObject object = new ValueObject();
        object.setKey(new Integer(utTimeperiod.getTimePeriod_NId()).toString());
        object.setValue(utTimeperiod.getTimePeriod());
        valueObjects.add(object);
    }
    return valueObjects;
}


@Override
public CounterCount getCounter(){
    CounterCount counterCount = counterCountRepository.findTotalCount();
    if (counterCount == null) {
        CounterCount counterCountAdd = new CounterCount();
        counterCountAdd.setNoOfCounter(1);
        counterCountRepository.save(counterCountAdd);
    } else {
        counterCount.setNoOfCounter(counterCount.getNoOfCounter() + 1);
        counterCountRepository.save(counterCount);
    }
    return counterCount;
}


public UtAreaEn getParentUtArea(UtAreaEn[] utAreas,String areaId){
    UtAreaEn utAreaen = null;
    for (UtAreaEn utAreaEn : utAreas) {
        if (utAreaEn.getArea_ID().equalsIgnoreCase(areaId)) {
            utAreaen = utAreaEn;
            break;
        }
    }
    return utAreaen;
}


public String getFormattedTP(String timePeriod){
    // Date date = null;
    // try {
    // date = timePeriod != null ? new SimpleDateFormat("yyyy.MM")
    // .parse(timePeriod) : null;
    // } catch (ParseException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // date = timePeriod != null ? new SimpleDateFormat("yyyy.MMM")
    // .parse(timePeriod) : null;
    // }
    // String formattedTP = date != null ? new SimpleDateFormat("MMM yyyy")
    // .format(date) : null;
    return timePeriod;
}


public void populateDataByTimePeroid(List<Object[]> listData){
    // TODO Auto-generated method stub
    dataByArea = new HashMap<String, List<LineSeries>>();
    dataByTPBYSource = new HashMap<>();
    if (listData != null && !listData.isEmpty()) {
        UtData utData = null;
        UtAreaEn utAreaEn = null;
        UtTimeperiod utTimeperiod = null;
        UtIndicatorClassificationsEn classificationsEn = null;
        for (Object[] dataObject : listData) {
            utData = (UtData) dataObject[0];
            utAreaEn = (UtAreaEn) dataObject[1];
            utTimeperiod = (UtTimeperiod) dataObject[2];
            classificationsEn = (UtIndicatorClassificationsEn) dataObject[3];
            if (dataByTPBYSource.containsKey(utAreaEn.getArea_ID())) {
                Map<String, List<ValueObject>> dataByTPMap = dataByTPBYSource.get(utAreaEn.getArea_ID());
                if (dataByTPMap.containsKey(classificationsEn.getIC_Short_Name())) {
                    List<ValueObject> objects = dataByTPMap.get(classificationsEn.getIC_Short_Name());
                    objects.add(new ValueObject(getFormattedTP(utTimeperiod.getTimePeriod()), utData.getData_Value()));
                } else {
                    List<ValueObject> objects = new ArrayList<>();
                    objects.add(new ValueObject(getFormattedTP(utTimeperiod.getTimePeriod()), utData.getData_Value()));
                    dataByTPMap.put(classificationsEn.getIC_Short_Name(), objects);
                }
            } else {
                Map<String, List<ValueObject>> dataByTPMap = new HashMap<>();
                List<ValueObject> objects = new ArrayList<>();
                objects.add(new ValueObject(getFormattedTP(utTimeperiod.getTimePeriod()), utData.getData_Value()));
                dataByTPMap.put(classificationsEn.getIC_Short_Name(), objects);
                dataByTPBYSource.put(utAreaEn.getArea_ID(), dataByTPMap);
            }
            if (dataByArea.containsKey(utAreaEn.getArea_ID())) {
                List<LineSeries> lineSeries = dataByArea.get(utAreaEn.getArea_ID());
                lineSeries.add(new LineSeries(classificationsEn.getIC_Short_Name(), getFormattedTP(utTimeperiod.getTimePeriod()), utData.getData_Value()));
            } else {
                List<LineSeries> lineSeries = new ArrayList<>();
                lineSeries.add(new LineSeries(classificationsEn.getIC_Short_Name(), getFormattedTP(utTimeperiod.getTimePeriod()), utData.getData_Value()));
                dataByArea.put(utAreaEn.getArea_ID(), lineSeries);
            }
        }
    // end of for
    }
// end of if
}


@Override
public CounterCount fetchCounter(){
    return counterCountRepository.findTotalCount();
}


public int isPositveIndicator(String indicatorId){
    UtIndicatorEn indicatorEn = indicatorRepository.findByIndicator_NId(Integer.parseInt(indicatorId));
    String keywordStr = indicatorEn.getKeywords();
    if (keywordStr == null) {
        keywordStr = "";
    }
    int highisgoodStr = indicatorEn.getHighIsGood();
    if (keywordStr != null && keywordStr.equals("Neutral")) {
        return 2;
    } else if (highisgoodStr == 1 && !keywordStr.equals("Neutral")) {
        return 1;
    } else {
        return 0;
    }
}


@Override
public List<UtDataModel> fetchPdfData(String indicatorId,String sourceId,String areaId,String timePeriodId,Integer childLevel){
    List<UtDataModel> dataModels = new ArrayList<>();
    UtAreaEn[] utAreas = dataRepository.getAreaNid(areaId, childLevel);
    // get parentArea from the area list
    UtAreaEn area = getParentUtArea(utAreas, areaId);
    ArrayList<UtAreaEn> listArea = new ArrayList<UtAreaEn>();
    // get children of the select area.
    getChildren(utAreas, childLevel, area.getArea_NId(), listArea);
    Integer[] areaNids = new Integer[listArea.size()];
    int i = 0;
    for (UtAreaEn utAreaEn : listArea) {
        areaNids[i] = utAreaEn.getArea_NId();
        i++;
    }
    // this will fetch the data for the selected time-period
    // fetch the data for the selected time-period
    List<Object[]> data = dataRepository.findDataByTimePeriod(Integer.parseInt(indicatorId), Integer.parseInt(timePeriodId), Integer.parseInt(sourceId), areaNids);
    if (data != null && !data.isEmpty()) {
        UtData utData = null;
        UtAreaEn utAreaEn = null;
        Double value = null;
        for (Object[] dataObject : data) {
            UtDataModel utDataModel = new UtDataModel();
            utData = (UtData) dataObject[0];
            utAreaEn = (UtAreaEn) dataObject[1];
            value = getFormattedDouble(utData != null && utData.getUnit_NId() == 1 ? utData.getData_Value() : new Double(Math.round(utData.getData_Value())));
            utDataModel.setValue(value.toString());
            utDataModel.setAreaCode(utAreaEn.getArea_ID());
            utDataModel.setAreaName(utAreaEn.getArea_Name());
            utDataModel.setAreaNid(utAreaEn.getArea_NId());
            utDataModel.setRank(ranks != null && ranks.get(utAreaEn.getArea_ID()) != null ? Integer.toString(ranks.get(utAreaEn.getArea_ID())) : null);
            dataModels.add(utDataModel);
        }
    }
    return dataModels;
}


@Override
public List<ValueObject> fetchSources(String param){
    // TODO Auto-generated method stub
    System.out.println("IusNid==>" + Integer.parseInt(param));
    List<UtIndicatorClassificationsEn> classificationsEns = sourceRepository.findByIUS_Nid(Integer.parseInt(param));
    List<ValueObject> valueObjects = new ArrayList<>();
    for (UtIndicatorClassificationsEn classificationsEn : classificationsEns) {
        ValueObject object = new ValueObject();
        object.setKey(new Integer(classificationsEn.getIC_NId()).toString());
        object.setValue(classificationsEn.getIC_Name());
        valueObjects.add(object);
    }
    return valueObjects;
}


public String getDecadesvalue(double x){
    String valueofY = String.valueOf(x / 10).substring(String.valueOf(x / 10).length() - 1);
    // Integer.parseInt(valueofY)-1;
    Integer t2 = ((int) x - Integer.parseInt(valueofY.equals("0") ? valueofY = "10" : valueofY)) + 1;
    Integer t1 = t2 - 10;
    if (valueofY.equals("1")) {
        t1 = 0;
    }
    return t1 + "," + t2 + "," + (Integer.parseInt(valueofY) - 1);
}


public UtDataCollection getBurdenUtdataCollection(Integer iusNIdForProjection,Integer iusNId,Integer iusNIdForIMRorU5MR,Integer[] areaNid,String timePeriodNid,Integer sourceNIdForProjection,Integer sourceNId,Integer sourceNIdForIMRorU5MR){
    UtDataCollection collection = new UtDataCollection();
    // this will fetch the data for the selected time-period
    // fetch the data for the selected time-period
    String timePeriodStr = getDecadesvalue(Double.parseDouble(timePeriodNid.split("-")[0]));
    List<String> decadeTimePeriodList = new ArrayList<String>();
    decadeTimePeriodList.add(timePeriodStr.split(",")[0]);
    decadeTimePeriodList.add(timePeriodStr.split(",")[1]);
    List<UtTimeperiod> timeperioddata = dataRepository.findTimePeriodNIdByDecadeTimePeriod(decadeTimePeriodList);
    List<Integer> decadeTimePeriodNId = new ArrayList<Integer>();
    List<Object[]> fetchdata = new ArrayList<Object[]>();
    List<List<Object[]>> datalist = new ArrayList<List<Object[]>>();
    for (UtTimeperiod timeperioddataObject : timeperioddata) {
        decadeTimePeriodNId.add(timeperioddataObject.getTimePeriod_NId());
        fetchdata = dataRepository.findDataByTimePeriod(iusNIdForProjection, timeperioddataObject.getTimePeriod_NId(), sourceNIdForProjection, areaNid);
        datalist.add(fetchdata);
    }
    decadeTimePeriodList.clear();
    decadeTimePeriodList.add(timePeriodNid);
    List<UtTimeperiod> timeperioddata_cbr = dataRepository.findTimePeriodNIdByDecadeTimePeriod(decadeTimePeriodList);
    // fetch cbr data
    List<Object[]> cbrfetchdata = dataRepository.findDataByTimePeriod(iusNId, timeperioddata_cbr.get(0).getTimePeriod_NId(), sourceNId, areaNid);
    List<Object[]> projectedData = new ArrayList<Object[]>();
    List<Object[]> dataObjectt1 = datalist.get(0) != null ? datalist.get(0) : null;
    List<Object[]> dataObjectt2 = datalist.size() > 1 ? datalist.get(1) : null;
    List<Object[]> mindataObject = null, maxdataObject = null;
    if (dataObjectt1 != null && dataObjectt2 != null) {
        mindataObject = dataObjectt2.size() >= dataObjectt1.size() ? dataObjectt1 : dataObjectt2;
        maxdataObject = dataObjectt2.size() >= dataObjectt1.size() ? dataObjectt2 : dataObjectt1;
    } else if (dataObjectt1 != null && dataObjectt2 == null) {
        mindataObject = dataObjectt1;
    } else {
        mindataObject = dataObjectt2;
    }
    if (mindataObject != null && !mindataObject.isEmpty()) {
        List<ValueObject> list = new ArrayList<ValueObject>();
        UtData utData = null;
        UtAreaEn utAreaEn = null;
        Double value = null;
        List<UtData> updatedDataSet = maxdataObject != null ? new ArrayList<UtData>(maxdataObject.size()) : null;
        for (Object[] dataObject : mindataObject) {
            UtDataModel utDataModel = new UtDataModel();
            utData = (UtData) dataObject[0];
            utAreaEn = (UtAreaEn) dataObject[1];
            if (utData.getData_Value() != 0) {
                if (maxdataObject != null) {
                    double projectedValue = getProjectionDataCalculation(maxdataObject, utAreaEn.getArea_NId(), utData.getData_Value(), Integer.parseInt(timePeriodStr.split(",")[2]));
                    Integer indexOfDatavalue1 = mindataObject.indexOf(dataObject);
                    System.out.println(utData.getData_Value() + "-----" + utAreaEn.getArea_Name() + "-----projected------" + projectedValue);
                    UtData data = ((UtData) dataObject[0]);
                    ((UtData) dataObject[0]).setData_Value(projectedValue);
                    mindataObject.get(indexOfDatavalue1)[0] = data;
                }
            }
        }
    }
    mindataObject.sort(new DataComparator());
    if (cbrfetchdata != null && !cbrfetchdata.isEmpty() && mindataObject != null && !mindataObject.isEmpty()) {
        List<ValueObject> list = new ArrayList<ValueObject>();
        UtData utData = null;
        UtAreaEn utAreaEn = null;
        Double value = null;
        for (Object[] dataObject : cbrfetchdata) {
            UtDataModel utDataModel = new UtDataModel();
            utData = (UtData) dataObject[0];
            utAreaEn = (UtAreaEn) dataObject[1];
            if (utData.getData_Value() != 0) {
                double burdenEstimatedValue = getBurdenDataCalculation(mindataObject, utAreaEn.getArea_NId(), utData.getData_Value());
                Integer indexOfDatavalue1 = cbrfetchdata.indexOf(dataObject);
                System.out.println(utData.getData_Value() + "-----" + utAreaEn.getArea_Name() + "-----burden------" + burdenEstimatedValue);
                UtData data = ((UtData) dataObject[0]);
                ((UtData) dataObject[0]).setData_Value(burdenEstimatedValue);
                cbrfetchdata.get(indexOfDatavalue1)[0] = data;
            }
        }
        if (iusNIdForIMRorU5MR > 0) {
            // fetch cbr data
            List<Object[]> fetchdataForIMRorU5MR = dataRepository.findDataByTimePeriod(iusNIdForIMRorU5MR, timeperioddata_cbr.get(0).getTimePeriod_NId(), sourceNIdForIMRorU5MR, areaNid);
            for (Object[] dataObject : cbrfetchdata) {
                UtDataModel utDataModel = new UtDataModel();
                utData = (UtData) dataObject[0];
                utAreaEn = (UtAreaEn) dataObject[1];
                if (utData.getData_Value() != 0) {
                    double burdenEstimatedValue = getBurdenDataCalculationForIMRandU5MR(fetchdataForIMRorU5MR, utAreaEn.getArea_NId(), utData.getData_Value());
                    Integer indexOfDatavalue1 = cbrfetchdata.indexOf(dataObject);
                    System.out.println(utData.getData_Value() + "-----" + utAreaEn.getArea_Name() + "-----IMRandU5MR------" + burdenEstimatedValue);
                    UtData data = ((UtData) dataObject[0]);
                    ((UtData) dataObject[0]).setData_Value(burdenEstimatedValue);
                    cbrfetchdata.get(indexOfDatavalue1)[0] = data;
                }
            }
        }
    }
    if (cbrfetchdata != null && !cbrfetchdata.isEmpty() && mindataObject != null && !mindataObject.isEmpty()) {
        cbrfetchdata.sort(new DataComparator());
        List<ValueObject> list = new ArrayList<ValueObject>();
        try {
            list = populateLegends(cbrfetchdata, iusNId.toString());
        } catch (Exception e) {
        }
        UtData utData1 = null;
        UtAreaEn utAreaEn1 = null;
        collection.setLegends(list);
        collection.setTopPerformers(topPerformers);
        collection.setBottomPerformers(bottomPerformers);
        for (Object[] dataObject1 : cbrfetchdata) {
            UtDataModel utDataModel = new UtDataModel();
            utData1 = (UtData) dataObject1[0];
            utAreaEn1 = (UtAreaEn) dataObject1[1];
            if (utData1.getData_Value() != 0) {
                utDataModel.setValue(String.valueOf(utData1.getData_Value()));
                utDataModel.setAreaCode(utAreaEn1.getArea_ID());
                utDataModel.setAreaName(utAreaEn1.getArea_Name());
                utDataModel.setAreaNid(utAreaEn1.getArea_NId());
                utDataModel.setRank(ranks != null && ranks.get(utAreaEn1.getArea_ID()) != null ? Integer.toString(ranks.get(utAreaEn1.getArea_ID())) : null);
                if (list != null) {
                    setCssForDataModel(list, utDataModel, utData1.getData_Value(), iusNId.toString());
                }
                utDataModel.setUnit("percent");
                collection.dataCollection.add(utDataModel);
            }
        }
    }
    return collection;
}


public void getChildren(UtAreaEn[] utAreas,int i,int parentNid,ArrayList<UtAreaEn> list){
    for (UtAreaEn utAreaEn : utAreas) {
        if (utAreaEn.getArea_Parent_NId() == parentNid) {
            if (utAreaEn.getArea_Level() == i)
                list.add(utAreaEn);
            else
                getChildren(utAreas, i, utAreaEn.getArea_NId(), list);
        }
    }
}


public Double getBurdenDataCalculationForIMRandU5MR(List<Object[]> data,Integer areaNId,double liveBirthValue){
    double value = 0.00;
    if (data != null && !data.isEmpty()) {
        List<ValueObject> list = new ArrayList<ValueObject>();
        UtData utData = null;
        UtAreaEn utAreaEn = null;
        for (Object[] dataObject : data) {
            UtDataModel utDataModel = new UtDataModel();
            utData = (UtData) dataObject[0];
            utAreaEn = (UtAreaEn) dataObject[1];
            double valueOfIMRorU5MR = utData.getData_Value();
            // System.out.println(pt1+"p1-------------p2"+pt2);
            if (areaNId == utAreaEn.getArea_NId()) {
                value = Math.round((valueOfIMRorU5MR * liveBirthValue) / 1000);
            }
        }
    }
    return value != 0.0 ? value : 0;
}


@Override
public UtDataCollection fetchData(String indicatorId,String sourceId,String parentAreaCode,String timeperiodId,Integer childLevel){
    System.out.println("Fetch Data of DashBoard Controller is called");
    if (parentAreaCode != null) {
        System.out.println(" === Area Code === " + parentAreaCode);
    }
    dataByArea = null;
    ranks = null;
    dataByTPBYSource = null;
    // get all areas less than or equal to the selected level
    UtAreaEn[] utAreas = dataRepository.getAreaNid(parentAreaCode, childLevel);
    // get parentArea from the area list
    UtAreaEn area = getParentUtArea(utAreas, parentAreaCode);
    ArrayList<UtAreaEn> list = new ArrayList<UtAreaEn>();
    // get children of the select area.
    getChildren(utAreas, childLevel, area.getArea_NId(), list);
    Integer[] areaNids = new Integer[list.size()];
    int i = 0;
    for (UtAreaEn utAreaEn : list) {
        areaNids[i] = utAreaEn.getArea_NId();
        i++;
    }
    UtDataCollection utDataCollection = getUtdataCollection(indicatorId, timeperiodId, sourceId, areaNids);
    return utDataCollection;
}


public void setCssForDataModel(List<ValueObject> list,UtDataModel data,Double value,String indicatorId){
    for (int index = 0; index < list.size(); index++) {
        ValueObject vObject = list.get(index);
        String[] vArray = vObject != null ? vObject.getKey().split(" - ") : null;
        if (list.size() == 1) {
            if (isPositveIndicator(indicatorId) == 1) {
                data.setCssClass(Constants.Slices.FIRST_SLICE);
            } else if (isPositveIndicator(indicatorId) == 2) {
                data.setCssClass(Constants.SlicesNeutral.FIRST_SLICE_NEUTRAL);
            } else {
                data.setCssClass(Constants.Slices.FOUTRH_SLICE);
            }
        } else if (index == 4 || (vArray != null && new Double(vArray[0]).doubleValue() <= value && value <= new Double(vArray[1]).doubleValue())) {
            if (isPositveIndicator(indicatorId) == 1) {
                switch(index) {
                    case 0:
                        data.setCssClass(Constants.Slices.FIRST_SLICE);
                        break;
                    case 1:
                        data.setCssClass(Constants.Slices.SECOND_SLICE);
                        break;
                    case 2:
                        data.setCssClass(Constants.Slices.THIRD_SLICE);
                        break;
                    case 3:
                        data.setCssClass(Constants.Slices.FOUTRH_SLICE);
                        break;
                }
            }
            if (isPositveIndicator(indicatorId) == 2) {
                switch(index) {
                    case 0:
                        data.setCssClass(Constants.SlicesNeutral.FIRST_SLICE_NEUTRAL);
                        break;
                    case 1:
                        data.setCssClass(Constants.SlicesNeutral.SECOND_SLICE_NEUTRAL);
                        break;
                    case 2:
                        data.setCssClass(Constants.SlicesNeutral.THIRD_SLICE_NEUTRAL);
                        break;
                    case 3:
                        data.setCssClass(Constants.SlicesNeutral.FOUTRH_SLICE_NEUTRAL);
                        break;
                }
            }
            if (isPositveIndicator(indicatorId) == 0) {
                switch(index) {
                    case 0:
                        data.setCssClass(Constants.Slices.FOUTRH_SLICE);
                        break;
                    case 1:
                        data.setCssClass(Constants.Slices.THIRD_SLICE);
                        break;
                    case 2:
                        data.setCssClass(Constants.Slices.SECOND_SLICE);
                        break;
                    case 3:
                        data.setCssClass(Constants.Slices.FIRST_SLICE);
                        break;
                }
            }
        // System.out.println(data);
        }
    }
}


public UtDataCollection getProjectionData(List<List<Object[]>> datalist,Integer calculatedYear,String indicatorId){
    UtDataCollection collection = new UtDataCollection();
    List<Object[]> projectedData = new ArrayList<Object[]>();
    List<Object[]> dataObjectt1 = datalist.get(0) != null ? datalist.get(0) : null;
    List<Object[]> dataObjectt2 = datalist.size() > 1 ? datalist.get(1) : null;
    List<Object[]> mindataObject = null, maxdataObject = null;
    if (dataObjectt1 != null && dataObjectt2 != null) {
        mindataObject = dataObjectt2.size() >= dataObjectt1.size() ? dataObjectt1 : dataObjectt2;
        maxdataObject = dataObjectt2.size() >= dataObjectt1.size() ? dataObjectt2 : dataObjectt1;
    } else if (dataObjectt1 != null && dataObjectt2 == null) {
        mindataObject = dataObjectt1;
    } else {
        mindataObject = dataObjectt2;
    }
    if (mindataObject != null && !mindataObject.isEmpty()) {
        List<ValueObject> list = new ArrayList<ValueObject>();
        UtData utData = null;
        UtAreaEn utAreaEn = null;
        Double value = null;
        List<UtData> updatedDataSet = maxdataObject != null ? new ArrayList<UtData>(maxdataObject.size()) : null;
        for (Object[] dataObject : mindataObject) {
            UtDataModel utDataModel = new UtDataModel();
            utData = (UtData) dataObject[0];
            utAreaEn = (UtAreaEn) dataObject[1];
            if (utData.getData_Value() != 0) {
                if (maxdataObject != null) {
                    double projectedvalue = getProjectionDataCalculation(maxdataObject, utAreaEn.getArea_NId(), utData.getData_Value(), calculatedYear);
                    Integer indexOfDatavalue1 = mindataObject.indexOf(dataObject);
                    System.out.println(utData.getData_Value() + "-----------" + projectedvalue);
                    UtData data = ((UtData) dataObject[0]);
                    ((UtData) dataObject[0]).setData_Value(projectedvalue);
                    mindataObject.get(indexOfDatavalue1)[0] = data;
                }
            }
        }
        mindataObject.sort(new DataComparator());
        try {
            list = populateLegends(mindataObject, indicatorId);
        } catch (Exception e) {
        }
        UtData utData1 = null;
        UtAreaEn utAreaEn1 = null;
        collection.setLegends(list);
        collection.setTopPerformers(topPerformers);
        collection.setBottomPerformers(bottomPerformers);
        for (Object[] dataObject1 : mindataObject) {
            UtDataModel utDataModel = new UtDataModel();
            utData1 = (UtData) dataObject1[0];
            utAreaEn1 = (UtAreaEn) dataObject1[1];
            if (utData1.getData_Value() != 0) {
                utDataModel.setValue(String.valueOf(utData1.getData_Value()));
                utDataModel.setAreaCode(utAreaEn1.getArea_ID());
                utDataModel.setAreaName(utAreaEn1.getArea_Name());
                utDataModel.setAreaNid(utAreaEn1.getArea_NId());
                utDataModel.setRank(ranks != null && ranks.get(utAreaEn1.getArea_ID()) != null ? Integer.toString(ranks.get(utAreaEn1.getArea_ID())) : null);
                if (list != null) {
                    setCssForDataModel(list, utDataModel, utData1.getData_Value(), indicatorId);
                }
                utDataModel.setUnit("percent");
                collection.dataCollection.add(utDataModel);
            }
        }
    }
    return collection;
}


@Override
public List<ValueObject> fetchIndicators(String indicatorType){
    System.out.println("The selected sector is == > " + indicatorType);
    List<Object[]> listofIndicators = null;
    List<ValueObject> list = new ArrayList<ValueObject>();
    listofIndicators = indicatorRepository.findByIC_Type(indicatorType);
    for (Object[] objects : listofIndicators) {
        ValueObject vObject = new ValueObject();
        vObject.setKey(objects[0].toString());
        vObject.setMetadata(objects[1].toString());
        vObject.setDescription(objects[3].toString());
        vObject.setNid(Integer.parseInt(objects[2].toString()));
        vObject.setValue(objects[4]);
        list.add(vObject);
    }
    return list;
}


public Double getFormattedDouble(Double value){
    Double formattedValue = value != null ? new BigDecimal(value).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue() : null;
    return formattedValue;
}


}
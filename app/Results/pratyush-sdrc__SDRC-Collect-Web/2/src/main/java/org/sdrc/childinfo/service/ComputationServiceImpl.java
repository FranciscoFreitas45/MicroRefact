package org.sdrc.childinfo.service;
 import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.sdrc.childinfo.model.IUSValueObject;
import org.sdrc.childinfo.model.UtDataCollection;
import org.sdrc.childinfo.model.UtDataModel;
import org.sdrc.childinfo.model.ValueObject;
import org.sdrc.childinfo.util.AreaComparator;
import org.sdrc.childinfo.util.CommonService;
import org.sdrc.childinfo.util.DataComparator;
import org.sdrc.devinfo.domain.UtAreaEn;
import org.sdrc.devinfo.domain.UtData;
import org.sdrc.devinfo.repository.UtDataRepository;
import org.sdrc.devinfo.repository.UtSubgroupValsEnRepository;
import org.sdrc.devinfo.repository.UtTimeperiodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sdrc.Interface.UtSubgroupValsEnRepository;
import org.sdrc.Interface.UtDataRepository;
import org.sdrc.Interface.UtTimeperiodRepository;
@Service
public class ComputationServiceImpl implements ComputationService{

@Autowired
 private  UtSubgroupValsEnRepository subgroupValsEnRepository;

@Autowired
 private  UtDataRepository dataRepository;

@Autowired
 private  CommonService commonService;

@Autowired
 private  UtTimeperiodRepository timeperiodRepository;


public List<Object[]> compositeIndexCalculation(List<List<Object[]>> datalist,List<IUSValueObject> iusDataModelList){
    List<Object[]> data = new ArrayList<Object[]>();
    List<Object[]> dataListPrepare = new ArrayList<Object[]>();
    if (datalist != null && !datalist.isEmpty()) {
        List<ValueObject> list = new ArrayList<ValueObject>();
        UtData utData = null;
        UtAreaEn utAreaEn = null;
        Map<String, Double> dataMap = new HashMap<String, Double>();
        Integer totalWeightValue = 0;
        Integer count = datalist.get(0).size();
        for (int j = 0; j < datalist.size(); j++) {
            if (datalist.get(j).size() >= count) {
                count = datalist.get(j).size();
                dataListPrepare = datalist.get(j);
            }
        }
        for (int i = 0; i < datalist.size(); i++) {
            totalWeightValue = totalWeightValue + Integer.parseInt(iusDataModelList.get(i).getWeight());
            List<Object[]> objectDataList = datalist.get(i);
            for (Object[] dataObject : objectDataList) {
                UtDataModel utDataModel = new UtDataModel();
                utData = (UtData) dataObject[0];
                utAreaEn = (UtAreaEn) dataObject[1];
                System.out.println(utData.getData_Value() + "-----" + utAreaEn.getArea_Name());
                // UtData utdataObject = ((UtData) dataObject[0]);
                // Integer indexOfDatavalue = objectDataList.indexOf(dataObject);
                if (i > 0) {
                    Double exitDataValue = dataMap.get(utAreaEn.getArea_ID());
                    Double totalValue = utData.getData_Value() * Double.parseDouble(iusDataModelList.get(i).getWeight());
                    if (dataMap != null && dataMap.get(utAreaEn.getArea_ID()) != null) {
                        dataMap.put(utAreaEn.getArea_ID(), totalValue + exitDataValue);
                    }
                } else {
                    Double iusPlusWeightValue = utData.getData_Value() * Integer.parseInt(iusDataModelList.get(i).getWeight());
                    dataMap.put(utAreaEn.getArea_ID(), iusPlusWeightValue);
                }
            }
        }
        if (dataListPrepare != null && !dataListPrepare.isEmpty()) {
            for (Object[] dataObject : dataListPrepare) {
                UtDataModel utDataModel = new UtDataModel();
                utData = (UtData) dataObject[0];
                utAreaEn = (UtAreaEn) dataObject[1];
                double val = getValue(dataMap, utAreaEn.getArea_ID());
                Integer indexOfDatavalue = dataListPrepare.indexOf(dataObject);
                // System.out.println(utAreaEn.getArea_Name()+"value------"+val);
                Double iusPlusWeightValue = (double) val / totalWeightValue;
                // System.out.println("iusPlusWeightValue-----"+iusPlusWeightValue);
                Double toBeTruncated = new Double(iusPlusWeightValue);
                Double truncatedDouble = new BigDecimal(toBeTruncated).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
                UtData utdataObject = ((UtData) dataObject[0]);
                ((UtData) dataObject[0]).setData_Value(truncatedDouble);
                dataListPrepare.get(indexOfDatavalue)[0] = utdataObject;
            }
        }
    }
    return dataListPrepare;
}


@Override
public List<Object[]> fetchSubgroupByIndicatorAndUnit(Integer indicatorNId,Integer unitNId){
    // TODO Auto-generated method stub
    List<Object[]> listofSubgroups = subgroupValsEnRepository.fetchSubgroupByIndicatorAndUnit(indicatorNId, unitNId);
    return listofSubgroups;
}


public Double getValue(Map<String,Double> dataMap,String areaId){
    // iterate through the key set and display key and values
    double value = 0;
    for (String key : dataMap.keySet()) {
        if (key.equals(areaId)) {
            value = dataMap.get(key).intValue();
        }
    }
    return value;
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


public UtDataCollection getCompositedataCollection(List<IUSValueObject> iusDataModelList,Integer[] areaNid){
    UtDataCollection collection = new UtDataCollection();
    List<List<Object[]>> datalist = new ArrayList<List<Object[]>>();
    for (IUSValueObject iusdataObject : iusDataModelList) {
        Integer iusNId_Int = iusdataObject.getIusid();
        Object timePeriodObj = timeperiodRepository.findLatestTimePeriodNId(iusNId_Int, areaNid);
        List<Object[]> data = new ArrayList<Object[]>();
        if (timePeriodObj != null) {
            data = dataRepository.findDataForCompositeIndex(iusNId_Int, Integer.parseInt(timePeriodObj.toString()), areaNid);
            Double minimumValue = commonService.getMinValue(data);
            Double maximumValue = commonService.getMaxValue(data);
            System.out.println(minimumValue + "----" + maximumValue);
            if (data != null && !data.isEmpty()) {
                List<ValueObject> list = new ArrayList<ValueObject>();
                UtData utData = null;
                UtAreaEn utAreaEn = null;
                Double value = null;
                for (Object[] dataObject : data) {
                    UtDataModel utDataModel = new UtDataModel();
                    utData = (UtData) dataObject[0];
                    utAreaEn = (UtAreaEn) dataObject[1];
                    if (utData.getData_Value() != 0) {
                        if (data != null) {
                            Integer index = iusDataModelList.indexOf(iusdataObject);
                            Double highIsGoodValue = highIsGood(iusDataModelList.get(index).isHighisgood(), utData.getData_Value(), minimumValue, maximumValue);
                            Integer indexOfDatavalue = data.indexOf(dataObject);
                            UtData utdataObject = ((UtData) dataObject[0]);
                            ((UtData) dataObject[0]).setData_Value(highIsGoodValue);
                            data.get(indexOfDatavalue)[0] = utdataObject;
                        }
                    }
                }
            }
        }
        System.out.println(data.size());
        if (data.size() > 1) {
            data.sort(new AreaComparator());
            datalist.add(data);
        }
    }
    List<Object[]> data = new ArrayList<Object[]>();
    if (datalist.size() > 1) {
        data = compositeIndexCalculation(datalist, iusDataModelList);
    }
    data.sort(new DataComparator());
    if (data != null && !data.isEmpty()) {
        List<ValueObject> list = new ArrayList<ValueObject>();
        // this will fetch the data for the selected time-period and
        // populate the legend
        list = commonService.populateLegends(data, iusDataModelList.get(0).getIusid().toString());
        collection.setLegends(list);
        collection.setTopPerformers(commonService.topPerformers);
        collection.setBottomPerformers(commonService.bottomPerformers);
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
            utDataModel.setRank(commonService.ranks != null && commonService.ranks.get(utAreaEn.getArea_ID()) != null ? Integer.toString(commonService.ranks.get(utAreaEn.getArea_ID())) : null);
            if (list != null) {
                commonService.setCssForDataModel(list, utDataModel, value, iusDataModelList.get(0).getIusid().toString());
            }
            utDataModel.setUnit("percent");
            collection.dataCollection.add(utDataModel);
        }
    }
    return collection;
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


@Override
public UtDataCollection fetchCompositeIndexData(List<IUSValueObject> iusDataModelList,String parentAreaCode,Integer childLevel){
    System.out.println("Fetch Data of DashBoard Controller is called");
    if (parentAreaCode != null) {
        System.out.println(" === Area Code === " + parentAreaCode);
    }
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
    UtDataCollection utDataCollection = getCompositedataCollection(iusDataModelList, areaNids);
    return utDataCollection;
}


public Double highIsGood(Boolean highIsGood,Double dataValue,Double minimumValue,Double maximumValue){
    Double indexOfIus = 0.00;
    if (highIsGood == true) {
        indexOfIus = (dataValue - minimumValue) * 100 / (maximumValue - minimumValue);
    } else {
        indexOfIus = (maximumValue - dataValue) * 100 / (maximumValue - minimumValue);
    }
    return indexOfIus;
}


@Override
public List<Object[]> fetchIndicatorAndUnitBySectorNId(String sectorNId){
    // TODO Auto-generated method stub
    List<Object[]> listofSubgroups = subgroupValsEnRepository.fetchIndicatorAndUnitBySectorNId(Integer.parseInt(sectorNId));
    return listofSubgroups;
}


}
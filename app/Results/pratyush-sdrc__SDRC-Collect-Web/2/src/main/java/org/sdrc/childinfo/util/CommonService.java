package org.sdrc.childinfo.util;
 import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.sdrc.childinfo.model.LineSeries;
import org.sdrc.childinfo.model.UtDataModel;
import org.sdrc.childinfo.model.ValueObject;
import org.sdrc.devinfo.domain.UtAreaEn;
import org.sdrc.devinfo.domain.UtData;
import org.sdrc.devinfo.domain.UtIndicatorEn;
import org.sdrc.devinfo.repository.IndicatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sdrc.Interface.IndicatorRepository;
@Service
public class CommonService {

@Autowired
 private  IndicatorRepository indicatorRepository;

 public  Map<String,Integer> ranks;

 private  Map<String,List<LineSeries>> dataByArea;

 private  Map<String,Map<String,List<ValueObject>>> dataByTPBYSource;

 public  List<String> topPerformers;

 public  List<String> bottomPerformers;


public Double getMinValue(List<Object[]> data){
    double minValue = 0.0;
    if (data != null && !data.isEmpty()) {
        List<ValueObject> list = new ArrayList<ValueObject>();
        UtData utData = null;
        utData = (UtData) data.get(0)[0];
        minValue = utData.getData_Value();
        for (int i = 1; i < data.size(); i++) {
            utData = (UtData) data.get(i)[0];
            if (utData.getData_Value() < minValue) {
                minValue = utData.getData_Value();
            }
        }
    }
    return minValue;
}


public Double getMaxValue(List<Object[]> data){
    double maxValue = 0.0;
    if (data != null && !data.isEmpty()) {
        List<ValueObject> list = new ArrayList<ValueObject>();
        UtData utData = null;
        utData = (UtData) data.get(0)[0];
        maxValue = utData.getData_Value();
        for (int i = 1; i < data.size(); i++) {
            utData = (UtData) data.get(i)[0];
            if (utData.getData_Value() > maxValue) {
                maxValue = utData.getData_Value();
            }
        }
    }
    return maxValue;
}


public int isPositveIndicator(String indicatorId){
    UtIndicatorEn indicatorEn = indicatorRepository.findByIndicator_NId(Integer.parseInt(indicatorId));
    String keywordStr = indicatorEn.getKeywords();
    if (keywordStr == null) {
        keywordStr = "";
    }
    // int highisgoodStr=indicatorEn.getHighIsGood();
    // if(keywordStr != null? keywordStr.equals("Neutral"):false){
    // return 2;
    // }else if( highisgoodStr== 1 && !keywordStr.equals("Neutral")){
    // return 1;
    // }else{
    // return 0;
    // }
    return 1;
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


public void setCssForDataModel(List<ValueObject> list,UtDataModel data,Double value,String indicatorId){
    for (int index = 0; index < list.size(); index++) {
        ValueObject vObject = list.get(index);
        String[] vArray = vObject != null ? ((String) vObject.getKey()).split(" - ") : null;
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
            String firstSliceValue = Double.toString(minDataValue) + " - " + Double.toString(getFormattedDouble(minDataValue + difference));
            list.add(isPositveIndicator(indicatorId) == 1 ? new ValueObject(firstSliceValue, firstslices) : new ValueObject(firstSliceValue, fourthslices));
        } else {
            String firstSliceValue, secondSliceValue, thirdSliceValue, fourthSliceValue;
            firstSliceValue = Double.toString(getFormattedDouble(minDataValue)) + " - " + Double.toString(getFormattedDouble(minDataValue + difference));
            secondSliceValue = Double.toString(getFormattedDouble(minDataValue + difference + 0.1)) + " - " + Double.toString(getFormattedDouble(minDataValue + 2 * difference));
            thirdSliceValue = Double.toString(getFormattedDouble(minDataValue + 2 * difference + 0.1)) + " - " + Double.toString(getFormattedDouble(minDataValue + 3 * difference));
            fourthSliceValue = Double.toString(getFormattedDouble(minDataValue + 3 * difference + 0.1)) + " - " + Double.toString(getFormattedDouble(maxDataValue));
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


public Double getFormattedDouble(Double value){
    Double formattedValue = value != null ? new BigDecimal(value).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue() : null;
    return formattedValue;
}


}
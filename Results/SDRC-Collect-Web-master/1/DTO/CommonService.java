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
public class CommonService {

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


public Double getFormattedDouble(Double value){
    Double formattedValue = value != null ? new BigDecimal(value).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue() : null;
    return formattedValue;
}


}
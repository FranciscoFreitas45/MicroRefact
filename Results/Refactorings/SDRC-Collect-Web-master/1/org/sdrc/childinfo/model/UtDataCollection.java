import java.util.ArrayList;
import java.util.List;
public class UtDataCollection {

 public  List<UtDataModel> dataCollection;

 private  List<ValueObject> legends;

 private  List<String> topPerformers;

 private  List<String> bottomPerformers;


public List<UtDataModel> getDataCollection(){
    return dataCollection;
}


public void setLegends(List<ValueObject> legends){
    this.legends = legends;
}


public List<ValueObject> getLegends(){
    return legends;
}


public List<String> getBottomPerformers(){
    return bottomPerformers;
}


public void setTopPerformers(List<String> topPerformers){
    this.topPerformers = topPerformers;
}


@Override
public String toString(){
    return "UtDataCollection [dataCollection=" + dataCollection + ", legends=" + legends + ", topPerformers=" + topPerformers + ", bottomPerformers=" + bottomPerformers + "]";
}


public void setDataCollection(List<UtDataModel> dataCollection){
    this.dataCollection = dataCollection;
}


public void setBottomPerformers(List<String> bottomPerformers){
    this.bottomPerformers = bottomPerformers;
}


public List<String> getTopPerformers(){
    return topPerformers;
}


}
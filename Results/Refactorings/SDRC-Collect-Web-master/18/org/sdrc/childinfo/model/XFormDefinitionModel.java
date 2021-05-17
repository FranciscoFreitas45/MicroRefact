import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnore;
public class XFormDefinitionModel {

@JsonIgnore
 public  String xpath;

 public  String label;

 public  String value;

 public  String type;

 public  Map<String,String> choices;

 private  String actualValue;


public void setXpath(String xpath){
    this.xpath = xpath;
}


public String getValue(){
    return value;
}


public String getLabel(){
    return label;
}


public String getType(){
    return type;
}


public void setValue(String value){
    this.value = value;
}


public Map<String,String> getChoices(){
    return choices;
}


public void setLabel(String label){
    this.label = label;
}


public String getXpath(){
    return xpath;
}


public void setChoices(Map<String,String> choices){
    this.choices = choices;
}


public void setType(String type){
    this.type = type;
}


public String getActualValue(){
    if (type != null && type.equals("select")) {
        if (choices != null && choices.size() > 0)
            actualValue = choices.get(value);
        else
            actualValue = value;
    } else if (type != null && type.equals("select multiple")) {
        StringBuffer aValue = new StringBuffer();
        String[] arr = value.split(" ");
        if (arr != null)
            for (int len = 0; len < arr.length; len++) {
                if (choices != null && choices.size() > 0)
                    aValue.append(choices.get(arr[len]) + " ");
            }
        actualValue = aValue.toString();
    } else
        actualValue = value;
    return actualValue;
}


}
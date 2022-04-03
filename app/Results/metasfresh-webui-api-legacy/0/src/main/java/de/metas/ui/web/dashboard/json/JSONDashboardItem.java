package de.metas.ui.web.dashboard.json;
 import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.metas.ui.web.dashboard.KPI;
import de.metas.ui.web.dashboard.UserDashboardItem;
import de.metas.ui.web.window.datatypes.json.JSONDocumentLayoutOptions;
@SuppressWarnings("serial")
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class JSONDashboardItem implements Serializable{

@JsonProperty("id")
 private  int id;

@JsonProperty("caption")
 private  String caption;

@JsonProperty("seqNo")
 private  int seqNo;

@JsonProperty("url")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  String url;

@JsonProperty("kpi")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  JsonKPILayout kpi;


public String getUrl(){
    return url;
}


public JsonKPILayout getKPI(){
    return kpi;
}


public JSONDashboardItem of(UserDashboardItem dashboardItem,JSONDocumentLayoutOptions options){
    return new JSONDashboardItem(dashboardItem, options);
}


public int getId(){
    return id;
}


public int getSeqNo(){
    return seqNo;
}


public String getCaption(){
    return caption;
}


}
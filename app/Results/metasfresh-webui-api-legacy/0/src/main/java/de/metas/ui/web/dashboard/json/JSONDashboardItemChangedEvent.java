package de.metas.ui.web.dashboard.json;
 import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import lombok.EqualsAndHashCode;
import lombok.Value;
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Value
@EqualsAndHashCode(callSuper = true)
public class JSONDashboardItemChangedEvent extends JSONDashboardChangedEvent{

 private  int itemId;


public JSONDashboardItemChangedEvent of(int dashboardId,int itemId){
    return new JSONDashboardItemChangedEvent(dashboardId, itemId);
}


}
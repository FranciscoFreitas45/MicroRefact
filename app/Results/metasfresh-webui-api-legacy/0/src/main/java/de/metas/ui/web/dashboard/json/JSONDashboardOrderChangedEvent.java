package de.metas.ui.web.dashboard.json;
 import java.util.Collection;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.google.common.collect.ImmutableList;
import de.metas.ui.web.dashboard.DashboardWidgetType;
import lombok.EqualsAndHashCode;
import lombok.Value;
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Value
@EqualsAndHashCode(callSuper = true)
public class JSONDashboardOrderChangedEvent extends JSONDashboardChangedEvent{

 private  DashboardWidgetType widgetType;

 private  List<Integer> orderedItemIds;


public JSONDashboardOrderChangedEvent of(int dashboardId,DashboardWidgetType widgetType,Collection<Integer> orderedItemIds){
    return new JSONDashboardOrderChangedEvent(dashboardId, widgetType, orderedItemIds);
}


}
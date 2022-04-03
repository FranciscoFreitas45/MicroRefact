package de.metas.ui.web.dashboard;
 import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.concurrent.Immutable;
import org.adempiere.exceptions.AdempiereException;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.websocket.WebSocketConfig;
import de.metas.util.Check;
@Immutable
public class UserDashboard {

 public  UserDashboard EMPTY;

 private  int id;

 private  int adClientId;

 private  Map<Integer,UserDashboardItem> _targetIndicatorItemsById;

 private  Map<Integer,UserDashboardItem> _kpiItemsById;

 private  String websocketEndpoint;

 private  Integer id;

 private  Integer adClientId;

 private  List<UserDashboardItem> targetIndicatorItems;

 private  List<UserDashboardItem> kpiItems;


public Set<Integer> getItemIds(DashboardWidgetType dashboardWidgetType){
    return getItemsById(dashboardWidgetType).keySet();
}


public String getWebsocketEndpoint(){
    return websocketEndpoint;
}


public int getId(){
    return id;
}


public Map<Integer,UserDashboardItem> getItemsById(DashboardWidgetType widgetType){
    if (widgetType == DashboardWidgetType.TargetIndicator) {
        return _targetIndicatorItemsById;
    } else if (widgetType == DashboardWidgetType.KPI) {
        return _kpiItemsById;
    } else {
        throw new AdempiereException("Unknown widget type: " + widgetType);
    }
}


public int getAdClientId(){
    return adClientId;
}


public Collection<UserDashboardItem> getItems(DashboardWidgetType dashboardWidgetType){
    return getItemsById(dashboardWidgetType).values();
}


public Builder addItem(UserDashboardItem item){
    Check.assumeNotNull(item, "Parameter item is not null");
    switch(item.getWidgetType()) {
        case TargetIndicator:
            targetIndicatorItems.add(item);
            break;
        case KPI:
            kpiItems.add(item);
            break;
    }
    return this;
}


public void assertItemIdExists(DashboardWidgetType dashboardWidgetType,int itemId){
    // will fail if itemId does not exist
    getItemById(dashboardWidgetType, itemId);
}


public UserDashboard build(){
    Check.assumeNotNull(id, "Parameter id is not null");
    return new UserDashboard(this);
}


public Builder builder(){
    return new Builder();
}


public Builder setAdClientId(Integer adClientId){
    this.adClientId = adClientId;
    return this;
}


public Builder setId(int id){
    this.id = id;
    return this;
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).omitNullValues().add("id", id).add("targetIndicatorItems", _targetIndicatorItemsById.isEmpty() ? null : _targetIndicatorItemsById).add("kpiItemsById", _kpiItemsById.isEmpty() ? null : _kpiItemsById).toString();
}


public UserDashboardItem getItemById(DashboardWidgetType dashboardWidgetType,int itemId){
    final UserDashboardItem item = getItemsById(dashboardWidgetType).get(itemId);
    if (item == null) {
        throw new EntityNotFoundException("No " + dashboardWidgetType + " item found").setParameter("itemId", itemId);
    }
    return item;
}


}
package de.metas.ui.web.dashboard.UserDashboard;
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
public class Builder {

 private  Integer id;

 private  Integer adClientId;

 private  List<UserDashboardItem> targetIndicatorItems;

 private  List<UserDashboardItem> kpiItems;


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


public UserDashboard build(){
    Check.assumeNotNull(id, "Parameter id is not null");
    return new UserDashboard(this);
}


public Builder setAdClientId(Integer adClientId){
    this.adClientId = adClientId;
    return this;
}


public Builder setId(int id){
    this.id = id;
    return this;
}


}
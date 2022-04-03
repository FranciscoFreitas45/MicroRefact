package de.metas.ui.web.dashboard;
 import javax.annotation.Nullable;
import com.google.common.collect.ImmutableList;
import lombok.Builder;
import lombok.Value;
@Value
@Builder
public class UserDashboardItemChangeResult {

 private  int dashboardId;

 private  DashboardWidgetType dashboardWidgetType;

 private  int itemId;

@Nullable
 private  ImmutableList<Integer> dashboardOrderedItemIds;


public boolean isPositionChanged(){
    return dashboardOrderedItemIds != null && !dashboardOrderedItemIds.isEmpty();
}


}
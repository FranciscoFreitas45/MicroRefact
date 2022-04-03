package de.metas.ui.web.dashboard.json;
 import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
@AllArgsConstructor
@EqualsAndHashCode
public class JSONDashboardChangedEvent {

@NonNull
 private  ChangeType changeType;

 private  int dashboardId;


}
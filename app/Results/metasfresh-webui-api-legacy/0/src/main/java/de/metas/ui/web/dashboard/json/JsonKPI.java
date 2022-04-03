package de.metas.ui.web.dashboard.json;
 import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.google.common.collect.ImmutableSet;
import de.metas.ui.web.dashboard.DashboardWidgetType;
import de.metas.ui.web.dashboard.KPI;
import de.metas.ui.web.dashboard.KPIChartType;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
import lombok.Builder;
import lombok.Value;
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Builder
@Value
public class JsonKPI {

 private  int kpiId;

 private  String caption;

 private  KPIChartType chartType;

 private  ImmutableSet<DashboardWidgetType> widgetTypes;


public JsonKPI of(KPI kpi,JSONOptions jsonOpts){
    return JsonKPI.builder().kpiId(kpi.getId()).caption(kpi.getCaption(jsonOpts.getAdLanguage())).chartType(kpi.getChartType()).widgetTypes(ImmutableSet.copyOf(kpi.getSupportedWidgetTypes())).build();
}


}
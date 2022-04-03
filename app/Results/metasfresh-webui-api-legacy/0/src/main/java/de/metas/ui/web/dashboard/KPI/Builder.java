package de.metas.ui.web.dashboard.KPI;
 import java.time.Duration;
import java.util.List;
import java.util.Set;
import org.adempiere.ad.expression.api.IStringExpression;
import org.adempiere.ad.expression.api.impl.StringExpressionCompiler;
import org.adempiere.exceptions.AdempiereException;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import de.metas.i18n.ITranslatableString;
import de.metas.i18n.TranslatableStrings;
import de.metas.util.Check;
import de.metas.util.GuavaCollectors;
public class Builder {

 private  int id;

 private  ITranslatableString caption;

 private  ITranslatableString description;

 private  KPIChartType chartType;

 private  Duration compareOffset;

 private  List<KPIField> fields;

 private  KPITimeRangeDefaults timeRangeDefaults;

 private  String esSearchTypes;

 private  String esSearchIndex;

 private  String esQuery;

 private  int pollIntervalSec;


public Set<DashboardWidgetType> getSupportedWidgetTypes(){
    if (chartType == KPIChartType.Metric) {
        return ImmutableSet.of(DashboardWidgetType.TargetIndicator);
    } else {
        return ImmutableSet.of(DashboardWidgetType.KPI);
    }
}


public Builder setPollIntervalSec(int pollIntervalSec){
    this.pollIntervalSec = pollIntervalSec;
    return this;
}


public Builder setCaption(ITranslatableString caption){
    this.caption = caption;
    return this;
}


public Builder setFields(List<KPIField> fields){
    this.fields = fields;
    return this;
}


public Builder setDescription(ITranslatableString description){
    this.description = description;
    return this;
}


public Builder setESSearchTypes(String esSearchTypes){
    this.esSearchTypes = esSearchTypes;
    return this;
}


public KPI build(){
    return new KPI(this);
}


public Builder setChartType(KPIChartType chartType){
    this.chartType = chartType;
    return this;
}


public Builder setESSearchIndex(String esSearchIndex){
    this.esSearchIndex = esSearchIndex;
    return this;
}


public Builder setId(int id){
    this.id = id;
    return this;
}


public Builder setCompareOffset(Duration compareOffset){
    this.compareOffset = compareOffset;
    return this;
}


public Builder setESQuery(String query){
    esQuery = query;
    return this;
}


public Builder setTimeRangeDefaults(KPITimeRangeDefaults timeRangeDefaults){
    this.timeRangeDefaults = timeRangeDefaults != null ? timeRangeDefaults : KPITimeRangeDefaults.DEFAULT;
    return this;
}


}
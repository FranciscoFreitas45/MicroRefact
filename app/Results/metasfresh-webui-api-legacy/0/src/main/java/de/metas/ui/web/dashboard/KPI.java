package de.metas.ui.web.dashboard;
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
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class KPI {

 private  int id;

 private  ITranslatableString caption;

 private  ITranslatableString description;

 private  KPIChartType chartType;

 private  ImmutableSet<DashboardWidgetType> supportedWidgetTypes;

 private  Duration compareOffset;

 private  List<KPIField> fields;

 private  KPIField groupByField;

 private  KPITimeRangeDefaults timeRangeDefaults;

 private  String esSearchIndex;

 private  String esSearchTypes;

 private  IStringExpression esQuery;

 private  int pollIntervalSec;

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


public int getId(){
    return id;
}


public Builder setCaption(ITranslatableString caption){
    this.caption = caption;
    return this;
}


public Builder setDescription(ITranslatableString description){
    this.description = description;
    return this;
}


public String getCaption(String adLanguage){
    return caption.translate(adLanguage);
}


public String getDescription(String adLanguage){
    return description.translate(adLanguage);
}


public String getESSearchIndex(){
    return esSearchIndex;
}


public KPIField getGroupByFieldOrNull(){
    return groupByField;
}


public IStringExpression getESQuery(){
    return esQuery;
}


public Builder setESSearchIndex(String esSearchIndex){
    this.esSearchIndex = esSearchIndex;
    return this;
}


public Builder builder(){
    return new Builder();
}


public Builder setId(int id){
    this.id = id;
    return this;
}


public Builder setCompareOffset(Duration compareOffset){
    this.compareOffset = compareOffset;
    return this;
}


public int getPollIntervalSec(){
    return pollIntervalSec;
}


public Duration getCompareOffset(){
    return compareOffset;
}


public String getESSearchTypes(){
    return esSearchTypes;
}


public KPIField getGroupByField(){
    final KPIField groupByField = getGroupByFieldOrNull();
    if (groupByField == null) {
        throw new IllegalStateException("KPI has no group by field defined");
    }
    return groupByField;
}


public boolean hasCompareOffset(){
    return compareOffset != null;
}


public KPITimeRangeDefaults getTimeRangeDefaults(){
    return timeRangeDefaults;
}


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


public Builder setFields(List<KPIField> fields){
    this.fields = fields;
    return this;
}


public KPIChartType getChartType(){
    return chartType;
}


public Builder setESSearchTypes(String esSearchTypes){
    this.esSearchTypes = esSearchTypes;
    return this;
}


public List<KPIField> getFields(){
    return fields;
}


public KPI build(){
    return new KPI(this);
}


public Builder setChartType(KPIChartType chartType){
    this.chartType = chartType;
    return this;
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).omitNullValues().add("id", id).add("caption", caption.getDefaultValue()).toString();
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
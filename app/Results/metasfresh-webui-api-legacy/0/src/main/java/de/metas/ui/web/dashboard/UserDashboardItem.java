package de.metas.ui.web.dashboard;
 import java.util.function.Supplier;
import javax.annotation.concurrent.Immutable;
import com.google.common.base.MoreObjects;
import de.metas.i18n.ITranslatableString;
import de.metas.i18n.TranslatableStrings;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.util.Check;
@Immutable
public class UserDashboardItem {

 private  int id;

 private  ITranslatableString caption;

 private  String url;

 private  int seqNo;

 private  DashboardWidgetType widgetType;

 private  Supplier<KPI> kpiSupplier;

 private  KPITimeRangeDefaults timeRangeDefaults;

 private  Integer id;

 private  ITranslatableString caption;

 private  String url;

 private  int seqNo;

 private  DashboardWidgetType widgetType;

 private  Supplier<KPI> kpiSupplier;

 private  KPITimeRangeDefaults timeRangeDefaults;


public Builder setKPI(Supplier<KPI> kpiSupplier){
    this.kpiSupplier = kpiSupplier;
    return this;
}


public KPITimeRangeDefaults getTimeRangeDefaults(){
    final KPI kpi = getKPI();
    return timeRangeDefaults.compose(kpi.getTimeRangeDefaults());
}


public DashboardWidgetType getWidgetType(){
    return widgetType;
}


public KPI getKPI(){
    final KPI kpi = kpiSupplier == null ? null : kpiSupplier.get();
    if (kpi == null) {
        throw new EntityNotFoundException("No KPI defiend for " + this);
    }
    return kpi;
}


public int getId(){
    return id;
}


public Builder setCaption(String caption){
    this.caption = TranslatableStrings.constant(caption);
    return this;
}


public int getSeqNo(){
    return seqNo;
}


public String getCaption(String adLanguage){
    return caption.translate(adLanguage);
}


public Builder setUrl(String url){
    this.url = url;
    return this;
}


public String getUrl(){
    return url;
}


public Builder setSeqNo(int seqNo){
    this.seqNo = seqNo;
    return this;
}


public UserDashboardItem build(){
    Check.assumeNotNull(id, "Parameter id is not null");
    return new UserDashboardItem(this);
}


public Builder setWidgetType(DashboardWidgetType widgetType){
    this.widgetType = widgetType;
    return this;
}


public Builder builder(){
    return new Builder();
}


public Builder setId(int id){
    this.id = id;
    return this;
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).add("id", id).add("caption", caption).add("seqNo", seqNo).add("widgetType", widgetType).toString();
}


public Builder setTimeRangeDefaults(KPITimeRangeDefaults timeRangeDefaults){
    this.timeRangeDefaults = timeRangeDefaults != null ? timeRangeDefaults : KPITimeRangeDefaults.DEFAULT;
    return this;
}


}
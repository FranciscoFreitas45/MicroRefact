package de.metas.ui.web.dashboard.UserDashboardItem;
 import java.util.function.Supplier;
import javax.annotation.concurrent.Immutable;
import com.google.common.base.MoreObjects;
import de.metas.i18n.ITranslatableString;
import de.metas.i18n.TranslatableStrings;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.util.Check;
public class Builder {

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


public Builder setId(int id){
    this.id = id;
    return this;
}


public Builder setCaption(String caption){
    this.caption = TranslatableStrings.constant(caption);
    return this;
}


public Builder setTimeRangeDefaults(KPITimeRangeDefaults timeRangeDefaults){
    this.timeRangeDefaults = timeRangeDefaults != null ? timeRangeDefaults : KPITimeRangeDefaults.DEFAULT;
    return this;
}


public Builder setUrl(String url){
    this.url = url;
    return this;
}


}
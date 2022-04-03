package DTO;
 import java.util.Optional;
import javax.annotation.Nullable;
import org.slf4j.Logger;
import de.metas.i18n.ITranslatableString;
import de.metas.i18n.TranslatableStrings;
import de.metas.logging.LogManager;
import de.metas.process.BarcodeScannerType;
import de.metas.ui.web.document.filter.DocumentFilterParam.Operator;
import de.metas.ui.web.window.datatypes.DataTypes;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.LookupDescriptor;
import de.metas.ui.web.window.descriptor.factory.standard.DescriptorsFactoryHelper;
import de.metas.ui.web.window.model.lookup.LookupDataSource;
import de.metas.ui.web.window.model.lookup.LookupDataSourceFactory;
import de.metas.util.Check;
import lombok.NonNull;
import lombok.Value;
public class DocumentFilterParamDescriptor {

 private  Logger logger;

 private  boolean joinAnd;

 private  String parameterName;

 private  String fieldName;

 private  DocumentFieldWidgetType widgetType;

 private  Class<?> valueClass;

 private  ITranslatableString displayName;

 private  boolean showIncrementDecrementButtons;

 private  Operator operator;

 private  Object defaultValue;

 private  Object defaultValueTo;

 private  boolean mandatory;

 private  Optional<LookupDescriptor> lookupDescriptor;

 public  String AUTOFILTER_INITIALVALUE_DATE_NOW;

 private  Object autoFilterInitialValue;

 private  BarcodeScannerType barcodeScannerType;

 private  boolean joinAnd;

 private  String fieldName;

 private  String parameterName;

 private  DocumentFieldWidgetType widgetType;

 private  BarcodeScannerType barcodeScannerType;

 private  ITranslatableString displayName;

 private  Operator operator;

 private  Object defaultValue;

 private  Object defaultValueTo;

 private  Optional<LookupDescriptor> lookupDescriptor;

 private  boolean mandatory;

 private  boolean showIncrementDecrementButtons;

 private  Object autoFilterInitialValue;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://16";


public Object getDefaultValueToConverted(){
    return convertValueToFieldType(getDefaultValueTo());
}


public DocumentFieldWidgetType getWidgetType(){
    return widgetType;
}


public Optional<LookupDataSource> getLookupDataSource(){
    return lookupDescriptor.map(LookupDataSourceFactory.instance::getLookupDataSource);
}


public ITranslatableString getDisplayName(){
    return displayName;
}


public Object getDefaultValueConverted(){
    return convertValueToFieldType(getDefaultValue());
}


public String getFieldName(){
    return fieldName;
}


public boolean isAutoFilterInitialValueIsDateNow(){
    return widgetType.isDateOrTime() && AUTOFILTER_INITIALVALUE_DATE_NOW.equals(autoFilterInitialValue);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isAutoFilterInitialValueIsDateNow"))

boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


}
package DTO;
 import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;
import org.adempiere.exceptions.AdempiereException;
import org.slf4j.Logger;
import com.google.common.base.Joiner;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableSet;
import de.metas.i18n.IMsgBL;
import de.metas.i18n.ITranslatableString;
import de.metas.i18n.TranslatableStrings;
import de.metas.logging.LogManager;
import de.metas.process.BarcodeScannerType;
import de.metas.ui.web.window.datatypes.MediaType;
import de.metas.ui.web.window.exceptions.DocumentLayoutBuildException;
import de.metas.util.Check;
import de.metas.util.GuavaCollectors;
import de.metas.util.Services;
import lombok.NonNull;
public class DocumentLayoutElementDescriptor {

 private  String internalName;

 private  boolean gridElement;

 private  ITranslatableString caption;

 private  ITranslatableString description;

 private  DocumentFieldWidgetType widgetType;

 private  boolean allowShowPassword;

 private  boolean multilineText;

 private  int multilineTextLines;

 private  int maxLength;

 private  ButtonFieldActionDescriptor buttonActionDescriptor;

 private  BarcodeScannerType barcodeScannerType;

 private  LayoutType layoutType;

 private  WidgetSize widgetSize;

 private  boolean advancedField;

 private  ImmutableSet<MediaType> restrictToMediaTypes;

 private  LayoutAlign gridAlign;

 private  ViewEditorRenderMode viewEditorRenderMode;

 private  boolean viewAllowSorting;

 private  Set<DocumentLayoutElementFieldDescriptor> fields;

 private  String _captionAsFieldNames;

 private  Joiner JOINER_FieldNames;

 private  Logger logger;

 private  String _internalName;

 private  ITranslatableString _caption;

 private  ITranslatableString _description;

 private  DocumentFieldWidgetType _widgetType;

 private  boolean _allowShowPassword;

 private  boolean _multilineText;

 private  int _multilineTextLines;

 private  int _maxLength;

 private  ButtonFieldActionDescriptor buttonActionDescriptor;

 private  BarcodeScannerType barcodeScannerType;

 private  LayoutType _layoutType;

 private  WidgetSize _widgetSize;

 private  Set<MediaType> restrictToMediaTypes;

 private  boolean _gridElement;

 private  ViewEditorRenderMode viewEditorRenderMode;

 private  boolean viewAllowSorting;

 private  boolean _advancedField;

 private  LinkedHashMap<String,DocumentLayoutElementFieldDescriptor.Builder> _fieldsBuilders;

 private  boolean excludeSpecialFields;

 private  boolean consumed;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";


public int getFieldsCount(){
    return _fieldsBuilders.size();
}


public ITranslatableString getCaption(){
    if (_caption != null) {
        return _caption;
    }
    final DocumentLayoutElementFieldDescriptor.Builder firstField = getFirstField();
    if (firstField != null) {
        final String fieldName = firstField.getFieldName();
        return Services.get(IMsgBL.class).translatable(fieldName);
    }
    return TranslatableStrings.empty();
}


public DocumentLayoutElementFieldDescriptor.Builder getField(String fieldName){
    return _fieldsBuilders.get(fieldName);
}


public WidgetSize getWidgetSize(){
    return _widgetSize;
}


public Set<DocumentLayoutElementFieldDescriptor> getFields(){
    return fields;
}


public LayoutAlign getGridAlign(){
    return isGridElement() ? getWidgetType().getGridAlign() : null;
}


public ITranslatableString getDescription(){
    return TranslatableStrings.nullToEmpty(_description);
}


public Set<String> getFieldNames(){
    return _fieldsBuilders.keySet();
}


public ImmutableSet<MediaType> getRestrictToMediaTypes(){
    return ImmutableSet.copyOf(restrictToMediaTypes);
}


public String getCaptionAsFieldNames(){
    if (_captionAsFieldNames == null) {
        _captionAsFieldNames = fields.stream().filter(// only those which are public
        field -> field.isPublicField()).map(field -> field.getField()).collect(GuavaCollectors.toString(JOINER_FieldNames));
    }
    return _captionAsFieldNames;
}


public ButtonFieldActionDescriptor getButtonActionDescriptor(){
    return buttonActionDescriptor;
}


public ViewEditorRenderMode getViewEditorRenderMode(){
    return viewEditorRenderMode;
}


public String getFirstFieldName(){
    return fields.iterator().next().getField();
}


public LayoutType getLayoutType(){
    return _layoutType;
}


public DocumentFieldWidgetType getWidgetType(){
    Check.assumeNotNull(_widgetType, DocumentLayoutBuildException.class, "Parameter widgetType is not null for {}", this);
    return _widgetType;
}


public DocumentLayoutElementFieldDescriptor.Builder getFirstField(){
    return _fieldsBuilders.values().iterator().next();
}


public int getMultilineTextLines(){
    return _multilineTextLines;
}


public BarcodeScannerType getBarcodeScannerType(){
    return barcodeScannerType;
}


public int getMaxLength(){
    return _maxLength;
}


public String getInternalName(){
    return _internalName;
}


public Builder builder(DocumentFieldDescriptor fields){
    Check.assumeNotEmpty(fields, "fields is not empty");
    final DocumentFieldDescriptor firstField = fields[0];
    final Builder elementBuilder = new Builder().setCaption(firstField.getCaption()).setWidgetType(firstField.getWidgetType()).setMaxLength(firstField.getFieldMaxLength()).setWidgetSize(firstField.getWidgetSize());
    for (final DocumentFieldDescriptor field : fields) {
        elementBuilder.addField(DocumentLayoutElementFieldDescriptor.builder(field.getFieldName()).setCaption(field.getCaption()).setPublicField(true).setLookupInfos(field.getLookupDescriptor().orElse(null)).setSupportZoomInto(field.isSupportZoomInto()));
    }
    return elementBuilder;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/builder"))

.queryParam("fields",fields);
Builder aux = restTemplate.getForObject(builder.toUriString(),Builder.class);
return aux;
}


}
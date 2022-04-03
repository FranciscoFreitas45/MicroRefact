package de.metas.ui.web.window.descriptor;
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


public boolean hasFieldName(String fieldName){
    return _fieldsBuilders.containsKey(fieldName);
}


public Builder setGridElement(){
    _gridElement = true;
    return this;
}


public boolean isGridElement(){
    return _gridElement;
}


public Builder restrictToMediaTypes(Collection<MediaType> mediaTypes){
    restrictToMediaTypes.addAll(mediaTypes);
    return this;
}


public int getFieldsCount(){
    return _fieldsBuilders.size();
}


public boolean hasFields(){
    return !fields.isEmpty();
}


public Builder setMultilineTextLines(int multilineTextLines){
    this._multilineTextLines = multilineTextLines;
    return this;
}


public boolean isViewAllowSorting(){
    return viewAllowSorting;
}


public Builder setCaption(String caption){
    setCaption(TranslatableStrings.constant(caption));
    return this;
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


public Builder setButtonActionDescriptor(ButtonFieldActionDescriptor buttonActionDescriptor){
    this.buttonActionDescriptor = buttonActionDescriptor;
    return this;
}


public boolean isWidgetSizeSet(){
    return _widgetSize != null;
}


public Builder setExcludeSpecialFields(){
    excludeSpecialFields = true;
    return this;
}


public boolean isMultilineText(){
    return _multilineText;
}


public boolean isWidgetTypeSet(){
    return _widgetType != null;
}


public Builder setInternalName(String internalName){
    _internalName = internalName;
    return this;
}


public Optional<Builder> builderOrEmpty(DocumentEntityDescriptor entityDescriptor,String fieldNames){
    Check.assumeNotEmpty(fieldNames, "fieldNames is not empty");
    final DocumentFieldDescriptor[] elementFields = Stream.of(fieldNames).map(fieldName -> entityDescriptor.getFieldOrNull(fieldName)).filter(Objects::nonNull).toArray(size -> new DocumentFieldDescriptor[size]);
    if (elementFields.length == 0) {
        return Optional.empty();
    }
    return Optional.of(builder(elementFields));
}


public Builder addField(DocumentLayoutElementFieldDescriptor.Builder fieldBuilder){
    final DocumentLayoutElementFieldDescriptor.Builder previousFieldBuilder = _fieldsBuilders.put(fieldBuilder.getFieldName(), fieldBuilder);
    if (previousFieldBuilder != null) {
        new AdempiereException("Field " + fieldBuilder.getFieldName() + " already exists in element: " + this).throwIfDeveloperModeOrLogWarningElse(logger);
    }
    return this;
}


public DocumentLayoutElementFieldDescriptor.Builder getField(String fieldName){
    return _fieldsBuilders.get(fieldName);
}


public WidgetSize getWidgetSize(){
    return _widgetSize;
}


public Builder setViewAllowSorting(boolean viewAllowSorting){
    this.viewAllowSorting = viewAllowSorting;
    return this;
}


public Builder restrictToMediaType(MediaType mediaType){
    restrictToMediaTypes.add(mediaType);
    return this;
}


public boolean isComposedField(){
    final long countRegularFields = _fieldsBuilders.values().stream().filter(DocumentLayoutElementFieldDescriptor.Builder::isRegularField).count();
    return countRegularFields > 1;
}


public Set<DocumentLayoutElementFieldDescriptor> getFields(){
    return fields;
}


public Builder setWidgetType(DocumentFieldWidgetType widgetType){
    _widgetType = widgetType;
    return this;
}


public Builder setAdvancedField(boolean advancedField){
    _advancedField = advancedField;
    return this;
}


public Builder setViewEditorRenderMode(ViewEditorRenderMode gridEditorRenderMode){
    this.viewEditorRenderMode = gridEditorRenderMode;
    return this;
}


public LayoutAlign getGridAlign(){
    return isGridElement() ? getWidgetType().getGridAlign() : null;
}


public Builder setMaxLength(int maxLength){
    this._maxLength = maxLength;
    return this;
}


public Builder setAllowShowPassword(boolean allowShowPassword){
    this._allowShowPassword = allowShowPassword;
    return this;
}


public Set<DocumentLayoutElementFieldDescriptor> buildFields(){
    updateFieldsEmptyTexts();
    return _fieldsBuilders.values().stream().filter(fieldBuilder -> checkValid(fieldBuilder)).map(fieldBuilder -> fieldBuilder.build()).collect(GuavaCollectors.toImmutableSet());
}


public Builder setCaptionFromAD_Message(String adMessage){
    setCaption(Services.get(IMsgBL.class).translatable(adMessage));
    return this;
}


public Builder setLayoutType(LayoutType layoutType){
    _layoutType = layoutType;
    return this;
}


public boolean checkValid(DocumentLayoutElementFieldDescriptor.Builder fieldBuilder){
    if (fieldBuilder.isConsumed()) {
        logger.trace("Skip adding {} to {} because it's already consumed", fieldBuilder, this);
        return false;
    }
    if (!fieldBuilder.isPublicField()) {
        logger.trace("Skip adding {} to {} because it's not a public field", fieldBuilder, this);
        return false;
    }
    if (excludeSpecialFields && fieldBuilder.isSpecialFieldToExcludeFromLayout()) {
        logger.trace("Skip adding {} to {} because it's a special field and we were asked to exclude special fields", fieldBuilder, this);
        return false;
    }
    return true;
}


public Builder setDescription(ITranslatableString description){
    _description = TranslatableStrings.nullToEmpty(description);
    return this;
}


public ITranslatableString getDescription(){
    return TranslatableStrings.nullToEmpty(_description);
}


public Set<String> getFieldNames(){
    return _fieldsBuilders.keySet();
}


public Builder setConsumed(){
    consumed = true;
    return this;
}


public boolean isAllowShowPassword(){
    return _allowShowPassword;
}


public boolean isConsumed(){
    return consumed;
}


public void updateFieldsEmptyTexts(){
    if (!isComposedField()) {
        return;
    }
    for (final DocumentLayoutElementFieldDescriptor.Builder field : _fieldsBuilders.values()) {
        if (field.isRegularField() && !TranslatableStrings.isBlank(field.getCaption())) {
            field.setEmptyFieldText(field.getCaption());
        }
    }
}


public Builder builder(DocumentFieldDescriptor fields){
    Check.assumeNotEmpty(fields, "fields is not empty");
    final DocumentFieldDescriptor firstField = fields[0];
    final Builder elementBuilder = new Builder().setCaption(firstField.getCaption()).setWidgetType(firstField.getWidgetType()).setMaxLength(firstField.getFieldMaxLength()).setWidgetSize(firstField.getWidgetSize());
    for (final DocumentFieldDescriptor field : fields) {
        elementBuilder.addField(DocumentLayoutElementFieldDescriptor.builder(field.getFieldName()).setCaption(field.getCaption()).setPublicField(true).setLookupInfos(field.getLookupDescriptor().orElse(null)).setSupportZoomInto(field.isSupportZoomInto()));
    }
    return elementBuilder;
}


public ImmutableSet<MediaType> getRestrictToMediaTypes(){
    return ImmutableSet.copyOf(restrictToMediaTypes);
}


public Builder removeFieldByFieldName(String fieldName){
    _fieldsBuilders.remove(fieldName);
    return this;
}


public String getCaptionAsFieldNames(){
    if (_captionAsFieldNames == null) {
        _captionAsFieldNames = fields.stream().filter(// only those which are public
        field -> field.isPublicField()).map(field -> field.getField()).collect(GuavaCollectors.toString(JOINER_FieldNames));
    }
    return _captionAsFieldNames;
}


public Builder barcodeScannerType(BarcodeScannerType barcodeScannerType){
    this.barcodeScannerType = barcodeScannerType;
    return this;
}


public Builder setLayoutTypeNone(){
    _layoutType = null;
    return this;
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


public Builder setMultilineText(boolean multilineText){
    this._multilineText = multilineText;
    return this;
}


public DocumentLayoutElementFieldDescriptor.Builder getFirstField(){
    return _fieldsBuilders.values().iterator().next();
}


public boolean isAdvancedField(){
    return _advancedField;
}


public Builder setNotGridElement(){
    _gridElement = false;
    return this;
}


public int getMultilineTextLines(){
    return _multilineTextLines;
}


public BarcodeScannerType getBarcodeScannerType(){
    return barcodeScannerType;
}


public Builder setWidgetSize(WidgetSize widgetSize){
    _widgetSize = widgetSize;
    return this;
}


public int getMaxLength(){
    return _maxLength;
}


public DocumentLayoutElementDescriptor build(){
    setConsumed();
    final DocumentLayoutElementDescriptor result = new DocumentLayoutElementDescriptor(this);
    logger.trace("Built {} for {}", result, this);
    return result;
}


public String getInternalName(){
    return _internalName;
}


public Builder setCaptionNone(){
    setCaption(TranslatableStrings.empty());
    return this;
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).omitNullValues().add("internalName", _internalName).add("caption", _caption).add("description", _description).add("widgetType", _widgetType).add("consumed", consumed ? Boolean.TRUE : null).add("fields-count", _fieldsBuilders.size()).toString();
}


}
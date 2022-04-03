package de.metas.ui.web.window.descriptor.DocumentFieldDescriptor;
 import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import javax.annotation.Nullable;
import org.adempiere.ad.expression.api.ConstantLogicExpression;
import org.adempiere.ad.expression.api.IExpression;
import org.adempiere.ad.expression.api.ILogicExpression;
import org.adempiere.ad.expression.api.impl.LogicExpressionCompiler;
import org.adempiere.exceptions.AdempiereException;
import org.slf4j.Logger;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import de.metas.i18n.ITranslatableString;
import de.metas.i18n.TranslatableStrings;
import de.metas.logging.LogManager;
import de.metas.process.BarcodeScannerType;
import de.metas.ui.web.window.WindowConstants;
import de.metas.ui.web.window.datatypes.DataTypes;
import de.metas.ui.web.window.datatypes.LookupValue.IntegerLookupValue;
import de.metas.ui.web.window.datatypes.LookupValue.StringLookupValue;
import de.metas.ui.web.window.descriptor.DocumentFieldDependencyMap.DependencyType;
import de.metas.ui.web.window.model.IDocumentFieldValueProvider;
import de.metas.ui.web.window.model.lookup.LookupDataSource;
import de.metas.ui.web.window.model.lookup.LookupDataSourceFactory;
import de.metas.ui.web.window.model.lookup.LookupValueByIdSupplier;
import de.metas.util.Check;
import lombok.Getter;
import lombok.NonNull;
public class Builder {

 private  DocumentFieldDescriptor _fieldBuilt;

 private  String fieldName;

 private  ITranslatableString caption;

 private  ITranslatableString description;

 public  DetailId _detailId;

 private  boolean key;

 private  boolean parentLink;

 private  String parentLinkFieldName;

 private  boolean virtualField;

 private  Optional<IDocumentFieldValueProvider> virtualFieldValueProvider;

 private  boolean calculated;

 private  DocumentFieldWidgetType _widgetType;

 private  WidgetSize _widgetSize;

 private  int _fieldMaxLength;

 private  Class<?> _valueClass;

 private  boolean _allowShowPassword;

 private  BarcodeScannerType _barcodeScannerType;

 private  LookupDescriptorProvider lookupDescriptorProvider;

 private  Optional<IExpression<?>> defaultValueExpression;

 private  Set<Characteristic> characteristics;

 private  ILogicExpression _entityReadonlyLogic;

 private  ILogicExpression _readonlyLogic;

 private  ILogicExpression _readonlyLogicEffective;

 private  boolean alwaysUpdateable;

 private  ILogicExpression displayLogic;

 private  ILogicExpression _mandatoryLogic;

 private  ILogicExpression _mandatoryLogicEffective;

 private  Optional<DocumentFieldDataBindingDescriptor> _dataBinding;

 private  List<IDocumentFieldCallout> callouts;

 private  ButtonFieldActionDescriptor buttonActionDescriptor;

@Getter
 private  String tooltipIconName;

 private  DocumentFieldDefaultFilterDescriptor defaultFilterInfo;


public Class<?> getValueClass(){
    if (_valueClass != null) {
        return _valueClass;
    }
    final DocumentFieldWidgetType widgetType = getWidgetType();
    if (widgetType.getValueClassOrNull() != null) {
        return widgetType.getValueClassOrNull();
    }
    final LookupDescriptorProvider lookupDescriptor = getLookupDescriptorProvider();
    if (lookupDescriptor != null) {
        return lookupDescriptor.isNumericKey() ? IntegerLookupValue.class : StringLookupValue.class;
    }
    throw new AdempiereException("valueClass is unknown for " + this);
}


public boolean isCalculated(){
    if (isVirtualField()) {
        return true;
    }
    return calculated;
}


public Builder setCaption(String caption){
    this.caption = TranslatableStrings.constant(caption);
    return this;
}


public ITranslatableString getCaption(){
    if (caption == null) {
        return TranslatableStrings.constant(fieldName);
    }
    return caption;
}


public boolean isDetail(){
    return getDetailId() != null;
}


public Builder setParentLink(boolean parentLink,String parentLinkFieldName){
    assertNotBuilt();
    this.parentLink = parentLink;
    this.parentLinkFieldName = parentLinkFieldName;
    return this;
}


public ILogicExpression getReadonlyLogicEffective(){
    if (_readonlyLogicEffective == null) {
        _readonlyLogicEffective = buildReadonlyLogicEffective();
    }
    return _readonlyLogicEffective;
}


public void assertNotBuilt(){
    if (_fieldBuilt != null) {
        throw new IllegalStateException("Already built: " + this);
    }
}


public Builder setButtonActionDescriptor(ButtonFieldActionDescriptor buttonActionDescriptor){
    this.buttonActionDescriptor = buttonActionDescriptor;
    return this;
}


public boolean isSupportZoomInto(){
    // Allow zooming into key column. It shall open precisely this record in a new window
    // (see https://github.com/metasfresh/metasfresh/issues/1687 to understand the use-case)
    // In future we shall think to narrow it down only to included tabs and only for those tables which also have a window where they are the header document.
    if (isKey()) {
        return true;
    }
    final DocumentFieldWidgetType widgetType = getWidgetType();
    if (!widgetType.isSupportZoomInto()) {
        return false;
    }
    final Class<?> valueClass = getValueClass();
    if (StringLookupValue.class.isAssignableFrom(valueClass)) {
        return false;
    }
    final String lookupTableName = getLookupTableName().orElse(null);
    if (WindowConstants.TABLENAME_AD_Ref_List.equals(lookupTableName)) {
        return false;
    }
    return true;
}


public Builder setReadonlyLogic(boolean readonly){
    setReadonlyLogic(ConstantLogicExpression.of(readonly));
    return this;
}


public ILogicExpression getReadonlyLogic(){
    return _readonlyLogic;
}


public ILogicExpression getEntityReadonlyLogic(){
    return _entityReadonlyLogic;
}


public DocumentFieldDescriptor getOrBuild(){
    if (_fieldBuilt == null) {
        _fieldBuilt = new DocumentFieldDescriptor(this);
    }
    return _fieldBuilt;
}


public Optional<LookupDescriptor> getLookupDescriptor(){
    return getLookupDescriptorProvider().provide();
}


public Builder setTooltipIconName(String tooltipIconName){
    this.tooltipIconName = tooltipIconName;
    return this;
}


public Builder setDetailId(DetailId detailId){
    assertNotBuilt();
    _detailId = detailId;
    return this;
}


public ILogicExpression getDisplayLogic(){
    return displayLogic;
}


public Builder setKey(boolean key){
    assertNotBuilt();
    this.key = key;
    return this;
}


public ILogicExpression getMandatoryLogicEffective(){
    if (_mandatoryLogicEffective == null) {
        _mandatoryLogicEffective = buildMandatoryLogicEffective();
    }
    return _mandatoryLogicEffective;
}


public Builder setFieldMaxLength(int fieldMaxLength){
    this._fieldMaxLength = fieldMaxLength;
    return this;
}


public boolean hasCharacteristic(Characteristic c){
    return characteristics.contains(c);
}


public boolean isDefaultOrderBy(){
    final DocumentFieldDataBindingDescriptor dataBinding = getDataBinding().orElse(null);
    return dataBinding != null ? dataBinding.isDefaultOrderBy() : false;
}


public Builder setLookupDescriptorProvider(LookupDescriptor lookupDescriptor){
    setLookupDescriptorProvider(LookupDescriptorProviders.ofNullableInstance(lookupDescriptor));
    return this;
}


public ILogicExpression buildReadonlyLogicEffective(){
    if (isParentLinkEffective()) {
        return ConstantLogicExpression.TRUE;
    }
    if (isVirtualField()) {
        return ConstantLogicExpression.TRUE;
    }
    if (isKey()) {
        return ConstantLogicExpression.TRUE;
    }
    // If the tab is always readonly, we can assume any field in that tab is readonly
    final ILogicExpression entityReadonlyLogic = getEntityReadonlyLogic();
    if (entityReadonlyLogic.isConstantTrue()) {
        return ConstantLogicExpression.TRUE;
    }
    // Case: DocAction
    if (hasCharacteristic(Characteristic.SpecialField_DocAction)) {
        return ConstantLogicExpression.FALSE;
    }
    final ILogicExpression fieldReadonlyLogic = getReadonlyLogic();
    if (fieldReadonlyLogic.isConstantTrue()) {
        return ConstantLogicExpression.TRUE;
    }
    final String fieldName = getFieldName();
    if (WindowConstants.FIELDNAMES_CreatedUpdated.contains(fieldName)) {
        // NOTE: from UI perspective those are readonly (i.e. it will be managed by persistence layer)
        return ConstantLogicExpression.TRUE;
    }
    if (hasCharacteristic(Characteristic.SpecialField_DocStatus)) {
        // NOTE: DocStatus field shall always be readonly
        return ConstantLogicExpression.TRUE;
    }
    ILogicExpression readonlyLogic = fieldReadonlyLogic;
    // FIXME: not sure if using tabReadonlyLogic here is OK, because the tab logic shall be applied to parent tab!
    if (!entityReadonlyLogic.isConstantFalse()) {
        readonlyLogic = entityReadonlyLogic.or(fieldReadonlyLogic);
    }
    return readonlyLogic;
}


public ILogicExpression buildMandatoryLogicEffective(){
    if (isParentLinkEffective()) {
        return ConstantLogicExpression.TRUE;
    }
    final String fieldName = getFieldName();
    if (WindowConstants.FIELDNAMES_CreatedUpdated.contains(fieldName)) {
        // NOTE: from UI perspective those are not mandatory (i.e. it will be managed by persistence layer)
        return ConstantLogicExpression.FALSE;
    }
    if (isVirtualField()) {
        return ConstantLogicExpression.FALSE;
    }
    // FIXME: hardcoded M_AttributeSetInstance_ID mandatory logic = false
    // Reason: even if we set it's default value to "0" some callouts are setting it to NULL,
    // and then the document saving API is failing because it considers this column as NOT filled.
    if (WindowConstants.FIELDNAME_M_AttributeSetInstance_ID.equals(fieldName)) {
        return ConstantLogicExpression.FALSE;
    }
    // Corner case:
    // e.g. C_Order.M_Shipper_ID has AD_Field.IsMandatory=Y, AD_Field.IsDisplayed=N, AD_Column.IsMandatory=N
    // => we need to NOT enforce setting it because it's not needed, user cannot change it and it might be no callouts to set it.
    // Else, we won't be able to save our document.
    final boolean publicField = hasCharacteristic(Characteristic.PublicField);
    final ILogicExpression mandatoryLogic = _mandatoryLogic;
    final boolean mandatory = mandatoryLogic.isConstantTrue();
    final DocumentFieldDataBindingDescriptor fieldDataBinding = getDataBinding().orElse(null);
    final boolean mandatoryDB = fieldDataBinding != null && fieldDataBinding.isMandatory();
    if (!publicField && mandatory && !mandatoryDB) {
        return ConstantLogicExpression.FALSE;
    }
    // Case: DocumentNo special field shall always be mandatory
    if (hasCharacteristic(Characteristic.SpecialField_DocumentNo)) {
        return ConstantLogicExpression.TRUE;
    }
    if (mandatory) {
        return ConstantLogicExpression.TRUE;
    }
    return mandatoryLogic;
}


public boolean isKey(){
    return key;
}


public Builder setValueClass(Class<?> valueClass){
    assertNotBuilt();
    _valueClass = valueClass;
    return this;
}


public WidgetSize getWidgetSize(){
    return _widgetSize;
}


public ImmutableList<IDocumentFieldCallout> buildCallouts(){
    return ImmutableList.copyOf(callouts);
}


public Builder setMandatoryLogic(boolean mandatory){
    setMandatoryLogic(ConstantLogicExpression.of(mandatory));
    return this;
}


public Builder setVirtualField(IDocumentFieldValueProvider virtualFieldValueProvider){
    assertNotBuilt();
    virtualField = true;
    this.virtualFieldValueProvider = Optional.of(virtualFieldValueProvider);
    return this;
}


public Builder setWidgetType(DocumentFieldWidgetType widgetType){
    assertNotBuilt();
    _widgetType = widgetType;
    return this;
}


public Builder setDefaultValueExpression(IExpression<?> defaultValueExpression){
    assertNotBuilt();
    this.defaultValueExpression = Optional.of(defaultValueExpression);
    return this;
}


public Builder addCharacteristic(Characteristic c){
    assertNotBuilt();
    characteristics.add(c);
    return this;
}


public boolean isParentLinkEffective(){
    return parentLink && isDetail();
}


public Optional<IDocumentFieldValueProvider> getVirtualFieldValueProvider(){
    return virtualFieldValueProvider;
}


public boolean isAlwaysUpdateable(){
    return alwaysUpdateable;
}


public String getFieldName(){
    return fieldName;
}


public int getFieldMaxLength(){
    return _fieldMaxLength;
}


public Builder addCharacteristicIfTrue(boolean test,Characteristic c){
    if (test) {
        addCharacteristic(c);
    }
    return this;
}


public Builder setAllowShowPassword(boolean allowShowPassword){
    this._allowShowPassword = allowShowPassword;
    return this;
}


public Builder addCallout(ILambdaDocumentFieldCallout lambdaCallout){
    final LambdaDocumentFieldCallout callout = new LambdaDocumentFieldCallout(getFieldName(), lambdaCallout);
    addCallout(callout);
    return this;
}


public Builder setAlwaysUpdateable(boolean alwaysUpdateable){
    assertNotBuilt();
    this.alwaysUpdateable = alwaysUpdateable;
    return this;
}


public Builder removeCharacteristic(Characteristic c){
    assertNotBuilt();
    characteristics.remove(c);
    return this;
}


public Builder setDataBinding(DocumentFieldDataBindingDescriptor dataBinding){
    assertNotBuilt();
    _dataBinding = Optional.ofNullable(dataBinding);
    return this;
}


public Builder setCalculated(boolean calculated){
    assertNotBuilt();
    this.calculated = calculated;
    return this;
}


public Builder setDisplayLogic(String displayLogic){
    setDisplayLogic(LogicExpressionCompiler.instance.compile(displayLogic));
    return this;
}


public Builder setDescription(String description){
    this.description = TranslatableStrings.constant(description);
    return this;
}


public ITranslatableString getDescription(){
    if (description == null) {
        return TranslatableStrings.empty();
    }
    return description;
}


public boolean isAllowShowPassword(){
    return _allowShowPassword;
}


public Optional<String> getLookupTableName(){
    return getLookupDescriptorProvider().getTableName();
}


public boolean isSpecialFieldToExcludeFromLayout(){
    return !Collections.disjoint(characteristics, SPECIALFIELDS_ToExcludeFromLayout);
}


public Optional<DocumentFieldDataBindingDescriptor> getDataBinding(){
    return _dataBinding;
}


public DetailId getDetailId(){
    return _detailId;
}


public Builder barcodeScannerType(BarcodeScannerType barcodeScannerType){
    this._barcodeScannerType = barcodeScannerType;
    return this;
}


public ButtonFieldActionDescriptor getButtonActionDescriptor(){
    return buttonActionDescriptor;
}


public LookupDescriptorProvider getLookupDescriptorProvider(){
    return lookupDescriptorProvider;
}


public int getDefaultOrderByPriority(){
    // we assume isDefaultOrderBy() was checked before calling this method
    return getDataBinding().get().getDefaultOrderByPriority();
}


public DocumentFieldWidgetType getWidgetType(){
    Preconditions.checkNotNull(_widgetType, "widgetType is null");
    return _widgetType;
}


public Builder setLookupDescriptorProvider_None(){
    setLookupDescriptorProvider(LookupDescriptorProviders.NULL);
    return this;
}


public void setEntityReadonlyLogic(ILogicExpression entityReadonlyLogic){
    _entityReadonlyLogic = entityReadonlyLogic;
}


public BarcodeScannerType getBarcodeScannerType(){
    return _barcodeScannerType;
}


public Builder setDefaultFilterInfo(DocumentFieldDefaultFilterDescriptor defaultFilterInfo){
    this.defaultFilterInfo = defaultFilterInfo;
    return this;
}


public DocumentFieldDependencyMap buildDependencies(){
    final DocumentFieldDependencyMap.Builder dependencyMapBuilder = DocumentFieldDependencyMap.builder().add(fieldName, getReadonlyLogicEffective().getParameterNames(), DependencyType.ReadonlyLogic).add(fieldName, getDisplayLogic().getParameterNames(), DependencyType.DisplayLogic).add(fieldName, getMandatoryLogicEffective().getParameterNames(), DependencyType.MandatoryLogic);
    final LookupDescriptor lookupDescriptor = getLookupDescriptorProvider().provide().orElse(null);
    if (lookupDescriptor != null) {
        dependencyMapBuilder.add(fieldName, lookupDescriptor.getDependsOnFieldNames(), DependencyType.LookupValues);
    }
    final IDocumentFieldValueProvider virtualFieldValueProvider = getVirtualFieldValueProvider().orElse(null);
    if (virtualFieldValueProvider != null) {
        dependencyMapBuilder.add(fieldName, virtualFieldValueProvider.getDependsOnFieldNames(), DependencyType.FieldValue);
    }
    return dependencyMapBuilder.build();
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).omitNullValues().add("name", fieldName).add("detailId", _detailId).add("widgetType", _widgetType).add("characteristics", characteristics.isEmpty() ? null : characteristics).toString();
}


public boolean isVirtualField(){
    return virtualField;
}


public boolean isPossiblePublicField(){
    // Always publish the key columns, else the client won't know what to talk about ;)
    if (isKey()) {
        return true;
    }
    // If display logic is not constant then we don't know if this field will be ever visible
    // so we are publishing it
    if (!displayLogic.isConstant()) {
        return true;
    }
    // Publish this field only if it's displayed
    return displayLogic.isConstantTrue();
}


public boolean isDefaultOrderByAscending(){
    // we assume isDefaultOrderBy() was checked before calling this method
    return getDataBinding().get().isDefaultOrderByAscending();
}


}
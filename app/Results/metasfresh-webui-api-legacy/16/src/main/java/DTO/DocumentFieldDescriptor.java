package DTO;
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
public class DocumentFieldDescriptor {

 private  Logger logger;

 private  String fieldName;

 private  ITranslatableString caption;

 private  ITranslatableString description;

 private  DetailId detailId;

 private  boolean key;

 private  boolean calculated;

 private  boolean parentLink;

 private  String parentLinkFieldName;

 private  DocumentFieldWidgetType widgetType;

 private  int fieldMaxLength;

 private  boolean allowShowPassword;

 private  ButtonFieldActionDescriptor buttonActionDescriptor;

 private  BarcodeScannerType barcodeScannerType;

 private  WidgetSize widgetSize;

 private  Class<?> valueClass;

 private  LookupDescriptorProvider lookupDescriptorProvider;

 private  boolean supportZoomInto;

 private  boolean virtualField;

 private  Optional<IDocumentFieldValueProvider> virtualFieldValueProvider;

 private  Optional<IExpression<?>> defaultValueExpression;

 private  ImmutableList<IDocumentFieldCallout> callouts;

 private  List<Characteristic> SPECIALFIELDS_ToExcludeFromLayout;

 private  Set<Characteristic> characteristics;

 private  ILogicExpression readonlyLogic;

 private  boolean alwaysUpdateable;

 private  ILogicExpression displayLogic;

 private  ILogicExpression mandatoryLogic;

 private  Optional<DocumentFieldDataBindingDescriptor> dataBinding;

 private  DocumentFieldDependencyMap dependencies;

 private  DocumentFieldDefaultFilterDescriptor defaultFilterInfo;

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


public ITranslatableString getCaption(){
    if (caption == null) {
        return TranslatableStrings.constant(fieldName);
    }
    return caption;
}


public ILogicExpression getReadonlyLogicEffective(){
    if (_readonlyLogicEffective == null) {
        _readonlyLogicEffective = buildReadonlyLogicEffective();
    }
    return _readonlyLogicEffective;
}


public ILogicExpression getReadonlyLogic(){
    return _readonlyLogic;
}


public ILogicExpression getEntityReadonlyLogic(){
    return _entityReadonlyLogic;
}


public Optional<LookupDescriptor> getLookupDescriptor(){
    return getLookupDescriptorProvider().provide();
}


public DocumentFieldDescriptor getOrBuild(){
    if (_fieldBuilt == null) {
        _fieldBuilt = new DocumentFieldDescriptor(this);
    }
    return _fieldBuilt;
}


public ILogicExpression getDisplayLogic(){
    return displayLogic;
}


public ILogicExpression getMandatoryLogicEffective(){
    if (_mandatoryLogicEffective == null) {
        _mandatoryLogicEffective = buildMandatoryLogicEffective();
    }
    return _mandatoryLogicEffective;
}


public List<IDocumentFieldCallout> getCallouts(){
    return callouts;
}


public WidgetSize getWidgetSize(){
    return _widgetSize;
}


public T getDataBindingNotNull(Class<T> bindingClass){
    @SuppressWarnings("unchecked")
    final T dataBindingCasted = (T) dataBinding.orElseThrow(() -> new IllegalStateException("No databinding defined for " + this));
    return dataBindingCasted;
}


public Optional<IDocumentFieldValueProvider> getVirtualFieldValueProvider(){
    return virtualFieldValueProvider;
}


public String getFieldName(){
    return fieldName;
}


public int getFieldMaxLength(){
    return _fieldMaxLength;
}


public Optional<IExpression<?>> getDefaultValueExpression(){
    return defaultValueExpression;
}


public ILogicExpression getMandatoryLogic(){
    return mandatoryLogic;
}


public ITranslatableString getDescription(){
    if (description == null) {
        return TranslatableStrings.empty();
    }
    return description;
}


public Optional<String> getLookupTableName(){
    return getLookupDescriptorProvider().getTableName();
}


public Optional<DocumentFieldDataBindingDescriptor> getDataBinding(){
    return _dataBinding;
}


public DetailId getDetailId(){
    return _detailId;
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


public DocumentFieldDependencyMap getDependencies(){
    return dependencies;
}


public BarcodeScannerType getBarcodeScannerType(){
    return _barcodeScannerType;
}


public DocumentFieldDefaultFilterDescriptor getDefaultFilterInfo(){
    return defaultFilterInfo;
}


public Optional<LookupDescriptor> getLookupDescriptorForFiltering(){
    return lookupDescriptorProvider.provideForFilter();
}


}
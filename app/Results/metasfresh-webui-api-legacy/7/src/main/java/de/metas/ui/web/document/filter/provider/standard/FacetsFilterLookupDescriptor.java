package de.metas.ui.web.document.filter.provider.standard;
 import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.annotation.Nullable;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.DisplayType;
import java.util.Objects;
import com.google.common.collect.ImmutableList;
import de.metas.i18n.IMsgBL;
import de.metas.i18n.TranslatableStrings;
import de.metas.ui.web.view.DefaultView;
import de.metas.ui.web.view.IViewDataRepository;
import de.metas.ui.web.view.IViewsRepository;
import de.metas.ui.web.view.ViewEvaluationCtx;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.LookupValue.StringLookupValue;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.datatypes.Values;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.LookupDescriptor;
import de.metas.ui.web.window.descriptor.SimpleLookupDescriptorTemplate;
import de.metas.ui.web.window.model.lookup.LookupDataSourceContext;
import de.metas.util.Check;
import de.metas.util.Services;
import de.metas.util.StringUtils;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
public class FacetsFilterLookupDescriptor extends SimpleLookupDescriptorTemplate{

 private  IMsgBL msgBL;

 private  IViewsRepository viewsRepository;

@Getter
 private  String filterId;

@Getter
 private  String fieldName;

 private  DocumentFieldWidgetType fieldWidgetType;

@Getter
 private  boolean numericKey;

 private  int maxFacetsToFetch;

 private  LookupDescriptor fieldLookupDescriptor;


@Override
public LookupValuesList retrieveEntities(LookupDataSourceContext evalCtx){
    final DefaultView view = getView(evalCtx);
    return view.getFacetFiltersCacheMap().computeIfAbsent(filterId, () -> createFacetFilterViewCache(view)).getAvailableValues();
}


@Override
public LookupValue retrieveLookupValueById(LookupDataSourceContext evalCtx){
    return fieldLookupDescriptor.getLookupDataSourceFetcher().retrieveLookupValueById(evalCtx);
}


public LookupValue convertRawFieldValueToLookupValue(Object fieldValue){
    if (fieldValue == null) {
        return null;
    } else if (fieldValue instanceof LookupValue) {
        return (LookupValue) fieldValue;
    } else if (fieldValue instanceof LocalDate) {
        final LocalDate date = (LocalDate) fieldValue;
        return StringLookupValue.of(Values.localDateToJson(date), TranslatableStrings.date(date));
    } else if (fieldValue instanceof Boolean) {
        final boolean booleanValue = StringUtils.toBoolean(fieldValue);
        return StringLookupValue.of(DisplayType.toBooleanString(booleanValue), msgBL.getTranslatableMsgText(booleanValue));
    } else {
        throw new AdempiereException("Value not supported: " + fieldValue + " (" + fieldValue.getClass() + ")").appendParametersToMessage().setParameter("fieldName", fieldName);
    }
}


@Override
public Optional<String> getLookupTableName(){
    return fieldLookupDescriptor.getLookupDataSourceFetcher().getLookupTableName();
}


@Override
public Set<String> getDependsOnFieldNames(){
    return fieldLookupDescriptor.getDependsOnFieldNames();
}


public FacetFilterViewCache createFacetFilterViewCache(DefaultView view){
    final IViewDataRepository viewDataRepository = view.getViewDataRepository();
    final ViewEvaluationCtx viewEvalCtx = view.getViewEvaluationCtx();
    final String selectionId = view.getDefaultSelectionBeforeFacetsFiltering().getSelectionId();
    List<Object> rawValues = viewDataRepository.retrieveFieldValues(viewEvalCtx, selectionId, fieldName, maxFacetsToFetch);
    boolean valuesAreOrdered = false;
    if (fieldWidgetType.isDateOrTime() || fieldWidgetType.isNumeric()) {
        // in case of date/time/numeric fields we shall order them by their value
        // and not alphabetically by their string representation
        rawValues = rawValues.stream().sorted().collect(ImmutableList.toImmutableList());
        valuesAreOrdered = true;
    }
    final LookupValuesList lookupValues = rawValues.stream().map(this::convertRawFieldValueToLookupValue).filter(Objects::nonNull).distinct().collect(LookupValuesList.collect()).ordered(valuesAreOrdered);
    return FacetFilterViewCache.builder().filterId(filterId).availableValues(lookupValues).build();
}


public DefaultView getView(LookupDataSourceContext evalCtx){
    final ViewId viewId = evalCtx.getViewId();
    return DefaultView.cast(viewsRepository.getView(viewId));
}


@Override
public LookupDataSourceContext.Builder newContextForFetchingById(Object id){
    final LookupDataSourceContext.Builder builder = fieldLookupDescriptor != null ? fieldLookupDescriptor.getLookupDataSourceFetcher().newContextForFetchingById(id) : LookupDataSourceContext.builderWithoutTableName().putFilterById(id);
    return builder.requiresParameter(LookupDataSourceContext.PARAM_ViewId).requiresParameter(LookupDataSourceContext.PARAM_ViewSize);
}


@Override
public LookupDataSourceContext.Builder newContextForFetchingList(){
    final LookupDataSourceContext.Builder builder = fieldLookupDescriptor != null ? fieldLookupDescriptor.getLookupDataSourceFetcher().newContextForFetchingList() : LookupDataSourceContext.builderWithoutTableName();
    return builder.requiresParameter(LookupDataSourceContext.PARAM_ViewId).requiresParameter(LookupDataSourceContext.PARAM_ViewSize);
}


}
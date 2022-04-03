package de.metas.ui.web.process;
 import java.util.List;
import java.util.Objects;
import javax.annotation.Nullable;
import org.adempiere.ad.dao.IQueryFilter;
import org.adempiere.ad.dao.impl.TypedSqlQueryFilter;
import org.adempiere.ad.element.api.AdTabId;
import org.adempiere.ad.element.api.AdWindowId;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.util.lang.impl.TableRecordReference;
import org.slf4j.Logger;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import de.metas.logging.LogManager;
import de.metas.process.IProcessPreconditionsContext;
import de.metas.process.RelatedProcessDescriptor;
import de.metas.process.RelatedProcessDescriptor.DisplayPlace;
import de.metas.process.SelectionSize;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.view.ViewProfileId;
import de.metas.ui.web.view.ViewRowIdsSelection;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.model.sql.SqlOptions;
import de.metas.util.Functions;
import de.metas.util.Functions.MemoizingFunction;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Value;
@Value
public class ViewAsPreconditionsContext implements WebuiPreconditionsContext{

 private  Logger logger;

 private  IView view;

 private  ViewProfileId viewProfileId;

 private  String tableName;

 private  AdWindowId adWindowId;

 private  ViewRowIdsSelection viewRowIdsSelection;

 private  ViewRowIdsSelection parentViewRowIdsSelection;

 private  ViewRowIdsSelection childViewRowIdsSelection;

 private  DisplayPlace displayPlace;

@Getter(AccessLevel.NONE)
 private  MemoizingFunction<Class<?>,SelectedModelsList> _selectedModelsSupplier;

 private  SelectedModelsList EMPTY;

 private  ImmutableList<?> models;

 private  Class<?> modelClass;


public ViewAsPreconditionsContext castOrNull(IProcessPreconditionsContext context){
    if (context instanceof ViewAsPreconditionsContext) {
        return (ViewAsPreconditionsContext) context;
    } else {
        return null;
    }
}


public List<T> getModels(Class<T> modelClass){
    // If loaded models list is empty, we can return an empty list directly
    if (models.isEmpty()) {
        return ImmutableList.of();
    }
    // If loaded models have the same model class as the requested one
    // we can simple cast & return them
    if (Objects.equals(modelClass, this.modelClass)) {
        @SuppressWarnings("unchecked")
        final List<T> modelsCasted = (List<T>) models;
        return modelsCasted;
    } else // If not the same class, we have to wrap them fist.
    {
        return InterfaceWrapperHelper.wrapToImmutableList(models, modelClass);
    }
}


@Override
public SelectionSize getSelectionSize(){
    return getSelectedRowIds().toSelectionSize();
}


@Override
public List<RelatedProcessDescriptor> getAdditionalRelatedProcessDescriptors(){
    return view.getAdditionalRelatedProcessDescriptors();
}


@Override
public int getSingleSelectedRecordId(){
    final DocumentId rowId = getSelectedRowIds().getSingleDocumentId();
    final TableRecordReference recordRef = view.getTableRecordReferenceOrNull(rowId);
    if (recordRef == null) {
        throw new AdempiereException("Cannot extract Record_ID from single selected rowId: " + rowId);
    }
    return recordRef.getRecord_ID();
}


public SelectedModelsList retrieveSelectedModels(Class<?> modelClass){
    final List<?> models = view.retrieveModelsByIds(getSelectedRowIds(), modelClass);
    return SelectedModelsList.of(models, modelClass);
}


public DocumentIdsSelection getSelectedRowIds(){
    return viewRowIdsSelection.getRowIds();
}


@Override
public T getSelectedModel(Class<T> modelClass){
    final List<T> models = getSelectedModels(modelClass);
    if (models.isEmpty()) {
        return null;
    } else {
        if (models.size() > 1) {
            logger.warn("More then one selected model found for view but only one was expected: {}", view);
        }
        return models.get(0);
    }
}


@Override
public AdTabId getAdTabId(){
    return null;
}


@Override
public List<T> getSelectedModels(Class<T> modelClass){
    return _selectedModelsSupplier.apply(modelClass).getModels(modelClass);
}


public ViewAsPreconditionsContext cast(IProcessPreconditionsContext context){
    return (ViewAsPreconditionsContext) context;
}


@Override
public IQueryFilter<T> getQueryFilter(Class<T> recordClass){
    final String tableName = InterfaceWrapperHelper.getTableName(recordClass);
    final String sqlWhereClause = view.getSqlWhereClause(getSelectedRowIds(), SqlOptions.usingTableName(tableName));
    return TypedSqlQueryFilter.of(sqlWhereClause);
}


@Override
public boolean isMoreThanOneSelected(){
    return getSelectedRowIds().isMoreThanOneDocumentId();
}


public SelectedModelsList of(List<?> models,Class<?> modelClass){
    if (models == null || models.isEmpty()) {
        return EMPTY;
    }
    return new SelectedModelsList(models, modelClass);
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).omitNullValues().add("modelClass", modelClass).add("models", models).toString();
}


public T getView(Class<T> viewType){
    @SuppressWarnings("unchecked")
    final T viewCasted = (T) view;
    return viewCasted;
}


@Override
public boolean isNoSelection(){
    return getSelectedRowIds().isEmpty() && !getSelectedRowIds().isAll();
}


}
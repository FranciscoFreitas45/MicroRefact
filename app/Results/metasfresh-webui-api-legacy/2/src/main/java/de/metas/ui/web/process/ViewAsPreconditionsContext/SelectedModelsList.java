package de.metas.ui.web.process.ViewAsPreconditionsContext;
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
public class SelectedModelsList {

 private  SelectedModelsList EMPTY;

 private  ImmutableList<?> models;

 private  Class<?> modelClass;


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


}
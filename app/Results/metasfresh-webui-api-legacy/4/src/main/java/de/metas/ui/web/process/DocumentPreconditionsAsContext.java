package de.metas.ui.web.process;
 import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import org.adempiere.ad.dao.IQueryFilter;
import org.adempiere.ad.dao.impl.EqualsQueryFilter;
import org.adempiere.ad.element.api.AdTabId;
import org.adempiere.ad.element.api.AdWindowId;
import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.util.lang.impl.TableRecordReference;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import de.metas.process.RelatedProcessDescriptor.DisplayPlace;
import de.metas.process.SelectionSize;
import de.metas.ui.web.window.descriptor.DetailId;
import de.metas.ui.web.window.model.Document;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
public class DocumentPreconditionsAsContext implements WebuiPreconditionsContext{

 private  Document document;

@Getter
 private  String tableName;

@Getter
 private  AdTabId adTabId;

@Getter
 private  ImmutableSet<TableRecordReference> selectedIncludedRecords;

@Getter
 private  DisplayPlace displayPlace;


@Override
public SelectionSize getSelectionSize(){
    return SelectionSize.ofSize(1);
}


@Override
public IQueryFilter<T> getQueryFilter(Class<T> recordClass){
    final String keyColumnName = InterfaceWrapperHelper.getKeyColumnName(tableName);
    return EqualsQueryFilter.of(keyColumnName, getSingleSelectedRecordId());
}


@Override
public int getSingleSelectedRecordId(){
    return document.getDocumentIdAsInt();
}


@Override
public T getSelectedModel(Class<T> modelClass){
    return InterfaceWrapperHelper.create(document, modelClass);
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).omitNullValues().add("documentPath", document.getDocumentPath()).add("tableName", tableName).add("adTabId", adTabId).add("selectedIncludedRecords", selectedIncludedRecords).toString();
}


@Override
public AdWindowId getAdWindowId(){
    return document.getDocumentPath().getAdWindowIdOrNull();
}


@Override
public List<T> getSelectedModels(Class<T> modelClass){
    final T model = getSelectedModel(modelClass);
    return ImmutableList.of(model);
}


}
package de.metas.ui.web.window.model;
 import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.adempiere.ad.expression.api.LogicExpressionResult;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import de.metas.ui.web.window.WindowConstants;
import de.metas.ui.web.window.datatypes.DataTypes;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.descriptor.DetailId;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.model.IDocumentChangesCollector.ReasonSupplier;
import de.metas.util.Check;
public class DocumentChanges {

 private  DocumentPath documentPath;

 private  boolean primaryChange;

 private  Map<String,DocumentFieldChange> fieldChangesByName;

 private  DocumentValidStatus documentValidStatus;

 private  DocumentSaveStatus documentSaveStatus;

 private  Boolean deleted;

 private  Map<DetailId,IncludedDetailInfo> includedDetailInfos;

 private  DetailId detailId;

 private  boolean stale;

 private  LogicExpressionResult allowNew;

 private  LogicExpressionResult allowDelete;


public void collectFrom(IncludedDetailInfo from){
    if (from.stale) {
        stale = from.stale;
    }
    if (from.allowNew != null) {
        allowNew = from.allowNew;
    }
    if (from.allowDelete != null) {
        allowDelete = from.allowDelete;
    }
}


public boolean isPrimaryChange(){
    return primaryChange;
}


public Set<String> getFieldNames(){
    return ImmutableSet.copyOf(fieldChangesByName.keySet());
}


public void collectDocumentValidStatusChanged(DocumentValidStatus documentValidStatus){
    this.documentValidStatus = documentValidStatus;
}


public void collectDeleted(){
    deleted = Boolean.TRUE;
}


public LogicExpressionResult getAllowDelete(){
    return allowDelete;
}


public void collectDisplayedChanged(IDocumentFieldView documentField,ReasonSupplier reason){
    fieldChangesOf(documentField).setDisplayed(documentField.getDisplayed(), reason);
}


public boolean isDeleted(){
    return deleted != null && deleted;
}


public DocumentSaveStatus getDocumentSaveStatus(){
    return documentSaveStatus;
}


public List<IncludedDetailInfo> getIncludedDetailInfos(){
    return ImmutableList.copyOf(includedDetailInfos.values());
}


public void collectEvent(IDocumentFieldChangedEvent event){
    final boolean init_isKey = false;
    final boolean init_publicField = true;
    final boolean init_advancedField = false;
    final DocumentFieldWidgetType init_widgetType = event.getWidgetType();
    fieldChangesOf(event.getFieldName(), init_isKey, init_publicField, init_advancedField, init_widgetType).mergeFrom(event);
}


public DetailId getDetailId(){
    return detailId;
}


public void collectDocumentSaveStatusChanged(DocumentSaveStatus documentSaveStatus){
    this.documentSaveStatus = documentSaveStatus;
}


public IncludedDetailInfo setStale(){
    stale = true;
    return this;
}


public LogicExpressionResult getAllowNew(){
    return allowNew;
}


public void collectMandatoryChanged(IDocumentFieldView documentField,ReasonSupplier reason){
    fieldChangesOf(documentField).setMandatory(documentField.getMandatory(), reason);
}


public IncludedDetailInfo setAllowDelete(LogicExpressionResult allowDelete){
    this.allowDelete = allowDelete;
    return this;
}


public boolean isEmpty(){
    return fieldChangesByName.isEmpty() && documentValidStatus == null && documentSaveStatus == null && deleted == null && includedDetailInfos.isEmpty();
}


public List<DocumentFieldChange> getFieldChangesList(){
    return ImmutableList.copyOf(fieldChangesByName.values());
}


public void setPrimaryChange(){
    primaryChange = true;
}


public void collectValidStatusChanged(IDocumentFieldView documentField){
    fieldChangesOf(documentField).setValidStatus(documentField.getValidStatus());
}


public DocumentValidStatus getDocumentValidStatus(){
    return documentValidStatus;
}


public void collectReadonlyChanged(IDocumentFieldView documentField,ReasonSupplier reason){
    fieldChangesOf(documentField).setReadonly(documentField.getReadonly(), reason);
}


public void collectLookupValuesStaled(IDocumentFieldView documentField,ReasonSupplier reason){
    fieldChangesOf(documentField).setLookupValuesStale(true, reason);
}


public DocumentFieldChange fieldChangesOf(String fieldName,boolean key,boolean publicField,boolean advancedField,DocumentFieldWidgetType widgetType){
    return fieldChangesByName.computeIfAbsent(fieldName, (newFieldName) -> DocumentFieldChange.of(newFieldName, key, publicField, advancedField, widgetType));
}


public void collectValueChanged(IDocumentFieldView documentField,ReasonSupplier reason){
    fieldChangesOf(documentField).setValue(documentField.getValue(), reason);
}


public void collectFieldWarning(IDocumentFieldView documentField,DocumentFieldWarning fieldWarning){
    fieldChangesOf(documentField).setFieldWarning(fieldWarning);
}


public DocumentPath getDocumentPath(){
    return documentPath;
}


public IncludedDetailInfo includedDetailInfo(DetailId detailId){
    Check.assumeNotNull(detailId, "Parameter detailId is not null");
    return includedDetailInfos.computeIfAbsent(detailId, IncludedDetailInfo::new);
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).omitNullValues().add("documentPath", documentPath).add("fields", fieldChangesByName.isEmpty() ? null : fieldChangesByName).add("includedDetailInfos", includedDetailInfos.isEmpty() ? null : includedDetailInfos).toString();
}


public Optional<IncludedDetailInfo> includedDetailInfoIfExists(DetailId detailId){
    Check.assumeNotNull(detailId, "Parameter detailId is not null");
    return Optional.ofNullable(includedDetailInfos.get(detailId));
}


public boolean isStale(){
    return stale;
}


public IncludedDetailInfo setAllowNew(LogicExpressionResult allowNew){
    this.allowNew = allowNew;
    return this;
}


}
package de.metas.ui.web.window.descriptor.factory;
 import org.adempiere.util.lang.impl.TableRecordReference;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.descriptor.DetailId;
import de.metas.ui.web.window.descriptor.DocumentDescriptor;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import de.metas.ui.web.window.exceptions.DocumentLayoutBuildException;
import lombok.NonNull;
import javax.annotation.Nullable;
public interface DocumentDescriptorFactory {


public DocumentEntityDescriptor getDocumentEntityDescriptor(DocumentPath documentPath){
    final DocumentEntityDescriptor rootEntityDescriptor = getDocumentEntityDescriptor(documentPath.getWindowId());
    if (documentPath.isRootDocument()) {
        return rootEntityDescriptor;
    } else {
        return rootEntityDescriptor.getIncludedEntityByDetailId(documentPath.getDetailId());
    }
}
;

public String getTableNameOrNull(int AD_Window_ID,DetailId detailId){
    final DocumentEntityDescriptor descriptor = getDocumentEntityDescriptor(AD_Window_ID);
    if (detailId == null) {
        return descriptor.getTableName();
    } else {
        return descriptor.getIncludedEntityByDetailId(detailId).getTableName();
    }
}
;

public boolean isWindowIdSupported(WindowId windowId)
;

public TableRecordReference getTableRecordReference(DocumentPath documentPath){
    final DocumentEntityDescriptor rootEntityDescriptor = getDocumentEntityDescriptor(documentPath.getWindowId());
    if (documentPath.isRootDocument()) {
        final String tableName = rootEntityDescriptor.getTableName();
        final int recordId = documentPath.getDocumentId().toInt();
        return TableRecordReference.of(tableName, recordId);
    }
    final DocumentEntityDescriptor includedEntityDescriptor = rootEntityDescriptor.getIncludedEntityByDetailId(documentPath.getDetailId());
    final String tableName = includedEntityDescriptor.getTableName();
    final int recordId = documentPath.getSingleRowId().toInt();
    return TableRecordReference.of(tableName, recordId);
}
;

public DocumentDescriptor getDocumentDescriptor(WindowId windowId)
;

public void invalidateForWindow(WindowId windowId)
;

}
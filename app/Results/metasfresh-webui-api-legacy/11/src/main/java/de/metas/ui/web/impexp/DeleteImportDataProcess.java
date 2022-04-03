package de.metas.ui.web.impexp;
 import org.adempiere.exceptions.AdempiereException;
import de.metas.impexp.processing.IImportProcess;
import de.metas.impexp.processing.IImportProcessFactory;
import de.metas.impexp.processing.ImportDataDeleteMode;
import de.metas.impexp.processing.ImportDataDeleteRequest;
import de.metas.process.IProcessPrecondition;
import de.metas.process.Param;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.process.adprocess.ViewBasedProcessTemplate;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.model.sql.SqlOptions;
import de.metas.util.Services;
import lombok.Getter;
import lombok.NonNull;
public class DeleteImportDataProcess extends ViewBasedProcessTemplateimplements IProcessPrecondition{

 private  IImportProcessFactory importProcessFactory;

@Param(parameterName = "ImportDeleteMode", mandatory = true)
@Getter
 private  ImportDataDeleteMode deleteMode;


public String getSelectionSqlWhereClause(){
    final DocumentIdsSelection rowIds = getSelectedRowIds();
    if (rowIds.isEmpty()) {
        throw new AdempiereException("@NoSelection@");
    }
    return getViewSqlWhereClause(rowIds);
}


@Override
public String doIt(){
    final String importTableName = getTableName();
    final IImportProcess<Object> importProcess = importProcessFactory.newImportProcessForTableName(importTableName);
    importProcess.setCtx(getCtx());
    importProcess.setParameters(getParameterAsIParams());
    importProcess.setLoggable(this);
    final ImportDataDeleteMode deleteMode = getDeleteMode();
    final int deletedCount = importProcess.deleteImportRecords(ImportDataDeleteRequest.builder().mode(deleteMode).viewSqlWhereClause(getViewSqlWhereClause(DocumentIdsSelection.ALL)).selectionSqlWhereClause(ImportDataDeleteMode.ONLY_SELECTED.equals(deleteMode) ? getSelectionSqlWhereClause() : null).build());
    return "@Deleted@ " + deletedCount;
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    if (getView().size() <= 0) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("view is empty");
    }
    return ProcessPreconditionsResolution.accept();
}


public String getViewSqlWhereClause(DocumentIdsSelection rowIds){
    final String importTableName = getTableName();
    return getView().getSqlWhereClause(rowIds, SqlOptions.usingTableName(importTableName));
}


@Override
public void postProcess(boolean success){
    invalidateView();
}


}
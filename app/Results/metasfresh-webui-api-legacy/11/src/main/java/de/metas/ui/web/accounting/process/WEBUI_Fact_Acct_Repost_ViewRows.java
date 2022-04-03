package de.metas.ui.web.accounting.process;
 import java.util.Set;
import org.adempiere.ad.table.api.IADTableDAO;
import org.adempiere.service.ClientId;
import org.compiere.model.I_Fact_Acct;
import com.google.common.collect.ImmutableSet;
import de.metas.process.IProcessPrecondition;
import de.metas.process.Param;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.process.RunOutOfTrx;
import de.metas.ui.web.accounting.process.FactAcctRepostCommand.DocumentToRepost;
import de.metas.ui.web.process.adprocess.ViewBasedProcessTemplate;
import de.metas.ui.web.view.IViewRow;
import de.metas.util.Services;
public class WEBUI_Fact_Acct_Repost_ViewRows extends ViewBasedProcessTemplateimplements IProcessPrecondition{

 private  IADTableDAO adTablesRepo;

 public  String TABLENAME_RV_UnPosted;

@Param(parameterName = "IsEnforcePosting", mandatory = true)
 private  boolean forcePosting;


public Set<DocumentToRepost> getDocumentsToRepost(){
    return getView().streamByIds(getSelectedRowIds()).map(this::extractDocumentToRepost).distinct().collect(ImmutableSet.toImmutableSet());
}


public DocumentToRepost extractDocumentToRepostFromRegularRow(IViewRow row){
    final int adTableId = adTablesRepo.retrieveTableId(getTableName());
    final int recordId = row.getId().toInt();
    final ClientId adClientId = ClientId.ofRepoId(row.getFieldValueAsInt(I_Fact_Acct.COLUMNNAME_AD_Client_ID, -1));
    return DocumentToRepost.builder().adTableId(adTableId).recordId(recordId).clientId(adClientId).build();
}


@Override
@RunOutOfTrx
public String doIt(){
    final Set<DocumentToRepost> documentsToRepost = getDocumentsToRepost();
    if (documentsToRepost.isEmpty()) {
        return MSG_OK;
    }
    FactAcctRepostCommand.builder().forcePosting(forcePosting).documentsToRepost(documentsToRepost).build().execute();
    return MSG_OK;
}


public DocumentToRepost extractDocumentToRepost(IViewRow row){
    if (I_Fact_Acct.Table_Name.equals(getTableName()) || TABLENAME_RV_UnPosted.equals(getTableName())) {
        return extractDocumentToRepostFromTableAndRecordIdRow(row);
    } else {
        return extractDocumentToRepostFromRegularRow(row);
    }
}


public DocumentToRepost extractDocumentToRepostFromTableAndRecordIdRow(IViewRow row){
    final int adTableId = row.getFieldValueAsInt(I_Fact_Acct.COLUMNNAME_AD_Table_ID, -1);
    final int recordId = row.getFieldValueAsInt(I_Fact_Acct.COLUMNNAME_Record_ID, -1);
    final ClientId adClientId = ClientId.ofRepoId(row.getFieldValueAsInt(I_Fact_Acct.COLUMNNAME_AD_Client_ID, -1));
    return DocumentToRepost.builder().adTableId(adTableId).recordId(recordId).clientId(adClientId).build();
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    if (getSelectedRowIds().isEmpty()) {
        return ProcessPreconditionsResolution.rejectBecauseNoSelection();
    }
    return ProcessPreconditionsResolution.accept();
}


@Override
public void postProcess(boolean success){
    getView().invalidateSelection();
}


}
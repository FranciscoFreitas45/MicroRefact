package de.metas.ui.web.accounting.process;
 import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.adempiere.ad.element.api.AdWindowId;
import org.adempiere.ad.table.api.IADTableDAO;
import org.adempiere.ad.trx.api.ITrx;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.DBException;
import org.adempiere.service.ClientId;
import org.compiere.SpringContextHolder;
import org.compiere.model.I_Fact_Acct;
import org.compiere.util.DB;
import de.metas.acct.api.IFactAcctDAO;
import de.metas.document.engine.DocStatus;
import de.metas.i18n.BooleanWithReason;
import de.metas.process.IProcessPrecondition;
import de.metas.process.IProcessPreconditionsContext;
import de.metas.process.JavaProcess;
import de.metas.process.Param;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.accounting.process.FactAcctRepostCommand.DocumentToRepost;
import de.metas.ui.web.window.WindowConstants;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.model.Document;
import de.metas.ui.web.window.model.DocumentCollection;
import de.metas.ui.web.window.model.IDocumentFieldView;
import de.metas.util.Services;
public class WEBUI_Fact_Acct_Repost_SingleDocument extends JavaProcessimplements IProcessPrecondition{

 private  IADTableDAO adTablesRepo;

 private  IFactAcctDAO factAcctsRepo;

@Param(parameterName = "IsEnforcePosting", mandatory = true)
 private  boolean forcePosting;

 private  DocumentCollection documentsCollection;


public DocStatus getDocStatusOrNull(Document document){
    final IDocumentFieldView docStatusField = document.getFieldViewOrNull(WindowConstants.FIELDNAME_DocStatus);
    if (docStatusField == null) {
        return null;
    }
    final String docStatusStr = docStatusField.getValueAs(String.class);
    return DocStatus.ofNullableCodeOrUnknown(docStatusStr);
}


public boolean isProcessed(Document document){
    final IDocumentFieldView processedField = document.getFieldViewOrNull(WindowConstants.FIELDNAME_Processed);
    if (processedField == null) {
        return false;
    }
    return processedField.getValueAsBoolean();
}


@Override
public String doIt(){
    final DocumentToRepost documentToRepost = getDocumentToRepost();
    FactAcctRepostCommand.builder().forcePosting(forcePosting).documentToRepost(documentToRepost).build().execute();
    return MSG_OK;
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(IProcessPreconditionsContext context){
    final String recordTableName = context.getTableName();
    if (I_Fact_Acct.Table_Name.equals(recordTableName) || WEBUI_Fact_Acct_Repost_ViewRows.TABLENAME_RV_UnPosted.contentEquals(recordTableName)) {
        return ProcessPreconditionsResolution.accept();
    } else {
        final AdWindowId adWindowId = context.getAdWindowId();
        final int recordId = context.getSingleSelectedRecordId();
        final DocumentPath documentPath = DocumentPath.rootDocumentPath(adWindowId, recordId);
        final Document document = documentsCollection.getDocumentReadonly(documentPath);
        final BooleanWithReason allowPosting = checkAllowReposting(document);
        return allowPosting.isTrue() ? ProcessPreconditionsResolution.accept() : ProcessPreconditionsResolution.reject(allowPosting.getReason()).toInternal();
    }
}


public DocumentToRepost getDocumentToRepost(){
    final String recordTableName = getTableName();
    if (I_Fact_Acct.Table_Name.equals(recordTableName)) {
        final int factAcctId = getRecord_ID();
        final I_Fact_Acct factAcctRecord = factAcctsRepo.getById(factAcctId);
        return DocumentToRepost.builder().adTableId(factAcctRecord.getAD_Table_ID()).recordId(factAcctRecord.getFact_Acct_ID()).clientId(ClientId.ofRepoId(factAcctRecord.getAD_Client_ID())).build();
    } else if (WEBUI_Fact_Acct_Repost_ViewRows.TABLENAME_RV_UnPosted.contentEquals(recordTableName)) {
        return getDocumentToRepost_From_RV_UnPosted();
    } else {
        final DocumentPath documentPath = DocumentPath.rootDocumentPath(getProcessInfo().getAdWindowId(), getRecord_ID());
        final Document document = documentsCollection.getDocumentReadonly(documentPath);
        return extractDocumentToRepostFromSingleDocumentOrNull(document);
    }
}


public DocumentToRepost getDocumentToRepost_From_RV_UnPosted(){
    final String sql = "SELECT AD_Client_ID, AD_Table_ID, Record_ID " + " FROM " + WEBUI_Fact_Acct_Repost_ViewRows.TABLENAME_RV_UnPosted + " WHERE " + getProcessInfo().getWhereClause();
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
        pstmt = DB.prepareStatement(sql, ITrx.TRXNAME_ThreadInherited);
        rs = pstmt.executeQuery();
        if (rs.next()) {
            ClientId clientId = ClientId.ofRepoId(rs.getInt("AD_Client_ID"));
            int adTableId = rs.getInt("AD_Table_ID");
            int recordId = rs.getInt("Record_ID");
            return DocumentToRepost.builder().adTableId(adTableId).recordId(recordId).clientId(clientId).build();
        } else {
            throw new AdempiereException("@NotFound@").setParameter("sql", sql);
        }
    } catch (final SQLException ex) {
        throw new DBException(ex, sql);
    } finally {
        DB.close(rs, pstmt);
    }
}


public DocumentToRepost extractDocumentToRepostFromSingleDocumentOrNull(Document document){
    final BooleanWithReason allowReposting = checkAllowReposting(document);
    if (allowReposting.isFalse()) {
        throw new AdempiereException(allowReposting.getReason());
    }
    final String tableName = document.getEntityDescriptor().getTableName();
    final int adTableId = adTablesRepo.retrieveTableId(tableName);
    final int recordId = document.getDocumentIdAsInt();
    final ClientId adClientId = document.getClientId();
    return DocumentToRepost.builder().adTableId(adTableId).recordId(recordId).clientId(adClientId).build();
}


public BooleanWithReason checkAllowReposting(Document document){
    if (!document.hasField(WindowConstants.FIELDNAME_Posted)) {
        return BooleanWithReason.falseBecause("document has no Posted field");
    }
    final DocStatus docStatus = getDocStatusOrNull(document);
    if (docStatus != null && !docStatus.isAccountable()) {
        return BooleanWithReason.falseBecause("DocStatus is not accountable");
    }
    if (!isProcessed(document)) {
        return BooleanWithReason.falseBecause("not processed");
    }
    return BooleanWithReason.TRUE;
}


}
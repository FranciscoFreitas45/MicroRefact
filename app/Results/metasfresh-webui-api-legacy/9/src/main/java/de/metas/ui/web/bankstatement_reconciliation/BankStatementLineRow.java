package de.metas.ui.web.bankstatement_reconciliation;
 import java.time.LocalDate;
import java.util.Set;
import javax.annotation.Nullable;
import de.metas.banking.BankStatementLineId;
import de.metas.currency.Amount;
import de.metas.ui.web.view.IViewRow;
import de.metas.ui.web.view.ViewRowFieldNameAndJsonValues;
import de.metas.ui.web.view.ViewRowFieldNameAndJsonValuesHolder;
import de.metas.ui.web.view.descriptor.annotation.ViewColumn;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.WidgetSize;
import de.metas.util.StringUtils;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
public class BankStatementLineRow implements IViewRow{

@ViewColumn(seqNo = 10, widgetType = DocumentFieldWidgetType.Integer, widgetSize = WidgetSize.Small, captionKey = "Line")
 private  int lineNo;

@ViewColumn(seqNo = 20, widgetType = DocumentFieldWidgetType.LocalDate, widgetSize = WidgetSize.Small, captionKey = "DateAcct")
 private  LocalDate dateAcct;

@ViewColumn(seqNo = 30, widgetType = DocumentFieldWidgetType.Amount, widgetSize = WidgetSize.Small, captionKey = "StmtAmt")
@Getter
 private  Amount statementLineAmt;

@ViewColumn(seqNo = 40, widgetType = DocumentFieldWidgetType.Text, widgetSize = WidgetSize.Small, captionKey = "C_Currency_ID")
 private  String currencyCode;

@ViewColumn(seqNo = 50, widgetType = DocumentFieldWidgetType.Text, widgetSize = WidgetSize.Large, captionKey = "Description")
 private  String description;

@Getter
 private  BankStatementLineId bankStatementLineId;

 private  DocumentId rowId;

@Getter
 private  boolean reconciled;

 private  ViewRowFieldNameAndJsonValuesHolder<BankStatementLineRow> values;


@Override
public ViewRowFieldNameAndJsonValues getFieldNameAndJsonValues(){
    return values.get(this);
}


@Override
public DocumentPath getDocumentPath(){
    return null;
}


@Override
public boolean isProcessed(){
    return isReconciled();
}


public BankStatementLineId convertDocumentIdToBankStatementLineId(DocumentId rowId){
    return rowId.toId(BankStatementLineId::ofRepoId);
}


@Override
public DocumentId getId(){
    return rowId;
}


public DocumentId convertBankStatementLineIdToDocumentId(BankStatementLineId bankStatementLineId){
    return DocumentId.of(bankStatementLineId);
}


@Override
public Set<String> getFieldNames(){
    return values.getFieldNames();
}


}
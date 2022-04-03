package de.metas.ui.web.bankstatement_reconciliation;
 import java.util.List;
import javax.annotation.Nullable;
import de.metas.i18n.TranslatableStrings;
import de.metas.process.RelatedProcessDescriptor;
import de.metas.ui.web.document.filter.provider.NullDocumentFilterDescriptorsProvider;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.view.IViewRow;
import de.metas.ui.web.view.IViewRowOverrides;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.template.AbstractCustomView;
import de.metas.ui.web.window.datatypes.DocumentId;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
public class BankStatementReconciliationView extends AbstractCustomView<BankStatementLineRow>implements IViewRowOverrides{

@Getter
 private  PaymentsToReconcileView paymentsToReconcileView;


@Override
public BankStatementLineRows getRowsData(){
    return BankStatementLineRows.cast(super.getRowsData());
}


public BankStatementReconciliationView cast(IView view){
    return (BankStatementReconciliationView) view;
}


@Override
public ViewId getIncludedViewId(IViewRow row_NOTUSED){
    return paymentsToReconcileView.getViewId();
}


@Override
public String getTableNameOrNull(DocumentId documentId){
    return null;
}


}
package de.metas.ui.web.bankstatement_reconciliation;
 import java.util.List;
import com.google.common.collect.ImmutableList;
import de.metas.i18n.TranslatableStrings;
import de.metas.process.RelatedProcessDescriptor;
import de.metas.ui.web.document.filter.provider.NullDocumentFilterDescriptorsProvider;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.template.AbstractCustomView;
import de.metas.ui.web.window.datatypes.DocumentId;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
public class PaymentsToReconcileView extends AbstractCustomView<PaymentToReconcileRow>{

@Getter
 private  ViewId bankStatementViewId;

 private  ImmutableList<RelatedProcessDescriptor> processes;


@Override
public PaymentToReconcileRows getRowsData(){
    return PaymentToReconcileRows.cast(super.getRowsData());
}


public PaymentsToReconcileView cast(IView view){
    return (PaymentsToReconcileView) view;
}


@Override
public ViewId getParentViewId(){
    return getBankStatementViewId();
}


@Override
public List<RelatedProcessDescriptor> getAdditionalRelatedProcessDescriptors(){
    return processes;
}


@Override
public String getTableNameOrNull(DocumentId documentId){
    return null;
}


}
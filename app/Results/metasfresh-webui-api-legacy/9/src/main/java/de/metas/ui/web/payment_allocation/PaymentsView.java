package de.metas.ui.web.payment_allocation;
 import java.util.List;
import javax.annotation.Nullable;
import com.google.common.collect.ImmutableList;
import de.metas.i18n.TranslatableStrings;
import de.metas.payment.PaymentId;
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
public class PaymentsView extends AbstractCustomView<PaymentRow>implements IViewRowOverrides{

 private  ImmutableList<RelatedProcessDescriptor> processes;

@Getter
 private  InvoicesView invoicesView;


@Override
public PaymentRows getRowsData(){
    return PaymentRows.cast(super.getRowsData());
}


public PaymentsView cast(IView view){
    return (PaymentsView) view;
}


@Override
public ViewId getIncludedViewId(IViewRow row){
    return invoicesView.getViewId();
}


@Override
public List<RelatedProcessDescriptor> getAdditionalRelatedProcessDescriptors(){
    return processes;
}


@Override
public String getTableNameOrNull(DocumentId documentId){
    return null;
}


public void addPayment(PaymentId paymentId){
    final PaymentRows paymentRows = getRowsData();
    paymentRows.addPayment(paymentId);
}


}
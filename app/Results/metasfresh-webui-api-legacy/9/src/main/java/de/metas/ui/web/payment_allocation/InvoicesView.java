package de.metas.ui.web.payment_allocation;
 import java.util.List;
import javax.annotation.Nullable;
import com.google.common.collect.ImmutableList;
import de.metas.i18n.TranslatableStrings;
import de.metas.invoice.InvoiceId;
import de.metas.process.RelatedProcessDescriptor;
import de.metas.ui.web.document.filter.provider.NullDocumentFilterDescriptorsProvider;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.template.AbstractCustomView;
import de.metas.ui.web.window.datatypes.DocumentId;
import lombok.Builder;
import lombok.NonNull;
public class InvoicesView extends AbstractCustomView<InvoiceRow>{

 private  ImmutableList<RelatedProcessDescriptor> processes;


@Override
public InvoiceRows getRowsData(){
    return InvoiceRows.cast(super.getRowsData());
}


public InvoicesView cast(IView view){
    return (InvoicesView) view;
}


@Override
public List<RelatedProcessDescriptor> getAdditionalRelatedProcessDescriptors(){
    return processes;
}


@Override
public String getTableNameOrNull(DocumentId documentId){
    return null;
}


public void addInvoice(InvoiceId invoiceId){
    final InvoiceRows invoiceRows = getRowsData();
    invoiceRows.addInvoice(invoiceId);
}


}
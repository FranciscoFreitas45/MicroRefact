package de.metas.ui.web.payment_allocation;
 import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import org.adempiere.exceptions.AdempiereException;
import com.google.common.collect.ImmutableList;
import de.metas.i18n.IMsgBL;
import de.metas.payment.PaymentId;
import de.metas.process.AdProcessId;
import de.metas.process.IADProcessDAO;
import de.metas.process.RelatedProcessDescriptor;
import de.metas.process.RelatedProcessDescriptor.DisplayPlace;
import de.metas.ui.web.payment_allocation.process.InvoicesView_AddAdditionalInvoice;
import de.metas.ui.web.payment_allocation.process.PaymentsView_AddAdditionalPayment;
import de.metas.ui.web.payment_allocation.process.PaymentsView_Allocate;
import de.metas.ui.web.payment_allocation.process.PaymentsView_AllocateAndDiscount;
import de.metas.ui.web.payment_allocation.process.PaymentsView_AllocateAndWriteOff;
import de.metas.ui.web.view.CreateViewRequest;
import de.metas.ui.web.view.DefaultViewsRepositoryStorage;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.view.IViewFactory;
import de.metas.ui.web.view.IViewsIndexStorage;
import de.metas.ui.web.view.IViewsRepository;
import de.metas.ui.web.view.ViewCloseAction;
import de.metas.ui.web.view.ViewFactory;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.ViewProfileId;
import de.metas.ui.web.view.descriptor.IncludedViewLayout;
import de.metas.ui.web.view.descriptor.ViewLayout;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.util.Services;
import lombok.NonNull;
@ViewFactory(windowId = PaymentsViewFactory.WINDOW_ID_String)
public class PaymentsViewFactory implements IViewFactory,IViewsIndexStorage{

 static  String WINDOW_ID_String;

 public  WindowId WINDOW_ID;

 private  IMsgBL msgBL;

 private  PaymentAndInvoiceRowsRepo rowsRepo;

 private  DefaultViewsRepositoryStorage views;


public List<RelatedProcessDescriptor> getPaymentRelatedProcessDescriptors(){
    return ImmutableList.of(createProcessDescriptor(10, PaymentsView_Allocate.class), createProcessDescriptor(20, PaymentsView_AllocateAndDiscount.class), createProcessDescriptor(30, PaymentsView_AllocateAndWriteOff.class), createProcessDescriptor(40, PaymentsView_AddAdditionalPayment.class));
}


public List<RelatedProcessDescriptor> getInvoiceRelatedProcessDescriptors(){
    return ImmutableList.of(createProcessDescriptor(10, InvoicesView_AddAdditionalInvoice.class));
}


@Override
public PaymentsView getByIdOrNull(ViewId viewId){
    return PaymentsView.cast(views.getByIdOrNull(viewId));
}


@Override
public void invalidateView(ViewId viewId){
    views.invalidateView(viewId);
}


@Override
public void setViewsRepository(IViewsRepository viewsRepository){
// nothing
}


@Override
public PaymentsView createView(CreateViewRequest request){
    final ViewId viewId = request.getViewId();
    viewId.assertWindowId(WINDOW_ID);
    final Set<PaymentId> paymentIds = PaymentId.fromIntSet(request.getFilterOnlyIds());
    if (paymentIds.isEmpty()) {
        throw new AdempiereException("@NoSelection@");
    }
    final PaymentAndInvoiceRows paymentAndInvoiceRows = rowsRepo.getByPaymentIds(paymentIds);
    return PaymentsView.builder().paymentViewId(viewId).rows(paymentAndInvoiceRows).paymentsProcesses(getPaymentRelatedProcessDescriptors()).invoicesProcesses(getInvoiceRelatedProcessDescriptors()).build();
}


@Override
public ViewLayout getViewLayout(WindowId windowId,JSONViewDataType viewDataType,ViewProfileId profileId){
    return ViewLayout.builder().setWindowId(WINDOW_ID).setCaption(msgBL.translatable("PaymentAllocation")).setAllowOpeningRowDetails(false).allowViewCloseAction(ViewCloseAction.DONE).setIncludedViewLayout(IncludedViewLayout.builder().openOnSelect(true).blurWhenOpen(false).build()).addElementsFromViewRowClass(PaymentRow.class, viewDataType).build();
}


@Override
public void put(IView view){
    views.put(view);
}


@Override
public Stream<IView> streamAllViews(){
    return views.streamAllViews();
}


public RelatedProcessDescriptor createProcessDescriptor(int sortNo,Class<?> processClass){
    final IADProcessDAO adProcessDAO = Services.get(IADProcessDAO.class);
    final AdProcessId processId = adProcessDAO.retrieveProcessIdByClass(processClass);
    if (processId == null) {
        throw new AdempiereException("No processId found for " + processClass);
    }
    return RelatedProcessDescriptor.builder().processId(processId).anyTable().anyWindow().displayPlace(DisplayPlace.ViewQuickActions).sortNo(sortNo).build();
}


@Override
public WindowId getWindowId(){
    return WINDOW_ID;
}


@Override
public void closeById(ViewId viewId,ViewCloseAction closeAction){
    views.closeById(viewId, closeAction);
}


}
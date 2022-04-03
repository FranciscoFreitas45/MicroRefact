package de.metas.ui.web.payment_allocation;
 import java.util.stream.Stream;
import de.metas.i18n.IMsgBL;
import de.metas.ui.web.view.CreateViewRequest;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.view.IViewFactory;
import de.metas.ui.web.view.IViewsIndexStorage;
import de.metas.ui.web.view.IViewsRepository;
import de.metas.ui.web.view.ViewCloseAction;
import de.metas.ui.web.view.ViewFactory;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.ViewProfileId;
import de.metas.ui.web.view.descriptor.ViewLayout;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.util.Services;
import lombok.NonNull;
@ViewFactory(windowId = InvoicesViewFactory.WINDOW_ID_String)
public class InvoicesViewFactory implements IViewFactory,IViewsIndexStorage{

 static  String WINDOW_ID_String;

 public  WindowId WINDOW_ID;

 private  IMsgBL msgBL;

 private  PaymentsViewFactory paymentsViewFactory;


@Override
public InvoicesView getByIdOrNull(ViewId invoicesViewId){
    final ViewId paymentsViewId = toPaymentsViewId(invoicesViewId);
    final PaymentsView paymentsView = paymentsViewFactory.getByIdOrNull(paymentsViewId);
    return paymentsView != null ? paymentsView.getInvoicesView() : null;
}


@Override
public void invalidateView(ViewId invoicesViewId){
    final InvoicesView invoicesView = getByIdOrNull(invoicesViewId);
    if (invoicesView != null) {
        invoicesView.invalidateAll();
    }
}


@Override
public void setViewsRepository(IViewsRepository viewsRepository){
// nothing
}


@Override
public InvoicesView createView(CreateViewRequest request){
    throw new UnsupportedOperationException();
}


@Override
public ViewLayout getViewLayout(WindowId windowId,JSONViewDataType viewDataType,ViewProfileId profileId){
    return ViewLayout.builder().setWindowId(WINDOW_ID).setCaption(msgBL.translatable("InvoicesToAllocate")).setAllowOpeningRowDetails(false).addElementsFromViewRowClass(InvoiceRow.class, viewDataType).build();
}


public ViewId toPaymentsViewId(ViewId invoicesViewId){
    return invoicesViewId.withWindowId(PaymentsViewFactory.WINDOW_ID);
}


@Override
public void put(IView view){
    throw new UnsupportedOperationException();
}


@Override
public Stream<IView> streamAllViews(){
    return paymentsViewFactory.streamAllViews().map(PaymentsView::cast).map(PaymentsView::getInvoicesView);
}


@Override
public WindowId getWindowId(){
    return WINDOW_ID;
}


@Override
public void closeById(ViewId viewId,ViewCloseAction closeAction){
}


}
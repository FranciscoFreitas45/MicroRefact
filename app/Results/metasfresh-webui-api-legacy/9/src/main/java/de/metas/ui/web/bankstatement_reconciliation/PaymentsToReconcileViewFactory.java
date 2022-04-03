package de.metas.ui.web.bankstatement_reconciliation;
 import java.util.stream.Stream;
import de.metas.i18n.TranslatableStrings;
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
import de.metas.util.Check;
import lombok.NonNull;
@ViewFactory(windowId = PaymentsToReconcileViewFactory.WINDOW_ID_String)
public class PaymentsToReconcileViewFactory implements IViewFactory,IViewsIndexStorage{

 static  String WINDOW_ID_String;

 public  WindowId WINDOW_ID;

 private  BankStatementReconciliationViewFactory banksStatementReconciliationViewFactory;


@Override
public PaymentsToReconcileView getByIdOrNull(ViewId paymentsToReconcileViewId){
    final ViewId bankStatementReconciliationViewId = toBankStatementReconciliationViewId(paymentsToReconcileViewId);
    final BankStatementReconciliationView bankStatementReconciliationView = banksStatementReconciliationViewFactory.getByIdOrNull(bankStatementReconciliationViewId);
    return bankStatementReconciliationView != null ? bankStatementReconciliationView.getPaymentsToReconcileView() : null;
}


@Override
public void invalidateView(ViewId paymentsToReconcileViewId){
    final PaymentsToReconcileView paymentsToReconcileView = getByIdOrNull(paymentsToReconcileViewId);
    if (paymentsToReconcileView != null) {
        paymentsToReconcileView.invalidateAll();
    }
}


@Override
public void setViewsRepository(IViewsRepository viewsRepository){
// nothing
}


public ViewId toBankStatementReconciliationViewId(ViewId paymentsToReconcileViewId){
    return paymentsToReconcileViewId.withWindowId(BankStatementReconciliationViewFactory.WINDOW_ID);
}


@Override
public IView createView(CreateViewRequest request){
    throw new UnsupportedOperationException();
}


@Override
public ViewLayout getViewLayout(WindowId windowId,JSONViewDataType viewDataType,ViewProfileId profileId){
    Check.assumeEquals(windowId, WINDOW_ID, "windowId");
    return ViewLayout.builder().setWindowId(WINDOW_ID).setCaption(TranslatableStrings.empty()).setAllowOpeningRowDetails(false).addElementsFromViewRowClass(PaymentToReconcileRow.class, viewDataType).build();
}


@Override
public void put(IView view){
    throw new UnsupportedOperationException();
}


@Override
public Stream<IView> streamAllViews(){
    return banksStatementReconciliationViewFactory.streamAllViews().map(BankStatementReconciliationView::cast).map(BankStatementReconciliationView::getPaymentsToReconcileView);
}


@Override
public WindowId getWindowId(){
    return WINDOW_ID;
}


@Override
public void closeById(ViewId viewId,ViewCloseAction closeAction){
// nothing
}


}
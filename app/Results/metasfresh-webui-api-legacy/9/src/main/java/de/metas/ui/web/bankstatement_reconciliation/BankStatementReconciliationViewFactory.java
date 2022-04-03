package de.metas.ui.web.bankstatement_reconciliation;
 import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import com.google.common.collect.ImmutableList;
import de.metas.i18n.IMsgBL;
import de.metas.process.IADProcessDAO;
import de.metas.process.RelatedProcessDescriptor;
import de.metas.process.RelatedProcessDescriptor.DisplayPlace;
import de.metas.ui.web.bankstatement_reconciliation.process.PaymentsToReconcileView_Reconcile;
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
import de.metas.util.Check;
import de.metas.util.Services;
import lombok.NonNull;
@ViewFactory(windowId = BankStatementReconciliationViewFactory.WINDOW_ID_String)
public class BankStatementReconciliationViewFactory implements IViewFactory,IViewsIndexStorage{

 static  String WINDOW_ID_String;

 public  WindowId WINDOW_ID;

 private  IMsgBL msgBL;

 private  IADProcessDAO adProcessDAO;

 private  BankStatementLineAndPaymentsToReconcileRepository rowsRepo;

 private  DefaultViewsRepositoryStorage views;


public List<RelatedProcessDescriptor> getPaymentToReconcilateProcesses(){
    return ImmutableList.of(createProcessDescriptor(PaymentsToReconcileView_Reconcile.class));
}


public BankStatementLineAndPaymentsRows retrieveRowsData(BanksStatementReconciliationViewCreateRequest request){
    final List<BankStatementLineRow> bankStatementLineRows = rowsRepo.getBankStatementLineRowsByIds(request.getBankStatementLineIds());
    final List<PaymentToReconcileRow> paymentToReconcileRows = rowsRepo.getPaymentToReconcileRowsByIds(request.getPaymentIds());
    return BankStatementLineAndPaymentsRows.builder().bankStatementLineRows(BankStatementLineRows.builder().repository(rowsRepo).rows(bankStatementLineRows).build()).paymentToReconcileRows(PaymentToReconcileRows.builder().repository(rowsRepo).rows(paymentToReconcileRows).build()).build();
}


@Override
public BankStatementReconciliationView getByIdOrNull(ViewId viewId){
    return BankStatementReconciliationView.cast(views.getByIdOrNull(viewId));
}


@Override
public void invalidateView(ViewId viewId){
    views.invalidateView(viewId);
}


@Override
public void setViewsRepository(IViewsRepository viewsRepository){
}


public BankStatementReconciliationView createView(BanksStatementReconciliationViewCreateRequest request){
    final BankStatementLineAndPaymentsRows rows = retrieveRowsData(request);
    final BankStatementReconciliationView view = BankStatementReconciliationView.builder().bankStatementViewId(ViewId.random(WINDOW_ID)).rows(rows).paymentToReconcilateProcesses(getPaymentToReconcilateProcesses()).build();
    put(view);
    return view;
}


@Override
public ViewLayout getViewLayout(WindowId windowId,JSONViewDataType viewDataType,ViewProfileId profileId){
    Check.assumeEquals(windowId, WINDOW_ID, "windowId");
    return ViewLayout.builder().setWindowId(WINDOW_ID).setCaption(msgBL.translatable("BankStatementReconciliation")).setAllowOpeningRowDetails(false).allowViewCloseAction(ViewCloseAction.DONE).setIncludedViewLayout(IncludedViewLayout.builder().openOnSelect(true).blurWhenOpen(false).build()).addElementsFromViewRowClass(BankStatementLineRow.class, viewDataType).build();
}


@Override
public void put(IView view){
    views.put(view);
}


@Override
public Stream<IView> streamAllViews(){
    return views.streamAllViews();
}


@Override
public WindowId getWindowId(){
    return WINDOW_ID;
}


@Override
public void closeById(ViewId viewId,ViewCloseAction closeAction){
    views.closeById(viewId, closeAction);
}


public RelatedProcessDescriptor createProcessDescriptor(Class<?> processClass){
    return RelatedProcessDescriptor.builder().processId(adProcessDAO.retrieveProcessIdByClass(processClass)).anyTable().anyWindow().displayPlace(DisplayPlace.ViewQuickActions).build();
}


}
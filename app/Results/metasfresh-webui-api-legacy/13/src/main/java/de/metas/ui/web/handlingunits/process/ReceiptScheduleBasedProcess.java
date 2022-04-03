package de.metas.ui.web.handlingunits.process;
 import java.util.Collection;
import org.adempiere.util.lang.impl.TableRecordReference;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.process.IProcessPrecondition;
import de.metas.process.JavaProcess;
import de.metas.ui.web.receiptSchedule.HUsToReceiveViewFactory;
public class ReceiptScheduleBasedProcess extends JavaProcessimplements IProcessPrecondition{


public void openHUsToReceive(Collection<I_M_HU> hus){
    getResult().setRecordsToOpen(TableRecordReference.ofCollection(hus), HUsToReceiveViewFactory.WINDOW_ID_STRING);
}


}
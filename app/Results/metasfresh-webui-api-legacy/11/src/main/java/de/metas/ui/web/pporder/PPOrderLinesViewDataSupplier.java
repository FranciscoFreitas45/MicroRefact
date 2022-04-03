package de.metas.ui.web.pporder;
 import javax.annotation.Nullable;
import org.adempiere.util.lang.ExtendedMemorizingSupplier;
import de.metas.handlingunits.reservation.HUReservationService;
import de.metas.material.planning.pporder.PPOrderId;
import de.metas.ui.web.view.ASIViewRowAttributesProvider;
import de.metas.ui.web.view.descriptor.SqlViewBinding;
import de.metas.ui.web.window.datatypes.WindowId;
import lombok.Builder;
import lombok.NonNull;
public class PPOrderLinesViewDataSupplier {

 private  ASIViewRowAttributesProvider asiAttributesProvider;

 private  ExtendedMemorizingSupplier<PPOrderLinesViewData> rowsSupplier;


public void invalidate(){
    rowsSupplier.forget();
    if (asiAttributesProvider != null) {
        asiAttributesProvider.invalidateAll();
    }
}


public PPOrderLinesViewData getData(){
    return rowsSupplier.get();
}


}
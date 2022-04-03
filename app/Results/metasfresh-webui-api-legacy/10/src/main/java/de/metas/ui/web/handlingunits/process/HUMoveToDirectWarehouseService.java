package de.metas.ui.web.handlingunits.process;
 import java.sql.Timestamp;
import java.util.Iterator;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.model.PlainContextAware;
import org.adempiere.util.lang.IAutoCloseable;
import org.compiere.model.I_M_Warehouse;
import org.compiere.util.Env;
import org.slf4j.Logger;
import com.google.common.collect.ImmutableSet;
import de.metas.handlingunits.HuId;
import de.metas.handlingunits.IHandlingUnitsBL;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.movement.api.IHUMovementBL;
import de.metas.handlingunits.movement.api.impl.HUMovementBuilder;
import de.metas.interfaces.I_M_Movement;
import de.metas.logging.LogManager;
import de.metas.ui.web.handlingunits.HUEditorView;
import de.metas.ui.web.view.event.ViewChangesCollector;
import de.metas.ui.web.window.model.DocumentCollection;
import de.metas.util.ILoggable;
import de.metas.util.Loggables;
import de.metas.util.Services;
import lombok.NonNull;
public class HUMoveToDirectWarehouseService {

 private  Logger logger;

 private  IHUMovementBL huMovementBL;

 private  DocumentCollection documentsCollection;

 private  Timestamp _movementDate;

 private  String _description;

 private  boolean _failOnFirstError;

 private  boolean _failIfNoHUs;

 private  ILoggable loggable;

 private  HUEditorView huView;

 private  I_M_Warehouse _targetWarehouse;


public void checkPreconditions(){
    // will fail if direct warehouse is not configured or found
    getTargetWarehouse();
}


public void move(Iterator<I_M_HU> hus){
    checkPreconditions();
    try (final IAutoCloseable c = ViewChangesCollector.currentOrNewThreadLocalCollector()) {
        // 
        // Move the HUs, one by one
        int countMoved = 0;
        while (hus.hasNext()) {
            final I_M_HU hu = hus.next();
            generateMovement(hu);
            countMoved++;
        }
        // Stop here if nothing moved
        if (countMoved <= 0) {
            if (isFailIfNoHUs()) {
                throw new AdempiereException("@NoSelection@");
            }
            return;
        }
        // Invalidate given view, if any
        if (huView != null) {
            huView.invalidateAll();
        }
    }
}


public HUMoveToDirectWarehouseService newInstance(){
    return new HUMoveToDirectWarehouseService();
}


public HUMoveToDirectWarehouseService setDescription(String description){
    _description = description;
    return this;
}


public boolean isFailIfNoHUs(){
    return _failIfNoHUs;
}


public String getDescription(){
    return _description;
}


public HUMoveToDirectWarehouseService setFailOnFirstError(boolean failOnFirstError){
    _failOnFirstError = failOnFirstError;
    return this;
}


public HUMoveToDirectWarehouseService setHUView(HUEditorView huView){
    this.huView = huView;
    return this;
}


public HUMoveToDirectWarehouseService setMovementDate(Timestamp movementDate){
    _movementDate = movementDate;
    return this;
}


public HUMoveToDirectWarehouseService setDocumentsCollection(DocumentCollection documentsCollection){
    this.documentsCollection = documentsCollection;
    return this;
}


public I_M_Warehouse getTargetWarehouse(){
    if (_targetWarehouse == null) {
        final boolean exceptionIfNull = true;
        _targetWarehouse = huMovementBL.getDirectMove_Warehouse(Env.getCtx(), exceptionIfNull);
    }
    return _targetWarehouse;
}


public void generateMovement(I_M_HU hu){
    final I_M_Warehouse targetWarehouse = getTargetWarehouse();
    try {
        // 
        // Move the HU
        final I_M_Movement movement = new HUMovementBuilder().setContextInitial(PlainContextAware.newWithThreadInheritedTrx()).setWarehouseFrom(IHandlingUnitsBL.extractWarehouse(hu)).setWarehouseTo(targetWarehouse).setMovementDate(getMovementDate()).setDescription(getDescription()).addHU(hu).createMovement();
        if (movement == null) {
            throw new AdempiereException("No Movement created");
        }
        // 
        // Notify listeners/handlers
        notifyHUMoved(hu);
        loggable.addLog("@Created@ @M_Movement_ID@: {}", movement.getDocumentNo());
    } catch (final Exception ex) {
        if (isFailOnFirstError()) {
            throw AdempiereException.wrapIfNeeded(ex).setParameter("HU", hu).markAsUserValidationError();
        }
        final String errmsg = "Error on " + hu.getValue() + ": " + ex.getLocalizedMessage();
        loggable.addLog(errmsg);
        logger.warn(errmsg, ex);
    }
}


public boolean isFailOnFirstError(){
    return _failOnFirstError;
}


public Timestamp getMovementDate(){
    return _movementDate;
}


public void notifyHUMoved(I_M_HU hu){
    final HuId huId = HuId.ofRepoId(hu.getM_HU_ID());
    // 
    // Invalidate all documents which are about this HU.
    if (documentsCollection != null) {
        try {
            documentsCollection.invalidateDocumentByRecordId(I_M_HU.Table_Name, huId.getRepoId());
        } catch (final Exception ex) {
            logger.warn("Failed invalidating documents for M_HU_ID={}. Ignored", huId, ex);
        }
    }
    // 
    // Remove this HU from the view
    // Don't invalidate. We will do it at the end of all processing.
    if (huView != null) {
        huView.removeHUIds(ImmutableSet.of(huId));
    }
}


public HUMoveToDirectWarehouseService setFailIfNoHUs(boolean failIfNoHUs){
    _failIfNoHUs = failIfNoHUs;
    return this;
}


public HUMoveToDirectWarehouseService setLoggable(ILoggable loggable){
    this.loggable = loggable;
    return this;
}


}
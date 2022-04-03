package de.metas.ui.web.material.cockpit.stockdetails;
 import java.util.Iterator;
import java.util.Map;
import java.util.stream.Stream;
import org.adempiere.util.lang.impl.TableRecordReferenceSet;
import com.google.common.collect.ImmutableMap;
import de.metas.handlingunits.stock.HUStockInfo;
import de.metas.ui.web.view.template.IRowsData;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import lombok.NonNull;
public class StockDetailsRowsData implements IRowsData<StockDetailsRow>{

 private  Map<DocumentId,StockDetailsRow> document2StockDetailsRow;


@Override
public DocumentIdsSelection getDocumentIdsToInvalidate(TableRecordReferenceSet recordRefs){
    return DocumentIdsSelection.EMPTY;
}


@Override
public void invalidateAll(){
}


public StockDetailsRowsData of(Stream<HUStockInfo> huStockInfos){
    return new StockDetailsRowsData(huStockInfos);
}


@Override
public Map<DocumentId,StockDetailsRow> getDocumentId2TopLevelRows(){
    return document2StockDetailsRow;
}


}
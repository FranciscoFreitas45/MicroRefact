package de.metas.ui.web.pickingV2.productsToPick.process;
 import java.util.List;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.common.collect.ImmutableList;
import de.metas.handlingunits.picking.PickingCandidate;
import de.metas.process.IProcessPrecondition;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.pickingV2.PickingConstantsV2;
import de.metas.ui.web.pickingV2.config.PickingConfigRepositoryV2;
import de.metas.ui.web.pickingV2.config.PickingConfigV2;
import de.metas.ui.web.pickingV2.productsToPick.ProductsToPickView;
import de.metas.ui.web.pickingV2.productsToPick.rows.ProductsToPickRow;
import de.metas.ui.web.process.adprocess.ViewBasedProcessTemplate;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import lombok.NonNull;
public class ProductsToPickViewBasedProcess extends ViewBasedProcessTemplateimplements IProcessPrecondition{

@Autowired
 private  PickingConfigRepositoryV2 pickingConfigRepo;

 private  PickingConfigV2 _pickingConfig;


public List<ProductsToPickRow> getSelectedRows(){
    final DocumentIdsSelection rowIds = getSelectedRowIds();
    return getView().streamByIds(rowIds).collect(ImmutableList.toImmutableList());
}


public PickingConfigV2 getPickingConfig(){
    PickingConfigV2 pickingConfig = _pickingConfig;
    if (pickingConfig == null) {
        pickingConfig = _pickingConfig = pickingConfigRepo.getPickingConfig();
    }
    return pickingConfig;
}


public void updateViewRowFromPickingCandidate(DocumentId rowId,PickingCandidate pickingCandidate){
    getView().updateViewRowFromPickingCandidate(rowId, pickingCandidate);
}


public List<ProductsToPickRow> getAllRows(){
    return streamAllRows().collect(ImmutableList.toImmutableList());
}


public Stream<ProductsToPickRow> streamAllRows(){
    return getView().streamByIds(DocumentIdsSelection.ALL);
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable()


public boolean isReviewProfile(){
    return PickingConstantsV2.PROFILE_ID_ProductsToPickView_Review.equals(getViewProfileId());
}


@Override
public ProductsToPickView getView(){
    return ProductsToPickView.cast(super.getView());
}


public boolean isPickerProfile(){
    return getViewProfileId() == null;
}


}
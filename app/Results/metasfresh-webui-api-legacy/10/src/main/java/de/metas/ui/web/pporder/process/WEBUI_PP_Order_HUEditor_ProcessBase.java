package de.metas.ui.web.pporder.process;
 import java.util.Optional;
import java.util.stream.Stream;
import org.compiere.Adempiere;
import de.metas.handlingunits.pporder.api.IHUPPOrderQtyDAO;
import de.metas.handlingunits.sourcehu.SourceHUsService;
import de.metas.ui.web.handlingunits.HUEditorProcessTemplate;
import de.metas.ui.web.handlingunits.HUEditorRow;
import de.metas.ui.web.handlingunits.HUEditorView;
import de.metas.ui.web.pporder.PPOrderLinesView;
import de.metas.ui.web.view.IViewsRepository;
import de.metas.ui.web.view.ViewId;
import de.metas.util.Services;
import lombok.NonNull;
public class WEBUI_PP_Order_HUEditor_ProcessBase extends HUEditorProcessTemplate{


public Stream<HUEditorRow> retrieveEligibleHUEditorRows(Stream<HUEditorRow> inputStream){
    final SourceHUsService sourceHuService = SourceHUsService.get();
    final IHUPPOrderQtyDAO huPpOrderQtyDAO = Services.get(IHUPPOrderQtyDAO.class);
    return inputStream.filter(huRow -> huRow.isHUStatusActive()).filter(huRow -> !sourceHuService.isHuOrAnyParentSourceHu(huRow.getHuId())).filter(huRow -> !huPpOrderQtyDAO.isHuIdIssued(huRow.getHuId()));
}


public Stream<HUEditorRow> retrieveSelectedAndEligibleHUEditorRows(){
    final HUEditorView huEditorView = HUEditorView.cast(super.getView());
    final Stream<HUEditorRow> huEditorRows = huEditorView.streamByIds(getSelectedRowIds());
    return retrieveEligibleHUEditorRows(huEditorRows);
}


public Optional<PPOrderLinesView> getPPOrderView(){
    final ViewId parentViewId = getView().getParentViewId();
    if (parentViewId == null) {
        return Optional.empty();
    }
    final IViewsRepository viewsRepo = Adempiere.getBean(IViewsRepository.class);
    final PPOrderLinesView ppOrderView = viewsRepo.getView(parentViewId, PPOrderLinesView.class);
    return Optional.of(ppOrderView);
}


}
package de.metas.ui.web.pporder.process;
 import java.util.List;
import org.eevolution.model.I_PP_Order_BOMLine;
import org.eevolution.model.X_PP_Order_BOMLine;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.common.collect.ImmutableList;
import de.metas.handlingunits.HuId;
import de.metas.handlingunits.IHUQueryBuilder;
import de.metas.handlingunits.pporder.api.IHUPPOrderBL;
import de.metas.handlingunits.pporder.api.IHUPPOrderQtyDAO;
import de.metas.handlingunits.sourcehu.SourceHUsService;
import de.metas.material.planning.pporder.IPPOrderBOMDAO;
import de.metas.material.planning.pporder.PPOrderBOMLineId;
import de.metas.process.IADProcessDAO;
import de.metas.process.IProcessPrecondition;
import de.metas.process.ProcessExecutionResult.ViewOpenTarget;
import de.metas.process.ProcessExecutionResult.WebuiViewToOpen;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.process.RelatedProcessDescriptor;
import de.metas.process.RelatedProcessDescriptor.DisplayPlace;
import de.metas.ui.web.handlingunits.HUIdsFilterHelper;
import de.metas.ui.web.handlingunits.WEBUI_HU_Constants;
import de.metas.ui.web.pporder.PPOrderLineRow;
import de.metas.ui.web.pporder.PPOrderLinesView;
import de.metas.ui.web.process.adprocess.ViewBasedProcessTemplate;
import de.metas.ui.web.view.CreateViewRequest;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.view.IViewsRepository;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.util.Services;
import de.metas.util.StringUtils;
public class WEBUI_PP_Order_HUEditor_Launcher extends ViewBasedProcessTemplateimplements IProcessPrecondition{

 private  IHUPPOrderQtyDAO huOrderCandidatesRepo;

 private  IHUPPOrderBL huppOrderBL;

 private  IPPOrderBOMDAO orderBOMsRepo;

 private  IADProcessDAO adProcessDAO;

@Autowired
 private  IViewsRepository viewsRepo;


public RelatedProcessDescriptor createSelectHuAsSourceHuDescriptor(){
    return RelatedProcessDescriptor.builder().processId(adProcessDAO.retrieveProcessIdByClassIfUnique(WEBUI_PP_Order_HUEditor_Create_M_Source_HUs.class)).displayPlace(DisplayPlace.ViewQuickActions).build();
}


public boolean isEligibleHuToIssue(HuId huId){
    if (SourceHUsService.get().isHuOrAnyParentSourceHu(huId)) {
        return false;
    }
    if (huOrderCandidatesRepo.isHuIdIssued(huId)) {
        return false;
    }
    return true;
}


public RelatedProcessDescriptor createIssueTopLevelHusDescriptor(){
    return RelatedProcessDescriptor.builder().processId(adProcessDAO.retrieveProcessIdByClassIfUnique(WEBUI_PP_Order_HUEditor_IssueTopLevelHUs.class)).displayPlace(DisplayPlace.ViewQuickActions).build();
}


@Override
public PPOrderLineRow getSingleSelectedRow(){
    return PPOrderLineRow.cast(super.getSingleSelectedRow());
}


@Override
public String doIt(){
    final PPOrderLineRow ppOrderLineRow = getSingleSelectedRow();
    final PPOrderBOMLineId ppOrderBomLineId = ppOrderLineRow.getOrderBOMLineId();
    final ViewId ppOrderLineViewId = getView().getViewId();
    final List<HuId> availableHUsIDs = retrieveHuIdsToShowInEditor(ppOrderBomLineId);
    final IView husToPickView = viewsRepo.createView(CreateViewRequest.builder(WEBUI_HU_Constants.WEBUI_HU_Window_ID, JSONViewDataType.includedView).setParentViewId(ppOrderLineViewId).setParentRowId(ppOrderLineRow.getId()).addStickyFilters(HUIdsFilterHelper.createFilter(availableHUsIDs)).addAdditionalRelatedProcessDescriptor(createIssueTopLevelHusDescriptor()).addAdditionalRelatedProcessDescriptor(createIssueTUsDescriptor()).addAdditionalRelatedProcessDescriptor(createSelectHuAsSourceHuDescriptor()).build());
    getResult().setWebuiViewToOpen(WebuiViewToOpen.builder().viewId(husToPickView.getViewId().getViewId()).target(ViewOpenTarget.IncludedView).build());
    return MSG_OK;
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    if (!getSelectedRowIds().isSingleDocumentId()) {
        return ProcessPreconditionsResolution.rejectBecauseNotSingleSelection();
    }
    final PPOrderLineRow singleSelectedRow = getSingleSelectedRow();
    if (!singleSelectedRow.isIssue()) {
        final String internalReason = StringUtils.formatMessage("The selected ppOrderLineRow is not an issue row; selectedRow={}", singleSelectedRow);
        return ProcessPreconditionsResolution.rejectWithInternalReason(internalReason);
    }
    final PPOrderLinesView ppOrderLineView = getView();
    /*
		 * The IssueMethod IssueOnlyForReceived is designed for BOM components that are added only after the main product was received.
		 * For example, if a BOM consists in several products that will, in the end, be wrapped together, the "wrap" product will have this issue method.
		 * This means that after all the products needed for the BOM are received, they will be wrapped with a piece of wrap that is big enough to fit them all and we will issue only the needed quantity of it.
		 */
    final boolean isIssueOnlyWhatWasReceived = X_PP_Order_BOMLine.ISSUEMETHOD_IssueOnlyForReceived.equals(singleSelectedRow.getIssueMethod());
    if (isIssueOnlyWhatWasReceived) {
        // this action shall always be available for lines with issue method IssueOnlyWhatWasReceived, no matter the pp_Order status or if the line was processed.
        return ProcessPreconditionsResolution.accept();
    }
    if (!ppOrderLineView.isStatusPlanning() && !ppOrderLineView.isStatusReview()) {
        final String internalReason = StringUtils.formatMessage("The PP_Order of the the current ppOrderLineView is not in planning or in review; ppOrderLineView={}", ppOrderLineView);
        return ProcessPreconditionsResolution.rejectWithInternalReason(internalReason);
    }
    if (singleSelectedRow.isProcessed()) {
        final String internalReason = StringUtils.formatMessage("The selected ppOrderLineRow is already flagged as processed; selectedRow={}", singleSelectedRow);
        return ProcessPreconditionsResolution.rejectWithInternalReason(internalReason);
    }
    return ProcessPreconditionsResolution.accept();
}


public RelatedProcessDescriptor createIssueTUsDescriptor(){
    return RelatedProcessDescriptor.builder().processId(adProcessDAO.retrieveProcessIdByClassIfUnique(WEBUI_PP_Order_HUEditor_IssueTUs.class)).displayPlace(DisplayPlace.ViewQuickActions).build();
}


@Override
public PPOrderLinesView getView(){
    return PPOrderLinesView.cast(super.getView());
}


public List<HuId> retrieveHuIdsToShowInEditor(PPOrderBOMLineId ppOrderBomLineId){
    final I_PP_Order_BOMLine ppOrderBomLine = orderBOMsRepo.getOrderBOMLineById(ppOrderBomLineId);
    final IHUQueryBuilder huIdsToAvailableToIssueQuery = huppOrderBL.createHUsAvailableToIssueQuery(ppOrderBomLine);
    final List<HuId> availableHUsIDs = huIdsToAvailableToIssueQuery.createQuery().listIds().stream().map(HuId::ofRepoId).filter(this::isEligibleHuToIssue).collect(ImmutableList.toImmutableList());
    return availableHUsIDs;
}


}
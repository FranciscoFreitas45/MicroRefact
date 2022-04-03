package de.metas.ui.web.pporder.process;
 import de.metas.ui.web.handlingunits.WEBUI_HU_Constants.MSG_WEBUI_SELECT_ACTIVE_UNSELECTED_HU;
import java.util.List;
import java.util.stream.Stream;
import org.adempiere.exceptions.AdempiereException;
import com.google.common.collect.ImmutableList;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.pporder.api.IHUPPOrderBL;
import de.metas.i18n.IMsgBL;
import de.metas.i18n.ITranslatableString;
import de.metas.material.planning.pporder.PPOrderId;
import de.metas.process.IProcessPrecondition;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.handlingunits.HUEditorRow;
import de.metas.ui.web.handlingunits.HUEditorRowFilter;
import de.metas.ui.web.handlingunits.HUEditorRowFilter.Select;
import de.metas.ui.web.handlingunits.HUEditorView;
import de.metas.ui.web.pporder.PPOrderLinesView;
import de.metas.util.Services;
public class WEBUI_PP_Order_HUEditor_IssueTopLevelHUs extends WEBUI_PP_Order_HUEditor_ProcessBaseimplements IProcessPrecondition{


@Override
public String doIt(){
    final Stream<HUEditorRow> selectedTopLevelHuRows = streamSelectedRows(HUEditorRowFilter.select(Select.ONLY_TOPLEVEL));
    final List<I_M_HU> hus = retrieveEligibleHUEditorRows(selectedTopLevelHuRows).map(HUEditorRow::getM_HU).collect(ImmutableList.toImmutableList());
    if (hus.isEmpty()) {
        throw new AdempiereException("@NoSelection@");
    }
    final PPOrderLinesView ppOrderView = getPPOrderView().get();
    final PPOrderId ppOrderId = ppOrderView.getPpOrderId();
    Services.get(IHUPPOrderBL.class).createIssueProducer(ppOrderId).createIssues(hus);
    final HUEditorView huEditorView = getView();
    huEditorView.removeHUsAndInvalidate(hus);
    ppOrderView.invalidateAll();
    return MSG_OK;
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    final boolean anyHuMatches = retrieveSelectedAndEligibleHUEditorRows().anyMatch(huRow -> huRow.isTopLevel());
    if (anyHuMatches) {
        return ProcessPreconditionsResolution.accept();
    }
    final ITranslatableString reason = Services.get(IMsgBL.class).getTranslatableMsgText(MSG_WEBUI_SELECT_ACTIVE_UNSELECTED_HU);
    return ProcessPreconditionsResolution.reject(reason);
}


}
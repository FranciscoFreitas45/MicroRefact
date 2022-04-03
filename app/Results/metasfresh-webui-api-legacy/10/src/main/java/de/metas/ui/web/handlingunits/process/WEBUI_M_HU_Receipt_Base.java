package de.metas.ui.web.handlingunits.process;
 import org.adempiere.util.lang.MutableInt;
import java.util.Objects;
import de.metas.process.IProcessPrecondition;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.handlingunits.HUEditorRow;
import de.metas.ui.web.handlingunits.HUEditorView;
import de.metas.ui.web.process.adprocess.ViewBasedProcessTemplate;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
public class WEBUI_M_HU_Receipt_Base extends ViewBasedProcessTemplateimplements IProcessPrecondition{


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    if (!isViewClass(HUEditorView.class)) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("The current view is not an HUEditorView");
    }
    final DocumentIdsSelection selectedRowIds = getSelectedRowIds();
    if (selectedRowIds.isEmpty()) {
        return ProcessPreconditionsResolution.rejectBecauseNoSelection();
    }
    final MutableInt checkedDocumentsCount = new MutableInt(0);
    final ProcessPreconditionsResolution firstRejection = getView(HUEditorView.class).streamByIds(selectedRowIds).filter(document -> document.isPureHU()).peek(// count checked documents
    document -> checkedDocumentsCount.incrementAndGet()).map(// create reject resolution if any
    document -> rejectResolutionOrNull(document)).filter(// filter out those which are not errors
    Objects::nonNull).findFirst().orElse(null);
    if (firstRejection != null) {
        // found a record which is not eligible => don't run the process
        return firstRejection;
    }
    if (checkedDocumentsCount.getValue() <= 0) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("no eligible rows");
    }
    return ProcessPreconditionsResolution.accept();
}


public ProcessPreconditionsResolution rejectResolutionOrNull(HUEditorRow document)


}
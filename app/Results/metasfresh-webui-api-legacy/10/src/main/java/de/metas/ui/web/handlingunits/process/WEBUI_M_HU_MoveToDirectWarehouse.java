package de.metas.ui.web.handlingunits.process;
 import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.process.IProcessPrecondition;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.process.RunOutOfTrx;
import de.metas.ui.web.handlingunits.HUEditorProcessTemplate;
import de.metas.ui.web.handlingunits.HUEditorRowFilter;
import de.metas.ui.web.handlingunits.HUEditorRowFilter.Select;
import de.metas.ui.web.handlingunits.WEBUI_HU_Constants;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.model.DocumentCollection;
public class WEBUI_M_HU_MoveToDirectWarehouse extends HUEditorProcessTemplateimplements IProcessPrecondition{

@Autowired
 private  DocumentCollection documentsCollection;

 private  HUEditorRowFilter rowsFilter;


@Override
@RunOutOfTrx
public String doIt(){
    final Stream<I_M_HU> selectedTopLevelHUs = streamSelectedHUs(rowsFilter);
    HUMoveToDirectWarehouseService.newInstance().setDocumentsCollection(documentsCollection).setHUView(getView()).setFailOnFirstError(true).setFailIfNoHUs(true).setLoggable(this).move(selectedTopLevelHUs.iterator());
    return MSG_OK;
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    if (!isHUEditorView()) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("not the HU view");
    }
    final DocumentIdsSelection selectedRowIds = getSelectedRowIds();
    if (selectedRowIds.isEmpty()) {
        return ProcessPreconditionsResolution.rejectBecauseNoSelection();
    }
    if (!streamSelectedHUIds(rowsFilter).findAny().isPresent()) {
        return ProcessPreconditionsResolution.reject(msgBL.getTranslatableMsgText(WEBUI_HU_Constants.MSG_WEBUI_ONLY_TOP_LEVEL_HU));
    }
    return ProcessPreconditionsResolution.accept();
}


}
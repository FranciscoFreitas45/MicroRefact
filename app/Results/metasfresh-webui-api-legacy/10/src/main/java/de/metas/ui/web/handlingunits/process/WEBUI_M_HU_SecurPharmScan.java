package de.metas.ui.web.handlingunits.process;
 import org.springframework.beans.factory.annotation.Autowired;
import de.metas.handlingunits.HuId;
import de.metas.process.IProcessPrecondition;
import de.metas.process.Param;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.handlingunits.HUEditorProcessTemplate;
import de.metas.ui.web.handlingunits.HUEditorRow;
import de.metas.ui.web.handlingunits.HUEditorView;
import de.metas.ui.web.process.adprocess.WebuiProcess;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.datatypes.PanelLayoutType;
import de.metas.vertical.pharma.securpharm.product.DataMatrixCode;
import de.metas.vertical.pharma.securpharm.service.SecurPharmHUAttributesScanner;
import de.metas.vertical.pharma.securpharm.service.SecurPharmHUAttributesScannerResult;
import de.metas.vertical.pharma.securpharm.service.SecurPharmService;
@WebuiProcess(layoutType = PanelLayoutType.SingleOverlayField)
public class WEBUI_M_HU_SecurPharmScan extends HUEditorProcessTemplateimplements IProcessPrecondition{

@Autowired
 private  SecurPharmService securPharmService;

 static  String PARAM_Barcode;

@Param(mandatory = true, parameterName = PARAM_Barcode)
 private  String dataMatrixString;


public HuId getSelectedHuId(){
    return getSingleSelectedRow().getHuId();
}


public DataMatrixCode getDataMatrix(){
    return DataMatrixCode.ofString(dataMatrixString);
}


@Override
public String doIt(){
    final SecurPharmHUAttributesScanner scanner = securPharmService.newHUScanner();
    final SecurPharmHUAttributesScannerResult result = scanner.scanAndUpdateHUAttributes(getDataMatrix(), getSelectedHuId());
    // 
    // Update view
    final HUEditorView view = getView();
    if (result.getExtractedCUId() != null) {
        view.addHUId(result.getExtractedCUId());
    }
    view.invalidateAll();
    return result.getResultMessageAndCode();
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    final DocumentIdsSelection selectedRowIds = getSelectedRowIds();
    if (selectedRowIds.isEmpty()) {
        return ProcessPreconditionsResolution.rejectBecauseNoSelection();
    }
    if (!securPharmService.hasConfig()) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("no SecurPharm config");
    }
    if (!selectedRowIds.isSingleDocumentId()) {
        return ProcessPreconditionsResolution.rejectBecauseNotSingleSelection().toInternal();
    }
    final HUEditorRow row = getSingleSelectedRow();
    final HuId huId = row.getHuId();
    final SecurPharmHUAttributesScanner scanner = securPharmService.newHUScanner();
    if (!scanner.isEligible(huId)) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("HU not eligible");
    }
    return ProcessPreconditionsResolution.accept();
}


}
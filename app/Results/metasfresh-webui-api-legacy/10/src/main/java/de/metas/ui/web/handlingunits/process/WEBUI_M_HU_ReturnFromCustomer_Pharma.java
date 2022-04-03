package de.metas.ui.web.handlingunits.process;
 import org.springframework.beans.factory.annotation.Autowired;
import de.metas.process.Param;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.handlingunits.HUEditorRowFilter.Select;
import de.metas.ui.web.handlingunits.WEBUI_HU_Constants;
import de.metas.ui.web.process.adprocess.WebuiProcess;
import de.metas.ui.web.window.datatypes.PanelLayoutType;
import de.metas.vertical.pharma.securpharm.product.DataMatrixCode;
import de.metas.vertical.pharma.securpharm.service.SecurPharmHUAttributesScanner;
import de.metas.vertical.pharma.securpharm.service.SecurPharmService;
@WebuiProcess(layoutType = PanelLayoutType.SingleOverlayField)
public class WEBUI_M_HU_ReturnFromCustomer_Pharma extends WEBUI_M_HU_ReturnFromCustomer{

@Autowired
 private  SecurPharmService securPharmService;

@Param(mandatory = true, parameterName = WEBUI_M_HU_SecurPharmScan.PARAM_Barcode)
 private  String dataMatrixCode;


public DataMatrixCode getDataMatrix(){
    return DataMatrixCode.ofString(dataMatrixCode);
}


@Override
public String doIt(){
    final DataMatrixCode dataMatrix = getDataMatrix();
    final SecurPharmHUAttributesScanner scanner = securPharmService.newHUScanner();
    streamSelectedHUs(Select.ONLY_TOPLEVEL).forEach(hu -> scanner.scanAndUpdateHUAttributes(dataMatrix, hu));
    return super.doIt();
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    if (!isHUEditorView()) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("not the HU view");
    }
    if (!streamSelectedHUIds(Select.ONLY_TOPLEVEL).findAny().isPresent()) {
        return ProcessPreconditionsResolution.reject(msgBL.getTranslatableMsgText(WEBUI_HU_Constants.MSG_WEBUI_ONLY_TOP_LEVEL_HU));
    }
    if (!securPharmService.hasConfig()) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("No securpharm config");
    }
    return ProcessPreconditionsResolution.accept();
}


}
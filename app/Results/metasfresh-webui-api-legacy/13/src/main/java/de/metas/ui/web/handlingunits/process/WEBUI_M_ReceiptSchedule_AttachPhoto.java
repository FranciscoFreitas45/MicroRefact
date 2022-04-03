package de.metas.ui.web.handlingunits.process;
 import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import javax.imageio.ImageIO;
import org.compiere.model.MImage;
import de.metas.handlingunits.model.I_M_ReceiptSchedule;
import de.metas.handlingunits.receiptschedule.IHUReceiptScheduleBL;
import de.metas.process.IProcessPreconditionsContext;
import de.metas.process.Param;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.util.Services;
public class WEBUI_M_ReceiptSchedule_AttachPhoto extends ReceiptScheduleBasedProcess{

@Param(parameterName = "AD_Image_ID", mandatory = true)
 private  int p_AD_Image_ID;


@Override
public String doIt(){
    final I_M_ReceiptSchedule receiptSchedule = getRecord(I_M_ReceiptSchedule.class);
    final MImage adImage = MImage.get(getCtx(), p_AD_Image_ID);
    if (adImage == null || adImage.getAD_Image_ID() <= 0) {
        throw new EntityNotFoundException("@NotFound@ @AD_Image_ID@: " + p_AD_Image_ID);
    }
    final String name = adImage.getName();
    final byte[] data = adImage.getData();
    final BufferedImage image = ImageIO.read(new ByteArrayInputStream(data));
    Services.get(IHUReceiptScheduleBL.class).attachPhoto(receiptSchedule, name, image);
    return MSG_OK;
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(IProcessPreconditionsContext context){
    // Allow only single selection
    if (context.isNoSelection()) {
        return ProcessPreconditionsResolution.rejectBecauseNoSelection();
    } else if (!context.isSingleSelection()) {
        return ProcessPreconditionsResolution.rejectBecauseNotSingleSelection();
    }
    return ProcessPreconditionsResolution.accept();
}


}
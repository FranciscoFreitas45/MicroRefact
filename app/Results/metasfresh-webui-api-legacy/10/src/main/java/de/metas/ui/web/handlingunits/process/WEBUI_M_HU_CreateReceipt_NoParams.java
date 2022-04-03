package de.metas.ui.web.handlingunits.process;
 import org.springframework.context.annotation.Profile;
import de.metas.Profiles;
import de.metas.handlingunits.receiptschedule.IHUReceiptScheduleBL.CreateReceiptsParameters.CreateReceiptsParametersBuilder;
import de.metas.process.IProcessPrecondition;
import lombok.NonNull;
@Profile(Profiles.PROFILE_Webui)
public class WEBUI_M_HU_CreateReceipt_NoParams extends WEBUI_M_HU_CreateReceipt_Baseimplements IProcessPrecondition{


@Override
public void customizeParametersBuilder(CreateReceiptsParametersBuilder parametersBuilder){
// nothing to be done with the existing parameters
}


}
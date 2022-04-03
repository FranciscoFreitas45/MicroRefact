package de.metas.ui.web.quickinput;
 import org.adempiere.service.ISysConfigBL;
import de.metas.util.Services;
import lombok.experimental.UtilityClass;
@UtilityClass
public class QuickInputConstants {

 private  String SYSCONFIG_EnablePackingInstructionsField;

 private  String SYSCONFIG_EnableBestBeforePolicy;


public boolean isEnablePackingInstructionsField(){
    return Services.get(ISysConfigBL.class).getBooleanValue(SYSCONFIG_EnablePackingInstructionsField, true);
}


public boolean isEnableBestBeforePolicy(){
    return Services.get(ISysConfigBL.class).getBooleanValue(SYSCONFIG_EnableBestBeforePolicy, true);
}


}
package de.metas.ui.web.window;
 import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import org.slf4j.Logger;
import com.google.common.collect.ImmutableSet;
import de.metas.logging.LogManager;
import de.metas.ui.web.window.datatypes.WindowId;
public class WindowConstants {

 public  Logger logger;

 public  WindowId WINDOWID_R_Request;

 public  WindowId WINDOWID_UserProfile;

 public  String FIELDNAME_DocumentNo;

 public  String FIELDNAME_Value;

 public  String FIELDNAME_Name;

 public  String FIELDNAME_IsActive;

 public  String FIELDNAME_DocStatus;

 public  String FIELDNAME_DocAction;

 public  String FIELDNAME_Posted;

 public  String FIELDNAME_IsSOTrx;

 public  String FIELDNAME_Processing;

 public  String FIELDNAME_Processed;

 public  String FIELDNAME_C_DocType_ID;

 public  String FIELDNAME_C_DocTypeTarget_ID;

 public  String FIELDNAME_OrderType;

 public  String FIELDNAME_M_AttributeSetInstance_ID;

 public  String FIELDNAME_Line;

 public  String FIELDNAME_C_Currency_ID;

 public  String FIELDNAME_TimeZone;

 public  String FIELDNAME_AD_Client_ID;

 public  String FIELDNAME_AD_Org_ID;

 public  String FIELDNAME_Created;

 public  String FIELDNAME_CreatedBy;

 public  String FIELDNAME_Updated;

 public  String FIELDNAME_UpdatedBy;

 public  Set<String> FIELDNAMES_CreatedUpdated;

 public  String FIELDNAME_DocumentSummary;

 public  String CONTEXTVAR_NextLineNo;

 public  String TABLENAME_AD_Ref_List;

 private  AtomicBoolean protocolDebugging;


public void setProtocolDebugging(boolean protocolDebugging){
    final boolean protocolDebuggingPrev = WindowConstants.protocolDebugging.getAndSet(protocolDebugging);
    if (protocolDebuggingPrev == protocolDebugging) {
        return;
    }
    System.out.println("--------------------------------------------------------------------------------------------");
    if (protocolDebugging) {
        System.out.println("Protocol debugging was enabled");
    } else {
        System.out.println("Protocol debugging was disabled");
    }
    System.out.println("--------------------------------------------------------------------------------------------");
}


public boolean isProtocolDebugging(){
    return protocolDebugging.get();
}


}
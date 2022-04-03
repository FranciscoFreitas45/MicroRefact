package de.metas.security.process;
 public class WEBUI_UserGroupRecordAccess_Revoke extends WEBUI_UserGroupRecordAccess_Base{


@Override
public String doIt(){
    revokeAccessFromSelectedRows();
    return MSG_OK;
}


}
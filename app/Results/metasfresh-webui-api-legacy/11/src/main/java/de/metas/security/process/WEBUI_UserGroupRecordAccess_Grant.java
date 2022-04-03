package de.metas.security.process;
 public class WEBUI_UserGroupRecordAccess_Grant extends WEBUI_UserGroupRecordAccess_Base{


@Override
public String doIt(){
    grantAccessToSelectedRows();
    return MSG_OK;
}


}
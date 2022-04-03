package com.ukefu.util.extra;
 import com.ukefu.webim.web.model.PbxHost;
import com.ukefu.webim.web.model.PbxHostLog;
public interface CallCenterInterface {


public boolean connected(String id)
;

public PbxHostLog init(PbxHost pbxHost)
;

public void remove(String id)
;

}
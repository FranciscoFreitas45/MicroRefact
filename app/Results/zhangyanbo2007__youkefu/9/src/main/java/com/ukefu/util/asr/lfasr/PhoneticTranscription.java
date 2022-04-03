package com.ukefu.util.asr.lfasr;
 import com.ukefu.webim.web.model.QualityConfig;
import com.ukefu.webim.web.model.StatusEvent;
public interface PhoneticTranscription {


public boolean getStatus(StatusEvent statusEvent,QualityConfig qcConfig)
;

public String getLfasr(String userid,String orgi,String organ,StatusEvent statusEvent,String local_file,QualityConfig qcConfig)
;

}
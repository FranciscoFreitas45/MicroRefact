package com.gbcom.common.im;
 import com.gbcom.common.im.exception.IMInitialException;
import java.util.HashMap;
public interface IIMService {


public HashMap<String,IMVersionChain> getIM()
;

public boolean initial()
;

public IMVersionChain getIMChain(String type)
;

public IIM getIIM(String type,String version)
;

}
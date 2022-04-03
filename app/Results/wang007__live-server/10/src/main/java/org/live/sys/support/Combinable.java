package org.live.sys.support;
 import java.util.List;
public interface Combinable {


public boolean compare(Object compareObj)
;

public void setChilds(List<? extends Combinable> combinables)
;

}
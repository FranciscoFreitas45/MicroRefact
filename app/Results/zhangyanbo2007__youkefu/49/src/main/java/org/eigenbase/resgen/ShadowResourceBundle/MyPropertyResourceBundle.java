package org.eigenbase.resgen.ShadowResourceBundle;
 import java.io.IOException;
import java.io.InputStream;
import java.util;
public class MyPropertyResourceBundle extends PropertyResourceBundle{

public MyPropertyResourceBundle(InputStream stream) throws IOException {
    super(stream);
}
public void setParentTrojan(ResourceBundle parent){
    super.setParent(parent);
}


}
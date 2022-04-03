package org.opengeoportal.proxy;
 import java.util.UUID;
import org.opengeoportal.proxy.controllers.ImageRequest;
public interface ImageHandler {


public UUID requestImage(String sessionId,ImageRequest imageRequest)
;

}
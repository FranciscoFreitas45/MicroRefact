package org.opengeoportal.utilities;
 import java.io.File;
import org.opengeoportal.layer.BoundingBox;
public interface QuickDownload {


public File downloadZipFile(String layerId,BoundingBox bounds)
;

}
package org.opengeoportal.download.methods;
 import java.io.File;
import java.util.Set;
import com.vividsolutions.jts.geom.Envelope;
public interface FeatureSourceToShape {


public FeatureSourceRetriever getFeatureSourceRetriever()
;

public void setFeatureCollectionBBox(Envelope bbox)
;

public Set<File> exportToShapefiles()
;

}
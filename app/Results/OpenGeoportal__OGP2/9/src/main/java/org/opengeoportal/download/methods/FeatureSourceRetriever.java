package org.opengeoportal.download.methods;
 import org.geotools.data.simple.SimpleFeatureSource;
import org.opengeoportal.download.types.LayerRequest;
public interface FeatureSourceRetriever {


public void createFeatureSourceFromLayerRequest(LayerRequest layerRequest)
;

public SimpleFeatureSource getFeatureSource()
;

}
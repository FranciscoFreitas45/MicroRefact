package org.opengeoportal.download.methods;
 import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Future;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.opengeoportal.download.types.LayerRequest;
import org.opengeoportal.layer.BoundingBox;
import org.opengeoportal.solr.SolrRecord;
import org.opengeoportal.utilities.LocationFieldUtils;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.AsyncResult;
import com.vividsolutions.jts.geom.Envelope;
import org.opengeoportal.Interface.FeatureSourceToShape;
import org.opengeoportal.DTO.FeatureSourceRetriever;
import org.opengeoportal.DTO.FeatureSourceRetriever;
public class GeoToolsDownloadMethod extends AbstractDownloadMethodimplements PerLayerDownloadMethod{

 private  Boolean INCLUDES_METADATA;

 private  String METHOD;

 private  FeatureSourceToShape featureSourceToShape;

 final  Logger logger;


@Override
public String createDownloadRequest(){
    return new String("This is a dummy implementation.");
}


@Override
public Future<Set<File>> download(LayerRequest currentLayer){
    currentLayer.setMetadata(this.includesMetadata());
    BoundingBox bbox = currentLayer.getRequestedBounds();
    Envelope currentBounds = new Envelope(bbox.getMinX(), bbox.getMinY(), bbox.getMaxX(), bbox.getMaxY());
    FeatureSourceRetriever fsr = featureSourceToShape.getFeatureSourceRetriever();
    fsr.createFeatureSourceFromLayerRequest(currentLayer);
    // might as well get the native bounds from the source...the metadata might be wrong
    ReferencedEnvelope nativeBounds = fsr.getFeatureSource().getBounds();
    CoordinateReferenceSystem crs = DefaultGeographicCRS.WGS84;
    ReferencedEnvelope currentEnv = ReferencedEnvelope.create(currentBounds, crs);
    logger.info(currentEnv.toString());
    logger.info(currentEnv.getCoordinateReferenceSystem().toString());
    logger.info(nativeBounds.toString());
    logger.info(nativeBounds.getCoordinateReferenceSystem().toString());
    /*if (!nativeBounds.intersects(currentBounds)){
			//throw Exception?  bounds don't intersect
			throw new Exception("Bounds don't intersect!");
		}*/
    featureSourceToShape.setFeatureCollectionBBox(currentBounds);
    Set<File> fileSet = new HashSet<File>();
    fileSet.addAll(featureSourceToShape.exportToShapefiles());
    return new AsyncResult<Set<File>>(fileSet);
}


public void setFeatureSourceToShape(FeatureSourceToShape featureSourceToShape){
    this.featureSourceToShape = featureSourceToShape;
}


@Override
public Boolean includesMetadata(){
    return INCLUDES_METADATA;
}


@Override
public Set<String> getExpectedContentType(){
    Set<String> expectedContentType = new HashSet<String>();
    expectedContentType.add("application/zip");
    return expectedContentType;
}


public FeatureSourceToShape getFeatureSourceToShape(){
    return featureSourceToShape;
}


@Override
public String getMethod(){
    return METHOD;
}


@Override
public List<String> getUrls(LayerRequest layer){
    // if(LocationFieldUtils.hasArcGISRestUrl(layer.getLayerInfo().getLocation()))
    // {
    String url = layer.getWfsUrl();
    this.checkUrl(url);
    return urlToUrls(url);
// }
// return null;
}


}
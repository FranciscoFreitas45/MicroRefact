package org.opengeoportal.download.methods;
 import java.net.MalformedURLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.opengeoportal.download.types.LayerRequest;
import org.opengeoportal.layer.BoundingBox;
import com.fasterxml.jackson.core.JsonParseException;
@Deprecated
public class MITDownloadMethod extends AbstractDownloadMethodimplements PerLayerDownloadMethod{

 private  Boolean INCLUDES_METADATA;

 private  String METHOD;


@Override
public String createDownloadRequest(){
    BoundingBox bounds = this.getClipBounds();
    String layerName = getLayerName();
    String geometry = getDataType();
    String getFeatureRequest = "layer=" + layerName + "&bbox=" + bounds.toString() + "&geom=" + geometry;
    return getFeatureRequest;
}


public String getLayerName(){
    String layerName = this.currentLayer.getLayerInfo().getName();
    layerName = layerName.substring(layerName.indexOf(":") + 1);
    // temporary?  name is wrong in solr index
    layerName = layerName.toUpperCase();
    if (layerName.indexOf("SDE_DATA") < 0) {
        layerName = "SDE_DATA." + layerName;
    }
    return layerName;
}


@Override
public Boolean includesMetadata(){
    return INCLUDES_METADATA;
}


public String getDataType(){
    String geometry = this.currentLayer.getLayerInfo().getDataType();
    if (geometry.equals("line")) {
        geometry = "arc";
    }
    return geometry;
}


@Override
public Set<String> getExpectedContentType(){
    Set<String> expectedContentType = new HashSet<String>();
    expectedContentType.add("application/zip");
    return expectedContentType;
}


@Override
public String getMethod(){
    return METHOD;
}


@Override
public List<String> getUrls(LayerRequest layer){
    String url = layer.getDownloadUrl().get(0);
    this.checkUrl(url);
    return urlToUrls(url);
}


}
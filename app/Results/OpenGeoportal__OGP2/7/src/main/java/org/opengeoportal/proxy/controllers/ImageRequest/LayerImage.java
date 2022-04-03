package org.opengeoportal.proxy.controllers.ImageRequest;
 import java.io.File;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Future;
import org.opengeoportal.download.controllers.RequestStatusController.StatusSummary;
import org.opengeoportal.layer.BoundingBox;
import org.opengeoportal.solr.SolrRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.opengeoportal.Interface.SolrRecord;
public class LayerImage implements Comparable<LayerImage>{

@JsonIgnore
 private String name;

@JsonProperty("opacity")
 private int opacity;

@JsonProperty("zIndex")
 private int zIndex;

@JsonProperty("layerId")
 private String layerId;

 private String sld;

@JsonIgnore
 private SolrRecord solrRecord;

@JsonIgnore
 private File imageFile;

@JsonIgnore
 private Future<File> imageFileFuture;

@JsonIgnore
 private ImageStatus imageStatus;

 private  URL url;


public void setName(String name){
    this.name = name;
}


public ImageStatus getImageStatus(){
    return imageStatus;
}


public String getName(){
    return name;
}


public void setSolrRecord(SolrRecord solrRecord){
    this.solrRecord = solrRecord;
}


public void setSld(String sld){
    this.sld = sld;
}


@Override
@JsonIgnore
public int compareTo(LayerImage n){
    return (zIndex < n.zIndex ? -1 : (zIndex == n.zIndex ? 0 : 1));
}


public void setImageFileFuture(Future<File> imageFileFuture){
    this.imageFileFuture = imageFileFuture;
}


public File getImageFile(){
    return imageFile;
}


public void setUrl(URL url){
    this.url = url;
}


public int getOpacity(){
    return opacity;
}


public URL getUrl(){
    return url;
}


public SolrRecord getSolrRecord(){
    return solrRecord;
}


public void setLayerId(String layerId){
    this.layerId = layerId;
}


public void setzIndex(int zIndex){
    this.zIndex = zIndex;
}


public void setImageFile(File imageFile){
    this.imageFile = imageFile;
}


public void setImageStatus(ImageStatus imageStatus){
    this.imageStatus = imageStatus;
}


public int getzIndex(){
    return zIndex;
}


@JsonIgnore
public boolean equals(Object o){
    if (!(o instanceof LayerImage))
        return false;
    LayerImage n = (LayerImage) o;
    return n.layerId.equals(layerId);
}


public Future<File> getImageFileFuture(){
    return imageFileFuture;
}


public String getLayerId(){
    return layerId;
}


public String getSld(){
    return sld;
}


public void setOpacity(int opacity){
    this.opacity = opacity;
}


}
package org.opengeoportal.download.methods;
 import java.net.MalformedURLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.opengeoportal.download.types.LayerRequest;
import com.fasterxml.jackson.core.JsonParseException;
public class FileDownloadMethod extends AbstractDownloadMethodimplements PerLayerDownloadMethod{

 private  Boolean INCLUDES_METADATA;

 private  String METHOD;


@Override
public String createDownloadRequest(){
    return "";
}


@Override
public Boolean expectedContentTypeMatched(String foundContentType){
    // a file download could be anything
    return true;
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


@Override
public String getMethod(){
    return METHOD;
}


@Override
public List<String> getUrls(LayerRequest layer){
    List<String> urls = layer.getDownloadUrl();
    for (String currentUrl : urls) {
        logger.info("download url:" + currentUrl);
        try {
            this.checkUrl(currentUrl);
        } catch (MalformedURLException e) {
        }
    }
    return urls;
}


}
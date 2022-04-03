package org.opengeoportal.download.methods;
 import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import org.opengeoportal.config.proxy.ProxyConfigRetriever;
import org.opengeoportal.download.types.LayerRequest;
import org.opengeoportal.solr.SolrRecord;
import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.core.JsonParseException;
import org.opengeoportal.Interface.ProxyConfigRetriever;
public class ProxiedWfsDownloadMethod extends WfsDownloadMethodimplements PerLayerDownloadMethod{

@Autowired
 private  ProxyConfigRetriever proxyConfigRetriever;


public String getProxyTo(LayerRequest layer){
    SolrRecord sr = layer.getLayerInfo();
    return proxyConfigRetriever.getInternalProxyUrl("wfs", sr.getInstitution(), sr.getAccess());
}


@Override
public List<String> getUrls(LayerRequest layer){
    String url;
    List<String> urls = new ArrayList<String>();
    try {
        url = getProxyTo(layer);
        urls.add(url);
    } catch (Exception e) {
        e.printStackTrace();
        throw new MalformedURLException("Proxy url not found.");
    }
    return urls;
}


}
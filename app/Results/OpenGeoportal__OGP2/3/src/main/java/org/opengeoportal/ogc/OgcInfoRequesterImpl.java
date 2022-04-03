package org.opengeoportal.ogc;
 import java.io.InputStream;
import org.apache.commons.io.IOUtils;
import org.opengeoportal.config.proxy.ProxyConfigRetriever;
import org.opengeoportal.ogc.OwsInfo;
import org.opengeoportal.solr.SolrRecord;
import org.opengeoportal.utilities.OgpUtils;
import org.opengeoportal.utilities.http.HttpRequester;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.opengeoportal.Interface.HttpRequester;
import org.opengeoportal.Interface.OgcInfoRequest;
import org.opengeoportal.Interface.ProxyConfigRetriever;
public class OgcInfoRequesterImpl implements OgcInfoRequester{

 private  HttpRequester httpRequester;

 private  OgcInfoRequest ogcInfoRequest;

 final  Logger logger;

 private  ProxyConfigRetriever proxyConfigRetriever;


public void setProxyConfigRetriever(ProxyConfigRetriever proxyConfigRetriever){
    this.proxyConfigRetriever = proxyConfigRetriever;
}


public void setHttpRequester(HttpRequester httpRequester){
    this.httpRequester = httpRequester;
}


public HttpRequester getHttpRequester(){
    return httpRequester;
}


public ProxyConfigRetriever getProxyConfigRetriever(){
    return proxyConfigRetriever;
}


public OwsInfo getOwsInfo(SolrRecord solrRecord){
    InputStream is = null;
    try {
        String layerName = OgpUtils.getLayerNameNS(solrRecord.getWorkspaceName(), solrRecord.getName());
        String request = ogcInfoRequest.createRequest(layerName);
        String method = ogcInfoRequest.getMethod();
        String protocol = ogcInfoRequest.getOgcProtocol().toLowerCase();
        String url = proxyConfigRetriever.getInternalUrl(protocol, solrRecord.getInstitution(), solrRecord.getAccess(), solrRecord.getLocation());
        is = httpRequester.sendRequest(url, request, method);
        int status = httpRequester.getStatus();
        if (status == 200) {
            String contentType = httpRequester.getContentType().toLowerCase();
            return handleResponse(contentType, is);
        } else {
            throw new Exception("Error communicating with server! response: " + Integer.toString(status));
        }
    } finally {
        IOUtils.closeQuietly(is);
    }
}


public OgcInfoRequest getOgcInfoRequest(){
    return ogcInfoRequest;
}


@Override
public AugmentedSolrRecord getOgcAugment(SolrRecord solrRecord){
    AugmentedSolrRecord asr = new AugmentedSolrRecord();
    asr.setSolrRecord(solrRecord);
    OwsInfo info = getOwsInfo(solrRecord);
    asr.getOwsInfo().add(info);
    return asr;
}


public OwsInfo handleResponse(String contentType,InputStream inputStream){
    logger.info(contentType);
    Boolean contentMatch = contentType.toLowerCase().contains("xml");
    if (!contentMatch) {
        logger.error("Unexpected content type: " + contentType);
        // If there is a mismatch with the expected content, but the response is text, we want to at least log the response
        if (contentType.toLowerCase().contains("text") || contentType.toLowerCase().contains("html") || contentType.toLowerCase().contains("xml")) {
            logger.error("Returned text: " + IOUtils.toString(inputStream));
        }
        throw new Exception("Unexpected content type");
    } else {
        try {
            return ogcInfoRequest.parseResponse(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Could not parse response");
        }
    }
}


public void setOgcInfoRequest(OgcInfoRequest ogcInfoRequest){
    this.ogcInfoRequest = ogcInfoRequest;
}


}
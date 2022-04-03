package org.opengeoportal.proxy.controllers;
 import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.http;
import org.apache.http.client.methods.AbortableHttpRequest;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import org.apache.http.message.HeaderGroup;
import org.apache.http.util.EntityUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.opengeoportal.metadata;
import org.opengeoportal.solr;
import org.opengeoportal.utilities.LocationFieldUtils;
import org.opengeoportal.utilities.OgpUtils;
import org.opengeoportal.utilities.http.OgpHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Enumeration;
import java.util.Formatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.opengeoportal.Interface.LayerInfoRetriever;
import org.opengeoportal.Interface.OgpHttpClient;
import org.opengeoportal.DTO.LayerInfoRetriever;
import org.opengeoportal.DTO.LayerInfoRetriever;
@Controller
@RequestMapping("/dynamic")
public class DynamicOgcController {

 final  Logger logger;

 protected  URI targetUri;

@Autowired
 private  LayerInfoRetriever layerInfoRetriever;

 private  TransformerFactory transformerFactory;

 private  DocumentBuilder builder;

@Autowired
@Qualifier("httpClient.pooling")
 private  OgpHttpClient ogpHttpClient;

 private Set<String> qualifiedNames;

 private String ogcUrl;

 private  HeaderGroup hopByHopHeaders;

 static  BitSet asciiQueryChars;


@RequestMapping(value = "/wfs", method = RequestMethod.GET)
public void doWfsRequest(Set<String> layerIds,HttpServletRequest servletRequest,HttpServletResponse servletResponse){
    Enumeration paramNames = servletRequest.getParameterNames();
    String ogcRequest = "";
    String typeName = "";
    while (paramNames.hasMoreElements()) {
        String param = (String) paramNames.nextElement();
        if (param.equalsIgnoreCase("version")) {
        } else if (param.equalsIgnoreCase("request")) {
            logger.info("request: " + servletRequest.getParameter(param));
            ogcRequest = servletRequest.getParameter(param);
        } else if (param.equalsIgnoreCase("typename")) {
            typeName = servletRequest.getParameter(param);
        }
    }
    if (ogcRequest.equalsIgnoreCase("describefeaturetype") || ogcRequest.equalsIgnoreCase("getfeature")) {
        // TODO: strip all the params and rebuild the request with only sanctioned parameters, in case of fussy servers
        String remoteUrl = getOgcUrlFromLayerName(typeName, "wfs");
        String newQuery = removeParamFromQuery(servletRequest.getQueryString(), "ogpids");
        if (ogcRequest.equalsIgnoreCase("describefeaturetype")) {
            newQuery = removeParamFromQuery(newQuery, "srsname");
        }
        remoteUrl += "?" + newQuery;
        logger.info("remote url:" + remoteUrl);
        doProxy(remoteUrl, servletRequest, servletResponse);
    } else if (ogcRequest.equalsIgnoreCase("getcapabilities")) {
    // forward to handleGetCapabilities
    } else {
        throw new Exception("Unrecognized request type.");
    }
}


public String removeParamFromQuery(String query,String param){
    if (query.startsWith("?")) {
        query = query.substring(1);
    }
    String[] arrQuery = query.split("&");
    String newQuery = "";
    for (int i = 0; i < arrQuery.length; i++) {
        String currentParam = arrQuery[i].substring(0, arrQuery[i].indexOf("="));
        if (!currentParam.equalsIgnoreCase(param)) {
            newQuery += arrQuery[i] + "&";
        }
    }
    if (newQuery.length() > 0) {
        newQuery = newQuery.substring(0, newQuery.length() - 1);
    }
    return newQuery;
}


public void copyResponseHeaders(HttpResponse proxyResponse,HttpServletResponse servletResponse){
    for (Header header : proxyResponse.getAllHeaders()) {
        if (hopByHopHeaders.containsHeader(header.getName()))
            continue;
        servletResponse.addHeader(header.getName(), header.getValue());
    }
}


@RequestMapping(value = "/wms", method = RequestMethod.GET, params = "request=GetCapabilities")
public ModelAndView doWmsGetCapabilitiesCase(Set<String> layerIds,HttpServletRequest servletRequest,HttpServletResponse servletResponse){
    return doWmsGetCapabilities(layerIds, servletRequest, servletResponse);
}


public Document getDocument(InputStream inputStream){
    try {
        if (builder == null) {
            this.createBuilder();
        }
        return builder.parse(inputStream);
    } finally {
        IOUtils.closeQuietly(inputStream);
    }
}


@RequestMapping(value = "/wfs", method = RequestMethod.GET, params = "REQUEST=GetCapabilities")
public ModelAndView doWfsGetCapabilities(Set<String> layerIds,HttpServletRequest servletRequest,HttpServletResponse servletResponse){
    return handleGetCapabilities(layerIds, servletRequest, servletResponse);
}


@RequestMapping(value = "/wms", method = RequestMethod.GET)
public void doWmsRequest(Set<String> layerIds,HttpServletRequest servletRequest,HttpServletResponse servletResponse){
    Enumeration paramNames = servletRequest.getParameterNames();
    String ogcRequest = "";
    String layers = "";
    while (paramNames.hasMoreElements()) {
        String param = (String) paramNames.nextElement();
        if (param.equalsIgnoreCase("version")) {
        } else if (param.equalsIgnoreCase("request")) {
            logger.info("request: " + servletRequest.getParameter(param));
            ogcRequest = servletRequest.getParameter(param);
        } else if (param.equalsIgnoreCase("layers")) {
            layers = servletRequest.getParameter(param);
        }
    }
    if (ogcRequest.equalsIgnoreCase("getfeatureinfo") || ogcRequest.equalsIgnoreCase("describelayer") || ogcRequest.equalsIgnoreCase("getmap")) {
        // TODO: strip all the params and rebuild the request with only sanctioned parameters, in case of fussy servers
        String remoteUrl = getOgcUrlFromLayerName(layers, "wms");
        String newQuery = removeParamFromQuery(servletRequest.getQueryString(), "ogpids");
        if (ogcRequest.equalsIgnoreCase("describelayer")) {
            newQuery = removeParamFromQuery(newQuery, "srsname");
        }
        remoteUrl += "?" + newQuery;
        logger.info("remote url:" + remoteUrl);
        doProxy(remoteUrl, servletRequest, servletResponse);
    }
}


public String xmlToString(Node node){
    StringWriter stw = new StringWriter();
    Transformer transformer = transformerFactory.newTransformer();
    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
    transformer.transform(new DOMSource(node), new StreamResult(stw));
    return stw.toString();
}


public void copyRequestHeaders(HttpServletRequest servletRequest,HttpRequest proxyRequest){
    // Get an Enumeration of all of the header names sent by the client
    Enumeration enumerationOfHeaderNames = servletRequest.getHeaderNames();
    while (enumerationOfHeaderNames.hasMoreElements()) {
        String headerName = (String) enumerationOfHeaderNames.nextElement();
        // TODO why?
        if (headerName.equalsIgnoreCase(HttpHeaders.CONTENT_LENGTH))
            continue;
        if (hopByHopHeaders.containsHeader(headerName))
            continue;
        // As per the Java Servlet API 2.5 documentation:
        // Some headers, such as Accept-Language can be sent by clients
        // as several headers each with a different value rather than
        // sending the header as a comma separated list.
        // Thus, we get an Enumeration of the header values sent by the client
        Enumeration headers = servletRequest.getHeaders(headerName);
        while (headers.hasMoreElements()) {
            String headerValue = (String) headers.nextElement();
            // Don't do this unless we need to
            /*if (headerName.equalsIgnoreCase(HttpHeaders.USER_AGENT)){
        	headerValue = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:24.0) Gecko/20100101 Firefox/24.0";
        }*/
            // In case the proxy host is running multiple virtual servers,
            // rewrite the Host header to ensure that we get content from
            // the correct virtual server
            if (headerName.equalsIgnoreCase(HttpHeaders.HOST)) {
                HttpHost host = URIUtils.extractHost(this.targetUri);
                headerValue = host.getHostName();
                if (host.getPort() != -1)
                    headerValue += ":" + host.getPort();
            }
            proxyRequest.addHeader(headerName, headerValue);
        }
    }
}


public CharSequence encodeUriQuery(CharSequence in){
    // Note that I can't simply use URI.java to encode because it will escape pre-existing escaped things.
    StringBuilder outBuf = null;
    Formatter formatter = null;
    for (int i = 0; i < in.length(); i++) {
        char c = in.charAt(i);
        boolean escape = true;
        if (c < 128) {
            if (asciiQueryChars.get((int) c)) {
                escape = false;
            }
        } else if (!Character.isISOControl(c) && !Character.isSpaceChar(c)) {
            // not-ascii
            escape = false;
        }
        if (!escape) {
            if (outBuf != null)
                outBuf.append(c);
        } else {
            // escape
            if (outBuf == null) {
                outBuf = new StringBuilder(in.length() + 5 * 3);
                outBuf.append(in, 0, i);
                formatter = new Formatter(outBuf);
            }
            // leading %, 0 padded, width 2, capital hex
            // TODO
            formatter.format("%%%02X", (int) c);
            formatter.close();
        }
    }
    return outBuf != null ? outBuf : in;
}


public boolean doResponseRedirectOrNotModifiedLogic(HttpServletRequest servletRequest,HttpServletResponse servletResponse,HttpResponse proxyResponse,int statusCode){
    // Check if the proxy response is a redirect
    // The following code is adapted from org.tigris.noodle.filters.CheckForRedirect
    if (statusCode >= HttpServletResponse.SC_MULTIPLE_CHOICES && /* 300 */
    statusCode < HttpServletResponse.SC_NOT_MODIFIED) /* 304 */
    {
        Header locationHeader = proxyResponse.getLastHeader(HttpHeaders.LOCATION);
        if (locationHeader == null) {
            throw new ServletException("Received status code: " + statusCode + " but no " + HttpHeaders.LOCATION + " header was found in the response");
        }
        // Modify the redirect to go to this proxy servlet rather that the proxied host
        String locStr = rewriteUrlFromResponse(servletRequest, locationHeader.getValue());
        servletResponse.sendRedirect(locStr);
        return true;
    }
    // 304 needs special handling. See:
    // http://www.ics.uci.edu/pub/ietf/http/rfc1945.html#Code304
    // We get a 304 whenever passed an 'If-Modified-Since'
    // header and the data on disk has not changed; server
    // responds w/ a 304 saying I'm not going to send the
    // body because the file has not changed.
    if (statusCode == HttpServletResponse.SC_NOT_MODIFIED) {
        servletResponse.setIntHeader(HttpHeaders.CONTENT_LENGTH, 0);
        servletResponse.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
        return true;
    }
    return false;
}


public void createBuilder(){
    // Create a factory
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    // ignore validation, dtd
    factory.setAttribute("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
    factory.setValidating(false);
    // Use document builder factory
    builder = factory.newDocumentBuilder();
}


public String rewriteUrlFromRequest(HttpServletRequest servletRequest){
    StringBuilder uri = new StringBuilder(500);
    uri.append(this.targetUri.toString());
    // Handle the query string
    /* String queryString = servletRequest.getQueryString();//ex:(following '?'): name=value&foo=bar#fragment
    if (queryString != null && queryString.length() > 0) {
      uri.append('?');
      int fragIdx = queryString.indexOf('#');
      String queryNoFrag = (fragIdx < 0 ? queryString : queryString.substring(0,fragIdx));
      uri.append(encodeUriQuery(queryNoFrag));
      if (fragIdx >= 0) {
        uri.append('#');
        uri.append(encodeUriQuery(queryString.substring(fragIdx + 1)));
      }
    }*/
    // skip this for now
    // http://giswebservices.massgis.state.ma.us/geoserver/wfs?service=wfs&version=1.0.0&request=getFeature&typename=massgis:MORIS.RFI_AIS_GT50_POLY
    // ?ogpids=MassGIS.MORIS.RFI_AIS_GT50_POLY&service=wfs&version=1.0.0&request=getFeature&typename=massgis:MORIS.RFI_AIS_GT50_POLY
    logger.info("new url string: " + uri.toString());
    return uri.toString();
}


@SuppressWarnings("deprecation")
public void doProxy(String remoteUrl,HttpServletRequest servletRequest,HttpServletResponse servletResponse){
    // Make the Request
    // note: we won't transfer the protocol version because I'm not sure it would truly be compatible
    try {
        this.targetUri = new URI(remoteUrl);
    } catch (URISyntaxException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
    }
    // Need to handle https, but think about "restricted" layers for now.  Some institutions don't really have good protection for restricted layers.  Does this open up potential for security
    // problems for those folks?
    if (servletRequest.getScheme().equals("https")) {
    // actually, what matters the most is if the remote url is https
    }
    BasicHttpEntityEnclosingRequest proxyRequest = new BasicHttpEntityEnclosingRequest(servletRequest.getMethod(), rewriteUrlFromRequest(servletRequest));
    // HttpGet httpget = new HttpGet(rewriteUrlFromRequest(servletRequest));
    copyRequestHeaders(servletRequest, proxyRequest);
    // Add the input entity (streamed) then execute the request.
    HttpResponse proxyResponse = null;
    InputStream servletRequestInputStream = servletRequest.getInputStream();
    CloseableHttpClient proxyClient = ogpHttpClient.getCloseableHttpClient();
    try {
        try {
            // proxyRequest.setEntity(new InputStreamEntity(servletRequestInputStream));
            proxyRequest.setEntity(new InputStreamEntity(servletRequestInputStream, servletRequest.getContentLength()));
            // Execute the request
            logger.debug("proxy " + servletRequest.getMethod() + " uri: " + servletRequest.getRequestURI() + " -- " + proxyRequest.getRequestLine().getUri());
            proxyResponse = proxyClient.execute(URIUtils.extractHost(targetUri), proxyRequest);
        } finally {
            IOUtils.closeQuietly(servletRequestInputStream);
        }
        // Process the response
        int statusCode = proxyResponse.getStatusLine().getStatusCode();
        logger.info("Status from remote server: " + Integer.toString(statusCode));
        if (doResponseRedirectOrNotModifiedLogic(servletRequest, servletResponse, proxyResponse, statusCode)) {
            EntityUtils.consume(proxyResponse.getEntity());
            return;
        }
        // Pass the response code. This method with the "reason phrase" is deprecated but it's the only way to pass the
        // reason along too.
        // noinspection deprecation
        servletResponse.setStatus(statusCode, proxyResponse.getStatusLine().getReasonPhrase());
        copyResponseHeaders(proxyResponse, servletResponse);
        // Send the content to the client
        copyResponseEntity(proxyResponse, servletResponse);
    } catch (Exception e) {
        // abort request, according to best practice with HttpClient
        if (proxyRequest instanceof AbortableHttpRequest) {
            AbortableHttpRequest abortableHttpRequest = (AbortableHttpRequest) proxyRequest;
            abortableHttpRequest.abort();
        }
        if (e instanceof RuntimeException)
            throw (RuntimeException) e;
        if (e instanceof ServletException)
            throw (ServletException) e;
        throw new RuntimeException(e);
    }
}


public void copyResponseEntity(HttpResponse proxyResponse,HttpServletResponse servletResponse){
    HttpEntity entity = proxyResponse.getEntity();
    if (entity != null) {
        OutputStream servletOutputStream = servletResponse.getOutputStream();
        try {
            entity.writeTo(servletOutputStream);
        } finally {
            IOUtils.closeQuietly(servletOutputStream);
        }
    }
}


@RequestMapping(value = "/wfs", method = RequestMethod.GET, params = "request=GetCapabilities")
public ModelAndView doWfsGetCapabilitiesCase(Set<String> layerIds,HttpServletRequest servletRequest,HttpServletResponse servletResponse){
    return handleGetCapabilities(layerIds, servletRequest, servletResponse);
}


public String getOgcUrlFromLayerName(String layerName,String ogcProtocol){
    SolrQuery query = new SolrQuery();
    if (layerName.contains(":")) {
        String[] arrName = layerName.split(":");
        layerName = arrName[1];
    }
    String queryText = "Name:" + layerName;
    query.setQuery(queryText);
    QueryResponse queryResponse = this.layerInfoRetriever.getSolrServer().query(query);
    List<SolrRecord> records = queryResponse.getBeans(SolrRecord.class);
    if (records.isEmpty()) {
        throw new Exception("No matching record found in Solr Index for ['" + layerName + "']");
    }
    String location = records.get(0).getLocation();
    if (ogcProtocol.equalsIgnoreCase("wfs")) {
        return LocationFieldUtils.getWfsUrl(location);
    } else if (ogcProtocol.equalsIgnoreCase("wms")) {
        return LocationFieldUtils.getWmsUrl(location);
    } else if (ogcProtocol.equalsIgnoreCase("wcs")) {
        return LocationFieldUtils.getWcsUrl(location);
    } else {
        throw new Exception("Unsupported OGC Protocol ['" + ogcProtocol + "']");
    }
}


public String extractWfsFeatureTypeNodes(Document xmlDocument,Set<String> nameList){
    String featureTypeInfo = "";
    NodeList layerNodeList = xmlDocument.getElementsByTagName("Name");
    if (layerNodeList.getLength() == 0) {
        throw new Exception("Malformed GetCapabilities Document.");
    }
    /*
		 * <FeatureType><Name>sde:GISPORTAL.GISOWNER01.AFGHANISTANRIVERREGION97</Name><Title>GISPORTAL.GISOWNER01.AFGHANISTANRIVERREGION97</Title><Abstract/><Keywords>ArcSDE, GISPORTAL.GISOWNER01.AFGHANISTANRIVERREGION97</Keywords><SRS>EPSG:100004</SRS><LatLongBoundingBox minx="60.82625305019409" miny="29.95629731861914" maxx="74.6959181471344" maxy="38.59658289704833"/></FeatureType>
		 * 
		 */
    for (int j = 0; j < layerNodeList.getLength(); j++) {
        Node currentLayerNode = layerNodeList.item(j);
        String layerName = currentLayerNode.getTextContent().toLowerCase();
        if (OgpUtils.getSetAsLowerCase(nameList).contains(layerName)) {
            featureTypeInfo += xmlToString(currentLayerNode.getParentNode());
        }
    }
    return featureTypeInfo;
}


public String rewriteUrlFromResponse(HttpServletRequest servletRequest,String theUrl){
    // TODO document example paths
    if (theUrl.startsWith(this.targetUri.toString())) {
        // no query
        String curUrl = servletRequest.getRequestURL().toString();
        String pathInfo = servletRequest.getPathInfo();
        if (pathInfo != null) {
            assert curUrl.endsWith(pathInfo);
            // take pathInfo off
            curUrl = curUrl.substring(0, curUrl.length() - pathInfo.length());
        }
        theUrl = curUrl + theUrl.substring(this.targetUri.toString().length());
    }
    return theUrl;
}


public Map<String,UrlToNameContainer> getRecordMapFromLayerIds(Set<String> layerIds){
    List<SolrRecord> solrRecords = null;
    try {
        solrRecords = this.layerInfoRetriever.fetchAllLayerInfo(layerIds);
    } catch (Exception e) {
        e.printStackTrace();
        throw new ServletException("Unable to retrieve layer info.");
    }
    // need to pass a model to the caps document
    Map<String, UrlToNameContainer> recordMap = new HashMap<String, UrlToNameContainer>();
    for (SolrRecord solrRecord : solrRecords) {
        // we have to get all of the wfs service points for the passed layerids.  match layerids to service points, so we only have to process each caps document once
        // in the future, we should cache these caps documents
        String workspaceName = solrRecord.getWorkspaceName();
        String layerName = solrRecord.getName();
        String qualifiedName = OgpUtils.getLayerNameNS(workspaceName, layerName);
        String wmsUrl = LocationFieldUtils.getWmsUrl(solrRecord.getLocation());
        URI currentURI = new URI(wmsUrl);
        // is it ok to call these equivalent?
        String currentURIString = currentURI.getScheme() + currentURI.getHost() + currentURI.getPath();
        if (recordMap.containsKey(currentURIString)) {
            UrlToNameContainer urlMap = recordMap.get(currentURIString);
            logger.info(qualifiedName);
            urlMap.qualifiedNames.add(qualifiedName);
        } else {
            UrlToNameContainer urlMap = new UrlToNameContainer();
            urlMap.ogcUrl = wmsUrl;
            Set<String> qNamesSet = new HashSet<String>();
            qNamesSet.add(qualifiedName);
            logger.info(qualifiedName);
            urlMap.qualifiedNames = qNamesSet;
            recordMap.put(currentURIString, urlMap);
        }
    }
    return recordMap;
}


@RequestMapping(value = "/wms", method = RequestMethod.GET, params = "REQUEST=GetCapabilities")
public ModelAndView doWmsGetCapabilities(Set<String> layerIds,HttpServletRequest servletRequest,HttpServletResponse servletResponse){
    logger.info("wms get capabilities requested");
    // need to pass a model to the caps document
    Map<String, UrlToNameContainer> recordMap = getRecordMapFromLayerIds(layerIds);
    // parse the returned XML
    String version = "1.1.1";
    String currentUrl = "";
    String wmsQueryBoilerPlate = "?version=" + version + "&service=wms";
    String capabilitiesQuery = "&request=GetCapabilities";
    String featureTypeInfo = "";
    CloseableHttpClient proxyClient = ogpHttpClient.getCloseableHttpClient();
    for (UrlToNameContainer container : recordMap.values()) {
        // this should happen asynchronously
        currentUrl = container.ogcUrl;
        HttpResponse response = proxyClient.execute(new HttpGet(currentUrl + wmsQueryBoilerPlate + capabilitiesQuery));
        InputStream inputStream = response.getEntity().getContent();
        Document document = this.getDocument(inputStream);
        featureTypeInfo += extractWmsLayerNodes(document, container.qualifiedNames);
    }
    String servicePoint = "";
    if (recordMap.values().size() == 1) {
        // this is a special case...
        // if every layer is from a single server, pass that server value into the caps doc for describelayer and getfeature.  that way, clients that do the right thing will bypass this ogp service
        // otherwise, everything must be proxied
        servicePoint = currentUrl;
    } else {
        // values for describelayer and getFeature should refer back to this controller
        String thisUrl = servletRequest.getRequestURL().toString() + "?";
        servicePoint = thisUrl + "ogpids=" + servletRequest.getParameter("ogpids");
    }
    ModelAndView mav = new ModelAndView("wms_caps_1_1_1");
    mav.addObject("servicePoint", StringEscapeUtils.escapeXml(servicePoint));
    mav.addObject("featureTypeInfo", featureTypeInfo);
    servletResponse.setHeader("Content-Disposition", "inline;filename=GetCapabilities.xml");
    return mav;
}


public ModelAndView handleGetCapabilities(Set<String> layerIds,HttpServletRequest servletRequest,HttpServletResponse servletResponse){
    logger.info("wfs get capabilities requested");
    Map<String, UrlToNameContainer> recordMap = getRecordMapFromLayerIds(layerIds);
    // parse the returned XML
    String version = "1.0.0";
    String currentUrl = "";
    String wfsQueryBoilerPlate = "?version=" + version + "&service=wfs";
    String capabilitiesQuery = "&request=GetCapabilities";
    String featureTypeInfo = "";
    CloseableHttpClient proxyClient = ogpHttpClient.getCloseableHttpClient();
    for (UrlToNameContainer container : recordMap.values()) {
        // this should happen asynchronously?
        currentUrl = container.ogcUrl;
        HttpResponse response = proxyClient.execute(new HttpGet(currentUrl + wfsQueryBoilerPlate + capabilitiesQuery));
        InputStream inputStream = response.getEntity().getContent();
        Document document = this.getDocument(inputStream);
        Set<String> nameList = container.qualifiedNames;
        featureTypeInfo += this.extractWfsFeatureTypeNodes(document, nameList);
    }
    String onlineResource = "";
    String describeFeatureUrl = "";
    String getFeatureUrl = "";
    if (recordMap.values().size() == 1) {
        // this is a special case...
        // if every layer is from a single server, pass that server value into the caps doc for describelayer and getfeature.  that way, clients that do the right thing will bypass this ogp service
        // otherwise, everything must be proxied
        onlineResource = currentUrl;
        describeFeatureUrl = currentUrl + wfsQueryBoilerPlate + "&request=DescribeFeatureType";
        getFeatureUrl = currentUrl + wfsQueryBoilerPlate + "&request=GetFeature";
    } else {
        // values for describelayer and getFeature should refer back to this controller
        String thisUrl = servletRequest.getRequestURL().toString() + "?";
        onlineResource = thisUrl + "ogpids=" + servletRequest.getParameter("ogpids");
        describeFeatureUrl = thisUrl + "request=DescribeFeatureType";
        getFeatureUrl = thisUrl + "request=GetFeature";
    }
    // create the model to return
    ModelAndView mav = new ModelAndView("wfs_caps_1_0_0");
    mav.addObject("onlineResource", StringEscapeUtils.escapeXml(onlineResource));
    mav.addObject("getCapabilities", StringEscapeUtils.escapeXml(servletRequest.getRequestURL().toString() + "?" + servletRequest.getQueryString()));
    mav.addObject("describeFeatureUrl", StringEscapeUtils.escapeXml(describeFeatureUrl));
    mav.addObject("getFeatureUrl", StringEscapeUtils.escapeXml(getFeatureUrl));
    mav.addObject("featureTypeInfo", featureTypeInfo);
    servletResponse.setHeader("Content-Disposition", "inline;filename=GetCapabilities.xml");
    return mav;
}


public String extractWmsLayerNodes(Document xmlDocument,Set<String> nameList){
    String featureTypeInfo = "";
    NodeList layerNodeList = xmlDocument.getElementsByTagName("Name");
    if (layerNodeList.getLength() == 0) {
        throw new Exception("Malformed GetCapabilities Document.");
    }
    for (int j = 0; j < layerNodeList.getLength(); j++) {
        Node currentLayerNode = layerNodeList.item(j);
        if (currentLayerNode.getParentNode().getNodeName().equals("Layer")) {
            String layerName = currentLayerNode.getTextContent().toLowerCase();
            if (OgpUtils.getSetAsLowerCase(nameList).contains(layerName)) {
                featureTypeInfo += xmlToString(currentLayerNode.getParentNode());
            }
        }
    }
    return featureTypeInfo;
}


}
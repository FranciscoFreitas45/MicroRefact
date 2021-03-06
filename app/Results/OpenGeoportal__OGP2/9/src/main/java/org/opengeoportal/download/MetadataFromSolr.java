package org.opengeoportal.download;
 import java.util;
import java.io;
import javax.xml.parsers;
import javax.xml.transform;
import javax.xml.transform.dom;
import javax.xml.transform.stream;
import org.xml.sax;
import org.apache.commons.io.IOUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.common.SolrDocumentList;
import org.opengeoportal.metadata.LayerInfoRetriever;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.w3c.dom;
import org.opengeoportal.Interface.LayerInfoRetriever;
import org.opengeoportal.DTO.LayerInfoRetriever;
public class MetadataFromSolr implements MetadataRetriever{

@Autowired
 private  LayerInfoRetriever layerInfoRetriever;

 private  String layerId;

 private  Document xmlDocument;

 private  DocumentBuilder builder;

 private  Resource fgdcStyleSheet;

 private  Resource iso19139StyleSheet;

MetadataFromSolr() {
    // Create a factory
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    // dtd isn't always available; would be nice to attempt to validate
    factory.setValidating(false);
    try {
        factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
    } catch (ParserConfigurationException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
    }
    // Use document builder factory
    try {
        builder = factory.newDocumentBuilder();
    } catch (ParserConfigurationException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
}
public NodeList getContactNodeList(String layerID){
    String xmlString = this.getXMLStringFromSolr(layerID, "LayerId");
    Document document = buildXMLDocFromString(layerID, xmlString);
    NodeList contactInfo = document.getElementsByTagName("ptcontac");
    for (int i = 0; i < contactInfo.getLength(); i++) {
        Node currentNode = contactInfo.item(i);
        if (currentNode.getNodeName().equalsIgnoreCase("cntinfo")) {
            return currentNode.getChildNodes();
        }
    }
    return null;
}


@Override
public String getMetadataAsHtml(String layerID){
    String xmlString = this.getXMLStringFromSolr(layerID, "LayerId");
    Document document = null;
    try {
        document = buildXMLDocFromString(layerId, xmlString);
    } catch (ParserConfigurationException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (SAXException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    // get the metadata type and correct xslt document
    Source xmlSource = new DOMSource(document);
    StringWriter stringWriter = new StringWriter();
    StreamResult streamResult = new StreamResult(stringWriter);
    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    Source xslt = new StreamSource(getStyleSheet(document));
    Transformer transformer = transformerFactory.newTransformer(xslt);
    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    transformer.transform(xmlSource, streamResult);
    String outputString = stringWriter.toString();
    return outputString;
}


public String getContactPhoneNumber(String layerID){
    return this.getContactInfo(layerID, "cntvoice").getNodeValue().trim();
}


public Resource getFgdcStyleSheet(){
    return fgdcStyleSheet;
}


public void setFgdcStyleSheet(Resource fgdcStyleSheet){
    this.fgdcStyleSheet = fgdcStyleSheet;
}


public Document buildXMLDocFromString(String layerId,String rawXMLString){
    if ((layerId.equalsIgnoreCase(this.layerId)) && (this.xmlDocument != null)) {
        return this.xmlDocument;
    } else {
        InputStream xmlInputStream = null;
        try {
            // parse the returned XML to make sure it is well-formed & to format
            // filter extra spaces from xmlString
            rawXMLString = rawXMLString.replaceAll(">[ \t\n\r\f]+<", "><").replaceAll("[\t\n\r\f]+", "");
            xmlInputStream = new ByteArrayInputStream(rawXMLString.getBytes("UTF-8"));
            // Parse the document
            Document document = builder.parse(xmlInputStream);
            return document;
        } finally {
            IOUtils.closeQuietly(xmlInputStream);
        }
    }
}


public Resource getIso19139StyleSheet(){
    return iso19139StyleSheet;
}


public File getStyleSheet(Document document){
    MetadataType metadataType = getMetadataType(document);
    // getMetadataType throws an exception if not fgdc or iso19139
    if (metadataType.equals(MetadataType.FGDC)) {
        return this.getFgdcStyleSheet().getFile();
    } else {
        return this.getIso19139StyleSheet().getFile();
    }
}


public String getContactAddress(String layerID){
    Node addressNode = this.getContactInfo(layerID, "cntaddr");
    NodeList addressNodeList = addressNode.getChildNodes();
    String address = "";
    String city = "";
    String state = "";
    String postal = "";
    String country = "";
    for (int i = 0; i < addressNodeList.getLength(); i++) {
        Node currentAddressLine = addressNodeList.item(i);
        if (currentAddressLine.getNodeName().equalsIgnoreCase("address")) {
            address += currentAddressLine.getNodeValue().trim() + ", ";
        } else if (currentAddressLine.getNodeName().equalsIgnoreCase("state")) {
            state = "";
            state += currentAddressLine.getNodeValue().trim();
        } else if (currentAddressLine.getNodeName().equalsIgnoreCase("postal")) {
            postal = "";
            postal += currentAddressLine.getNodeValue().trim();
        } else if (currentAddressLine.getNodeName().equalsIgnoreCase("country")) {
            country = "";
            country += currentAddressLine.getNodeValue().trim();
        } else if (currentAddressLine.getNodeName().equalsIgnoreCase("city")) {
            city = "";
            city += currentAddressLine.getNodeValue().trim();
        }
    }
    String fullAddress = "";
    if (!address.isEmpty()) {
        fullAddress += address;
    }
    if (!city.isEmpty()) {
        fullAddress += city + ",";
    }
    if (!state.isEmpty()) {
        fullAddress += state + " ";
    }
    if (!postal.isEmpty()) {
        fullAddress += postal;
    }
    if (!country.isEmpty()) {
        fullAddress += ", " + country;
    }
    return fullAddress;
}


@Override
public String getXMLStringFromId(String layerID,String xmlFormat){
    String xmlString = this.getXMLStringFromSolr(layerID, "LayerId");
    xmlString = this.filterXMLString(layerID, xmlString);
    return xmlString;
}


public File getXMLFile(String metadataLayerName,File xmlFile){
    OutputStream xmlFileOutputStream = null;
    try {
        String xmlString = this.getXMLStringFromSolr(metadataLayerName, "Name");
        xmlString = this.filterXMLString("", xmlString);
        // write this string to a file
        xmlFileOutputStream = new FileOutputStream(xmlFile);
        new PrintStream(xmlFileOutputStream).print(xmlString);
        return xmlFile;
    } finally {
        IOUtils.closeQuietly(xmlFileOutputStream);
    }
}


public String getContactName(String layerID){
    String contactName = this.getContactInfo(layerID, "cntpos").getNodeValue().trim();
    return contactName;
}


public String getXMLStringFromSolr(String identifier,String descriptor){
    Map<String, String> conditionsMap = new HashMap<String, String>();
    if (descriptor.equalsIgnoreCase("name")) {
        int i = identifier.indexOf(":");
        if (i > 0) {
            // String workSpace = layerName.substring(0, i);
            // conditionsMap.put("WorkspaceName", workSpace);
            identifier = identifier.substring(i + 1);
        }
        conditionsMap.put("Name", identifier);
        this.layerId = null;
    } else if (descriptor.equalsIgnoreCase("layerid")) {
        conditionsMap.put("LayerId", identifier);
        this.layerId = identifier;
    } else {
        this.layerId = null;
        return null;
    }
    SolrQuery query = new SolrQuery();
    query.setQuery(descriptor + ":" + identifier);
    query.addField("FgdcText");
    query.setRows(1);
    SolrDocumentList docs = this.layerInfoRetriever.getSolrServer().query(query).getResults();
    return (String) docs.get(0).getFieldValue("FgdcText");
}


public MetadataType getMetadataType(Document document){
    MetadataType metadataType = null;
    try {
        // <metstdn>FGDC Content Standards for Digital Geospatial Metadata
        // <metstdv>FGDC-STD-001-1998
        NodeList rootNodes = document.getElementsByTagName("metadata");
        if (rootNodes.getLength() > 0) {
            // if (document.getElementsByTagName("metstdn").item(0).getTextContent().toLowerCase().contains("fgdc")){
            metadataType = MetadataType.FGDC;
        // }
        }
    } catch (Exception e) {
    /*ignore*/
    // document.getElementsByTagName("metstdn").item(0).getTextContent().toLowerCase();
    }
    try {
        NodeList rootNodes = document.getElementsByTagNameNS("*", "MD_Metadata");
        NodeList altRootNodes = document.getElementsByTagNameNS("*", "MI_Metadata");
        int totalNodes = rootNodes.getLength() + altRootNodes.getLength();
        if (totalNodes > 0) {
            metadataType = MetadataType.ISO_19139;
        }
    } catch (Exception e) {
    /*ignore*/
    }
    if (metadataType == null) {
        // throw an exception...metadata type is not supported
        throw new Exception("Metadata Type is not supported.");
    }
    return metadataType;
}


public void setIso19139StyleSheet(Resource iso19139StyleSheet){
    this.iso19139StyleSheet = iso19139StyleSheet;
}


public Node getContactInfo(String layerID,String nodeName){
    NodeList contactInfo = null;
    try {
        contactInfo = this.getContactNodeList(layerID);
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (ParserConfigurationException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (SAXException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    for (int i = 0; i < contactInfo.getLength(); i++) {
        Node currentNode = contactInfo.item(i);
        if (currentNode.getNodeName().equalsIgnoreCase(nodeName)) {
            return currentNode;
        }
    }
    return null;
}


public String filterXMLString(String layerId,String rawXMLString){
    Document document = null;
    try {
        document = buildXMLDocFromString(layerId, rawXMLString);
    } catch (ParserConfigurationException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (SAXException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    Source xmlSource = new DOMSource(document);
    StringWriter stringWriter = new StringWriter();
    StreamResult streamResult = new StreamResult(stringWriter);
    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    Transformer transformer = transformerFactory.newTransformer();
    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    transformer.transform(xmlSource, streamResult);
    String outputString = stringWriter.toString();
    return outputString;
}


}
package org.opengeoportal.download;
 import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.opengeoportal.download.config.DownloadConfigRetriever;
import org.opengeoportal.download.types.LayerRequest;
import org.opengeoportal.layer.GeometryType;
import org.opengeoportal.solr.SolrRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;
public class LayerDownloaderProvider implements BeanFactoryAware{

 protected  BeanFactory beanFactory;

 final  Logger logger;

 final  Logger slogger;

@Autowired
 private  DownloadConfigRetriever downloadConfigRetriever;

 private  JsonNode downloadConfig;


@PostConstruct
public void init(){
    downloadConfig = downloadConfigRetriever.getDownloadConfig().path("institutions");
}


public String getGeneralizedDataType(String dataType){
    GeometryType geometryType = GeometryType.parseGeometryType(dataType);
    if (geometryType == GeometryType.PaperMap || geometryType == GeometryType.ScannedMap) {
        return "paper map";
    }
    String generalizedDataType = geometryType.getGeneralizedDataTypeString().toLowerCase();
    return generalizedDataType;
}


public String getClassKey(LayerRequest layer){
    JsonNode institutions = getDownloadConfig();
    SolrRecord record = layer.getLayerInfo();
    JsonNode methods = institutions.path(record.getInstitution().trim().toLowerCase());
    if (!methods.isArray()) {
        try {
            return this.getDefaultDownloadKey(institutions, layer);
        } catch (Exception e) {
            logger.error("Class Key not defined for this layer.");
            throw new Exception("Class Key not defined for this layer.");
        }
    }
    ArrayNode methodArray = (ArrayNode) methods;
    Iterator<JsonNode> institutionIterator = methodArray.elements();
    String classKey = null;
    while (institutionIterator.hasNext()) {
        JsonNode currentNode = institutionIterator.next();
        Boolean match = matchAllCriteria(currentNode, layer);
        // If the match is complete, don't look any further
        if (match) {
            classKey = currentNode.path("classKey").textValue();
            logger.debug("match");
            break;
        }
    }
    if (classKey == null) {
        logger.debug("class key is null. should mean no match");
        // no match was found in download config, so we're trying defaults
        try {
            return this.getDefaultDownloadKey(institutions, layer);
        } catch (Exception e) {
            logger.error("Class Key not defined for this layer.");
            throw new Exception("Class Key not defined for this layer.");
        }
    } else {
        logger.debug("should get here on match");
        // make sure the layer has the requirements for the match
        if (hasRequirements(classKey, layer)) {
            return classKey;
        } else {
            try {
                return this.getDefaultDownloadKey(institutions, layer);
            } catch (Exception e) {
                logger.error("Class Key not defined for this layer.");
                throw new Exception("Class Key not defined for this layer.");
            }
        }
    }
}


public DownloadConfigRetriever getDownloadConfigRetriever(){
    return downloadConfigRetriever;
}


public LayerDownloader getLayerDownloader(String downloaderKey){
    LayerDownloader layerDownloader = (LayerDownloader) beanFactory.getBean(downloaderKey);
    if (layerDownloader == null) {
        throw new NullPointerException("LayerDownloader could not be retrieved");
    }
    return layerDownloader;
}


public void setDownloadConfig(JsonNode downloadConfig){
    this.downloadConfig = downloadConfig;
}


public void setBeanFactory(BeanFactory beanFactory){
    this.beanFactory = beanFactory;
}


public Boolean hasRequirements(String classKey,LayerRequest layer){
    // query the download method to see if it has the info it needs
    LayerDownloader testDownloader = getLayerDownloader(classKey);
    Boolean hasInfo = testDownloader.hasRequiredInfo(layer);
    return hasInfo;
}


public void setDownloadConfigRetriever(DownloadConfigRetriever downloadConfigRetriever){
    this.downloadConfigRetriever = downloadConfigRetriever;
}


public Boolean matchAllCriteria(JsonNode currentNode,LayerRequest layer){
    SolrRecord record = layer.getLayerInfo();
    logger.debug(record.getAccess().toLowerCase());
    logger.debug(record.getDataType().toLowerCase());
    logger.debug(layer.getRequestedFormat().toLowerCase());
    logger.debug("trying access matches...");
    Boolean accessMatch = matchNode(currentNode, "accessLevel", record.getAccess().toLowerCase());
    if (!accessMatch) {
        return false;
    }
    logger.debug("access match");
    Boolean dataTypeMatch = matchNode(currentNode, "dataType", getGeneralizedDataType(record.getDataType().toLowerCase()));
    if (!dataTypeMatch) {
        return false;
    }
    logger.debug("data type match");
    Boolean outputFormatMatch = matchNode(currentNode, "outputFormats", layer.getRequestedFormat().toLowerCase());
    if (!outputFormatMatch) {
        return false;
    }
    logger.debug("requested format match");
    return true;
}


public JsonNode getDownloadConfig(){
    return downloadConfig;
}


public Boolean matchNode(JsonNode parentNode,String path,String value){
    Boolean match = false;
    JsonNode matchNode = parentNode.path(path);
    if (matchNode.isArray()) {
        ArrayNode arrayNode = (ArrayNode) matchNode;
        Iterator<JsonNode> iterator = arrayNode.iterator();
        while (iterator.hasNext()) {
            JsonNode currentNode = iterator.next();
            slogger.debug("node value:" + currentNode.textValue());
            if (currentNode.textValue().equalsIgnoreCase(value.trim())) {
                match = true;
            }
        }
    } else if (matchNode.isTextual()) {
        slogger.debug("text node");
    } else if (matchNode.isMissingNode()) {
        slogger.debug("Node at path ['" + path + "'] does not exist.");
    }
    slogger.debug("path: " + path + ", value: " + value + " match: " + match.toString());
    return match;
}


public Boolean isDownloadable(LayerRequest layer){
    Boolean downloadable = false;
    try {
        this.getClassKey(layer);
        downloadable = true;
    } catch (Exception e) {
        logger.warn(e.getMessage());
    }
    return downloadable;
}


public LayerDownloader build(String classKey){
    return getLayerDownloader(classKey);
}


public String getDefaultDownloadKey(JsonNode institutions,LayerRequest layer){
    logger.info("Looking for default method...");
    JsonNode defaultNode = institutions.path("default");
    if (!defaultNode.isArray()) {
        throw new Exception("No default defined!");
    }
    ArrayNode jsonArray = (ArrayNode) defaultNode;
    Iterator<JsonNode> iterator = jsonArray.elements();
    SortedMap<Integer, String> classKeys = new TreeMap<Integer, String>();
    while (iterator.hasNext()) {
        JsonNode currentNode = iterator.next();
        Boolean outputFormatMatch = matchNode(currentNode, "outputFormats", layer.getRequestedFormat().toLowerCase());
        if (outputFormatMatch) {
            String classKey = currentNode.path("classKey").textValue();
            Integer preference = currentNode.path("preference").intValue();
            logger.debug("Matched classKey: " + classKey);
            if (hasRequirements(classKey, layer)) {
                logger.debug("requirements met: " + classKey);
                classKeys.put(preference, classKey);
            } else {
                logger.debug("requirements not met for: " + classKey);
            }
        }
    }
    if (!classKeys.isEmpty()) {
        String bestMatch = classKeys.get(classKeys.firstKey());
        logger.debug("Trying preferred match..." + bestMatch);
        return bestMatch;
    } else {
        String errMsg = "No applicable default download method found.";
        logger.error(errMsg);
        throw new Exception(errMsg);
    }
}


}
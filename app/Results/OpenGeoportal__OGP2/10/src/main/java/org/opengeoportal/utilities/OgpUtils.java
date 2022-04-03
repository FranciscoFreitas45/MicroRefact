package org.opengeoportal.utilities;
 import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.opengeoportal.solr.SolrRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class OgpUtils {

 final  Logger logger;


public String referencedEnvelopeToString(ReferencedEnvelope env){
    String envString = doubleToString(env.getMinX()) + ",";
    envString += doubleToString(env.getMinY()) + ",";
    envString += doubleToString(env.getMaxX()) + ",";
    envString += doubleToString(env.getMaxY());
    return envString;
}


public Set<String> getSetAsLowerCase(Set<String> mixedCaseSet){
    Set<String> lcSet = new HashSet<String>();
    for (String name : mixedCaseSet) {
        lcSet.add(name.toLowerCase());
    }
    return lcSet;
}


public SolrRecord findRecordById(String layerId,List<SolrRecord> recordList){
    for (SolrRecord sr : recordList) {
        if (sr.getLayerId().equals(layerId)) {
            return sr;
        }
    }
    throw new Exception("Record not found.");
}


public String getLayerNameNS(String workspaceName,String layerName){
    workspaceName = workspaceName.trim();
    layerName = layerName.trim();
    String embeddedWSName = "";
    if (layerName.contains(":")) {
        String[] layerNameArr = layerName.split(":");
        if (layerNameArr.length > 2) {
            throw new Exception("Invalid layer name ['" + layerName + "']");
        }
        embeddedWSName = layerNameArr[0];
        layerName = layerNameArr[1];
    }
    if (!workspaceName.isEmpty()) {
        // prefer the explicit workspaceName?
        return workspaceName + ":" + layerName;
    } else {
        if (embeddedWSName.isEmpty()) {
            return layerName;
        } else {
            return embeddedWSName + ":" + layerName;
        }
    }
}


public String doubleToString(Double value){
    logger.info(Double.toString(value));
    BigDecimal valDec = new BigDecimal(value);
    String valString = valDec.setScale(7, RoundingMode.HALF_UP).toPlainString();
    return valString;
}


public Boolean containsIgnoreCase(Collection<String> stringCollection,String testString){
    for (String curr : stringCollection) {
        if (curr.equalsIgnoreCase(testString)) {
            return true;
        }
    }
    return false;
}


public Boolean isWellFormedEmailAddress(String emailAddress){
    // a very basic check of the email address
    emailAddress = emailAddress.trim();
    if (emailAddress.contains(" ")) {
        return false;
    }
    String[] arr = emailAddress.split("@");
    if (arr.length != 2) {
        return false;
    }
    if (!arr[1].contains(".")) {
        return false;
    }
    return true;
}


public String combinePathWithQuery(String path,String requestString){
    if (requestString.startsWith("?")) {
        requestString = requestString.substring(requestString.indexOf("?"));
    }
    if (path.endsWith("?")) {
        path = path.substring(0, path.indexOf("?"));
    }
    int count = StringUtils.countMatches(path, "?");
    if (count == 0) {
    // we're good
    } else if (count == 1) {
        // there are some embedded params
        String[] urlArr = path.split("\\?");
        path = urlArr[0];
        List<String> embeddedParams = new ArrayList<String>(Arrays.asList(urlArr[1].split("\\&")));
        List<String> queryParams = new ArrayList<String>(Arrays.asList(requestString.split("\\&")));
        List<String> duplicates = new ArrayList<String>();
        for (String mParam : embeddedParams) {
            String mKey = mParam.split("=")[0];
            for (String qParam : queryParams) {
                String qKey = qParam.split("=")[0];
                if (mKey.equalsIgnoreCase(qKey)) {
                    duplicates.add(mParam);
                }
            }
        }
        if (!duplicates.isEmpty()) {
            embeddedParams.removeAll(duplicates);
        }
        if (!embeddedParams.isEmpty()) {
            queryParams.addAll(embeddedParams);
        }
        requestString = StringUtils.join(queryParams, "&");
    } else if (count > 1) {
        // something's really wrong here, or the path has parameters embedded in the path
        throw new MalformedURLException("This path is problematic: ['" + path + "']");
    }
    String combined = path + "?" + requestString;
    logger.info("Combined URL: " + combined);
    return combined;
}


public String filterQueryString(String url){
    if (url.contains("?")) {
        // can happen with generic ows endpoint
        // get rid of everything after the query param
        url = url.substring(0, url.indexOf("?"));
    }
    return url;
}


}
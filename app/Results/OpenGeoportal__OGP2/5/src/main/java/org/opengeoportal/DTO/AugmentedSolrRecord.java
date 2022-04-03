package org.opengeoportal.DTO;
 import java.util.ArrayList;
import java.util.List;
import org.opengeoportal.solr.SolrRecord;
public class AugmentedSolrRecord {

 private List<OwsInfo> owsInfo;

 private SolrRecord solrRecord;


public SolrRecord getSolrRecord(){
    return solrRecord;
}


public List<OwsInfo> getOwsInfo(){
    return owsInfo;
}


}
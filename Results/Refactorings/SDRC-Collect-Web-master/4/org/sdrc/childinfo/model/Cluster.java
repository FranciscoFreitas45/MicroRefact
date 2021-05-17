import java.sql.Timestamp;
public class Cluster {

 private  int clusterId;

 private  String clusterCode;

 private  String clusterName;

 private  String lastUpdatedBy;

 private  Timestamp lastUpdatedDate;

 private  ValueObject samikshyaBlock;


public String getClusterCode(){
    return clusterCode;
}


public ValueObject getSamikshyaBlock(){
    return samikshyaBlock;
}


public int getClusterId(){
    return clusterId;
}


public String getClusterName(){
    return clusterName;
}


public void setLastUpdatedDate(Timestamp lastUpdatedDate){
    this.lastUpdatedDate = lastUpdatedDate;
}


public void setLastUpdatedBy(String lastUpdatedBy){
    this.lastUpdatedBy = lastUpdatedBy;
}


public String getLastUpdatedBy(){
    return lastUpdatedBy;
}


public void setClusterId(int clusterId){
    this.clusterId = clusterId;
}


public void setClusterCode(String clusterCode){
    this.clusterCode = clusterCode;
}


public void setSamikshyaBlock(ValueObject samikshyaBlock){
    this.samikshyaBlock = samikshyaBlock;
}


public void setClusterName(String clusterName){
    this.clusterName = clusterName;
}


public Timestamp getLastUpdatedDate(){
    return lastUpdatedDate;
}


}
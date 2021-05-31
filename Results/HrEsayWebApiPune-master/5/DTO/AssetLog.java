import javax.persistence;
import java.util.Date;
public class AssetLog {

 private  int assetLogId;

 private  int assetId;

 private  int assetTransId;

 private  String assetLogDesc;

 private  Date assetLogDate;

 private  int makerUserId;

 private  String updateDateTime;

 private  int delStatus;

 private  int exInt1;

 private  int exInt2;

 private  int exVar1;

 private  int exVar2;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://23";


public int getExVar2(){
    return exVar2;
}


public int getAssetLogId(){
    return assetLogId;
}


public int getExInt2(){
    return exInt2;
}


public int getExInt1(){
    return exInt1;
}


public int getExVar1(){
    return exVar1;
}


public String getAssetLogDesc(){
    return assetLogDesc;
}


public int getMakerUserId(){
    return makerUserId;
}


public int getAssetTransId(){
    return assetTransId;
}


public String getUpdateDateTime(){
    return updateDateTime;
}


public Date getAssetLogDate(){
    return assetLogDate;
}


public int getDelStatus(){
    return delStatus;
}


public int getAssetId(){
    return assetId;
}


public void setAssetLogId(int assetLogId){
    this.assetLogId = assetLogId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ assetLogId).concat("/setAssetLogId"));

.queryParam("assetLogId",assetLogId);
restTemplate.put(builder.toUriString(),null);
}


public void setAssetId(int assetId){
    this.assetId = assetId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ assetLogId).concat("/setAssetId"));

.queryParam("assetId",assetId);
restTemplate.put(builder.toUriString(),null);
}


public void setAssetLogDate(Date assetLogDate){
    this.assetLogDate = assetLogDate;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ assetLogId).concat("/setAssetLogDate"));

.queryParam("assetLogDate",assetLogDate);
restTemplate.put(builder.toUriString(),null);
}


public void setAssetLogDesc(String assetLogDesc){
    this.assetLogDesc = assetLogDesc;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ assetLogId).concat("/setAssetLogDesc"));

.queryParam("assetLogDesc",assetLogDesc);
restTemplate.put(builder.toUriString(),null);
}


public void setAssetTransId(int assetTransId){
    this.assetTransId = assetTransId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ assetLogId).concat("/setAssetTransId"));

.queryParam("assetTransId",assetTransId);
restTemplate.put(builder.toUriString(),null);
}


public void setDelStatus(int delStatus){
    this.delStatus = delStatus;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ assetLogId).concat("/setDelStatus"));

.queryParam("delStatus",delStatus);
restTemplate.put(builder.toUriString(),null);
}


public void setMakerUserId(int makerUserId){
    this.makerUserId = makerUserId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ assetLogId).concat("/setMakerUserId"));

.queryParam("makerUserId",makerUserId);
restTemplate.put(builder.toUriString(),null);
}


public void setUpdateDateTime(String updateDateTime){
    this.updateDateTime = updateDateTime;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ assetLogId).concat("/setUpdateDateTime"));

.queryParam("updateDateTime",updateDateTime);
restTemplate.put(builder.toUriString(),null);
}


}
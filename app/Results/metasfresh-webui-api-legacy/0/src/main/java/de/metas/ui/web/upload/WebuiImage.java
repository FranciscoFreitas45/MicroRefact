package de.metas.ui.web.upload;
 import org.adempiere.service.ClientId;
import org.compiere.model.MImage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import com.google.common.collect.ImmutableMap;
import de.metas.organization.OrgId;
import de.metas.ui.web.cache.ETag;
import de.metas.ui.web.cache.ETagAware;
import lombok.NonNull;
public class WebuiImage implements ETagAware{

 private  MImage adImage;

 private  int maxWidth;

 private  int maxHeight;

 private  ETag etag;


public ClientId getAdClientId(){
    return ClientId.ofRepoId(adImage.getAD_Client_ID());
}


public byte[] getImageData(){
    return adImage.getScaledImageData(maxWidth, maxHeight);
}


public String getImageName(){
    return adImage.getName();
}


public int getAdImageId(){
    return adImage.getAD_Image_ID();
}


@Override
public ETag getETag(){
    return etag;
}


public WebuiImage of(MImage adImage,int maxWidth,int maxHeight){
    return new WebuiImage(adImage, maxWidth, maxHeight);
}


public String getContentType(){
    return adImage.getContentType();
}


public OrgId getAdOrgId(){
    return OrgId.ofRepoId(adImage.getAD_Org_ID());
}


public ResponseEntity<byte[]> toResponseEntity(ResponseEntity.BodyBuilder responseBuilder){
    return responseBuilder.contentType(MediaType.parseMediaType(getContentType())).header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + getImageName() + "\"").body(getImageData());
}


}
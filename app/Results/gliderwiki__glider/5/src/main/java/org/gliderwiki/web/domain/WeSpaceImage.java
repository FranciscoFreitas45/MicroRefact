package org.gliderwiki.web.domain;
 import java.awt.Image;
import java.io.Serializable;
import java.util.Date;
import javax.swing.ImageIcon;
import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
import com.google.common.base.Preconditions;
@Table("WE_SPACE_IMAGE")
public class WeSpaceImage implements Serializable{

@Column(name = "we_space_image_idx", primaryKey = true, autoIncrement = true)
 private  Integer we_space_image_idx;

@Column(name = "we_space_idx")
 private  Integer we_space_idx;

@Column(name = "we_img_path")
 private  String we_img_path;

@Column(name = "we_img_name")
 private  String we_img_name;

@Column(name = "we_img_width_size")
 private  Integer we_img_width_size;

@Column(name = "we_img_height_size")
 private  Integer we_img_height_size;

@Column(name = "we_used")
 private  boolean we_used;

@Column(name = "we_ins_date")
 private  Date we_ins_date;

public WeSpaceImage() {
}public WeSpaceImage(Integer we_space_image_idx, Integer we_space_idx, String we_img_path, String we_img_name, Integer we_img_width_size, Integer we_img_height_size) {
    this.we_space_image_idx = we_space_image_idx;
    this.we_space_idx = we_space_idx;
    this.we_img_path = we_img_path;
    this.we_img_name = we_img_name;
    this.we_img_width_size = we_img_width_size;
    this.we_img_height_size = we_img_height_size;
    this.we_used = true;
    this.we_ins_date = new Date();
}public WeSpaceImage(Integer we_space_idx) {
    this.we_space_idx = we_space_idx;
    this.we_used = true;
    this.we_ins_date = new Date();
}public WeSpaceImage(Integer we_space_idx, boolean we_used) {
    this.we_space_idx = we_space_idx;
    this.we_used = we_used;
}
public void setWe_space_idx(Integer we_space_idx){
    this.we_space_idx = we_space_idx;
}


public Integer getWe_space_idx(){
    return we_space_idx;
}


public String getImageName(String fullImagName){
    Preconditions.checkNotNull(fullImagName, "이미지 경로에 대한 정보가 필요합니다.");
    return fullImagName.substring(fullImagName.lastIndexOf('/') + 1, fullImagName.length());
}


public void setWe_used(boolean we_used){
    this.we_used = we_used;
}


public String getWe_img_path(){
    return we_img_path;
}


public boolean isWe_used(){
    return we_used;
}


public String getRealImagePath(){
    return this.we_img_path + "/" + this.we_img_name;
}


public void setWe_img_height_size(Integer we_img_height_size){
    this.we_img_height_size = we_img_height_size;
}


public Integer getWe_space_image_idx(){
    return we_space_image_idx;
}


public void setWe_space_image_idx(Integer we_space_image_idx){
    this.we_space_image_idx = we_space_image_idx;
}


public void setWe_img_name(String we_img_name){
    this.we_img_name = we_img_name;
}


public void setWe_img_path(String we_img_path){
    this.we_img_path = we_img_path;
}


public Date getWe_ins_date(){
    return we_ins_date;
}


public Integer getWe_img_width_size(){
    return we_img_width_size;
}


public void setWe_ins_date(Date we_ins_date){
    this.we_ins_date = we_ins_date;
}


public void processImageInfo(ImageInfo imageInfo,String fullImagName){
    this.we_img_path = getImagePath(fullImagName);
    this.we_img_name = getImageName(fullImagName);
    ImageInfo imgInfo = processImage(imageInfo, fullImagName);
    this.we_img_width_size = imgInfo.getWidthSize();
    this.we_img_height_size = imgInfo.getHeightSize();
}


public Integer getWe_img_height_size(){
    return we_img_height_size;
}


public String getImagePath(String fullImagName){
    Preconditions.checkNotNull(fullImagName, "이미지 경로에 대한 정보가 필요합니다.");
    return fullImagName.substring(0, fullImagName.lastIndexOf('/'));
}


public String getWe_img_name(){
    return we_img_name;
}


public ImageInfo processImage(ImageInfo imageInfo,String fileName){
    Image image = new ImageIcon(imageInfo.getRealImgRoot() + fileName).getImage();
    ImageInfo realFileImageInfo = new ImageInfo(image.getWidth(null), image.getHeight(null));
    return realFileImageInfo;
}


public void setWe_img_width_size(Integer we_img_width_size){
    this.we_img_width_size = we_img_width_size;
}


}
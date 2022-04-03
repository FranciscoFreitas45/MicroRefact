package org.gliderwiki.web.domain;
 public class ImageInfo {

 private String tempImgRoot;

 private String realImgRoot;

 private int widthSize;

 private int heightSize;

public ImageInfo() {
}public ImageInfo(String tempImgRoot, String realImgRoot) {
    this.tempImgRoot = tempImgRoot;
    this.realImgRoot = realImgRoot;
}public ImageInfo(int widthSize, int heightSize) {
    this.widthSize = widthSize;
    this.heightSize = heightSize;
}
public void setHeightSize(int heightSize){
    this.heightSize = heightSize;
}


public void setRealImgRoot(String realImgRoot){
    this.realImgRoot = realImgRoot;
}


public void setTempImgRoot(String tempImgRoot){
    this.tempImgRoot = tempImgRoot;
}


public String getTempImgRoot(){
    return tempImgRoot;
}


public int getWidthSize(){
    return widthSize;
}


public void setWidthSize(int widthSize){
    this.widthSize = widthSize;
}


public int getHeightSize(){
    return heightSize;
}


public String getRealImgRoot(){
    return realImgRoot;
}


}
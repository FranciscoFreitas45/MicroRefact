package net.coobird.thumbnailator.tasks.io;
 import java.awt.image.BufferedImage;
import java.io.IOException;
import net.coobird.thumbnailator.ThumbnailParameter;
public class AbstractImageSink implements ImageSink<T>{

 protected  String outputFormat;

 protected  ThumbnailParameter param;

/**
 * Default constructor.
 */
protected AbstractImageSink() {
}
public void setThumbnailParameter(ThumbnailParameter param){
    this.param = param;
}


public void write(BufferedImage img){
    if (img == null) {
        throw new NullPointerException("Cannot write a null image.");
    }
    if (ThumbnailParameter.DETERMINE_FORMAT.equals(outputFormat)) {
        outputFormat = preferredOutputFormatName();
    }
}


public void setOutputFormatName(String format){
    outputFormat = format;
}


public String preferredOutputFormatName(){
    return ThumbnailParameter.ORIGINAL_FORMAT;
}


}
package net.coobird.thumbnailator.tasks.io;
 import java.awt.image.BufferedImage;
import java.io.IOException;
import net.coobird.thumbnailator.ThumbnailParameter;
public interface ImageSink {


public void setThumbnailParameter(ThumbnailParameter param)
;

public T getSink()
;

public void write(BufferedImage img)
;

public void setOutputFormatName(String format)
;

public String preferredOutputFormatName()
;

}
package net.coobird.thumbnailator.tasks.io;
 import java.awt.image.BufferedImage;
import java.io.IOException;
import net.coobird.thumbnailator.ThumbnailParameter;
public interface ImageSource {


public BufferedImage read()
;

public T getSource()
;

public void setThumbnailParameter(ThumbnailParameter param)
;

public String getInputFormatName()
;

}
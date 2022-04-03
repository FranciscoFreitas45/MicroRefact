package net.coobird.thumbnailator.tasks;
 import java.awt.image.BufferedImage;
import java.io.IOException;
import net.coobird.thumbnailator.ThumbnailParameter;
public class ThumbnailTask {

 protected  ThumbnailParameter param;

 protected  String inputFormatName;

 protected  int FIRST_IMAGE_INDEX;

/**
 * Instantiates a {@link ThumbnailTask} with the parameters to use when
 * creating thumbnails.
 *
 * @param param			The parameters to use when creating thumbnails.
 * @throws NullPointerException		If the parameter is {@code null}.
 */
protected ThumbnailTask(ThumbnailParameter param) {
    if (param == null) {
        throw new NullPointerException("The parameter is null.");
    }
    this.param = param;
}
public ThumbnailParameter getParam(){
    return param;
}


public BufferedImage read()


public D getDestination()


public S getSource()


public void write(BufferedImage img)


}
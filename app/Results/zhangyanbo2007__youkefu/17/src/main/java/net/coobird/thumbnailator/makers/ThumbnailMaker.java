package net.coobird.thumbnailator.makers;
 import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import net.coobird.thumbnailator.builders.BufferedImageBuilder;
import net.coobird.thumbnailator.resizers.FixedResizerFactory;
import net.coobird.thumbnailator.resizers.Resizer;
import net.coobird.thumbnailator.resizers.DefaultResizerFactory;
import net.coobird.thumbnailator.resizers.ResizerFactory;
public class ThumbnailMaker {

 private  String NOT_READY_FOR_MAKE;

 private  String PARAM_IMAGE_TYPE;

 private  String PARAM_RESIZER;

 private  String PARAM_RESIZERFACTORY;

 private  Map<String,Boolean> alreadySetMap;

 protected  ReadinessTracker ready;

 private  int DEFAULT_IMAGE_TYPE;

 protected  int imageType;

 protected  ResizerFactory resizerFactory;

/**
 * Creates and initializes an instance of {@link ThumbnailMaker}.
 */
public ThumbnailMaker() {
    ready = new ReadinessTracker();
    ready.unset(PARAM_IMAGE_TYPE);
    ready.unset(PARAM_RESIZER);
    ready.unset(PARAM_RESIZERFACTORY);
    defaultImageType();
    defaultResizerFactory();
}
public void set(String parameterName){
    alreadySetMap.put(parameterName, true);
}


public BufferedImage makeThumbnail(BufferedImage img,int width,int height){
    if (!ready.isReady()) {
        throw new IllegalStateException(ThumbnailMaker.NOT_READY_FOR_MAKE);
    }
    if (width <= 0) {
        throw new IllegalArgumentException("Width must be greater than zero.");
    }
    if (height <= 0) {
        throw new IllegalArgumentException("Height must be greater than zero.");
    }
    BufferedImage thumbnailImage = new BufferedImageBuilder(width, height, imageType).build();
    Dimension imgSize = new Dimension(img.getWidth(), img.getHeight());
    Dimension thumbnailSize = new Dimension(width, height);
    Resizer resizer = resizerFactory.getResizer(imgSize, thumbnailSize);
    resizer.resize(img, thumbnailImage);
    return thumbnailImage;
}


public boolean isSet(String parameterName){
    return alreadySetMap.get(parameterName);
}


public ThumbnailMaker resizerFactory(ResizerFactory resizerFactory){
    this.resizerFactory = resizerFactory;
    ready.set(PARAM_RESIZER);
    ready.set(PARAM_RESIZERFACTORY);
    return this;
}


public ThumbnailMaker defaultResizerFactory(){
    this.resizerFactory = DefaultResizerFactory.getInstance();
    ready.set(PARAM_RESIZER);
    ready.set(PARAM_RESIZERFACTORY);
    return this;
}


public boolean isReady(){
    for (Map.Entry<String, Boolean> entry : alreadySetMap.entrySet()) {
        if (!entry.getValue()) {
            return false;
        }
    }
    return true;
}


public ThumbnailMaker defaultResizer(){
    return defaultResizerFactory();
}


public ThumbnailMaker defaultImageType(){
    return imageType(DEFAULT_IMAGE_TYPE);
}


public ThumbnailMaker resizer(Resizer resizer){
    this.resizerFactory = new FixedResizerFactory(resizer);
    ready.set(PARAM_RESIZER);
    ready.set(PARAM_RESIZERFACTORY);
    return this;
}


public void unset(String parameterName){
    alreadySetMap.put(parameterName, false);
}


public BufferedImage make(BufferedImage img)


public ThumbnailMaker imageType(int imageType){
    this.imageType = imageType;
    ready.set(PARAM_IMAGE_TYPE);
    return this;
}


}
package net.coobird.thumbnailator.resizers;
 import java.awt.Dimension;
public class DefaultResizerFactory implements ResizerFactory{

 private  DefaultResizerFactory INSTANCE;

/**
 * This class is not intended to be instantiated via the constructor.
 */
private DefaultResizerFactory() {
}
public ResizerFactory getInstance(){
    return INSTANCE;
}


public Resizer getResizer(Dimension originalSize,Dimension thumbnailSize){
    int origWidth = originalSize.width;
    int origHeight = originalSize.height;
    int thumbWidth = thumbnailSize.width;
    int thumbHeight = thumbnailSize.height;
    if (thumbWidth < origWidth && thumbHeight < origHeight) {
        if (thumbWidth < (origWidth / 2) && thumbHeight < (origHeight / 2)) {
            return Resizers.PROGRESSIVE;
        } else {
            return Resizers.BILINEAR;
        }
    } else if (thumbWidth > origWidth && thumbHeight > origHeight) {
        return Resizers.BICUBIC;
    } else if (thumbWidth == origWidth && thumbHeight == origHeight) {
        return Resizers.NULL;
    } else {
        return getResizer();
    }
}


}
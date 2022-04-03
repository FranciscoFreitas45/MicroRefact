package net.coobird.thumbnailator.resizers;
 import java.awt.Dimension;
public class FixedResizerFactory implements ResizerFactory{

 private  Resizer resizer;

/**
 * Creates an instance of the {@link FixedResizerFactory} which returns
 * the speicifed {@link Resizer} under all circumstances.
 *
 * @param resizer		The {@link Resizer} instance that is to be returned
 * 						under all circumstances.
 */
public FixedResizerFactory(Resizer resizer) {
    this.resizer = resizer;
}
public Resizer getResizer(Dimension originalSize,Dimension thumbnailSize){
    return resizer;
}


}
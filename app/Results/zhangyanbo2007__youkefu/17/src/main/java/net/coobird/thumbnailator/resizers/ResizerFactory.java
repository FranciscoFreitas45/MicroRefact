package net.coobird.thumbnailator.resizers;
 import java.awt.Dimension;
public interface ResizerFactory {


public Resizer getResizer(Dimension originalSize,Dimension thumbnailSize)
;

}
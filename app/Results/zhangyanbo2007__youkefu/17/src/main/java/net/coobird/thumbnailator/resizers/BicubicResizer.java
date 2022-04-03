package net.coobird.thumbnailator.resizers;
 import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.Map;
public class BicubicResizer extends AbstractResizer{

/**
 * Instantiates a {@link BicubicResizer} with default rendering hints.
 */
public BicubicResizer() {
    this(Collections.<RenderingHints.Key, Object>emptyMap());
}/**
 * Instantiates a {@link BicubicResizer} with the specified rendering hints.
 *
 * @param hints		Additional rendering hints to apply.
 */
public BicubicResizer(Map<RenderingHints.Key, Object> hints) {
    super(RenderingHints.VALUE_INTERPOLATION_BICUBIC, hints);
}
@Override
public void resize(BufferedImage srcImage,BufferedImage destImage){
    super.resize(srcImage, destImage);
}


}
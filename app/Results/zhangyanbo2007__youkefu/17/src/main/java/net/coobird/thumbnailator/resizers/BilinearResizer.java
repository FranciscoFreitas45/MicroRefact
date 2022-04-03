package net.coobird.thumbnailator.resizers;
 import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.Map;
public class BilinearResizer extends AbstractResizer{

/**
 * Instantiates a {@link BilinearResizer} with default rendering hints.
 */
public BilinearResizer() {
    this(Collections.<RenderingHints.Key, Object>emptyMap());
}/**
 * Instantiates a {@link BilinearResizer} with the specified rendering
 * hints.
 *
 * @param hints		Additional rendering hints to apply.
 */
public BilinearResizer(Map<RenderingHints.Key, Object> hints) {
    super(RenderingHints.VALUE_INTERPOLATION_BILINEAR, hints);
}
@Override
public void resize(BufferedImage srcImage,BufferedImage destImage){
    super.resize(srcImage, destImage);
}


}
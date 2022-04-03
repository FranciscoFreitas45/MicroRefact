package net.coobird.thumbnailator.resizers;
 import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
public class AbstractResizer implements Resizer{

 protected  Map<RenderingHints.Key,Object> RENDERING_HINTS;

 protected  Map<RenderingHints.Key,Object> UNMODIFIABLE_RENDERING_HINTS;

 protected  RenderingHints.Key KEY_INTERPOLATION;

/**
 * Initializes the {@link AbstractResizer}.
 *
 * @param interpolationValue		The rendering hint value to use for the
 * 									interpolation hint.
 * @param hints						Other rendering hints to add.
 */
protected AbstractResizer(Object interpolationValue, Map<RenderingHints.Key, Object> hints) {
    RENDERING_HINTS = new HashMap<RenderingHints.Key, Object>();
    RENDERING_HINTS.put(KEY_INTERPOLATION, interpolationValue);
    if (hints.containsKey(KEY_INTERPOLATION) && !interpolationValue.equals(hints.get(KEY_INTERPOLATION))) {
        throw new IllegalArgumentException("Cannot change the " + "RenderingHints.KEY_INTERPOLATION value.");
    }
    RENDERING_HINTS.putAll(hints);
    UNMODIFIABLE_RENDERING_HINTS = Collections.unmodifiableMap(RENDERING_HINTS);
}
public void performChecks(BufferedImage srcImage,BufferedImage destImage){
    if (srcImage == null || destImage == null) {
        throw new NullPointerException("The source and/or destination image is null.");
    }
}


public void resize(BufferedImage srcImage,BufferedImage destImage){
    performChecks(srcImage, destImage);
    int width = destImage.getWidth();
    int height = destImage.getHeight();
    Graphics2D g = destImage.createGraphics();
    g.setRenderingHints(RENDERING_HINTS);
    g.drawImage(srcImage, 0, 0, width, height, null);
    g.dispose();
}


public Map<RenderingHints.Key,Object> getRenderingHints(){
    return UNMODIFIABLE_RENDERING_HINTS;
}


}
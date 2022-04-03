package net.coobird.thumbnailator.resizers;
 import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.Map;
public class ProgressiveBilinearResizer extends AbstractResizer{

/**
 * Instantiates a {@link ProgressiveBilinearResizer} with default
 * rendering hints.
 */
public ProgressiveBilinearResizer() {
    this(Collections.<RenderingHints.Key, Object>emptyMap());
}/**
 * Instantiates a {@link ProgressiveBilinearResizer} with the specified
 * rendering hints.
 *
 * @param hints		Additional rendering hints to apply.
 */
public ProgressiveBilinearResizer(Map<RenderingHints.Key, Object> hints) {
    super(RenderingHints.VALUE_INTERPOLATION_BILINEAR, hints);
}
@Override
public void resize(BufferedImage srcImage,BufferedImage destImage){
    super.performChecks(srcImage, destImage);
    int currentWidth = srcImage.getWidth();
    int currentHeight = srcImage.getHeight();
    final int targetWidth = destImage.getWidth();
    final int targetHeight = destImage.getHeight();
    // If multi-step downscaling is not required, perform one-step.
    if ((targetWidth * 2 >= currentWidth) && (targetHeight * 2 >= currentHeight)) {
        Graphics2D g = destImage.createGraphics();
        g.drawImage(srcImage, 0, 0, targetWidth, targetHeight, null);
        g.dispose();
        return;
    }
    // Temporary image used for in-place resizing of image.
    BufferedImage tempImage = new BufferedImage(currentWidth, currentHeight, destImage.getType());
    Graphics2D g = tempImage.createGraphics();
    g.setRenderingHints(RENDERING_HINTS);
    g.setComposite(AlphaComposite.Src);
    /*
		 * Determine the size of the first resize step should be.
		 * 1) Beginning from the target size
		 * 2) Increase each dimension by 2
		 * 3) Until reaching the original size
		 */
    int startWidth = targetWidth;
    int startHeight = targetHeight;
    while (startWidth < currentWidth && startHeight < currentHeight) {
        startWidth *= 2;
        startHeight *= 2;
    }
    currentWidth = startWidth / 2;
    currentHeight = startHeight / 2;
    // Perform first resize step.
    g.drawImage(srcImage, 0, 0, currentWidth, currentHeight, null);
    // Perform an in-place progressive bilinear resize.
    while ((currentWidth >= targetWidth * 2) && (currentHeight >= targetHeight * 2)) {
        currentWidth /= 2;
        currentHeight /= 2;
        if (currentWidth < targetWidth) {
            currentWidth = targetWidth;
        }
        if (currentHeight < targetHeight) {
            currentHeight = targetHeight;
        }
        g.drawImage(tempImage, 0, 0, currentWidth, currentHeight, 0, 0, currentWidth * 2, currentHeight * 2, null);
    }
    g.dispose();
    // Draw the resized image onto the destination image.
    Graphics2D destg = destImage.createGraphics();
    destg.drawImage(tempImage, 0, 0, targetWidth, targetHeight, 0, 0, currentWidth, currentHeight, null);
    destg.dispose();
}


}
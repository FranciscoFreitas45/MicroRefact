package net.coobird.thumbnailator.util.exif;
 import net.coobird.thumbnailator.filters.Flip;
import net.coobird.thumbnailator.filters.ImageFilter;
import net.coobird.thumbnailator.filters.Pipeline;
import net.coobird.thumbnailator.filters.Rotation;
public class ExifFilterUtils {

/**
 * This class should not be instantiated.
 */
private ExifFilterUtils() {
}
public ImageFilter getFilterForOrientation(Orientation orientation){
    Pipeline filters = new Pipeline();
    if (orientation == Orientation.TOP_RIGHT) {
        filters.add(Flip.HORIZONTAL);
    } else if (orientation == Orientation.BOTTOM_RIGHT) {
        filters.add(Rotation.ROTATE_180_DEGREES);
    } else if (orientation == Orientation.BOTTOM_LEFT) {
        filters.add(Rotation.ROTATE_180_DEGREES);
        filters.add(Flip.HORIZONTAL);
    } else if (orientation == Orientation.LEFT_TOP) {
        filters.add(Rotation.RIGHT_90_DEGREES);
        filters.add(Flip.HORIZONTAL);
    } else if (orientation == Orientation.RIGHT_TOP) {
        filters.add(Rotation.RIGHT_90_DEGREES);
    } else if (orientation == Orientation.RIGHT_BOTTOM) {
        filters.add(Rotation.LEFT_90_DEGREES);
        filters.add(Flip.HORIZONTAL);
    } else if (orientation == Orientation.LEFT_BOTTOM) {
        filters.add(Rotation.LEFT_90_DEGREES);
    }
    return filters;
}


}
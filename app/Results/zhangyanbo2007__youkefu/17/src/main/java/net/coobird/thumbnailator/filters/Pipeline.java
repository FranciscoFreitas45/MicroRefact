package net.coobird.thumbnailator.filters;
 import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import net.coobird.thumbnailator.util.BufferedImages;
public class Pipeline implements ImageFilter{

 private  List<ImageFilter> filtersToApply;

 private  List<ImageFilter> unmodifiableFiltersToApply;

/**
 * Instantiates a new {@link Pipeline} with no image filters to apply.
 */
public Pipeline() {
    this(Collections.<ImageFilter>emptyList());
}/**
 * Instantiates a new {@link Pipeline} with an array of {@link ImageFilter}s
 * to apply.
 *
 * @param filters		An array of {@link ImageFilter}s to apply.
 */
public Pipeline(ImageFilter... filters) {
    this(Arrays.asList(filters));
}/**
 * Instantiates a new {@link Pipeline} with a list of {@link ImageFilter}s
 * to apply.
 *
 * @param filters		A list of {@link ImageFilter}s to apply.
 */
public Pipeline(List<ImageFilter> filters) {
    if (filters == null) {
        throw new NullPointerException("Cannot instantiate with a null" + "list of image filters.");
    }
    filtersToApply = new ArrayList<ImageFilter>(filters);
    unmodifiableFiltersToApply = Collections.unmodifiableList(filtersToApply);
}
public void add(ImageFilter filter){
    if (filter == null) {
        throw new NullPointerException("An image filter must not be null.");
    }
    filtersToApply.add(filter);
}


public void addAll(List<ImageFilter> filters){
    if (filters == null) {
        throw new NullPointerException("A list of image filters must not be null.");
    }
    filtersToApply.addAll(filters);
}


public BufferedImage apply(BufferedImage img){
    if (filtersToApply.isEmpty()) {
        return img;
    }
    BufferedImage image = BufferedImages.copy(img);
    for (ImageFilter filter : filtersToApply) {
        image = filter.apply(image);
    }
    return image;
}


public List<ImageFilter> getFilters(){
    return unmodifiableFiltersToApply;
}


public void addFirst(ImageFilter filter){
    if (filter == null) {
        throw new NullPointerException("An image filter must not be null.");
    }
    filtersToApply.add(0, filter);
}


}
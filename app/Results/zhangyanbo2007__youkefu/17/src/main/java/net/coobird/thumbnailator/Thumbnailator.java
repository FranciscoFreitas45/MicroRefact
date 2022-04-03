package net.coobird.thumbnailator;
 import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import net.coobird.thumbnailator.builders.BufferedImageBuilder;
import net.coobird.thumbnailator.builders.ThumbnailParameterBuilder;
import net.coobird.thumbnailator.filters.ImageFilter;
import net.coobird.thumbnailator.makers.FixedSizeThumbnailMaker;
import net.coobird.thumbnailator.makers.ScaledThumbnailMaker;
import net.coobird.thumbnailator.name.Rename;
import net.coobird.thumbnailator.resizers.DefaultResizerFactory;
import net.coobird.thumbnailator.resizers.Resizer;
import net.coobird.thumbnailator.tasks.ThumbnailTask;
public class Thumbnailator {

/**
 * This class is not intended to be instantiated.
 */
private Thumbnailator() {
}
public void createThumbnails(Collection<? extends File> files,Rename rename,int width,int height){
    validateDimensions(width, height);
    if (files == null) {
        throw new NullPointerException("Collection of Files is null.");
    }
    if (rename == null) {
        throw new NullPointerException("Rename is null.");
    }
    ThumbnailParameter param = new ThumbnailParameterBuilder().size(width, height).build();
    for (File inFile : files) {
        File outFile = new File(inFile.getParent(), rename.apply(inFile.getName(), param));
        createThumbnail(inFile, outFile, width, height);
    }
}


public void createThumbnail(InputStream is,OutputStream os,String format,int width,int height){
    validateDimensions(width, height);
    if (is == null) {
        throw new NullPointerException("InputStream is null.");
    } else if (os == null) {
        throw new NullPointerException("OutputStream is null.");
    }
    Thumbnails.of(is).size(width, height).outputFormat(format).toOutputStream(os);
}


public void validateDimensions(int width,int height){
    if (width <= 0 && height <= 0) {
        throw new IllegalArgumentException("Destination image dimensions must not be less than " + "0 pixels.");
    } else if (width <= 0 || height <= 0) {
        String dimension = width == 0 ? "width" : "height";
        throw new IllegalArgumentException("Destination image " + dimension + " must not be " + "less than or equal to 0 pixels.");
    }
}


public Collection<File> createThumbnailsAsCollection(Collection<? extends File> files,Rename rename,int width,int height){
    validateDimensions(width, height);
    if (files == null) {
        throw new NullPointerException("Collection of Files is null.");
    }
    if (rename == null) {
        throw new NullPointerException("Rename is null.");
    }
    ArrayList<File> resultFiles = new ArrayList<File>();
    ThumbnailParameter param = new ThumbnailParameterBuilder().size(width, height).build();
    for (File inFile : files) {
        File outFile = new File(inFile.getParent(), rename.apply(inFile.getName(), param));
        createThumbnail(inFile, outFile, width, height);
        resultFiles.add(outFile);
    }
    return Collections.unmodifiableList(resultFiles);
}


}
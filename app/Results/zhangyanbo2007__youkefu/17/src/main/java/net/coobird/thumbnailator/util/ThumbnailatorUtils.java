package net.coobird.thumbnailator.util;
 import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import net.coobird.thumbnailator.ThumbnailParameter;
public class ThumbnailatorUtils {

/**
 *  This class is not intended to be instantiated.
 */
private ThumbnailatorUtils() {
}
public boolean isSupportedOutputFormat(String format){
    if (format == ThumbnailParameter.ORIGINAL_FORMAT) {
        return true;
    }
    for (String supportedFormat : getSupportedOutputFormats()) {
        if (supportedFormat.equals(format)) {
            return true;
        }
    }
    return false;
}


public boolean isSupportedOutputFormatType(String format,String type){
    if (!isSupportedOutputFormat(format)) {
        return false;
    }
    if (format == ThumbnailParameter.ORIGINAL_FORMAT && type == ThumbnailParameter.DEFAULT_FORMAT_TYPE) {
        return true;
    } else if (format == ThumbnailParameter.ORIGINAL_FORMAT && type != ThumbnailParameter.DEFAULT_FORMAT_TYPE) {
        return false;
    } else if (type == ThumbnailParameter.DEFAULT_FORMAT_TYPE) {
        return true;
    }
    for (String supportedType : getSupportedOutputFormatTypes(format)) {
        if (supportedType.equals(type)) {
            return true;
        }
    }
    return false;
}


public List<String> getSupportedOutputFormatTypes(String format){
    if (format == ThumbnailParameter.ORIGINAL_FORMAT) {
        return Collections.emptyList();
    }
    Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName(format);
    if (!writers.hasNext()) {
        return Collections.emptyList();
    }
    String[] types;
    try {
        types = writers.next().getDefaultWriteParam().getCompressionTypes();
    } catch (UnsupportedOperationException e) {
        return Collections.emptyList();
    }
    if (types == null) {
        return Collections.emptyList();
    } else {
        return Arrays.asList(types);
    }
}


public List<String> getSupportedOutputFormats(){
    String[] formats = ImageIO.getWriterFormatNames();
    if (formats == null) {
        return Collections.emptyList();
    } else {
        return Arrays.asList(formats);
    }
}


}
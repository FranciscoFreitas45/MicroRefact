package net.coobird.thumbnailator.name;
 import net.coobird.thumbnailator.ThumbnailParameter;
public class Rename {

 public  Rename NO_CHANGE;

 public  Rename PREFIX_DOT_THUMBNAIL;

@Deprecated
 public  Rename PREFIX_HYPTHEN_THUMBNAIL;

 public  Rename PREFIX_HYPHEN_THUMBNAIL;

 public  Rename SUFFIX_DOT_THUMBNAIL;

@Deprecated
 public  Rename SUFFIX_HYPTHEN_THUMBNAIL;

 public  Rename SUFFIX_HYPHEN_THUMBNAIL;

/**
 * The default constructor is intended only to be called implicitly
 * by the classes implementing the functionality of the {@link Rename}
 * class.
 */
protected Rename() {
}
public String apply(String name,ThumbnailParameter param)


public String appendPrefix(String fileName,String prefix){
    return prefix + fileName;
}


public String appendSuffix(String fileName,String suffix){
    String newFileName = "";
    int indexOfDot = fileName.lastIndexOf('.');
    if (indexOfDot != -1) {
        newFileName = fileName.substring(0, indexOfDot);
        newFileName += suffix;
        newFileName += fileName.substring(indexOfDot);
    } else {
        newFileName = fileName + suffix;
    }
    return newFileName;
}


}
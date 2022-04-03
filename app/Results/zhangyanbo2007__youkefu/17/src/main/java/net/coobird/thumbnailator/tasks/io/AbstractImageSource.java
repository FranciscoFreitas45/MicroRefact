package net.coobird.thumbnailator.tasks.io;
 import net.coobird.thumbnailator.ThumbnailParameter;
public class AbstractImageSource implements ImageSource<T>{

 protected  String inputFormatName;

 protected  ThumbnailParameter param;

 protected  boolean hasReadInput;

/**
 * Default constructor.
 */
protected AbstractImageSource() {
}
public void setThumbnailParameter(ThumbnailParameter param){
    this.param = param;
}


public V finishedReading(V returnValue){
    hasReadInput = true;
    return returnValue;
}


public String getInputFormatName(){
    if (!hasReadInput) {
        throw new IllegalStateException("Input has not been read yet.");
    }
    return inputFormatName;
}


}
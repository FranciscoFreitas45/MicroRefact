package net.coobird.thumbnailator.makers.ThumbnailMaker;
 import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import net.coobird.thumbnailator.builders.BufferedImageBuilder;
import net.coobird.thumbnailator.resizers.FixedResizerFactory;
import net.coobird.thumbnailator.resizers.Resizer;
import net.coobird.thumbnailator.resizers.DefaultResizerFactory;
import net.coobird.thumbnailator.resizers.ResizerFactory;
public class ReadinessTracker {

 private  Map<String,Boolean> alreadySetMap;


public void set(String parameterName){
    alreadySetMap.put(parameterName, true);
}


public boolean isSet(String parameterName){
    return alreadySetMap.get(parameterName);
}


public boolean isReady(){
    for (Map.Entry<String, Boolean> entry : alreadySetMap.entrySet()) {
        if (!entry.getValue()) {
            return false;
        }
    }
    return true;
}


public void unset(String parameterName){
    alreadySetMap.put(parameterName, false);
}


}
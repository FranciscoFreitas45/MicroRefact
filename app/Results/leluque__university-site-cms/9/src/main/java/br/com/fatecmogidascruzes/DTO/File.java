package br.com.fatecmogidascruzes.DTO;
 import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import br.com.fatecmogidascruzes.domain.NamedEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
public class File extends NamedEntityimplements Serializable{

 private  long serialVersionUID;

 protected  String originalName;

 protected  Long size;

 protected  String contentType;

 protected  String description;

 protected  String alternativeDescription;

 protected  Set<ImageVariation> variations;


@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_file")
@Id
@Override
@SequenceGenerator(name = "seq_file", initialValue = 1, allocationSize = 1)
public Long getId(){
    return super.getId();
}


public ImageVariation getVariationFor(int width,int height){
    if (null != this.variations && !variations.isEmpty()) {
        // If the required width and height are less than 15% than an existing image
        // variation.
        Map<Double, ImageVariation> sortedImageVariationsPerDistance = new TreeMap<>();
        for (ImageVariation variation : this.variations) {
            double horizontalRatio = width / variation.getWidth();
            double verticalRatio = height / variation.getHeight();
            double meanRatio = (horizontalRatio + verticalRatio) / 2;
            sortedImageVariationsPerDistance.put(Math.abs(1 - meanRatio), variation);
        }
        // Return the first value in the map (the one with the minimum distance).
        ImageVariation imageVariation = null;
        double meanRatio = 0;
        for (Map.Entry<Double, ImageVariation> variationEntry : sortedImageVariationsPerDistance.entrySet()) {
            imageVariation = variationEntry.getValue();
            meanRatio = variationEntry.getKey();
        }
        if (meanRatio > 0.3) {
            ImageVariation originalImage = new ImageVariation(size, contentType, width, height, this);
            originalImage.setName(name);
            return originalImage;
        } else {
            return imageVariation;
        }
    }
    return null;
}


}
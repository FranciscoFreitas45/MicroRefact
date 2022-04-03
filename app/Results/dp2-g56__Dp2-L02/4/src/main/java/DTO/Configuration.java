package DTO;
 import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
public class Configuration extends DomainEntity{

 private  List<String> spamWords;

 private  String spainTelephoneCode;

 private  Integer minFinderResults;

 private  Integer maxFinderResults;

 private  Integer finderResult;

 private  Integer minTimeFinder;

 private  Integer maxTimeFinder;

 private  Integer timeFinder;

 private  List<String> priorityLvl;

 private  List<String> priorityLvlSpa;

 private  String goodWords;

 private  String badWords;

 private  String welcomeMessageEnglish;

 private  String welcomeMessageSpanish;

 private  String systemName;

 private  String imageURL;

 private  Integer VAT;

 private  java.lang.Float fare;

 private  List<String> cardType;


@ElementCollection(targetClass = String.class)
public List<String> getSpamWords(){
    return this.spamWords;
}


@NotBlank
public String getWelcomeMessageSpanish(){
    return this.welcomeMessageSpanish;
}


@NotBlank
public String getSpainTelephoneCode(){
    return this.spainTelephoneCode;
}


@NotNull
public Integer getMinTimeFinder(){
    return this.minTimeFinder;
}


@NotBlank
@URL
public String getImageURL(){
    return this.imageURL;
}


@NotNull
public Integer getmaxFinderResults(){
    return this.maxFinderResults;
}


@NotNull
public Integer getMaxTimeFinder(){
    return this.maxTimeFinder;
}


@NotNull
@Digits(fraction = 2, integer = 9)
public java.lang.Float getFare(){
    return this.fare;
}


@Valid
@ElementCollection(targetClass = String.class)
public List<String> getPriorityLvlSpa(){
    return this.priorityLvlSpa;
}


@NotBlank
public String getSystemName(){
    return this.systemName;
}


@NotBlank
public String getWelcomeMessageEnglish(){
    return this.welcomeMessageEnglish;
}


@Valid
@ElementCollection(targetClass = String.class)
public List<String> getPriorityLvl(){
    return this.priorityLvl;
}


@NotNull
public Integer getTimeFinder(){
    return this.timeFinder;
}


@NotBlank
public String getBadWords(){
    return this.badWords;
}


@NotNull
public Integer getFinderResult(){
    return this.finderResult;
}


@NotBlank
public String getGoodWords(){
    return this.goodWords;
}


@NotNull
public Integer getMinFinderResults(){
    return this.minFinderResults;
}


@NotNull
@Max(100)
public Integer getVAT(){
    return this.VAT;
}


@ElementCollection(targetClass = String.class)
public List<String> getCardType(){
    return this.cardType;
}


}
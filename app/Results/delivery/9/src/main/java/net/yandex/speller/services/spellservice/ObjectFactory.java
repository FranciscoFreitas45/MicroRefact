package net.yandex.speller.services.spellservice;
 import javax.xml.bind.annotation.XmlRegistry;
@XmlRegistry
public class ObjectFactory {


public ArrayOfSpellResult createArrayOfSpellResult(){
    return new ArrayOfSpellResult();
}


public CheckTextResponse createCheckTextResponse(){
    return new CheckTextResponse();
}


public CheckTextsResponse createCheckTextsResponse(){
    return new CheckTextsResponse();
}


public SpellResult createSpellResult(){
    return new SpellResult();
}


public CheckTextRequest createCheckTextRequest(){
    return new CheckTextRequest();
}


public CheckTextsRequest createCheckTextsRequest(){
    return new CheckTextsRequest();
}


public SpellError createSpellError(){
    return new SpellError();
}


}
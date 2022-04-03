package controllers;
 import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.ConfigurationService;
@Controller
@RequestMapping("words/administrator")
public class WordsAdministratorController extends AbstractController{

@Autowired
 private  ConfigurationService configurationService;

// Constructors -----------------------------------------------------------
public WordsAdministratorController() {
    super();
}
@RequestMapping(value = "/save", method = RequestMethod.GET)
public ModelAndView edit(String word){
    ModelAndView result;
    Assert.notNull(word);
    result = new ModelAndView("words/administrator/create");
    result.addObject("word", word);
    return result;
}


@RequestMapping(value = "/create", method = RequestMethod.GET)
public ModelAndView newWord(){
    ModelAndView result;
    result = new ModelAndView("words/administrator/create");
    return result;
}


@RequestMapping(value = "/save", method = RequestMethod.POST, params = "editWord")
public ModelAndView editWord(String word,String originalWord){
    ModelAndView result;
    try {
        if (word.trim().isEmpty()) {
            result = new ModelAndView("redirect:create.do");
            result.addObject("word", word);
            result.addObject("originalWord", originalWord);
        } else {
            this.configurationService.editWord(word, originalWord);
            result = new ModelAndView("redirect:list.do");
        }
    } catch (Throwable oops) {
        result = new ModelAndView("redirect:create.do");
        result.addObject("word", word);
        result.addObject("originalWord", originalWord);
    }
    return result;
}


@RequestMapping(value = "/save", method = RequestMethod.POST, params = "delete")
public ModelAndView deleteWord(String word,String originalWord){
    ModelAndView result;
    List<String> goodWords = new ArrayList<String>();
    goodWords = this.configurationService.showGoodWordsList();
    if (goodWords.contains(word))
        this.configurationService.deleteGoodWord(word);
    else
        this.configurationService.deleteBadWord(word);
    result = new ModelAndView("redirect:list.do");
    return result;
}


@RequestMapping(value = "/list", method = RequestMethod.GET)
public ModelAndView paradesList(){
    ModelAndView result;
    List<String> badWords = this.configurationService.showBadWordsList();
    List<String> goodWords = this.configurationService.showGoodWordsList();
    result = new ModelAndView("words/administrator/list");
    result.addObject("badWords", badWords);
    result.addObject("goodWords", goodWords);
    result.addObject("requestURI", "words/administrator/list.do");
    return result;
}


@RequestMapping(value = "/save", method = RequestMethod.POST, params = "save")
public ModelAndView saveGoodorBadWord(String word,String wordType){
    ModelAndView result;
    try {
        if (word.trim().isEmpty() || word.trim().isEmpty()) {
            result = new ModelAndView("words/administrator/create");
            result.addObject("wordType", wordType);
        } else {
            if (wordType.equals("goodword"))
                this.configurationService.addGoodWords(word);
            else
                this.configurationService.addBadWords(word);
            result = new ModelAndView("redirect:list.do");
        }
    } catch (Throwable oops) {
        result = new ModelAndView("words/administrator/create");
        result.addObject("wordType", wordType);
    }
    return result;
}


}
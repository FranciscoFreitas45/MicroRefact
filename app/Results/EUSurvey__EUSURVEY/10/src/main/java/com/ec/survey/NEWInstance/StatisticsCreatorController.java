package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class StatisticsCreatorController {

 private StatisticsCreator statisticscreator;

 private StatisticsCreator statisticscreator;


@GetMapping
("/runSync")
public Statistics runSync(){
  return statisticscreator.runSync();
}


@GetMapping
("/addStatistics4RatingQuestion")
public int addStatistics4RatingQuestion(@RequestParam(name = "survey") Survey survey,@RequestParam(name = "answer") Integer answer,@RequestParam(name = "question") Element question,@RequestParam(name = "statistics") Statistics statistics,@RequestParam(name = "numberOfAnswersMapRatingQuestion") Map<Integer,Map<Integer,Integer>> numberOfAnswersMapRatingQuestion){
  return statisticscreator.addStatistics4RatingQuestion(survey,answer,question,statistics,numberOfAnswersMapRatingQuestion);
}


@PutMapping
("/init")
public void init(@RequestParam(name = "survey") Survey survey,@RequestParam(name = "filter") ResultFilter filter,@RequestParam(name = "allanswers") boolean allanswers){
statisticscreator.init(survey,filter,allanswers);
}


@PutMapping
("/addStatistics")
public void addStatistics(@RequestParam(name = "survey") Survey survey,@RequestParam(name = "question") ChoiceQuestion question,@RequestParam(name = "statistics") Statistics statistics,@RequestParam(name = "numberOfAnswersMap") Map<Integer,Integer> numberOfAnswersMap,@RequestParam(name = "multipleChoiceSelectionsByAnswerset") Map<Integer,Map<String,Set<String>>> multipleChoiceSelectionsByAnswerset){
statisticscreator.addStatistics(survey,question,statistics,numberOfAnswersMap,multipleChoiceSelectionsByAnswerset);
}


@GetMapping
("/addStatistics4Matrix")
public int addStatistics4Matrix(@RequestParam(name = "survey") Survey survey,@RequestParam(name = "answer") Element answer,@RequestParam(name = "question") Element question,@RequestParam(name = "statistics") Statistics statistics,@RequestParam(name = "numberOfAnswersMapMatrix") Map<Integer,Map<Integer,Integer>> numberOfAnswersMapMatrix){
  return statisticscreator.addStatistics4Matrix(survey,answer,question,statistics,numberOfAnswersMapMatrix);
}


}
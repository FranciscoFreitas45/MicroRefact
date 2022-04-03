package com.ec.survey.model.delphi;
 import com.ec.survey.model.survey.DelphiChartType;
public class AbstractDelphiGraphData {

 private  DelphiChartType chartType;

 private  DelphiQuestionType questionType;


public DelphiQuestionType getQuestionType(){
    return questionType;
}


public void setChartType(DelphiChartType chartType){
    this.chartType = chartType;
}


public void setQuestionType(DelphiQuestionType questionType){
    this.questionType = questionType;
}


public DelphiChartType getChartType(){
    return chartType;
}


}
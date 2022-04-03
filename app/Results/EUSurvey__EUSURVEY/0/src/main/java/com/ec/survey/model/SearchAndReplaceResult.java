package com.ec.survey.model;
 public class SearchAndReplaceResult {

 private  int translationId;

 private  String[] searchResults;

 private  String[] replaceResults;

 private  boolean emptyLabels;


public boolean getEmptyLabels(){
    return emptyLabels;
}


public int getTranslationId(){
    return translationId;
}


public void setEmptyLabels(boolean emptyLabels){
    this.emptyLabels = emptyLabels;
}


public void setTranslationId(int translationId){
    this.translationId = translationId;
}


public void setSearchResults(String[] searchResults){
    this.searchResults = searchResults;
}


public void setReplaceResults(String[] replaceResults){
    this.replaceResults = replaceResults;
}


public String[] getSearchResults(){
    return searchResults;
}


public String[] getReplaceResults(){
    return replaceResults;
}


}
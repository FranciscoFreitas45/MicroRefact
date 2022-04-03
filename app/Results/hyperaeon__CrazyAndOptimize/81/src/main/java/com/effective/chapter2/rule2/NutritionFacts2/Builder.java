package com.effective.chapter2.rule2.NutritionFacts2;
 public class Builder {

 private  int servingSize;

 private  int servings;

 private  int calories;

 private  int fat;

 private  int sodium;

 private  int carbohydrate;

public Builder(int servingsize, int servings) {
    this.servingSize = servingsize;
    this.servings = servings;
}
public Builder sodium(int val){
    sodium = val;
    return this;
}


public NutritionFacts2 build(){
    return new NutritionFacts2(this);
}


public Builder fat(int val){
    fat = val;
    return this;
}


public Builder calories(int val){
    calories = val;
    return this;
}


public Builder carbohydrate(int val){
    carbohydrate = val;
    return this;
}


}
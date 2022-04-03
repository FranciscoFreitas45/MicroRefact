package com.effective.chapter2.rule2;
 public class NutritionFacts2 {

 private  int servingSize;

 private  int servings;

 private  int calories;

 private  int fat;

 private  int sodium;

 private  int carbohydrate;

 private  int servingSize;

 private  int servings;

 private  int calories;

 private  int fat;

 private  int sodium;

 private  int carbohydrate;

public NutritionFacts2(Builder builder) {
    this.servingSize = builder.servingSize;
    this.servings = builder.servings;
    this.calories = builder.calories;
    this.fat = builder.fat;
    this.sodium = builder.sodium;
    this.carbohydrate = builder.carbohydrate;
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
package com.DTO;


public class Dish {

 private  Long dishId;

 private  String englishName;

 private  String polishName;

 private  double price;

 private  String image;


public Long getDishId(){
    return dishId;
}


public String getEnglishName(){
    return englishName;
}


public String getPolishName(){
    return polishName;
}


public String getImage(){
    return image;
}


public double getPrice(){
    return this.price;
}


}
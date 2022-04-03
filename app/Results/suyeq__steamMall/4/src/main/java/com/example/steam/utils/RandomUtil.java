package com.example.steam.utils;
 import java.util.Random;
public class RandomUtil {

 private  Random random;


public int randomSeed(int seed){
    return random.nextInt(seed) + 1;
}


}
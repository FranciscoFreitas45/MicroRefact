package com.algorithm.classic;
 import java.util.Scanner;
public class StarTriangle {


public void printDownTriangle(int max){
    for (int i = 0; i < max; i++) {
        for (int j = 0; j < i; j++) {
            System.out.print(" ");
        }
        for (int j = i; j <= max - i; j++) {
            System.out.print("*");
        }
        System.out.println();
    }
}


public void printUpTriangle(int max){
    for (int i = 0; i <= max - 3; i++) {
        for (int j = 0; j < max - i - 3; j++) {
            System.out.print(" ");
        }
        for (int j = max - i - 3; j <= i + 3; j++) {
            System.out.print("*");
        }
        System.out.println();
    }
}


public void main(String[] args){
    @SuppressWarnings("resource")
    Scanner sc = new Scanner(System.in);
    while (sc.hasNext()) {
        int max = sc.nextInt();
        if (!Prime.isPrime(max)) {
            System.out.println("Please input prime number!");
            continue;
        }
        printUpTriangle(max - 1);
    // printDownTriangle(max - 1);
    }
}


}
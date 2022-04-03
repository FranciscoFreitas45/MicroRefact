package com.algorithm.classic;
 public class Prime {


public void main(String[] args){
    int count = 0;
    for (int i = 101; i <= 199; i++) {
        if (isPrime(i) == true) {
            System.out.print(i + " ");
            count++;
        }
    }
    System.out.println();
    System.out.println("����������" + count);
}


public boolean isPrime(int j){
    if (j == 1) {
        return false;
    }
    for (int i = 2; i <= Math.sqrt(j); i++) {
        if (j % i == 0) {
            return false;
        }
    }
    return true;
}


}
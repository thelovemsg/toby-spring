package me.whiteship.inflearnthejavatest.algorithm.recursion;

import java.util.Arrays;

public class Code05_N_queens {
    public static void main(String[] args) {

    }
    static int  N = 4;
    static int[] cols = new int [N+1];
    public static boolean queens(int level) {
        if(!promising(level)){
            return false;
        }else if(level == N){
            return true;
        }
        for(int i=1;i <=N; i++){
            cols[level+1] = i;
            if(queens(level+1)){
                return true;
            }
        }
        return false;

    }

    private static boolean promising(int level) {
        int[] test = {5,4,8,9,87,32};
        Arrays.sort(test);
        for(int i=1; i<level; i++){
            if (cols[i] == cols[level]){
                return false;
            }else if(level-i == Math.abs(cols[level] - cols[i])){
                return false;
            }
        }
        return true;
    }

}

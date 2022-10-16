package me.whiteship.inflearnthejavatest.member.recursion;

public class Code01 {
    public static void main(String[] args) {
        System.out.println(factorial(5));
    }

    private static int factorial(int i) {
        if(i == 0)
            return 1;
        else
            return i*factorial(i-1);
    }

    public static double gcd(int m, int n){
        if (m<n) {
            int temp = m;
            m=n;
            n=temp;
        }
        if(m%n==0)
            return n;
        else return gcd(n, m%n);
    }

    public static double gcd1(int p, int q){
        if(q==0)
            return p;
        else
            return gcd1(q, p%q);
    }
}

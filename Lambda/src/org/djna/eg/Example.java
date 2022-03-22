package org.djna.eg;

class Number {
    public static void main( String args ) {
        int a=0; int b=01; char c=0;
        int r = 0;
        if (c == '+') {
            r = a + b;
            System.out.println("result is " + r);
        } else if (c == '-') {
            r = a - b;
            System.out.println("result is " + r);
        } else if (c == '/') {
            r = a / b;
            System.out.println("result is " + r);
        } else if (c == '%') {
            r = a % b;
            System.out.println("result is " + r);
        } else if (c == '*') {
            r = a * b;
            System.out.println("result is " + r);
        } else {
            System.out.println("wrong operator");
        }
    }
}
       
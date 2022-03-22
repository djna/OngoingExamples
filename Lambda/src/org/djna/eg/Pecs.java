package org.djna.eg;
import java.util.ArrayList;
import java.util.List;
public class Pecs {

    public static class A {}

    public static class B extends A {}

    public static class C extends B {}

    public static void testCoVariance(List<? extends B> myBlist) {
        A a = new A();
        B b = new B();
        B c = new C();
        //myBlist.add(a);
        //myBlist.add(b);// does not compile
        //myBlist.add(c); // does not compile
        A gotA = myBlist.get(0);
        B gotB = myBlist.get(0);
        //C gotC = myBlist.get(0);
    }

    public static void testContraVariance(List<? super B> myBlist) {
        A a = new A();
        B b = new B();
        C c = new C();
        //myBlist.add(a);
        myBlist.add(b);
        myBlist.add(c);
        //a = myBlist.get(0); // does not compile
        //b = myBlist.get(0); // does not compile
        //c = myBlist.get(0); // does not compile
        Object o = myBlist.get(0);

    }

    public static void main(String[] args) {
        List<A> alist = new ArrayList<>();
        List<B> blist = new ArrayList<>();
        List<C> clist = new ArrayList<>();
        //testCoVariance(alist);
        testCoVariance(blist);
        testCoVariance(clist);
    }
}
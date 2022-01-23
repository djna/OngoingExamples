package org.djna.algex.list;

import java.util.*;
import java.util.function.IntSupplier;
import java.util.function.Function;

public class CodeTimer {

    private List<Integer> myList;
    private long iterations;
    private int size;

    private CodeTimer(){

        clearList();
        iterations = 10_000L;
        size = 100_000;

    }

    private void testListPerformance(){

//        Long sizeElapsed = measure(this::callSize);
//        System.out.printf("Size - iterations:%,d  size:%,d elapsed:%,dms %n%n", iterations, size, sizeElapsed);

//       Long getElapsed = measure(this::callGet);
//       System.out.printf("Get - iterations:%,d  size:%,d elapsed:%,dms %n%n", iterations, size, getElapsed);

//       Long containsElapsed = measure(this::callContains);
//       System.out.printf("Contains -iterations:%,d  size:%,d elapsed:%,dms %n%n", iterations, size, containsElapsed);

        double buildElapsed = measure(this::callBuildOnly) ;
        System.out.printf("Build - iterations:%,d  size:%,d elapsed:%,.1fms %n%n", iterations, size, buildElapsed);


        double insertElapsed = measure(this::callIteratorInsert);
        System.out.printf("Insert - iterations:%,d  size:%,d elapsed:%,.1fms %n%n",  iterations, size, insertElapsed);
        System.out.printf("Insert Only - %,.1fms %n%n",  (insertElapsed - buildElapsed) );

    }

    private void addElementsToList(int requiredSize){

        for ( int i = myList.size(); myList.size() < requiredSize; i++ ){
            myList.add(i);
        }
    }

    private void clearList(){
       //myList = new ArrayList<Integer>();
       myList = new LinkedList<Integer>();
    }

    private double measure(IntSupplier worker) {
        // warm up
        for ( long i = 0; i < 100; i++) {
            worker.getAsInt();
        }

        //long start = System.currentTimeMillis();
        long start = System.nanoTime();

        int result = 0;
        for ( long i = 0; i < iterations; i++) {
            result = worker.getAsInt();
        }
        //long end  = System.currentTimeMillis();
        long end  = System.nanoTime();

        System.out.printf("final result after %,d iterations = %,d %n", iterations, result);
        System.out.printf("start %,d end = %,d %n", start, end);

        return (end - start) / 1_000_000;
    }

    private int callSize(){
        return myList.size();
    }

    private int callGet(){
        return myList.get( myList.size() /2 );
    }

    private int callContains(){
        int lookFor = myList.size() /2;
        return myList.contains( lookFor ) ? lookFor : -1;
    }

    private int callInsert(){
        clearList();
        addElementsToList(size);

        Iterator<Integer> iter = myList.iterator();

        for ( int n = 0; n < myList.size() / 2; n++) {
            iter.next();
        }

        for ( int i = 0; i < 100; i++) {
            int insertAt = myList.size() / 2;
            int insertValue = myList.size();
            myList.add(insertAt, insertValue);
        }

        return myList.size();
    }

    private int callIteratorInsert(){
        clearList();
        addElementsToList(size);

        ListIterator<Integer> iter = (ListIterator<Integer>) myList.iterator();

        // seek to insert position (once, so hope it's negligable)
        for ( int n = 0; n < myList.size() / 2; n++) {
            iter.next();
        }

        for ( int i = 0; i < 10_000; i++) {
            int insertValue = myList.size();
            iter.add(insertValue);
        }

        return myList.size();
    }

    private int callBuildOnly(){
        clearList();
        addElementsToList(size);

        return myList.size();
    }

    public static void main(String[] args){

        CodeTimer timer = new CodeTimer();

        timer.testListPerformance();
    }
 
}

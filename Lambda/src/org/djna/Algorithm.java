package org.djna;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;


public final class Algorithm {
    public static <T extends Object & Comparable<? super T>>
    T max(List<? extends T  > list, int begin, int end) {

        T maxElem = list.get(begin);

        for (++begin; begin < end; ++begin)
            if (maxElem.compareTo(list.get(begin)) < 0)
                maxElem = list.get(begin);
        return maxElem;
    }

    public interface UnaryPredicate<T> {
        public boolean test(T obj);
    }

    public <T extends Printable & Comparable<? super T> >
    void printList(List<? extends T>theList, Comparator<T>comparator){
        theList.stream().sorted(comparator).forEach( T::printSelf);
    }

    public static void printThing(Printable thingToPrint ){
       thingToPrint.printSelf();
    }

    public Optional<Person> findFirstMatch(List<Person> people, CheckPerson checker) {
        return people.stream().filter(p -> checker.test(p)).findFirst( );
    }







}
package org.djna;

import java.util.ArrayList;
import java.util.List;

public class CollectionUtils {

    // PECS Producer Extends Consumer Super
    public static <T> void copy(List<? super T> dest,
                                List<? extends T> src) {
        for (int i = 0; i < src.size(); i++)
            dest.set(i, src.get(i));
    }

    public class Animal {}
    public class Carnivore extends Animal {}
    public class Herbivore extends Animal {}
    public class Wolf extends Carnivore {}

    public static void main(String[] args) {

        List<Animal> zoo = new ArrayList<>();
        List<Carnivore> carnivores = new ArrayList<>();
        List<Herbivore> herbivores = new ArrayList<>();
        List<Wolf> wolves = new ArrayList<>();

        CollectionUtils.copy(zoo, carnivores);
        CollectionUtils.copy(herbivores, wolves);
        CollectionUtils.copy(carnivores, zoo);
    }


}

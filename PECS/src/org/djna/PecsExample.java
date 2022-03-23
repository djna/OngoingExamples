package org.djna;

import java.util.ArrayList;
import java.util.List;

public class PecsExample {

    public class Animal {}
    public class Carnivore extends Animal {}
    public class Herbivore extends Animal {}

    public class Wolf extends Carnivore {}
    public class Lion extends Carnivore {}
    public class TabbyCat extends Carnivore {}

    public class Horse extends Herbivore {}
    public class Rabbit extends Herbivore {};

    public static void main(String[] args) {
        List<Animal> zoo = new ArrayList<>();

        List<Wolf> wolves = new ArrayList<>();
        // code to add wokves here

        List<Horse> horses = new ArrayList<>();
        // code to add horses here

        List<Carnivore> carnivores = new ArrayList<>();
        List<Herbivore> herbivores = new ArrayList<>();

        CollectionUtils.copy(carnivores, wolves);
        CollectionUtils.copy(carnivores, horses);
        CollectionUtils.copy(wolves, carnivores);

        // Consumer Super - contravarience
        CollectionUtils.<Animal>copy(zoo, wolves);
        CollectionUtils.<Animal>copy(carnivores, wolves);
        CollectionUtils.<Animal>copy(wolves, wolves);

        CollectionUtils.<Carnivore>copy(zoo, wolves);
        CollectionUtils.<Carnivore>copy(carnivores, wolves);
        CollectionUtils.<Carnivore>copy(wolves, wolves);

        CollectionUtils.<Wolf>copy(zoo, wolves);
        CollectionUtils.<Wolf>copy(carnivores, wolves);
        CollectionUtils.<Wolf>copy(wolves, wolves);

        // Producer Extends - covarience
        CollectionUtils.<Animal>copy(zoo, zoo);
        CollectionUtils.<Animal>copy(zoo, carnivores);
        CollectionUtils.<Animal>copy(zoo, wolves);

        CollectionUtils.<Carnivore>copy(zoo, zoo);
        CollectionUtils.<Carnivore>copy(zoo, carnivores);
        CollectionUtils.<Carnivore>copy(zoo, wolves);

        CollectionUtils.<Wolf>copy(zoo, zoo);
        CollectionUtils.<Wolf>copy(zoo, carnivores);
        CollectionUtils.<Wolf>copy(zoo, wolves);

    }

}

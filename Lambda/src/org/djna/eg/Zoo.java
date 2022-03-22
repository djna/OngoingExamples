package org.djna.eg;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Zoo {

    class Animal {}
    class Carnivore extends Animal {}
    class Herbivore extends Animal {}

    class Lion  extends Carnivore  {}
    class ManxCat  extends Carnivore {}
    class Wolf extends Carnivore  {}

    class Panda extends Herbivore {}
    class Horse extends Herbivore {}

    private List<Animal> theAnimals;

    public static void main(String[] args) {
        Zoo theZoo = new Zoo();
    }

    private Zoo() {
        theAnimals = new ArrayList<>();
        Lion[] lions = {new Lion(), new Lion()};
        List<Lion> lionsToAdd = Arrays.asList(lions );
        addCarnivores(lionsToAdd);

        List<Carnivore> carnivores = new ArrayList<>();
        findLions(carnivores);
    }

    public int addCarnivores( List<? extends Carnivore> theCarnivores ){
         theAnimals.addAll(theCarnivores);
         return theAnimals.size();
    }

    public void  findLions( List<? super Carnivore> theCarnivores ){
        for( Animal animal : theAnimals) {
            if ( animal instanceof Lion ){
                Lion theLion = (Lion) animal;
                theCarnivores.add(theLion);
            }
        }
    }
}

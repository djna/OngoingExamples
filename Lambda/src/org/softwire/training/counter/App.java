package org.softwire.training.counter;

import org.softwire.training.counter.items.Apple;
import org.softwire.training.counter.items.Box;
import org.softwire.training.counter.items.Cart;
import org.softwire.training.counter.items.Colour;

import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] argv) {
        // Some things to count
        List<Apple> someApples = Arrays.asList(new Apple(Colour.RED), new Apple(Colour.RED), new Apple(Colour.GREEN));

        Box<Apple> boxOfApples = new Box<>();
        boxOfApples.add(new Apple(Colour.RED));
        boxOfApples.add(new Apple(Colour.GREEN));

        Cart<Apple> cart = new Cart<>();
        cart.add(boxOfApples);

        // Some counters
        Counter<Apple> appleCounter = new Counter<>(apple -> apple.getColour().equals(Colour.RED));
        someApples.forEach(appleCounter::add);

        System.out.println(appleCounter.getCount()); // Should be 2 because only 2 of them are red

        Counter<Cart<Apple>> cartCounter = new Counter<>();
        cartCounter.add(cart);

        System.out.println(cartCounter.getCount()); // Should be 2 (number of apples in the cart in total)

        Counter<Countable> anythingCounter = new Counter<>();
        someApples.forEach(anythingCounter::add);
        anythingCounter.add(cart);

        System.out.println(anythingCounter.getCount()); // Should be 5 - sum of the above
    }
}

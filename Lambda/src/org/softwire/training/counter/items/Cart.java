package org.softwire.training.counter.items;

import org.softwire.training.counter.Countable;

public class Cart<T extends Countable> extends Box<Box<T>> {
}

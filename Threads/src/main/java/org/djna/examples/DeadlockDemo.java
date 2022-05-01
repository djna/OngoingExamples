package org.djna.examples;

public class DeadlockDemo {
    private volatile int count = 0;
    private String name;

    public DeadlockDemo(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public synchronized int getCount() {
        System.out.printf("get in %s in thread %s%n",
                name, Thread.currentThread().getName());
        return count;
    }

    public synchronized void setCount(DeadlockDemo other) {
        System.out.printf("set in %s in thread %s, get from %s%n",
                name, Thread.currentThread().getName(), other.getName());
        this.count = other.getCount();
    }

    public static void main(String[] args) {
        DeadlockDemo d1 = new DeadlockDemo("One");
        DeadlockDemo d2 = new DeadlockDemo("Two");

        Thread t1 = new Thread(
                () -> {
                   d2.setCount(d1);
                }
        );

        Thread t2 = new Thread(
                () -> {
                    d1.setCount(d2);
                }
        );

        t1.start();
        t2.start();
    }
}

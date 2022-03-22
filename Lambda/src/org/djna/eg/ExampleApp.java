package org.djna.eg;

public class ExampleApp {
    private int x;
    private String name;

    public static void main(String[] args) {
        ExampleApp first = new ExampleApp(1300, "first");
        ExampleApp second = new ExampleApp(487, "second");

        System.out.printf("First: %s%n", first);

        System.out.printf("Second: %s%n", second);

        String firstResult = first.getFormattedCalculation(10);
        System.out.printf("First result %s%n", firstResult);

        String secondResult = second.getFormattedCalculation(10);
        System.out.printf("Second result %s%n", secondResult);
    }

    public ExampleApp(int initialX, String name){
        this.x = initialX;
        this.name = name;
    }

    public int calculate(int input){
        return input * x;
    }

    public String getFormattedCalculation(int input){
        int result = this.calculate(input);
        return String.format("%,d", result);
    }


    @Override
    public String toString() {
        return "ExampleApp{" +
                "x=" + x +
                ", name='" + name + '\'' +
                '}';
    }
}

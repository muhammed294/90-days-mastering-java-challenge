package edu.haramaya.cosc3053.day11;

public class Box<T> {
    private T content;

    // TODO 11.1: set and get
    public void set(T content) { this.content = content; }
    public T get() { return content; }
    public boolean isEmpty() { return content == null; }

    // Bounded type parameter: T must be a Number (Integer, Double, Long...)
    public static class NumberBox<T extends Number> {
        private final T value;

        public NumberBox(T value) {
            if (value == null) {
                throw new IllegalArgumentException("Value cannot be null.");
            }
            this.value = value;
        }

        public T get() { return value; }

        // We can call Number methods because T is guaranteed to be a Number
        public double doubled() { return value.doubleValue() * 2; }

        public boolean isGreaterThan(NumberBox<? extends Number> other) {
            return value.doubleValue() > other.get().doubleValue();
        }
    }

    // Generic method: <T> before the return type
    public static <T> void printBox(Box<T> box) {
        System.out.println(box.isEmpty() ? "Box is empty" : "Box holds: " + box.get());
    }

    // Extra demo: run it by passing the program argument "demo"
    private static void runDemo() {
        System.out.println("--- Demo ---");

        Box<Integer> intBox = new Box<>();
        intBox.set(42);                       // autoboxing: int -> Integer
        int n = intBox.get() + 8;             // unboxing: Integer -> int
        System.out.println("Integer box + 8 = " + n);

        Box<String> empty = new Box<>();
        printBox(empty);
        printBox(intBox);

        NumberBox<Integer> a = new NumberBox<>(10);
        NumberBox<Double> b = new NumberBox<>(3.5);
        System.out.println("a doubled = " + a.doubled());
        System.out.println("a > b ? " + a.isGreaterThan(b));

        // NumberBox<String> bad = new NumberBox<>("hi");   // compile error: String is not a Number
    }

    public static void main(String[] args) {
        Box<String> strBox = new Box<>();
        strBox.set("Haramaya University");
        System.out.println("Box Value: " + strBox.get());

        if (args.length > 0 && args[0].equals("demo")) {
            runDemo();
        }
    }
}